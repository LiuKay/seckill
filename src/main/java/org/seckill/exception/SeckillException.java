package org.seckill.exception;

/**
 * 秒杀异常
 * Created by kay on 2017/4/29.
 */
public class SeckillException extends RuntimeException {

    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
