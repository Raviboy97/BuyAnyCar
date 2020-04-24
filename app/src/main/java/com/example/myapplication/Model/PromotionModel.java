package com.example.myapplication.Model;

public class PromotionModel {
    private String ImageURL,PromotionDescription,PromotionDiscount,PromotionTopic;

    public PromotionModel(String imageURL, String promotionDescription, String promotionDiscount, String promotionTopic) {
        ImageURL = imageURL;
        PromotionDescription = promotionDescription;
        PromotionDiscount = promotionDiscount;
        PromotionTopic = promotionTopic;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }

    public String getPromotionDescription() {
        return PromotionDescription;
    }

    public void setPromotionDescription(String promotionDescription) {
        PromotionDescription = promotionDescription;
    }

    public String getPromotionDiscount() {
        return PromotionDiscount;
    }

    public void setPromotionDiscount(String promotionDiscount) {
        PromotionDiscount = promotionDiscount;
    }

    public String getPromotionTopic() {
        return PromotionTopic;
    }

    public void setPromotionTopic(String promotionTopic) {
        PromotionTopic = promotionTopic;
    }

    public PromotionModel() {
    }
}
