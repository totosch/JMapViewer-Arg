package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class ModelTest {

	@Test
	public void ubicacionSeAgrega() {
		Model model = new Model();
		
		model.agregarGeneradorDeCostos(new GeneradorDeCostos(10, 5, 20));
		
		model.agregarUbicacion("el chalten", "Chubut", -10, -50);
		
		assertEquals(1, model.getUbicaciones().size());
	}
	
	@Test
	public void planificarCantidadPosicionesCorrectas() throws Exception {
		Model model = new Model();

		model.agregarGeneradorDeCostos(new GeneradorDeCostos(10, 5, 20));
		
		model.agregarUbicacion("el chalten1", "Chubut", -10, -50);
		model.agregarUbicacion("el chalten2", "Chubut", -10, -60);
		model.agregarUbicacion("el chalten3", "Chubut", -10, -70);
		model.agregarUbicacion("el chalten4", "Chubut", -10, -80);
		model.agregarUbicacion("el chalten5", "Chubut", -10, -90);
		
		RespuestaPlanificacion respuesta = model.planificarConexiones();
		
		assertEquals(model.getUbicaciones().size() - 1, respuesta.getPosiciones().size());
	}
	

	
	@Test(expected = Exception.class)
	public void planificarSinUbicaciones() throws Exception {
		Model model = new Model();

		model.agregarGeneradorDeCostos(new GeneradorDeCostos(10, 5, 20));
		
		model.planificarConexiones();
	}
	
	@Test(expected = Exception.class)
	public void planificarMenosDeTresUbicaciones() throws Exception {
		Model model = new Model();

		model.agregarGeneradorDeCostos(new GeneradorDeCostos(10, 5, 20));
		model.agregarUbicacion("el chalten1", "Chubut", -10, -50);
		model.agregarUbicacion("el chalten2", "Chubut", -10, -60);
		
		model.planificarConexiones();
	}
}
