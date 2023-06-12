package com.example.yingcha.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.yingcha.bean.User;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "Register.db";
    public static final String CREATE_TABLE_SQL = "create table register(account text, password text, name text) ";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
    }

    public long insertData(User u) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("account", u.getAccount());
        cv.put("password", u.getPassword());
        cv.put("name", u.getName());
        long row = db.insert("register", null, cv);
        return row;
    }

    public Boolean checkAccountPassword(String account, String password) {
        SQLiteDatabase db1 = getWritableDatabase();
        Boolean result = false;
        Cursor cursor = db1.query("register", null, "account like ?", new String[]{account}, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String password1 = cursor.getString(1);
                result = password1.equals(password);
                return result;
            }
        }
        return false;
    }

    public String findName(String account) {
        SQLiteDatabase db1 = getWritableDatabase();
        String username = "";
        Cursor cursor = db1.query("register", null, "account like ?", new String[]{account}, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                username = cursor.getString(2);
            }
        }
        return username;
    }
}

