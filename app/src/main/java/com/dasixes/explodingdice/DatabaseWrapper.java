package com.dasixes.explodingdice;

/**
 * Created by cdavis on 1/19/2015.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseWrapper extends SQLiteOpenHelper {

    public static final String CUSTOMDICE = "CUSTOMDICE";
    public static final String ID = "_id";
    public static final String DICEPILE_ID = "_dicepile_id";
    public static final String AMOUNT = "_amount";

    private static final String DATABASE_NAME = "RunnerDice.db";
    private static final int DATABASE_VERSION = 2;

    // creation SQLite statement
    private static final String DATABASE_CREATE = "create table " + CUSTOMDICE
            + "(" + ID + " integer primary key autoincrement, "
            + DICEPILE_ID + " integer not null,"
            + AMOUNT + " integer not null);";

    public DatabaseWrapper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // you should do some logging in here
        // ..

        db.execSQL("DROP TABLE IF EXISTS " + CUSTOMDICE);
        onCreate(db);
    }

}