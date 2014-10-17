package Clases;

import java.util.List;

public class Imagen {
	
	public static String TAMA�O_CUADRADO_PEQUE�O = "q";
	public static String TAMA�O_PEQUE�O = "m";
	public static String TAMA�O_ORIGINAL = "o";
	
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
	
	public String getURL(String tama�o){
		return "https://farm" + getFarm() + ".staticflickr.com/" + getServer()+ "/" + getId() + "_" + getSecret() + "_" + tama�o + ".jpg";
	}

}
