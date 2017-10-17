package com.iteso.caflores.splashscreen.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Carlos Flores on 16/10/2017.
 */

public class City implements Parcelable {

    private int idCity;
    private String name;

    public static final Creator<City> CREATOR =
            new Creator<City>() {
                @Override
                public City createFromParcel(Parcel parcel) {
                    return new City(parcel);
                }

                @Override
                public City[] newArray(int size) {
                    return new City[size];
                }
            };

    @Override
    public int describeContents() {
        return 0;
    }

    public City(Parcel in){
        idCity = in.readInt();
        name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idCity);
        dest.writeString(name);
    }

    public  City(){
        idCity = 0;
        name = "";
    }

    public int getIdCity() {
        return idCity;
    }

    public void setIdCity(int idCity) {
        this.idCity = idCity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

