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

public class AddCar extends AppCompatActivity {

    private static final int REQUEST_CODE_IMAGE = 101;
    //variables
    ImageView addCar_ImageView;
    TextView textViewProgress;
    ProgressBar progressBar;
    Button addCarSave;
    RadioGroup radioGroup;
    RadioButton radioButton;
    TextInputLayout addCarTopic,addCarBrand,addCarModel,addCarModelYear,addCarBodyType,addCarTransmission,addCarEngCapacity,addCarMilage,addCarDesc,addCarPrice;
    Uri imageUri;
    boolean isImageAdded = false;

    DatabaseReference dataReference;
    StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        //hooks

        addCar_ImageView = findViewById(R.id.image_view_add);
        textViewProgress = findViewById(R.id.textViewProgress);
        progressBar = findViewById(R.id.addCar_progressBar);
        addCarSave = findViewById(R.id.addCar_save_btn);
        addCarTopic = findViewById(R.id.add_car_topic);
        addCarBrand = findViewById(R.id.add_car_Brand);
        addCarModel = findViewById(R.id.add_car_model);
        addCarModelYear = findViewById(R.id.add_car_year);
        addCarBodyType = findViewById(R.id.add_car_bodyType);
        addCarTransmission = findViewById(R.id.add_car_transmission);
        addCarEngCapacity = findViewById(R.id.add_car_enginecapacity);
        addCarMilage = findViewById(R.id.add_car_mileage);
        addCarDesc = findViewById(R.id.add_car_descrip);
        radioGroup = findViewById(R.id.addCarRadioGroup);
        addCarPrice = findViewById(R.id.add_car_price);

        textViewProgress.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);

        dataReference = FirebaseDatabase.getInstance().getReference().child("Add Cars");
        storageReference = FirebaseStorage.getInstance().getReference().child("AddCarImages");



        addCar_ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,REQUEST_CODE_IMAGE);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_IMAGE && data != null){
            imageUri = data.getData();
            isImageAdded = true;
            addCar_ImageView.setImageURI(imageUri);
        }
    }

    public void checkButton(View view){
        int radioID = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioID);
    }

    private boolean validateTopic(){
        String val = addCarTopic.getEditText().getText().toString();

        if(val.isEmpty()) {
            addCarTopic.setError("Field Cannot be Empty");
            return false;
        }else if(val.length() >= 150) {
            addCarTopic.setError("Topic is Too Long");
            return false;
        }else{
            addCarTopic.setError(null);
            addCarTopic.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validateBrand(){
        String val = addCarBrand.getEditText().getText().toString();

        if(val.isEmpty()) {
            addCarBrand.setError("Field Cannot be Empty");
            return false;
        }else{
            addCarBrand.setError(null);
            addCarBrand.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validateModel(){
        String val = addCarModel.getEditText().getText().toString();

        if(val.isEmpty()) {
            addCarModel.setError("Field Cannot be Empty");
            return false;
        }else{
            addCarModel.setError(null);
            addCarModel.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validateModelYear(){
        String val = addCarModelYear.getEditText().getText().toString();

        if(val.isEmpty()) {
            addCarModelYear.setError("Field Cannot be Empty");
            return false;
        }else{
            addCarModelYear.setError(null);
            addCarModelYear.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validateBodyType(){
        String val = addCarBodyType.getEditText().getText().toString();

        if(val.isEmpty()) {
            addCarBodyType.setError("Field Cannot be Empty");
            return false;
        }else{
            addCarBodyType.setError(null);
            addCarBodyType.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validateTransmission(){
        String val = addCarTransmission.getEditText().getText().toString();

        if(val.isEmpty()) {
            addCarTransmission.setError("Field Cannot be Empty");
            return false;
        }else{
            addCarTransmission.setError(null);
            addCarTransmission.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validateEngineCapacity(){
        String val = addCarEngCapacity.getEditText().getText().toString();

        if(val.isEmpty()) {
            addCarEngCapacity.setError("Field Cannot be Empty");
            return false;
        }else{
            addCarEngCapacity.setError(null);
            addCarEngCapacity.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validateMileage(){
        String val = addCarMilage.getEditText().getText().toString();

        if(val.isEmpty()) {
            addCarMilage.setError("Field Cannot be Empty");
            return false;
        }else{
            addCarMilage.setError(null);
            addCarMilage.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validateDescription(){
        String val = addCarDesc.getEditText().getText().toString();

        if(val.isEmpty()) {
            addCarDesc.setError("Field Cannot be Empty");
            return false;
        }else if(val.length() >= 1500) {
            addCarDesc.setError("Description is Too Long");
            return false;
        }else{
            addCarDesc.setError(null);
            addCarDesc.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validatePrice(){
        String val = addCarPrice.getEditText().getText().toString();

        if(val.isEmpty()) {
            addCarPrice.setError("Field Cannot be Empty");
            return false;
        }else{
            addCarPrice.setError(null);
            addCarPrice.setErrorEnabled(false);
            return true;
        }
    }

    public void uploadAddCar(View view){
        if(!validateTopic() | !validateBrand() | !validateModel() | !validateModelYear() | !validateBodyType() | !validateTransmission() | !validateEngineCapacity() | !validateMileage() | !validateDescription() | !validatePrice()){
            return;
        }else{
            addCarDetails();
        }
    }

    private void addCarDetails(){
                int radioID = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioID);

                final String radioBtnValue = radioButton.getText().toString();
                final String add_car_topic = addCarTopic.getEditText().getText().toString();
                final String add_car_brand = addCarBrand.getEditText().getText().toString();
                final String add_car_model = addCarModel.getEditText().getText().toString();
                final String add_car_model_year = addCarModelYear.getEditText().getText().toString();
                final String add_car_body_type = addCarBodyType.getEditText().getText().toString();
                final String add_car_transmission = addCarTransmission.getEditText().getText().toString();
                final String add_car_engine_capacity = addCarEngCapacity.getEditText().getText().toString();
                final String add_car_mileage = addCarMilage.getEditText().getText().toString();
                final String add_car_description = addCarDesc.getEditText().getText().toString();
                final String add_car_price = addCarPrice.getEditText().getText().toString();

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
                                    hashMap.put("CarTopic",add_car_topic);
                                    hashMap.put("CarBrand",add_car_brand);
                                    hashMap.put("CarModel",add_car_model);
                                    hashMap.put("CarModelYear",add_car_model_year);
                                    hashMap.put("CarBodyType",add_car_body_type);
                                    hashMap.put("CarTransmission",add_car_transmission);
                                    hashMap.put("CarEngineCapacity",add_car_engine_capacity);
                                    hashMap.put("CarMileage",add_car_mileage);
                                    hashMap.put("CarDescription",add_car_description);
                                    hashMap.put("CarCondition",radioBtnValue);
                                    hashMap.put("CarPrice",add_car_price);
                                    hashMap.put("ImageURL",uri.toString());

                                    dataReference.child(key).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Toast.makeText(AddCar.this,"Data Successfully Uploaded",Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(AddCar.this,AdminDashboard.class));
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(AddCar.this, "Error", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddCar.this, "Error", Toast.LENGTH_SHORT).show();
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
