package model;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.Test;


public class ArbolTest {

    @Test
    public void testeandoArbolCincoCompleto() {
        Grafo grafo = new Grafo(5);
        grafo.agregarArista(0, 1);
        grafo.agregarArista(0, 2);
        grafo.agregarArista(0, 3);
        grafo.agregarArista(0, 4);
        grafo.agregarArista(1, 2);
        grafo.agregarArista(1, 3);
        grafo.agregarArista(1, 4);
        grafo.agregarArista(2, 3);
        grafo.agregarArista(2, 4);
        grafo.agregarArista(3, 4);

        HashMap<String, Double> costoPorArista = new HashMap<String, Double>();

        costoPorArista.put("01", 7.0);
        costoPorArista.put("02", 12.0);
        costoPorArista.put("03", 9.0);
        costoPorArista.put("04", 5.0);
        costoPorArista.put("12", 11.0);
        costoPorArista.put("13", 8.0);
        costoPorArista.put("14", 6.0);
        costoPorArista.put("23", 3.0);
        costoPorArista.put("24", 7.0);
        costoPorArista.put("34", 10.0);

        ArbolGeneradorMinimo generador = new ArbolGeneradorMinimo(grafo, costoPorArista);
        Grafo arbol = generador.generar();

        Arista[] aristasEsperadas = { new Arista(0,4), new Arista(1, 3), new Arista(1,4), new Arista(3,2) };

        for (int i = 0; i < aristasEsperadas.length; i++) {
            assertTrue(arbol.existeArista(aristasEsperadas[i].getI(), aristasEsperadas[i].getJ()));
        }
    }
}