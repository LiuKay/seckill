package org.seckill.entity;

import lombok.Data;

@Data
public class Goods {
    private String id;
    private String name;
    private String description;
    private int stock;
    private double price;
    private String img;
}
