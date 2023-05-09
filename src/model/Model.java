package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import com.google.gson.Gson;

import model.grafo.ArbolGeneradorMinimo;
import model.grafo.ArbolInformacion;

public class Model extends SerializableJSON {
	private ArrayList<Ubicacion> ubicaciones;
	private GeneradorDeCostos generadorDeCostos;
	private ArrayList<Arista> aristas;
	private static final String nombreArchivo = "ubicaciones.txt";
	private boolean fueBorradaInformacion;
	
	
	public Model () {
		this.ubicaciones = new ArrayList<Ubicacion>();
		this.aristas = new ArrayList<Arista>();
		this.fueBorradaInformacion = false;
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
					
			double costo = generadorDeCostos.generarCostoPorDistancia(distancia, mismaProvincia);
			
			Arista nuevaArista = new Arista(ubicaciones.size(), i, costo);
			aristas.add(nuevaArista);
		}
	
		ubicaciones.add(nuevaUbicacion);
	}
	
	public ArrayList<Ubicacion> leerUbicacionesGuardadas() {
		Gson gson = new Gson();
		Model data = null;
		
		if (fueBorradaInformacion)
			return null;
			
		try {
			BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
			data = gson.fromJson(br, Model.class);
		} catch (Exception e) {
			return null;
		}
			
			
		this.ubicaciones = data.ubicaciones;
		this.aristas = data.aristas;
			
		return data.ubicaciones;
	}
	
	public void borrarInformacionGuardada() {
		File archivo = new File(nombreArchivo);
			
		boolean fueBorrado = archivo.delete();
		
		fueBorradaInformacion = true;
			
		if (!fueBorrado) { 
			throw new Error("Error al borrar archivo");
		}
	}
	
	public RespuestaPlanificacion planificarConexiones() {
		ArbolGeneradorMinimo generador = new ArbolGeneradorMinimo(aristas, ubicaciones.size());
		ArbolInformacion informacionArbol = generador.generar();
		
		double costoTotal = 0;
		for (Arista arista: informacionArbol.getAristas()) {
			costoTotal += arista.getPeso();
		}
		
		super.generarJSON(nombreArchivo);
		
		return new RespuestaPlanificacion(costoTotal, informacionArbol.getAristas());
	}
}