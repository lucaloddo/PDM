package com.example.athletify.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Workouts implements Parcelable {

    private String name;
    private String level;
    private String freq;
    private List<wkDays> wkDaysList;

    public Workouts(String name, String level, String freq, List<wkDays> wkDaysList) {
        this.name = name;
        this.level = level;
        this.freq = freq;
        this.wkDaysList = wkDaysList;
    }

    protected Workouts(Parcel in) {
        name = in.readString();
        level = in.readString();
        freq = in.readString();
        wkDaysList = in.createTypedArrayList(wkDays.CREATOR);
    }

    public static final Creator<Workouts> CREATOR = new Creator<Workouts>() {
        @Override
        public Workouts createFromParcel(Parcel in) {
            return new Workouts(in);
        }

        @Override
        public Workouts[] newArray(int size) {
            return new Workouts[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getFreq() {
        return freq;
    }

    public void setFreq(String freq) {
        this.freq = freq;
    }

    public List<wkDays> getWkDaysList() {
        return wkDaysList;
    }

    public void setWkDaysList(List<wkDays> wkDaysList) {
        this.wkDaysList = wkDaysList;
    }

    @Override
    public String toString() {
        return "Workouts{" +
                "name='" + name + '\'' +
                ", level='" + level + '\'' +
                ", freq='" + freq + '\'' +
                ", wkDaysList=" + wkDaysList +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(this.name);
        dest.writeString(this.level);
        dest.writeString(this.freq);
        dest.writeTypedList(this.wkDaysList);
    }

    public void readFromParcel(Parcel source) {
        this.name = source.readString();
        this.level = source.readString();
        this.freq = source.readString();
        this.wkDaysList = source.createTypedArrayList(wkDays.CREATOR);
    }
}
