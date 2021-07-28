package org.seckill.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.seckill.cache.RedisDao;
import org.seckill.dao.SeckillDao;
import org.seckill.dao.SuccessKilledDao;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.entity.SuccessKilled;
import org.seckill.enums.SeckillStatEnum;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.seckill.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by kay on 2017/4/29.
 */
@Slf4j
@Service
public class SeckillServiceImpl implements SeckillService {

    @Autowired
    private SeckillDao seckillDao;

    @Autowired
    private SuccessKilledDao successKilledDao;

    @Autowired
    private RedisDao redisDao;

    //TODO: move out md盐值字符串，混淆
    private static final String slat = "dasdasdafafaukfh.jpi7o2o;3ip;'''''''135";

    public List<Seckill> getSeckillList() {
        return seckillDao.queryAll(0, 4);
    }

    public Seckill getById(long seckillId) {
        return seckillDao.queryById(seckillId);
    }

    /**
     * todo 优化点1：将秒杀商品对象缓存在redis中，避免高并发下大量去数据库查询
     *
     * @param seckillId
     * @return
     */
    public Exposer exportSeckillUrl(long seckillId) {

        //todo 在缓存超时的基础上维护一致性
        //1.先去缓存中找
        Seckill seckill = redisDao.getSeckill(seckillId);
        if (seckill == null) {
            //2.缓存中没有则去DB里面找
            seckill = seckillDao.queryById(seckillId);
            if (seckill == null) {
                return new Exposer(false, seckillId);
            } else {
                //3.从数据库取出来之后再放入缓存
                redisDao.putSeckill(seckill);
            }
        }
        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        Date nowTime = new Date();
        if (nowTime.getTime() < startTime.getTime()
                || nowTime.getTime() > endTime.getTime()) {
            return new Exposer(false, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
        }
        //不可逆的转化md5字符串
        String md5 = getMD5(seckillId);//TODO

        return new Exposer(true, md5, seckillId);
    }

    private String getMD5(long seckillId) {
        String base = seckillId + "/" + slat;
        return DigestUtils.md5DigestAsHex(base.getBytes());
    }

    /**
     * todo 优化点2：update操作会获取行级锁，其后操作都会有网络延迟和GC停顿，可以调整sql顺序，先insert再update
     * 使用注解控制事务方法的优点：
     * 1-开发达成一致约定，明确标注事务方法的编程风格
     * 2-保证事务执行时间尽可能短，不要穿插其他网络操作（RPC/HTTP请求）
     * 3-不是所有的方法都需要事务
     */
    @Transactional
    public SeckillExecution executeSeckill(long seckillId, long userphone, String md5) throws SeckillException {
        if (md5 == null || !md5.equals(getMD5(seckillId))) {
            throw new SeckillException("seckill data rewrite!");
        }
        //执行秒杀逻辑>减库存+记录购买记录
        Date nowTime = new Date();
        try {
            //记录购买行为
            int insertCount = successKilledDao.insertSuccessKilled(seckillId, userphone);
            //唯一:seckillId ,userphone
            if (insertCount <= 0) {
                throw new RepeatKillException("seckill repeat");
            } else {
                //减库存，todo 执行竞争条件，减库存，update获得行级锁
                int updateCount = seckillDao.reduceNumber(seckillId, nowTime);
                if (updateCount <= 0) {
                    //没有更新记录
                    throw new SeckillCloseException("seckill is closed");
                } else {
                    //秒杀成功
                    SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userphone);
                    return new SeckillExecution(seckillId, SeckillStatEnum.SUCCESS, successKilled);
                }
            }
        } catch (SeckillCloseException | RepeatKillException e1) {
            throw e1;
        } catch (Exception e) {
            //编译异常转换，spring发现后会回滚
            throw new SeckillException("seckill inner error:" + e.getMessage());
        }
    }
}
