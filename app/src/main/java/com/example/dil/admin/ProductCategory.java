package com.example.dil.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProductCategory extends AppCompatActivity {

    private Button AddAvailableProduct, AddUpcomingProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_category);

        AddAvailableProduct = (Button)findViewById(R.id.available_product);
        AddUpcomingProduct = (Button)findViewById(R.id.upcoming_product);

        AddAvailableProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductCategory.this, AddProduct.class);
                intent.putExtra("category","availableProduct");
                startActivity(intent);
            }
        });

        AddUpcomingProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductCategory.this, AddProduct.class);
                intent.putExtra("category","upcomingProduct");
                startActivity(intent);
            }
        });
    }
}
