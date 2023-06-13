package com.example.yingcha.views;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.example.yingcha.R;

public class SubViewPager extends ViewPager {
    private static final String TAG = "SubViewPager";
    private Handler handler;

    public SubViewPager(@NonNull Context context) {
        //统一构造方法的入口，跳转到第二个构造方法
        this(context,null);
    }

    public SubViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //使用句柄设置切换时间
        handler = new Handler(Looper.getMainLooper());
        View item=LayoutInflater.from(context).inflate(R.layout.looper_pager_layout1,this,false);
        addView(item);
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
        private int currentItem;

        public void setCurrentItem(int currentItem) {
            this.currentItem = currentItem;
        }

        public int getCurrentItem() {
            return currentItem;
        }

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
