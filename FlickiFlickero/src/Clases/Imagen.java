package Clases;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import WebServices.ComunicacionFlickr;

public class Imagen {
	
	public static String TAMAÑO_CUADRADO_PEQUEÑO = "q";
	public static String TAMAÑO_PEQUEÑO = "m";
	public static String TAMAÑO_ORIGINAL = "o";
	
	private String id;
	private String secret;
	private String server;
	private int farm;
	private String title;
	private List<Comentario> comentarios;
	
	public Imagen(JSONObject objImagen) throws JSONException {
		id = objImagen.optString("id");
		secret = objImagen.optString("secret");
		server = objImagen.optString("server");
		farm = objImagen.optInt("farm");
		title = objImagen.optString("title");
		
		JSONArray arrayComentarios = ComunicacionFlickr.getComentarios(id);		
		comentarios = new ArrayList<Comentario>();
		for (int i = 0; i < arrayComentarios.length(); i++) {
			comentarios.add(new Comentario(arrayComentarios.getJSONObject(i)));
		}
	}
	
	public String getId() {
		return id;
	}
	
	public String getSecret() {
		return secret;
	}
	
	public String getServer() {
		return server;
	}
	
	public int getFarm() {
		return farm;
	}
	
	public String getTitle() {
		return title;
	}
	
	public List<Comentario> getComentarios() {
		return comentarios;
	}
	
	public String getURL(String tamaño){
		return "https://farm" + getFarm() + ".staticflickr.com/" + getServer()+ "/" + getId() + "_" + getSecret() + "_" + tamaño + ".jpg";
	}

}
