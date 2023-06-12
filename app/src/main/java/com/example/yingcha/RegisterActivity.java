package com.example.yingcha;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yingcha.bean.User;
import com.example.yingcha.db.DatabaseHelper;

public class RegisterActivity extends AppCompatActivity {
    private TextView register_account_text,register_password_text,register_name_text;
    private EditText register_account,register_password,register_name;
    private Button register,exit;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        databaseHelper=new DatabaseHelper(this);

        Typeface typeFace =Typeface.createFromAsset(getAssets(),"fonts/SanJiKaiShu-2.ttf");
        register_account_text=findViewById(R.id.register_account_text);
        register_password_text=findViewById(R.id.register_password_text);
        register_name_text=findViewById(R.id.register_name_text);
        register_account=findViewById(R.id.register_account);
        register_password=findViewById(R.id.register_password);
        register_name=findViewById(R.id.register_name);
        register=findViewById(R.id.register);
        exit=findViewById(R.id.exit);

        register_account_text.setTypeface(typeFace);
        register_password_text.setTypeface(typeFace);
        register_name_text.setTypeface(typeFace);
        register.setTypeface(typeFace);
        exit.setTypeface(typeFace);
    }

    public void register(View view) {
        String account=register_account.getText().toString();
        String password=register_password.getText().toString();
        String name=register_name.getText().toString();

        if(TextUtils.isEmpty(account)||TextUtils.isEmpty(password)){
            Toast.makeText(RegisterActivity.this,"账号或密码不能为空！",Toast.LENGTH_SHORT).show();
            return;
        }

        User u=new User();
        u.setAccount(account);
        u.setPassword(password);
        u.setName(name);

        long l=databaseHelper.insertData(u);
        if(l!=-1){
            Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(RegisterActivity.this,"注册失败",Toast.LENGTH_SHORT).show();
        }

    }

    public void exit(View view) {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}