package org.iesalandalus.programacion.reservasaulas;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.Controlador;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.Modelo;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.Vista;

public class MainApp {



	public static void main(String[] args) {
		System.out.println("Programa para la gestión de reservas de espacios del IES Al-Ándalus");

		Modelo modelo = new Modelo();
		Vista vista = new Vista();
		try {
			Controlador controlador = new Controlador(modelo, vista);
			controlador.comenzar();
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

	}

	Controlador controlador;

	public MainApp() {

	}

}
