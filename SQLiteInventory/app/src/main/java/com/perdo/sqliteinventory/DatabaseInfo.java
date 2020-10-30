package com.perdo.sqliteinventory;

import android.provider.BaseColumns;

public class DatabaseInfo implements BaseColumns{


    private DatabaseInfo() {
    }

        public static final String TABLE_NAME = "ProductTable";
        public static final String productCode = "ProductCode";
        public static final String productName = "ProductName";
        public static final String productDes = "ProductDescription";
        public static final String productQuantity = "ProductTable";

}
