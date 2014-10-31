package com.tdam2014.grupo09.BaseDeDatos;

import java.io.ObjectOutputStream.PutField;
import java.util.ArrayList;

import org.json.JSONArray;

import android.content.ContentValues;
import android.database.Cursor;

import com.tdam2014.grupo09.Clases.Comentario;
import com.tdam2014.grupo09.Clases.Directorio;
import com.tdam2014.grupo09.Clases.Imagen;

public class ControllerBD {

    public static void insertarDirectorio(Directorio directorio){
            ContentValues valuesTransaccion = new ContentValues();
            valuesTransaccion.put("id", directorio.getId());
            valuesTransaccion.put("_primary", directorio.getPrimary());
            valuesTransaccion.put("titulo", directorio.getTitulo());
            valuesTransaccion.put("cantidad", directorio.getCantidadFotos());
            BD.GetDB().insert("Directorios", null, valuesTransaccion);
    }

    public static void insertarImagenes(Imagen imagen){
        ContentValues valuesTransaccion = new ContentValues();
        valuesTransaccion.put("id", imagen.getId());
        valuesTransaccion.put("directorioId", imagen.getDirectorioId());
        valuesTransaccion.put("secret", imagen.getSecret());
        valuesTransaccion.put("server", imagen.getServer());
        valuesTransaccion.put("farm", imagen.getFarm());
        valuesTransaccion.put("title", imagen.getTitulo());
        valuesTransaccion.put("path", imagen.getPath());
        BD.GetDB().insert("Imagenes", null, valuesTransaccion);
    }

    public static void insertarComentarios(Comentario comentario){
        ContentValues valuesTransaccion = new ContentValues();
        valuesTransaccion.put("id", comentario.getId());
        valuesTransaccion.put("imagenId", comentario.getImagenId());
        valuesTransaccion.put("author", comentario.getAuthor());
        valuesTransaccion.put("authorname", comentario.getAuthorname());
        valuesTransaccion.put("comment", comentario.getComment());
        BD.GetDB().insert("Comentarios", null, valuesTransaccion);
    }
    
	public static ArrayList<Directorio> getDirectorios(){
		ArrayList<Directorio> result = new ArrayList<Directorio>();
		Cursor cursor = BD.GetDB().rawQuery("SELECT * FROM Directorios" , null);
	    if (cursor.moveToFirst()){
	        do {
	        	result.add(new Directorio(cursor));
	        }while(cursor.moveToNext());
	    
		}
	    return result;
	}
	
	public static ArrayList<Imagen> getImagenes(String photoset_id, Boolean ordenado) {
		ArrayList<Imagen> result = new ArrayList<Imagen>();
		Cursor cursor = BD.GetDB().rawQuery("SELECT * FROM Imagenes WHERE directorioId = '"+ photoset_id +"'" +
		((ordenado)? "ORDER BY title" : ""), null);
	    if (cursor.moveToFirst()){
	        do {
	        	result.add(new Imagen(cursor));
	        }while(cursor.moveToNext());
	    
		}
	    return result;
		
	}
	
	public static ArrayList<Comentario> getComentarios(String photo_id) {
		ArrayList<Comentario> result = new ArrayList<Comentario>();
		Cursor cursor = BD.GetDB().rawQuery("SELECT * FROM Comentarios WHERE imagenId = '"+ photo_id +"'", null);
	    if (cursor.moveToFirst()){
	        do {
	        	result.add(new Comentario(cursor));
	        }while(cursor.moveToNext());
	    
		}
	    return result;
	}
}
