package com.example.myapplication.Model;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class PromotionViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;
    public TextView promotionTitile;
    public View v;

    public PromotionViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.promotionImage);
        promotionTitile = itemView.findViewById(R.id.promotionTitle);
        v = itemView;
    }
}
