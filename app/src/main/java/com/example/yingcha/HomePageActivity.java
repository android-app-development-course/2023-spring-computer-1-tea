package com.example.yingcha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.Preference;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.yingcha.service.PrefercesService;

import java.util.Map;

public class HomePageActivity extends AppCompatActivity {

    private TextView title1, name_text, name,account_text,account;
    private Button love,support,taste,exit;
    private ImageButton first,community,my;
    private PrefercesService prefercesService;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        prefercesService=new PrefercesService(this);

        Typeface typeFace =Typeface.createFromAsset(getAssets(),"fonts/SanJiKaiShu-2.ttf");
        title1 =findViewById(R.id.title1);
        name_text=findViewById(R.id.name_text);
        name=findViewById(R.id.name);   //昵称
        account_text=findViewById(R.id.account_text);
        account=findViewById(R.id.account);  //账号
        love=findViewById(R.id.love);
        support=findViewById(R.id.support);
        taste=findViewById(R.id.taste);
        exit=findViewById(R.id.exit);
        first=findViewById(R.id.first);
        community=findViewById(R.id.community);
        my=findViewById(R.id.my);

        title1.setTypeface(typeFace);
        name_text.setTypeface(typeFace);
        name.setTypeface(typeFace);
        account_text.setTypeface(typeFace);
        account.setTypeface(typeFace);
        love.setTypeface(typeFace);
        support.setTypeface(typeFace);
        taste.setTypeface(typeFace);
        exit.setTypeface(typeFace);

        Map<String,String> params=prefercesService.getPreferences();
        account.setText(params.get("account"));
        name.setText(params.get("name"));


    }

    //跳转到收藏界面
    public void love(View view) {
        Intent intent=new Intent(this,MyCollection.class);
        startActivity(intent);
    }

    //跳转到点赞界面
    public void support(View view) {
        Intent intent=new Intent(this,MyLikes.class);
        startActivity(intent);
    }

    //跳转到我的口味界面
    public void taste(View view) {
        Intent intent=new Intent(this,TasteActivity.class);
        startActivity(intent);
    }

    //退出到登录界面
    public void exit(View view) {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    //跳转到首页
    public void first(View view) {
        Intent intent=new Intent(this,HomePageActivity.class);
        startActivity(intent);
    }

    //跳转到社区页面
    public void community(View view) {
        Intent intent=new Intent(this,CommunityActivity.class);
        startActivity(intent);
    }

    public void my(View view) {
        Intent intent=new Intent(this,HomePageActivity.class);
        startActivity(intent);
    }
}