package com.example.yingcha.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.Nullable;

import com.example.yingcha.bean.Collection;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 收藏和点赞增删改查工具类
 */
public class CollectionDBOpenHelper extends SQLiteOpenHelper {
    //数据库名称
    private static final String DB_NAME = "collection.db";
    //表名
    //收藏的表
    public static final String TABLE_NAME_COLLECTION = "collection";
    //点赞的表
    public static final String TABLE_NAME_LIKE = "myLike";
    //建表sql语句
    private static final String create_table_sql_collection =
        "create table if not exists " + TABLE_NAME_COLLECTION + "(id integer primary key autoincrement,username text,content text,logo blob,image blob)";
    private static final String create_table_sql_like =
            "create table if not exists " + TABLE_NAME_LIKE + "(id integer primary key autoincrement,username text,content text,logo blob,image blob)";

    public CollectionDBOpenHelper(Context context){
        super(context,DB_NAME,null,1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(create_table_sql_collection);
        sqLiteDatabase.execSQL(create_table_sql_like);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {}

    /**
     * 将动态添加收藏或点赞
     * @param collection 收藏对象
     * @param tableName 表名
     * @return 返回-1表示插入失败
     */
    public long insert(Collection collection,String tableName){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        //装数据
        values.put("username",collection.getUsername());
        values.put("content",collection.getContent());
        ByteArrayOutputStream os1 = new ByteArrayOutputStream();
        collection.getLogo().compress(Bitmap.CompressFormat.PNG, 100, os1);
        values.put("logo",os1.toByteArray());

        ByteArrayOutputStream os2 = new ByteArrayOutputStream();
        collection.getImage().compress(Bitmap.CompressFormat.PNG, 100, os2);
        values.put("image",os2.toByteArray());

        return db.insert(tableName,null,values);
    }

    /**
     * 取消收藏
     * @param id 收藏或点赞编号
     * @param tableName 表名
     * @return 返回0表示删除该列失败
     */
    public int deleteById(Integer id,String tableName){
        SQLiteDatabase db = getWritableDatabase();
        String[] strArr = {id.toString()};
        return db.delete(tableName,"id = ?",strArr);
    }

    /**
     * 查询所有收藏或点赞记录
     * @param tableName 表名
     * @return 收藏或点赞数据
     */
    @SuppressLint("Range")
    public List<Collection> queryAll(String tableName){
        SQLiteDatabase db = getWritableDatabase();
        List<Collection> list = new ArrayList<>();
        Cursor cursor = db.query(tableName,null,null,null,null,null,null);
        if(cursor!=null){
            while (cursor.moveToNext()){
                //(id integer primary key autoincrement,username text,content text,logo blob,image blob)
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String username = cursor.getString(cursor.getColumnIndex("username"));
                String content = cursor.getString(cursor.getColumnIndex("content"));
                //图片处理
                byte[] logoBlob = cursor.getBlob(cursor.getColumnIndex("logo"));
                Bitmap logoBmp = BitmapFactory.decodeByteArray(logoBlob, 0, logoBlob.length);
                byte[] imageBlob = cursor.getBlob(cursor.getColumnIndex("image"));
                Bitmap imageBmp = BitmapFactory.decodeByteArray(imageBlob, 0, imageBlob.length);
                //封装数据
                Collection collection = new Collection(id,username,content,logoBmp,imageBmp);
                list.add(collection);
            }
            cursor.close();
        }
        return list;
    }

    /**
     * 清空表中数据
     * @param tableName 表名
     */
    public void deleteAll(String tableName){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(tableName,null,null);
    }
}
