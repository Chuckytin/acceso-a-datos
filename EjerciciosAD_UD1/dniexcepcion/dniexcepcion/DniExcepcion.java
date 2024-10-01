package dniexcepcion;

public class DniExcepcion {

	public static void main(String[] args) {
		
		String dniPrueba = "17764969Z";
		
		System.out.println("El dni " + dniPrueba + " es " + (esValidoDNI(dniPrueba) ? "válido." : "erróneo."));
		
	}
	
	public static boolean esValidoDNI (String dni) {
		
		int resto = 23;
		int longitudDNI = 9;
		
		
		boolean esValido = false;
		String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
		
		String cifras = "";
		
		if (dni.length() != longitudDNI) {
			
			System.err.println("Longitud no válida");
			
		}
		
		for (int i = 0; i < dni.length() - 1; i++) {
			
			cifras += dni.charAt(i);
			
		}
		
		int cifrasInt = Integer.parseInt(cifras);
		
		if (letras.indexOf(cifrasInt % resto) == dni.indexOf(dni.length())) {
			
			esValido = true;
			
		}
		
		return esValido;
		
	}
	
}
