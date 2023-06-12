package com.example.yingcha.adapter;

import static android.provider.MediaStore.Images.Media.getBitmap;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yingcha.CommentActivity;
import com.example.yingcha.CommunityActivity;
import com.example.yingcha.R;
import com.example.yingcha.bean.Collection;
import com.example.yingcha.db.CollectionDBOpenHelper;
import com.example.yingcha.db.CommuItemDB;
import com.example.yingcha.bean.CommunityItem;


import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>{

    private List<CommunityItem> itemList;

    private CollectionDBOpenHelper collectionDBOpenHelper;
    private LayoutInflater layoutInflater;
    private Context context;
    private CommuItemDB cmdb;
    public HomeAdapter(Context context, List<CommunityItem> itemList){
        this.itemList=itemList;
        this.context=context;
        layoutInflater=LayoutInflater.from(context);
        //创建数据库对象
        collectionDBOpenHelper = new CollectionDBOpenHelper(context);
    }
    @NonNull
    @Override
    //加载布局文件并返回MyViewHolder对象
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //创建view对象
        View view = layoutInflater.inflate(R.layout.coummunity_list_item,parent,false);
        //创建MyViewHolder对象
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }
    @Override
    //获取数据并显示到对应控件
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        //给控件获取一下数据，注意不同类型调用不同的方法，设置图片用setImageResource（），设置文字用setText（）
        CommunityItem item=itemList.get(position);
        holder.createdtime.setText(item.getCreatedTime());
        holder.img1.setImageResource(item.getImgs1());
        holder.img2.setImageResource(item.getImgs2());
        holder.content.setText(item.getmContext());
        holder.head.setImageResource(item.getHeadIcons());
        holder.username.setText(item.getUsernames());
        holder.btn_dz.setOnClickListener(new View.OnClickListener() {
            boolean b=true;
            @Override
            public void onClick(View v) {
                //为点赞按钮设置点击切换颜色的事件
                if(!b){
                    v.setSelected(false);
                    b=true;
                }
                else{
                    //添加点赞内容到数据库
                    //封装数据
                    Collection collection = new Collection();
                    collection.setUsername(item.getUsernames());
                    collection.setContent(item.getmContext());
                    //图片1
                    Bitmap bitmap1 = ((BitmapDrawable)holder.img1.getDrawable()).getBitmap();
                    collection.setImage(bitmap1);
                    //图片2
                    Bitmap bitmap2 = ((BitmapDrawable)holder.head.getDrawable()).getBitmap();
                    collection.setLogo(bitmap2);
                    //数据库插入数据
                    collectionDBOpenHelper.insert(collection,CollectionDBOpenHelper.TABLE_NAME_LIKE);
                    v.setSelected(true);
                }
            }
        });
        holder.btn_sc.setOnClickListener(new View.OnClickListener() {
            boolean b=true;
            @Override
            public void onClick(View v) {
                //为收藏按钮设置点击切换颜色的事件
                if(!b){
                    v.setSelected(false);
                    b=true;
                }
                else{
                    //添加点赞内容到数据库
                    //封装数据
                    Collection collection = new Collection();
                    collection.setUsername(item.getUsernames());
                    collection.setContent(item.getmContext());
                    //图片1
                    Bitmap bitmap1 = ((BitmapDrawable)holder.img1.getDrawable()).getBitmap();
                    collection.setImage(bitmap1);
                    //图片2
                    Bitmap bitmap2 = ((BitmapDrawable)holder.head.getDrawable()).getBitmap();
                    collection.setLogo(bitmap2);
                    //数据库插入数据
                    collectionDBOpenHelper.insert(collection,CollectionDBOpenHelper.TABLE_NAME_COLLECTION);
                    v.setSelected(true);
                }
            }
        });
        holder.btn_pl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击评论按钮跳转到评论页面
                Intent intent = new Intent(context, CommentActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        //获取列表条目总数
        return itemList.size();
    }
    public void refreshData(List<CommunityItem> itemlist){
        //刷新列表数据
        this.itemList=itemlist;
        //通知ViewHolder数据刷新了
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        //初始化控件
        ImageView img1,img2,head;
        TextView createdtime,content,username;

        ImageButton btn_dz,btn_sc,btn_pl;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //绑定控件，使适配器能够找到对应的控件
            createdtime=itemView.findViewById(R.id.community_item_createdtime);
            img1=itemView.findViewById(R.id.community_item_img1);
            img2=itemView.findViewById(R.id.community_item_img2);
            content=itemView.findViewById(R.id.community_item_context);
            head=itemView.findViewById(R.id.community_item_head);
            username=itemView.findViewById(R.id.community_item_username);
            btn_dz=itemView.findViewById(R.id.btn_dz);
            btn_sc=itemView.findViewById(R.id.btn_sc);
            btn_pl=itemView.findViewById(R.id.btn_pl);
        }
    }

}
