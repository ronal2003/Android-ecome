package com.cdp.agenda.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NOMBRE = "pruebaCrud.db";
    public static final String TABLE_CLIENTE = "cliente";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_CLIENTE + "(" +
                "cli_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "cli_nombre TEXT NOT NULL DEFAULT ''," +
                "cli_apelli TEXT NOT NULL DEFAULT ''," +
                "cli_cedula VARCHAR(30) NOT NULL DEFAULT ''," +
                "cli_telefo TEXT NOT NULL DEFAULT ''," +
                "cli_email TEXT DEFAULT ''," +
                "cli_edad INTEGER DEFAULT ''," +
                "cli_fechaNacimiento VARCHAR(30) NOT NULL DEFAULT '')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_CLIENTE);
        onCreate(sqLiteDatabase);

    }
}
