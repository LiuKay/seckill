package org.seckill.exception;

/**
 * 运行期异常 ，重复秒杀
 * Created by kay on 2017/4/29.
 */
public class RepeatKillException extends SeckillException {

    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
