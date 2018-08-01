package com.example.brand.inventoryappstage1;

import android.support.v7.app.AppCompatActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.brand.inventoryappstage1.data.InventoryAppContract;
import com.example.brand.inventoryappstage1.data.InventoryAppDbHelper;
import com.example.brand.inventoryappstage1.data.InventoryAppContract.InventoryAppEntry;

public class MainActivity extends AppCompatActivity {
    private InventoryAppDbHelper mInventoryAppDbHelper;



    private void insertProduct() {
        // Gets the database in write mode
        SQLiteDatabase db = mInventoryAppDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and Toto's pet attributes are the values.
        ContentValues values = new ContentValues();
        values.put(InventoryAppEntry.PROUDCT_NAME_COLUMN, "Head Tennis Racquet");
        values.put(InventoryAppEntry.PRODUCT_PRICE_COLUMN, "199");
        values.put(InventoryAppEntry.PRODUCT_QUANTITY_COLUMN, "22");
        values.put(InventoryAppEntry.PRODUCT_SUPPLIER_COLUMN, "Head");
        values.put(InventoryAppEntry.SUPPLIER_PHONE_NUMBER_COLUMN, "1-800-234-5678");

        // Insert a new row for Toto in the database, returning the ID of that new row.
        // The first argument for db.insert() is the pets table name.
        // The second argument provides the name of a column in which the framework
        // can insert NULL in the event that the ContentValues is empty (if
        // this is set to "null", then the framework will not insert a row when
        // there are no values).
        // The third argument is the ContentValues object containing the info for Toto.
        long newRowId = db.insert(InventoryAppEntry.TABLE_NAME, null, values);

        // Show a toast message depending on whether or not the insertion was successful
        if (newRowId == -1) {
            // If the row ID is -1, then there was an error with insertion.
            Toast.makeText(this, "Error with saving product", Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the insertion was successful and we can display a toast with the row ID.
            Toast.makeText(this, "Inventory Product saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mInventoryAppDbHelper = new InventoryAppDbHelper(this);
        insertProduct();
    }


}
