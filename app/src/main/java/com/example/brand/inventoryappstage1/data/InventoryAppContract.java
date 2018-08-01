package com.example.brand.inventoryappstage1.data;

import android.provider.BaseColumns;


public final class InventoryAppContract {

    private InventoryAppContract() {}


    public static final class InventoryAppEntry implements BaseColumns {


        public final static String TABLE_NAME = "product inventory";

        public final static String _ID = BaseColumns._ID;

        public final static String PROUDCT_NAME_COLUMN ="product name";


        public final static String PRODUCT_PRICE_COLUMN = "product price";


        public final static String PRODUCT_QUANTITY_COLUMN = "product quantity";

        public final static String PRODUCT_SUPPLIER_COLUMN = "product supplier name";
        public final static String SUPPLIER_PHONE_NUMBER_COLUMN = "product supplier phone number";




    }

}
