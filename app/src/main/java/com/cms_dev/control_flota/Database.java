package com.cms_dev.control_flota;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    private static final String Database_Name = "Control_flota.db";
    private static final String Table_name = "conductores";
    private static final String Col_1 = "ID";
    private static final String Col_2 = "Conductor";
    private static final String Col_3 = "Vehiculo";

    public Database(Context context ) {
        super(context, Database_Name,null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ Table_name + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, Conductor TEXT, Vehiculo TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ Table_name);
        onCreate(db);
    }
    public boolean insertConductor(String conductor, String vehiculo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Conductor", conductor);
        contentValues.put("Vehiculo", vehiculo);
        long result = db.insert("conductores", null, contentValues);
        return result != -1;
    }

    public ArrayList<String> getAllConductor() {
        ArrayList<String> conductores = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + Table_name, null);
        if (res.moveToFirst()) {
            do {
                String conductorConVehiculo = res.getString(1) + " - " + res.getString(2);
                conductores.add(conductorConVehiculo);
            } while (res.moveToNext());
        }
        res.close();
        return conductores;
    }

    public void deleteConductor(String conductor){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Table_name,"Conductor = ?", new String[]{conductor});
    }
    public boolean updateConductor(String oldConductor, String nuevoConductor, String nuevoVehiculo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Conductor", nuevoConductor);
        contentValues.put("Vehiculo", nuevoVehiculo);
        int rowsAffected = db.update(Table_name, contentValues, "Conductor = ?", new String[]{oldConductor});
        return rowsAffected > 0;
    }



}
