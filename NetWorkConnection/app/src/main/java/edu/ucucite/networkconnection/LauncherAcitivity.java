package edu.ucucite.networkconnection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LauncherAcitivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher_acitivity);
    }

    public void clickAuto(View view){
        startActivity(new Intent(this, MainActivity.class));
    }
    public void clickSwipe(View view){
        startActivity(new Intent(this, Swipe.class));
    }
}