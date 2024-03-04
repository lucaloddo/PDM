package com.example.athletify.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Joules implements Parcelable {

    private double value;

    public Joules(double value) {
        this.value = value;
    }

    public Joules() {

    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Joules{" +
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

    protected Joules(Parcel in) {
        value = in.readDouble();
    }

    public static final Creator<Joules> CREATOR = new Creator<Joules>() {
        @Override
        public Joules createFromParcel(Parcel in) {
            return new Joules(in);
        }

        @Override
        public Joules[] newArray(int size) {
            return new Joules[size];
        }
    };
}
