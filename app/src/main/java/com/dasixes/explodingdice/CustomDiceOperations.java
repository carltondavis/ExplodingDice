package com.dasixes.explodingdice;


/**
 * Created by cdavis on 1/19/2015.
 */

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class CustomDiceOperations {

    // Database fields
    private DatabaseWrapper dbHelper;
    private String[] CUSTOMDICE_TABLE_COLUMNS = {DatabaseWrapper.ID, DatabaseWrapper.DICEPILE_ID, DatabaseWrapper.AMOUNT};
    private SQLiteDatabase database;

    public CustomDiceOperations(Context context) {
        dbHelper = new DatabaseWrapper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public CustomDice addDice(int dicepile, int amount) {

        ContentValues values = new ContentValues();

        values.put(DatabaseWrapper.DICEPILE_ID, dicepile);
        values.put(DatabaseWrapper.AMOUNT, amount);

        long diceId = database.insert(DatabaseWrapper.CUSTOMDICE, null, values);
        System.out.println("DicePile added with id: " + diceId);
        // now that the dicepile is created return it ...
        Cursor cursor = database.query(DatabaseWrapper.CUSTOMDICE,
                CUSTOMDICE_TABLE_COLUMNS, DatabaseWrapper.ID + " = "
                        + diceId, null, null, null, null);

        cursor.moveToFirst();

        CustomDice newDicePile = parseDice(cursor);
        cursor.close();
        return newDicePile;
    }

    public CustomDice updateDice(CustomDice dicepile) {
        deleteDice(dicepile);
        return addDice(dicepile.getDicePileId(), dicepile.getAmount());
    }

    public void deleteDice(CustomDice dicepile) {
        long id = dicepile.getId();
        System.out.println("DicePile deleted with id: " + id);
        database.delete(DatabaseWrapper.CUSTOMDICE, DatabaseWrapper.ID
                + " = " + id, null);
    }

    public List getAllCustomDice() {
        List customdice = new ArrayList();

        Cursor cursor = database.query(DatabaseWrapper.CUSTOMDICE,
                CUSTOMDICE_TABLE_COLUMNS, null, null, null, null, DatabaseWrapper.DICEPILE_ID + " ASC");

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            CustomDice dicepile = parseDice(cursor);
            customdice.add(dicepile);
            cursor.moveToNext();
        }

        cursor.close();
        return customdice;
    }

    private CustomDice parseDice(Cursor cursor) {
        CustomDice customDice = new CustomDice();
        customDice.setId((cursor.getInt(0)));
        customDice.setDicePileId(cursor.getInt(1));
        customDice.setAmount(cursor.getInt(2));

        return customDice;
    }
}