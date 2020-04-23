package com.example.myapplication.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.myapplication.Customer.UserProfile;
import com.example.myapplication.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class DeleteRentCar extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ImageView imageView;
    TextView carTitle,carPrice,fuelCity,fuelHighway,carBrand,carModel,carBodyType,EngineCapacity,carMileage,modelYear,Transmission,Description;
    Button rentButton_dlt;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_rent_car);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        imageView = findViewById(R.id.singleDeleteRentCarViewImage);
        carTitle = findViewById(R.id.deleteSingleRentCarTitle);
        carPrice = findViewById(R.id.deleteSingleRentCarPrice);
        fuelCity = findViewById(R.id.cityFuelViewDeleteRentCar);
        fuelHighway = findViewById(R.id.highwayFuelViewDeleteRentCar);
        carBrand = findViewById(R.id.textViewDeleteRentCarBrand);
        carModel = findViewById(R.id.textViewDeleteRentCarModel);
        carBodyType = findViewById(R.id.textViewDeleteRentCarBodyType);
        EngineCapacity = findViewById(R.id.textViewDeleteRentCarEngineCapacity);
        carMileage = findViewById(R.id.textViewDeleteRentCarMileage);
        modelYear = findViewById(R.id.textViewDeleteRentCarModelYear);
        Transmission = findViewById(R.id.textViewDeleteRentCarTransmission);
        Description = findViewById(R.id.textViewDeleteRentCarDescription);
        rentButton_dlt = findViewById(R.id.deleteRentCarButton);
        ref = FirebaseDatabase.getInstance().getReference().child("Add Rent Cars");

        String CarKey = getIntent().getStringExtra("CarKey");
        ref.child(CarKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String rentCarFuelCity = dataSnapshot.child("FuelCity").getValue().toString();
                    String rentCarFuelHighway = dataSnapshot.child("FuelHighway").getValue().toString();
                    String rentCarImage = dataSnapshot.child("ImageURL").getValue().toString();
                    String rentCarBodyType = dataSnapshot.child("RentCarBodyType").getValue().toString();
                    String rentCarBrand = dataSnapshot.child("RentCarBrand").getValue().toString();
                    String rentCarDescription = dataSnapshot.child("RentCarDescription").getValue().toString();
                    String rentCarEngineCapacity = dataSnapshot.child("RentCarEngineCapacity").getValue().toString();
                    String rentCarMileage = dataSnapshot.child("RentCarMileage").getValue().toString();
                    String rentCarModel = dataSnapshot.child("RentCarModel").getValue().toString();
                    String rentCarPrice = dataSnapshot.child("RentCarPrice").getValue().toString();
                    String rentCarTitle = dataSnapshot.child("RentCarTopic").getValue().toString();
                    String rentCarTransmission = dataSnapshot.child("RentCarTransmission").getValue().toString();
                    String rentCarModelYear = dataSnapshot.child("RentCarYear").getValue().toString();

                    Picasso.get().load(rentCarImage).into(imageView);
                    carTitle.setText(rentCarTitle);
                    carPrice.setText("Rs." + rentCarPrice + "Per KM");
                    fuelCity.setText(rentCarFuelCity);
                    fuelHighway.setText(rentCarFuelHighway);
                    carBrand.setText("Brand Name : " + rentCarBrand);
                    carModel.setText("Model : " + rentCarModel);
                    carBodyType.setText("Body Type : " + rentCarBodyType);
                    EngineCapacity.setText("Engine Capacity : " + rentCarEngineCapacity);
                    carMileage.setText("Mileage : " + rentCarMileage);
                    modelYear.setText("Model Year : " + rentCarModelYear);
                    Transmission.setText("Transmission Type : " + rentCarTransmission);
                    Description.setText(rentCarDescription);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //toolbar

        setSupportActionBar(toolbar);


        //navigation drawer menu

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                break;

            case R.id.nav_profile:
                Intent intent = new Intent(DeleteRentCar.this, UserProfile.class);
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
