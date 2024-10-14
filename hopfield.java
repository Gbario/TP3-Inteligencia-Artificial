
public class hopfield {

	final static int alto = 10;	// Número de filas
	final static int ancho = 10; // Número de columnas

	
	// Matrices de patrones para el entrenamiento
	final static int[][] circulo1 = { // Circulo 1
	        { 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 }, 
	        { 0, 1, 0, 0, 1, 0, 0, 0, 0, 0 },
	        { 1, 0,0, 0, 0, 1, 0, 0, 0, 0 }, 
	        { 1, 0, 0, 0, 0, 1, 0, 0, 0, 0 },
	        { 1, 0, 0, 0, 0, 1, 0, 0, 0, 0 }, 
	        { 0, 1, 0, 0, 1, 0, 0, 0, 0, 0 },
	        { 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 }, 
	        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
	        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } 
	};
	
	final static float[] centroCirculo1 = {3.5f, 4};	// Coordenadas del centro del circulo 1

	final static int[][] circulo2 = { // Circulo 2
	        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
	        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
	        { 0, 0, 0, 0, 0, 0, 0, 1, 1, 0 },
	        { 0, 0, 0, 0, 0, 0, 1, 0, 0, 1 }, 
	        { 0, 0, 0, 0, 0, 0, 1, 0, 0, 1 },
	        { 0, 0, 0, 0, 0, 0, 1, 0, 0, 1 }, 
	        { 0, 0, 0, 0, 0, 0, 0, 1, 1, 0 },
	        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
	        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } 
	};
	final static float[] centroCirculo2 = {8.5f, 6};	// Coordenadas del centro del circulo 2
	
	final static int[][] circulo3 = { // Circulo 3
	        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
	        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
	        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
	        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	        { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 }, 
	        { 0, 0, 0, 1, 0, 1, 0, 0, 0, 0 },
	        { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 }, 
	        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } 
	};
	final static float[] centroCirculo3 = {5, 8};	// Coordenadas del centro del circulo 3
	
	final static int[][] circulo1Ruido = { // Circulo 1 con ruido
			{ 0, 1, 0, 1, 0, 0, 0, 0, 0, 0 }, 
	        { 1, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
	        { 1, 0,0, 0, 0, 1, 0, 0, 0, 0 }, 
	        { 0, 0, 0, 0, 0, 1, 0, 0, 0, 0 },
	        { 0, 0, 0, 0, 0, 1, 0, 0, 0, 0 }, 
	        { 1, 1, 0, 0, 1, 0, 0, 0, 0, 0 },
	        { 1, 0, 1, 1, 0, 0, 0, 0, 0, 0 }, 
	        { 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
	        { 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 }, 
	        { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 } 
	};

	
	final static int[][] circulo2Ruido = { // Circulo 2 con ruido
	        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
	        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 }, 
	        { 0, 0, 0, 0, 0, 0, 0, 1, 1, 1 },
	        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 }, 
	        { 0, 0, 0, 0, 0, 1, 0, 0, 0, 1 },
	        { 0, 0, 0, 0, 0, 0, 1, 0, 0, 1 }, 
	        { 0, 0, 0, 0, 0, 0, 1, 1, 1, 0 },
	        { 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 }, 
	        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } 
	};
	
	final static int[][] circulo3Ruido = { // Circulo 3 con ruido
	        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
	        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
	        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
	        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	        { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 }, 
	        { 0, 0, 0, 1, 0, 1, 0, 0, 0, 0 },
	        { 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 }, 
	        { 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 } 
	};
	
	static void delay(int milisegundos) { // Función delay para evitar errores de impresión en la consola
		try {
			Thread.sleep(milisegundos);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	
	
	static void imprimirMatriz(int[][] matriz) { // Mostrar matriz en consola
		for (int i = 0; i < alto; i++) {
			for (int j = 0; j < ancho; j++) {
				delay(5);
				if (matriz[i][j] == 1) {
					System.err.print(" " + matriz[i][j] + " ");// Resaltar perimetro del circulo en rojo

				} else {
					System.out.print(" " + matriz[i][j] + " ");
				}

			}
			delay(1);
			System.out.println();
		}
		System.out.println();
	}

	
	
	static void imprimirVector(int[] vector) {	// Funcion para imprimir un vector
		System.out.print("[");
		for (int i = 0; i < alto * ancho; i++) {
			delay(5);
			if (vector[i] <= 0) {

				System.out.print(" " + vector[i] + " ");

			} else {

				System.err.print(" " + vector[i] + " ");
			}
		}
		System.out.println("]");
	}

	
	
	static int[] vectorizarPatron(int[][] matriz) {	// Funcion para vectorizar una matriz (convertirla a un vector unidimensional)
		int[] matrizVectorizada = new int[alto * ancho];
		int a = 0;
		for (int i = 0; i < alto; i++) {
			for (int j = 0; j < ancho; j++) {
				matrizVectorizada[a] = matriz[i][j];
				a++;
			}
		}
		return matrizVectorizada;
	}

	
	
	static int[][] matrizarVector(int[] vector) {  // Función para convertir un vector unidimensional a una matriz bidimensional
		int[][] vectorMatrizado = new int[alto][ancho];
		int a = 0;
		for (int i = 0; i < alto; i++) {
			for (int j = 0; j < ancho; j++) {
				vectorMatrizado[i][j] = vector[a];
				a++;
			}
		}
		return vectorMatrizado;
	}

	
	
	static int[][] calcularMatrizPesos(int[] matrizVectorizada) {   // Funcion para calcular la matriz de pesos utilizando el patrón vectorizado

		int[][] matrizPesos = new int[matrizVectorizada.length][matrizVectorizada.length];
		for (int i = 0; i < matrizVectorizada.length; i++) {
			for (int j = 0; j < matrizVectorizada.length; j++) {
				if (i != j) { // Evita la diagonal
					matrizPesos[i][j] = matrizVectorizada[i] * matrizVectorizada[j]; // Multiplicamos la matriz vectorizada por su transpuesta para obtener la matriz de pesos
				} else {
					matrizPesos[i][j] = 0; // Establece 0 en la diagonal
				}
			}
		}
		System.out.println();
		delay(5);
		return matrizPesos;
	}

	
	
	static int[][] sumarMatricesDePesos(int[][] matriz1, int[][] matriz2, int[][] matriz3) {	// Método que suma las tres matrices de pesos y devuelve la matriz combinada
		int[][] matrizCombinacion = new int[matriz1.length][matriz1.length];
		for (int i = 0; i < matriz1.length; i++) {
			for (int j = 0; j < matriz1.length; j++) {
				matrizCombinacion[i][j] = matriz1[i][j] + matriz2[i][j] + matriz3[i][j];  // Suma los valores de las tres matrices en la posición [i][j]
			}
		}
		System.out.println();
		return matrizCombinacion;
	}

	
	
	static int[] reconstruccion(int[] entradaVectorizada, int[][] matrizCombinada) {	// Método que reconstruye un vector a partir de la entrada vectorizada y la matriz combinada
		int[] vectorReconstruido = new int[entradaVectorizada.length];

		for (int i = 0; i < entradaVectorizada.length; i++) {
			int suma = 0;
			for (int j = 0; j < entradaVectorizada.length; j++) {
				suma += (entradaVectorizada[j] * matrizCombinada[i][j]);	// Suma ponderada

			}
			if (suma > 0) { 	// Aplica la función de activación: si la suma es mayor que 0, establece el valor a 1; de lo contrario, a 0
				vectorReconstruido[i] = 1;
			} else {
				vectorReconstruido[i] = 0;
			}
		}
		return vectorReconstruido;
	}

	static boolean checkearCoincidencia(int[][] matrizResultado) {	// Método que verifica si la matriz resultado coincide con alguna de las matrices de referencia
		boolean coincideConCirculo1 = true;
		boolean coincideConCirculo2 = true;
		boolean coincideConCirculo3 = true;

		for (int i = 0; i < alto; i++) { // Checkear coincidencia con circulos
			for (int j = 0; j < ancho; j++) {
				if (matrizResultado[i][j] != circulo1[i][j]) {
					coincideConCirculo1 = false;
				}
				if (matrizResultado[i][j] != circulo2[i][j]) {
					coincideConCirculo2 = false;
				}
				if (matrizResultado[i][j] != circulo3[i][j]) {
					coincideConCirculo3 = false;
				}
			}
		}

		delay(5);
		if (coincideConCirculo1 == true) {
			System.err.println("Coincide con el circulo 1");
			System.out.println("Su centro está en x:" + centroCirculo1[0] + " y:" + centroCirculo1[1]);
			return true;
		} else if (coincideConCirculo2 == true) {
			System.err.println("Coincide con el circulo 2");
			System.out.println("Su centro está en x:" + centroCirculo2[0] + " y:" + centroCirculo2[1]);
			return true;
		} else if (coincideConCirculo3 == true) {
			System.err.println("Coincide con el circulo 3");
			System.out.println("Su centro está en x:" + centroCirculo3[0] + " y:" + centroCirculo3[1]);
			return true;
		} else {
			System.err.println("No coincide con ningun circulo");
			return false;
		}
	}

	static void iterar(int[][] entrada, int[][] matrizCombinada) {	// Método que itera sobre la entrada y reconstruye la matriz
		System.out.println("--------------------------------------------");
		System.out.println("Vector de entrada:");
		imprimirMatriz(entrada);														// Imprime la matriz de entrada
		int[] entradaVectorizada = vectorizarPatron(entrada); 							// Vectoriza la entrada
		boolean coincide = false;
		int iteracion = 1;																// Contador de iteraciones
		int[] vectorReconstruido = new int[entradaVectorizada.length];
		int[][] matrizReconstruida = new int[alto][ancho];
		while (coincide == false) {														// Mientras no haya coincidencia sigue iterando
			vectorReconstruido = reconstruccion(entradaVectorizada, matrizCombinada);	// Reconstrucción del vector
			System.out.println("Reconstrucción iteración " + iteracion + ": ");
			matrizReconstruida = matrizarVector(vectorReconstruido);					// Convierte el vector en matriz
			imprimirMatriz(matrizReconstruida);
			if (checkearCoincidencia(matrizReconstruida) == true) {						// Verifica si coincide con algún círculo
				System.out.println();
				System.out.println();
				coincide = true;														//Si coincide sale del loop
			} else if (iteracion > 50) {
				System.err.println("Error: La matriz no se ha podido reconstruir");		// Mensaje de error si no se reconstruye
				break;																	// Sale del bucle si se excede el número máximo de iteraciones
			} else {
				entradaVectorizada = vectorReconstruido;								// Si no encuentra coincidencia actualiza el vector de entrada
				iteracion++;															// Incrementa el contador de iteraciones
			}
		}
	}

	

	public static void main(String[] args) {

		// Imprimir matrices
		System.out.println("Circulo 1:");
		imprimirMatriz(circulo1);
		System.out.println("Circulo 2:");
		imprimirMatriz(circulo2);
		System.out.println("Circulo 3:");
		imprimirMatriz(circulo3);

		// Vectorizar las matrices
		int[] matrizVectorizada1 = vectorizarPatron(circulo1);
		int[] matrizVectorizada2 = vectorizarPatron(circulo2);
		int[] matrizVectorizada3 = vectorizarPatron(circulo3);

		// Calcular las matrices de pesos para cada circulo
		int[][] matrizPesos1 = calcularMatrizPesos(matrizVectorizada1);
		int[][] matrizPesos2 = calcularMatrizPesos(matrizVectorizada2);
		int[][] matrizPesos3 = calcularMatrizPesos(matrizVectorizada3);

		// Calcula la matriz de pesos combinada
		int[][] matrizCombinada = sumarMatricesDePesos(matrizPesos1, matrizPesos2, matrizPesos3);
		System.out.println("Matriz combinada de pesos:");
		imprimirMatriz(matrizCombinada);

		System.out.println("Prueba de imagen de circulo 1 con ruido:");
		iterar(circulo1Ruido, matrizCombinada);

		System.out.println("Prueba de imagen de circulo 2 con ruido:");
		iterar(circulo2Ruido, matrizCombinada);

		System.out.println("Prueba de imagen de circulo 3 con ruido:");
		iterar(circulo3Ruido, matrizCombinada);

	}
}
