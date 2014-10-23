package com.tdam2014.grupo09.BaseDeDatos;

import android.content.ContentValues;
import android.database.Cursor;
import java.util.ArrayList;

import com.tdam2014.grupo09.Clases.Directorio;

public class ControllerBD {

    public static void insertarDirectorio(Directorio directorio){
            ContentValues valuesTransaccion = new ContentValues();/*
            valuesTransaccion.put(SQLiteManager.TABLE_ACCOUNT_COL_email, account.get_email());
            valuesTransaccion.put(SQLiteManager.TABLE_ACCOUNT_COL_type, account.get_type());
            valuesTransaccion.put(SQLiteManager.TABLE_ACCOUNT_COL_number, account.get_number());
            valuesTransaccion.put(SQLiteManager.TABLE_ACCOUNT_COL_pass, account.get_pass());
            valuesTransaccion.put(SQLiteManager.TABLE_ACCOUNT_COL_collegeId, account.get_college().get_idCollege());
            valuesTransaccion.put(SQLiteManager.TABLE_ACCOUNT_COL_collegeName, account.get_college().get_name());
            valuesTransaccion.put(SQLiteManager.TABLE_ACCOUNT_COL_userId, account.getUserId());
            BD.GetDB().insert(SQLiteManager.TABLE_ACCOUNT_Name, null, valuesTransaccion);*/
    }

/*    public static Account getAccount(int accountId){
       Account account = null;
        try{
            Cursor cursor = BD.GetDB().rawQuery("SELECT * FROM " + SQLiteManager.TABLE_ACCOUNT_Name +
                    " WHERE " + SQLiteManager.TABLE_ACCOUNT_COL_id + " = " +  accountId ,null);
            if (cursor.moveToFirst()){
                do {
                    account = new Account(cursor);
                }while(cursor.moveToNext());
            }
        }catch (Exception ex){ }
        return account;
    }*/
}
