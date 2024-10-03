package dniexcepcion;

public class DniExcepcion {

	public static void main(String[] args) {
		
		String dniPrueba = "17764869Z";
		
		try {
			
			esValidoDNI(dniPrueba);
			
		} catch (Exception e) {
			
			//System.err.println(e.getMessage());
			
		}
		
		System.out.println("El dni " + dniPrueba + " es " + (esValidoDNI(dniPrueba) ? "válido." : "erróneo."));
		
	}
	
	public static boolean esValidoDNI (String dni) {
		
		int resto = 23;
		int longitudDNI = 9;
		
		boolean esValido = false;
		String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
		
		String cifras = dni.substring(0, longitudDNI - 1);
		int cifrasInt = Integer.parseInt(cifras);
		
		if (dni.length() != longitudDNI) {
			
			System.err.println("Longitud no válida");
			
		}
		
		char letraDni = dni.charAt(longitudDNI - 1);
		
		if (letras.charAt((cifrasInt % resto)) == letraDni) {
			
			esValido = true;
			
		}
		
		return esValido;
		
	}
	
}
