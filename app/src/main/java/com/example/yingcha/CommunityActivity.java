package com.example.yingcha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yingcha.adapter.HomeAdapter;
import com.example.yingcha.bean.CommunityItem;
import com.example.yingcha.db.CommuItemDB;
import com.example.yingcha.db.CommuItemDB;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommunityActivity extends AppCompatActivity {
    private ViewPager mviewPager;//存放轮播图的ViewPager控件
    private ImageButton first,community,my;//底部导航栏的ImageButton
    private List<CommunityItem> itemList;
    private HomeAdapter homeAdapter;
    private CommuItemDB cmdb;//数据库
    private FloatingActionButton mBtnAdd;//悬浮添加按钮
    private int space=5;//设置RecyclerView控件的item的上下间距


    //存放轮播图图片路径的集合
    private List<Integer> mData=new ArrayList<>();
    private RecyclerView recyclerView;//RecyclerView控件

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);

        //导航栏按钮绑定
        first=findViewById(R.id.first);
        community=findViewById(R.id.community);
        my=findViewById(R.id.my);
        initView();
        initData();
        initEvent();
    }


    @Override
    protected void onPostResume() {
        //由于app的生命周期的缘故，要在onResume中再执行一遍查找所有数据的操作来进行刷新
        super.onPostResume();
        refreshDataFromDb();
    }
    private void refreshDataFromDb() {
        //拿到新的数据
        itemList=getDataFromDB();
        //通知适配器列表已刷新
        homeAdapter.refreshData(itemList);
    }

    private List<CommunityItem> getDataFromDB() {
        return cmdb.queryAllFromDb();
    }

    private void initData() {
        //准备数据
        //轮播图
        mData.add(R.drawable.banner5);
        mData.add(R.drawable.banner2);
        mData.add(R.drawable.banner3);
        mData.add(R.drawable.banner4);
        mData.add(R.drawable.banner1);
        //数据准备完后进行更新
        mpagerAdapter.notifyDataSetChanged();
        //设置中间位置
        mviewPager.setCurrentItem(Integer.MAX_VALUE / 2 + 1);
        //列表
        itemList=new ArrayList<>();
        cmdb=new CommuItemDB(this);
    }

    private void initView(){
        //初始化视图
        //绑定控件
        mviewPager=this.findViewById(R.id.view_pager);
        //设置适配器
        mviewPager.setAdapter(mpagerAdapter);
        //绑定RecycleView
        recyclerView = findViewById(R.id.community_item);
        //设置为表格布局
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new space_item(space));//给recycleView添加item的间距
    }

    private void initEvent() {
        //渲染列表
        homeAdapter=new HomeAdapter(this,itemList);
        recyclerView.setAdapter(homeAdapter);
    }
    //ViewPager适配器写成内部类
    private PagerAdapter mpagerAdapter=new PagerAdapter() {
        @Override
        public int getCount() {
            //实现无限左右滑动
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view==object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            //初始化:把ImageView放进父控件container里
            //载入布局
            View item=LayoutInflater.from(container.getContext()).inflate(R.layout.item_pager,container,false);
            ImageView iv=item.findViewById(R.id.cover);
            //设置数据
            int realPosition=position % mData.size();
            iv.setImageResource(mData.get(realPosition));
            if(iv.getParent() instanceof ViewGroup){
                ((ViewGroup) iv.getParent()).removeView(iv);
            }
            container.addView(iv);
            return iv;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            //销毁:从container里删除ImageView
            container.removeView((View) object);
        }
    };
    //跳转到首页
    public void first(View view) {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    //跳转到社区页面
    public void community(View view) {
        Intent intent=new Intent(this,CommunityActivity.class);
        startActivity(intent);
    }
    //跳转到我的页面
    public void my(View view) {
        Intent intent=new Intent(this,HomePageActivity.class);
        startActivity(intent);
    }
    //更新数据


    class space_item extends RecyclerView.ItemDecoration{
        //设置item的间距
        private int space=5;
        public space_item(int space){
            this.space=space;
        }
        public void getItemOffsets(Rect outRect,View view,RecyclerView parent,RecyclerView.State state){
            outRect.bottom=space;
            outRect.top=space;
        }
    }


    public void add(View view) {
        //点击悬浮按钮跳转到发布动态页面
        Intent intent=new Intent(this,AddCoummnityActivity.class);
        startActivity(intent);
    }

    public void comment(View view) {
        //点击悬浮按钮跳转到评论页面
        Intent intent=new Intent(this,CommentActivity.class);
        startActivity(intent);
    }
}