package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dao.SeckillDao;
import org.seckill.dao.SuccessKilledDao;
import org.seckill.entity.SuccessKilled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * Created by kay on 2017/4/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKilledDaoTest {

    @Autowired
    private SuccessKilledDao successKilledDao;

    /**
     * 第一次 insertCount=1；
     * 第二次 insertCount=0；
     * 原因在于写sql语句的时候 INSERT IGNORE INTO success_killed(seckill_id,user_phone)
     * VALUES (#{seckillId},#{userPhone})，否则出现主键冲突将报错。
     * 用返回值来显示插入数量
     * @throws Exception
     */
    @Test
    public void insertSuccessKilled() throws Exception {
        long id=1000L;
        long phone=1806279547;
        int insertCount=successKilledDao.insertSuccessKilled(id,phone);
        System.out.println(insertCount);
    }

    @Test
    public void queryByIdWithSeckill() throws Exception {
        long id=1000L;
        long phone=1806279547;
        SuccessKilled successKilled=successKilledDao.queryByIdWithSeckill(id,phone);
        System.out.println(successKilled);
        System.out.println(successKilled.getSeckill());
    }

}