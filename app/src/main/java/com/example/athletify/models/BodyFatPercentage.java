package com.example.athletify.models;

import android.os.Parcel;
import android.os.Parcelable;

public class BodyFatPercentage implements Parcelable {
    private BMI_BFP bmi;

    public BodyFatPercentage(BMI_BFP bmi) {
        this.bmi = bmi;
    }

    public BodyFatPercentage() {

    }

    public BMI_BFP getBmi() {
        return bmi;
    }

    public void setBmi(BMI_BFP bmi) {
        this.bmi = bmi;
    }

    @Override
    public String toString() {
        return "BodyFatPercentage{" +
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

    protected BodyFatPercentage(Parcel in) {
        this.bmi = in.readParcelable(BMI_BFP.class.getClassLoader());
    }

    public static final Creator<BodyFatPercentage> CREATOR = new Creator<BodyFatPercentage>() {
        @Override
        public BodyFatPercentage createFromParcel(Parcel in) {
            return new BodyFatPercentage(in);
        }

        @Override
        public BodyFatPercentage[] newArray(int size) {
            return new BodyFatPercentage[size];
        }
    };
}
