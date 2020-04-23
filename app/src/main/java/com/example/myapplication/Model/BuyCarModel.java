package com.example.myapplication.Model;

public class BuyCarModel {

    private String CarBodyType,CarBrand,CarCondition,CarDescription,CarEngineCapacity,CarMileage,CarModel,CarModelYear,CarPrice,CarTopic,CarTransmission,FuelCity,FuelHighway,ImageURL;

    public BuyCarModel() {
    }

    public BuyCarModel(String carBodyType, String carBrand, String carCondition, String carDescription, String carEngineCapacity, String carMileage, String carModel, String carModelYear,String carPrice, String carTopic, String carTransmission,String fuelCity,String fuelHighway, String imageURL) {
        CarBodyType = carBodyType;
        CarBrand = carBrand;
        CarCondition = carCondition;
        CarDescription = carDescription;
        CarEngineCapacity = carEngineCapacity;
        CarMileage = carMileage;
        CarModel = carModel;
        CarModelYear = carModelYear;
        CarPrice = carPrice;
        CarTopic = carTopic;
        CarTransmission = carTransmission;
        FuelCity = fuelCity;
        FuelHighway = fuelHighway;
        ImageURL = imageURL;

    }

    public String getFuelCity() {
        return FuelCity;
    }

    public void setFuelCity(String fuelCity) {
        FuelCity = fuelCity;
    }

    public String getFuelHighway() {
        return FuelHighway;
    }

    public void setFuelHighway(String fuelHighway) {
        FuelHighway = fuelHighway;
    }

    public String getCarBodyType() {
        return CarBodyType;
    }

    public void setCarBodyType(String carBodyType) {
        CarBodyType = carBodyType;
    }

    public String getCarBrand() {
        return CarBrand;
    }

    public String getCarPrice() {
        return CarPrice;
    }

    public void setCarPrice(String carPrice) {
        CarPrice = carPrice;
    }

    public void setCarBrand(String carBrand) {
        CarBrand = carBrand;
    }

    public String getCarCondition() {
        return CarCondition;
    }

    public void setCarCondition(String carCondition) {
        CarCondition = carCondition;
    }

    public String getCarDescription() {
        return CarDescription;
    }

    public void setCarDescription(String carDescription) {
        CarDescription = carDescription;
    }

    public String getCarEngineCapacity() {
        return CarEngineCapacity;
    }

    public void setCarEngineCapacity(String carEngineCapacity) {
        CarEngineCapacity = carEngineCapacity;
    }

    public String getCarMileage() {
        return CarMileage;
    }

    public void setCarMileage(String carMileage) {
        CarMileage = carMileage;
    }

    public String getCarModel() {
        return CarModel;
    }

    public void setCarModel(String carModel) {
        CarModel = carModel;
    }

    public String getCarModelYear() {
        return CarModelYear;
    }

    public void setCarModelYear(String carModelYear) {
        CarModelYear = carModelYear;
    }

    public String getCarTopic() {
        return CarTopic;
    }

    public void setCarTopic(String carTopic) {
        CarTopic = carTopic;
    }

    public String getCarTransmission() {
        return CarTransmission;
    }

    public void setCarTransmission(String carTransmission) {
        CarTransmission = carTransmission;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }
}
