package com.perdo.sqliteinventory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    OpenHelper dbHelper;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter userAdapter;
    private RecyclerView.LayoutManager layoutManager;
    Button btnRegister;

    List<POJO> productDetails;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new OpenHelper(this);
        sqLiteDatabase= dbHelper.getReadableDatabase();
        recyclerView = (RecyclerView) findViewById(R.id.recycler);



        productDetails = new ArrayList<POJO>();
        productDetails.clear();
        Cursor c1 = sqLiteDatabase.query(DatabaseInfo.TABLE_NAME, null, null, null, null, null, null);

        if (c1 != null && c1.getCount() != 0) {
            productDetails.clear();
            while (c1.moveToNext()) {
                POJO prodDetails = new POJO();

                prodDetails.setP_id(c1.getInt(c1.getColumnIndex(DatabaseInfo._ID)));
                prodDetails.setP_code(c1.getString(c1.getColumnIndex(DatabaseInfo.productCode)));
                prodDetails.setP_name(c1.getString(c1.getColumnIndex(DatabaseInfo.productName)));
                prodDetails.setP_des(c1.getString(c1.getColumnIndex(DatabaseInfo.productDes)));
                prodDetails.setP_quantity(c1.getString(c1.getColumnIndex(DatabaseInfo.productQuantity)));
                productDetails.add(prodDetails);


            }


        }

        c1.close();
        layoutManager = new LinearLayoutManager(this);
        userAdapter = new RecycleAdapter(productDetails);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(userAdapter);


    }

    @Override
    protected void onDestroy() {
        sqLiteDatabase.close();
        super.onDestroy();
    }

    public void clickAddProduct(View view){
        startActivity(new Intent(this,AddingProduct.class));
    }
}