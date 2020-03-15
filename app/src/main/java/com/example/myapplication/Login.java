package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {

    Button callRegister,Login_btn;
    ImageView image;
    TextView logoText, sloganText;
    TextInputLayout userName,passwrd;

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
         userName = findViewById(R.id.username);
         passwrd = findViewById(R.id.password);
         Login_btn = findViewById(R.id.signin_btn);

        callRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,Register.class);
                Pair[] pairs = new Pair[7];
                pairs[0] = new <View,String> Pair(image,"logo_image");
                pairs[1] = new <View,String> Pair(logoText,"logo_text");
                pairs[2] = new <View,String> Pair(sloganText,"logo_desc");
                pairs[3] = new <View,String> Pair(userName,"username_tran");
                pairs[4] = new <View,String> Pair(passwrd,"password_tran");
                pairs[5] = new <View,String> Pair(Login_btn,"signin_tran");
                pairs[6] = new <View,String> Pair(callRegister,"signup_tran");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this,pairs);
                    startActivity(intent, options.toBundle());
                }


            }
        });

        Login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,Dashboard.class);
                startActivity(intent);
            }
        });
    }
}
