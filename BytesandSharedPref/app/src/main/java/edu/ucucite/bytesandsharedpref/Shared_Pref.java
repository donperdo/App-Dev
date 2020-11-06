package edu.ucucite.bytesandsharedpref;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class Shared_Pref extends AppCompatActivity {

    EditText eCode;
    EditText eName;
    EditText eDescription;
    EditText eQuantity;

    //Objects
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    //Properties of Shared Pref
    public static final String Pref_name="Product";
    public static final String field_code="Product Code";
    public static final String field_name="Product Name";
    public static final String field_description="Product Description";
    public static final String field_quantity="Product Quantity";


    String stCode;
    String stName;
    String stDescription;
    int quantity=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared__pref);

        eCode=findViewById(R.id.editCode);
        eName=findViewById(R.id.editName);
        eDescription=findViewById(R.id.editDescription);
        eQuantity=findViewById(R.id.editQuantity);

        sharedPreferences=getSharedPreferences(Pref_name, MODE_PRIVATE);
        editor=sharedPreferences.edit();
    }

    //Buttons
    public void clickRetrieve(View view){
        stCode=sharedPreferences.getString(field_code,"");
        stName=sharedPreferences.getString(field_name,"");
        stDescription=sharedPreferences.getString(field_description,"");
        quantity=sharedPreferences.getInt(field_quantity, -1);



        if(stCode.isEmpty()){
            snack("No data found",view);

        }else{
            eCode.setText(stCode);
            eName.setText(stName);
            eDescription.setText(stDescription);
            eQuantity.setText("" + quantity);
            snack("Successfully Retrieved",view);
        }



    }

    public void clickAdd(View view){
        stCode=eCode.getText().toString();
        stName=eName.getText().toString();
        stDescription=eDescription.getText().toString();
        try{
            quantity=Integer.valueOf(eQuantity.getText().toString());
        }catch (NumberFormatException numberFormatException){

        }

        if(stCode.isEmpty() || stName.isEmpty() || stDescription.isEmpty() || TextUtils.isEmpty(eQuantity.getText().toString())){
            snack("Please Check missing field", view);
        }else{
            editor.putString(field_code,stCode);
            editor.putString(field_name,stName);
            editor.putString(field_description, stDescription);
            editor.putInt(field_quantity, quantity);
            editor.commit();


            eCode.setText("");
            eName.setText("");
            eDescription.setText("");
            eQuantity.setText("");
            snack("Added", view);
        }


    }

    public void clickClear(View view){

        editor.clear();
        editor.commit();

        eCode.setText("");
        eName.setText("");
        eDescription.setText("");
        eQuantity.setText("");

        snack("Shared Pref data Cleared", view);


    }

    public void snack(String msg, View view){

        Snackbar snackBar = Snackbar.make(view, "" + msg, Snackbar.LENGTH_LONG);
        snackBar.show();
    }
}