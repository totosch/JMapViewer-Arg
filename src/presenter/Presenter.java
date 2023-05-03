package presenter;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import org.openstreetmap.gui.jmapviewer.Coordinate;

import model.GeneradorDeCostos;
import model.Model;
import view.View;

public class Presenter {
	private Model model;
	private View view;
	
	public Presenter(View view, Model model) {
		this.view = view;
		this.model = model;
		
		view.addActionListenerToButton(new ScreenChanger(), view.getGameStartButton());
	}
	
	public void startGame() {
		view.initializeView();
		view.generateMenu();
	}
	
	class ScreenChanger implements ActionListener {       			
		@Override
		public void actionPerformed(ActionEvent e) {
			view.prepareScreen();
			view.userInputs();
			view.addActionListenerToMouseClick(new MouseClick(), view.getMap());
			
			GeneradorDeCostos generadorDeCostos = new GeneradorDeCostos(
					view.getKilometerCost(),
					view.getLongConectionCost(),
					view.getProvinceBorderCost());
			
			model.agregarGeneradorDeCostos(generadorDeCostos);
			}			
		}	
	
	class MouseClick implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			Coordinate coordinate = view.mouseAction(e);
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