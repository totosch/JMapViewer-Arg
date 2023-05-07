package model;

import java.util.ArrayList;

public class RespuestaPlanificacion {
	private double costoTotal;
	private ArrayList<Arista> posiciones;
	
	public RespuestaPlanificacion(double costoTotal, ArrayList<Arista> posiciones) {
		this.costoTotal = costoTotal;
		this.posiciones = posiciones;
	}
	
	public double getCostoTotal() {
		return this.costoTotal;
	}
	
	public ArrayList<Arista> getPosiciones() {
		return this.posiciones;
	}
}
