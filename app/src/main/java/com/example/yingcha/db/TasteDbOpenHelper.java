package com.example.yingcha.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class TasteDbOpenHelper extends SQLiteOpenHelper {
    private static final String DB_NAME="tasteSQLite.db";
    private static final String TABLE_NAME_TASTE="Taste";
    private static final String CREATE_TABLE_SQL="create table "+TABLE_NAME_TASTE+" (taste text)";

    public TasteDbOpenHelper(Context context){super(context,DB_NAME,null,1);}
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long insertData(String taste){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("taste",taste);
        return db.insert(TABLE_NAME_TASTE,null,values);
    }


    public List<String> queryAllFromDb(){

        SQLiteDatabase db=getWritableDatabase();
        List<String>list=new ArrayList<>();
        Cursor cursor=db.query(TABLE_NAME_TASTE,null,null,null,null,null,null);

        if(cursor!=null){
            while(cursor.moveToNext()){
                @SuppressLint("Range")String taste=cursor.getString(cursor.getColumnIndex("taste"));
                list.add(taste);
            }
            cursor.close();
        }
        return list;
    }

    public int deleteFromDbById(String taste){
        SQLiteDatabase db=getWritableDatabase();
        return db.delete(TABLE_NAME_TASTE,"taste like ?",new String[]{taste});
    }








}

