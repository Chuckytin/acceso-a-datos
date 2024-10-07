package excepcionescontrhows;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/*
 * Declaración de excepciones lanzadas por método de clase con throws
 */
public class ExcepcionesConThrows {

	public static void main(String[] args) {
		
		try {
			
			File f = new ExcepcionesConThrows().crearFicheroTempConChar("AAAA_", 'A', 20);
			System.out.println("Fichero creado: " + f.getAbsolutePath());
			f.delete();
			
		} catch (Exception e) {
			
			System.err.println(e.getMessage());
			
		}
		
	}
	
	public File crearFicheroTempConChar (String prefNomFich, char caracter, int numRep) throws IOException {
		
		File f = File.createTempFile(prefNomFich, "");
		FileWriter fw = new FileWriter(f);
		
		for (int i = 0; i < numRep; i++) {
			fw.write(caracter);
		}
		
		fw.close();
		
		return f;
		
	}
	
}
