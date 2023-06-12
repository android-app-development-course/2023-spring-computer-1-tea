package com.example.yingcha.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.yingcha.R;
import com.example.yingcha.bean.CommentItem;

import java.util.ArrayList;
import java.util.List;

public class CommentDB extends SQLiteOpenHelper {
    //存时间和内容
    private static final String DB_NAME="CommentSQLite.db1";
    public static final String TABLE_NAME = "comment";
    public static final String ID = "id";//id
    public static final String TIME = "time";//时间
    public static final String CONTENT = "content";//内容
    public CommentDB(Context context){
        super(context,DB_NAME,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db1) {
        //建表
        db1.execSQL("CREATE TABLE " + TABLE_NAME + " (" + ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " TEXT ," + TIME
                + " TEXT ," + CONTENT
                + " TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public long insertData(String time,String content) {
        //将数据插入到数据库中(增)
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("time",time);
        values.put("content",content);
        return db.insert(TABLE_NAME, null, values);
    }
    public List<CommentItem> queryAllFromDb() {
        //从数据库中查找全部内容
        SQLiteDatabase db = getWritableDatabase();
        List<CommentItem> itemList = new ArrayList<>();
        //要展示的对应item的数据
        List<String> createdTime=new ArrayList<>();
        createdTime.add("2023年4月15日");
        createdTime.add("2023年4月15日");
        List<String> contents=new ArrayList<>();
        contents.add("看起来很好喝哦！");
        contents.add("get到了养生小知识");
        List<Integer> headsIcon=new ArrayList<>();
        headsIcon.add(R.drawable.cm_tx1);
        headsIcon.add(R.drawable.cm_tx2);
        List<String> usernames=new ArrayList<>();
        usernames.add("柒柒");
        usernames.add("林空");
        CommentItem item1=new CommentItem();
        item1.setCreatedTime(createdTime.get(0));
        item1.setUsernames(usernames.get(0));
        item1.setmContext(contents.get(0));
        item1.setHeadIcons(headsIcon.get(0));
        itemList.add(item1);
        CommentItem item2=new CommentItem();
        item2.setCreatedTime(createdTime.get(1));
        item2.setUsernames(usernames.get(1));
        item2.setmContext(contents.get(1));
        item2.setHeadIcons(headsIcon.get(1));
        itemList.add(item2);
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") String time = cursor.getString(cursor.getColumnIndex("time"));
                @SuppressLint("Range") String content = cursor.getString(cursor.getColumnIndex("content"));
                createdTime.add(time);
                contents.add(content);
                CommentItem item=new CommentItem();
                item.setCreatedTime(time);
                item.setUsernames("Outlier");
                item.setmContext(content);
                item.setHeadIcons(R.drawable.ic_head);
                itemList.add(item);
            }
            cursor.close();
        }
        return itemList;
    }
}
