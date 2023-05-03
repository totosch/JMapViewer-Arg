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
		double costo = distancia * costoPorKilometro;
		return (costo * porcentajeAumento) + (mismaProvincia ? costoFijoProvinciasDistintas : 0);
	}
}