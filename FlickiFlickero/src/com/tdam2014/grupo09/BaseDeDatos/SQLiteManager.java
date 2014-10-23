package com.tdam2014.grupo09.BaseDeDatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteManager extends SQLiteOpenHelper {

    public static final String TABLE_ACCOUNT_Name = "Accounts";
    public static final String TABLE_ACCOUNT_COL_id = "AccountId";
    public static final String TABLE_ACCOUNT_COL_email = "Email";
    public static final String TABLE_ACCOUNT_COL_type = "Type";
    public static final String TABLE_ACCOUNT_COL_number = "Number";
    public static final String TABLE_ACCOUNT_COL_pass = "Pass";
    public static final String TABLE_ACCOUNT_COL_collegeId = "CollegeId";
    public static final String TABLE_ACCOUNT_COL_collegeName = "CollegeName";
    public static final String TABLE_ACCOUNT_COL_userId = "UserId";

    String createAccountsTable = "CREATE TABLE " + TABLE_ACCOUNT_Name +
            " (" + TABLE_ACCOUNT_COL_id + " INTEGER PRIMARY KEY AUTOINCREMENT" +
            ", " + TABLE_ACCOUNT_COL_email + " TEXT" +
            ", " + TABLE_ACCOUNT_COL_type + " TEXT" +
            ", " + TABLE_ACCOUNT_COL_number + " TEXT" +
            ", " + TABLE_ACCOUNT_COL_pass + " TEXT" +
            ", " + TABLE_ACCOUNT_COL_collegeId + " INTEGER" +
            ", " + TABLE_ACCOUNT_COL_userId + " INTEGER" +
            ", " + TABLE_ACCOUNT_COL_collegeName + " TEXT" +
            ")";

    public SQLiteManager(Context contexto, String nombre,
                               CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }    
 
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createAccountsTable);
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {}
}