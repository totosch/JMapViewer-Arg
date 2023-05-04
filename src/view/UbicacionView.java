package view;

import org.openstreetmap.gui.jmapviewer.Coordinate;

public class UbicacionView {
    private String nombre;
    private String provincia;
    private Coordinate coordenada;

    public UbicacionView (String nombre, String provincia, Coordinate coordenada) {
        this.nombre = nombre;
        this.provincia = provincia;
        this.coordenada = coordenada;
    }

    public Coordinate getCoordenada() {
    	return coordenada;
    }

    public String getNombre() {
        return nombre;
    }

    public String getProvincia() {
        return provincia;
    }
}