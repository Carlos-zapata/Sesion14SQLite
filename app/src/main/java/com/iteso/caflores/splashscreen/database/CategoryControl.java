package com.iteso.caflores.splashscreen.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.iteso.caflores.splashscreen.beans.Category;
import com.iteso.caflores.splashscreen.beans.City;
import com.iteso.caflores.splashscreen.beans.Store;

import java.util.ArrayList;

/**
 * Created by Carlos Flores on 16/10/2017.
 */

public class CategoryControl {


    public long addCategory(Category category, DatabaseHandler dh) {
        long inserted = 0;
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHandler.KEY_CATEGORY_NAME, category.getName());
        // Inserting Row
        inserted = db.insert(DatabaseHandler.TABLE_CATEGORY, null, values);
        // Closing database connection
        try {db.close();} catch (Exception e) {}
        db = null; values = null;
        return inserted;
    }

    public void deleteCategory(int idCategory, DatabaseHandler dh) {
        SQLiteDatabase db = dh.getWritableDatabase();
        db.delete(DatabaseHandler.TABLE_CATEGORY, DatabaseHandler.KEY_CATEGORY_ID
                + " = ?", new String[] { String.valueOf(idCategory) });
        try {
            db.close();
        } catch (Exception e) {
        }
        db = null;
    }

    public int updateCategory(Category category, DatabaseHandler dh) {
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHandler.KEY_CATEGORY_NAME, category.getName());

        // Updating row
        int count = db.update(DatabaseHandler.TABLE_CATEGORY, values,
                DatabaseHandler.KEY_CATEGORY_ID + " = ?",
                new String[] { String.valueOf(category.getIdCategory()) });
        try { db.close();} catch (Exception e) {}
        db = null;
        return count;
    }

    public Category getCategoryById(int idCategory, DatabaseHandler dh) {
        Category category = new Category();
        String selectQuery = "SELECT C." + DatabaseHandler.KEY_CATEGORY_ID + ","
                + "C." + DatabaseHandler.KEY_CATEGORY_NAME + " FROM "
                + DatabaseHandler.TABLE_CATEGORY + " C WHERE C."
                + DatabaseHandler.KEY_CATEGORY_ID + "= " + idCategory;

        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            category.setIdCategory(cursor.getInt(0));
            category.setName(cursor.getString(1));
        }
        try {cursor.close();db.close();
        } catch (Exception e) {}
        db = null;
        cursor = null;
// return store
        return category;
    }

    public ArrayList<Category> getAllCategories(DatabaseHandler dh){
        ArrayList<Category> categories = new ArrayList<>();
        String selectQuery = "SELECT C." + DatabaseHandler.KEY_CATEGORY_ID + ","
                + "C." + DatabaseHandler.KEY_CATEGORY_NAME + " FROM "
                + DatabaseHandler.TABLE_CATEGORY;

        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        while (cursor.moveToNext()) {
                Category category = new Category();
                category.setIdCategory(cursor.getInt(0));
                category.setName(cursor.getString(1));
                categories.add(category);
        }
        try {cursor.close();db.close();
        } catch (Exception e) {}
        db = null;
        cursor = null;

        return  categories;
    }
}
