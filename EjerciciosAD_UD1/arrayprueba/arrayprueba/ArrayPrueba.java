package arrayprueba;

/*
 * A la hora de capturar excepciones hay que diferenciar todas las conocidas y dejar una genérica para lo inesperado.
Vamos a probar:
Crea un array bidimensional int[2][3] y rellenalo nums[i][j]=i+j;
Recórrelo con dos bucles anidados for i=[0,3) for j=[0,3)
5*nums[i][j]
Captura las distintas excepciones que puedan surgir
 */
public class ArrayPrueba {

	public static void main(String[] args) {

		int [][] arrayBidimensional = new int [2][3];

		try {
			
			crearArray(arrayBidimensional);
			
			mostrarArray(arrayBidimensional);
			
		} catch (Exception e) {
			
			System.err.println(e.getMessage());
			
		}

	}

	private static int[][] crearArray (int [][] arrayBidimensional) {
		
		for (int i = 0; i < arrayBidimensional.length; i++) {

			for (int j = 0; j < arrayBidimensional[i].length; j++) {

				arrayBidimensional[i][j] = i+j;

			}

		}
		
		return arrayBidimensional;
		
	}
	
	private static void mostrarArray(int [][] arrayBidimensional) {
		
		for (int i = 0; i < 3; i++) {
			
			for (int j = 0; j < 3; j++) {
				
				System.out.print(arrayBidimensional[i][j]);
				
			}
			
			System.out.println();
			
		}
		
	}
	
}
