package com.example.yingcha.bean;

import java.io.Serializable;

public class CommunityItem implements Serializable {
    //社区页面列表item的定义
    private String createdTime;//创建时间
    private int imgs1;//图片1路径
    private int imgs2;//图片2路径
    private String mContext;//动态内容
    private int headIcons;//头像
    private String usernames;//昵称

    private String id;


    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public int getImgs1() {
        return imgs1;
    }

    public void setImgs1(int imgs1) {
        this.imgs1 = imgs1;
    }

    public int getImgs2() {
        return imgs2;
    }

    public void setImgs2(int imgs2) {
        this.imgs2 = imgs2;
    }

    public String getmContext() {
        return mContext;
    }

    public void setmContext(String mContext) {
        this.mContext = mContext;
    }

    public int getHeadIcons() {
        return headIcons;
    }

    public void setHeadIcons(int headIcons) {
        this.headIcons = headIcons;
    }

    public String getUsernames() {
        return usernames;
    }

    public void setUsernames(String usernames) {
        this.usernames = usernames;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Community{" +
                "img1='" + imgs1 + '\'' +
                "img2='" + imgs2 + '\'' +
                ", usernames='" + usernames + '\'' +
                ", content='" + mContext + '\'' +
                ", createdTime='" + createdTime + '\'' +
                ", headIcons='" +headIcons + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
