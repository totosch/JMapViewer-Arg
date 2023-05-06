package model.grafo;

import static org.junit.Assert.*;

import org.junit.Test;

public class EdicionDeAristasTest {

	@Test
	public void aristaExistenteTest() {
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(2, 3);
		assertTrue(grafo.existeArista(2, 3));
	}
	
	@Test
	public void aristaOpuestaTest() {
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(2, 3);
		assertTrue(grafo.existeArista(3, 2));
	}
	
	@Test
	public void aristaInexistenteTest() {
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(2, 3);
		assertFalse(grafo.existeArista(2, 1));
	}
	
	@Test
	public void agregarAristaDosVecesTest() {
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(2, 3);
		grafo.agregarArista(2, 3);
		
		assertTrue(grafo.existeArista(2, 3));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void primerVerticeNegativoTest() {
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(-1, 3);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void segundoVerticeNegativoTest() {
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(3, -1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void primerVerticeExcedidoTest() {
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(6, 3);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void segundoVerticeExcedidoTest() {
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(3, 6);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void agregarLoopTest() {
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(3, 3);
	}
	
	@Test
	public void eliminarAristaExistenteTest() {
		Grafo grafo = new Grafo(5);
		
		grafo.agregarArista(2, 4);
		grafo.eliminarArista(2, 4);
		
		assertFalse( grafo.existeArista(2, 4));
	}
	
	@Test
	public void eliminarAristaInexistenteTest() {
		Grafo grafo = new Grafo(5);
		grafo.eliminarArista(2, 4);
		assertFalse( grafo.existeArista(2, 4));
	}
	
	@Test
	public void eliminarAristaDosVecesTest() {
		Grafo grafo = new Grafo(5);
		
		grafo.agregarArista(2, 4);
		grafo.eliminarArista(2, 4);
		grafo.eliminarArista(2, 4);
		
		assertFalse( grafo.existeArista(2, 4));
	}
}
