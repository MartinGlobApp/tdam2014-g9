package Clases;

import java.util.List;

public class Imagen {
	
	public static String TAMAÑO_CUADRADO_PEQUEÑO = "q";
	public static String TAMAÑO_PEQUEÑO = "m";
	public static String TAMAÑO_ORIGINAL = "o";
	
	private String id;
	private String secret;
	private String server;
	private int farm;
	private String title;
	private List<Comentarios> comentarios;
	
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
	
	public List<Comentarios> getComentarios() {
		return comentarios;
	}
	
	public String getURL(String tamaño){
		return "https://farm" + getFarm() + ".staticflickr.com/" + getServer()+ "/" + getId() + "_" + getSecret() + "_" + tamaño + ".jpg";
	}

}
