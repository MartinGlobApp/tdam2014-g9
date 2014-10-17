package Clases;

import java.util.List;

import org.json.JSONObject;

public class Directorio {

	private String id;
	private String primary;
	private String titulo;
	private int cantidadFotos;
	private List<Imagen> imagenes;
	
	public Directorio(JSONObject objDirectorio) {
		id = objDirectorio.optString("id");
		primary = objDirectorio.optString("primary");
		titulo = objDirectorio.optString("title");
		cantidadFotos = objDirectorio.optInt("photos");
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
