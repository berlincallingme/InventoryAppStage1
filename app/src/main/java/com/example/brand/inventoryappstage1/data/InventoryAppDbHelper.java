package com.example.brand.inventoryappstage1.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.brand.inventoryappstage1.data.InventoryAppContract.InventoryAppEntry;


public class InventoryAppDbHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "inventoryapp.db";

    private static final int DATABASE_VERSION = 1;

    public InventoryAppDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_INVENTORY_APP_TABLE = "CREATE TABLE " + InventoryAppEntry.TABLE_NAME + " ("
                + InventoryAppEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + InventoryAppEntry.PROUDCT_NAME_COLUMN + " TEXT NOT NULL, "
                + InventoryAppEntry.PRODUCT_PRICE_COLUMN + " INTEGER, "
                + InventoryAppEntry.PRODUCT_QUANTITY_COLUMN + " INTEGER DEFAULT 0, "
                + InventoryAppEntry.PRODUCT_SUPPLIER_COLUMN + " TEXT,"
                + InventoryAppEntry.SUPPLIER_PHONE_NUMBER_COLUMN + " TEXT);";

        db.execSQL(SQL_CREATE_INVENTORY_APP_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}