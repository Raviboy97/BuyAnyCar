package com.example.myapplication.Admin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class AddAccessories extends AppCompatActivity {

    private static final int REQUEST_CODE_IMAGE = 101;
    //variables
    ImageView addAccessories_ImageView;
    TextView textViewProgress_Accessories;
    ProgressBar progressBar_Accessories;
    Button addAccessoriesSave;
    RadioGroup radioGroup;
    RadioButton radioButton;
    TextInputLayout addAccessoriesTopic,addAccessoriesBrand,addAccessoriesModel,addAccessoriesDesc,addAccessoriesPrice;
    Uri imageUri;
    boolean isImageAdded = false;

    DatabaseReference dataReference;
    StorageReference storageReference;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_IMAGE && data != null){
            imageUri = data.getData();
            isImageAdded = true;
            addAccessories_ImageView.setImageURI(imageUri);
        }
    }

    public void checkButtonCondition(View view){
        int radioID = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioID);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_accessories);

        addAccessories_ImageView = findViewById(R.id.image_view_add_accessories);
        textViewProgress_Accessories = findViewById(R.id.textViewProgress_accessories);
        progressBar_Accessories = findViewById(R.id.addAccessories_progressBar);
        addAccessoriesSave = findViewById(R.id.addAccessories_save_btn);
        radioGroup = findViewById(R.id.addAccessoriesRadioGroup);
        addAccessoriesTopic = findViewById(R.id.add_accessories_topic);
        addAccessoriesBrand = findViewById(R.id.add_accessories_Brand);
        addAccessoriesModel = findViewById(R.id.add_accessories_model);
        addAccessoriesDesc = findViewById(R.id.add_accessories_descrip);
        addAccessoriesPrice = findViewById(R.id.add_accessories_price);

        dataReference = FirebaseDatabase.getInstance().getReference().child("Add Accessories");
        storageReference = FirebaseStorage.getInstance().getReference().child("AddAccessoriesImages");

        addAccessories_ImageView.setOnClickListener(new View.OnClickListener() {
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
        String val = addAccessoriesTopic.getEditText().getText().toString();

        if(val.isEmpty()) {
            addAccessoriesTopic.setError("Field Cannot be Empty");
            return false;
        }else if(val.length() >= 150) {
            addAccessoriesTopic.setError("Topic is Too Long");
            return false;
        }else{
            addAccessoriesTopic.setError(null);
            addAccessoriesTopic.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validateBrand(){
        String val = addAccessoriesBrand.getEditText().getText().toString();

        if(val.isEmpty()) {
            addAccessoriesBrand.setError("Field Cannot be Empty");
            return false;
        }else{
            addAccessoriesBrand.setError(null);
            addAccessoriesBrand.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validateModel(){
        String val = addAccessoriesModel.getEditText().getText().toString();

        if(val.isEmpty()) {
            addAccessoriesModel.setError("Field Cannot be Empty");
            return false;
        }else{
            addAccessoriesModel.setError(null);
            addAccessoriesModel.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validateDescription(){
        String val = addAccessoriesDesc.getEditText().getText().toString();

        if(val.isEmpty()) {
            addAccessoriesDesc.setError("Field Cannot be Empty");
            return false;
        }else if(val.length() >= 1500) {
            addAccessoriesDesc.setError("Description is Too Long");
            return false;
        }else{
            addAccessoriesDesc.setError(null);
            addAccessoriesDesc.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validatePrice(){
        String val = addAccessoriesPrice.getEditText().getText().toString();

        if(val.isEmpty()) {
            addAccessoriesPrice.setError("Field Cannot be Empty");
            return false;
        }else{
            addAccessoriesPrice.setError(null);
            addAccessoriesPrice.setErrorEnabled(false);
            return true;
        }
    }


    public void uploadAddAccessories(View view){
        if(!validateTopic() | !validateBrand() | !validateModel() | !validateDescription() | !validatePrice()){
            return;
        }else{
            addAccessoriesDetails();
        }
    }

    private void addAccessoriesDetails(){
        int radioID = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioID);

        final String radioBtnValue = radioButton.getText().toString();
        final String add_accessories_topic = addAccessoriesTopic.getEditText().getText().toString();
        final String add_accessories_brand = addAccessoriesBrand.getEditText().getText().toString();
        final String add_accessories_model = addAccessoriesModel.getEditText().getText().toString();
        final String add_accessories_description = addAccessoriesDesc.getEditText().getText().toString();
        final String add_accessories_price = addAccessoriesPrice.getEditText().getText().toString();

        if(isImageAdded!=false){

            textViewProgress_Accessories.setVisibility(View.VISIBLE);
            progressBar_Accessories.setVisibility(View.VISIBLE);


            final String key = dataReference.push().getKey();
            storageReference.child(key+ "jpg").putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    storageReference.child(key+ "jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("AccessoriesTopic",add_accessories_topic);
                            hashMap.put("AccessoriesBrand",add_accessories_brand);
                            hashMap.put("AccessoriesModel",add_accessories_model);
                            hashMap.put("AccessoriesDescription",add_accessories_description);
                            hashMap.put("AccessoriesCondition",radioBtnValue);
                            hashMap.put("AccessoriesPrice",add_accessories_price);
                            hashMap.put("ImageURL",uri.toString());

                            dataReference.child(key).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(AddAccessories.this,"Data Successfully Uploaded",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(AddAccessories.this,AdminDashboard.class));
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(AddAccessories.this, "Error", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(AddAccessories.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    double progress = (taskSnapshot.getBytesTransferred()*100)/taskSnapshot.getTotalByteCount();
                    progressBar_Accessories.setProgress((int) progress);
                    textViewProgress_Accessories.setText(progress+"%");
                }
            });
        }

    }
}
