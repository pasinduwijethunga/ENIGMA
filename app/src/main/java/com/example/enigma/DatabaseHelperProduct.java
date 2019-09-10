package com.example.enigma;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelperProduct extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "product.db";
    public static final String TABLE_NAME = "product_table";
    public static final String Col_Id = "ID";
    public static final String Col_name = "Name";
    public static final String Col_title = "Title";
    public static final String Col_rating = "Rating";
    public static final String Col_price = "Price";


    public DatabaseHelperProduct(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT, Title text,Rating DOUBLE,Price INTEGER )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);

    }
}
