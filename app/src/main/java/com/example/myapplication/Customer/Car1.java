package com.example.myapplication.Customer;

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

import com.example.myapplication.LoginRegister.Login;
import com.example.myapplication.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Car1 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ImageView imageView;
    TextView carTitle,carPrice,fuelCity,fuelHighway,carBrand,carModel,carBodyType,carCondition,EngineCapacity,carMileage,modelYear,Transmission,Description;
    Button buyButton;
    FirebaseAuth mAuth;

    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car1);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        imageView = findViewById(R.id.singleCarViewImage);
        carTitle = findViewById(R.id.singleCarTitle);
        carPrice = findViewById(R.id.singleCarPrice);
        fuelCity = findViewById(R.id.cityFuelView);
        fuelHighway = findViewById(R.id.highwayFuelView);
        carBrand = findViewById(R.id.textViewCarBrand);
        carModel = findViewById(R.id.textViewCarModel);
        carBodyType = findViewById(R.id.textViewCarBodyType);
        carCondition = findViewById(R.id.textViewCarCondition);
        EngineCapacity = findViewById(R.id.textViewEngineCapacity);
        carMileage = findViewById(R.id.textViewMileage);
        modelYear = findViewById(R.id.textViewModelYear);
        Transmission = findViewById(R.id.textViewTransmission);
        Description = findViewById(R.id.textViewDescription);
        ref = FirebaseDatabase.getInstance().getReference().child("Add Cars");
        mAuth = FirebaseAuth.getInstance();

        String CarKey = getIntent().getStringExtra("CarKey");
        ref.child(CarKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){

                    String buyCarBodyType = dataSnapshot.child("CarBodyType").getValue().toString();
                    String buyCarBrand = dataSnapshot.child("CarBrand").getValue().toString();
                    String buyCarCondition = dataSnapshot.child("CarCondition").getValue().toString();
                    String buyCarDescription = dataSnapshot.child("CarDescription").getValue().toString();
                    String buyCarEngineCapacity = dataSnapshot.child("CarEngineCapacity").getValue().toString();
                    String buyCarMileage = dataSnapshot.child("CarMileage").getValue().toString();
                    String buyCarModel = dataSnapshot.child("CarModel").getValue().toString();
                    String buyCarModelYear = dataSnapshot.child("CarModelYear").getValue().toString();
                    String buyCarPrice = dataSnapshot.child("CarPrice").getValue().toString();
                    String buyCarTitle = dataSnapshot.child("CarTopic").getValue().toString();
                    String buyCarTransmission = dataSnapshot.child("CarTransmission").getValue().toString();
                    String buyCarFuelCity = dataSnapshot.child("FuelCity").getValue().toString();
                    String buyCarFuelHighway = dataSnapshot.child("FuelHighway").getValue().toString();
                    String buyCarImage = dataSnapshot.child("ImageURL").getValue().toString();




                    Picasso.get().load(buyCarImage).into(imageView);
                    carTitle.setText(buyCarTitle);
                    carPrice.setText("Rs." + buyCarPrice);
                    fuelCity.setText(buyCarFuelCity);
                    fuelHighway.setText(buyCarFuelHighway);
                    carBrand.setText("Brand Name : " + buyCarBrand);
                    carModel.setText("Model : " + buyCarModel);
                    carBodyType.setText("Body Type : " + buyCarBodyType);
                    carCondition.setText("Condition : " + buyCarCondition);
                    EngineCapacity.setText("Engine Capacity : " + buyCarEngineCapacity);
                    carMileage.setText("Mileage : " + buyCarMileage);
                    modelYear.setText("Model Year : " + buyCarModelYear);
                    Transmission.setText("Transmission Type : " + buyCarTransmission);
                    Description.setText(buyCarDescription);


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
                startActivity(new Intent(Car1.this,Dashboard.class));
                break;

            case R.id.nav_profile:
                String uid = mAuth.getCurrentUser().getUid();
                Intent intent = new Intent(Car1.this, UserProfile.class);
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
