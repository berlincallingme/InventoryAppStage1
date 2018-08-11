package com.example.brand.inventoryappstage1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.brand.inventoryappstage1.data.InventoryAppContract;


public class ProductCursorAdapter extends CursorAdapter {

    public ProductCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, final Context context, final Cursor cursor) {
        TextView productNameText = view.findViewById(R.id.list_name);
        TextView productPriceText = view.findViewById(R.id.list_price);
        final TextView productQuantityText = view.findViewById(R.id.list_quantity);
        Button saleButton = view.findViewById(R.id.sale_buton);
        int nameColumnIndex = cursor.getColumnIndex(InventoryAppContract.InventoryAppEntry.PROUDCT_NAME_COLUMN);
        int priceColumnIndex = cursor.getColumnIndex(InventoryAppContract.InventoryAppEntry.PRODUCT_PRICE_COLUMN);
        int quantityColumnIndex = cursor.getColumnIndex(InventoryAppContract.InventoryAppEntry.PRODUCT_QUANTITY_COLUMN);
        int idColumnIndex = cursor.getColumnIndex(InventoryAppContract.InventoryAppEntry._ID);

        String productName = cursor.getString(nameColumnIndex);
        String productPrice = cursor.getString(priceColumnIndex);
        String productQuantity = cursor.getString(quantityColumnIndex);
        final int id = cursor.getInt(idColumnIndex);
        productNameText.setText(productName);
        productPriceText.setText(productPrice);
        productQuantityText.setText(productQuantity);
        saleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(productQuantityText.getText().toString());
                quantity -= 1;
                if (quantity < 0) {
                    Toast.makeText(context, "You are out of product", Toast.LENGTH_SHORT).show();
                    return;
                }
                productQuantityText.setText(String.valueOf(quantity));
                updateQuantity(id, quantity, context);

            }
        });
    }

    private void updateQuantity(int id, int quantity, Context context) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(InventoryAppContract.InventoryAppEntry.PRODUCT_QUANTITY_COLUMN, quantity);
        Uri uri = Uri.withAppendedPath(InventoryAppContract.InventoryAppEntry.CONTENT_URI, "/" + id);
        context.getContentResolver().update(uri, contentValues, null, null);
    }
}