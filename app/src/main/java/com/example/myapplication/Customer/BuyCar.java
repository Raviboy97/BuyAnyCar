package com.example.myapplication.Customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.myapplication.Model.BuyCarModel;
import com.example.myapplication.Model.MyViewHolder;
import com.example.myapplication.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class BuyCar extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    SearchView InputBuyCar;
    RecyclerView BuyCarRecyclerView;
    FirebaseRecyclerOptions<BuyCarModel> options;
    FirebaseRecyclerAdapter<BuyCarModel, MyViewHolder> adapter;
    DatabaseReference DataRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_car);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        InputBuyCar = findViewById(R.id.inputSearch_BuyCar);
        BuyCarRecyclerView = findViewById(R.id.recyclerViewBuyCar);
        BuyCarRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        BuyCarRecyclerView.setHasFixedSize(true);
        DataRef = FirebaseDatabase.getInstance().getReference().child("Add Cars");


        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        ImageSlider imageSlider = findViewById(R.id.slider);

        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.benzcar1,"Buy Brand New Cars"));
        slideModels.add(new SlideModel(R.drawable.benzcar2,"Buy Used Cars"));
        slideModels.add(new SlideModel(R.drawable.benzcar3,"Various Collection"));

        imageSlider.setImageList(slideModels,true);


        LoadData();

    }

    private void LoadData() {
        options = new FirebaseRecyclerOptions.Builder<BuyCarModel>().setQuery(DataRef,BuyCarModel.class).build();
        adapter = new FirebaseRecyclerAdapter<BuyCarModel, MyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull BuyCarModel model) {
                holder.bCarTitle.setText(model.getCarTopic());
                holder.bCarModel.setText("Car Model : " + model.getCarModel());
                holder.bCarModelYear.setText("Model Year : " + model.getCarModelYear());
                holder.bCarCondition.setText("Car Condition : " +model.getCarCondition());
                holder.bCarTransmission.setText("Transmission Type : " + model.getCarTransmission());
                holder.bCarPrice.setText("Price : " + model.getCarPrice());
                Picasso.get().load(model.getImageURL()).into(holder.imageView);


            }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_view_buy_car,parent,false);
                return new MyViewHolder(v);
            }
        };
        adapter.startListening();
        BuyCarRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }


    }


    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                break;

            case R.id.nav_profile:
                Intent intent = new Intent(BuyCar.this, UserProfile.class);
                startActivity(intent);
                break;

            case R.id.nav_share:
                Toast.makeText(this,"Share",Toast.LENGTH_SHORT).show();
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}


