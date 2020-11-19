package musica;

/**
 * Musica.
 *
 * @author <a href="mailto:rmartico@ubu.es">Raúl Marticorena</a>
 * @version 1.0
 */
public class Musica {

	private static void afinar(Musical m) {
		m.tocar(obtenerNotaAleatoria());
	}
	
	public static void main(String args[]){
		for(int i=0; i<10; i++) {
			Musical musical = obtenerInstrumentoAleatorio();
			afinar(musical);
		}
	}
	
	private static Nota obtenerNotaAleatoria() {
		Nota[] notas = Nota.values();
		int posicion = (int) (Math.random() * notas.length);
				return notas[posicion];
	}
	
	private static Musical obtenerInstrumentoAleatorio() {
		int numero = (int) (Math.random()*3);
		switch(numero) {
			case 0: 
				return new Viento(10.0F);
			case 1: 
				return new Metal(11.0F);
			case 2: 
				return new Cuerda(19.0F);
		}
		throw new RuntimeException("Error de programación.");
	}
}