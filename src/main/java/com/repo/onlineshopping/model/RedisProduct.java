package com.repo.onlineshopping.model;

import java.io.Serializable;

/*
    Java Bean to fill items in redis cache
 */
public class RedisProduct implements Serializable {
    private String product_name;
    private Integer id;
    private String category;
    private Integer price;

    public RedisProduct(Integer id, String product_name, String category, Integer price) {
        this.id = id;
        this.product_name = product_name;
        this.category = category;
        this.price = price;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getId(){
        return id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
