package edu.ucucite.bytesandsharedpref;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickBytes(View view){
        startActivity(new Intent(this,Bytes.class));
    }
    public void clickShared(View view){
        startActivity(new Intent(this,Shared_Pref.class));
    }
}