package com.perdo.sqliteinventory;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class OpenHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Product.db";


    private static final String CREATE_PRODUCT_TABLE = "CREATE TABLE " + DatabaseInfo.TABLE_NAME +
            "( " + DatabaseInfo._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            DatabaseInfo.productCode + " text," +
            DatabaseInfo.productName + " text," +
            DatabaseInfo.productDes + " text," +
            DatabaseInfo.productQuantity + " text)";
    private static final String DELETE_PRODUCT_TABLE = "DROP TABLE IF EXISTS " + DatabaseInfo.TABLE_NAME;




    public OpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_PRODUCT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL(DELETE_PRODUCT_TABLE);
        onCreate(sqLiteDatabase);

    }


}
