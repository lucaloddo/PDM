package com.example.athletify.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Calories implements Parcelable {

    private double value;

    public Calories(double value) {
        this.value = value;
    }

    public Calories() {

    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Calories{" +
                "value=" + value +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(value);
    }

    public void readFromParcel(Parcel source) {
        this.value = source.readDouble();
    }

    protected Calories(Parcel in) {
        value = in.readDouble();
    }

    public static final Creator<Calories> CREATOR = new Creator<Calories>() {
        @Override
        public Calories createFromParcel(Parcel in) {
            return new Calories(in);
        }

        @Override
        public Calories[] newArray(int size) {
            return new Calories[size];
        }
    };
}
