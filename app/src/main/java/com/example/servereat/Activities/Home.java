package com.example.servereat.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.servereat.Map.Map;
import com.example.servereat.Model.LoginUser;
import com.example.servereat.Model.Requset;
import com.example.servereat.Adapter.MyViewHolder;
import com.example.servereat.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Home extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DatabaseReference reference;
    private FirebaseRecyclerAdapter<Requset, MyViewHolder> adapter;
    private FirebaseRecyclerOptions<Requset> options;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = (RecyclerView)findViewById(R.id.recycler);
        reference = FirebaseDatabase.getInstance().getReference().child("Requests");
        reference.keepSynced(true);
        recyclerView.setHasFixedSize(true);

        options = new FirebaseRecyclerOptions.Builder<Requset>().setQuery(reference,Requset.class).build();

        adapter = new FirebaseRecyclerAdapter<Requset, MyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final MyViewHolder holder, int position, @NonNull final Requset model) {
                holder.phone.setText(model.getPhone());
                holder.name.setText(model.getName());
                holder.address.setText(model.getAddress());
                LoginUser.mCurrentOrder = model;

                holder.layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Home.this, Map.class);
                        startActivity(intent);
                    }
                });

                holder.layout.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        Intent intent = new Intent(Home.this, PopUp.class);
//                        Bundle bundle = new Bundle();
//                        bundle.putSerializable("list", (Serializable) list);
//                        intent.putExtras(bundle);
                        LoginUser.mCurrentOrder = model;
                        startActivity(intent);
                        return true;
                    }
                });

            }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

                View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_layout,viewGroup,false);
                return new MyViewHolder(v);
            }
        };

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.startListening();
        recyclerView.setAdapter(adapter);

    }

//    private void showPopup(View v) {
//        dialog.setContentView(R.layout.popup);
//        TextView txtId, txtMeat, txtBread,txtAddones, txtPrice;
//        txtId = dialog.findViewById(R.id.order_id);
//        txtMeat = dialog.findViewById(R.id.order_meat_type);
//        txtBread = dialog.findViewById(R.id.order_bread_type);
//        txtAddones = dialog.findViewById(R.id.order_add_ones);
//        txtPrice = dialog.findViewById(R.id.order_price);
//        for (Order order:list)
//        {
//            txtId.setText(order.getId());
//            txtMeat.setText(order.getMeatType());
//            txtBread.setText(order.getBreadType());
//            txtPrice.setText(order.getPrice());
//        }
//        dialog.show();
//    }

    @Override
    protected void onStart() {
        super.onStart();
        if (adapter != null)
        {
            adapter.startListening();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adapter != null)
        {
            adapter.startListening();
        }
    }

    @Override
    protected void onStop() {
        if (adapter != null)
        {
            adapter.stopListening();
        }
        super.onStop();
    }
}
