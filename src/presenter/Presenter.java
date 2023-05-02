package presenter;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
			}			
		}		
	}

