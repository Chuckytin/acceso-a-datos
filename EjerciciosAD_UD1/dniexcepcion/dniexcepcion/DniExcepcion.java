package dniexcepcion;

public class DniExcepcion {

	public static void main(String[] args) {

		String dniPrueba = "177634869Z";

		esValidoDNI(dniPrueba);

		System.out.println("El dni " + dniPrueba + " es " + (esValidoDNI(dniPrueba) ? "válido." : "erróneo."));

	}

	private static boolean esValidoDNI (String dni) {

		int resto = 23;
		int longitudDNI = 9;

		boolean esValido = false;
		String letras = "TRWAGMYFPDXBNJZSQVHLCKE";

		try {

			String cifras = dni.substring(0, longitudDNI - 1);
			int cifrasInt = Integer.parseInt(cifras);

			if (dni.length() != longitudDNI) { 
				
				return esValido;
				
			} else {
				
				char letraDni = dni.charAt(longitudDNI - 1);

				if (letras.charAt((cifrasInt % resto)) == letraDni) {

					esValido = true;

				}
				
			}

		} catch (Exception e) {

			System.err.println(e.getMessage());

		}

		return esValido;

	}

}
