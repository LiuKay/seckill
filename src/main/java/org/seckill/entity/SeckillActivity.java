package org.seckill.entity;

import java.util.Date;

import lombok.Data;

/**
 * Created by kay on 2017/4/28.
 */
@Data
public class SeckillActivity {
    private long id;
    private String goodsId;
    private String title;
    private int number;
    private Date startTime;
    private Date endTime;
    private Date createTime;

}
