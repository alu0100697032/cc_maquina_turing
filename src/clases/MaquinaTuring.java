package clases;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MaquinaTuring {

	/*
	 * ATRIBUTOS
	 */

	private ArrayList<String> conjuntoQ; // conjunto estados
	private ArrayList<String> conjuntoSigma; // alfabeto entrada
	private ArrayList<String> conjuntoR; // alfabeto cina
	private ArrayList<String> conjuntoF; // conjuno estados finales
	private ArrayList<String[]> conjuntoTransiciones;
	private String estadoInicial;
	private String simboloBlanco;
	private String estadoActual;

	/*
	 * CONSTRUCTOR
	 */

	public MaquinaTuring() throws IOException {

		conjuntoQ = new ArrayList<String>();
		conjuntoSigma = new ArrayList<String>();
		conjuntoR = new ArrayList<String>();
		conjuntoF = new ArrayList<String>();
		conjuntoTransiciones = new ArrayList<String[]>();

		String nombreFichero = "mt1.txt";
		File ruta;
		Scanner imputNombreFichero = new Scanner(System.in);
		// Pedir el fichero al usuario
		/*
		 * System.out.println(
		 * "Introduzca el nombre del fichero con los datos de la máquina de turing:"
		 * ); nombreFichero = imputNombreFichero.nextLine(); //(para versión
		 * final)
		 */
		ruta = new File(nombreFichero);
		// Almacenar la informacion del fichero
		String textoFichero;
		// FileReader leerFichero = new FileReader("./" + ruta);
		FileReader leerFichero = new FileReader("./src/" + ruta);
		BufferedReader bufferLectura = new BufferedReader(leerFichero);
		int linea = 0;
		while ((textoFichero = bufferLectura.readLine()) != null) {
			if (textoFichero.matches("#.*"))
				continue;
			else if (textoFichero.matches("\b*"))
				continue;
			else {
				if (linea >= 6) {
					String separarEspacios[] = textoFichero.split(" ");
					conjuntoTransiciones.add(separarEspacios);
				} else {
					String separarEspacios[] = textoFichero.split(" ");
					for (int i = 0; i < separarEspacios.length; i++) {
						if (separarEspacios[i].matches("#.*"))
							break;
						else if (separarEspacios[i].matches("\t*"))
							break;
						else {
							if (linea == 0)
								conjuntoQ.add(separarEspacios[i]);
							else if (linea == 1)
								conjuntoSigma.add(separarEspacios[i]);
							else if (linea == 2)
								conjuntoR.add(separarEspacios[i]);
							else if (linea == 3)
								estadoInicial = separarEspacios[i];
							else if (linea == 4)
								simboloBlanco = separarEspacios[i];
							else if (linea == 5)
								conjuntoF.add(separarEspacios[i]);
						}
					}
				}// END IF ELSE INTERIOR
				linea++;
			}// END IF ELSE EXTERIOR
		}// END WHILE
		bufferLectura.close();
	}

	/*
	 * MOSTRAR INFORMACION DE LA MÁQUINA DE TURING
	 */

	public void mostrarInformacionAutomata() {
		System.out.println("Información del autómata con pila");
		System.out.println();
		System.out.println("Estado inicial: " + estadoInicial);
		System.out.println("Símbolo blanco: " + simboloBlanco);
		System.out.println("Conjunto de estados: " + conjuntoQ.toString());
		System.out
				.println("Alfabeto del lenguaje: " + conjuntoSigma.toString());
		System.out.println("Alfabeto de la cinta: " + conjuntoR.toString());
		System.out.println("Conjunto de estados finales: "
				+ conjuntoF.toString());
		System.out.println("Conjunto de transiciones:");
		for (int i = 0; i < conjuntoTransiciones.size(); i++)
			System.out.println(Arrays.toString(conjuntoTransiciones.get(i)));
		System.out.println("");
	}

	/*
	 * EJECUTAR MAQUINA DE TURING
	 */

	public void ejecutarMaquinaTuring() {

		String cadenaEntrada;
		int posicionCabezaLecturaEscitura = 0;
		estadoActual = estadoInicial;
		// El usuario inserta la cadena
		System.out.println("Inserte la cadena a probar:");
		// System.out.println("(Introduzca siempre '@' al final)");
		Scanner imputUsuario = new Scanner(System.in);
		cadenaEntrada = imputUsuario.nextLine();
		char cadenaCinta[] = cadenaEntrada.toCharArray();
		imputUsuario.close();
		// Empezamos a evaluar la cadena de entrada
		boolean noTransiciones = false;

		while (noTransiciones == false) {

			noTransiciones = true;// suponemos, a priori, que no hay
									// transiciones

			for (int j = 0; j < conjuntoTransiciones.size(); j++) {
		
				// si encuentra alguna transicion entra
				if (estadoActual.equals(conjuntoTransiciones.get(j)[0])
						&& cadenaCinta[posicionCabezaLecturaEscitura] == conjuntoTransiciones
								.get(j)[1].charAt(0)) {

					estadoActual = conjuntoTransiciones.get(j)[2];
					cadenaCinta[posicionCabezaLecturaEscitura] = conjuntoTransiciones
							.get(j)[3].charAt(0);
					if (conjuntoTransiciones.get(j)[4].equals("R")
							&& cadenaCinta.length > posicionCabezaLecturaEscitura + 1)
						posicionCabezaLecturaEscitura++;
					else if (conjuntoTransiciones.get(j)[4] == "L"
							&& posicionCabezaLecturaEscitura > 0)
						posicionCabezaLecturaEscitura--;
					else if (conjuntoTransiciones.get(j)[4] == "R"
							&& cadenaCinta.length <= posicionCabezaLecturaEscitura + 1) {
						cadenaCinta = (String.valueOf(cadenaCinta) + "@")
								.toCharArray();
						posicionCabezaLecturaEscitura++;
					} else if (conjuntoTransiciones.get(j)[4] == "L"
							&& posicionCabezaLecturaEscitura <= 0) {
						cadenaCinta = ("@" + String.valueOf(cadenaCinta))
								.toCharArray();
						posicionCabezaLecturaEscitura--;
					}
					noTransiciones = false;

					break;// si se encuentra la transicion pasa al siguiente
							// simbolo de la cadena
				}
			}// END FOR
		}// END WHILE (NO QUEDAN TRANSICIONES)
		System.out.println(String.valueOf(cadenaCinta));
		cadenaEsAceptada();
	}

	public void cadenaEsAceptada(){
		for(int i = 0; i < conjuntoF.size(); i++){
			if(estadoActual.equals(conjuntoF.get(i))){
				System.out.println("Cadena aceptada!");
				break;
			}else
				System.out.println("Cadena no aceptada");
		}
	}
	/*
	 * GETER Y SETTER
	 */

	/**
	 * @return the conjuntoSigma
	 */
	public ArrayList<String> getConjuntoSigma() {
		return conjuntoSigma;
	}

	/**
	 * @param conjuntoSigma
	 *            the conjuntoSigma to set
	 */
	public void setConjuntoSigma(ArrayList<String> conjuntoSigma) {
		this.conjuntoSigma = conjuntoSigma;
	}

	/**
	 * @return the conjuntoQ
	 */
	public ArrayList<String> getConjuntoQ() {
		return conjuntoQ;
	}

	/**
	 * @param conjuntoQ
	 *            the conjuntoQ to set
	 */
	public void setConjuntoQ(ArrayList<String> conjuntoQ) {
		this.conjuntoQ = conjuntoQ;
	}

	/**
	 * @return the conjuntoR
	 */
	public ArrayList<String> getConjuntoR() {
		return conjuntoR;
	}

	/**
	 * @param conjuntoR
	 *            the conjuntoR to set
	 */
	public void setConjuntoR(ArrayList<String> conjuntoR) {
		this.conjuntoR = conjuntoR;
	}

	/**
	 * @return the conjuntoF
	 */
	public ArrayList<String> getConjuntoF() {
		return conjuntoF;
	}

	/**
	 * @param conjuntoF
	 *            the conjuntoF to set
	 */
	public void setConjuntoF(ArrayList<String> conjuntoF) {
		this.conjuntoF = conjuntoF;
	}

	/**
	 * @return the conjuntoTransiciones
	 */
	public ArrayList<String[]> getConjuntoTransiciones() {
		return conjuntoTransiciones;
	}

	/**
	 * @param conjuntoTransiciones
	 *            the conjuntoTransiciones to set
	 */
	public void setConjuntoTransiciones(ArrayList<String[]> conjuntoTransiciones) {
		this.conjuntoTransiciones = conjuntoTransiciones;
	}

	/**
	 * @return the estadoInicial
	 */
	public String getEstadoInicial() {
		return estadoInicial;
	}

	/**
	 * @param estadoInicial
	 *            the estadoInicial to set
	 */
	public void setEstadoInicial(String estadoInicial) {
		this.estadoInicial = estadoInicial;
	}

	/**
	 * @return the estadoActual
	 */
	public String getEstadoActual() {
		return estadoActual;
	}

	/**
	 * @param estadoActual
	 *            the estadoActual to set
	 */
	public void setEstadoActual(String estadoActual) {
		this.estadoActual = estadoActual;
	}

	/**
	 * @return the simboloBlanco
	 */
	public String getSimboloBlanco() {
		return simboloBlanco;
	}

	/**
	 * @param simboloBlanco
	 *            the simboloBlanco to set
	 */
	public void setSimboloBlanco(String simboloBlanco) {
		this.simboloBlanco = simboloBlanco;
	}
}
