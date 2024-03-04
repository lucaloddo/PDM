package com.example.athletify.models;

import android.os.Parcel;
import android.os.Parcelable;

public class TotalDailyEnergyExpenditure implements Parcelable {

    private BMI_TDEE bmi;

    public TotalDailyEnergyExpenditure(BMI_TDEE bmi) {
        this.bmi = bmi;
    }

    public TotalDailyEnergyExpenditure() {

    }

    public BMI_TDEE getBmi() {
        return bmi;
    }

    public void setBmi(BMI_TDEE bmi) {
        this.bmi = bmi;
    }

    @Override
    public String toString() {
        return "TotalDailyEnergyExpenditure{" +
                "bmi=" + bmi +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.bmi, i);
    }

    public void readFromParcel(Parcel source) {
        this.bmi = source.readParcelable(BMI_TDEE.class.getClassLoader());
    }

    protected TotalDailyEnergyExpenditure(Parcel in) {
        this.bmi = in.readParcelable(BMI_TDEE.class.getClassLoader());
    }

    public static final Creator<TotalDailyEnergyExpenditure> CREATOR = new Creator<TotalDailyEnergyExpenditure>() {
        @Override
        public TotalDailyEnergyExpenditure createFromParcel(Parcel in) {
            return new TotalDailyEnergyExpenditure(in);
        }

        @Override
        public TotalDailyEnergyExpenditure[] newArray(int size) {
            return new TotalDailyEnergyExpenditure[size];
        }
    };
}
