package com.example.yingcha.bean;

import java.io.Serializable;

public class CommentItem implements Serializable {
    //评论页面列表item的定义
    private String createdTime;//创建时间
    private String mContext;//评论内容
    private int headIcons;//头像
    private String usernames;//昵称
    private String id;


    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
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
                ", content='" + mContext + '\'' +
                ", usernames='" + usernames + '\'' +
                ", createdTime='" + createdTime + '\'' +
                ", headIcons='" +headIcons + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
