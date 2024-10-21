package accesosecuencial;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/*
Abre un fichero escribe, cierra y vuelve a abrirlo en modo append y escribe.
Si el fichero ya existe no hace nada
¿Por qué?
 */
public class EscribeEnFlujoSalida{
	
	public static void main(String[] args) {
		
		String nombreFichero="f_texto.txt";
		File f = new File(nombreFichero);
		
		if (f.exists()) {
			System.out.println("Fichero " + nombreFichero + " ya existe. No se hace nada.");
			return;
		}

		try {
			
			BufferedWriter bfw = new BufferedWriter(new FileWriter(f)); 
			
			bfw.write(" Este es un fichero de texto. ");
			bfw.newLine();
			bfw.write(" Quizás no está del todo bien. ");
			bfw.close(); 
			
			bfw = new BufferedWriter(new FileWriter(f, true)); //true (no sobreescribe) false (sobreescribe)
			bfw.write(" Pero se puede arreglar.");
			bfw.close(); 
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}
	
}

