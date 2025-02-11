package accesosecuencial;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/*
 * Uso de readLine() de BufferedReader()
 */
public class BufferedReaderReadLine {

	public static void main(String[] args) {
		
		if (args.length < 1) {
			System.out.println("Indica el nombre del fichero.");
			return;
		}
		
		String nombreFichero = args[0];
		
		try (BufferedReader br = new BufferedReader(new FileReader(nombreFichero))) {
			
			int i = 0;
			String linea = br.readLine();
			while (linea != null) {
				
				System.out.format("[%5d] %s", i++, linea);
				System.out.println();
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
