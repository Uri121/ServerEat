package com.example.servereat.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.servereat.R;

public class MyViewHolder extends RecyclerView.ViewHolder {

    public LinearLayout layout;
    public TextView phone, address, name;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        phone = (TextView) itemView.findViewById(R.id.phone);
        address = (TextView)itemView.findViewById(R.id.address);
        name = (TextView)itemView.findViewById(R.id.name);
        layout =(LinearLayout) itemView.findViewById(R.id.background);
    }
}
