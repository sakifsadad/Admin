package com.example.dil.admin;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button AddProductButton, AvailableProductListButton, UpcomingProductListButton;
    private int STORAGE_PERMISSION_CODE = 1;
    private boolean InternetChceck = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AddProductButton = (Button) findViewById(R.id.add_Product);
        AvailableProductListButton = (Button) findViewById(R.id.available_productList);
        UpcomingProductListButton = (Button) findViewById(R.id.upcoming_ProductList);


        AddProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AppStatus.getInstance(MainActivity.this).isOnline()) {


                    if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
//                    Toast.makeText(MainActivity.this, "You have already the permission", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(MainActivity.this, ProductCategory.class);
                        startActivity(intent);
                    } else {
//                    requestStoragePermission();
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);

                    }
                }
                else {
                    Toast.makeText(MainActivity.this, "No Internet Connection!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        AvailableProductListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AppStatus.getInstance(MainActivity.this).isOnline()) {


                    Intent intent = new Intent(MainActivity.this, AvailableProductList.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(MainActivity.this, "No Internet Connection!", Toast.LENGTH_SHORT).show();
                }
            }

        });

        UpcomingProductListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (AppStatus.getInstance(MainActivity.this).isOnline()) {

                Intent intent = new Intent(MainActivity.this, UpcomingProductList.class);
                startActivity(intent);
            }
                else {
                    Toast.makeText(MainActivity.this, "No Internet Connection!", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Toast.makeText(MainActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }





}

