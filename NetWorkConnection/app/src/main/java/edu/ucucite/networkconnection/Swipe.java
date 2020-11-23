package edu.ucucite.networkconnection;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Swipe extends AppCompatActivity {



    private SwipeRefreshLayout swipeRefreshLayout;
    TextView txt;


    //Object
    ConnectivityManager connectivityManager;
    BroadcastReceiver broadcastReceiver;

    //Variables
    boolean isConnected;
    boolean isConnectionSignal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe);

        swipeRefreshLayout= findViewById(R.id.swipe);
        txt=findViewById(R.id.textView);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                broadCast();
                swipeRefreshLayout.setRefreshing(false);
            }
        });


    }
    public void broadCast(){
                conStatus();
                if(isConnected){
                    if(isConnectionSignal){
                        swipeStatus("Connected");
                    }else{
                        swipeStatus("Connecting...");
                    }
                }else{
                    swipeStatus("No Connection");
                }
    }

    public void swipeStatus(String msg){

        AlertDialog.Builder alertDialog= new AlertDialog.Builder(this);
        alertDialog.setMessage("" + msg);
        alertDialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.show();
    }

    //Methods
    @Override
    public void onResume(){
        super.onResume();
        registerReceiver(broadcastReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    public void onPause(){
        super.onPause();
        unregisterReceiver(broadcastReceiver);
    }

    public void conStatus(){
        connectivityManager= (ConnectivityManager)
                getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();

        isConnected=networkInfo != null && networkInfo.isConnectedOrConnecting();//Boolean
        if(isConnected){
            isConnectionSignal=conSignal(networkInfo.getType(),networkInfo.getSubtype());
            isConnected=true;
        }else{
            isConnected=false;
        }



    }


    public static boolean conSignal(int type , int subType){
        if(type==ConnectivityManager.TYPE_WIFI){
            return true;
        }else if(type==ConnectivityManager.TYPE_MOBILE){
            switch(subType){
                case TelephonyManager.NETWORK_TYPE_1xRTT:
                    return false; // ~ 50-100 kbps
                case TelephonyManager.NETWORK_TYPE_CDMA:
                    return false; // ~ 14-64 kbps
                case TelephonyManager.NETWORK_TYPE_EDGE:
                    return false; // ~ 50-100 kbps
                case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    return true; // ~ 400-1000 kbps
                case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    return true; // ~ 600-1400 kbps
                case TelephonyManager.NETWORK_TYPE_GPRS:
                    return false; // ~ 100 kbps
                case TelephonyManager.NETWORK_TYPE_HSDPA:
                    return true; // ~ 2-14 Mbps
                case TelephonyManager.NETWORK_TYPE_HSPA:
                    return true; // ~ 700-1700 kbps
                case TelephonyManager.NETWORK_TYPE_HSUPA:
                    return true; // ~ 1-23 Mbps
                case TelephonyManager.NETWORK_TYPE_UMTS:
                    return true; // ~ 400-7000 kbps
                case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                default:
                    return false;
            }
        }else{
            return false;
        }
    }
}