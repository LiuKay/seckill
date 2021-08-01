create table goods
(
    id          varchar(36) not null,
    name        varchar(20) charset utf8 null,
    img         varchar(50) charset utf8 null,
    stock       int null,
    description varchar(64) null,
    price       decimal(10, 2) null,
    constraint goods_id_uindex
        unique (id)
);

alter table goods
    add primary key (id);

INSERT INTO seckill.goods (id, name, img, stock, description, price)
VALUES ('db330c35-0900-4dcd-a679-d054f524644b', 'test-goods', null, 1000, 'this is a test goods.', 99.00);