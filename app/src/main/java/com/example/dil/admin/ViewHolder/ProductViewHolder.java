package com.example.dil.admin.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dil.admin.Interface.ItemClickListener;
import com.example.dil.admin.R;

public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtProductName, txtProductPrice;
    public ImageView imageView;
    public ItemClickListener listener;


    public ProductViewHolder(@NonNull View itemView) {
        super(itemView);

        txtProductName = (TextView) itemView.findViewById(R.id.product_name);
        txtProductPrice = (TextView) itemView.findViewById(R.id.product_price);
        imageView = (ImageView) itemView.findViewById(R.id.product_image);

    }

    public void setItemClickListener(ItemClickListener listener){
        this.listener = listener;
    }


    @Override
    public void onClick(View v) {
        listener.onClick(v, getAdapterPosition(), false);

    }
}
