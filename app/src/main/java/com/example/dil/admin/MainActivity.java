package com.example.dil.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button AddProductButton, AvailableProductListButton, UpcomingProductListButton;

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

                Intent intent = new Intent(MainActivity.this, ProductCategory.class);
                startActivity(intent);
            }
        });

        AvailableProductListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AvailableProductList.class);
                startActivity(intent);
            }
        });

        UpcomingProductListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UpcomingProductList.class);
                startActivity(intent);
            }
        });

    }
}
