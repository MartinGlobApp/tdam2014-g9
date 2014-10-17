package WebServices;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ComunicacionFlickr {
	
	public static JSONArray getDirectorios(String user_id) throws JSONException{
		
		JSONObject objResponse = new JSONObject();// Obtener de la web
		JSONArray arrayDirectorios = objResponse.getJSONArray("photoset");
		return arrayDirectorios;
		
	}
	
	public static JSONArray getFotos(String photoset_id) throws JSONException{
		
		JSONObject objResponse = new JSONObject();// Obtener de la web
		JSONArray arrayFotos = objResponse.getJSONArray("photo");
		return arrayFotos;
		
	}
	
	public static JSONArray getComentarios(String photo_id) throws JSONException{

		JSONObject objResponse = new JSONObject();// Obtener de la web
		JSONArray arrayComentarios = objResponse.getJSONArray("comment");
		return arrayComentarios;
	}
}
