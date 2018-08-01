package com.example.brand.inventoryappstage1.data;

import android.provider.BaseColumns;


public final class InventoryAppContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private InventoryAppContract() {}

    /**
     * Inner class that defines constant values for the pets database table.
     * Each entry in the table represents a single pet.
     */
    public static final class InventoryAppEntry implements BaseColumns {

        /** Name of database table for pets */
        public final static String TABLE_NAME = "product inventory";

        /**
         * Unique ID number for the pet (only for use in the database table).
         *
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        public final static String PROUDCT_NAME_COLUMN ="product name";


        public final static String PRODUCT_PRICE_COLUMN = "product price";


        public final static String PRODUCT_QUANTITY_COLUMN = "product quantity";

        public final static String PRODUCT_SUPPLIER_COLUMN = "product supplier name";
        public final static String SUPPLIER_PHONE_NUMBER_COLUMN = "product supplier phone number";




    }

}
