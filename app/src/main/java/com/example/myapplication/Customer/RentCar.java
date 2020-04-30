package com.example.myapplication.Customer;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
import com.example.myapplication.LoginRegister.Login;
import com.example.myapplication.Model.RentCarModel;
import com.example.myapplication.Model.RentCarViewHolder;
import com.example.myapplication.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RentCar extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    EditText InputRentCar;
    RecyclerView RentCarRecyclerView;
    FirebaseRecyclerOptions<RentCarModel  > options;
    FirebaseRecyclerAdapter<RentCarModel, RentCarViewHolder> adapter;
    DatabaseReference DataRef;
    FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_car);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        InputRentCar = findViewById(R.id.inputSearch_RentCar);
        RentCarRecyclerView = findViewById(R.id.recyclerViewRentCar);
        RentCarRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        RentCarRecyclerView.setHasFixedSize(true);
        DataRef = FirebaseDatabase.getInstance().getReference().child("Add Rent Cars");
        mAuth = FirebaseAuth.getInstance();

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


        LoadRentCarData("");
        InputRentCar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString() != null){
                    LoadRentCarData(s.toString());
                }else{
                    LoadRentCarData("");
                }
            }
        });
    }

    private void LoadRentCarData(String data){

        Query query = DataRef.orderByChild("RentCarTopic").startAt(data).endAt(data + "\uf8ff");
        options = new FirebaseRecyclerOptions.Builder<RentCarModel>().setQuery(query,RentCarModel.class).build();
        adapter = new FirebaseRecyclerAdapter<RentCarModel, RentCarViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull RentCarViewHolder holder, final int position, @NonNull RentCarModel model) {

                holder.rCarTitle.setText(model.getRentCarTopic());
                holder.rCarModel.setText("Model : " + model.getRentCarModel());
                holder.rCarModelYear.setText("Model Year : " + model.getRentCarYear());
                holder.rCarTransmission.setText("Transmission Type : " + model.getRentCarTransmission());
                holder.rCarPrice.setText("Rs." + model.getRentCarPrice() + " Per KM");
                Picasso.get().load(model.getImageURL()).into(holder.imageView);
                holder.v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(RentCar.this,ViewRentCar.class);
                        intent.putExtra("CarKey",getRef(position).getKey());
                        startActivity(intent);
                    }
                });

            }

            @NonNull
            @Override
            public RentCarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_view_rent_car,parent,false);
                return new RentCarViewHolder(v);
            }
        };
        adapter.startListening();
        RentCarRecyclerView.setAdapter(adapter);
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
                startActivity(new Intent(RentCar.this,Dashboard.class));
                break;

            case R.id.nav_profile:
                String uid = mAuth.getCurrentUser().getUid();
                Intent intent = new Intent(RentCar.this, UserProfile.class);
                intent.putExtra("UserID",uid);
                startActivity(intent);
                break;

            case R.id.nav_logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), Login.class));
                Toast.makeText(this,"Successfully Logged Out!",Toast.LENGTH_SHORT).show();
                finish();
                break;

            case R.id.nav_share:
                Toast.makeText(this,"Share",Toast.LENGTH_SHORT).show();
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


}
