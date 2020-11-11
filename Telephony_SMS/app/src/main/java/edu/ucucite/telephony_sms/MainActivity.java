package edu.ucucite.telephony_sms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.EmptyStackException;

public class MainActivity extends AppCompatActivity {

    EditText eNumber;
    EditText eMessage;

    //String
    String sent="SMS_SENT";
    String delivered="SMS_DELIVERED";

    //CLASS
    PendingIntent sentPi;
    PendingIntent deliveredPi;

    BroadcastReceiver smsSentReceiver;
    BroadcastReceiver smsDeliveredReceiver;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eNumber=findViewById(R.id.editNumber);
        eMessage=findViewById(R.id.editMessage);


        //PendingIntent
        sentPi= PendingIntent.getBroadcast(this, 0, new Intent(sent), 0);
        deliveredPi=PendingIntent.getBroadcast(this, 0, new Intent(delivered), 0);
    }


    @Override
    public void onResume() {
        super.onResume();

        smsSentReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        display("Message Sent");
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        display("Message Failed");
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        display("No Signal");
                        break;
                    case SmsManager.RESULT_RIL_RADIO_NOT_AVAILABLE:
                        display("Airplane Mode");
                        break;
                    //for lower version of Android OS
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        display("Airplane Mode");
                        break;

                }



            }
        };// smsSentReceiver

        smsDeliveredReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                       display("OK");
                        break;
                    case Activity.RESULT_CANCELED:
                        display("Canceled");
                        break;
                }

            }
        };//smsDeliveredReceiver

        registerReceiver(smsSentReceiver, new IntentFilter(sent));
        registerReceiver(smsDeliveredReceiver, new IntentFilter(delivered));

    }//onResume

    @Override
    public void onPause(){
        super.onPause();
        unregisterReceiver(smsSentReceiver);
        unregisterReceiver(smsDeliveredReceiver);
    }//onPause
    //Button Call
    public void clickCall(View view) {
        int permissionCheck =
                ContextCompat.checkSelfPermission(this,Manifest.permission.CALL_PHONE);
        if (permissionCheck ==PackageManager.PERMISSION_GRANTED) {

            String phoneNum =eNumber.getText().toString().trim();
            if(phoneNum.isEmpty()){
                eNumber.setError("Please provide phone number");
            }else{
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + phoneNum));
                startActivity(callIntent);
            }

        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE},
                    0);

        }
    }
    //Button SMS
    public void clickSMS(View view){
        String stNumber= eNumber.getText().toString();
        String stMessage= eMessage.getText().toString();


        int permissionCheck =ContextCompat.checkSelfPermission(this,
                        Manifest.permission.SEND_SMS);

        if (permissionCheck==PackageManager.PERMISSION_GRANTED){
            if(stNumber.isEmpty() || stMessage.isEmpty()){
                display("Please Check missing Field");
            }else{
                SmsManager smsManager= SmsManager.getDefault();
                smsManager.sendTextMessage(stNumber, null, stMessage,sentPi, deliveredPi);
                display("Sending..");
            }

        }else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 0);
        }


    }//SMS Method
        //tOAST
        public void display(String msg){
            Toast.makeText(this, "" + msg, Toast.LENGTH_SHORT).show();
        }



}//Main