create table seckill_activity
(
    seckill_id  bigint auto_increment comment '商品库存id'
        primary key,
    goods_id    varchar(36)                         not null,
    name        varchar(120)                        not null comment '商品名称',
    number      int                                 not null comment '库存数量',
    start_time  timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '开始时间',
    end_time    timestamp default CURRENT_TIMESTAMP not null comment '结束时间',
    create_time timestamp default CURRENT_TIMESTAMP not null comment '创建时间'
) comment '秒杀库存表' charset = utf8;

create
index idx_create_time
    on seckill_activity (create_time);

create
index idx_end_time
    on seckill_activity (end_time);

create
index idx_start_time
    on seckill_activity (start_time);

INSERT INTO seckill.seckill_activity (seckill_id, goods_id, name, number, start_time, end_time, create_time)
VALUES (1000, 'db330c35-0900-4dcd-a679-d054f524644b', '1000元秒杀iphone', 200, '2021-08-01 19:08:09',
        '2021-12-01 00:00:00', '2021-07-25 20:06:19');
INSERT INTO seckill.seckill_activity (seckill_id, goods_id, name, number, start_time, end_time, create_time)
VALUES (1001, 'db330c35-0900-4dcd-a679-d054f524644b', '500元秒杀ipad2', 200, '2021-08-01 19:08:09', '2021-12-01 00:00:00',
        '2021-07-25 20:06:19');
INSERT INTO seckill.seckill_activity (seckill_id, goods_id, name, number, start_time, end_time, create_time)
VALUES (1002, 'db330c35-0900-4dcd-a679-d054f524644b', '300元秒杀小米4', 200, '2021-08-01 19:08:09', '2021-12-01 00:00:00',
        '2021-07-25 20:06:19');
INSERT INTO seckill.seckill_activity (seckill_id, goods_id, name, number, start_time, end_time, create_time)
VALUES (1003, 'db330c35-0900-4dcd-a679-d054f524644b', '100元秒杀红米note', 200, '2021-08-01 19:08:09', '2021-12-01 00:00:00',
        '2021-07-25 20:06:19');