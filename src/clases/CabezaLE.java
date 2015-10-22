package clases;

public class CabezaLE {

	/*
	 * Atributos
	 */
	
	private int posicionActual;
	
	/*
	 * Constructor
	 */
	
	public CabezaLE(){
		setPosicionActual(0);
	}
	/*
	 * Recibe como parametros el estado altual de la maquina, la cinta y la transicion a comprobar
	 * Devuelve el estado al que transito 
	 */
	
	public String transitar(String estadoActual, char[] cinta, String[] transicion){
		String estadoSiguiente = null;
		//si puede transitar...
		if (estadoActual.equals(transicion[0])
				&& cinta[getPosicionActual()] == transicion[1].charAt(0)) {

			estadoSiguiente = transicion[2];//actualiza el estado siguiente a ir
			cinta[getPosicionActual()] = transicion[3].charAt(0);//escribe en la cinta
			//se mueve
			if (transicion[4].equals("R")
					&& cinta.length > getPosicionActual() + 1)
				setPosicionActual(getPosicionActual()+1);
			else if (transicion[4] == "L"
					&& getPosicionActual() > 0)
				setPosicionActual(getPosicionActual()-1);
			else if (transicion[4] == "R"
					&& cinta.length <= getPosicionActual() + 1) {
				cinta = (String.valueOf(cinta) + "@")
						.toCharArray();
				setPosicionActual(getPosicionActual()+1);
			} else if (transicion[4] == "L"
					&& getPosicionActual() <= 0) {
				cinta = ("@" + String.valueOf(cinta))
						.toCharArray();
				setPosicionActual(getPosicionActual()-1);
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
	 * @param posicionActual the posicionActual to set
	 */
	public void setPosicionActual(int posicionActual) {
		this.posicionActual = posicionActual;
	}

}
