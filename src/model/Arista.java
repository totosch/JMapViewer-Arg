package model;

public class Arista {
	private int i;
	private int j;
	private double peso;
	
	public Arista(int i, int j, double peso) {
		this.i = i;
		this.j = j;
		this.peso = peso;
	}
	
	public Arista(int i, int j) {
		this.i = i;
		this.j = j;
	}

	public int getI() {
		return i;
	}

	public int getJ() {
		return j;
	}
	
	public double getPeso() {
		return peso;
	}
}