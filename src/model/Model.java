package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

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
			double distancia = DistanceCalculator.distance(nuevaUbicacion.getLatitud(), nuevaUbicacion.getLongitud(), latitud, longitud);
			boolean mismaProvincia = provincia.equals(ubicacionActual.getProvincia());
					
			double costo = generadorDeCostos.generarCostoPorDistancia(distancia, mismaProvincia);
			
			Arista nuevaArista = new Arista(ubicaciones.size(), i);
			conexiones.agregarArista(nuevaArista.getI(), nuevaArista.getJ());
			costoPorArista.put(Integer.toString(nuevaArista.getI()) + Integer.toString(nuevaArista.getJ()), costo);
		}
		
	
		ubicaciones.add(nuevaUbicacion);
	}
	
	public RespuestaPlanificacion planificarConexiones() {
		ArbolGeneradorMinimo generador = new ArbolGeneradorMinimo(conexiones, costoPorArista);
		ArrayList<Arista> aristas = generador.generar().getAristas();
		
		double costoTotal = 0;
		for (Arista arista: aristas) {
			costoTotal += generador.obtenerCostoPorArista(arista.getI(), arista.getJ());
		}
		
		return new RespuestaPlanificacion(costoTotal, aristas);
	}
}