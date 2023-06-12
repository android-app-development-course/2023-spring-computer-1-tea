package com.example.yingcha.util;

import android.content.Context;

public class SizeUtils {
    //像素转dp工具类
    public static int dip2px(Context context,float dpValue){
        float scale=context.getResources().getDisplayMetrics().density;
        return (int)(dpValue * scale + 0.5);
    }
}
