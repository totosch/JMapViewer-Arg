package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class CostosTest {

	@Test
	public void diferenteProvinciaTest() {
		GeneradorDeCostos generadorCostos = new GeneradorDeCostos(1000, 2000, 4000);
		
		assertEquals(14000, generadorCostos.generarCostoPorDistancia(10, false), 0.0f);
	}

	@Test
	public void mismaProvinciaTest() {
		GeneradorDeCostos generadorCostos = new GeneradorDeCostos(1000, 2000, 4000);
		
		assertEquals(10000, generadorCostos.generarCostoPorDistancia(10, true), 0.0f);
	}
}
