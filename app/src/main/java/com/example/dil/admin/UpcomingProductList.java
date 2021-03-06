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
import com.squareup.picasso.Picasso;

public class UpcomingProductList extends AppCompatActivity {

    private DatabaseReference upcomingProductsref;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_product_list);
        setTitle("Upcoming Products");


        recyclerView = findViewById(R.id.recycler_menu);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        upcomingProductsref = FirebaseDatabase.getInstance().getReference().child("Upcoming Products");
    }

    @Override
    protected void onStart() {
        super.onStart();


            FirebaseRecyclerOptions<Product> options =
                    new FirebaseRecyclerOptions.Builder<Product>()
                            .setQuery(upcomingProductsref, Product.class)
                            .build();

            FirebaseRecyclerAdapter<Product, ProductViewHolder> adapter =
                    new FirebaseRecyclerAdapter<Product, ProductViewHolder>(options) {
                        @Override
                        protected void onBindViewHolder(@NonNull ProductViewHolder holder, int position, @NonNull final Product model) {

                            holder.txtProductName.setText(model.getProductName());
                            holder.txtProductPrice.setText(model.getPrice() + " Tk.");
                            Picasso.get().load(model.getImageUris().getImages().get(0)).into(holder.imageView);

                            holder.itemView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (AppStatus.getInstance(UpcomingProductList.this).isOnline()) {
                                        Intent intent = new Intent(UpcomingProductList.this, UpdateUpcomingProduct.class);
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
                                        Toast.makeText(UpcomingProductList.this, "No Internet Connection!", Toast.LENGTH_SHORT).show();
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