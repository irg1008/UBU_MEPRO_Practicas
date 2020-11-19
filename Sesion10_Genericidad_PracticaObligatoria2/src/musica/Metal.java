package musica;

/**
 * Instrumento de metal.
 * 
 * @author Iván Ruiz Gázquez
 * @since JDK 11
 * @version 1.0
 * 
 */
public class Metal extends Instrumento{
	
	public Metal(float precio) {
		super(precio);
	}
	
	@Override
	public void tocar(Nota nota) {		
		System.out.printf("Metal.tocar(): %s%n", nota.toString());
	}

	@Override
	public String toString() {
		return "[" + this.consultarPrecio() +  ":Metal]";
	}
}
