package edu.ucucite.networkconnection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    TextView txtConnected;
    TextView txtConnecting;
    TextView txtNoConnection;

    //Object
    ConnectivityManager connectivityManager;
    BroadcastReceiver broadcastReceiver;

    //Variables
    boolean isConnected;
    boolean isConnectionSignal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtConnected=findViewById(R.id.textConnectedSwipe);
        txtConnecting=findViewById(R.id.textConnectingSwipe);
        txtNoConnection=findViewById(R.id.textNoConnectionSwipe);

        broadcastReceiver= new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                conStatus();
                if(isConnected){
                    if(isConnectionSignal){
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                txtConnected.setVisibility(View.VISIBLE);
                                txtNoConnection.setVisibility(View.GONE);
                                txtConnecting.setVisibility(View.GONE);

                            }
                        },2000);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                txtConnected.setVisibility(View.GONE);
                            }
                        },10000);
                    }else{
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                txtConnected.setVisibility(View.GONE);
                                txtNoConnection.setVisibility(View.GONE);
                                txtConnecting.setVisibility(View.VISIBLE);

                            }
                        },2000);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                txtConnecting.setVisibility(View.GONE);
                            }
                        },10000);
                    }
                }else{
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            txtConnected.setVisibility(View.GONE);
                            txtNoConnection.setVisibility(View.VISIBLE);
                            txtConnecting.setVisibility(View.GONE);

                        }
                    },2000);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            txtNoConnection.setVisibility(View.GONE);
                        }
                    },10000);
                }

            }
        };

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
                case TelephonyManager.NETWORK_TYPE_LTE:
                    return true;
                case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                default:
                    return false;
            }
        }else{
            return false;
        }
    }
}