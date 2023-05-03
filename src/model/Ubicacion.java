package model;

public class Ubicacion {
	private String nombre;
	private String provincia;
	
	public Ubicacion (String nombre, String provincia) {
		this.nombre = nombre;
		this.provincia = provincia;
	}

	public String getNombre() {
		return nombre;
	}

	public String getProvincia() {
		return provincia;
	}
}