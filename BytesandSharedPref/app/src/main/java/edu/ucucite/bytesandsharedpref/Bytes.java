package edu.ucucite.bytesandsharedpref;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Bytes extends AppCompatActivity {

    EditText eCode;
    EditText eName;
    EditText eDescription;
    EditText eQuantity;

    //Objects

    //Properties of Shared Pref
    private static final String field_code="Product Code";
    private static final String field_name="Product Name";
    private static final String field_description="Product Description";
    private static final String field_quantity="Product Quantity";


    private String stCode;
    private String stName;
    private String stDescription;
    private  String quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bytes);

        eCode=findViewById(R.id.editCodeBytes);
        eName=findViewById(R.id.editNameBytes);
        eDescription=findViewById(R.id.editDescriptionBytes);
        eQuantity=findViewById(R.id.editQuantityBytes);

    }

    public void clickAddBytes(View view) throws IOException {
        stCode="";
        stName="";
        stDescription="";
        quantity="";
        stCode=eCode.getText().toString();
        stName=eName.getText().toString();
        stDescription=eDescription.getText().toString();
        quantity=eQuantity.getText().toString();

        if(stCode.isEmpty() || stName.isEmpty() || stDescription.isEmpty() || quantity.isEmpty()){
            snack("Please Check missing field", view);
        }else{

            //Creating Fields
            FileOutputStream streamCode=openFileOutput(field_code,MODE_PRIVATE);
            FileOutputStream streamName=openFileOutput(field_name, MODE_PRIVATE);
            FileOutputStream streamDescription=openFileOutput(field_description, MODE_PRIVATE);
            FileOutputStream streamQuantity=openFileOutput(field_quantity, MODE_PRIVATE);

            //Input Process
            streamCode.write(stCode.getBytes());
            streamName.write(stName.getBytes());
            streamDescription.write(stDescription.getBytes());
            streamQuantity.write(quantity.getBytes());

            streamCode.close();
            streamName.close();
            streamDescription.close();
            streamQuantity.close();

            //Notification
            snack("Added",view);

            //Clearing EditText
            eCode.setText("");
            eName.setText("");
            eDescription.setText("");
            eQuantity.setText("");

            }




    }

    public void clickRetrieveBytes(View view) throws IOException {
        stCode="";
        stName="";
        stDescription="";
        quantity="";
        //for Product Code

        try {
            FileInputStream  streamCode = openFileInput(field_code);

            int lengthCode;
            while((lengthCode=streamCode.read())!=-1){
                stCode=stCode + Character.toString((char) lengthCode);
            }
            //for Product Name
            FileInputStream streamName=openFileInput(field_name);
            int lengthName;
            while((lengthName=streamName.read())!= -1){
                stName=stName + Character.toString((char) lengthName);
            }
            //for Product Description
            FileInputStream streamDes=openFileInput(field_description);
            int lengthDes;
            while((lengthDes=streamDes.read()) != -1){
                stDescription=stDescription + Character.toString((char) lengthDes);
            }
            //for Product Quantity
            FileInputStream streamQuantity=openFileInput(field_quantity);
            int lengthQuanity;
            while((lengthQuanity=streamQuantity.read()) != -1){
                quantity=quantity + Character.toString((char) lengthQuanity);
            }

            eCode.setText(stCode);
            eName.setText(stName);
            eDescription.setText(stDescription);
            eQuantity.setText(quantity);

            snack("Successfully Retrieved",view);



        } catch (FileNotFoundException e) {
            snack("No Data Found",view);
        }


    }

    public void snack(String msg, View view){

        Snackbar snackBar = Snackbar.make(view, "" + msg, Snackbar.LENGTH_LONG);
        snackBar.show();
    }
}