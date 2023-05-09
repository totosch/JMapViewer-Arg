package presenter;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

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
		view.agregarActionListenerAlBoton(new DeleteInformation(), view.getBotonBorrarUbicacionesPrevias());
	}
	
	public void startGame() {
		view.inicializarView();
		view.generarMenu();
	}
	
	private void entregarPlanificacion(RespuestaPlanificacion planificacion) {
		view.dibujarPlanificacion(planificacion.getPosiciones());
		view.mostrarCostoTotal(planificacion.getCostoTotal());
	}
	
	class DeleteInformation implements ActionListener {       			
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				model.borrarInformacionGuardada();				
			} catch (Exception exception) {
				view.showMessageDialog(exception.getMessage());
			}
		}
	}
	
	
	class ScreenChanger implements ActionListener {       			
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
			view.obtenerInputsUsuario();
			view.prepararPantalla();
			
			view.agregarActionListenerAlMouseClick(new MouseClick(), view.getMapa());
			view.agregarActionListenerAlBoton(new PlanificationRequest(), view.getBotonDibujarLinea());
			
			GeneradorDeCostos generadorDeCostos = new GeneradorDeCostos(
					view.getCostoKilometro(),
					view.getCostoConexionLarga(),
					view.getCostoCruzeProvincia());
			
			model.agregarGeneradorDeCostos(generadorDeCostos);
			
			ArrayList<Ubicacion> ubicacionesGuardadas = model.leerUbicacionesGuardadas();
			
			if (ubicacionesGuardadas == null)
				return;
			
			ArrayList<UbicacionView> ubicacionesView = new ArrayList<UbicacionView>();
			
			for (Ubicacion ubicacion: ubicacionesGuardadas) {
				ubicacionesView.add(new UbicacionView(
						ubicacion.getNombre(), 
						ubicacion.getProvincia(), 
						ubicacion.getLatitud(), 
						ubicacion.getLongitud()));
			}
			
			view.dibujarUbicacionesPrecargadas(ubicacionesView);
			entregarPlanificacion(model.planificarConexiones());
			} catch (Exception exception) {
				view.showMessageDialog(exception.getMessage());
			}
		}			
	}	
	
	class PlanificationRequest implements ActionListener {       			
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				entregarPlanificacion(model.planificarConexiones());				
			} catch (Exception exception) {
				view.showMessageDialog(exception.getMessage());
			}
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