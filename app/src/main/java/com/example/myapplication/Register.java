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

public class Register extends AppCompatActivity {

    Button calllogin,register;
    ImageView image;
    TextView logoText,logantext;
    TextInputLayout username,password;

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
        username = findViewById(R.id.uname);
        password = findViewById(R.id.pass);

        calllogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this,Login.class);
                Pair[] pairs = new Pair[7];
                pairs[0] = new <View,String> Pair(image,"logo_image");
                pairs[1] = new <View,String> Pair(logoText,"logo_text");
                pairs[2] = new <View,String> Pair(logantext,"logo_desc");
                pairs[3] = new <View,String> Pair(username,"username_tran");
                pairs[4] = new <View,String> Pair(password,"password_tran");
                pairs[5] = new <View,String> Pair(register,"signin_tran");
                pairs[6] = new <View,String> Pair(calllogin,"signup_tran");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Register.this,pairs);
                    startActivity(intent,options.toBundle());
                }

            }
        });
    }
}
