package com.tdam2014.grupo09.BaseDeDatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteManager extends SQLiteOpenHelper {

    String createTableDirectorios = "CREATE TABLE Directorios" +
            " ( id TEXT PRIMARY KEY" +
            ", _primary TEXT" +
            ", titulo TEXT" +
            ", cantidad INTEGER" +
            ")";

    String createTableImagenes = "CREATE TABLE Imagenes" +
            " ( id TEXT PRIMARY KEY" +
            ", directorioId TEXT" +
            ", secret TEXT" +
            ", server TEXT" +
            ", farm INTEGER" +
            ", title TEXT" +
            ", path TEXT" +
            ")";

    String createTableComentaris = "CREATE TABLE Comentarios" +
            " ( id TEXT PRIMARY KEY" +
            ", imagenId TEXT" +
            ", author TEXT" +
            ", authorname TEXT" +
            ", comment INTEGER" +
            ")";
    
    public SQLiteManager(Context contexto, String nombre,
                               CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }    
 
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTableImagenes);
        db.execSQL(createTableComentaris);
        db.execSQL(createTableDirectorios);
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {}
}