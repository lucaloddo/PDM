package com.example.athletify.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class wkDays implements Parcelable {

    private String dayName;
    private List<wkExercise> wkExerciseList;

    public wkDays(String dayName, List<wkExercise> wkExerciseList) {
        this.dayName = dayName;
        this.wkExerciseList = wkExerciseList;
    }

    public wkDays() {

    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public List<wkExercise> getWkExerciseList() {
        return wkExerciseList;
    }

    public void setWkExerciseList(List<wkExercise> wkExerciseList) {
        this.wkExerciseList = wkExerciseList;
    }

    @Override
    public String toString() {
        return "wkDays{" +
                "dayName='" + dayName + '\'' +
                ", wkExerciseList=" + wkExerciseList +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(this.dayName);
        dest.writeTypedList(this.wkExerciseList);
    }

    public void readFromParcel(Parcel source) {
        this.dayName = source.readString();
        this.wkExerciseList = source.createTypedArrayList(wkExercise.CREATOR);
    }

    protected wkDays(Parcel in) {
        dayName = in.readString();
        wkExerciseList = in.createTypedArrayList(wkExercise.CREATOR);
    }

    public static final Creator<wkDays> CREATOR = new Creator<wkDays>() {
        @Override
        public wkDays createFromParcel(Parcel in) {
            return new wkDays(in);
        }

        @Override
        public wkDays[] newArray(int size) {
            return new wkDays[size];
        }
    };
}
