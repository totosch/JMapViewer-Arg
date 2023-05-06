package model.grafo;

import java.util.ArrayList;
import java.util.HashMap;

import model.Arista;


public class ArbolGeneradorMinimo {
	private Grafo arbol;
	private HashMap<String, Double> costoPorArista;
	private int tamanio;
	
	public ArbolGeneradorMinimo(HashMap<String, Double> costoPorArista, int tamanio) {
		this.arbol = new Grafo(tamanio);
		this.costoPorArista = costoPorArista;
		this.tamanio = tamanio;
	}
	
	public ArbolInformacion generar() {
		double menorCostoVecinosActuales = Double.MAX_VALUE;
		int indiceVerticeMenorCosto = -1;
		ArrayList<Arista> aristasFinales = new ArrayList<Arista>(tamanio - 1);
		
		for (int i = 0; i < tamanio - 1; i++) { 
			menorCostoVecinosActuales = Double.MAX_VALUE;
			for (int j = 0; j < tamanio; j++) {
				boolean existeArista = arbol.existeArista(i, j);
				if (i == j || existeArista) {
					continue;
				}
				
				double costoActual = obtenerCostoPorArista(i, j);
				if (costoActual < menorCostoVecinosActuales) {
					menorCostoVecinosActuales = costoActual;
					indiceVerticeMenorCosto = j;
				}
			}
			
			arbol.agregarArista(i, indiceVerticeMenorCosto);
			aristasFinales.add(new Arista(i, indiceVerticeMenorCosto));
			
			System.out.println("en la posicion: " + i + "/n");
			System.out.println(arbol.toString());
		}
 
		return new ArbolInformacion(aristasFinales, arbol);
	}
	
	public double obtenerCostoPorArista(int i, int j) {
		Double arista = costoPorArista.get(Integer.toString(i) + Integer.toString(j));
		
		if (arista == null) {
			return (double) costoPorArista.get(Integer.toString(j) + Integer.toString(i));
		}
		
		return (double) arista;
	}
}