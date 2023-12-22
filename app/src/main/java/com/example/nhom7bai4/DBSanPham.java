package com.example.nhom7bai4;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBSanPham extends SQLiteOpenHelper {
    public DBSanPham(@Nullable Context context) {
        super(context, "dbQLSanPham", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE tbSanPham (masp TEXT, tensp TEXT, soluong TEXT, loaisp TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void ThemDL(SanPham sp) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "INSERT INTO tbSanPham VALUES (?, ?, ?, ?)";
        db.execSQL(sql, new String[]{sp.getMaSP(), sp.getTenSP(), String.valueOf(sp.getSoLuong()), sp.getLoaiSP()});
    }

    public List<SanPham> DocDL() {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM tbSanPham";
        List<SanPham> data = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                SanPham sp = new SanPham();
                sp.setMaSP(cursor.getString(0));
                sp.setTenSP(cursor.getString(1));
                sp.setSoLuong(cursor.getString(2));
                sp.setLoaiSP(cursor.getString(3));
                data.add(sp);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return data;
    }
    public void XoaDL(SanPham sp) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "DELETE FROM tbSanPham WHERE masp=?";
        db.execSQL(sql, new String[]{sp.getMaSP()});
    }

    public void SuaDL(SanPham sp) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "UPDATE tbSanPham SET tensp=?, soluong=?, loaisp=? WHERE masp=?";
        db.execSQL(sql, new String[]{sp.getTenSP(), String.valueOf(sp.getSoLuong()), sp.getLoaiSP(), sp.getMaSP()});
    }
}
