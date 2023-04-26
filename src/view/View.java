package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.interfaces.ICoordinate;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;


import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;

public class View {

	private JFrame frame;
	private JMapViewer map;
	private ArrayList<Coordinate> coordinatesOnClick;
	private JButton gameStartButton = new JButton();
	private JLabel kilometerCostText = new JLabel();
	private JLabel longConectionCostText = new JLabel();
	private JLabel provinceBorderCostText = new JLabel();
	private JTextField kilometerCost = new JTextField();
	private JTextField longConectionCost = new JTextField();
	private JTextField provinceBorderCost = new JTextField();
	private int kilometerCostInput;
	private int longConectionCostInput;
	private int provinceBorderCostInput;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View window = new View();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public View() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 680, 525);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Mapardo");
	}
	
	public void initializeView() {
		this.frame.setVisible(true);
	}
	
	public void generateMenu() {		
		JLabel menuTitle = new JLabel("Bienvenido al Mapardo!");
		menuTitle.setHorizontalAlignment(SwingConstants.CENTER);
		menuTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		menuTitle.setBounds(184, 66, 264, 53);
		frame.getContentPane().add(menuTitle);
		
		kilometerCostText = new JLabel("Ingrese el costo deseado por KM");
		kilometerCostText.setBounds(150, 150, 250, 25);
        frame.getContentPane().add(kilometerCostText);        
		kilometerCost = new JTextField("");
		kilometerCost.setBounds(400, 150, 100, 25);
        frame.getContentPane().add(kilometerCost);
        
        longConectionCostText = new JLabel("Aumento en caso de superar los 300KM");
        longConectionCostText.setBounds(150, 200, 250, 25);
        frame.getContentPane().add(longConectionCostText);
        longConectionCost = new JTextField("");
        longConectionCost.setBounds(400, 200, 100, 25);
        frame.getContentPane().add(longConectionCost);

        provinceBorderCostText = new JLabel("Costo por cruze de provincia");
        provinceBorderCostText.setBounds(150, 250, 250, 25);
        frame.getContentPane().add(provinceBorderCostText);        
        provinceBorderCost = new JTextField("");
        provinceBorderCost.setBounds(400, 250, 100, 25);
        frame.getContentPane().add(provinceBorderCost);
	
        gameStartButton.setBounds(250, 350, 200, 50);
		gameStartButton.setText("Comenzar!");
		gameStartButton.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(gameStartButton);	
	}
	

	
	//podriamos hacer que el nombre que le pongan el nodo nuevo sea si o si el nombre de una provincia
	private void userClickedCoordinates() {
		map.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					Coordinate clickedCoordinate = (Coordinate) map.getPosition(e.getPoint());
					String nombre = JOptionPane.showInputDialog("Nodo nuevo: ");
					MapMarker marker = new MapMarkerDot(nombre, clickedCoordinate);
					map.addMapMarker(marker);
					coordinatesOnClick.add(clickedCoordinate);
					if (coordinatesOnClick.size() >= 2) {
						LineGenerator polyLine = new LineGenerator(coordinatesOnClick);
						map.addMapPolygon(polyLine);
					}
				}
			}
		});
	}
	
	private void showMap() {
		frame.getContentPane().setLayout(null);
		map = new JMapViewer();
		map.setZoomControlsVisible(false);
		Coordinate coordinate = new Coordinate(-34.451, -64.450);
		map.setDisplayPosition(coordinate, 6);
		frame.add(map);
		
		coordinatesOnClick = new ArrayList<Coordinate>();
		userClickedCoordinates();
	}
	
	public JButton getGameStartButton() {
		return gameStartButton;
	}
	
	public void addActionListenerToButton(ActionListener listener, JButton button) {
		button.addActionListener(listener);		
	}
	
	public void prepareScreen() {
		wipeOutPreviousScreen();
		showMap();
	}

	public void wipeOutPreviousScreen() {
		for (Component c : frame.getContentPane().getComponents()) {
			frame.getContentPane().remove(c);
			frame.getContentPane().revalidate();
		}
	}
	
	public void userInputs() {
		try {
		int kilometerCostInput = Integer.parseInt(kilometerCost.getText());;
		int longConectionCostInput = Integer.parseInt(longConectionCost.getText());;
		int provinceBorderCostInput = Integer.parseInt(provinceBorderCost.getText());;
		}
		catch (Exception error) {
			JOptionPane.showMessageDialog(null, "Solo se permiten numeros!");
		}
		
	}
	
		

}
