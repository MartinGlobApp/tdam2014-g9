package Clases;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import WebServices.ComunicacionFlickr;

public class Directorio {

	private String id;
	private String primary;
	private String titulo;
	private int cantidadFotos;
	private List<Imagen> imagenes;
	
	public Directorio(JSONObject objDirectorio) throws JSONException{
		id = objDirectorio.optString("id");
		primary = objDirectorio.optString("primary");
		titulo = objDirectorio.optString("title");
		cantidadFotos = objDirectorio.optInt("photos");
		
		JSONArray arrayImages = ComunicacionFlickr.getFotos(id);
		
		imagenes = new ArrayList<Imagen>();
		for (int i = 0; i < arrayImages.length(); i++) {
			imagenes.add(new Imagen(arrayImages.getJSONObject(i)));
		}
	}

	public String getId() {
		return id;
	}
	
	public String getPrimary() {
		return primary;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public int getCantidadFotos() {
		return cantidadFotos;
	}
	
	public List<Imagen> getImagenes() {
		return imagenes;
	}
}
