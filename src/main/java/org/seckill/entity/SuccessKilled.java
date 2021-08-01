package org.seckill.entity;

import java.util.Date;

/**
 * Created by kay on 2017/4/28.
 */
public class SuccessKilled {
    private long seckillId;
    private long userPhone;
    private short state;
    private Date createTime;

    //变通
    //复合属性,多对一
    private SeckillActivity seckillActivity;

    public SeckillActivity getSeckill() {
        return seckillActivity;
    }

    public void setSeckill(SeckillActivity seckillActivity) {
        this.seckillActivity = seckillActivity;
    }


    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(long userPhone) {
        this.userPhone = userPhone;
    }

    public short getState() {
        return state;
    }

    public void setState(short state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SuccessKilled{" +
                "seckillId=" + seckillId +
                ", userPhone=" + userPhone +
                ", state=" + state +
                ", createTime=" + createTime +
                '}';
    }
}
