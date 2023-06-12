package com.example.yingcha.bean;

public class PageItem {
    //单一页面类
    private Integer picResId;//图片路径

    public Integer getPicResId() {
        return picResId;
    }

    public void setPicResId(Integer picResId) {
        this.picResId = picResId;
    }

    public PageItem(Integer picResId) {
        //构造方法
        this.picResId = picResId;
    }
}
