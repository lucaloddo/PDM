package com.example.athletify.models;

import android.os.Parcel;
import android.os.Parcelable;

public class BMI_TDEE implements Parcelable {

    private String formulaName;
    private Calories calories;
    private Joules joules;

    public BMI_TDEE(String formulaName, Calories calories, Joules joules) {
        this.formulaName = formulaName;
        this.calories = calories;
        this.joules = joules;
    }

    public BMI_TDEE() {

    }

    public String getFormulaName() {
        return formulaName;
    }

    public void setFormulaName(String formulaName) {
        this.formulaName = formulaName;
    }

    public Calories getCalories() {
        return calories;
    }

    public void setCalories(Calories calories) {
        this.calories = calories;
    }

    public Joules getJoules() {
        return joules;
    }

    public void setJoules(Joules joules) {
        this.joules = joules;
    }

    @Override
    public String toString() {
        return "BMI_TDEE{" +
                "formulaName='" + formulaName + '\'' +
                ", calories=" + calories +
                ", joules=" + joules +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.formulaName);
        dest.writeParcelable(this.calories, flags);
        dest.writeParcelable(this.joules, flags);
    }

    public void readFromParcel(Parcel source) {
        this.formulaName = source.readString();
        this.calories = source.readParcelable(Calories.class.getClassLoader());
        this.joules = source.readParcelable(Joules.class.getClassLoader());
    }

    protected BMI_TDEE(Parcel in) {
        this.formulaName = in.readString();
        this.calories = in.readParcelable(Calories.class.getClassLoader());
        this.joules = in.readParcelable(Joules.class.getClassLoader());
    }

    public static final Creator<BMI_TDEE> CREATOR = new Creator<BMI_TDEE>() {
        @Override
        public BMI_TDEE createFromParcel(Parcel in) {
            return new BMI_TDEE(in);
        }

        @Override
        public BMI_TDEE[] newArray(int size) {
            return new BMI_TDEE[size];
        }
    };
}
