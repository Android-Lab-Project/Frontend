package com.example.myapplication;

public class NutritionItem {
    private String imageUrl;
    private String name;
    private String quantity;
    private String unit;
    private String servingWeight;
    private String calories;
    private String fat;
    private String Cholesterol;
    private String protein;
    private String carbohydrate;
    private String sugar;

    public String getImageUrl() {
        return imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getUnit() {
        return unit;
    }

    public String getServingWeight() {
        return servingWeight;
    }

    public String getCalories() {
        return calories;
    }

    public String getFat() {
        return fat;
    }

    public String getCholesterol() {
        return Cholesterol;
    }

    public String getProtein() {
        return protein;
    }

    public String getCarbohydrate() {
        return carbohydrate;
    }

    public String getSugar() {
        return sugar;
    }

    public NutritionItem(String imageUrl, String name, String quantity, String unit, String servingWeight, String calories, String fat, String cholesterol, String protein, String carbohydrate, String sugar) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
        this.servingWeight = servingWeight;
        this.calories = calories;
        this.fat = fat;
        Cholesterol = cholesterol;
        this.protein = protein;
        this.carbohydrate = carbohydrate;
        this.sugar = sugar;
    }
}
