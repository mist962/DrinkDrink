package com.example.drinkdrink.POJO;

public class WaterNormPOJO {

    private int weight;
    private float gender;

    public WaterNormPOJO(int weight, float gender) {
        this.weight = weight;
        this.gender = gender;
    }

    public WaterNormPOJO(float gender, int weight) {
        this.gender = gender;
        this.weight = weight;

    }

    public int getWeight() {
        return weight;
    }
    public float getGender(){
        return gender;
    }
}
