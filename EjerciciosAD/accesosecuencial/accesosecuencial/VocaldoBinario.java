package accesosecuencial;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.InputStream;

/*
Lee el código, entiéndelo y coméntalo.
Completa el catch.
¿Sirve para ficheros de texto? Prueba
Modificarlo para poder elegir el PrintStream de salida.
 */
public class VocaldoBinario {

	static int TAM_FILA = 32;
	static int MAX_BYTES = 2048;
	InputStream is = null;

	public VocaldoBinario (InputStream is) {
		this.is = is;
	}

	public void volcar() throws IOException {
		
		byte buffer[] = new byte[TAM_FILA];
		int bytesLeidos;
		int offset=0;

		do{
			
			bytesLeidos=is.read(buffer);
			System.out.format("[%5d]", offset);
			
			for(int i = 0; i < bytesLeidos; i++){
				System.out.format(" %2x", buffer[i]);
			}
			
			offset += bytesLeidos;
			System.out.println();
			
		} while (bytesLeidos == TAM_FILA && offset < MAX_BYTES);
		
	}

	public static void main(String[] args) {
		
		if (args.length < 1) {
			System.out.println("No se ha indicado ningun fichero");
			return;
		}
		
		String nombreFichero = args[0];
		
		try (FileInputStream fis = new FileInputStream(nombreFichero)) {
			
			VocaldoBinario lectura = new VocaldoBinario(fis);
			lectura.volcar();
			
		} catch (FileNotFoundException e) {
			
			System.err.println("ERROR: No existe fichero " + nombreFichero);
			
		} catch (IOException e) {
			
			System.err.println("ERROR de E/S: " + e.getMessage());
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}

}
