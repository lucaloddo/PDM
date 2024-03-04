package com.example.athletify.models;

import android.os.Parcel;
import android.os.Parcelable;

public class BMI_LBM implements Parcelable {

    private String formulaName;
    private double value;

    public BMI_LBM(String formulaName, double value) {
        this.formulaName = formulaName;
        this.value = value;
    }

    public BMI_LBM() {

    }

    public String getFormulaName() {
        return formulaName;
    }

    public void setFormulaName(String formulaName) {
        this.formulaName = formulaName;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "BMI_LBM{" +
                "formulaName='" + formulaName + '\'' +
                ", value=" + value +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(formulaName);
        parcel.writeDouble(value);
    }

    public void readFromParcel(Parcel source) {
        this.formulaName = source.readString();
        this.value = source.readDouble();
    }

    protected BMI_LBM(Parcel in) {
        formulaName = in.readString();
        value = in.readDouble();
    }

    public static final Creator<BMI_LBM> CREATOR = new Creator<BMI_LBM>() {
        @Override
        public BMI_LBM createFromParcel(Parcel in) {
            return new BMI_LBM(in);
        }

        @Override
        public BMI_LBM[] newArray(int size) {
            return new BMI_LBM[size];
        }
    };
}
