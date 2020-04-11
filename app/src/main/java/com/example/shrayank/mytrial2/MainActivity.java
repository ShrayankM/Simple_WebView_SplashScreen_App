package com.example.shrayank.mytrial2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT=3000;
    private boolean InternetCheck = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        PostDelayedMethod();


    }
    public void PostDelayedMethod(){
        new Handler().postDelayed(new Runnable(){

            public void run(){
                    boolean InternetResult = checkConnection();
                    if(InternetResult) {
                        Intent Home_intent = new Intent(MainActivity.this, HomeActivity.class);
                        startActivity(Home_intent);
                        finish();
                    }
                    else
                        DialogAppear();
            }
        },SPLASH_TIME_OUT);
    }
    public void DialogAppear(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Network Error");
        builder.setMessage("No Internet Connection");

        builder.setNegativeButton("Exit",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog,int which){
                finish();
            }
        });

        builder.setPositiveButton("Retry",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog,int which){
                PostDelayedMethod();
            }
        });
        AlertDialog alertT = builder.create();
        alertT.show();

        Button b1 = alertT.getButton(DialogInterface.BUTTON_NEGATIVE);
        b1.setTextColor(Color.BLUE);
        b1.setBackgroundColor(Color.WHITE);

        Button b2 = alertT.getButton(DialogInterface.BUTTON_POSITIVE);
        b2.setTextColor(Color.BLUE);
        b2.setBackgroundColor(Color.WHITE);
    }
    public boolean isOnline(){
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nt = cm.getActiveNetworkInfo();
        if(nt != null && nt.isConnectedOrConnecting())
            return true;
        else
            return false;
    }
    public boolean checkConnection(){
        if(isOnline()) {
            InternetCheck = true;
            return InternetCheck;
        }
        else{
            InternetCheck = false;
            return InternetCheck;
        }
    }
}
