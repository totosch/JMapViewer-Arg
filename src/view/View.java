package view;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;


import org.openstreetmap.gui.jmapviewer.Coordinate;

public class View {

	private JFrame frame;
	private JMapViewer map;
	private ArrayList<Coordinate> coordinatesOnClick;
	private JPanel mapPanel = new JPanel();
	private JPanel buttonsPanel = new JPanel();
	private JButton gameStartButton = new JButton();
	private JButton drawLineButton = new JButton();
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
		frame.setBounds(100, 100, 800, 600);
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
	    menuTitle.setBounds(184, 66, 432, 53); // changed from 264 to 432
	    
	    int x = (frame.getWidth() - 500) / 2; // center horizontally
	    int y = (frame.getHeight() - 400) / 2; // center vertically
	    
	    menuTitle.setLocation(x + 34, y + 16); // adjust x and y coordinates
	    
	    frame.getContentPane().add(menuTitle);
	    
	    kilometerCostText = new JLabel("Ingrese el costo deseado por KM");
	    kilometerCostText.setBounds(x + 100, y + 100, 250, 25);
	    frame.getContentPane().add(kilometerCostText);
	    kilometerCost = new JTextField("");
	    kilometerCost.setBounds(x + 350, y + 100, 100, 25);
	    frame.getContentPane().add(kilometerCost);
	    
	    longConectionCostText = new JLabel("Aumento en caso de superar los 300KM");
	    longConectionCostText.setBounds(x + 100, y + 150, 250, 25);
	    frame.getContentPane().add(longConectionCostText);
	    longConectionCost = new JTextField("");
	    longConectionCost.setBounds(x + 350, y + 150, 100, 25);
	    frame.getContentPane().add(longConectionCost);
	    
	    provinceBorderCostText = new JLabel("Costo por cruze de provincia");
	    provinceBorderCostText.setBounds(x + 100, y + 200, 250, 25);
	    frame.getContentPane().add(provinceBorderCostText);
	    provinceBorderCost = new JTextField("");
	    provinceBorderCost.setBounds(x + 350, y + 200, 100, 25);
	    frame.getContentPane().add(provinceBorderCost);
	    
	    gameStartButton.setBounds(x + 175, y + 300, 200, 50);
	    gameStartButton.setText("Comenzar!");
	    gameStartButton.setHorizontalAlignment(SwingConstants.CENTER);
	    frame.getContentPane().add(gameStartButton);
	}

	
	
	private void showMap() {		
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mapPanel = new JPanel();
		mapPanel.setBounds(0, 0, 650, 600);
		frame.getContentPane().add(mapPanel);
		mapPanel.setLayout(new GridLayout(1,1));
			
		buttonsPanel = new JPanel();
		buttonsPanel.setBounds(655, 250, 120, 30);
		frame.getContentPane().add(buttonsPanel);	
		buttonsPanel.setLayout(new GridLayout(1,1));
		
		
		map = new JMapViewer();
		map.setZoomControlsVisible(false);
		Coordinate coordinate = new Coordinate(-34.451, -64.450);
		map.setDisplayPosition(coordinate, 6);
		mapPanel.add(map);

		coordinatesOnClick = new ArrayList<Coordinate>();
		userClickedCoordinates();
		drawLine();
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
		System.out.println(kilometerCostInput);
		System.out.println(longConectionCostInput);
		System.out.println(provinceBorderCostInput);
		} catch (Exception error) {
			JOptionPane.showMessageDialog(null, "Solo se permiten numeros!");
		}		
	}
	
	public JButton getGameStartButton() {
		return gameStartButton;
	}
	
	public void addActionListenerToButton(ActionListener listener, JButton button) {
		button.addActionListener(listener);		
	}
	
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
					System.out.println(clickedCoordinate);
				}
			}
		});
	}
	
	private void drawLine() {
		drawLineButton = new JButton("Dibujar Linea");
		drawLineButton.setBounds(10, 11, 195, 23);
		drawLineButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LineGenerator polyLine = new LineGenerator(coordinatesOnClick);
				map.addMapPolygon(polyLine);
			}
		});
		drawLineButton.setBounds(100, 20, 125, 50);
		buttonsPanel.add(drawLineButton);
	}

}
