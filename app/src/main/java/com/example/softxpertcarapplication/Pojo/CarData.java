package com.example.softxpertcarapplication.Pojo;

public class CarData {
    private int id;
    private String brand;
    private String constractionYear;
    private boolean isUsed;
    private String imageUrl;
    private boolean equals;

    public boolean isEquals() {
        return equals;
    }

    public String getBrand() {
        return brand;
    }

    public String getConstractionYear() {
        return constractionYear;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getId() {
        return id;
    }
}
