package com.example.brand.inventoryappstage1;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.brand.inventoryappstage1.data.InventoryAppContract;


public class ProductEditorActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor> {

    EditText productName;
    EditText productPrice;
    EditText productQuantity;
    EditText supplierName;
    EditText supplierPhoneNumber;
    Button callSupplierButton;
    private Uri mCurrentProductUri;
    private static final int EXISTING_PRODUCT_LOADER = 0;
    Button increaseButton;
    Button decreaseButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_editor_layout);
        Intent intent = getIntent();
        mCurrentProductUri = intent.getData();

        productName = findViewById(R.id.product_name);
        productPrice = findViewById(R.id.product_price);
        productQuantity = findViewById(R.id.product_quantity);
        supplierName = findViewById(R.id.supplier_name);
        supplierPhoneNumber = findViewById(R.id.phone_number);

        callSupplierButton = findViewById(R.id.call_supplier);
        increaseButton = findViewById(R.id.increase_quantity_button);
        decreaseButton = findViewById(R.id.decrease_quantity_button_quantity_button);


        if (mCurrentProductUri == null) {
            setTitle("add product");
            callSupplierButton.setVisibility(View.GONE);
            invalidateOptionsMenu();
        } else {
            setTitle("edit product");
            getLoaderManager().initLoader(EXISTING_PRODUCT_LOADER, null, this);
        }

        Button saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String productNameEntered = productName.getText().toString().trim();
                if (TextUtils.isEmpty(productNameEntered)) {
                    Toast.makeText(ProductEditorActivity.this, "Enter product name", Toast.LENGTH_SHORT).show();
                    return;
                }

                String productPriceEntered = productPrice.getText().toString().trim();
                if (TextUtils.isEmpty(productPriceEntered)) {
                    Toast.makeText(ProductEditorActivity.this, "Enter product price", Toast.LENGTH_SHORT).show();
                    return;
                }

                String productQuantityEntered = productQuantity.getText().toString().trim();
                if (TextUtils.isEmpty(productQuantityEntered)) {
                    Toast.makeText(ProductEditorActivity.this, "Enter product quantity", Toast.LENGTH_SHORT).show();
                    return;
                }
                String productSupplierEntered = supplierName.getText().toString().trim();
                if (TextUtils.isEmpty(productSupplierEntered)) {
                    Toast.makeText(ProductEditorActivity.this, "Enter supplier name", Toast.LENGTH_SHORT).show();
                    return;
                }
                String productSupplierPhoneNumberEntered = supplierPhoneNumber.getText().toString().trim();
                if (TextUtils.isEmpty(productSupplierPhoneNumberEntered)) {
                    Toast.makeText(ProductEditorActivity.this, "Enter supplier phone number", Toast.LENGTH_SHORT).show();
                    return;
                }

                ContentValues values = new ContentValues();
                values.put(InventoryAppContract.InventoryAppEntry.PROUDCT_NAME_COLUMN, productNameEntered);
                values.put(InventoryAppContract.InventoryAppEntry.PRODUCT_PRICE_COLUMN, productPriceEntered);
                values.put(InventoryAppContract.InventoryAppEntry.PRODUCT_QUANTITY_COLUMN, productQuantityEntered);
                values.put(InventoryAppContract.InventoryAppEntry.PRODUCT_SUPPLIER_COLUMN, productSupplierEntered);
                values.put(InventoryAppContract.InventoryAppEntry.SUPPLIER_PHONE_NUMBER_COLUMN, productSupplierPhoneNumberEntered);

                if (mCurrentProductUri == null) {
                    Uri newUri = getContentResolver().insert(InventoryAppContract.InventoryAppEntry.CONTENT_URI, values);

                    if (newUri == null) {
                        Toast.makeText(ProductEditorActivity.this, "FAIL",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ProductEditorActivity.this, "success",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {

                    int rowsAffected = getContentResolver().update(mCurrentProductUri, values, null, null);
                    if (rowsAffected == 0) {
                        Toast.makeText(ProductEditorActivity.this, "error",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ProductEditorActivity.this, "success",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if (mCurrentProductUri == null) {
            MenuItem menuItem = menu.findItem(R.id.action_delete);
            menuItem.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                showDeleteConfirmationDialog();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private void showDeleteConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.delete_dialog_msg);
        builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                deleteProduct();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void deleteProduct() {
        if (mCurrentProductUri != null) {
            int rowsDeleted = getContentResolver().delete(mCurrentProductUri, null, null);
            if (rowsDeleted == 0) {
                Toast.makeText(this, "error",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "success",
                        Toast.LENGTH_SHORT).show();
            }
        }
        finish();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] projection = {
                InventoryAppContract.InventoryAppEntry._ID,
                InventoryAppContract.InventoryAppEntry.PROUDCT_NAME_COLUMN,
                InventoryAppContract.InventoryAppEntry.PRODUCT_PRICE_COLUMN,
                InventoryAppContract.InventoryAppEntry.PRODUCT_QUANTITY_COLUMN,
                InventoryAppContract.InventoryAppEntry.PRODUCT_SUPPLIER_COLUMN,
                InventoryAppContract.InventoryAppEntry.SUPPLIER_PHONE_NUMBER_COLUMN};

        return new CursorLoader(this,
                mCurrentProductUri,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (cursor == null || cursor.getCount() < 1) {
            return;
        }

        if (cursor.moveToFirst()) {
            int nameColumnIndex = cursor.getColumnIndex(InventoryAppContract.InventoryAppEntry.PROUDCT_NAME_COLUMN);
            int priceColumnIndex = cursor.getColumnIndex(InventoryAppContract.InventoryAppEntry.PRODUCT_PRICE_COLUMN);
            int quantityColumnIndex = cursor.getColumnIndex(InventoryAppContract.InventoryAppEntry.PRODUCT_QUANTITY_COLUMN);
            int supplierColumnIndex = cursor.getColumnIndex(InventoryAppContract.InventoryAppEntry.PRODUCT_SUPPLIER_COLUMN);
            int suppllierPhoneColumnIndex = cursor.getColumnIndex(InventoryAppContract.InventoryAppEntry.SUPPLIER_PHONE_NUMBER_COLUMN);
            int idColumnIndex = cursor.getColumnIndex(InventoryAppContract.InventoryAppEntry._ID);

            String name = cursor.getString(nameColumnIndex);
            int price = cursor.getInt(priceColumnIndex);
            int quantity = cursor.getInt(quantityColumnIndex);
            String supplier = cursor.getString(supplierColumnIndex);
            final int phone = cursor.getInt(suppllierPhoneColumnIndex);
            final int id = cursor.getInt(idColumnIndex);


            productName.setText(name);
            productPrice.setText(String.valueOf(price));
            productQuantity.setText(String.valueOf(quantity));
            supplierName.setText(supplier);
            supplierPhoneNumber.setText(String.valueOf(phone));

            callSupplierButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" + phone));
                    startActivity(intent);
                }
            });

            increaseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int quantity = Integer.parseInt(productQuantity.getText().toString());
                    quantity += 1;
                    productQuantity.setText(String.valueOf(quantity));
                    updateQuantity(id, quantity);

                }
            });

            decreaseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int quantity = Integer.parseInt(productQuantity.getText().toString());
                    quantity -= 1;
                    if (quantity < 0) {
                        Toast.makeText(ProductEditorActivity.this, "You are out of product", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    productQuantity.setText(String.valueOf(quantity));
                    updateQuantity(id, quantity);

                }
            });

        }

    }

    private void updateQuantity(int id, int quantity) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(InventoryAppContract.InventoryAppEntry.PRODUCT_QUANTITY_COLUMN, quantity);
        Uri uri = Uri.withAppendedPath(InventoryAppContract.InventoryAppEntry.CONTENT_URI, "/" + id);
        getContentResolver().update(uri, contentValues, null, null);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        productName.setText("");
        productPrice.setText("");
        productQuantity.setText("");
        supplierName.setText("");
        supplierPhoneNumber.setText("");

    }
}