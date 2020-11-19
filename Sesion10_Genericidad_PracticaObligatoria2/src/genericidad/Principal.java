package genericidad;
import musica.Cuerda;
import musica.Instrumento;
import musica.Metal;
import musica.Nota;
import musica.Viento;

/**
 * 
 * @author Iván Ruiz Gázquez
 *
 */
public class Principal {
	
	public static void main(String[] args) {
		Lista<Nota> notasMusicales = new Lista<Nota>(10);
		for(int i=0; i<notasMusicales.size(); i++)
			notasMusicales.set(i, obtenerNotaAleatoria());
		mostrar(notasMusicales);
		
		Lista<Instrumento> instrumentos = new Lista<Instrumento>(10);
		for(int i=0; i<instrumentos.size(); i++)
			instrumentos.set(i, obtenerInstrumentoAleatorio());
		mostrar(instrumentos);
	}
	
	private static Nota obtenerNotaAleatoria() {
		Nota[] notas = Nota.values();
		int posicion = (int) (Math.random() * notas.length);
		return notas[posicion];
	}
	
	private static Instrumento obtenerInstrumentoAleatorio() {
		int posicion = (int) (Math.random() * 3);
		switch(posicion) {
			case 0: return new Viento(10.0F);
			case 1: return new Metal(11.0F);
			case 2: return new Cuerda(12.0F);
		}
		throw new RuntimeException("Error de programación");
	}
	
	private static void mostrar(Lista<?> lista) {
		for(int i=0; i<lista.size(); i++)
			System.out.println(lista.get(i).toString()); // Muestra métodos de object y de la clase de la que extienda la lista
	}
}
