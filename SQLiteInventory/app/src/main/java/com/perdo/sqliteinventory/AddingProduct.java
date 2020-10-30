package com.perdo.sqliteinventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddingProduct extends AppCompatActivity {

    OpenHelper openHelper;
    String stCode;
    String stName;
    String stDes;
    String stQuantity;
    SQLiteDatabase sqLiteDatabase;

    EditText eCode;
    EditText eName;
    EditText eDes;
    EditText eQuantity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_product);
        openHelper = new OpenHelper(this);
        sqLiteDatabase = openHelper.getWritableDatabase();

        eCode=findViewById(R.id.editCode);
        eName=findViewById(R.id.editName);
        eDes=findViewById(R.id.editDes);
        eQuantity=findViewById(R.id.editQuanity);



    }

    public void clickAdd(View view){
        stCode=eCode.getText().toString();
        stName=eName.getText().toString();
        stDes=eDes.getText().toString();
        stQuantity=eQuantity.getText().toString();

        if(TextUtils.isEmpty(stCode) || TextUtils.isEmpty(stName) || TextUtils.isEmpty(stDes) || TextUtils.isEmpty(stQuantity)){

            Toast.makeText(this, "Check the Empty Fields", Toast.LENGTH_SHORT).show();
        }else {

            ContentValues contentValues = new ContentValues();
            contentValues.put(DatabaseInfo._ID, (byte[]) null);
            contentValues.put(DatabaseInfo.productCode, stCode);
            contentValues.put(DatabaseInfo.productName, stName);
            contentValues.put(DatabaseInfo.productDes, stDes);
            contentValues.put(DatabaseInfo.productQuantity, stQuantity);
            long rowId = sqLiteDatabase.insert(DatabaseInfo.TABLE_NAME, null, contentValues);
            if (rowId != -1) {

                Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainActivity.class));
                finish();

            } else {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            }

        }

    }

    @Override
    protected void onDestroy() {
        sqLiteDatabase.close();
        super.onDestroy();
    }
}