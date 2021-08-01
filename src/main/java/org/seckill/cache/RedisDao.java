package org.seckill.cache;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

import org.seckill.entity.SeckillActivity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


/**
 * Created by kay on 2017/9/26.
 * 补充 readis缓存
 */
public class RedisDao {

    private final Logger logger = LoggerFactory.getLogger(RedisDao.class);

    private static final String KEY_PERFIX = "seckill:";

    private final JedisPool jedisPool;

    //创建一个schema 用于序列化的转义
    private final RuntimeSchema<SeckillActivity> schema = RuntimeSchema.createFrom(SeckillActivity.class);

    public RedisDao(int port, String host) {
        jedisPool = new JedisPool(host, port);
    }

    public SeckillActivity getSeckill(long seckillId) {
        try {
            try (Jedis jedis = jedisPool.getResource()) {
                String key = KEY_PERFIX + seckillId;
                //没有用jdk内部的序列化
                //采用protostuff来自定义序列化 get->byte[]->Object
                byte[] bytes = jedis.get(key.getBytes());
                if (bytes != null) {
                    //空对象
                    SeckillActivity seckillActivity = schema.newMessage();
                    //填充空对象
                    ProtostuffIOUtil.mergeFrom(bytes, seckillActivity, schema);
                    return seckillActivity;
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public String putSeckill(SeckillActivity seckillActivity) {
        //todo Object->序列化->放入redis
        try {
            try (Jedis jedis = jedisPool.getResource()) {
                String key = KEY_PERFIX + seckillActivity.getSeckillId();
                byte[] bytes = ProtostuffIOUtil.toByteArray(seckillActivity, schema,
                                                            LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                int timeout = 60 * 60;  //设置过期时间过一天
                return jedis.setex(key.getBytes(), timeout, bytes); //返回ok或错误信息
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
}
