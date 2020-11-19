package musica;

/**
 * Instrumento de cuerda.
 * 
 * @author Iván Ruiz Gázquez
 * @since JDK 11
 * @version 1.0
 * 
 */
public class Cuerda extends Instrumento{
	
	public Cuerda(float precio) {
		super(precio);
	}
	
	@Override
	public void tocar(Nota nota) {		
		System.out.printf("Cuerda.tocar(): %s%n", nota.toString());
	}

	@Override
	public String toString() {
		return "[" + this.consultarPrecio() +  ":Cuerda]";
	}
}
