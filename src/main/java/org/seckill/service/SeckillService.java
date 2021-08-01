package org.seckill.service;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.SeckillActivity;
import org.seckill.exception.SeckillException;

import java.util.List;

/**
 * 业务接口设计原则：站在“使用者”的角度设计接口
 * 三个方面：方法定义的粒度（具体操作，行为）
 * 参数
 * 返回类型（entity 或者 dto 或者exception）
 * Created by kay on 2017/4/29.
 */
public interface SeckillService {
    /**
     * 查询所有秒杀记录
     *
     * @return
     */
    List<SeckillActivity> getSeckillList();

    /**
     * 查询单个秒杀记录
     *
     * @param seckillId
     * @return
     */
    SeckillActivity getById(long seckillId);

    /**
     * 秒杀开始时，输出秒杀接口的url
     * 否则输出秒杀开始时间和系统时间
     *
     * @param seckillId
     */
    Exposer exportSeckillUrl(long seckillId);

    /**
     * 执行秒杀接口
     * 可能会抛出异常，区分常见异常
     *
     * @param seckillId
     * @param userphone
     * @param md5
     */
    SeckillExecution executeSeckill(long seckillId, long userphone, String md5)
            throws SeckillException;
}
