package com.iteso.caflores.splashscreen.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Carlos Flores on 16/10/2017.
 */

public class Category implements Parcelable {

    private int idCategory;
    private String name;

    public static final Creator<Category> CREATOR =
            new Creator<Category>() {
                @Override
                public Category createFromParcel(Parcel parcel) {
                    return new Category(parcel);
                }

                @Override
                public Category[] newArray(int size) {
                    return new Category[size];
                }
            };

    @Override
    public int describeContents() {
        return 0;
    }

    public Category(Parcel in){
        idCategory = in.readInt();
        name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idCategory);
        dest.writeString(name);
    }

    public  Category(){
        idCategory = 0;
        name = "";
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
