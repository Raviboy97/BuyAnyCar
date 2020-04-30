package com.example.myapplication.Admin;

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

public class AdminUserProfile extends AppCompatActivity {
    TextView adminfullname,adminusername;
    EditText adminFName,adminUName,adminEmail,adminPhone;
    Button editAdmin;
    ProgressBar progressBar;
    FirebaseAuth fAuth;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_profile);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        adminfullname = findViewById(R.id.admin_f_name);
        adminusername = findViewById(R.id.admin_u_name);
        adminFName = findViewById(R.id.admin_full_name);
        adminUName = findViewById(R.id.admin_name);
        adminEmail = findViewById(R.id.admin_email_address);
        adminPhone = findViewById(R.id.admin_mobile_number);
        editAdmin = findViewById(R.id.adminUpdate_btn);
        progressBar = findViewById(R.id.admin_update_prograssbar);
        ref = FirebaseDatabase.getInstance().getReference().child("Users");
        fAuth = FirebaseAuth.getInstance();

        final String UserKey = getIntent().getStringExtra("UserID");
        ref.child(UserKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){
                    String admin_full_name = dataSnapshot.child("FullName").getValue().toString();
                    String admin_user_name = dataSnapshot.child("UserName").getValue().toString();
                    String admin_email = dataSnapshot.child("Email").getValue().toString();
                    String admin_phone_number = dataSnapshot.child("Phone").getValue().toString();

                    adminFName.setText(admin_full_name);
                    adminfullname.setText(admin_full_name);
                    adminUName.setText(admin_user_name);
                    adminusername.setText(admin_user_name);
                    adminEmail.setText(admin_email);
                    adminPhone.setText(admin_phone_number);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private Boolean validateUsername(){
        String val = adminUName.getText().toString();

        if(val.isEmpty()) {
            adminUName.setError("Field Cannot be Empty");
            return false;
        }else if(val.length() >= 15) {
            adminUName.setError("User Name is Too Long");
            return false;
        }else{
            adminUName.setError(null);
            return true;
        }
    }
    private Boolean validateName(){
        String val = adminFName.getText().toString();

        if(val.isEmpty()){
            adminFName.setError("Field Cannot be Empty");
            return false;
        }else{
            adminFName.setError(null);
            return true;
        }
    }
    private Boolean validateEmail(){
        String val = adminEmail.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if(val.isEmpty()) {
            adminEmail.setError("Field Cannot be Empty");
            return false;
        }else if(!val.matches(emailPattern)){
            adminEmail.setError("Invalid Email Address");
            return false;
        }else{
            adminEmail.setError(null);
            return true;
        }
    }
    private Boolean validateNumber(){
        String val = adminPhone.getText().toString();

        if(val.isEmpty()) {
            adminPhone.setError("Field Cannot be Empty");
            return false;
        }else if(val.length()>10){
            adminPhone.setError("Invalid Mobile Number");
            return false;
        }else{
            adminPhone.setError(null);
            return true;
        }
    }

    public void updateAdmin(View view){
        if(!validateUsername() | !validateName() | !validateEmail() | !validateNumber()){
            return;
        }else {
            final String fname = adminFName.getText().toString();
            final String username = adminUName.getText().toString();
            final String email = adminEmail.getText().toString();
            final String phoneNo = adminPhone.getText().toString();
            final String role = "Admin";

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
                        Toast.makeText(AdminUserProfile.this,"User Updated Successfully",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), AdminDashboard.class));
                        progressBar.setVisibility(View.GONE);
                        finish();

                    }else{
                        Toast.makeText(AdminUserProfile.this,"Error !" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                }
            });

        }
    }
}
