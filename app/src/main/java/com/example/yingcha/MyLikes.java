package com.example.yingcha;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

public class MyLikes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_likes);
    }
    /**
     * 点击弹窗
     * @param view
     */
    public void windowClick2(View view) {
        //获得布局文件
        View inflate = LayoutInflater.from(this).inflate(R.layout.popup_window,null);
        //创建弹窗对像
        PopupWindow popupWindow = new PopupWindow(view,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                true);
        //绑定弹窗和布局
        popupWindow.setContentView(inflate);
        //设置弹窗位置
        popupWindow.showAsDropDown(view, Gravity.CENTER,0);
        //设置布局内点击事件
        Button button = inflate.findViewById(R.id.imageView8);
        button.setOnClickListener(new View.OnClickListener() {
            /**
             * 关闭弹窗
             * @param view
             */
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
    }
}