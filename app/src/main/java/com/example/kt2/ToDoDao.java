package com.example.kt2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.kt2.DatabaseHelper;
import com.example.kt2.Product;

import java.util.ArrayList;

public class ToDoDao {
    private static final String TAG = "ToDoDao";
    private final DatabaseHelper dbHelper;

    public ToDoDao(Context context) {
        this.dbHelper = new DatabaseHelper(context);
    }

    @SuppressLint("Range")
    public ArrayList<Product> getListProduct() {
        ArrayList<Product> list = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        database.beginTransaction();
        Cursor cursor = null;
        try {
            cursor = database.rawQuery("SELECT * FROM SanPham", null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                do {
                    String tenSP = cursor.getString(cursor.getColumnIndexOrThrow("TenSP"));
                    double giaTien = cursor.getDouble(cursor.getColumnIndexOrThrow("GiaTien"));
                    String image = cursor.getString(cursor.getColumnIndexOrThrow("Image"));

                    // Kiểm tra và sử dụng giá trị mặc định nếu cần
                    if (image == null) {
                        image = "default_image_url";
                    }


                } while (cursor.moveToNext());

                database.setTransactionSuccessful();
            }
        } catch (Exception e) {
            Log.e(TAG, "getListProduct: " + e);
        } finally {
            // Đóng Cursor sau khi sử dụng
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }

            database.endTransaction();
        }

        return list;
    }
}
