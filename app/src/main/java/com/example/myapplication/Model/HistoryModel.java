package com.example.myapplication.Model;

public class HistoryModel {

    private String Topic,Brand,Model,Description,Price,ImageURL;

    public HistoryModel(String topic, String brand, String model, String description, String price, String imageURL) {
        Topic = topic;
        Brand = brand;
        Model = model;
        Description = description;
        Price = price;
        ImageURL = imageURL;
    }

    public HistoryModel() {
    }

    public String getTopic() {
        return Topic;
    }

    public void setTopic(String topic) {
        Topic = topic;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }
}
