package com.example.myapplication.Admin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class AddRentCar extends AppCompatActivity {

    private static final int REQUEST_CODE_IMAGE = 101;
    ImageView addRentCar_ImageView;
    TextView prograssText;
    ProgressBar progressBar_add_rent_car;
    Button add_rent_car;
    EditText addRentCarTopic, addRentCarBrand, addRentCarModel, addRentCarModelYear, addRentCarBodyType, addRentCarTransmission, addRentCarEngCapacity, addRentCarMilage, addRentCarDesc, addRentCarPrice,addRentCarFuelCity,addRentCarFuelHighway;
    Uri imageUri;
    boolean isImageAdded = false;

    DatabaseReference dataReference;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rent_car);

        addRentCar_ImageView = findViewById(R.id.image_view_add_rent_car);
        prograssText = findViewById(R.id.textViewProgress_add_rent_car);
        progressBar_add_rent_car = findViewById(R.id.addCar_progressBar);
        add_rent_car = findViewById(R.id.add_Rent_Car_save_btn);
        addRentCarTopic = findViewById(R.id.add_rent_car_topic);
        addRentCarBrand = findViewById(R.id.add_rent_car_Brand);
        addRentCarModel = findViewById(R.id.add_rent_car_model);
        addRentCarModelYear = findViewById(R.id.add_rent_car_year);
        addRentCarBodyType = findViewById(R.id.add_rent_car_bodyType);
        addRentCarTransmission = findViewById(R.id.add_rent_car_transmission);
        addRentCarEngCapacity = findViewById(R.id.add_rent_car_enginecapacity);
        addRentCarMilage = findViewById(R.id.add_rent_car_mileage);
        addRentCarDesc = findViewById(R.id.add_rent_car_descrip);
        addRentCarPrice = findViewById(R.id.add_rent_car_rent_price);
        addRentCarFuelCity = findViewById(R.id.add_rent_car_fuel_City);
        addRentCarFuelHighway = findViewById(R.id.add_rent_car_fuel_Highway);

        dataReference = FirebaseDatabase.getInstance().getReference().child("Add Rent Cars");
        storageReference = FirebaseStorage.getInstance().getReference().child("AddRentCarImages");

        addRentCar_ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_CODE_IMAGE);
            }
        });

    }


    private boolean validateTopic(){
        String val = addRentCarTopic.getText().toString();

        if(val.isEmpty()) {
            addRentCarTopic.setError("Field Cannot be Empty");
            return false;
        }else if(val.length() >= 150) {
            addRentCarTopic.setError("Topic is Too Long");
            return false;
        }else{
            addRentCarTopic.setError(null);
            return true;
        }
    }
    private boolean validateBrand(){
        String val = addRentCarBrand.getText().toString();

        if(val.isEmpty()) {
            addRentCarBrand.setError("Field Cannot be Empty");
            return false;
        }else{
            addRentCarBrand.setError(null);
            return true;
        }
    }
    private boolean validateModel(){
        String val = addRentCarModel.getText().toString();

        if(val.isEmpty()) {
            addRentCarModel.setError("Field Cannot be Empty");
            return false;
        }else{
            addRentCarModel.setError(null);
            return true;
        }
    }
    private boolean validateModelYear(){
        String val = addRentCarModelYear.getText().toString();

        if(val.isEmpty()) {
            addRentCarModelYear.setError("Field Cannot be Empty");
            return false;
        }else{
            addRentCarModelYear.setError(null);
            return true;
        }
    }
    private boolean validateBodyType(){
        String val = addRentCarBodyType.getText().toString();

        if(val.isEmpty()) {
            addRentCarBodyType.setError("Field Cannot be Empty");
            return false;
        }else{
            addRentCarBodyType.setError(null);
            return true;
        }
    }
    private boolean validateTransmission(){
        String val = addRentCarTransmission.getText().toString();

        if(val.isEmpty()) {
            addRentCarTransmission.setError("Field Cannot be Empty");
            return false;
        }else{
            addRentCarTransmission.setError(null);
            return true;
        }
    }
    private boolean validateEngineCapacity(){
        String val = addRentCarEngCapacity.getText().toString();

        if(val.isEmpty()) {
            addRentCarEngCapacity.setError("Field Cannot be Empty");
            return false;
        }else{
            addRentCarEngCapacity.setError(null);
            return true;
        }
    }
    private boolean validateMileage(){
        String val = addRentCarMilage.getText().toString();

        if(val.isEmpty()) {
            addRentCarMilage.setError("Field Cannot be Empty");
            return false;
        }else{
            addRentCarMilage.setError(null);
            return true;
        }
    }
    private boolean validateDescription(){
        String val = addRentCarDesc.getText().toString();

        if(val.isEmpty()) {
            addRentCarDesc.setError("Field Cannot be Empty");
            return false;
        }else if(val.length() >= 1500) {
            addRentCarDesc.setError("Description is Too Long");
            return false;
        }else{
            addRentCarDesc.setError(null);
            return true;
        }
    }
    private boolean validatePrice(){
        String val = addRentCarPrice.getText().toString();

        if(val.isEmpty()) {
            addRentCarPrice.setError("Field Cannot be Empty");
            return false;
        }else{
            addRentCarPrice.setError(null);
            return true;
        }
    }

    private boolean validateFuelCity(){
        String val = addRentCarFuelCity.getText().toString();

        if(val.isEmpty()) {
            addRentCarFuelCity.setError("Field Cannot be Empty");
            return false;
        }else{
            addRentCarFuelCity.setError(null);
            return true;
        }
    }

    private boolean validateFuelHighway(){
        String val = addRentCarFuelHighway.getText().toString();

        if(val.isEmpty()) {
            addRentCarFuelHighway.setError("Field Cannot be Empty");
            return false;
        }else{
            addRentCarFuelHighway.setError(null);
            return true;
        }
    }


    public void uploadAddRentCar(View view){
        if(!validateTopic() | !validateBrand() | !validateModel() | !validateModelYear() | !validateBodyType() | !validateTransmission() | !validateEngineCapacity() | !validateMileage() | !validateDescription() | !validatePrice() | !validateFuelCity() | !validateFuelHighway()){
            return;
        }else{
            addRentCarDetails();
        }
    }


    private void addRentCarDetails(){

        final String add_rent_car_topic = addRentCarTopic.getText().toString();
        final String add_rent_car_brand = addRentCarBrand.getText().toString();
        final String add_rent_car_model = addRentCarModel.getText().toString();
        final String add_rent_car_model_year = addRentCarModelYear.getText().toString();
        final String add_rent_car_body_type = addRentCarBodyType.getText().toString();
        final String add_rent_car_transmission = addRentCarTransmission.getText().toString();
        final String add_rent_car_engine_capacity = addRentCarEngCapacity.getText().toString();
        final String add_rent_car_mileage = addRentCarMilage.getText().toString();
        final String add_rent_car_description = addRentCarDesc.getText().toString();
        final String add_rent_car_price = addRentCarPrice.getText().toString();
        final String add_rent_car_fuel_city = addRentCarFuelCity.getText().toString();
        final String add_rent_car_fuel_Highway = addRentCarFuelHighway.getText().toString();

        if(isImageAdded != false){

            prograssText.setVisibility(View.VISIBLE);
            progressBar_add_rent_car.setVisibility(View.VISIBLE);

            final String key = dataReference.push().getKey();
            storageReference.child(key+ "jpg").putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    storageReference.child(key+ "jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("RentCarTopic",add_rent_car_topic);
                            hashMap.put("RentCarBrand",add_rent_car_brand);
                            hashMap.put("RentCarModel",add_rent_car_model);
                            hashMap.put("RentCarYear",add_rent_car_model_year);
                            hashMap.put("RentCarBodyType",add_rent_car_body_type);
                            hashMap.put("RentCarTransmission",add_rent_car_transmission);
                            hashMap.put("RentCarEngineCapacity",add_rent_car_engine_capacity);
                            hashMap.put("RentCarMileage",add_rent_car_mileage);
                            hashMap.put("RentCarDescription",add_rent_car_description);
                            hashMap.put("RentCarPrice",add_rent_car_price);
                            hashMap.put("FuelCity",add_rent_car_fuel_city);
                            hashMap.put("FuelHighway",add_rent_car_fuel_Highway);
                            hashMap.put("ImageURL",uri.toString());

                            dataReference.child(key).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(AddRentCar.this,"Data Successfully Uploaded",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(AddRentCar.this,AdminDashboard.class));
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(AddRentCar.this, "Error", Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(AddRentCar.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    double progress = (taskSnapshot.getBytesTransferred()*100)/taskSnapshot.getTotalByteCount();
                    progressBar_add_rent_car.setProgress((int) progress);
                    prograssText.setText(progress+"%");
                }
            });
        }

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_IMAGE && data != null){
            imageUri = data.getData();
            isImageAdded = true;
            addRentCar_ImageView.setImageURI(imageUri);

        }
    }
}
