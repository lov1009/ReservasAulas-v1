package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;

public class Profesores {

	/*
	 * Modifica la clase Profesores para que utilice un ArrayList en vez de un
	 * Array. Modifica también los métodos, eliminando los necesarios, para que
	 * sigan haciendo lo mismo pero utilizando esta estructura. Ten en cuenta que el
	 * método representar ahora también devuelve una lista. Haz un commit.
	 */

	private List<Profesor> profesores;
	
	public Profesores() {
		
		profesores = new ArrayList<>();
	}
	

	public Profesores(Profesores profesores) {

		if (profesores == null) {
			throw new NullPointerException("ERROR: No se pueden copiar profesores nulos.");
		}
		setProfesores(profesores);

	}

	private void setProfesores(Profesores profesores) {

		this.profesores = copiaProfundaProfesores(profesores.getProfesores());
	}

	public List<Profesor> getProfesores() {

		return copiaProfundaProfesores(profesores);
	}

	private List<Profesor> copiaProfundaProfesores(List<Profesor> profesores) {

		List<Profesor> copiaProfesores = new ArrayList<>();

		Iterator<Profesor> it = profesores.iterator();

		while (it.hasNext()) {
			copiaProfesores.add(new Profesor(it.next()));

		}
		return copiaProfesores;
	}

	public int getNumProfesores() {

		return profesores.size();
	}

	public void insertar(Profesor profesor) throws OperationNotSupportedException {
		if (profesor == null) {
			throw new NullPointerException("ERROR: No se puede insertar un profesor nulo.");
		}

		if (buscar(profesor) != null) {
			throw new OperationNotSupportedException("ERROR: Ya existe un profesor con ese nombre.");
		}
		profesores.add(new Profesor(profesor));

	}

	public Profesor buscar(Profesor profesor) {
		if (profesor == null) {
			throw new NullPointerException("ERROR: No se puede buscar un profesor nulo.");
		}
		if (profesores.contains(profesor)) {
			return new Profesor(profesor);
		}
		return null;
	}

	public void borrar(Profesor profesor) throws OperationNotSupportedException {
		if (profesor == null) {
			throw new NullPointerException("ERROR: No se puede borrar un profesor nulo.");
		}
		if (buscar(profesor) == null) {
			throw new OperationNotSupportedException("ERROR: No existe ningún profesor con ese nombre.");
		}
		profesores.remove(new Profesor(profesor));
	}

	public List<String> representar() {

		List<String> representacion = new ArrayList<>();
		
		

		Iterator<Profesor> it = profesores.iterator();

		while (it.hasNext()) {
			representacion.add(it.next().toString());

		}
		return representacion;
	}

}
