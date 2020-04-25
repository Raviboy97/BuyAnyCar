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

import com.example.myapplication.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Accessories1 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ImageView imageView;
    Button buyButton;
    TextView aTitle,aBrand,aModel,aCondition,aPrice,aDescription;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accessories1);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        imageView = findViewById(R.id.accessoriesImageView);
        aTitle = findViewById(R.id.accessories_title_label);
        aBrand = findViewById(R.id.accessories_Brand_label);
        aModel = findViewById(R.id.accessories_model_label);
        aCondition = findViewById(R.id.accessories_condition_label);
        aPrice = findViewById(R.id.accessories_price_label);
        aDescription = findViewById(R.id.accessories_textView_Desc);
        buyButton = findViewById(R.id.accessoriesBuy);
        ref = FirebaseDatabase.getInstance().getReference().child("Add Accessories");

        String AccessKey = getIntent().getStringExtra("AccessoriesKey");
        ref.child(AccessKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){

                    String Accessories_Title = dataSnapshot.child("AccessoriesTopic").getValue().toString();
                    String Accessories_Brand = dataSnapshot.child("AccessoriesBrand").getValue().toString();
                    String Accessories_Model = dataSnapshot.child("AccessoriesModel").getValue().toString();
                    String Accessories_Condition = dataSnapshot.child("AccessoriesCondition").getValue().toString();
                    String Accessories_Price = dataSnapshot.child("AccessoriesPrice").getValue().toString();
                    String Accessories_Description = dataSnapshot.child("AccessoriesDescription").getValue().toString();
                    String Accessories_ImageUrl = dataSnapshot.child("ImageURL").getValue().toString();


                    Picasso.get().load(Accessories_ImageUrl).into(imageView);
                    aTitle.setText(Accessories_Title);
                    aBrand.setText("Brand Name : " + Accessories_Brand);
                    aModel.setText("Model : " + Accessories_Model);
                    aCondition.setText("Condition : " + Accessories_Condition);
                    aPrice.setText("Rs. : " + Accessories_Price);
                    aDescription.setText(Accessories_Description);
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
                Intent intent = new Intent(Accessories1.this, UserProfile.class);
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
