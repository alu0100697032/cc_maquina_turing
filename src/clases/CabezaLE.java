package clases;

import java.util.ArrayList;

public class CabezaLE {

	/*
	 * Atributos
	 */

	private int posicionActual;

	/*
	 * Constructor
	 */

	public CabezaLE() {
		setPosicionActual(0);
	}
	/*
	 * Recibe como parametros el estado altual de la maquina, la cinta y la
	 * transicion a comprobar Devuelve el estado al que transito
	 */

	public String transitar(String estadoActual, ArrayList<String> cinta, String[] transicion) {
		String estadoSiguiente = null;
		// si puede transitar...
		if (estadoActual.equals(transicion[0]) && cinta.get(getPosicionActual()).equals(transicion[1])) {

			estadoSiguiente = transicion[2];// actualiza el estado siguiente a
											// ir
			cinta.set(getPosicionActual(), transicion[3]);// escribe en
															// la cinta
			// se mueve
			if (transicion[4].equals("R") && cinta.size() > getPosicionActual() + 1)
				setPosicionActual(getPosicionActual() + 1);
			else if (transicion[4].equals("L") && getPosicionActual() > 0)
				setPosicionActual(getPosicionActual() - 1);
			else if (transicion[4].equals("R") && cinta.size() <= getPosicionActual() + 1) {
				cinta.add("@");
				setPosicionActual(getPosicionActual() + 1);
			} else if (transicion[4].equals("L") && getPosicionActual() <= 0) {
				cinta.add(0, "@");
				;
				setPosicionActual(0);
			}
		}
		return estadoSiguiente;
	}

	/**
	 * @return the posicionActual
	 */
	public int getPosicionActual() {
		return posicionActual;
	}

	/**
	 * @param posicionActual
	 *            the posicionActual to set
	 */
	public void setPosicionActual(int posicionActual) {
		this.posicionActual = posicionActual;
	}

}
