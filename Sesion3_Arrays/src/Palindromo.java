/**
*
* @ Autor: Iván Ruiz Gázquez
* @ Mod: 5:00 PM - 04/10/2019 (DD/MM/AAAA)
* @ Comprueba si el array es palíndromo.
* @ TODO -> Completo
*
*/

public class Palindromo {

	public static void main (String[] args) {
		char[] texto = {'a', 'b', 'a'};
		boolean palindromo;
		
		palindromo = esPalindromo(texto);
		
		if(palindromo)
			System.out.print("El array es palíndromo");
		else
			System.out.print("El array no es palíndromo");
	}
	
	// Comprueba si el array es palíndromo
	static boolean esPalindromo(char[] texto) {
			
		for(int i=0, id = texto.length-1; i <= id; i++, id--) {
			if(texto[i] != texto[id])
				return false;
		}
		
		return true;
	}
}