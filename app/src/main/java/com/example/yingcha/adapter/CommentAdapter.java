package com.example.yingcha.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yingcha.CommentActivity;
import com.example.yingcha.R;
import com.example.yingcha.bean.CommentItem;
import com.example.yingcha.db.CommuItemDB;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MyViewHolder>{
    private List<CommentItem> itemList;
    private LayoutInflater layoutInflater;
    private Context context;
    private CommuItemDB commentdb;
    public CommentAdapter(Context context, List<CommentItem> itemList){
        this.itemList=itemList;
        this.context=context;
        layoutInflater=LayoutInflater.from(context);
    }
    @NonNull
    @Override
    //加载布局文件并返回MyViewHolder对象
    public CommentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //创建view对象
        View view = layoutInflater.inflate(R.layout.comment_list_item,parent,false);
        //创建MyViewHolder对象
        CommentAdapter.MyViewHolder myViewHolder=new CommentAdapter.MyViewHolder(view);
        return myViewHolder;
    }
    @Override
    //获取数据并显示到对应控件
    public void onBindViewHolder(@NonNull CommentAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        //给控件获取一下数据，注意不同类型调用不同的方法，设置图片用setImageResource（），设置文字用setText（）
        CommentItem item=itemList.get(position);
        holder.createdtime.setText(item.getCreatedTime());
        holder.content.setText(item.getmContext());
        holder.head.setImageResource(item.getHeadIcons());
        holder.username.setText(item.getUsernames());
        holder.comment_btn_dz.setOnClickListener(new View.OnClickListener() {
            boolean b=true;
            @Override
            public void onClick(View v) {
                //为点赞按钮设置点击切换颜色的事件
                if(!b){
                    v.setSelected(false);
                    b=true;
                }
                else{
                    v.setSelected(true);
                }
            }
        });

        holder.comment_btn_pl.setOnClickListener(new View.OnClickListener() {
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
    public void refreshData(List<CommentItem> itemlist){
        //刷新列表数据
        this.itemList=itemlist;
        //通知ViewHolder数据刷新了
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        //初始化控件
        ImageView head;
        TextView createdtime,content,username;

        ImageButton comment_btn_dz,comment_btn_pl;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //绑定控件，使适配器能够找到对应的控件
            createdtime=itemView.findViewById(R.id.comment_item_createdtime);
            content=itemView.findViewById(R.id.comment_item_context);
            head=itemView.findViewById(R.id.comment_item_head);
            username=itemView.findViewById(R.id.comment_item_username);
            comment_btn_dz=itemView.findViewById(R.id.comment_btn_dz);
            comment_btn_pl=itemView.findViewById(R.id.comment_btn_pl);
        }
    }
}
