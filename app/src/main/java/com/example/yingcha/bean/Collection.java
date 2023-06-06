package com.example.yingcha.bean;

import android.graphics.Bitmap;

/**
 * 封装收藏和点赞数据的实体类
 */
public class Collection {
    Integer id;
    String username;    //用户名
    String content;     //内容详情
    Bitmap logo;        //用户头像
    Bitmap image;       //发文图片

    public Collection() {
    }

    public Collection(Integer id, String username, String content, Bitmap logo, Bitmap image) {
        this.id = id;
        this.username = username;
        this.content = content;
        this.logo = logo;
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Bitmap getLogo() {
        return logo;
    }

    public void setLogo(Bitmap logo) {
        this.logo = logo;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
