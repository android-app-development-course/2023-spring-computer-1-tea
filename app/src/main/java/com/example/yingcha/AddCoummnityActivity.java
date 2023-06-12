package com.example.yingcha;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.yingcha.db.CommuItemDB;
import com.example.yingcha.bean.CommunityItem;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddCoummnityActivity extends AppCompatActivity {
    //添加动态页面
    private ImageButton back;
    //存取图片路径
    private Button submit;
    private EditText edtext;

    private CommuItemDB cmdb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_coummnity);
        cmdb=new CommuItemDB(this);
        //绑定顶部按钮
        back=findViewById(R.id.community_back);
        submit=findViewById(R.id.community_submit);
        //绑定输入框
        edtext=findViewById(R.id.add_edtext);

    }

    //返回社区页面
    public void turnback(View view) {
        Intent intent=new Intent(this,CommunityActivity.class);
        startActivity(intent);
    }
    public String getCurrentTimeFormat(){
        //获取当前日期并指定格式
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy年MM月dd日");
        Date date=new Date();
        return simpleDateFormat.format(date);
    }
    //发布动态
    public void msubmit(View view) {
        //发布动态
        String time=getCurrentTimeFormat();
        String content=edtext.getText().toString();
        //将数据保存到数据库中
        long row=cmdb.insertData(time,content);
        if(row!=-1){
            Toast.makeText(AddCoummnityActivity.this,"发布成功",Toast.LENGTH_SHORT).show();
            //自动回到上一个页面
            this.finish();
        }else{
            Toast.makeText(AddCoummnityActivity.this,"发布失败",Toast.LENGTH_SHORT).show();
        }
    }

}