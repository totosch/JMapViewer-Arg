package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Model {
	private ArrayList<Coordenada> coordenadas;
	private Grafo conexiones;
	private GeneradorDeCostos generadorDeCostos;
	private int maximaCantidadDeUbicaciones = 10;
	private HashMap<String, Double> costoPorArista;
	
	public Model () {
		this.coordenadas = new ArrayList<Coordenada>();
		this.conexiones = new Grafo(maximaCantidadDeUbicaciones);
		this.costoPorArista = new HashMap<String, Double>();
	}
	
	public void agregarGeneradorDeCostos(GeneradorDeCostos generadorDeCostos) {
		this.generadorDeCostos = generadorDeCostos;
	}
	
	public void agregarUbicacion(String identificador, String provincia, double latitud, double longitud) throws Exception {
		if (coordenadas.size() == maximaCantidadDeUbicaciones) {
			throw new Exception("No puedes agregar mas ubicaciones");
		}
		
		Coordenada nuevaCoordenada = new Coordenada(coordenadas.size(), latitud, longitud);
		
		for (Coordenada coordenada: coordenadas) {
			double distancia = nuevaCoordenada.obtenerDistancia(nuevaCoordenada);
			double costo = generadorDeCostos.generarCostoPorDistancia(distancia, false);
			
			Arista nuevaArista = new Arista(nuevaCoordenada.getIdentificador(), coordenada.getIdentificador());
			conexiones.agregarArista(nuevaArista.getI(), nuevaArista.getJ());
			costoPorArista.put(Integer.toString(nuevaArista.getI()) + Integer.toString(nuevaArista.getJ()), costo);
		}
		
		coordenadas.add(nuevaCoordenada);
	}
}