package accesosecuencial;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/*
 * Crea un programa que abra un fichero y busque un texto para cada aparición, que muestre la línea y la columna (posición).
 */
public class BuscaTexto {

	public static void main(String[] args) {

		String nombreFichero = args[0];
		
		if (args.length < 1) {
			System.out.println("Indica el nombre del fichero.");
			return;
		}

		String textoParaBuscar = " ";
		
		try (BufferedReader br = new BufferedReader(new FileReader(nombreFichero))) {

			int i = 0;
			int posicion = 0;
			String linea = br.readLine();
			while (linea != null) {

				posicion = linea.indexOf(textoParaBuscar);
				
				if (posicion != -1) {
					System.out.format("[%5d][%5d] %s", i++, posicion, linea);
					System.out.println();
				}
				
				linea = br.readLine();

			}

		} catch (FileNotFoundException e) {

			System.out.println("No existe el fichero " + nombreFichero);

		} catch (IOException e) {

			System.out.println("Error de E/S: " + e.getMessage());

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}
