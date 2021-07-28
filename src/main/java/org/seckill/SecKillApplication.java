package org.seckill;

import org.mybatis.spring.annotation.MapperScan;
import org.seckill.cache.RedisDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@MapperScan(basePackages = "org.seckill.dao")
@SpringBootApplication
public class SecKillApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecKillApplication.class, args);
    }

    @Value("${redis.port:6379}")
    private int port;

    @Value("${redis.host:localhost}")
    private String host;

    @Bean
    public RedisDao redisDao() {
        return new RedisDao(port, host);
    }
}
