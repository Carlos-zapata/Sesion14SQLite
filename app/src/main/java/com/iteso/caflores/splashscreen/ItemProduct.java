package com.iteso.caflores.splashscreen;

import android.os.Parcel;
import android.os.Parcelable;

import com.iteso.caflores.splashscreen.beans.Category;
import com.iteso.caflores.splashscreen.beans.Store;

public class ItemProduct implements Parcelable {

    private int code;
    private int image;
    private String title;
    private String description;
    private Category category;
    private Store store;

    public ItemProduct(Parcel in) {
        code = in.readInt();
        image = in.readInt();
        title = in.readString();
        description = in.readString();
        category = in.readParcelable(Category.class.getClassLoader());
        store = in.readParcelable(Store.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(code);
        dest.writeInt(image);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeParcelable(category, flags);
        dest.writeParcelable(store, flags);
    }

    public static final Creator<ItemProduct> CREATOR =
            new Creator<ItemProduct>() {
                @Override
                public ItemProduct createFromParcel(Parcel parcel) {
                    return new ItemProduct(parcel);
                }

                @Override
                public ItemProduct[] newArray(int size) {
                    return new ItemProduct[size];
                }
            };

    public ItemProduct() {
        code = 0;
        image = 0;
        title = "";
        description = "";
        category = null;
        store = null;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
