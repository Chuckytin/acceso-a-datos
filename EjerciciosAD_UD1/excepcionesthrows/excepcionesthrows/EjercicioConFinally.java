package excepcionesthrows;

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

			Random random = new Random();
			int falloTras = random.nextInt(3) + 1;

			if (falloTras <= 1) {
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
			if (fm1 != null) { 
				fm1.delete(); 
				System.out.println("Fichero temporal " + fm1.getName() + " borrado.");
			}

			if (ifs2 != null) ifs2.close();
			if (fm2 != null) { 
				fm2.delete(); 
				System.out.println("Fichero temporal " + fm2.getName() + " borrado.");
			}

			System.out.println("Liberando recursos: FIN.");

		}

	}

}
