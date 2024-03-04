package com.example.athletify.models;

import android.os.Parcel;
import android.os.Parcelable;

public class wkExercise implements Parcelable {

    private String exerciseName;
    private String set;
    private String reps;
    private String weight;

    public wkExercise(String exerciseName, String set, String reps, String weight) {
        this.exerciseName = exerciseName;
        this.set = set;
        this.reps = reps;
        this.weight = weight;
    }

    protected wkExercise(Parcel in) {
        exerciseName = in.readString();
        set = in.readString();
        reps = in.readString();
        weight = in.readString();
    }

    public static final Creator<wkExercise> CREATOR = new Creator<wkExercise>() {
        @Override
        public wkExercise createFromParcel(Parcel in) {
            return new wkExercise(in);
        }

        @Override
        public wkExercise[] newArray(int size) {
            return new wkExercise[size];
        }
    };

    public wkExercise() {

    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
    }

    public String getReps() {
        return reps;
    }

    public void setReps(String reps) {
        this.reps = reps;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "wkExercise{" +
                "exerciseName='" + exerciseName + '\'' +
                ", set='" + set + '\'' +
                ", reps='" + reps + '\'' +
                ", weight='" + weight + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(this.exerciseName);
        dest.writeString(this.set);
        dest.writeString(this.reps);
        dest.writeString(this.weight);
    }

    public void readFromParcel(Parcel source) {
        this.exerciseName = source.readString();
        this.set = source.readString();
        this.reps = source.readString();
        this.weight = source.readString();
    }
}
