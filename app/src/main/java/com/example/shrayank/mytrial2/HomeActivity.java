package com.example.shrayank.mytrial2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.Timer;
import java.util.TimerTask;

import static android.webkit.WebView.*;

public class HomeActivity extends AppCompatActivity {
    WebView webView;
    ConstraintLayout constraintLayout;
    Timer myTimer = new Timer();
    int flag1= 1;
    int flag2= 1;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        constraintLayout = (ConstraintLayout) findViewById(R.id.MyView);

        webView = (WebView) findViewById(R.id.webView);
        WebSettings wSettings = webView.getSettings();
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView.getSettings().setAppCacheEnabled(true);
        wSettings.setDomStorageEnabled(true);
        wSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        wSettings.setUseWideViewPort(true);
        wSettings.setEnableSmoothTransition(true);

        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl("https://www.itsmykart.com/");
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                myAsyncTask mtask = new myAsyncTask();
                mtask.execute();
            }
        }, 0, 5000);
    }

    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
    private class myAsyncTask extends AsyncTask<Void ,Void ,Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {
            ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nt = cm.getActiveNetworkInfo();
            if(nt != null && nt.isConnectedOrConnecting())
                return true;
            else
                return false;
        }

        @Override
        protected void onPostExecute(Boolean a) {
            if(a) {
                if(flag1==1) {
                    Snackbar.make(constraintLayout, "connection is avaliable", Snackbar.LENGTH_SHORT).show();
                    flag2=1;
                    flag1=2;
                }
            }
            else
            if(flag2==1) {
                Snackbar.make(constraintLayout, "connection is not avaliable", Snackbar.LENGTH_SHORT).show();
                flag1=1;
                flag2=2;
            }

        }
    }
    
}
