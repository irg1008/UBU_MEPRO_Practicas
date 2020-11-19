package genericidad;
import java.util.ArrayList;
import java.util.List;
//import musica.Instrumento;

/**
 * 
 * @author Iván Ruiz Gázquez
 *
 */
public class Lista<E extends /*Instrumento*/ Object> {
	
	private List<E> elementos;
	
	public Lista (int numero) {
		elementos = new ArrayList<E>();
		for (int i=0; i<numero; i++)
			elementos.add(null);
	}
	
	public int size () {
		return elementos.size();
	}
	
	public void set (int posicion, E valor) {
		if(posicion>= 0 && posicion < size())
			elementos.set(posicion, valor);
	}
	
	public E get (int posicion) {
		if(posicion>= 0 && posicion < size())
			return elementos.get(posicion);
		return null;
	}
	
	
}
