package model;

import java.util.HashSet;
import java.util.Set;

public class Grafo implements IGrafo  {
	private boolean[][] matrizDeAdyacencia;
	
	public Grafo (int vertices) {
		matrizDeAdyacencia = new boolean[vertices][vertices];
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < matrizDeAdyacencia.length; i++) {
			for (int j = 0; j < matrizDeAdyacencia.length; j++) {
				String comparador = matrizDeAdyacencia[i][j] ? "R" : "N";
				
				sb.append(i + comparador + j + "| ");
			}
			sb.append('\n');
		}
		
		return sb.toString();
	}
	
	public int tamanio () {
		return this.matrizDeAdyacencia.length;
	}
	
	public void agregarArista(int i, int j) throws IllegalArgumentException {
		verificarDistintos(i, j);
		
		verificarVertice(i);
		verificarVertice(j);
		
		matrizDeAdyacencia[i][j] = true;
		matrizDeAdyacencia[j][i] = true;
	}
	
	public void eliminarArista(int i, int j) throws IllegalArgumentException {
		verificarDistintos(i, j);
		
		verificarVertice(i);
		verificarVertice(j);
		
		
		matrizDeAdyacencia[i][j] = false;
		matrizDeAdyacencia[j][i] = false;
	}

	public boolean existeArista(int i, int j) throws IllegalArgumentException {
		verificarVertice(i);
		verificarVertice(j);
		
		return matrizDeAdyacencia[i][j];
	}
	
	public Set<Integer> vecinos(int indice) throws IllegalArgumentException {
		verificarVertice(indice);
		
		Set<Integer> ret = new HashSet<Integer>();
		for(int j = 0; j < this.tamanio(); ++j) if( indice != j )
		{
			if( this.existeArista(indice, j) )
				ret.add(j);
		}
		
		return ret;		
	}
	
	private void verificarDistintos(int i, int j) {
		if (i == j) 
			throw new IllegalArgumentException("No se puede autoreferenciar el vertice");
	}
	

	private void verificarVertice(int i) {
		if (i < 0)
			throw new IllegalArgumentException("Los vertices deben ser positivos y estar dentro de las dimensiones de la matriz: " + i);
		
		if (i >= this.tamanio())
			throw new IllegalArgumentException("Los vertices deben ser positivos y estar dentro de las dimensiones de la matriz: " + i);
	}
}