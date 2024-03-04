package com.example.athletify.models;

import android.os.Parcel;
import android.os.Parcelable;

public class LeanBodyMass implements Parcelable {

    private BMI_LBM bmi;

    public LeanBodyMass(BMI_LBM bmi) {
        this.bmi = bmi;
    }

    public LeanBodyMass() {

    }

    public BMI_LBM getBmi() {
        return bmi;
    }

    public void setBmi(BMI_LBM bmi) {
        this.bmi = bmi;
    }

    @Override
    public String toString() {
        return "LeanBodyMass{" +
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
        this.bmi = source.readParcelable(BMI_LBM.class.getClassLoader());
    }

    protected LeanBodyMass(Parcel in) {
        this.bmi = in.readParcelable(BMI_LBM.class.getClassLoader());
    }

    public static final Creator<LeanBodyMass> CREATOR = new Creator<LeanBodyMass>() {
        @Override
        public LeanBodyMass createFromParcel(Parcel in) {
            return new LeanBodyMass(in);
        }

        @Override
        public LeanBodyMass[] newArray(int size) {
            return new LeanBodyMass[size];
        }
    };
}
