package com.example.myapplication.Model;

public class RentCarModel {

    private String FuelCity,FuelHighway,RentCarBodyType,RentCarBrand,RentCarDescription,RentCarEngineCapacity,RentCarMileage,RentCarModel,RentCarPrice,RentCarTopic,RentCarTransmission,RentCarYear,ImageURL;

    public RentCarModel() {
    }

    public RentCarModel(String fuelCity, String fuelHighway, String rentCarBodyType, String rentCarBrand, String rentCarDescription, String rentCarEngineCapacity, String rentCarMileage, String rentCarModel, String rentCarPrice, String rentCarTopic, String rentCarTransmission, String rentCarYear,String imageURL) {
        FuelCity = fuelCity;
        FuelHighway = fuelHighway;
        RentCarBodyType = rentCarBodyType;
        RentCarBrand = rentCarBrand;
        RentCarDescription = rentCarDescription;
        RentCarEngineCapacity = rentCarEngineCapacity;
        RentCarMileage = rentCarMileage;
        RentCarModel = rentCarModel;
        RentCarPrice = rentCarPrice;
        RentCarTopic = rentCarTopic;
        RentCarTransmission = rentCarTransmission;
        RentCarYear = rentCarYear;
        ImageURL = imageURL;
    }

    public String getFuelCity() {
        return FuelCity;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
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

    public String getRentCarBodyType() {
        return RentCarBodyType;
    }

    public void setRentCarBodyType(String rentCarBodyType) {
        RentCarBodyType = rentCarBodyType;
    }

    public String getRentCarBrand() {
        return RentCarBrand;
    }

    public void setRentCarBrand(String rentCarBrand) {
        RentCarBrand = rentCarBrand;
    }

    public String getRentCarDescription() {
        return RentCarDescription;
    }

    public void setRentCarDescription(String rentCarDescription) {
        RentCarDescription = rentCarDescription;
    }

    public String getRentCarEngineCapacity() {
        return RentCarEngineCapacity;
    }

    public void setRentCarEngineCapacity(String rentCarEngineCapacity) {
        RentCarEngineCapacity = rentCarEngineCapacity;
    }

    public String getRentCarMileage() {
        return RentCarMileage;
    }

    public void setRentCarMileage(String rentCarMileage) {
        RentCarMileage = rentCarMileage;
    }

    public String getRentCarModel() {
        return RentCarModel;
    }

    public void setRentCarModel(String rentCarModel) {
        RentCarModel = rentCarModel;
    }

    public String getRentCarPrice() {
        return RentCarPrice;
    }

    public void setRentCarPrice(String rentCarPrice) {
        RentCarPrice = rentCarPrice;
    }

    public String getRentCarTopic() {
        return RentCarTopic;
    }

    public void setRentCarTopic(String rentCarTopic) {
        RentCarTopic = rentCarTopic;
    }

    public String getRentCarTransmission() {
        return RentCarTransmission;
    }

    public void setRentCarTransmission(String rentCarTransmission) {
        RentCarTransmission = rentCarTransmission;
    }

    public String getRentCarYear() {
        return RentCarYear;
    }

    public void setRentCarYear(String rentCarYear) {
        RentCarYear = rentCarYear;
    }
}
