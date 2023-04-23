package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
	private Coordinate clickedCoordinate;


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

	/**
	 * Create the application.
	 */
	public View() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Mapardo");

		map = new JMapViewer();
		map.setZoomControlsVisible(false);
		Coordinate coordinate = new Coordinate(-34.451, -64.450);
		map.setDisplayPosition(coordinate, 5);
		frame.add(map);

		/*
		 * MapMarker marker01 = new MapMarkerDot("hola", coordinate);
		 * marker01.getStyle().setBackColor(Color.CYAN); map.addMapMarker(marker01);
		 * 
		 * ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
		 * coordinates.add(new Coordinate(-34.100, -61.300)); coordinates.add(new
		 * Coordinate(-32.100, -64.100)); coordinates.add(new Coordinate(-33.300,
		 * -66.300)); coordinates.add(new Coordinate(-35.700, -64.800));
		 * coordinates.add(new Coordinate(-36.100, -69.800));
		 * 
		 * MapPolygonImpl polygon = new MapPolygonImpl(coordinates);
		 * map.addMapPolygon(polygon);
		 */
		
		coordinatesOnClick = new ArrayList<Coordinate>();
		userClickedCoordinates();

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
	                if (coordinatesOnClick.size() >= 2) {
	                    MapPolyLine polyLine = new MapPolyLine(coordinatesOnClick);
	                    map.addMapPolygon(polyLine);
	                }
	            }
	        }
	    });
	}
	
    public static class MapPolyLine extends MapPolygonImpl {
        public MapPolyLine(List<? extends ICoordinate> points) {
            super(null, null, points);
        }
    }

}
