package com.example.brand.inventoryappstage1;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.example.brand.inventoryappstage1.data.InventoryAppContract.InventoryAppEntry;
import com.example.brand.inventoryappstage1.data.InventoryAppDbHelper;

public class MainActivity extends AppCompatActivity {
    private InventoryAppDbHelper mInventoryAppDbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mInventoryAppDbHelper = new InventoryAppDbHelper(this);
        insertProduct();
        readProduct();
    }

    private void readProduct() {
        SQLiteDatabase database = mInventoryAppDbHelper.getReadableDatabase();
        Cursor cursor = database.query(
                InventoryAppEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        Log.i("Database:", "Output: "+ DatabaseUtils.dumpCursorToString(cursor));

        cursor.close();
    }

    private void insertProduct() {
        SQLiteDatabase db = mInventoryAppDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(InventoryAppEntry.PROUDCT_NAME_COLUMN, "Head Tennis Racquet");
        values.put(InventoryAppEntry.PRODUCT_PRICE_COLUMN, 199);
        values.put(InventoryAppEntry.PRODUCT_QUANTITY_COLUMN, 22);
        values.put(InventoryAppEntry.PRODUCT_SUPPLIER_COLUMN, "Head");
        values.put(InventoryAppEntry.SUPPLIER_PHONE_NUMBER_COLUMN, "1-800-234-5678");


        long newRowId = db.insert(InventoryAppEntry.TABLE_NAME, null, values);

        if (newRowId == -1) {
            Log.e("Error", "Something went wrong during the database save.");
        } else {
            Log.i("Success", "Product is saved");
        }

    }

}
