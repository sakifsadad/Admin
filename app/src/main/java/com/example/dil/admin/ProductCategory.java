package com.example.dil.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ProductCategory extends AppCompatActivity {

    private Button AddAvailableProduct, AddUpcomingProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_category);
        setTitle("Add Product");

        AddAvailableProduct = (Button)findViewById(R.id.available_product);
        AddUpcomingProduct = (Button)findViewById(R.id.upcoming_product);

        AddAvailableProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (AppStatus.getInstance(ProductCategory.this).isOnline()) {


                    Intent intent = new Intent(ProductCategory.this, com.example.dil.admin.AddAvailableProduct.class);
                    intent.putExtra("category", "Available Product");
                    startActivity(intent);
                }

                else {
                    Toast.makeText(ProductCategory.this, "No Internet Connection!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        AddUpcomingProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (AppStatus.getInstance(ProductCategory.this).isOnline()) {


                    Intent intent = new Intent(ProductCategory.this, AddUpcomingProduct.class);
                    intent.putExtra("category", "Upcoming Product");
                    startActivity(intent);
                }
                else {
                    Toast.makeText(ProductCategory.this, "No Internet Connection!", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}
