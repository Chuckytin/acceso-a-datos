package excepcionescontrhows;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

public class EjercicioConFinally {

	public static void main(String[] args) throws IOException {

		File fm1 = null;
		File fm2 = null;
		FileInputStream ifs1 = null;
		FileInputStream ifs2 = null;
		
		try {
			
			Random aleat = new Random();
			int falloTras = aleat.nextInt(3) + 1;
			
			if (falloTras<=1) {
				System.out.println("SE SALE AL LLEGAR A " + falloTras);
				return;
			}
			
			ifs1 = new FileInputStream("f1.dat");
			System.out.println("Abierto f1.dat");
			fm1 = new File("f1.info.tmp"); 
			fm1.createNewFile();
			System.out.println("Creado " + fm1.getAbsolutePath());
			
			if (falloTras <= 2) {
				System.out.println("SE SALE AL LLEGAR A " + falloTras);
				return;
			}
			
			ifs2 = new FileInputStream("f2.dat");
			System.out.println("Abierto f2.dat");
			fm2 = new File("f2.info.tmp");
			fm2.createNewFile();
			System.out.println("Creado " + fm2.getAbsolutePath());
			
			System.out.println("Ejecutado hasta el final.");
			
			
		} catch (FileNotFoundException e) {
			
			System.err.println("Fichero no encontrado: " + e.getMessage());
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally {
			
			System.out.println("Liberando recursos: INICIO.");
			if (ifs1 != null) ifs1.close();
			if (ifs2 != null) ifs2.close();
			
		}
		
	}
	
}