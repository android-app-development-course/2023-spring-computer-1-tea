package com.example.yingcha.adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yingcha.R;
import com.example.yingcha.bean.Collection;
import com.example.yingcha.db.CollectionDBOpenHelper;

import java.util.List;

public class CollectionAdapter extends BaseAdapter {
    private List<Collection> mCollectionList;
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public CollectionAdapter(Context mContext,List<Collection> mCollectionList) {
        this.mCollectionList = mCollectionList;
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mCollectionList.size();
    }

    @Override
    public Object getItem(int i) {
        return mCollectionList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view = mLayoutInflater.inflate(R.layout.collection_card_layout,viewGroup,false);
        }
        //获取控件
        TextView username = view.findViewById(R.id.username);
        TextView userId = view.findViewById(R.id.userId);
        ImageView image = view.findViewById(R.id.image);
        ImageView logo = view.findViewById(R.id.logo);
        //获得对象数据
        Collection collection = mCollectionList.get(i);
        //展示数据
        username.setText(collection.getUsername());
        userId.setText(collection.getId());
        image.setImageBitmap(collection.getImage());
        logo.setImageBitmap(collection.getLogo());
        return view;
    }
}
