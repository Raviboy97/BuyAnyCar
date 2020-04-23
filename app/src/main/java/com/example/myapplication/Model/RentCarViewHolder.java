package com.example.myapplication.Model;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class RentCarViewHolder extends RecyclerView.ViewHolder {

    public ImageView imageView;
    public TextView rCarTitle,rCarModel,rCarModelYear,rCarTransmission,rCarPrice;
    public View v;



    public RentCarViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.rentCarImage);
        rCarTitle = itemView.findViewById(R.id.rentCartitle);
        rCarModel = itemView.findViewById(R.id.rentCarModelDes);
        rCarModelYear = itemView.findViewById(R.id.rentCarModelYearDes);
        rCarTransmission = itemView.findViewById(R.id.rentCarTransmissionDes);
        rCarPrice = itemView.findViewById(R.id.rentCarPriceDesc);
        v = itemView;

    }
}
