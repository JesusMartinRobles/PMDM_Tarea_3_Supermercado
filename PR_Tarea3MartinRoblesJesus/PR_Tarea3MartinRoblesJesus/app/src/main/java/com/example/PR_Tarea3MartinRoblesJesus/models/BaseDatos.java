package com.example.PR_Tarea3MartinRoblesJesus.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;

public class BaseDatos extends SQLiteOpenHelper {
    private static final String NameBD = "DBConsumer";
    private static final int VersionBD = 1;

    public BaseDatos(Context context) {
        super(context, NameBD, null, VersionBD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Productos (_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,name VARCHAR(30), ingredientes VARCHAR(500),URL  VARCHAR(500), gramos INTEGER, " +
                " price REAL," +
                "isAvilable INTEGER)");

       ;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void insertProducto(Producto producto) {
        SQLiteDatabase db = getWritableDatabase();

        if (db.isOpen()) {
            ContentValues valores = new ContentValues();
            valores.put("name", producto.getName());
            valores.put("price", producto.getPrice());
            valores.put("ingredientes", producto.getIngredient());
            valores.put("gramos", producto.getWeight());
            valores.put("URL", producto.getURL());
            valores.put("isAvilable", producto.isAvilable());

            db.insert("Productos", null, valores);
            db.close();
        }

    }
    public void actualizarProducto(Producto producto) {
        SQLiteDatabase db = getWritableDatabase();

        String[] args = new String[]{String.valueOf(producto.getId())};

        if (db.isOpen()) {
            ContentValues valores = new ContentValues();
            valores.put("name", producto.getName());
            valores.put("price", producto.getPrice());
            valores.put("ingredientes", producto.getIngredient());
            valores.put("gramos", producto.getWeight());
            valores.put("URL", producto.getURL());
            valores.put("isAvilable", producto.isAvilable());

            //Operaciones
            db.update("Productos", valores, "_id = ?", args);
            db.close();
        }
    }
    public void deleteProducto(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        String[] args = new String[]{String.valueOf(id)};

        if (db.isOpen()) {
            //Operaciones
            db.delete("Productos", "_id = ?", args);
            db.close();
        }
    }
    public Cursor getProductos(boolean isAvilable) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = null;
        if (db.isOpen()) {
            //Operaciones
            if (isAvilable){
                c = db.rawQuery("SELECT * FROM Productos where isAvilable = 1", null);
        }else{
            c = db.rawQuery("SELECT * FROM Productos", null);
        }}

        return c;

    }
    public Cursor getProductId(int  id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = null;
        if (db.isOpen()) {
            //Operaciones

            c = db.rawQuery("SELECT * FROM Productos where _id ="+ id, null);
        }

        return c;

    }
    public int countProducts() {
        int numFilas = (int) DatabaseUtils.queryNumEntries(this.getReadableDatabase(), "Productos");
        return numFilas;
    }
}