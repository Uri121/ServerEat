package com.example.servereat.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import com.example.servereat.Adapter.OrderDetailAdapter;
import com.example.servereat.Model.LoginUser;
import com.example.servereat.R;

public class PopUp extends AppCompatActivity {

    RecyclerView foods;
    RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width =dm.widthPixels;
        int height = dm.heightPixels;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up);
        foods= findViewById(R.id.detail_recycler);
        foods.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        foods.setLayoutManager(layoutManager);

        OrderDetailAdapter adapter = new OrderDetailAdapter(LoginUser.mCurrentOrder.getFood());
        adapter.notifyDataSetChanged();
        foods.setAdapter(adapter);
        getWindow().setLayout((int)(width*.8),(int)(height*.6));


    }
}
