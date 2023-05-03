package model;

import java.util.Set;

public interface IGrafo {
	public void agregarArista(int i, int j) throws IllegalArgumentException;
	
	public void eliminarArista(int i, int j) throws IllegalArgumentException;
	
	public boolean existeArista(int i, int j) throws IllegalArgumentException;
	
	public Set<Integer> vecinos(int indice) throws IllegalArgumentException;
}