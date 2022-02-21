package org.iesalandalus.programacion.reservasaulas.mvc.vista;

import java.time.LocalDate;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.Controlador;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Tramo;

public class Vista {

	Controlador controlador;

	private static final String ERROR = "";
	private static final String NOMBRE_VALIDO = "salón de actos";
	private static final String CORREO_VALIDO = "pepi@gmail.com";

	public Vista() {

		Opcion.setVista(this);
	}

	public void setControlador(Controlador controlador) {

		if (controlador == null) {
			throw new NullPointerException("ERROR: El controlador no puede ser nulo.");

		}
		this.controlador = controlador;
	}

	/*
	 * El método comenzar será un bucle que mostrará el menú, pedirá la opción
	 * deseada y la ejecutará, así hasta que la opción elegida sea SALIR
	 */

	public void comenzar() {

		int ordinalOpcion;
		do {
			Consola.mostrarMenu();
			ordinalOpcion = Consola.elegirOpcion();
			Opcion opcion = Opcion.getOpcionSegunOrdinal(ordinalOpcion);
			opcion.ejecutar();
		} while (ordinalOpcion != Opcion.SALIR.ordinal());

	}

	public void salir() {

		controlador.terminar();
		System.out.println("ADIÓS");

	}

	public void insertarAula() {

		Aula aula = Consola.leerAula();

		try {
			controlador.insertarAula(aula);

		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());

		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

	}

	public void borrarAula() {

		Aula aula = Consola.leerAula();

		try {
			controlador.borrarAula(aula);
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());

		}

	}

	public void buscarAula() {
		Aula aula = Consola.leerAula();

		Aula aulaEncontrada = controlador.buscarAula(aula);
		if (aulaEncontrada != null) {
			System.out.println(aulaEncontrada);
		} else
			System.out.println("ERROR: No existe el aula " + aula.getNombre());

	}


	public void listarAulas() {
		List<String> aulas = controlador.representarAulas();

		if (aulas.size() <= 0) {
			System.out.println("ERROR: No hay aulas registradas.");
		}
		for (String aula : aulas) {
			System.out.println(aula);
		}

	}

	public void insertarProfesor() {
		Profesor profesor = Consola.leerProfesor();

		try {
			controlador.insertarProfesor(profesor);

		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());

		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void borrarProfesor() {
		String profesorNombre = Consola.leerNombreProfesor();
		Profesor profesor = new Profesor(profesorNombre, CORREO_VALIDO);
		

		try {
			controlador.borrarProfesor(profesor);

		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());

		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

	}

	public void buscarProfesor() {
		String profesorNombre = Consola.leerNombreProfesor();
		Profesor profesor = new Profesor(profesorNombre, CORREO_VALIDO);
		Profesor profesorEncontrado = controlador.buscarProfesor(profesor);
		if (profesorEncontrado != null) {
			System.out.println(profesorEncontrado.toString());
		} else
			System.out.println("ERROR: No existe el profesor " + profesor.getNombre());

	}

	public void listarProfesores() {
		List<String> profesores = controlador.representarProfesores();

		if (profesores.size() <= 0) {
			System.out.println("ERROR: No hay profesores dados de alta.");
		}
		for (String profesor : profesores) {
			System.out.println(profesor);
		}

	}

	public void realizarReserva() {

		String profesorNombre = Consola.leerNombreProfesor();
		Profesor profesor = new Profesor(profesorNombre, CORREO_VALIDO);
		Profesor profesorEncontrado = controlador.buscarProfesor(profesor);

		
		if (profesorEncontrado == null) {
			System.out.println("ERROR: El profesor " + profesor.getNombre() + " no está registrado en el sistema.");
			return;
		}
	
		Reserva reserva = leerReserva(profesorEncontrado);

		
		try {
			controlador.realizarReserva(reserva);

		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());

		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());

		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	private Reserva leerReserva(Profesor profesor) {

		Aula aula = Consola.leerAula();
		Aula aulaEncontrada = controlador.buscarAula(aula);

		if (aulaEncontrada == null) {
			System.out.println("ERROR: El aula " + aula.getNombre() + " no está registrada en el sistema.");
			return null;
		}
		
		
		LocalDate dia = Consola.leerDia();
		Tramo tramo = Consola.leerTramo();

		Permanencia permanencia = new Permanencia(dia, tramo);

		return new Reserva(profesor, new Aula(aula), permanencia);

	}

	public void anularReserva() {

		String profesorNombre = Consola.leerNombreProfesor();
		Profesor profesor = new Profesor(profesorNombre, CORREO_VALIDO);
		Profesor profesorEncontrado = controlador.buscarProfesor(profesor);

		if (profesorEncontrado == null) {
			System.out.println("ERROR: No existe el profesor: " + profesor);
			return;
		}
		Reserva reserva = leerReserva(profesorEncontrado);

		try {
			controlador.anularReserva(reserva);
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());

		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());

		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarReservas() {

		List<String> reservas = controlador.representarReservas();

		if (reservas.size() <= 0) {
			System.out.println("ERROR: No hay reservas.");
		}
		for (String reserva : reservas) {
			System.out.println(reserva);
		}

	}

	public void listarReservasAula() {
		Aula aula = Consola.leerAula();
		List<Reserva> reservasAula = controlador.getReservasAula(aula);

		if (reservasAula.size() <= 0) {
			System.out.println("No hay reservas para esta aula.");
			return;
		}
		for (Reserva reserva : reservasAula) {
			System.out.println("Reservas por aula: " + reserva);
		}

	}

	public void listarReservasProfesor() {
		String profesorNombre = Consola.leerNombreProfesor();
		Profesor profesor = new Profesor(profesorNombre, CORREO_VALIDO);
		List<Reserva> reservasProfesor = controlador.getReservasProfesor(profesor);

		if (reservasProfesor.size() <= 0) {
			System.out.println("No hay reservas para este profesor.");
			return;
		}
		for (Reserva reserva : reservasProfesor) {
			System.out.println("Reservas por profesor: " + reserva);
		}

	}

	public void listarReservasPermanencia() {
		Tramo tramo = Consola.leerTramo();
		LocalDate dia = Consola.leerDia();
		Permanencia permanencia = new Permanencia(dia, tramo);

		List<Reserva> reservasPermanencia = controlador.getReservasPermanencia(permanencia);

		if (reservasPermanencia.size() <= 0) {
			System.out.println("No hay reservas para esta permanencia.");
			return;
		}
		for (Reserva reserva : reservasPermanencia) {
			System.out.println("Reservas por permanencia: " + reserva);
		}

	}

	public void consultarDisponibilidad() {
		
		Aula aula = Consola.leerAula();
		Aula aulaEncontrada = controlador.buscarAula(aula);

		if (aulaEncontrada == null) {
			System.out.println("ERROR: El aula " + aula.getNombre() + " no exite, por lo que no se puede comprobar su disponibilidad.");
			return;
		}
		
		Tramo tramo = Consola.leerTramo();
		LocalDate dia = Consola.leerDia();

		Permanencia permanencia = new Permanencia(dia, tramo);

		if (controlador.consultarDisponibilidad(aula, permanencia)) {
			System.out.println(aula + " " + permanencia + ": DISPONIBLE.");

		} else {
			System.out.println(aula + " " + permanencia + ": NO DISPONIBLE.");
		}

	}

}
