package Clases;

import java.util.List;

public class Directorio {

	private String id;
	private String primary;
	private String titulo;
	private int cantidadFotos;
	private List<Imagen> imagenes;
	
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
