package com.hello.d.studentsystem;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 太平人d on 2018/5/23.
 */
public class StudentManager extends SQLiteOpenHelper {

    private String Create_Table_SQL="create table student_tb(_id integer primary key autoincrement,name,grade)";

    public StudentManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public StudentManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Create_Table_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                 System.out.println("-------------------由"+oldVersion+"更新到"+newVersion+"-------------------------------");
    }
}
