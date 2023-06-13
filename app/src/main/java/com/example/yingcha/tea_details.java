package com.example.yingcha;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class tea_details extends AppCompatActivity {
    private ImageButton back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tea_details);

        back=findViewById(R.id.returnButton);
    }

    public void back(View view) {
        Intent intent=new Intent(this,first_page.class);
        startActivity(intent);
    }
}
