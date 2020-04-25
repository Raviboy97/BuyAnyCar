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

public class DeletePromotion extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ImageView imageView;
    TextView pTitle,pDiscount,pDescription;
    Button deleteButton;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_promotion);

        drawerLayout = findViewById(R.id.drawer_layout_admin);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        imageView = findViewById(R.id.deletePromotionImage);
        pTitle = findViewById(R.id.textViewDeletePromotionTitle);
        pDiscount = findViewById(R.id.textViewDeletePromotionDiscount);
        pDescription = findViewById(R.id.textViewDeletePromotionDescription);
        deleteButton = findViewById(R.id.promotionDeleteBtn);
        ref = FirebaseDatabase.getInstance().getReference().child("Add Promotions");

        String PromoKey = getIntent().getStringExtra("PromotionKey");
        ref.child(PromoKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){
                    String Promotion_Title = dataSnapshot.child("PromotionTopic").getValue().toString();
                    String Promotion_Discount = dataSnapshot.child("PromotionDiscount").getValue().toString();
                    String Promotion_Description = dataSnapshot.child("PromotionDescription").getValue().toString();
                    String Image_URL = dataSnapshot.child("ImageURL").getValue().toString();

                    Picasso.get().load(Image_URL).into(imageView);
                    pTitle.setText(Promotion_Title);
                    pDiscount.setText("Discount : " + Promotion_Discount);
                    pDescription.setText(Promotion_Description);
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
        navigationView.setCheckedItem(R.id.nav_home);
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
                Intent intent = new Intent(DeletePromotion.this, UserProfile.class);
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
