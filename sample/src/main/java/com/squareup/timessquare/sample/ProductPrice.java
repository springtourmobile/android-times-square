package com.squareup.timessquare.sample;

import java.io.Serializable;

/**
 * 作者：wangdakuan
 * 主要功能:
 * 创建时间：2016/4/20 14:02
 */
public class ProductPrice implements Serializable {
    private String data; //日期
    private String price; //价格

    public ProductPrice() {
    }

    public ProductPrice(String data, String price) {
        this.data = data;
        this.price = price;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
