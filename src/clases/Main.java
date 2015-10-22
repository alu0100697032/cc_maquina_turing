package clases;

import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		MaquinaTuring maquinaTuring = new MaquinaTuring();
		maquinaTuring.mostrarInformacionAutomata();
		//ejecutar varias veces
		while(true){
			maquinaTuring.ejecutarMaquinaTuring();
			System.out.println("Quiere probar otra cadena? (s/n)");
			Scanner imputUsuario = new Scanner(System.in);
			String sino = imputUsuario.nextLine();
			if(sino.equals("n"))
				break;
		}
	}

}
