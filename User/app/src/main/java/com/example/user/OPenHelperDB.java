package com.example.user;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class OPenHelperDB extends SQLiteOpenHelper {
    private static final String name ="UserDB";
    private static final int version =1;

    public OPenHelperDB(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE USER("+"_id INTEGER PRIMARY KEY AUTOINCREMENT,"+"Name TEXT,"+"Age INTEGER)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
