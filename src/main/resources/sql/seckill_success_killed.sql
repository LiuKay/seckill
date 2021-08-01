create table success_killed
(
    seckill_id  bigint                              not null,
    user_phone  bigint                              not null,
    state       tinyint   default -1                not null,
    create_time timestamp default CURRENT_TIMESTAMP not null,
    primary key (seckill_id, user_phone)
) comment '秒杀成功明细表' charset = utf8;

create
index idx_create_time
    on success_killed (create_time);

