package clases;

import java.util.ArrayList;

public class Cinta {

	/*
	 * Atributos
	 */

	private ArrayList<String> cadenaCinta;
	private CabezaLE cabezaLE;

	/*
	 * Constructor
	 */

	public Cinta(ArrayList<String> cadena, CabezaLE cabeza) {
		setCadenaCinta(cadena);
		setCabezaLE(cabeza);
	}

	public void mostrarCinta() {
		System.out.println(cadenaCinta);
	}

	/**
	 * @return the cadenaCinta
	 */
	public ArrayList<String> getCadenaCinta() {
		return cadenaCinta;
	}

	/**
	 * @param cadenaCinta
	 *            the cadenaCinta to set
	 */
	public void setCadenaCinta(ArrayList<String> cadenaCinta) {
		this.cadenaCinta = cadenaCinta;
	}

	/**
	 * @return the cabezaLE
	 */
	public CabezaLE getCabezaLE() {
		return cabezaLE;
	}

	/**
	 * @param cabezaLE
	 *            the cabezaLE to set
	 */
	public void setCabezaLE(CabezaLE cabezaLE) {
		this.cabezaLE = cabezaLE;
	}
}
