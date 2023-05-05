package model;

import java.util.ArrayList;

public class ArbolInformacion {
	private ArrayList<Arista> aristas;
	private Grafo arbol;
	
	public ArbolInformacion(ArrayList<Arista> aristas, Grafo arbol) {
		super();
		this.aristas = aristas;
		this.arbol = arbol;
	}
	
	public ArrayList<Arista> getAristas() {
		return aristas;
	}
	
	public Grafo getArbol() {
		return arbol;
	}
}
