package com.example.myapplication.Admin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class AddPromotion extends AppCompatActivity {

    private static final int REQUEST_CODE_IMAGE = 101;
    //variables
    ImageView addPromotion_ImageView;
    TextView textViewProgress;
    ProgressBar progressBar;
    Button addPromotionSave;
    TextInputLayout addPromotionTopic,addPromotionDesc,addPromotionPrice;
    Uri imageUri;
    boolean isImageAdded = false;

    DatabaseReference dataReference;
    StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_promotion);

        addPromotion_ImageView = findViewById(R.id.image_view_add_promotion);
        textViewProgress = findViewById(R.id.textViewProgress_Promotion);
        progressBar = findViewById(R.id.addPromotion_progressBar);
        addPromotionSave = findViewById(R.id.addPromotion_save_btn);
        addPromotionTopic = findViewById(R.id.add_promotion_topic);
        addPromotionDesc = findViewById(R.id.add_promotion_descrip);
        addPromotionPrice = findViewById(R.id.add_promotion_price);

        dataReference = FirebaseDatabase.getInstance().getReference().child("Add Promotions");
        storageReference = FirebaseStorage.getInstance().getReference().child("AddPromotionImages");

        addPromotion_ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,REQUEST_CODE_IMAGE);
            }
        });

    }

    private boolean validateTopic(){
        String val = addPromotionTopic.getEditText().getText().toString();

        if(val.isEmpty()) {
            addPromotionTopic.setError("Field Cannot be Empty");
            return false;
        }else if(val.length() >= 150) {
            addPromotionTopic.setError("Topic is Too Long");
            return false;
        }else{
            addPromotionTopic.setError(null);
            addPromotionTopic.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validateDescription(){
        String val = addPromotionDesc.getEditText().getText().toString();

        if(val.isEmpty()) {
            addPromotionDesc.setError("Field Cannot be Empty");
            return false;
        }else if(val.length() >= 1500) {
            addPromotionDesc.setError("Description is Too Long");
            return false;
        }else{
            addPromotionDesc.setError(null);
            addPromotionDesc.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validatePrice(){
        String val = addPromotionPrice.getEditText().getText().toString();

        if(val.isEmpty()) {
            addPromotionPrice.setError("Field Cannot be Empty");
            return false;
        }else{
            addPromotionPrice.setError(null);
            addPromotionPrice.setErrorEnabled(false);
            return true;
        }
    }

    public void uploadPromotion(View view){
        if(!validateTopic() | !validateDescription() | !validatePrice()){
            return;
        }else{
            addPromotionDetails();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_IMAGE && data != null){
            imageUri = data.getData();
            isImageAdded = true;
            addPromotion_ImageView.setImageURI(imageUri);
        }
    }

    private void addPromotionDetails(){

        final String add_promotion_topic = addPromotionTopic.getEditText().getText().toString();
        final String add_promotion_description = addPromotionDesc.getEditText().getText().toString();
        final String add_promotion_price = addPromotionPrice.getEditText().getText().toString();


        if(isImageAdded!=false){

            textViewProgress.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.VISIBLE);


            final String key = dataReference.push().getKey();
            storageReference.child(key+ "jpg").putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    storageReference.child(key+ "jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("PromotionTopic",add_promotion_topic);
                            hashMap.put("PromotionDescription",add_promotion_description);
                            hashMap.put("PromotionDiscount",add_promotion_price);
                            hashMap.put("ImageURL",uri.toString());

                            dataReference.child(key).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(AddPromotion.this,"Data Successfully Uploaded",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(AddPromotion.this,AdminDashboard.class));
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(AddPromotion.this, "Error", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(AddPromotion.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    double progress = (taskSnapshot.getBytesTransferred()*100)/taskSnapshot.getTotalByteCount();
                    progressBar.setProgress((int) progress);
                    textViewProgress.setText(progress+"%");
                }
            });
        }
    }
}
