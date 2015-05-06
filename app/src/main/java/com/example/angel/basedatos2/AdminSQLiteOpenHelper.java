package com.example.angel.basedatos2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by angel on 05/05/2015.
 */
public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table contactos(dni integer primary key, nombre text, numero text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnte, int versionNue) {

        db.execSQL("drop table if exists contactos");
        db.execSQL("create table contactos(dni integer primary key, nombre text, numero text)");
    }
}



