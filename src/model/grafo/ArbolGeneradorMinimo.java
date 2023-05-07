package model.grafo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

import model.Arista;

public class ArbolGeneradorMinimo {
	private Grafo arbol;
	private ArrayList<Arista> aristas;
	private Subconjunto subconjuntos[];
	private int tamanio;

	public ArbolGeneradorMinimo(ArrayList<Arista> aristas, int tamanio) {
		this.arbol = new Grafo(tamanio);
		this.aristas = aristas;
		this.tamanio = tamanio;
		this.subconjuntos = new Subconjunto[tamanio];
		
		for (int i = 0; i < tamanio; i++) {
			subconjuntos[i] = new Subconjunto(i, 0);
		}

		aristas.sort(new Comparator<Arista>() {
			@Override
			public int compare(Arista o1, Arista o2) {
				return (int) (o1.getPeso() - o2.getPeso());
			}
		});
	}

	static class Subconjunto {
		int padre;
		int altura;

		public Subconjunto(int padre, int altura) {
			this.padre = padre;
			this.altura = altura;
		}
	}

	public ArbolInformacion generar() {
		int j = 0;
		int verticesAniadidos = 0;
		
		ArrayList<Arista> aristasResultantes = new ArrayList<Arista>(tamanio - 1);

		while (verticesAniadidos < tamanio - 1) {
			Arista arista = aristas.get(j);
			int x = buscarRaiz(arista.getI()); 
			int y = buscarRaiz(arista.getJ());

			if (x != y) {
				aristasResultantes.add(verticesAniadidos, arista);
				arbol.agregarArista(arista.getI(), arista.getJ());;
				union(x, y);
				verticesAniadidos++;
			}

			j++;
		}
		
		return new ArbolInformacion(aristasResultantes, arbol);
	}

	private void union(int x, int y) {
		int raizX = buscarRaiz(x);
		int raizY = buscarRaiz(y);

		if (subconjuntos[raizY].altura < subconjuntos[raizX].altura) {
			subconjuntos[raizY].padre = raizX;
		} else if (subconjuntos[raizX].altura < subconjuntos[raizY].altura) {
			subconjuntos[raizX].padre = raizY;
		} else {
			subconjuntos[raizY].padre = raizX;
			subconjuntos[raizX].altura++;
		}
	}

	private int buscarRaiz(int i) {
		if (subconjuntos[i].padre == i)
			return subconjuntos[i].padre;

		subconjuntos[i].padre = buscarRaiz(subconjuntos[i].padre);
		return subconjuntos[i].padre;
	}
}