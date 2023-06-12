package com.example.yingcha.views;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class SobViewPager extends ViewPager {

    private static final String TAG = "SobViewPager";
    private Handler handler;

    public SobViewPager(@NonNull Context context) {
        //统一构造方法的入口，跳转到第二个构造方法
        this(context,null);
    }

    public SobViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //使用句柄设置切换时间
        handler = new Handler(Looper.getMainLooper());

    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d(TAG,"onAttachedToWindow...");
        startLooper();
    }

    private void startLooper() {
        //设置切换时间
        handler.post(mTask);
    }
    private Runnable mTask=new Runnable() {
        @Override
        public void run() {
            //自动切换
            int currentItem=getCurrentItem();
            currentItem++;
            setCurrentItem(currentItem);
            postDelayed(this,2000);
        }
    };

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.d(TAG,"onDetachedFromWindow...");
        stopLooper();
    }

    private void stopLooper() {
        //停止自动切换
        handler.removeCallbacks(mTask);
    }
}
