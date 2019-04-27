package com.example.dil.admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dil.admin.Model.Product;
import com.example.dil.admin.ViewHolder.ProductViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AvailableProductList extends AppCompatActivity {

    private DatabaseReference availableProductsref;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_product_list);
        setTitle("Available Products");

        recyclerView = findViewById(R.id.recycler_menu);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        availableProductsref = FirebaseDatabase.getInstance().getReference().child("Available Products");
    }

    @Override
    protected void onStart() {
        super.onStart();



            FirebaseRecyclerOptions<Product> options =
                    new FirebaseRecyclerOptions.Builder<Product>()
                            .setQuery(availableProductsref, Product.class)
                            .build();

            FirebaseRecyclerAdapter<Product, ProductViewHolder> adapter =
                    new FirebaseRecyclerAdapter<Product, ProductViewHolder>(options) {
                        @Override
                        protected void onBindViewHolder(@NonNull ProductViewHolder holder, int position, @NonNull final Product model) {

                            holder.txtProductName.setText(model.getProductName());
                            holder.txtProductPrice.setText(model.getPrice() + " Tk.");


                            holder.itemView.setOnClickListener(new View.OnClickListener() {


                                @Override
                                public void onClick(View v) {
                                    if (AppStatus.getInstance(AvailableProductList.this).isOnline()) {

                                    Intent intent = new Intent(AvailableProductList.this, UpdateAvailableProduct.class);
                                    intent.putExtra("pid", model.getPid());
                                    intent.putExtra("ProductName", model.getProductName());
                                    intent.putExtra("Price", model.getPrice());
                                    intent.putExtra("Color", model.getColor());
                                    intent.putExtra("Camera", model.getCamera());
                                    intent.putExtra("Battery", model.getBattery());
                                    intent.putExtra("Display", model.getDisplay());
                                    intent.putExtra("Fingerprint", model.getFingerprint());
                                    intent.putExtra("ROM", model.getROM());
                                    intent.putExtra("Sim", model.getSim());
                                    intent.putExtra("Network", model.getNetwork());
                                    intent.putExtra("Processor", model.getProcessor());
                                    intent.putExtra("RAM", model.getRAM());
                                    intent.putExtra("YoutubeVideoLink", model.getYoutubeVideoLink());
                                    intent.putExtra("Others", model.getOthers());
                                    startActivity(intent);
                                }

                                    else {
                                        Toast.makeText(AvailableProductList.this, "No Internet Connection!", Toast.LENGTH_SHORT).show();
                                    }
                            }
                            });

                        }


                        @NonNull
                        @Override
                        public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_item_layout, viewGroup, false);
                            ProductViewHolder holder = new ProductViewHolder(view);
                            return holder;
                        }
                    };

            recyclerView.setAdapter(adapter);
            adapter.startListening();
    }
}

