package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;

public class Reservas {
	/*
	 * Modifica la clase Reservas para que utilice un ArrayList en vez de un Array.
	 * Modifica también los métodos, eliminando los necesarios, para que sigan
	 * haciendo lo mismo pero utilizando esta estructura. Ten en cuenta que el
	 * método representar ahora también devuelve una lista. Haz un commit.
	 */

	private List<Reserva> reservas;

	public Reservas() {

		reservas = new ArrayList<>();
	}

	public Reservas(Reservas reservas) {

		if (reservas == null) {
			throw new NullPointerException("ERROR: No se pueden copiar reservas nulas.");
		}

		setReservas(reservas);
	}

	private void setReservas(Reservas reservas) {

		this.reservas = copiaProfundaReservas(reservas.getReservas());
	}

	public List<Reserva> getReservas() {

		return copiaProfundaReservas(reservas);
	}

	private List<Reserva> copiaProfundaReservas(List<Reserva> reservas) {

		List<Reserva> copiaReservas = new ArrayList<>();

		Iterator<Reserva> it = reservas.iterator();

		while (it.hasNext()) {
			copiaReservas.add(new Reserva(it.next()));

		}
		return copiaReservas;
	}

	public int getNumReservas() {

		return reservas.size();
	}

	public void insertar(Reserva reserva) throws OperationNotSupportedException {
		if (reserva == null) {
			throw new NullPointerException("ERROR: No se puede realizar una reserva nula.");
		}
		if (buscar(reserva) != null) {
			throw new OperationNotSupportedException("ERROR: La reserva ya existe.");
		}
		reservas.add(new Reserva(reserva));
		System.out.println("La reserva " + reserva + " se ha realizado correctamente.");
	}

	/*
	 * private boolean esMesSiguienteOPosterior(Reserva reserva): Comprueba que la
	 * reserva pasada como parámetro es una reserva para el mes siguiente o
	 * posteriores. En ese caso devolverá true, y en otro caso, false.
	 * ----------------------------------------------------------------------------
	 * private List getReservasProfesorMes(Profesor profesor, LocalDate mes):
	 * Devuelve una lista de todas la reservas del profesor (pasado como parámetro)
	 * para el mes indicado como parámetro.
	 * ----------------------------------------------------------------------------
	 * private Reserva getReservaAulaDia(Aula aula, LocalDate dia): Devuelve un
	 * objeto de tipo reserva si existe una reserva para el aula indicado como
	 * parámetro en el día pasado como parámetro. En caso de que no exista dicha
	 * reserva devuelve null.
	 */

	// TODO comparar primero año y luego mes
	
	private boolean esMesSiguienteOPosterior(Reserva reserva) {

		if (reserva.getPermanencia().getDia().getYear() > LocalDate.now().getYear()) {
			return true;
			
		} else if (reserva.getPermanencia().getDia().getYear() < LocalDate.now().getYear()) {
			return false;
			
		} else {
			
			if (reserva.getPermanencia().getDia().getMonthValue() > LocalDate.now().getMonthValue()) {
				return true;
			}
			return false;
		}
	}

	

	// TODO
	private List<Reserva> getReservasProfesorMes(Profesor profesor, LocalDate mes) {

		List<Reserva> reservasProfeMes = new ArrayList<>();

		Iterator<Reserva> it = reservas.iterator();

		while (it.hasNext()) {
			Reserva reservaActual = it.next();
			if (reservaActual.getProfesor().equals(profesor)
					&& reservaActual.getPermanencia().getDia().getMonth().equals(mes.getMonth())) {
				reservasProfeMes.add(new Reserva(reservaActual));
			}
		}
		return reservasProfeMes;

	}

	// TODO
	private Reserva getReservaAulaDia(Aula aula, LocalDate dia) {

		Iterator<Reserva> it = reservas.iterator();

		while (it.hasNext()) {
			Reserva reservaActual = it.next();
			if (reservaActual.getAula().equals(aula) && reservaActual.getPermanencia().getDia().equals(dia)) {
				return new Reserva(reservaActual);
			}
		}
		return null;
	}

	public Reserva buscar(Reserva reserva) {
		if (reserva == null) {
			throw new NullPointerException("ERROR: No se puede buscar un reserva nula.");
		}
		if (reservas.contains(reserva)) {
			return new Reserva(reserva);
		}
		return null;
	}

	public void borrar(Reserva reserva) throws OperationNotSupportedException {
		if (reserva == null) {
			throw new NullPointerException("ERROR: No se puede anular una reserva nula.");
		}

		if (buscar(reserva) == null) {
			throw new OperationNotSupportedException("ERROR: La reserva a anular no existe.");
		}
		reservas.remove(new Reserva(reserva));
		System.out.println("La reserva " + reserva + " se ha borrado con éxito.");
	}

	public List<String> representar() {

		List<String> representacion = new ArrayList<>();

		Iterator<Reserva> it = reservas.iterator();

		while (it.hasNext()) {
			representacion.add(it.next().toString());
		}

		return representacion;
	}

	public List<Reserva> getReservasProfesor(Profesor profesor) {
		if (profesor == null) {
			throw new NullPointerException("ERROR: El profesor no puede ser nulo.");
		}

		Iterator<Reserva> it = reservas.iterator();

		List<Reserva> reservasProfesor = new ArrayList<>();

		while (it.hasNext()) {
			Reserva reservaActual = it.next();

			if (reservaActual.getProfesor().equals(profesor)) {
				reservasProfesor.add(new Reserva(reservaActual));
			}
		}
		return reservasProfesor;

	}

	public List<Reserva> getReservasAula(Aula aula) {
		if (aula == null) {
			throw new NullPointerException("ERROR: El aula no puede ser nula.");
		}

		Iterator<Reserva> it = reservas.iterator();

		List<Reserva> reservasAula = new ArrayList<>();

		while (it.hasNext()) {
			Reserva reservaActual = it.next();

			if (reservaActual.getAula().equals(aula)) {
				reservasAula.add(new Reserva(reservaActual));
			}
		}
		return reservasAula;
	}

	public List<Reserva> getReservasPermanencia(Permanencia permanencia) {
		if (permanencia == null) {
			throw new NullPointerException("ERROR: La permanencia no puede ser nula.");
		}
		Iterator<Reserva> it = reservas.iterator();

		List<Reserva> reservasPermanencia = new ArrayList<>();

		while (it.hasNext()) {
			Reserva reservaActual = it.next();

			if (reservaActual.getPermanencia().equals(permanencia)) {
				reservasPermanencia.add(new Reserva(reservaActual));
			}
		}
		return reservasPermanencia;

	}

	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {
		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede consultar la disponibilidad de un aula nula.");
		}

		if (permanencia == null) {
			throw new NullPointerException("ERROR: No se puede consultar la disponibilidad de una permanencia nula.");
		}

		Iterator<Reserva> it = reservas.iterator();

		while (it.hasNext()) {
			Reserva reservaActual = it.next();

			if (reservaActual.getPermanencia().equals(permanencia) && reservaActual.getAula().equals(aula)) {
				return false;
			}
		}
		return true;
	}
}
