package com.example.myapplication.LoginRegister;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Admin.AdminDashboard;
import com.example.myapplication.Customer.Dashboard;
import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    Button Login_btn,call_admin;
    ImageView image;
    TextView logoText, sloganText;
    EditText email,passwrd;
    ProgressBar progressBar;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);


        //Hooks
         final Button callRegister = findViewById(R.id.signup_screen);
         image = findViewById(R.id.logo_image);
         logoText = findViewById(R.id.logo_name);
         sloganText = findViewById(R.id.slogan_name);
         email = findViewById(R.id.email);
         passwrd = findViewById(R.id.password);
         Login_btn = findViewById(R.id.signin_btn);
         progressBar = findViewById(R.id.progressBar);
         fAuth = FirebaseAuth.getInstance();

        if(fAuth.getCurrentUser() != null){
            progressBar.setVisibility(View.VISIBLE);
            FirebaseUser  firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(fAuth.getCurrentUser().getUid());
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String role = dataSnapshot.child("Role").getValue().toString();
                    if (role.equals("Customer")) {
                        Toast.makeText(Login.this, "User Login Successfully", Toast.LENGTH_SHORT).show();
                        Intent dashboardIntent = new Intent(Login.this, Dashboard.class);
                        startActivity(dashboardIntent);
                        finish();
                    } else if (role.equals("Admin")) {
                        Toast.makeText(Login.this, "Admin Login Successfully", Toast.LENGTH_SHORT).show();
                        Intent adminDashboardIntent = new Intent(Login.this, AdminDashboard.class);
                        startActivity(adminDashboardIntent);
                        finish();
                    } else {
                        Toast.makeText(Login.this, "No Redirect ", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }

        callRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                Pair[] pairs = new Pair[7];
                pairs[0] = new <View,String> Pair(image,"logo_image");
                pairs[1] = new <View,String> Pair(logoText,"logo_text");
                pairs[2] = new <View,String> Pair(sloganText,"logo_desc");
                pairs[3] = new <View,String> Pair(email,"username_tran");
                pairs[4] = new <View,String> Pair(passwrd,"password_tran");
                pairs[5] = new <View,String> Pair(Login_btn,"signin_tran");
                pairs[6] = new <View,String> Pair(callRegister,"signup_tran");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this,pairs);
                    startActivity(intent, options.toBundle());
                }


            }
        });

    }

    private Boolean validateEmail(){
        String val = email.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if(val.isEmpty()) {
            email.setError("Field Cannot be Empty");
            return false;
        }else if(!val.matches(emailPattern)){
            email.setError("Invalid Email Address");
            return false;
        }else{
            email.setError(null);
            return true;
        }
    }
    private Boolean validatePassword(){
        String val = passwrd.getText().toString();

        if(val.isEmpty()) {
            passwrd.setError("Field Cannot be Empty");
            return false;
        }else{
            passwrd.setError(null);
            return true;
        }
    }

    public void loginuser(View view){
        if(!validateEmail() | !validatePassword()){
            return;
        }else {
            isUser();
        }
    }


    private void isUser(){
        final String userEnteredEmail = email.getText().toString().trim();
        final String userEnteredpassword = passwrd.getText().toString().trim();

        progressBar.setVisibility(View.VISIBLE);

        //Authenticate the User

        fAuth.signInWithEmailAndPassword(userEnteredEmail,userEnteredpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Login.this,"Login Successfully",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), Starter.class));
                    finish();
                }else{
                    Toast.makeText(Login.this,"Error! " + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

    }
}


