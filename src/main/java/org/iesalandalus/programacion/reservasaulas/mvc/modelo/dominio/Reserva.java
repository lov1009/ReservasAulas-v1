package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

import java.util.Objects;

public class Reserva {

	

	Profesor profesor;
	Aula aula;
	Permanencia permanencia;

	// Constructor con parámetros
	public Reserva(Profesor profesor, Aula aula, Permanencia permanencia) {

		setProfesor(profesor);
		setAula(aula);
		setPermanencia(permanencia);

	}

	// constructor copia
	public Reserva(Reserva reserva) {

		if (reserva == null) {
			throw new NullPointerException("ERROR: No se puede copiar una reserva nula.");
		}

		setProfesor(reserva.profesor);
		setAula(reserva.aula);
		setPermanencia(reserva.permanencia);

	}

	public Profesor getProfesor() {

		return new Profesor(profesor);
	}

	private void setProfesor(Profesor profesor) {
		if (profesor == null) {

			throw new NullPointerException("ERROR: La reserva debe estar a nombre de un profesor.");
		}

		this.profesor = new Profesor(profesor);
	}

	public Aula getAula() {

		return new Aula(aula);
	}

	private void setAula(Aula aula) {

		if (aula == null) {
			throw new NullPointerException("ERROR: La reserva debe ser para un aula concreta.");
		}
		this.aula = new Aula(aula);
	}

	public Permanencia getPermanencia() {

		return new Permanencia(permanencia);
	}

	private void setPermanencia(Permanencia permanencia) {

		if (permanencia == null) {
			throw new NullPointerException("ERROR: La reserva se debe hacer para una permanencia concreta.");
		}
		this.permanencia = new Permanencia(permanencia);
	}

	@Override
	public int hashCode() {

		return Objects.hash(aula, permanencia);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reserva other = (Reserva) obj;
		return Objects.equals(aula, other.aula) && Objects.equals(permanencia, other.permanencia);
	} //equals ya t dice si es true o false

	@Override
	public String toString() {
		return "Profesor=" + profesor + ", aula=" + aula + ", permanencia=" + permanencia;
	}

}
