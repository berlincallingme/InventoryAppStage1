package com.example.brand.inventoryappstage1.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;


public final class InventoryAppContract {
    public static final String CONTENT_AUTHORITY = "com.example.brand.inventoryappstage1";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_PRODUCTS = "inventoryappstage1";
    private InventoryAppContract() {}
    public static final class InventoryAppEntry implements BaseColumns {
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PRODUCTS);
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PRODUCTS;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PRODUCTS;
        public final static String TABLE_NAME = "productInventory";
        public final static String _ID = BaseColumns._ID;
        public final static String PROUDCT_NAME_COLUMN ="productName";
        public final static String PRODUCT_PRICE_COLUMN = "productPrice";
        public final static String PRODUCT_QUANTITY_COLUMN = "productQuantity";
        public final static String PRODUCT_SUPPLIER_COLUMN = "productSupplierName";
        public final static String SUPPLIER_PHONE_NUMBER_COLUMN = "productSupplierPhoneNumber";
    }

}
