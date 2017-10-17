package com.iteso.caflores.splashscreen.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Carlos Flores on 16/10/2017.
 */

public class Store implements Parcelable {

    private Integer id;
    private String name;
    private String phone;
    private City city;
    private Integer thumbnail;
    private Double latitude;
    private Double longitude;

    @Override
    public int describeContents() {
        return 0;
    }

    public Store(Parcel in){
        id = in.readInt();
        name = in.readString();
        phone = in.readString();
        city = in.readParcelable(City.class.getClassLoader());
        thumbnail = in.readInt();
        latitude = in.readDouble();
        longitude = in.readDouble();

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(phone);
        dest.writeParcelable(city, flags);
        dest.writeInt(thumbnail);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);

    }

    public static final Creator<Store> CREATOR =
            new Creator<Store>() {
                @Override
                public Store createFromParcel(Parcel parcel) {
                    return new Store(parcel);
                }

                @Override
                public Store[] newArray(int size) {
                    return new Store[size];
                }
            };

    public Store(){
        id = 0;
        name = "";
        phone = "";
        city = null;
        thumbnail = 0;
        latitude = 0.0;
        longitude = 0.0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Integer getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Integer thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
