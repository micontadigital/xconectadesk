package com.idalytec.cfdidesk;

import java.util.ArrayList;

public class MetodoPago {
	String valor,nombre;

	public MetodoPago(String valor, String nombre) {
		super();
		this.valor = valor;
		this.nombre = nombre;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return nombre;
	}
	
	
	public static ArrayList<MetodoPago> getMetodos() {
		ArrayList<MetodoPago> metodos = new ArrayList<MetodoPago>();
		metodos.add(new MetodoPago("01", "Efectivo"));
		metodos.add(new MetodoPago("02", "Cheque nominativo"));
		metodos.add(new MetodoPago("03", "Transferencia electrónica de fondos"));
		metodos.add(new MetodoPago("04", "Tarjeta de crédito"));
		metodos.add(new MetodoPago("05", "Monedero electrónico"));
		metodos.add(new MetodoPago("06", "Dinero electrónico"));
		metodos.add(new MetodoPago("08", "Vales de despensa"));
		metodos.add(new MetodoPago("28", "Tarjeta de débito"));
		metodos.add(new MetodoPago("29", "Tarjeta de servicio"));
		metodos.add(new MetodoPago("99", "Otros"));
		
		
		return metodos;
	}
	
	public static String getNombreCompleto(String valor) {
		String nombreCompleto = "";
		switch (valor){
			case "01":
				nombreCompleto = "01 - Efectivo";
				break;
		
			case "02":
				nombreCompleto = "02 - Cheque nominativo";
				break;
				
			case "03":
				nombreCompleto = "03 - Transferencia electrónica de fondos";
				break;
				
			case "04":
				nombreCompleto = "04 - Tarjeta de crédito";
				break;
				
			case "05":
				nombreCompleto = "05 - Monedero electrónico";
				break;
				
			case "06":
				nombreCompleto = "06 - Dinero electrónico";
				break;
				
			case "08":
				nombreCompleto = "08 - Vales de despensa";
				break;
				
			case "28":
				nombreCompleto = "28 - Tarjeta de débito";
				break;
				
			case "29":
				nombreCompleto = "29 - Tarjeta de servicio";
				break;
				
			case "99":
				nombreCompleto = "99 - Otros";
				break;
		
		}
		
		
		return nombreCompleto;
	}

}
