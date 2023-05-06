package model.grafo;

import java.util.ArrayList;

import model.Arista;

public class ArbolInformacion {
	private ArrayList<Arista> aristas;
	private Grafo arbol;
	
	public ArbolInformacion(ArrayList<Arista> aristas, Grafo arbol) {
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
