/**
*
* @ Autor: Iván Ruiz Gázquez
* @ Mod: 5:00 PM - 04/10/2019 (DD/MM/AAAA)
* @ Juega con un array de enteros sacándolo por pantalla normal y al cuadrado
* @ TODO -> Completo
*
*/

public class Tabla {

	public static void main (String[] args) {
		int m = 3;
		int n = 3;
		int[][] tabla = new int[m][n];
		
		System.out.printf(toString(tabla));	
	}
	
	// Inicializa Tabla
	static void inicializarTabla(int[][] tabla) {
		for(int i = 0,  j = 0, d = 1; j < tabla.length; i++, d++) {
			
			tabla[j][i] = d; 
			
			if(i == tabla[0].length -1) {
				i = -1;
				j++;
			}
		}
	}
	

	String toString(tabla) {
		char tablero[][] = new char[3][3];
		String tabString = "";
		
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				if(tabla[i][j] != null)
					tablero[i][j] = 'X';
				else
					tablero[i][j] = '-';
				
				tabString += tablero[i][j];
			}
			tabString += "\n";
		}
		
		return tabString;
	}
	
	// Hace el cuadrado de sus componentes
	static void cuadrados(int[][] tabla) {
		for(int i = 0,  j = 0; j < tabla.length; i++) {
			
			tabla[j][i] *= tabla[j][i]; 
			
			if(i == tabla[0].length -1) {
				i = -1;
				j++;
			}
		}
	}
	
	// Saca los valores por pantalla
	static void mostrar(int[][] tabla) {
		System.out.print("\n");
		for(int i = 0,  j = 0; j < tabla.length; i++) {
			
			if(i == 0)
				System.out.print("\n");
			
			System.out.print("\t" + tabla[j][i]);
			
			
			if(i == tabla[0].length -1) {
				i = -1;
				j++;
			}
		}
	}
	
}