package com.example.yingcha;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

//import com.example.first.views.FirstItem;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.List;

public class first_page extends AppCompatActivity {
    //private ViewPager mviewPager;//存放轮播图的ViewPager控件
    //private HomeAdapter homeAdapter;
//    private FirstItemDB cmdb;//数据库
//    private List<FirstItem> itemList;
//    private int space=5;//设置RecyclerView控件的item的上下间距


    //存放轮播图图片路径的集合
//    private List<Integer> mData=new ArrayList<>();
    //private RecyclerView recyclerView;//RecyclerView控件

//    @SuppressLint("MissingInflatedId")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_first);

//        //导航栏按钮绑定
//        first=findViewById(R.id.first);
//        community=findViewById(R.id.community);
//        my=findViewById(R.id.my);
//        soup=findViewById(R.id.detail3);
//        tea=findViewById(R.id.detail1);

//        initView();
//        initData();
//    }

    private ImageButton first,community,my,soup,tea;//底部导航栏的ImageButton
    private Banner banner;
    private List<Integer> banner_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

        initData();
        banner = findViewById(R.id.main_banner);

        banner.setAdapter(new BannerImageAdapter<Integer>(banner_data) {

            @Override
            public void onBindView(BannerImageHolder holder, Integer data, int position, int size) {
                holder.imageView.setImageResource(data);
            }
        });

        // 开启循环轮播
        banner.isAutoLoop(true);
        banner.setIndicator(new CircleIndicator(this));
        banner.setScrollBarFadeDuration(1000);
        // 设置指示器颜色(TODO 即选中时那个小点的颜色)
        banner.setIndicatorSelectedColor(Color.GREEN);
        // 开始轮播
        banner.start();

        //导航栏按钮绑定
        first=findViewById(R.id.first);
        community=findViewById(R.id.community);
        my=findViewById(R.id.my);
        soup=findViewById(R.id.detail3);
        tea=findViewById(R.id.detail1);
    }

    private void initData(){
        banner_data = new ArrayList<>();
        banner_data.add(R.drawable.banner1);
        banner_data.add(R.drawable.banner2);
        banner_data.add(R.drawable.banner3);
        banner_data.add(R.drawable.banner4);
        banner_data.add(R.drawable.banner5);
    }

////    @Override
////    protected void onPostResume() {
////        //由于app的生命周期的缘故，要在onResume中再执行一遍查找所有数据的操作来进行刷新
////        super.onPostResume();
////    }
//
//    private void initData() {
//        //准备数据
//        //轮播图
//        mData.add(R.drawable.banner1);
//        mData.add(R.drawable.banner2);
//        mData.add(R.drawable.banner3);
//        mData.add(R.drawable.banner4);
//        mData.add(R.drawable.banner5);
//        //数据准备完后进行更新
//        mpagerAdapter.notifyDataSetChanged();
//        //设置中间位置
//        mviewPager.setCurrentItem(Integer.MAX_VALUE / 2 + 1);
//    }
//
//    private void initView(){
//        //初始化视图
//        //绑定控件
//        mviewPager=this.findViewById(R.id.view_pager);
//        //设置适配器
//        mviewPager.setAdapter(mpagerAdapter);
//        //绑定RecycleView
//        //recyclerView = findViewById(R.id.first_item);
//        //设置为表格布局
//        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        //recyclerView.addItemDecoration(new space_item(space));//给recycleView添加item的间距
//    }
//
//    //ViewPager适配器写成内部类
//    private PagerAdapter mpagerAdapter=new PagerAdapter() {
//        @Override
//        public int getCount() {
//            //实现无限左右滑动
//            return Integer.MAX_VALUE;
//        }
//
//        @Override
//        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
//            return view==object;
//        }
//
//        @NonNull
//        @Override
//        public Object instantiateItem(@NonNull ViewGroup container, int position) {
//            //初始化:把ImageView放进父控件container里
//            //载入布局
//            View item=LayoutInflater.from(container.getContext()).inflate(R.layout.item_pager1,container,false);
//            ImageView iv=item.findViewById(R.id.cover);
//            //设置数据
//            int realPosition=position % mData.size();
//            iv.setImageResource(mData.get(realPosition));
//            if(iv.getParent() instanceof ViewGroup){
//                ((ViewGroup) iv.getParent()).removeView(iv);
//            }
//            container.addView(iv);
//            return iv;
//        }
//
//        @Override
//        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//            //销毁:从container里删除ImageView
//            container.removeView((View) object);
//        }
//    };


    //跳转到首页
    public void first(View view) {
        Intent intent=new Intent(this,first_page.class);
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

    //跳转到汤详情页面
    public void soup(View view) {
        Intent intent=new Intent(this,soup_details.class);
        startActivity(intent);
    }

    //跳转到茶详情页面
    public void tea(View view) {
        Intent intent=new Intent(this,tea_details.class);
        startActivity(intent);
    }

}
