package com.example.brand.inventoryappstage1.data;

import android.provider.BaseColumns;


public final class InventoryAppContract {

    private InventoryAppContract() {}

    public static final class InventoryAppEntry implements BaseColumns {
        public final static String TABLE_NAME = "productInventory";
        public final static String _ID = BaseColumns._ID;
        public final static String PROUDCT_NAME_COLUMN ="productName";
        public final static String PRODUCT_PRICE_COLUMN = "productPrice";
        public final static String PRODUCT_QUANTITY_COLUMN = "productQuantity";
        public final static String PRODUCT_SUPPLIER_COLUMN = "productSupplierName";
        public final static String SUPPLIER_PHONE_NUMBER_COLUMN = "productSupplierPhoneNumber";
    }

}
