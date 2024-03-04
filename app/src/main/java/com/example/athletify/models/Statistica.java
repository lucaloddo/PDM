package com.example.athletify.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Statistica implements Parcelable {

    private BodyMassIndex bodyMassIndex;
    private BodyFatPercentage bodyFatPercentage;
    private LeanBodyMass leanBodyMass;
    private TotalDailyEnergyExpenditure totalDailyEnergyExpenditure;

    public Statistica(BodyMassIndex bodyMassIndex, BodyFatPercentage bodyFatPercentage, LeanBodyMass leanBodyMass, TotalDailyEnergyExpenditure totalDailyEnergyExpenditure) {
        this.bodyMassIndex = bodyMassIndex;
        this.bodyFatPercentage = bodyFatPercentage;
        this.leanBodyMass = leanBodyMass;
        this.totalDailyEnergyExpenditure = totalDailyEnergyExpenditure;
    }

    public Statistica() {

    }

    public BodyMassIndex getBodyMassIndex() {
        return bodyMassIndex;
    }

    public void setBodyMassIndex(BodyMassIndex bodyMassIndex) {
        this.bodyMassIndex = bodyMassIndex;
    }

    public BodyFatPercentage getBodyFatPercentage() {
        return bodyFatPercentage;
    }

    public void setBodyFatPercentage(BodyFatPercentage bodyFatPercentage) {
        this.bodyFatPercentage = bodyFatPercentage;
    }

    public LeanBodyMass getLeanBodyMass() {
        return leanBodyMass;
    }

    public void setLeanBodyMass(LeanBodyMass leanBodyMass) {
        this.leanBodyMass = leanBodyMass;
    }

    public TotalDailyEnergyExpenditure getTotalDailyEnergyExpenditure() {
        return totalDailyEnergyExpenditure;
    }

    public void setTotalDailyEnergyExpenditure(TotalDailyEnergyExpenditure totalDailyEnergyExpenditure) {
        this.totalDailyEnergyExpenditure = totalDailyEnergyExpenditure;
    }

    @Override
    public String toString() {
        return "Statistica{" +
                "bodyMassIndex=" + bodyMassIndex +
                ", bodyFatPercentage=" + bodyFatPercentage +
                ", leanBodyMass=" + leanBodyMass +
                ", totalDailyEnergyExpenditure=" + totalDailyEnergyExpenditure +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.bodyMassIndex, i);
        parcel.writeParcelable(this.bodyFatPercentage, i);
        parcel.writeParcelable(this.leanBodyMass, i);
        parcel.writeParcelable(this.totalDailyEnergyExpenditure, i);
    }

    public void readFromParcel(Parcel source) {
        this.bodyMassIndex = source.readParcelable(BodyMassIndex.class.getClassLoader());
        this.bodyFatPercentage = source.readParcelable(BodyFatPercentage.class.getClassLoader());
        this.leanBodyMass = source.readParcelable(LeanBodyMass.class.getClassLoader());
        this.totalDailyEnergyExpenditure = source.readParcelable(TotalDailyEnergyExpenditure.class.getClassLoader());
    }

    protected Statistica(Parcel in) {
        this.bodyMassIndex = in.readParcelable(BodyMassIndex.class.getClassLoader());
        this.bodyFatPercentage = in.readParcelable(BodyFatPercentage.class.getClassLoader());
        this.leanBodyMass = in.readParcelable(LeanBodyMass.class.getClassLoader());
        this.totalDailyEnergyExpenditure = in.readParcelable(TotalDailyEnergyExpenditure.class.getClassLoader());
    }

    public static final Creator<Statistica> CREATOR = new Creator<Statistica>() {
        @Override
        public Statistica createFromParcel(Parcel in) {
            return new Statistica(in);
        }

        @Override
        public Statistica[] newArray(int size) {
            return new Statistica[size];
        }
    };

}
