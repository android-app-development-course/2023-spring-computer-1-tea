package com.example.yingcha.db;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.yingcha.R;
import com.example.yingcha.bean.CommunityItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommuItemDB extends SQLiteOpenHelper {
    //存时间和内容
    private static final String DB_NAME="noteSQLite.db";
    public static final String TABLE_NAME = "community";
    public static final String ID = "id";//id
    public static final String TIME = "time";//时间
    public static final String CONTENT = "content";//内容
    public CommuItemDB(Context context){
        super(context,DB_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //建表
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " TEXT ," + TIME
                + " TEXT ," + CONTENT
                + " TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public long insertData(String time,String content) {
        //将数据插入到数据库中(增)
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("time",time);
        values.put("content",content);
        return db.insert(TABLE_NAME, null, values);
    }
    public List<CommunityItem> queryAllFromDb() {
        //从数据库中查找全部内容
        SQLiteDatabase db = getWritableDatabase();
        List<CommunityItem> itemList = new ArrayList<>();
        //要展示的对应item的数据
        List<String> createdTime=new ArrayList<>();
        createdTime.add("2023年4月13日");
        createdTime.add("2023年4月15日");
        List<Integer> imgs1=new ArrayList<>();
        imgs1.add(R.drawable.dt1);
        imgs1.add(R.drawable.dt2);
        List<Integer> imgs2=new ArrayList<>();
        imgs2.add(R.drawable.dt4);
        imgs2.add(R.drawable.dt3);
        List<String> contents=new ArrayList<>();
        contents.add("打卡一家街边凉茶铺。");
        contents.add("自制下火凉茶——\n润喉罗汉果胎菊水!");
        List<Integer> headsIcon=new ArrayList<>();
        headsIcon.add(R.drawable.tx1);
        headsIcon.add(R.drawable.tx2);
        List<String> usernames=new ArrayList<>();
        usernames.add("局外人");
        usernames.add("断章。");
        CommunityItem item1=new CommunityItem();
        item1.setCreatedTime(createdTime.get(0));
        item1.setUsernames(usernames.get(0));
        item1.setmContext(contents.get(0));
        item1.setHeadIcons(headsIcon.get(0));
        item1.setImgs1(imgs1.get(0));
        item1.setImgs2(imgs2.get(0));
        itemList.add(item1);
        CommunityItem item2=new CommunityItem();
        item2.setCreatedTime(createdTime.get(1));
        item2.setUsernames(usernames.get(1));
        item2.setmContext(contents.get(1));
        item2.setHeadIcons(headsIcon.get(1));
        item2.setImgs1(imgs1.get(1));
        item2.setImgs2(imgs2.get(1));
        itemList.add(item2);
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") String time = cursor.getString(cursor.getColumnIndex("time"));
                @SuppressLint("Range") String content = cursor.getString(cursor.getColumnIndex("content"));
                createdTime.add(time);
                contents.add(content);
                CommunityItem item=new CommunityItem();
                item.setCreatedTime(time);
                item.setUsernames("Outlier");
                item.setmContext(content);
                item.setHeadIcons(R.drawable.ic_head);
                item.setImgs1(0);
                item.setImgs2(0);
                itemList.add(item);
            }
            cursor.close();
        }
        return itemList;
    }
}
