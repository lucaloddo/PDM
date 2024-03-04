package com.example.athletify.models;

import android.os.Parcel;
import android.os.Parcelable;

public class BMI_BFP implements Parcelable {

    private String formulaName;
    private double value;
    private String conclusion;

    public BMI_BFP(String formulaName, double value, String conclusion) {
        this.formulaName = formulaName;
        this.value = value;
        this.conclusion = conclusion;
    }

    public BMI_BFP() {

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

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    @Override
    public String toString() {
        return "BMI_BFP{" +
                "formulaName='" + formulaName + '\'' +
                ", value=" + value +
                ", conclusion='" + conclusion + '\'' +
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
        parcel.writeString(conclusion);
    }

    public void readFromParcel(Parcel source) {
        this.formulaName = source.readString();
        this.value = source.readDouble();
        this.conclusion = source.readString();
    }

    protected BMI_BFP(Parcel in) {
        formulaName = in.readString();
        value = in.readDouble();
        conclusion = in.readString();
    }

    public static final Creator<BMI_BFP> CREATOR = new Creator<BMI_BFP>() {
        @Override
        public BMI_BFP createFromParcel(Parcel in) {
            return new BMI_BFP(in);
        }

        @Override
        public BMI_BFP[] newArray(int size) {
            return new BMI_BFP[size];
        }
    };
}
