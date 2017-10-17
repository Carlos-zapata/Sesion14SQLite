package com.iteso.caflores.splashscreen.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.iteso.caflores.splashscreen.ItemProduct;
import com.iteso.caflores.splashscreen.beans.Category;
import com.iteso.caflores.splashscreen.beans.City;
import com.iteso.caflores.splashscreen.beans.Store;

import java.util.ArrayList;

/**
 * Created by Carlos Flores on 16/10/2017.
 */

public class ItemProductControl {



    public long addItemProduct(ItemProduct product, DatabaseHandler dh) {
        long inserted = 0;
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DatabaseHandler.KEY_PRODUCT_TITLE, product.getTitle());
        values.put(DatabaseHandler.KEY_PRODUCT_IMAGE, product.getImage());
        values.put(DatabaseHandler.KEY_PRODUCT_CATEGORY, product.getCategory().getIdCategory());

        // Inserting Row
        inserted = db.insert(DatabaseHandler.TABLE_PRODUCT, null, values);
        // Closing database connection
        try {db.close();} catch (Exception e) {}
        db = null; values = null;
        return inserted;
    }

    public void deleteItemProduct(int idProduct, DatabaseHandler dh) {
        SQLiteDatabase db = dh.getWritableDatabase();
        db.delete(DatabaseHandler.TABLE_PRODUCT, DatabaseHandler.KEY_PRODUCT_ID
                + " = ?", new String[] { String.valueOf(idProduct) });
        try {
            db.close();
        } catch (Exception e) {
        }
        db = null;
    }

    public int updateItemProduct(ItemProduct product, DatabaseHandler dh) {
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHandler.KEY_PRODUCT_TITLE, product.getTitle());
        values.put(DatabaseHandler.KEY_PRODUCT_IMAGE, product.getImage());
        values.put(DatabaseHandler.KEY_PRODUCT_CATEGORY, product.getCategory().getIdCategory());
        // Updating row
        int count = db.update(DatabaseHandler.TABLE_PRODUCT, values,
                DatabaseHandler.KEY_PRODUCT_ID + " = ?",
                new String[] { String.valueOf(product.getCode()) });
        try { db.close();} catch (Exception e) {}
        db = null;
        return count;
    }

    public ItemProduct getItemProductById(int idProduct, DatabaseHandler dh) {
        ItemProduct product = new ItemProduct();
        String selectQuery = "SELECT P." + DatabaseHandler.KEY_PRODUCT_ID + ","
                + "P." + DatabaseHandler.KEY_PRODUCT_IMAGE + ","
                + "P." + DatabaseHandler.KEY_PRODUCT_TITLE + ","
                + "P." + DatabaseHandler.KEY_PRODUCT_DESCRIPTION + ","
                + "C." + DatabaseHandler.KEY_CATEGORY_ID + ","
                + "C." + DatabaseHandler.KEY_CATEGORY_NAME + ","
                + "T." + DatabaseHandler.KEY_CITY_ID + ","
                + "T." + DatabaseHandler.KEY_CITY_NAME + ","
                + "S." + DatabaseHandler.KEY_STORE_ID + ","
                + "S." + DatabaseHandler.KEY_STORE_NAME + ","
                + "S." + DatabaseHandler.KEY_STORE_CITY + ","
                + "S." + DatabaseHandler.KEY_STORE_LAT + ","
                + "S." + DatabaseHandler.KEY_STORE_LNG + ","
                + "S." + DatabaseHandler.KEY_STORE_PHONE + ","
                + "S." + DatabaseHandler.KEY_STORE_THUMBNAIL + " FROM "
                + DatabaseHandler.TABLE_PRODUCT + " P, "
                + DatabaseHandler.TABLE_STORE + " S, "
                + DatabaseHandler.TABLE_CITY + " T, "
                + DatabaseHandler.TABLE_CATEGORY + " C WHERE P."
                + DatabaseHandler.KEY_PRODUCT_ID + "= " + idProduct
                + " AND P." + DatabaseHandler.KEY_PRODUCT_CATEGORY
                + " = C." + DatabaseHandler.KEY_CATEGORY_ID
                + " AND P." + DatabaseHandler.KEY_PRODUCT_STORE
                + " = S." + DatabaseHandler.KEY_STORE_ID +
                " AND S." + DatabaseHandler.KEY_STORE_CITY
                + " = T." + DatabaseHandler.KEY_CITY_ID;

        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            Category category = new Category();
            category.setIdCategory(cursor.getInt(4));
            category.setName(cursor.getString(5));

            City city = new City();
            city.setIdCity(cursor.getInt(6));
            city.setName(cursor.getString(7));

            Store store = new Store();
            store.setId(cursor.getInt(8));
            store.setName(cursor.getString(9));
            store.setCity(city);
            store.setLatitude(cursor.getDouble(11));
            store.setLongitude(cursor.getDouble(12));
            store.setPhone(cursor.getString(13));
            store.setThumbnail(cursor.getInt(14));

            product.setCode(cursor.getInt(0));
            product.setImage(cursor.getInt(1));
            product.setTitle(cursor.getString(2));
            product.setDescription(cursor.getString(3));
            product.setStore(store);
            product.setCategory(category);

        }
        try {cursor.close();db.close();
        } catch (Exception e) {}
        db = null;
        cursor = null;
// return store
        return product;
    }

    public ArrayList<ItemProduct> getProductsWhere(String strWhere, String strOrderBy, DatabaseHandler dh) {
       ArrayList<ItemProduct> products = new ArrayList<>();
        String selectQuery = "SELECT P." + DatabaseHandler.KEY_PRODUCT_ID + ","
                + "P." + DatabaseHandler.KEY_PRODUCT_IMAGE + ","
                + "P." + DatabaseHandler.KEY_PRODUCT_TITLE + ","
                + "P." + DatabaseHandler.KEY_PRODUCT_DESCRIPTION + ","
                + "C." + DatabaseHandler.KEY_CATEGORY_ID + ","
                + "C." + DatabaseHandler.KEY_CATEGORY_NAME + ","
                + "T." + DatabaseHandler.KEY_CITY_ID + ","
                + "T." + DatabaseHandler.KEY_CITY_NAME + ","
                + "S." + DatabaseHandler.KEY_STORE_ID + ","
                + "S." + DatabaseHandler.KEY_STORE_NAME + ","
                + "S." + DatabaseHandler.KEY_STORE_CITY + ","
                + "S." + DatabaseHandler.KEY_STORE_LAT + ","
                + "S." + DatabaseHandler.KEY_STORE_LNG + ","
                + "S." + DatabaseHandler.KEY_STORE_PHONE + ","
                + "S." + DatabaseHandler.KEY_STORE_THUMBNAIL + " FROM "
                + DatabaseHandler.TABLE_PRODUCT + " P, "
                + DatabaseHandler.TABLE_STORE + " S, "
                + DatabaseHandler.TABLE_CITY + " T, "
                + DatabaseHandler.TABLE_CATEGORY + " C WHERE P."
                + DatabaseHandler.KEY_PRODUCT_CATEGORY
                + " = C." + DatabaseHandler.KEY_CATEGORY_ID
                + " AND P." + DatabaseHandler.KEY_PRODUCT_STORE
                + " = S." + DatabaseHandler.KEY_STORE_ID +
                " AND S." + DatabaseHandler.KEY_STORE_CITY
                + " = T." + DatabaseHandler.KEY_CITY_ID +
                " ORDER BY " + DatabaseHandler.KEY_PRODUCT_ID + " ASC";

        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        while (cursor.moveToNext()) {
                ItemProduct product = new ItemProduct();
                Category category = new Category();
                category.setIdCategory(cursor.getInt(4));
                category.setName(cursor.getString(5));

                City city = new City();
                city.setIdCity(cursor.getInt(6));
                city.setName(cursor.getString(7));

                Store store = new Store();
                store.setId(cursor.getInt(8));
                store.setName(cursor.getString(9));
                store.setCity(city);
                store.setLatitude(cursor.getDouble(11));
                store.setLongitude(cursor.getDouble(12));
                store.setPhone(cursor.getString(13));
                store.setThumbnail(cursor.getInt(14));

                product.setCode(cursor.getInt(0));
                product.setImage(cursor.getInt(1));
                product.setTitle(cursor.getString(2));
                product.setDescription(cursor.getString(3));
                product.setStore(store);
                product.setCategory(category);
                products.add(product);
        }
            ItemProduct product = new ItemProduct();
            product.setDescription("Ola k ase");
            product.setImage(1);
            product.setTitle("asios");
            product.setCode(1);

            Category category = new Category();
            category.setName("TECHNOLOGY");
            category.setIdCategory(1);

            Store store = new Store();
            store.setId(1);
            store.setName("Bestbuy");
            store.setThumbnail(1);
            store.setPhone("45455656");
            store.setLongitude(545.4545);
            store.setLatitude(455.4545);

            City city = new City();
            city.setIdCity(1);
            city.setName("ZPN");

            store.setCity(city);

            product.setStore(store);
            product.setCategory(category);

            products.add(product);


        try {cursor.close();db.close();
        } catch (Exception e) {}
        db = null;
        cursor = null;
// return store
        return products;
    }
}
