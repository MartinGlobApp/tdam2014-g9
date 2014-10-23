package com.tdam2014.grupo09.BaseDeDatos;

import com.tdam2014.grupo09.Activity_Principal;

import android.database.sqlite.SQLiteDatabase;

public class BD {
	
    public static String NAME_DATA_BASE = "FlikiFlickeroBD";
    public static int VERSION_NUMBER = 1;
	static SQLiteDatabase db;

	public static SQLiteDatabase GetDB(){
		if(db == null){
            db = new SQLiteManager(Activity_Principal.contexto, NAME_DATA_BASE, null, VERSION_NUMBER).getWritableDatabase();
        }
		return db;
	}

    public static void setBase(SQLiteDatabase db){
        BD.db = db;
    }
}
