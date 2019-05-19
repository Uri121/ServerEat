package com.example.servereat.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.servereat.Model.Order;
import com.example.servereat.R;

import java.util.List;

import static android.support.v7.widget.RecyclerView.*;

class  MyOrderViewHolder extends ViewHolder{

   public TextView meatType, breadType, addOnes, price;

    public MyOrderViewHolder(@NonNull View itemView) {
        super(itemView);
        meatType = itemView.findViewById(R.id.order_meat_type);
        breadType = itemView.findViewById(R.id.order_bread_type);
        addOnes = itemView.findViewById(R.id.order_add_ones);
        price = itemView.findViewById(R.id.order_price);
    }
}
public class OrderDetailAdapter extends RecyclerView.Adapter<MyOrderViewHolder> {


    List<Order> orders;

    public OrderDetailAdapter(List<Order> list)
    {
        this.orders = list;
    }

    @NonNull
    @Override
    public MyOrderViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.detail_layout,viewGroup,false);
        return new MyOrderViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull MyOrderViewHolder myViewHolder, int i) {


        Order order = orders.get(i);

        if (orders.get(i).getBreadType() != null)
        {
            myViewHolder.meatType.setText(String.format("Meat: "+order.getMeatType()));
            myViewHolder.breadType.setText(String.format("Bread: "+order.getBreadType()));
            StringBuilder builder = new StringBuilder();
            if (order != null)
            {
                for (String details : order.getAddOnes()) {
                    builder.append(details + "\n");
                }
            }
            myViewHolder.addOnes.setText(String.format("Add Ones: "+builder));
        }else
            {

            myViewHolder.meatType.setText(String.format("Drink: "+order.getMeatType()));
            myViewHolder.breadType.setText("");
            myViewHolder.addOnes.setText("");

        }

        myViewHolder.price.setText((String.format("Price: "+order.getPrice())));
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }
}
