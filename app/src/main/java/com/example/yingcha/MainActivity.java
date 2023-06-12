package com.example.yingcha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yingcha.db.DatabaseHelper;
import com.example.yingcha.service.PrefercesService;

public class MainActivity extends AppCompatActivity {

    private TextView login_account_text,login_password_text;
    private EditText login_account,login_password;
    private Button login,register;

    private DatabaseHelper databaseHelper;
    private PrefercesService prefercesService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper=new DatabaseHelper(this);
        prefercesService = new PrefercesService(this);

        Typeface typeFace =Typeface.createFromAsset(getAssets(),"fonts/SanJiKaiShu-2.ttf");
        login_account_text=findViewById(R.id.login_account_text);
        login_password_text=findViewById(R.id.login_password_text);
        login_account=findViewById(R.id.login_account);
        login_password=findViewById(R.id.login_password);
        login=findViewById(R.id.login);
        register=findViewById(R.id.register);

        login_account_text.setTypeface(typeFace);
        login_password_text.setTypeface(typeFace);
        login.setTypeface(typeFace);
        register.setTypeface(typeFace);

    }

    public void login(View view) {
        String account=login_account.getText().toString();
        String password=login_password.getText().toString();

        if(account.equals("")||password.equals("")){
            Toast.makeText(MainActivity.this,"账号或密码不能为空！",Toast.LENGTH_SHORT).show();
        }
        else{
            Boolean login=databaseHelper.checkAccountPassword(account,password);
            String name=databaseHelper.findName(account);
            if(login==true){
                prefercesService.save(account,name);
                Toast.makeText(MainActivity.this,"登录成功！",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(this,HomePageActivity.class);
                startActivity(intent);

            }
            else{
                Toast.makeText(MainActivity.this,"登录失败！",Toast.LENGTH_SHORT).show();
            }

        }



    }

    public void register(View view) {
        Intent intent=new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }
}