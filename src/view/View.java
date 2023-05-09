package view;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JComboBox;

import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;

import model.Arista;

import org.openstreetmap.gui.jmapviewer.Coordinate;

public class View {

	private JFrame frame;
	private JMapViewer map;
	private JPanel panelMapa = new JPanel();
	private JPanel panelBotones = new JPanel();
	private JButton botonComenzarJuego = new JButton();
	private JButton botonDibujarLinea = new JButton();
	private JButton botonBorrarUbicacionesPrevias = new JButton();
	private JLabel textoCostoPorKilometro = new JLabel();
	private JLabel textoConexionLarga = new JLabel();
	private JLabel textoCruzeProvincia = new JLabel();
	private JLabel labelCostoTotal = new JLabel();
	private JTextField costoPorKilometro = new JTextField();
	private JTextField costoConexionLarga = new JTextField();
	private JTextField costoCruzeProvincia = new JTextField();
	JComboBox<String> provinciasComboBox = new JComboBox<String>();
	
	private ArrayList<Coordinate> coordenadasClickeadas;
	
	private int costoKilometroInput;
	private int costoConexionLargaInput;
	private int costoCruzeProvinciaInput;
	
	

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
	
	public void inicializarView() {
		this.frame.setVisible(true);
	}
	
	public void generarMenu() {      
	    JLabel menuTitle = new JLabel("Bienvenido al Mapardo!");
	    menuTitle.setHorizontalAlignment(SwingConstants.CENTER);
	    menuTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
	    menuTitle.setBounds(184, 66, 432, 53); // changed from 264 to 432
	    
	    int x = (frame.getWidth() - 500) / 2; // center horizontally
	    int y = (frame.getHeight() - 400) / 2; // center vertically
	    
	    menuTitle.setLocation(x + 34, y + 16); // adjust x and y coordinates
	    
	    frame.getContentPane().add(menuTitle);
	    
	    textoCostoPorKilometro = new JLabel("Ingrese el costo deseado por KM");
	    textoCostoPorKilometro.setBounds(x + 100, y + 100, 250, 25);
	    frame.getContentPane().add(textoCostoPorKilometro);
	    costoPorKilometro = new JTextField("1000");
	    costoPorKilometro.setBounds(x + 350, y + 100, 100, 25);
	    frame.getContentPane().add(costoPorKilometro);
	    
	    textoConexionLarga = new JLabel("Aumento en caso de superar los 300KM");
	    textoConexionLarga.setBounds(x + 100, y + 150, 250, 25);
	    frame.getContentPane().add(textoConexionLarga);
	    costoConexionLarga = new JTextField("5000");
	    costoConexionLarga.setBounds(x + 350, y + 150, 100, 25);
	    frame.getContentPane().add(costoConexionLarga);
	    
	    textoCruzeProvincia = new JLabel("Costo por cruze de provincia");
	    textoCruzeProvincia.setBounds(x + 100, y + 200, 250, 25);
	    frame.getContentPane().add(textoCruzeProvincia);
	    costoCruzeProvincia = new JTextField("20000");
	    costoCruzeProvincia.setBounds(x + 350, y + 200, 100, 25);
	    frame.getContentPane().add(costoCruzeProvincia);
	    
	    botonComenzarJuego.setBounds(x + 175, y + 300, 200, 50);
	    botonComenzarJuego.setText("Comenzar!");
	    botonComenzarJuego.setHorizontalAlignment(SwingConstants.CENTER);
	    frame.getContentPane().add(botonComenzarJuego);
	    
	    botonBorrarUbicacionesPrevias.setBounds(x + 175, y + 400, 200, 30);
	    botonBorrarUbicacionesPrevias.setText("Borrar ubicaciones previas");
	    botonBorrarUbicacionesPrevias.setHorizontalAlignment(SwingConstants.CENTER);
	    frame.getContentPane().add(botonBorrarUbicacionesPrevias);
	}

		
	public void prepararPantalla() {
		borrarPantallaAnterior();
		showMapa();				
	}
	
	private void showMapa() {		
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panelMapa = new JPanel();
		panelMapa.setBounds(0, 0, 620, 600);
		frame.getContentPane().add(panelMapa);
		panelMapa.setLayout(new GridLayout(1,1));
			
		panelBotones = new JPanel();
		panelBotones.setBounds(625, 250, 150, 30);
		frame.getContentPane().add(panelBotones);	
		panelBotones.setLayout(new GridLayout(2,1));		
		
		map = new JMapViewer();
		map.setZoomControlsVisible(false);
		Coordinate coordinate = new Coordinate(-34.451, -64.450);
		map.setDisplayPosition(coordinate, 6);
		panelMapa.add(map);
		
		coordenadasClickeadas = new ArrayList<Coordinate>();
		
		botonDibujarLinea = new JButton("Planificar Conexiones");
		
		panelBotones.add(botonDibujarLinea);		
		labelCostoTotal = new JLabel("Costo total: " );		
		panelBotones.add(labelCostoTotal);
	}

	public void borrarPantallaAnterior() {
			for (Component c : frame.getContentPane().getComponents()) {
				frame.getContentPane().remove(c);
				frame.getContentPane().revalidate();
			}
		}
	
	public void obtenerInputsUsuario() {
		try {
			costoKilometroInput = Integer.parseInt(costoPorKilometro.getText());;
			costoConexionLargaInput = Integer.parseInt(costoConexionLarga.getText());;
			costoCruzeProvinciaInput = Integer.parseInt(costoCruzeProvincia.getText());;
		} catch (Exception exc) {
			throw new Error("Solo se permiten numeros!");
		}		
	}
	
	public JButton getBotonComenzarJuego() {
		return botonComenzarJuego;
	}
	
	public JButton getBotonDibujarLinea () {
		return botonDibujarLinea;
	}
	
	public JButton getBotonBorrarUbicacionesPrevias () {
		return botonBorrarUbicacionesPrevias;
	}
	
	public int getCostoKilometro() {
		return costoKilometroInput;
	}
	
	public int getCostoConexionLarga() {
		return costoConexionLargaInput;
	}
	
	public int getCostoCruzeProvincia() {
		return costoCruzeProvinciaInput;
	}
	
	public JMapViewer getMapa() {
		return map;
	}
	
	public void showMessageDialog(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje);
	}
	
	public void agregarActionListenerAlBoton(ActionListener listener, JButton button) {
		button.addActionListener(listener);		
	}
	
	public UbicacionView clickearMapa(MouseEvent e) {
	    if (e.getButton() != MouseEvent.BUTTON1) {
	        return null;
	    }
	    Coordinate coordenadaClickeada = (Coordinate) map.getPosition(e.getPoint());
	    String nombreNodo = JOptionPane.showInputDialog("Nodo nuevo:");
		if (nombreNodo == null) {
			   return null;
			}
	    
	    JComboBox<String> provinciasComboBox = new JComboBox<>();
	    provinciasComboBox.setToolTipText("Seleccione");
	    String[] comboBoxOptions = { "Buenos Aires", "Catamarca", "Chaco", "Chubut", "Córdoba", "Corrientes", "Entre Ríos", "Formosa", "Jujuy", "La Pampa", "La Rioja", "Mendoza", "Misiones", "Neuquén", "Río Negro", "Salta", "San Juan", "San Luis", "Santa Cruz", "Santa Fe", "Santiago del Estero", "Tierra del Fuego", "Tucumán" };
	    for (int i = 0; i < comboBoxOptions.length; i++) {
	    	provinciasComboBox.addItem(comboBoxOptions[i]);
	    }
	    JOptionPane.showOptionDialog(null, provinciasComboBox, "Seleccione la provincia", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
	    String province = (String) provinciasComboBox.getSelectedItem();
	    
	    MapMarker marker = new MapMarkerDot(nombreNodo + " (" + province + ")", coordenadaClickeada);
	    map.addMapMarker(marker);
	    coordenadasClickeadas.add(coordenadaClickeada);

	    UbicacionView ubicacion = new UbicacionView(nombreNodo, province, coordenadaClickeada);
	    return ubicacion;
	}
	
	public void dibujarUbicacionesPrecargadas(ArrayList<UbicacionView> ubicaciones) {
        for (UbicacionView ubicacion : ubicaciones) {
	        MapMarker marker = new MapMarkerDot(ubicacion.getNombre() + "(" + ubicacion.getProvincia() + ")", ubicacion.getCoordenada());
	        coordenadasClickeadas.add(ubicacion.getCoordenada());
	        map.addMapMarker(marker);
        }
    }
	
	public void dibujarPlanificacion(ArrayList<Arista> paresPosiciones) {
		map.removeAllMapPolygons();
	    ArrayList<ArrayList<Coordinate>> arregloCoordenadasFinales = new ArrayList<ArrayList<Coordinate>>();
	    for (int i = 0; i < paresPosiciones.size(); i++) {
	        ArrayList<Coordinate> coordenadas = new ArrayList<Coordinate>();
	        coordenadas.add(coordenadasClickeadas.get(paresPosiciones.get(i).getI()));
	        coordenadas.add(coordenadasClickeadas.get(paresPosiciones.get(i).getJ()));
	        coordenadas.add(coordenadasClickeadas.get(paresPosiciones.get(i).getJ()));
	        arregloCoordenadasFinales.add(coordenadas);
	    }
	    for (int i = 0; i < arregloCoordenadasFinales.size(); i++) {
	        LineGenerator polyLine = new LineGenerator(arregloCoordenadasFinales.get(i));
	        map.addMapPolygon(polyLine);
	    }
	}
	
	public int getProvinciaFromDimensionsBox() {
        switch ((String) provinciasComboBox.getSelectedItem()) {
            case "Buenos Aires": return 1;
            case "Catamarca": return 2;
            case "Chaco": return 3;
            case "Chubut": return 4;
            case "Córdoba": return 5;
            case "Corrientes": return 6;
            case "Entre Ríos": return 7;
            case "Formosa": return 8;
            case "Jujuy": return 9;
            case "La Pampa": return 10;
            case "La Rioja": return 11;
            case "Mendoza": return 12;
            case "Misiones": return 13;
            case "Neuquén": return 14;
            case "Río Negro": return 15;
            case "Salta": return 16;
            case "San Juan": return 17;
            case "San Luis": return 18;
            case "Santa Cruz": return 19;
            case "Santa Fe": return 20;
            case "Santiago del Estero": return 21;
            case "Tierra del Fuego": return 22;
            case "Tucumán": return 23;
        }
        return 1;
    }
	
	public void agregarActionListenerAlMouseClick(MouseListener click, JMapViewer map) {
		map.addMouseListener(click);		
	}

	public void mostrarCostoTotal(double costoTotal) {
        String formattedCostoTotal = String.format("%.2f", costoTotal);
        labelCostoTotal.setText("Costo total: $" + formattedCostoTotal);
    }
}