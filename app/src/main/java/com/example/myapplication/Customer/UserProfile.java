package com.example.myapplication.Customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class UserProfile extends AppCompatActivity {

    EditText userFullName,userUserName,userEmail,userPhoneNumber;
    ProgressBar progressBar;
    Button updateBtn;
    FirebaseAuth fAuth;
    DatabaseReference ref;
    TextView userfname,useruname;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_profile);

        userFullName = findViewById(R.id.user_full_name);
        userUserName = findViewById(R.id.user_name);
        userEmail = findViewById(R.id.user_email_address);
        userPhoneNumber = findViewById(R.id.user_mobile_number);
        updateBtn = findViewById(R.id.userUpdate_btn);
        progressBar = findViewById(R.id.user_update_prograssbar);
        userfname = findViewById(R.id.full_name);
        useruname = findViewById(R.id.username_cus);
        ref = FirebaseDatabase.getInstance().getReference().child("Users");
        fAuth = FirebaseAuth.getInstance();



        final String UserKey = getIntent().getStringExtra("UserID");
        ref.child(UserKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){
                    String user_full_name = dataSnapshot.child("FullName").getValue().toString();
                    String user_user_name = dataSnapshot.child("UserName").getValue().toString();
                    String user_email = dataSnapshot.child("Email").getValue().toString();
                    String user_phone_number = dataSnapshot.child("Phone").getValue().toString();

                    userFullName.setText(user_full_name);
                    userfname.setText(user_full_name);
                    useruname.setText(user_user_name);
                    userUserName.setText(user_user_name);
                    userEmail.setText(user_email);
                    userPhoneNumber.setText(user_phone_number);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private Boolean validateUsername(){
        String val = userUserName.getText().toString();

        if(val.isEmpty()) {
            userUserName.setError("Field Cannot be Empty");
            return false;
        }else if(val.length() >= 15) {
            userUserName.setError("User Name is Too Long");
            return false;
        }else{
            userUserName.setError(null);
            return true;
        }
    }
    private Boolean validateName(){
        String val = userFullName.getText().toString();

        if(val.isEmpty()){
            userFullName.setError("Field Cannot be Empty");
            return false;
        }else{
            userFullName.setError(null);
            return true;
        }
    }
    private Boolean validateEmail(){
        String val = userEmail.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if(val.isEmpty()) {
            userEmail.setError("Field Cannot be Empty");
            return false;
        }else if(!val.matches(emailPattern)){
            userEmail.setError("Invalid Email Address");
            return false;
        }else{
            userEmail.setError(null);
            return true;
        }
    }
    private Boolean validateNumber(){
        String val = userPhoneNumber.getText().toString();

        if(val.isEmpty()) {
            userPhoneNumber.setError("Field Cannot be Empty");
            return false;
        }else if(val.length()>10){
            userPhoneNumber.setError("Invalid Mobile Number");
            return false;
        }else{
            userPhoneNumber.setError(null);
            return true;
        }
    }


    public void updateUser (View view){
        if(!validateUsername() | !validateName() | !validateEmail() | !validateNumber()){
            return;
        }else {
            final String fname = userFullName.getText().toString();
            final String username = userUserName.getText().toString();
            final String email = userEmail.getText().toString();
            final String phoneNo = userPhoneNumber.getText().toString();
            final String role = "Customer";

            progressBar.setVisibility(View.VISIBLE);


            ref = FirebaseDatabase.getInstance().getReference().child("Users").child(fAuth.getCurrentUser().getUid());
            HashMap<String,String> hashMap = new HashMap<>();
            hashMap.put("FullName",fname);
            hashMap.put("UserName",username);
            hashMap.put("Email",email);
            hashMap.put("Phone",phoneNo);
            hashMap.put("Role",role);

            ref.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(UserProfile.this,"User Updated Successfully",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), Dashboard.class));
                        progressBar.setVisibility(View.GONE);
                        finish();

                    }else{
                        Toast.makeText(UserProfile.this,"Error !" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                }
            });

        }
    }
}
