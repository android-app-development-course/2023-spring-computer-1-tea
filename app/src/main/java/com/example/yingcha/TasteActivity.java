package com.example.yingcha;

import androidx.appcompat.app.AppCompatActivity;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yingcha.db.TasteDbOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class TasteActivity extends AppCompatActivity {
    private TextView taste_label;
    private ImageButton return_homepage;
    private EditText edit_taste;
    private Button taste_submit;
    private GridView gridView;
    private ArrayAdapter<String> tasteAdapter;
    private List<String> mytaste;
    private TasteDbOpenHelper mTasteDbOPenHelper;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private  TextView taste_item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taste);

        mContext=this;
        mLayoutInflater=LayoutInflater.from(mContext);

        Typeface typeFace =Typeface.createFromAsset(getAssets(),"fonts/SanJiKaiShu-2.ttf");
        taste_label=findViewById(R.id.taste_label);
        return_homepage=findViewById(R.id.return_homepage);
        edit_taste=findViewById(R.id.edit_taste);
        taste_submit=findViewById(R.id.taste_submit);
        taste_label.setTypeface(typeFace);

        mTasteDbOPenHelper=new TasteDbOpenHelper(this);
        gridView=findViewById(R.id.gridview);
        mytaste=new ArrayList<>();
        mytaste=mTasteDbOPenHelper.queryAllFromDb();

        tasteAdapter=new ArrayAdapter<>(this, R.layout.taste_item,mytaste);
        gridView.setAdapter(tasteAdapter);

        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View v, int i, long l) {

                Dialog dialog=new Dialog(mContext, android.R.style.ThemeOverlay_Material_Dialog_Alert);
                View view=mLayoutInflater.inflate(R.layout.taste_delete,null);
                TextView tvDelete=view.findViewById(R.id.delete);
                tvDelete.setTypeface(typeFace);
                tvDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String taste=mytaste.get(i);
                        int row=mTasteDbOPenHelper.deleteFromDbById(taste);
                        if(row>0){
                            refreshDataFromDB();
                            Toast.makeText(TasteActivity.this,"成功删除口味!",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(TasteActivity.this,"删除口味失败！",Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                    }
                });
                dialog.setContentView(view);
                dialog.show();
                return false;
//                return true;
            }
        });

    }

    public void back(View view) {
        this.finish();
    }

    public void submit(View view) {
        String taste=edit_taste.getText().toString();
        if(TextUtils.isEmpty(taste)){
            Toast.makeText(TasteActivity.this,"输入不能为空！",Toast.LENGTH_SHORT).show();
            return;
        }
        long row=mTasteDbOPenHelper.insertData(taste);
        if(row!=-1){
            Toast.makeText(TasteActivity.this,"添加口味成功!",Toast.LENGTH_SHORT).show();
            refreshDataFromDB();
            edit_taste.setText("");
        }
        else{
            Toast.makeText(TasteActivity.this,"添加失败！",Toast.LENGTH_SHORT).show();
        }

    }


    private void refreshDataFromDB(){
        mytaste=mTasteDbOPenHelper.queryAllFromDb();
        tasteAdapter=new ArrayAdapter<>(this, R.layout.taste_item,mytaste);
        gridView.setAdapter(tasteAdapter);
    }
}