package com.example.myapplication.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterAdmin extends AppCompatActivity {
    EditText adminfullname,adminusername,adminemail,adminmobile,adminpassword;
    Button register_admin_btn;
    ProgressBar progressBar;
    FirebaseAuth fAuth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_admin);

        adminfullname = findViewById(R.id.admin_fname);
        adminusername = findViewById(R.id.admin_uname);
        adminemail = findViewById(R.id.admin_email);
        adminmobile = findViewById(R.id.admin_mobile);
        adminpassword = findViewById(R.id.admin_pass);
        register_admin_btn = findViewById(R.id.admin_register_btn);
        progressBar = findViewById(R.id.admin_register_progressBar);
        fAuth = FirebaseAuth.getInstance();


    }

    private Boolean validateUsername(){
        String val = adminusername.getText().toString();

        if(val.isEmpty()) {
            adminusername.setError("Field Cannot be Empty");
            return false;
        }else if(val.length() >= 15) {
            adminusername.setError("User Name is Too Long");
            return false;
        }else{
            adminusername.setError(null);
            return true;
        }
    }
    private Boolean validateName(){
        String val = adminfullname.getText().toString();

        if(val.isEmpty()){
            adminfullname.setError("Field Cannot be Empty");
            return false;
        }else{
            adminfullname.setError(null);
            return true;
        }
    }
    private Boolean validateEmail(){
        String val = adminemail.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if(val.isEmpty()) {
            adminemail.setError("Field Cannot be Empty");
            return false;
        }else if(!val.matches(emailPattern)){
            adminemail.setError("Invalid Email Address");
            return false;
        }else{
            adminemail.setError(null);
            return true;
        }
    }
    private Boolean validateNumber(){
        String val = adminmobile.getText().toString();

        if(val.isEmpty()) {
            adminmobile.setError("Field Cannot be Empty");
            return false;
        }else if(val.length()>10){
            adminmobile.setError("Invalid Mobile Number");
            return false;
        }else{
            adminmobile.setError(null);
            return true;
        }
    }
    private Boolean validatePassword(){
        String val = adminpassword.getText().toString();
        String passwordmatch = "^"+
                "(?=.*[0-9])"+ //at least one digit
                "(?=.*[a-z])"+ //at least one lower case letter
                "(?=.*[A-Z])"+ //at lease one upper case letter
                "(?=.*[@#$%^&+=])"+ //at lease one special character
                "(?=\\S+$)"+ //no white spaces
                ".{4,}"+ //at least four characters
                "$";

        if(val.isEmpty()) {
            adminpassword.setError("Field Cannot be Empty");
            return false;
        }else if(!val.matches(passwordmatch)){
            adminpassword.setError("Password Is Too Weak");
            return false;
        }else{
            adminpassword.setError(null);
            return true;
        }
    }

    public void registerUser(View view){
        if(!validateUsername() |!validateName() |!validateEmail() |!validateNumber() |!validatePassword()){
            return;
        }else{

            final String admin_fname = adminfullname.getText().toString();
            final String admin_username = adminusername.getText().toString();
            final String admin_email = adminemail.getText().toString();
            final String admin_phoneNo = adminmobile.getText().toString();
            final String admin_password = adminpassword.getText().toString();
            final String admin_role = "Admin";

            progressBar.setVisibility(View.VISIBLE);

            fAuth.createUserWithEmailAndPassword(admin_email,admin_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        FirebaseUser firebaseUser =fAuth.getCurrentUser();
                        assert firebaseUser != null;
                        String userID =firebaseUser.getUid();

                        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(userID);
                        HashMap<String,String> hashMap = new HashMap<>();
                        hashMap.put("FullName",admin_fname);
                        hashMap.put("UserName",admin_username);
                        hashMap.put("Email",admin_email);
                        hashMap.put("Phone",admin_phoneNo);
                        hashMap.put("Role",admin_role);

                        databaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(RegisterAdmin.this,"User Created Successfully",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), AdminDashboard.class));
                                    finish();
                                }else{
                                    Toast.makeText(RegisterAdmin.this,"Error !" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                    progressBar.setVisibility(View.GONE);
                                }
                            }
                        });

                    }else {
                        Toast.makeText(RegisterAdmin.this,"Error !" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                }
            });

        }
    }
}
