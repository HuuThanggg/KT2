package com.example.kt2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "QuanLySanPham.db";
    private static final String TABLE_NAME = "SanPham";
    private static final String COL_MASP = "MaSP";
    private static final String COL_TENSP = "TenSP";
    private static final String COL_GIATIEN = "GiaTien";
    private static final String COL_IMAGE = "Image";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME +
                " (" + COL_MASP + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_TENSP + " TEXT, " +
                COL_GIATIEN + " REAL, " +
                COL_IMAGE + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String tenSP, double giaTien, String image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_TENSP, tenSP);
        contentValues.put(COL_GIATIEN, giaTien);
        contentValues.put(COL_IMAGE, image);

        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }
}
