package Clases;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import WebServices.ComunicacionFlickr;

public class Cuenta {
	
	private String user_id;
	private List<Directorio> directorios;
	
	public Cuenta(String user_id) throws JSONException{
		this.user_id = user_id;
		
		JSONArray arrayDirectorio = ComunicacionFlickr.getDirectorios(user_id);		
		directorios = new ArrayList<Directorio>();
		for (int i = 0; i < arrayDirectorio.length(); i++) {
			directorios.add(new Directorio(arrayDirectorio.getJSONObject(i)));
		}
	}
	
	public String getUser_id() {
		return user_id;
	}
	
	public List<Directorio> getDirectorios() {
		return directorios;
	}
}
