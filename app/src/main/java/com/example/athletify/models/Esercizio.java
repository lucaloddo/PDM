package com.example.athletify.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Esercizio implements Parcelable {

    private String bodyPart;
    private String equipment;
    private String gifUrl;
    private String id;
    private String name;
    private String target;

    public Esercizio(String bodyPart, String equipment, String gifUrl, String id, String name, String target) {
        this.bodyPart = bodyPart;
        this.equipment = equipment;
        this.gifUrl = gifUrl;
        this.id = id;
        this.name = name;
        this.target = target;
    }

    protected Esercizio(Parcel in) {
        bodyPart = in.readString();
        equipment = in.readString();
        gifUrl = in.readString();
        id = in.readString();
        name = in.readString();
        target = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(bodyPart);
        dest.writeString(equipment);
        dest.writeString(gifUrl);
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(target);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Esercizio> CREATOR = new Creator<Esercizio>() {
        @Override
        public Esercizio createFromParcel(Parcel in) {
            return new Esercizio(in);
        }

        @Override
        public Esercizio[] newArray(int size) {
            return new Esercizio[size];
        }
    };

    public String getBodyPart() {
        return bodyPart;
    }

    public void setBodyPart(String bodyPart) {
        this.bodyPart = bodyPart;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getGifUrl() {
        return gifUrl;
    }

    public void setGifUrl(String gifUrl) {
        this.gifUrl = gifUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    @Override
    public String toString() {
        return "Esercizio{" +
                "bodyPart='" + bodyPart + '\'' +
                ", equipment='" + equipment + '\'' +
                ", gifUrl='" + gifUrl + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", target='" + target + '\'' +
                '}';
    }
}
