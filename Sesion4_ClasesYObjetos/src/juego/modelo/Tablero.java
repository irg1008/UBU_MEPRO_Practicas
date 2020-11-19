package juego.modelo;
import juego.util.Direccion;

/**
 * 
 * @author Iván Ruiz Gázquez
 * 
 */

public class Tablero {
	
	
	private Celda[][] celdas;
	
	
	public Tablero(int fila, int columna) {
		if(fila<1)
			fila = 1;
		if(columna<1)
			columna = 1;
		
		celdas = new Celda[fila][columna];
		
		for(int i=0; i<fila; i++)
			for(int j=0; j<columna; j++)
				celdas[i][j] = new Celda(i, j);
	}
	
	public void colocar(Pieza pieza, Celda celda) {
		pieza.establecerCelda(celda);
		celda.establecerPieza(pieza);
	}
	
	public Celda obtenerCelda(int fila, int columna) {
		if((fila<0||fila>=this.obtenerNumeroFilas())||(columna<0||columna>=this.obtenerNumeroColumnas()))
			return null;
		else
			return celdas[fila][columna];
	}
	
// "Añadido por mí" TODO => Revistar funcionamineto óptimo
	// Cuenta el número de piezas consecutivas en una dirección en ambos sentidos TODO => Ver si hay errores en ejecución y mejorar algoritmo
	public int contarPiezas(Celda celda, Direccion direccion) {
		int numPiezasMismoColor = 0;
		
		if(celda.estaVacia() == false) {
			int filaCelda = celda.obtenerFila();
			int columnaCelda = celda.obtenerColumna();
			
			Color color = celda.obtenerPieza().obtenerColor();
			
			switch(direccion) {
				case HORIZONTAL:
					// Cuenta a la derecha
					for(int i=columnaCelda; i<this.obtenerNumeroColumnas(); i++) {
						if(this.celdas[filaCelda][i].obtenerPieza().obtenerColor() == color)
							numPiezasMismoColor++;
						else
							break;
					}
					
					// Cuenta a la izquierda
					for(int i=(columnaCelda-1); i>=0; i--) {
						if(this.celdas[filaCelda][i].obtenerPieza().obtenerColor() == color)
							numPiezasMismoColor++;
						else
							break;
					}
					break;
					
				case VERTICAL:
					// Cuenta hacia arriba
					for(int i=filaCelda; i<this.obtenerNumeroFilas(); i++) {
						if(this.celdas[i][columnaCelda].obtenerPieza().obtenerColor() == color)
							numPiezasMismoColor++;
						else
							break;
					}
					
					// Cuenta hacia abajo
					for(int i=(filaCelda-1); i>=0; i--) {
						if(this.celdas[i][columnaCelda].obtenerPieza().obtenerColor() == color)
							numPiezasMismoColor++;
						else
							break;
					}
					break;
					
				case DIAGONAL_SO_NE:
					// Cuenta hacia arriba-derecha
					for(int i=filaCelda, j=columnaCelda; i>=0 && j<this.obtenerNumeroColumnas(); i--, j++) {
						if(this.celdas[i][j].obtenerPieza().obtenerColor() == color)
							numPiezasMismoColor++;
						else
							break;
					}
					
					// Cuenta hacia abajo-izquierda
					for(int i=(filaCelda+1), j=(columnaCelda-1); i<this.obtenerNumeroFilas() && j>=0; i++, j--) {
						if(this.celdas[i][j].obtenerPieza().obtenerColor() == color)
							numPiezasMismoColor++;
						else
							break;
					}
					break;
					
				case DIAGONAL_NO_SE:
					// Cuenta hacia arriba-izquierda
					for(int i=filaCelda, j=columnaCelda; i>=0 && j>=0; i--, j--) {
						if(this.celdas[i][j].obtenerPieza().obtenerColor() == color)
							numPiezasMismoColor++;
						else
							break;
					}
					
					// Cuenta hacia abajo-derecha
					for(int i=(filaCelda+1), j=(columnaCelda+1); i<this.obtenerNumeroFilas() && j<this.obtenerNumeroColumnas(); i++, j++) {
						if(this.celdas[i][j].obtenerPieza().obtenerColor() == color)
							numPiezasMismoColor++;
						else
							break;
					}
					break;
			}
		}
		
		return numPiezasMismoColor;
	}
	
	// Obtiene el número de piezas del mismo color en el tablero
	public int obtenerNumeroPiezas(Color color) {
		int mismoColor = 0;
		
		for(int i=0; i<this.obtenerNumeroFilas(); i++)
			for(int j=0; j<this.obtenerNumeroColumnas(); j++)
				if(this.celdas[i][j].estaVacia() == false)
					if(this.celdas[i][j].obtenerPieza().obtenerColor() == color)
						mismoColor++;
				
		return mismoColor;
	}

	// Obtiene el número de filas del tablero
	public int obtenerNumeroFilas() {
		return this.celdas.length;
	}
	
	// Obtiene el número de columnas del tablero
	public int obtenerNumeroColumnas() {
		return this.celdas[0].length; 
	}
	
	// Comprueba si la coordenada dada esta entre los límites
	public boolean estaEnTablero(int fila, int columna) {
		boolean flag = false;
		
		if(fila < obtenerNumeroFilas() && columna < obtenerNumeroColumnas())
			flag = true;
		
		return flag;
	}
	
	// Si alguna celda esta vacia devuelve False, si no devuelve True
	public boolean estaCompleto() {
		boolean flag = true;
		
		for(int i=0; i<this.obtenerNumeroFilas(); i++)
			for(int j=0; j<this.obtenerNumeroColumnas(); j++)
				if(this.celdas[i][j].estaVacia())
					flag = false;
		
		return flag;
	}
	
	// Devuelve el estado actual del tablero
	public String toString() {
		char tablero[][] = new char[this.obtenerNumeroFilas()][this.obtenerNumeroColumnas()];
		String tabString = "";
		
		for(int i=0; i<this.obtenerNumeroFilas(); i++) {
			for(int j=0; j<this.obtenerNumeroColumnas(); j++) {
				if(this.celdas[i][j].obtenerPieza() != null)
					tablero[i][j] = this.celdas[i][j].obtenerPieza().obtenerColor().toChar();
				else
					tablero[i][j] = '-';
				
				tabString += tablero[i][j];
			}
			
			// If para evitar añadir último salto de línea
			if(i<(this.obtenerNumeroFilas()-1))
				tabString += "\n";
		}
		
		return tabString;
	}
// Fin "Añadido por mí"
	
	public static void main(String[] args) {
		Tablero tablero = new Tablero(3, 3);
		// Con 9 celdas vacias
		
		Pieza pieza00 = new Pieza(Color.BLANCO);
		Pieza pieza11 = new Pieza(Color.NEGRO);
		Pieza pieza22 = new Pieza(Color.BLANCO);
		
		Celda celda00 = tablero.obtenerCelda(0, 1);
		Celda celda11 = tablero.obtenerCelda(1, 1);
		Celda celda22 = tablero.obtenerCelda(2, 2);
		
		tablero.colocar(pieza00, celda00);
		tablero.colocar(pieza11, celda11);
		tablero.colocar(pieza22, celda22);
	}
}
