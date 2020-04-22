package com.example.myapplication.LoginRegister;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Admin.AdminDashboard;
import com.example.myapplication.Customer.Dashboard;
import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Starter extends AppCompatActivity {
    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starter);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getCurrentUser().getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String role = dataSnapshot.child("Role").getValue().toString();

                if (role.equals("Customer")) {
                    Toast.makeText(Starter.this, "User Login Successfully", Toast.LENGTH_SHORT).show();
                    Intent dashboardIntent = new Intent(Starter.this, Dashboard.class);
                    startActivity(dashboardIntent);
                    finish();
                } else if (role.equals("Admin")) {
                    Toast.makeText(Starter.this, "Admin Login Successfully", Toast.LENGTH_SHORT).show();
                    Intent adminDashboardIntent = new Intent(Starter.this, AdminDashboard.class);
                    startActivity(adminDashboardIntent);
                    finish();
                } else {
                    Toast.makeText(Starter.this, "No Redirect ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
