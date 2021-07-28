package org.seckill.exception;

/**
 * 秒杀关闭异常
 * Created by kay on 2017/4/29.
 */
public class SeckillCloseException extends SeckillException {

    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
