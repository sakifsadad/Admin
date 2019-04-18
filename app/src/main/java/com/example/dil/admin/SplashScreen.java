package com.example.dil.admin;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 5000;
    private boolean InternetChceck = true;
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        spinner = (ProgressBar) findViewById(R.id.progressBar);
        spinner.setVisibility(View.VISIBLE);
        PostDelayedMethod();
    }

    private void PostDelayedMethod() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean InternetResult = checkConection();
                if (InternetResult){
                    Intent intent =  new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    spinner.setVisibility(View.VISIBLE);
                    spinner.setVisibility(View.GONE);

                    DialogAppear();
                }
            }
        }, SPLASH_TIME_OUT);
    }

    public void DialogAppear(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(
                SplashScreen.this);

        builder.setTitle("No Internet Connectivity");
        builder.setMessage("No Internet Connection. Please Check Your Internet Connection.");
        builder.setCancelable(false);


        builder.setNegativeButton("Exit",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        builder.setPositiveButton("Retry",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PostDelayedMethod();
                        spinner.setVisibility(View.VISIBLE);
                    }
                });
        builder.show();
    }
    protected boolean isOnline(){

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnectedOrConnecting()){
            return true;
        }
        else {
            return false;
        }
    }

    private boolean checkConection(){
        if (isOnline()){
            return InternetChceck=true;

        }

        else {
            InternetChceck = false;
            return InternetChceck;
        }
    }

}