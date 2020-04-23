package com.example.myapplication.Model;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class MyViewHolder extends RecyclerView.ViewHolder {

    public ImageView imageView;
    public TextView bCarTitle,bCarModel,bCarModelYear,bCarCondition,bCarTransmission,bCarPrice;
    public View v;


    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.buyCarImage);
        bCarTitle = itemView.findViewById(R.id.buyCartitle);
        bCarModel = itemView.findViewById(R.id.buyCarModelDes);
        bCarModelYear = itemView.findViewById(R.id.buyCarModelYearDes);
        bCarCondition = itemView.findViewById(R.id.buyCarConditionDes);
        bCarTransmission = itemView.findViewById(R.id.buyCarTransmissionDes);
        bCarPrice = itemView.findViewById(R.id.buyCarPriceDesc);
        v = itemView;


    }
}
