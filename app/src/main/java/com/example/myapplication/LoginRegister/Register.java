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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Register extends AppCompatActivity {

    Button calllogin,register;
    ImageView image;
    TextView logoText,logantext;
    EditText uname,pass,fullname,mail,mnumber;
    ProgressBar progressBar;
    FirebaseAuth fAuth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);

        calllogin = findViewById(R.id.calllogin_btn);
        register = findViewById(R.id.register_btn);
        image = findViewById(R.id.image_logo);
        logoText = findViewById(R.id.logo_wel);
        logantext = findViewById(R.id.slogan_signup);
        uname = findViewById(R.id.uname);
        pass = findViewById(R.id.pass);
        fullname = findViewById(R.id.fname);
        mail = findViewById(R.id.email);
        mnumber = findViewById(R.id.mobile);
        progressBar = findViewById(R.id.progressBar2);
        fAuth = FirebaseAuth.getInstance();

        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), AdminDashboard.class));
            finish();
        }

        calllogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, Login.class);
                Pair[] pairs = new Pair[7];
                pairs[0] = new <View,String> Pair(image,"logo_image");
                pairs[1] = new <View,String> Pair(logoText,"logo_text");
                pairs[2] = new <View,String> Pair(logantext,"logo_desc");
                pairs[3] = new <View,String> Pair(uname,"username_tran");
                pairs[4] = new <View,String> Pair(pass,"password_tran");
                pairs[5] = new <View,String> Pair(register,"signin_tran");
                pairs[6] = new <View,String> Pair(calllogin,"signup_tran");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Register.this,pairs);
                    startActivity(intent,options.toBundle());
                }

            }
        });
    }

    //validations
    private Boolean validateUsername(){
        String val = uname.getText().toString();

        if(val.isEmpty()) {
            uname.setError("Field Cannot be Empty");
            return false;
        }else if(val.length() >= 15) {
            uname.setError("User Name is Too Long");
            return false;
        }else{
            uname.setError(null);
            return true;
        }
    }
    private Boolean validateName(){
        String val = fullname.getText().toString();

        if(val.isEmpty()){
            fullname.setError("Field Cannot be Empty");
            return false;
        }else{
            fullname.setError(null);
            return true;
        }
    }
    private Boolean validateEmail(){
        String val = mail.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if(val.isEmpty()) {
            mail.setError("Field Cannot be Empty");
            return false;
        }else if(!val.matches(emailPattern)){
            mail.setError("Invalid Email Address");
            return false;
        }else{
            mail.setError(null);
            return true;
        }
    }
    private Boolean validateNumber(){
        String val = mnumber.getText().toString();

        if(val.isEmpty()) {
            mnumber.setError("Field Cannot be Empty");
            return false;
        }else if(val.length()>10){
            mnumber.setError("Invalid Mobile Number");
            return false;
        }else{
            mnumber.setError(null);
            return true;
        }
    }
    private Boolean validatePassword(){
        String val = pass.getText().toString();
        String passwordmatch = "^"+
                "(?=.*[0-9])"+ //at least one digit
                "(?=.*[a-z])"+ //at least one lower case letter
                "(?=.*[A-Z])"+ //at lease one upper case letter
                "(?=.*[@#$%^&+=])"+ //at lease one special character
                "(?=\\S+$)"+ //no white spaces
                ".{4,}"+ //at least four characters
                "$";

        if(val.isEmpty()) {
            pass.setError("Field Cannot be Empty");
            return false;
        }else if(!val.matches(passwordmatch)){
            pass.setError("Password Is Too Weak");
            return false;
        }else{
            pass.setError(null);
            return true;
        }
    }

    //DB Connection
    public void registerUser (View view){

        if(!validateUsername() |!validateName() |!validateEmail() |!validateNumber() |!validatePassword()){
            return;
        }else{

            final String fname = fullname.getText().toString();
            final String username = uname.getText().toString();
            final String email = mail.getText().toString();
            final String phoneNo = mnumber.getText().toString();
            final String password = pass.getText().toString();
            final String role = "Customer";

            progressBar.setVisibility(View.VISIBLE);

            //register user in firebase
            fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        FirebaseUser firebaseUser =fAuth.getCurrentUser();
                        assert firebaseUser != null;
                        String userID =firebaseUser.getUid();

                        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(userID);
                        HashMap<String,String> hashMap = new HashMap<>();
                        hashMap.put("FullName",fname);
                        hashMap.put("UserName",username);
                        hashMap.put("Email",email);
                        hashMap.put("Phone",phoneNo);
                        hashMap.put("Role",role);

                        databaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(Register.this,"User Created Successfully",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), Dashboard.class));
                                    finish();
                                }else{
                                    Toast.makeText(Register.this,"Error !" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                    progressBar.setVisibility(View.GONE);
                                }
                            }
                        });


                    }else{
                        Toast.makeText(Register.this,"Error !" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                }
            });


        }



    }


}

    