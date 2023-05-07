package model;

public class GeneradorDeCostos {
	private int costoPorKilometro;
	private int porcentajeAumentoMayorA300km;
	private int costoFijoProvinciasDistintas;
	
	public GeneradorDeCostos (int costoPorKilometro,
	int porcentajeAumentoMayorA300km,
	int costoFijoProvinciasDistintas) {
		this.costoPorKilometro = costoPorKilometro;
		this.porcentajeAumentoMayorA300km = porcentajeAumentoMayorA300km;
		this.costoFijoProvinciasDistintas = costoFijoProvinciasDistintas; 
	}
	
	public double generarCostoPorDistancia(double distancia, boolean mismaProvincia) {
		double porcentajeAumento = distancia > 300 ? porcentajeAumentoMayorA300km : 1;
		System.out.println("porcentajeAumento: " + porcentajeAumento);
		double costo = distancia * costoPorKilometro;
		System.out.println("costoCasi: " + costo);
		return (costo * porcentajeAumento) + (mismaProvincia ?  0 : costoFijoProvinciasDistintas);
	}
}