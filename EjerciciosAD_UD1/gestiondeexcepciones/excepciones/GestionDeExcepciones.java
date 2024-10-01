package excepciones;

/*
 * Excepción: Evento que ocurre durante la ejecución de un programa e interrumpe su curso normal de ejecución.
Crea un programa que defina una función “divide” que devuelva a/b.
En el main, llámala con 5/2, 5/0 y 5/3 y muestra por pantalla la división y el resultado (5/2 = …)
Observamos la excepción.
¿De qué tipo es?
Modifica el programa y añade un bloque try / catch que capture ese tipo de excepción y muestre por stderr el error y los parámetros.
(Algunos metodos de la clase Exception: printStackTrace, getMessage, getLocalizedMessage)

 */
public class GestionDeExcepciones {

	public static void main(String[] args) {

		System.out.println("5/2 = " + divide(5, 2));
		System.out.println("5/0 = "+ divide(5, 0));
		System.out.println("5 / 3 = " + divide(5, 3));

	}

	private static double divide (int num1, int num2) {

		double resultado = 0.0d;

		try {

			resultado = num1/num2;

		} catch (ArithmeticException e) {

			System.err.println("Error al dividir por 0 " + e.getMessage());
			
		}

		return resultado;

	}

}
