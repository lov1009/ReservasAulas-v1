package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;

public class Aulas {

	/*
	 * Modifica la clase Aulas para que utilice un ArrayList en vez de un Array.
	 * Modifica también los métodos, eliminando los necesarios, para que sigan
	 * haciendo lo mismo pero utilizando esta estructura. Ten en cuenta que el
	 * método representar ahora también devuelve una lista. Haz un commit.
	 */

	private List<Aula> aulas;

	public Aulas() {

		aulas = new ArrayList<>();

	}

	public Aulas(Aulas aulas) {

		if (aulas == null) {
			throw new NullPointerException("ERROR: No se pueden copiar aulas nulas.");
		}
		setAulas(aulas);

	}

	private void setAulas(Aulas aulas) {

		this.aulas = copiaProfundaAulas(aulas.getAulas());
	}

	public List<Aula> getAulas() {

		return copiaProfundaAulas(aulas);
	}

	private List<Aula> copiaProfundaAulas(List<Aula> aulas) {

		List<Aula> copiaAulas = new ArrayList<>();

		Iterator<Aula> it = aulas.iterator();

		while (it.hasNext()) {
			copiaAulas.add(new Aula(it.next()));

		}
		return copiaAulas;
	}

	public int getNumAulas() {

		return aulas.size();
	}

	public void insertar(Aula aula) throws OperationNotSupportedException {
		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede insertar un aula nula.");
		}
		if (buscar(aula) != null) {
			throw new OperationNotSupportedException("ERROR: Ya existe un aula con ese nombre.");
		}
		aulas.add(new Aula(aula));

	}

	public Aula buscar(Aula aula) {
		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede buscar un aula nula.");
		}
		if (aulas.contains(aula)) {
			return new Aula(aula);
		}
		return null;
	}

	public void borrar(Aula aula) throws OperationNotSupportedException {
		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede borrar un aula nula.");
		}
		if (buscar(aula) == null) {
			throw new OperationNotSupportedException("ERROR: No existe ningún aula con ese nombre.");
		}
		aulas.remove(new Aula(aula));
	}

	public List<String> representar() {

		List<String> representacion = new ArrayList<>();

		Iterator<Aula> it = aulas.iterator();

		while (it.hasNext()) {
			representacion.add(it.next().toString());

		}
		return representacion;

	}
}
