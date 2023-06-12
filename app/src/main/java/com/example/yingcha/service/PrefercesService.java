package com.example.yingcha.service;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

public class PrefercesService {

    private Context context;
    public PrefercesService(Context context) {
        super();
        this.context = context;
    }

    //保存参数
    //@param account 账号
    public void save(String account,String name) {
        //第一个参数 指定名称 不需要写后缀名 第二个参数文件的操作模式
        SharedPreferences preferences=context.getSharedPreferences("itcast", Context.MODE_PRIVATE);
        //取到编辑器
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("account", account);
        editor.putString("name",name);
        //把数据提交给文件中
        editor.commit();
    }

    //获取各项配置参数
    public Map<String,String> getPreferences(){
        SharedPreferences pre=context.getSharedPreferences("itcast", Context.MODE_PRIVATE);
        //如果得到的name没有值则设置为空 pre.getString("name", "");
        Map<String,String> params=new HashMap<String,String>();
        params.put("account", pre.getString("account", ""));
        params.put("name",pre.getString("name",""));

        return params;


    }

}

