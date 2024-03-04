package com.example.athletify.models;

import android.os.Parcel;
import android.os.Parcelable;

public class BodyMassIndex implements Parcelable {
    private double value;
    private String conclusion;

    public BodyMassIndex(double value, String conclusion) {
        this.value = value;
        this.conclusion = conclusion;
    }

    public BodyMassIndex() {

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
        return "BodyMassIndex{" +
                "value=" + value +
                ", conclusion='" + conclusion + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.value);
        parcel.writeString(this.conclusion);
    }

    public void readFromParcel(Parcel source) {
        this.value = source.readDouble();
        this.conclusion = source.readString();
    }

    protected BodyMassIndex(Parcel in) {
        this.value = in.readDouble();
        this.conclusion = in.readString();
    }

    public static final Creator<BodyMassIndex> CREATOR = new Creator<BodyMassIndex>() {
        @Override
        public BodyMassIndex createFromParcel(Parcel in) {
            return new BodyMassIndex(in);
        }

        @Override
        public BodyMassIndex[] newArray(int size) {
            return new BodyMassIndex[size];
        }
    };
}
