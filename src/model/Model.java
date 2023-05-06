package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import model.grafo.ArbolGeneradorMinimo;
import model.grafo.ArbolInformacion;
import model.grafo.Grafo;

public class Model {
	private ArrayList<Ubicacion> ubicaciones;
	private Grafo conexiones;
	private GeneradorDeCostos generadorDeCostos;
	private int maximaCantidadDeUbicaciones = 10;
	private HashMap<String, Double> costoPorArista;
	
	public Model () {
		this.ubicaciones = new ArrayList<Ubicacion>();
		this.conexiones = new Grafo(maximaCantidadDeUbicaciones);
		this.costoPorArista = new HashMap<String, Double>();
	}
	
	protected ArrayList<Ubicacion> getUbicaciones() {
		return ubicaciones;
	}
	
	public void agregarGeneradorDeCostos(GeneradorDeCostos generadorDeCostos) {
		this.generadorDeCostos = generadorDeCostos;
	}
	
	public void agregarUbicacion(String identificador, String provincia, double latitud, double longitud) throws Exception {
		if (ubicaciones.size() == maximaCantidadDeUbicaciones) {
			throw new Exception("No puedes agregar mas ubicaciones");
		}
		
		Ubicacion nuevaUbicacion = new Ubicacion(identificador, provincia, latitud, longitud);
		
		for (int i = 0; i < ubicaciones.size(); i++) {
			Ubicacion ubicacionActual = ubicaciones.get(i);
			double distancia = DistanceCalculator.distance(nuevaUbicacion.getLatitud(), nuevaUbicacion.getLongitud(), ubicacionActual.getLatitud(), ubicacionActual.getLongitud());
			boolean mismaProvincia = provincia.equals(ubicacionActual.getProvincia());
					
			double costo = generadorDeCostos.generarCostoPorDistancia(distancia, mismaProvincia);
			
			Arista nuevaArista = new Arista(ubicaciones.size(), i);
			conexiones.agregarArista(nuevaArista.getI(), nuevaArista.getJ());
			costoPorArista.put(Integer.toString(nuevaArista.getI()) + Integer.toString(nuevaArista.getJ()), costo);
		}
	
		ubicaciones.add(nuevaUbicacion);
	}
	
	public RespuestaPlanificacion planificarConexiones() {
		ArbolGeneradorMinimo generador = new ArbolGeneradorMinimo(costoPorArista, ubicaciones.size());
		ArbolInformacion arbol = generador.generar();
		
		double costoTotal = 0;
		for (Arista arista: arbol.getAristas()) {
			costoTotal += generador.obtenerCostoPorArista(arista.getI(), arista.getJ());
		}
		
		ArrayList<Integer> vertices = new ArrayList<Integer>();
		
		int i = 0;
		while (vertices.size() == 0) {
			ArrayList<Integer> vecinos = new ArrayList<Integer>(arbol.getArbol().vecinos(i));
			if (vecinos.size() == 1) {
				vertices.add(i);
				vertices.add(vecinos.get(0));
				arbol.getArbol().eliminarArista(i, vecinos.get(0));
			}
			i++;
		}
		
		while (vertices.size() < ubicaciones.size()) {
			int ultimoVertice = vertices.get(vertices.size() - 1);
			ArrayList<Integer> vecinos = new ArrayList<Integer>(arbol.getArbol().vecinos(ultimoVertice));
			System.out.println(vecinos.toString());
			vertices.add(vecinos.get(0));
			arbol.getArbol().eliminarArista(ultimoVertice, vecinos.get(0));
		}
		
		
		return new RespuestaPlanificacion(costoTotal, vertices);
	}
}