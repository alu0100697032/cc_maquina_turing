package clases;

public class Cinta {

	/*
	 * Atributos
	 */
	
	private char[] cadenaCinta;
	private CabezaLE cabezaLE;
	
	/*
	 * Constructor
	 */
	
	public Cinta(char[] cadena, CabezaLE cabeza){
		setCadenaCinta(cadena);
		setCabezaLE(cabeza);
	}

	public void mostrarCinta(){
		System.out.println(String.valueOf(cadenaCinta));
	}
	/**
	 * @return the cadenaCinta
	 */
	public char[] getCadenaCinta() {
		return cadenaCinta;
	}

	/**
	 * @param cadenaCinta the cadenaCinta to set
	 */
	public void setCadenaCinta(char[] cadenaCinta) {
		this.cadenaCinta = cadenaCinta;
	}

	/**
	 * @return the cabezaLE
	 */
	public CabezaLE getCabezaLE() {
		return cabezaLE;
	}

	/**
	 * @param cabezaLE the cabezaLE to set
	 */
	public void setCabezaLE(CabezaLE cabezaLE) {
		this.cabezaLE = cabezaLE;
	}
}
