package model;

import java.util.ArrayList;
import java.util.HashSet;

import model.grafo.ArbolGeneradorMinimo;
import model.grafo.ArbolInformacion;
import model.grafo.Grafo;

public class Model {
	private ArrayList<Ubicacion> ubicaciones;
	private GeneradorDeCostos generadorDeCostos;
	private ArrayList<Arista> aristas;
	
	public Model () {
		this.ubicaciones = new ArrayList<Ubicacion>();
		this.aristas = new ArrayList<Arista>();
	}
	
	protected ArrayList<Ubicacion> getUbicaciones() {
		return ubicaciones;
	}
	
	public void agregarGeneradorDeCostos(GeneradorDeCostos generadorDeCostos) {
		this.generadorDeCostos = generadorDeCostos;
	}
	
	public void agregarUbicacion(String identificador, String provincia, double latitud, double longitud) {
		Ubicacion nuevaUbicacion = new Ubicacion(identificador, provincia, latitud, longitud);
		
		for (int i = 0; i < ubicaciones.size(); i++) {
			Ubicacion ubicacionActual = ubicaciones.get(i);
			double distancia = DistanceCalculator.distance(nuevaUbicacion.getLatitud(), nuevaUbicacion.getLongitud(), ubicacionActual.getLatitud(), ubicacionActual.getLongitud());
			boolean mismaProvincia = provincia.equals(ubicacionActual.getProvincia());
			
			System.out.println("misma provincia: " + mismaProvincia);
					
			double costo = generadorDeCostos.generarCostoPorDistancia(distancia, mismaProvincia);
			
			Arista nuevaArista = new Arista(ubicaciones.size(), i, costo);
			aristas.add(nuevaArista);
		}
	
		ubicaciones.add(nuevaUbicacion);
	}
	
	public RespuestaPlanificacion planificarConexiones() {
		ArbolGeneradorMinimo generador = new ArbolGeneradorMinimo(aristas, ubicaciones.size());
		ArbolInformacion informacionArbol = generador.generar();
		
		System.out.println(informacionArbol.getArbol().toString());
		
		double costoTotal = 0;
		for (Arista arista: informacionArbol.getAristas()) {
			costoTotal += arista.getPeso();
		}
		
		
		return new RespuestaPlanificacion(costoTotal, informacionArbol.getAristas());
	}
}