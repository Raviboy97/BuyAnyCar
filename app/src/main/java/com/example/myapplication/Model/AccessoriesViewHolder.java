package com.example.myapplication.Model;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class AccessoriesViewHolder extends RecyclerView.ViewHolder {

    public ImageView imageView;
    public TextView aTitle,aBrand,aModel,aCondition,aPrice;
    public View v;

    public AccessoriesViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.accessoriesImage);
        aTitle = itemView.findViewById(R.id.accessoriesTitle);
        aBrand = itemView.findViewById(R.id.accessoriesBrandDesc);
        aModel = itemView.findViewById(R.id.accessoriesModelDes);
        aCondition = itemView.findViewById(R.id.accessoriesConditionDes);
        aPrice = itemView.findViewById(R.id.accessoriesPriceDesc);
        v = itemView;
    }
}
