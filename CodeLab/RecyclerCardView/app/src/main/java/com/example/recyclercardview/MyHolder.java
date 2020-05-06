package com.example.recyclercardview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyHolder extends RecyclerView.ViewHolder {

    ImageView mImage;
    TextView mTitle, mdes;


    public MyHolder(@NonNull View itemView) {
        super(itemView);

        this.mImage = itemView.findViewById(R.id.imageIv);
        this.mTitle = itemView.findViewById(R.id.titleIv);
        this.mdes = itemView.findViewById(R.id.descriptionIv);
    }
}
