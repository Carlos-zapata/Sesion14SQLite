package com.iteso.caflores.splashscreen.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by Carlos Flores on 16/10/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    // Table names
    public static final String TABLE_STORE = "store";
    public static final String TABLE_PRODUCT = "product";
    public static final String TABLE_CITY = "city";
    public static final String TABLE_CATEGORY = "category";

    // Columns store
    public static final String KEY_STORE_ID = "idStore";
    public static final String KEY_STORE_NAME = "name";
    public static final String KEY_STORE_PHONE = "phone";
    public static final String KEY_STORE_CITY = "idCity";
    public static final String KEY_STORE_THUMBNAIL = "thumbnail";
    public static final String KEY_STORE_LAT = "latitude";
    public static final String KEY_STORE_LNG = "longitude";

    // Columns city
    public static final String KEY_CITY_ID = "idCity";
    public static final String KEY_CITY_NAME = "name";

    // Columns category
    public static final String KEY_CATEGORY_ID = "idCategory";
    public static final String KEY_CATEGORY_NAME = "name";

    // Columns roduct
    public static final String KEY_PRODUCT_ID = "idProduct";
    public static final String KEY_PRODUCT_TITLE = "name";
    public static final String KEY_PRODUCT_IMAGE = "image";
    public static final String KEY_PRODUCT_DESCRIPTION = "description";
    public static final String KEY_PRODUCT_CATEGORY = "idCategory";
    public static final String KEY_PRODUCT_STORE = "idStore";


    private static DatabaseHandler databaseHandler;
    private static final String DATABASE_NAME = "test.db";
    private static final int DATABASE_VERSION = 1;

    private DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DatabaseHandler getInstance(Context context){
        if(databaseHandler == null)
            databaseHandler = new DatabaseHandler(context);
        return databaseHandler;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CITY_TABLE = "CREATE TABLE " + TABLE_CITY + "("
                + KEY_CITY_ID + " INTEGER PRIMARY KEY,"
                + KEY_CITY_NAME + " TEXT)";
        sqLiteDatabase.execSQL(CREATE_CITY_TABLE);

        String CREATE_CATEGORY_TABLE = "CREATE TABLE " + TABLE_CATEGORY + "("
                + KEY_CATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_CATEGORY_NAME + " TEXT)";
        sqLiteDatabase.execSQL(CREATE_CATEGORY_TABLE);

        String CREATE_STORE_TABLE = "CREATE TABLE " + TABLE_STORE + "("
                + KEY_STORE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_STORE_NAME + " TEXT,"
                + KEY_STORE_PHONE + " TEXT,"
                + KEY_STORE_CITY + " INTEGER,"
                + KEY_STORE_THUMBNAIL + " INTEGER,"
                + KEY_STORE_LAT + " DOUBLE,"
                + KEY_STORE_LNG + " DOUBLE)";
        sqLiteDatabase.execSQL(CREATE_STORE_TABLE);

        String CREATE_PRODUCT_TABLE = "CREATE TABLE " + TABLE_PRODUCT + "("
                + KEY_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_PRODUCT_TITLE + " TEXT,"
                + KEY_PRODUCT_IMAGE + " INTEGER,"
                + KEY_PRODUCT_DESCRIPTION + " TEXT,"
                + KEY_PRODUCT_CATEGORY + " INTEGER,"
                + KEY_PRODUCT_STORE + " INTEGER)";
        sqLiteDatabase.execSQL(CREATE_PRODUCT_TABLE);

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_CATEGORY + " (" + KEY_CATEGORY_NAME + ") VALUES ('TECHNOLOGY')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_CATEGORY + " (" + KEY_CATEGORY_NAME + ") VALUES ('HOME')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_CATEGORY + " (" + KEY_CATEGORY_NAME + ") VALUES ('ELECTRONICS')");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_CITY + " (" + KEY_CITY_ID + "," + KEY_CITY_NAME + ") VALUES (1, 'El Salto')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_CITY + " (" + KEY_CITY_ID + "," + KEY_CITY_NAME + ") VALUES (2, 'Guadalajara')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_CITY + " (" + KEY_CITY_ID + "," + KEY_CITY_NAME + ") VALUES (3, 'Ixtlahuacán de los Membrillos')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_CITY + " (" + KEY_CITY_ID + "," + KEY_CITY_NAME + ") VALUES (4, 'Juanacatlán')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_CITY + " (" + KEY_CITY_ID + "," + KEY_CITY_NAME + ") VALUES (5, 'San Pedro Tlaquepaque')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_CITY + " (" + KEY_CITY_ID + "," + KEY_CITY_NAME + ") VALUES (6, 'Tlajomulco')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_CITY + " (" + KEY_CITY_ID + "," + KEY_CITY_NAME + ") VALUES (7, 'Tonalá')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_CITY + " (" + KEY_CITY_ID + "," + KEY_CITY_NAME + ") VALUES (8, 'Zapopan')");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_STORE + " (" + KEY_STORE_NAME + "," + KEY_STORE_PHONE + ","
                + KEY_STORE_CITY + "," + KEY_STORE_THUMBNAIL + "," + KEY_STORE_LAT + "," + KEY_STORE_LNG
                + ") VALUES ('BESTBUY', '01 800 237 8289', 2, 0, 20.6489713, -103.4207757)");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_PRODUCT + " (" + KEY_PRODUCT_TITLE + "," + KEY_PRODUCT_IMAGE +
                                "," + KEY_PRODUCT_STORE + "," + KEY_PRODUCT_CATEGORY + "," + KEY_PRODUCT_DESCRIPTION +
                                ") VALUES ('MACBOOK PRO', 1, 1, 1, 'Computadora de gama alta')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_PRODUCT + " (" + KEY_PRODUCT_TITLE + "," + KEY_PRODUCT_IMAGE +
                "," + KEY_PRODUCT_STORE + "," + KEY_PRODUCT_CATEGORY + "," + KEY_PRODUCT_DESCRIPTION +
                ") VALUES ('ALIENWARE PRO', 2, 1, 2, 'Computadora de gama media')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_PRODUCT + " (" + KEY_PRODUCT_TITLE + "," + KEY_PRODUCT_IMAGE +
                "," + KEY_PRODUCT_STORE + "," + KEY_PRODUCT_CATEGORY + "," + KEY_PRODUCT_DESCRIPTION +
                ") VALUES ('ALIENWARE', 2, 1, 3, 'Computadora de gama baja')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        switch(oldVersion){
            case 1: upgradeV2(sqLiteDatabase);
            case 2: upgradeV3(sqLiteDatabase);
                break;
        }
    }

    private void upgradeV2(SQLiteDatabase sqLiteDatabase){
        String createTable = "CREATE TABLE Career (id INTEGER NOT NULL, " +
                " nombre TEXT)";
        sqLiteDatabase.execSQL(createTable);
    }

    private void upgradeV3(SQLiteDatabase sqLiteDatabase){
        String alterTable = "ALTER TABLE Student ADD COLUMN idCarrera INTEGER";
        sqLiteDatabase.execSQL(alterTable);
    }
}
