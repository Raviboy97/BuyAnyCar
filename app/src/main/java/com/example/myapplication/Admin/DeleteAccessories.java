package com.example.myapplication.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class DeleteAccessories extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ImageView imageView;
    Button deleteButton;
    TextView aTitle,aBrand,aModel,aCondition,aPrice,aDescription;
    DatabaseReference ref,Dataref;
    StorageReference Storageref;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_accessories);

        drawerLayout = findViewById(R.id.drawer_layout_admin);
        navigationView = findViewById(R.id.nav_view_admin);
        toolbar = findViewById(R.id.toolbar);
        imageView = findViewById(R.id.accessoriesDeleteImageView);
        aTitle = findViewById(R.id.accessories_delete_title_label);
        aBrand = findViewById(R.id.accessories_delete_Brand_label);
        aModel = findViewById(R.id.accessories_delete_model_label);
        aCondition = findViewById(R.id.accessories_delete_condition_label);
        aPrice = findViewById(R.id.accessories_delete_price_label);
        aDescription = findViewById(R.id.accessories_delete_textView_Desc);
        deleteButton = findViewById(R.id.accessoriesDeleteBtn);
        progressBar = findViewById(R.id.prograss_delete_accessories);
        ref = FirebaseDatabase.getInstance().getReference().child("Add Accessories");

        String AccessKey = getIntent().getStringExtra("AccessoriesKey");
        Dataref = FirebaseDatabase.getInstance().getReference().child("Add Accessories").child(AccessKey);
        Storageref = FirebaseStorage.getInstance().getReference().child("AddAccessoriesImages").child(AccessKey+ "jpg");
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

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                Dataref.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Storageref.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                startActivity(new Intent(DeleteAccessories.this,AddAccessoriesView.class));
                                Toast.makeText(DeleteAccessories.this,"Item Deleted Successfully!",Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(DeleteAccessories.this,"Error!",Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(DeleteAccessories.this,"Error!",Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                });
            }
        });
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
                Intent intent = new Intent(DeleteAccessories.this, UserProfile.class);
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
