package com.nbs.app.latihan_sql_lite;


/**
 * Created by DENIS on 01/11/2016.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "biodatadiri.db";

    private static final int DATABASE_VERSION = 1;
    public DataHelper(Context context) {
        super(context, DATABASE_NAME,  null, DATABASE_VERSION);
// TODO Auto-generated constructor stub
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
// TODO Auto-generated method stub
        String sql1 = "create table login(id text primary key, username text null, password text null); ";
        Log.d("Data", "onCreate:" + sql1);
        db.execSQL(sql1);
        sql1 = "INSERT INTO login (id, username, password) VALUES ('1', 'denis', 'denis');";
        db.execSQL(sql1);

        String sql = "create table biodata(nim text primary key, nama text null, tgl text null, jk text null, alamat text null, angkatan text null, jurusan text null);";
        Log.d("Data", "onCreate: " + sql);
        db.execSQL(sql);
        sql = "INSERT INTO biodata (nim, nama, tgl, jk, alamat, angkatan, jurusan) VALUES ('1137050142', 'M. Denis Juliansyah', '1 Juli 1995', 'Laki-laki','Bandung', '2013' ,'Teknik Informatika');";
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
// TODO Auto-generated method stub
    }
}
