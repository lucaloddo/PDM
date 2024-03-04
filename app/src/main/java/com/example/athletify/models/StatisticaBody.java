package com.example.athletify.models;

public class StatisticaBody {

    private String height;
    private String weight;
    private String age;
    private String gender;
    private String exercise;
    private String neck;
    private String hip;
    private String waist;

    public StatisticaBody(String height, String weight, String age, String gender, String exercise, String neck, String hip, String waist) {
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.gender = gender;
        this.exercise = exercise;
        this.neck = neck;
        this.hip = hip;
        this.waist = waist;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public String getNeck() {
        return neck;
    }

    public void setNeck(String neck) {
        this.neck = neck;
    }

    public String getHip() {
        return hip;
    }

    public void setHip(String hip) {
        this.hip = hip;
    }

    public String getWaist() {
        return waist;
    }

    public void setWaist(String waist) {
        this.waist = waist;
    }
}
