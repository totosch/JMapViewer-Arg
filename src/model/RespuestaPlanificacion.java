package model;

import java.util.ArrayList;

public class RespuestaPlanificacion {
	private double costoTotal;
	private ArrayList<Arista> aristas;
	
	public RespuestaPlanificacion(double costoTotal, ArrayList<Arista> aristas) {
		this.costoTotal = costoTotal;
	}
	
	public double getCostoTotal() {
		return this.costoTotal;
	}
	
	public ArrayList<Arista> getAristas() {
		return this.aristas;
	}
}
