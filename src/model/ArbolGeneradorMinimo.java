package model;

import java.util.HashMap;


public class ArbolGeneradorMinimo {
	private Grafo grafo;
	private Grafo arbol;
	private HashMap<String, Double> costoPorArista;
	
	public ArbolGeneradorMinimo(Grafo grafo, HashMap<String, Double> costoPorArista) {
		this.grafo = grafo;
		this.arbol = new Grafo(grafo.tamanio());
		this.costoPorArista = costoPorArista;
	}
	
	public Grafo generar() {
		double menorCostoVecinosActuales = Double.MAX_VALUE;
		int indiceVerticeMenorCosto = -1;
		
		for (int i = 0; i < grafo.tamanio() - 1; i++) { 
			menorCostoVecinosActuales = Double.MAX_VALUE;
			for (int j = 0; j < grafo.tamanio(); j++) {
				if (i == j) {
					continue;
				}
				
				boolean existeArista = arbol.existeArista(i, j);
				System.out.println(Integer.toString(i) + Integer.toString(j));
				double costoActual = obtenerCostoPorArista(i, j);
				if (!existeArista && costoActual < menorCostoVecinosActuales) {
					menorCostoVecinosActuales = costoActual;
					indiceVerticeMenorCosto = j;
				}
			}
			
			arbol.agregarArista(i, indiceVerticeMenorCosto);
		}
 
		return arbol;
	}
	
	private double obtenerCostoPorArista(int i, int j) {
		Double arista = costoPorArista.get(Integer.toString(i) + Integer.toString(j));
		
		if (arista == null) {
			return (double) costoPorArista.get(Integer.toString(j) + Integer.toString(i));
		}
		
		return (double) arista;
	}
}