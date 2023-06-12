package com.example.yingcha;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.yingcha.adapter.CommentAdapter;
import com.example.yingcha.bean.CommentItem;
import com.example.yingcha.db.CommentDB;
import com.example.yingcha.adapter.HomeAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommentActivity extends AppCompatActivity {
    //评论页面
    private List<CommentItem> itemList;
    //顶部按钮
    private ImageButton back;
    private Button commentsubmit;
    private EditText comedit;
    private RecyclerView recyclerView;//RecyclerView控件
    private CommentAdapter commentAdapter;
    private CommentDB commentdb;//数据库
    //输入框
    EditText cmedit;
    private int space=5;//设置RecyclerView控件的item的上下间距


    @Override
    protected void onResume() {
        //由于app的生命周期的缘故，要在onResume中再执行一遍查找所有数据的操作来进行刷新
        super.onResume();
        refreshDataFromDb();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        //绑定顶部按钮
        back=findViewById(R.id.comment_back);
        commentsubmit=findViewById(R.id.comment_submit);
        commentsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //发布评论
                String time=getCurrentTimeFormat();
                String content=comedit.getText().toString();
                //将数据保存到数据库中
                long row=commentdb.insertData(time,content);
                if(row!=-1){
                    Toast.makeText(CommentActivity.this,"发布成功",Toast.LENGTH_SHORT).show();
                    refreshDataFromDb();

                }else{
                    Toast.makeText(CommentActivity.this,"发布失败",Toast.LENGTH_SHORT).show();
                }
            }
        });
        //绑定输入框
        comedit=findViewById(R.id.comment_edit);
        initView();
        initData();
        initEvent();
    }
    private void refreshDataFromDb() {
        //拿到新的数据
        itemList=getDataFromDB();
        //通知适配器列表已刷新
        commentAdapter.refreshData(itemList);
    }
    private List<CommentItem> getDataFromDB() {
        return commentdb.queryAllFromDb();
    }
    private void initData() {
        //准备数据
        //列表
        itemList=new ArrayList<>();
        commentdb= new CommentDB(this);
    }
    private void initView(){
        //初始化视图
        //绑定RecycleView
        recyclerView = findViewById(R.id.comment_item);
        //设置为表格布局
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new CommentActivity.space_item(space));//给recycleView添加item的间距
    }
    private void initEvent() {
        //渲染列表
        commentAdapter=new CommentAdapter(this,itemList);
        recyclerView.setAdapter(commentAdapter);
    }

    class space_item extends RecyclerView.ItemDecoration{
        //设置item的间距
        private int space=5;
        public space_item(int space){
            this.space=space;
        }
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state){
            outRect.bottom=space;
            outRect.top=space;
        }
    }
    //返回社区页面
    public void turnback2(View view) {
        Intent intent=new Intent(this,CommunityActivity.class);
        startActivity(intent);
    }
    public String getCurrentTimeFormat(){
        //获取当前日期并指定格式
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy年MM月dd日");
        Date date=new Date();
        return simpleDateFormat.format(date);
    }
}