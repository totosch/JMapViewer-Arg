package presenter;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.GeneradorDeCostos;
import model.Model;
import model.RespuestaPlanificacion;
import model.Ubicacion;
import view.UbicacionView;
import view.View;

public class Presenter {
	private Model model;
	private View view;
	
	public Presenter(View view, Model model) {
		this.view = view;
		this.model = model;
		
		view.agregarActionListenerAlBoton(new ScreenChanger(), view.getBotonComenzarJuego());
	}
	
	public void startGame() {
		view.inicializarView();
		view.generarMenu();
	}
	
	class ScreenChanger implements ActionListener {       			
		@Override
		public void actionPerformed(ActionEvent e) {
			view.prepararPantalla();
			view.inputsUsuario();
			
			view.agregarActionListenerAlMouseClick(new MouseClick(), view.getMapa());
			view.agregarActionListenerAlBoton(new PlanificationRequest(), view.getBotonDibujarLinea());
			
			GeneradorDeCostos generadorDeCostos = new GeneradorDeCostos(
					view.getCostoKilometro(),
					view.getCostoConexionLarga(),
					view.getCostoCruzeProvincia());
			
			model.agregarGeneradorDeCostos(generadorDeCostos);
		}			
	}	
	
	class PlanificationRequest implements ActionListener {       			
		@Override
		public void actionPerformed(ActionEvent e) {
			RespuestaPlanificacion planificacion = model.planificarConexiones();  
			
			view.dibujarPlanificacion(planificacion.getPosiciones());
		}			
	}	
	
	class MouseClick implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			UbicacionView ubicacionVista = view.clickearMapa(e);
			try {
				model.agregarUbicacion(
						ubicacionVista.getNombre(), 
						ubicacionVista.getProvincia(),
						ubicacionVista.getCoordenada().getLat(),
						ubicacionVista.getCoordenada().getLon());
			} catch (Exception e1) {
				view.showMessageDialog("Ocurrio un problema al guardar la ubicacion, intente de nuevo");
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	}