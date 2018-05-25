-- 数据库初始化脚本

CREATE DATABASE seckill;

USE seckill;

-- 库存秒杀表
CREATE TABLE seckill (
  `seckill_id`  BIGINT       NOT NULL AUTO_INCREMENT
  COMMENT '商品库存id',
  `name`        VARCHAR(120) NOT NULL
  COMMENT '商品名称',
  `number`      INT          NOT NULL
  COMMENT '库存数量',
  `start_time`  TIMESTAMP    NOT NULL
  COMMENT '开始时间',
  `end_time`    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
  COMMENT '结束时间',
  `create_time` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  PRIMARY KEY (seckill_id),
  KEY idx_start_time(start_time),
  KEY idx_end_time(end_time),
  KEY idx_create_time(create_time)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1000
  DEFAULT CHARSET = utf8
  COMMENT = '秒杀库存表';

-- 初始化数据
INSERT INTO
  seckill (`name`, `number`, `start_time`, `end_time`)
VALUES
  ('1000元秒杀iphone', 100, '2017-04-01 00:00:00', '2017-04-02 00:00:00'),
  ('500元秒杀ipad2', 100, '2017-04-01 00:00:00', '2017-04-02 00:00:00'),
  ('300元秒杀小米4', 100, '2017-04-01 00:00:00', '2017-04-02 00:00:00'),
  ('100元秒杀红米note', 100, '2017-04-01 00:00:00', '2017-04-02 00:00:00');

-- 秒杀成功明细表
-- 用户登录认证相关信息
CREATE TABLE success_killed (
  `seckill_id`  BIGINT    NOT NULL,
  `user_phone`  BIGINT    NOT NULL,
  `state`       TINYINT   NOT NULL DEFAULT -1,
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (seckill_id, user_phone), /*联合主键*/
  KEY idx_create_time(create_time)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT = '秒杀成功明细表';

