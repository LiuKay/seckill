package org.seckill.enums;

/**
 * 使用枚举表示状态
 * 数据字典最好放在枚举
 * Created by kay on 2017/4/29.
 */
public enum SeckillStatEnum {
    SUCCESS(1, "秒杀成功"),
    END(0, "秒杀结束"),
    REPEAT_KILL(-1, "重复秒杀"),
    INNER_ERROR(-2, "内部错误"),
    DATA_REWRITE(-3, "数据篡改");

    private final int state;

    private final String stateInfo;

    SeckillStatEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }
}
