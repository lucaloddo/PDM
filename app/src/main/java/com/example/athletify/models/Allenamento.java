package com.example.athletify.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Allenamento implements Parcelable {

    private List<Workouts> workouts;

    public Allenamento(List<Workouts> workouts) {
        this.workouts = workouts;
    }

    public List<Workouts> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(List<Workouts> workouts) {
        this.workouts = workouts;
    }

    @Override
    public String toString() {
        return "Allenamento{" +
                "workouts=" + workouts +
                '}';
    }

    protected Allenamento(Parcel in) {
        this.workouts = in.createTypedArrayList(Workouts.CREATOR);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeTypedList(this.workouts);
    }

    public static final Creator<Allenamento> CREATOR = new Creator<Allenamento>() {
        @Override
        public Allenamento createFromParcel(Parcel in) {
            return new Allenamento(in);
        }

        @Override
        public Allenamento[] newArray(int size) {
            return new Allenamento[size];
        }
    };

}
