package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dao.SeckillDao;
import org.seckill.entity.Seckill;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by kay on 2017/4/28.
 * 配置spring和junit的整合，junit启动时要加载springIOC容器
 * spring-test,junit
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {

    @Resource
    private SeckillDao seckillDao;

    @Test
    public void queryById() throws Exception {
        long id=1000;
        Seckill seckill =seckillDao.queryById(id);
        System.out.println(seckill.getName());
        System.out.println(seckill);
    }

    /**
     * Caused by: org.apache.ibatis.binding.BindingException: Parameter 'offset' not found.
     * Available parameters are [arg1, arg0, param1, param2]
     * @throws Exception
     * java没有保存形参的记录，多个参数要告诉Mybatis每个位置的参数叫什么
     */
    @Test
    public void queryAll() throws Exception {
       List<Seckill> seckillList =seckillDao.queryAll(0,100);
       for (Seckill seckill:seckillList){
           System.out.println(seckill);
       }
    }
    @Test
    public void reduceNumber() throws Exception {
        Date killTime=new Date();
        int reduceNumber=seckillDao.reduceNumber(1000L,killTime);
        System.out.println(reduceNumber);
    }





}