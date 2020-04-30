package com.example.myapplication.Model;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class HistoryViewHolder extends RecyclerView.ViewHolder {
    public ImageView HimageView;
    public TextView HTitle,HBrand,HModel,HDescription,HPrice;

    public HistoryViewHolder(@NonNull View itemView) {
        super(itemView);

        HimageView = itemView.findViewById(R.id.History_Image_view);
        HTitle = itemView.findViewById(R.id.history_title_textView);
        HBrand = itemView.findViewById(R.id.history_brand_textView);
        HModel = itemView.findViewById(R.id.history_model_textView);
        HDescription = itemView.findViewById(R.id.history_description_textView);
        HPrice = itemView.findViewById(R.id.history_price_textView);

    }
}
