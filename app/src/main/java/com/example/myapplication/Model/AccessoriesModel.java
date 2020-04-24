package com.example.myapplication.Model;

public class AccessoriesModel {
    private String AccessoriesBrand,AccessoriesCondition,AccessoriesDescription,AccessoriesModel,AccessoriesPrice,AccessoriesTopic,ImageURL;

    public String getAccessoriesBrand() {
        return AccessoriesBrand;
    }

    public void setAccessoriesBrand(String accessoriesBrand) {
        AccessoriesBrand = accessoriesBrand;
    }

    public String getAccessoriesCondition() {
        return AccessoriesCondition;
    }

    public void setAccessoriesCondition(String accessoriesCondition) {
        AccessoriesCondition = accessoriesCondition;
    }

    public String getAccessoriesDescription() {
        return AccessoriesDescription;
    }

    public void setAccessoriesDescription(String accessoriesDescription) {
        AccessoriesDescription = accessoriesDescription;
    }

    public String getAccessoriesModel() {
        return AccessoriesModel;
    }

    public void setAccessoriesModel(String accessoriesModel) {
        AccessoriesModel = accessoriesModel;
    }

    public String getAccessoriesPrice() {
        return AccessoriesPrice;
    }

    public void setAccessoriesPrice(String accessoriesPrice) {
        AccessoriesPrice = accessoriesPrice;
    }

    public String getAccessoriesTopic() {
        return AccessoriesTopic;
    }

    public void setAccessoriesTopic(String accessoriesTopic) {
        AccessoriesTopic = accessoriesTopic;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }

    public AccessoriesModel(String accessoriesBrand, String accessoriesCondition, String accessoriesDescription, String accessoriesModel, String accessoriesPrice, String accessoriesTopic, String imageURL) {
        AccessoriesBrand = accessoriesBrand;
        AccessoriesCondition = accessoriesCondition;
        AccessoriesDescription = accessoriesDescription;
        AccessoriesModel = accessoriesModel;
        AccessoriesPrice = accessoriesPrice;
        AccessoriesTopic = accessoriesTopic;
        ImageURL = imageURL;
    }

    public AccessoriesModel() {
    }
}
