package model.grafo;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import model.Arista;

public class ArbolTest {

	@Test
	public void testeandoArbolCincoCompleto() {
		ArrayList<Arista> aristas = new ArrayList<Arista>();
		
		aristas.add(new Arista(0, 1, 7.0));
		aristas.add(new Arista(0, 2, 12.0));
		aristas.add(new Arista(0, 3, 9.0));
		aristas.add(new Arista(0, 4, 5.0));
		aristas.add(new Arista(1, 2, 11.0));
		aristas.add(new Arista(1, 3, 8.0));
		aristas.add(new Arista(1, 4, 6.0));
		aristas.add(new Arista(2, 3, 3.0));
		aristas.add(new Arista(2, 4, 7.0));
		aristas.add(new Arista(3, 4, 10.0));

		ArbolGeneradorMinimo generador = new ArbolGeneradorMinimo(aristas, 5);
		ArbolInformacion informacionArbol = generador.generar();
		
		System.out.println(informacionArbol.getArbol().toString());

		Arista[] aristasEsperadas = { new Arista(0, 4), new Arista(2, 4), new Arista(1, 4), new Arista(3, 2) };

		for (int i = 0; i < aristasEsperadas.length; i++) {
			assertTrue(
					informacionArbol.getArbol().existeArista(aristasEsperadas[i].getI(), aristasEsperadas[i].getJ()));
		}
	}
}