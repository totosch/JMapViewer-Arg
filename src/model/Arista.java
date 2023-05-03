package model;

public class Arista implements Comparable<Arista> {
	private int i;
	private int j;
	private double peso;
	
	public Arista(int i, int j, double peso) {
		this.i = i;
		this.j = j;
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
	
	@Override
	public int compareTo(Arista arista) {
		return Double.compare(this.peso, arista.peso);
	}
}