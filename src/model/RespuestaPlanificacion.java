package model;

import java.util.ArrayList;

public class RespuestaPlanificacion {
	private double costoTotal;
	private ArrayList<Integer> posiciones;
	
	public RespuestaPlanificacion(double costoTotal, ArrayList<Integer> posiciones) {
		this.costoTotal = costoTotal;
	}
	
	public double getCostoTotal() {
		return this.costoTotal;
	}
	
	public ArrayList<Integer> getPosiciones() {
		return this.posiciones;
	}
}
