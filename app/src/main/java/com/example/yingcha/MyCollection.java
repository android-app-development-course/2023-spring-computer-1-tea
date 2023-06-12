package com.example.yingcha;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yingcha.adapter.CollectionAdapter;
import com.example.yingcha.bean.Collection;
import com.example.yingcha.db.CollectionDBOpenHelper;

import java.util.List;

public class MyCollection extends AppCompatActivity {
    private GridView gridView;
    private List<Collection> mCollectionList;
    private CollectionAdapter mCollectionAdapter;
    private TextView myCollectionText;
    private ImageView returnButton;
    private Typeface typeFace;
    private CollectionDBOpenHelper mCollectionDBOpenHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collection);
        initView();
        initData();
        initEvent(this);
    }

    /**
     * 初始化相关事件
     */
    private void initEvent(Context context){
        //设置返回按钮
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,HomePageActivity.class);
                startActivity(intent);
            }
        });
        //展示数据
       mCollectionAdapter = new CollectionAdapter(this,mCollectionList);
       gridView.setAdapter(mCollectionAdapter);
        /**
         * 添加长按出现浮窗
         */
       gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
           @Override
           public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
               //获取数据
               Collection collection = mCollectionList.get(i);
               //弹窗
               //获得布局文件
               View inflate = LayoutInflater.from(context).inflate(R.layout.popup_window,null);
               //创建弹窗对像
               PopupWindow popupWindow = new PopupWindow(view,
                       ViewGroup.LayoutParams.WRAP_CONTENT,
                       ViewGroup.LayoutParams.WRAP_CONTENT,
                       true);
               //对应弹窗和布局
               popupWindow.setContentView(inflate);
               //设置弹窗位置
               popupWindow.showAsDropDown(view, Gravity.CENTER,0);
               //设置展现数据
               ImageView logo = (ImageView) findViewById(R.id.winLogo);
               ImageView image = (ImageView)findViewById(R.id.winImage);
               TextView username = (TextView) findViewById(R.id.winUsername);
               TextView content = (TextView)findViewById(R.id.winContent);
               logo.setImageBitmap(collection.getLogo());
               image.setImageBitmap(collection.getImage());
               username.setText(collection.getUsername());
               content.setText(collection.getContent());
               //设置布局内点击事件
               Button button = inflate.findViewById(R.id.winCloseBtn);
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
               return true;
           }

       });

    }

    /**
     * 初始化控件
     */
    private void initView(){
        typeFace =Typeface.createFromAsset(getAssets(),"fonts/SanJiKaiShu-2.ttf");
        gridView = (GridView) findViewById(R.id.gridview_collection);
        mCollectionDBOpenHelper = new CollectionDBOpenHelper(this);
        myCollectionText = (TextView) findViewById(R.id.textView6);
        myCollectionText.setTypeface(typeFace);
        returnButton = (ImageView) findViewById(R.id.returnButton);
    }

    /**
     * 初始化数据
     */
    private void initData(){
        mCollectionList = mCollectionDBOpenHelper.queryAll(CollectionDBOpenHelper.TABLE_NAME_COLLECTION);
    }

    public void quitCollection(View view) {
        TextView userIdText = (TextView) findViewById(R.id.userId);
        Integer userId = Integer.getInteger(userIdText.getText().toString());
        int res = mCollectionDBOpenHelper.deleteById(userId,CollectionDBOpenHelper.TABLE_NAME_COLLECTION);
        //实现刷新
        if(res>0){
            Toast.makeText(this,"取消成功",Toast.LENGTH_SHORT).show();
            initData();
            initEvent(this);
        }else{
            Toast.makeText(this,"取消失败",Toast.LENGTH_SHORT).show();
        }
    }
}