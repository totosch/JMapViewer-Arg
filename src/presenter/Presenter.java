package presenter;


import model.Model;
import view.View;

public class Presenter {
	private Model model;
	private View view;
	
	public Presenter(View view, Model model) {
		this.view = view;
		this.model = model;
	}
	
	public void startGame() {
		view.initializeView();
		view.generateMenu();
	}

}
