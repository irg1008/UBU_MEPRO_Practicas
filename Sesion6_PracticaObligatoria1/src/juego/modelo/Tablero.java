package juego.modelo;
import juego.util.Direccion;

/**
 * Tablero compuesto por celdas. El tablero esta compuesto por n celdas, en las que insertamos piezas de distintos colores a lo largo del juego.
 * 
 * @author Iván Ruiz Gázquez
 * @since JDK 11
 * @version 1.0
 * 
 */
public class Tablero {
	
	/**
	 * Celda del tablero.
	 */
	private Celda[][] celdas;
	
	/**
	 * Establecemos el numero de celdas del tablero.
	 * 
	 * @param fila Numero de filas del tablero.
	 * @param columna Número de columnas del tablero.
	 */
	public Tablero(int fila, int columna) {
		// Minimo valor de fila y columna es 1
		if(fila<1)
			fila = 1;
		if(columna<1)
			columna = 1;
		
		celdas = new Celda[fila][columna];
		
		for(int i=0; i<fila; i++)
			for(int j=0; j<columna; j++)
				celdas[i][j] = new Celda(i, j);
	}
	
	/**
	 * Coloca las piezas en la celda dada.
	 * 
	 * @param pieza Pieza a colocar en la celda.
	 * @param celda Celda en la que colocamos la pieza.
	 */
	public void colocar(Pieza pieza, Celda celda) {
		pieza.establecerCelda(celda);
		celda.establecerPieza(pieza);
	}
	
	/**
	 * Obtiene la celda si está entre los límites del tablero.
	 * 
	 * @param fila Fila de la celda.
	 * @param columna Columna de la celda.
	 * @return Retorna la celda o nulo.
	 */
	public Celda obtenerCelda(int fila, int columna) {
		if(fila<0 || fila>=this.obtenerNumeroFilas() || columna<0 || columna>=this.obtenerNumeroColumnas())
			return null;
		else
			return celdas[fila][columna];
	}
		
	/**
	 * Cuenta las piezas de un mismo color en un solo sentido.
	 * 
	 * @param color Color de la pieza.
	 * @param filaCelda Fila en la que empezar a contar.
	 * @param columnaCelda Columna en la que empezar a contar.
	 * @param dirX Dirección en el eje de las columnas.
	 * @param dirY Dirección en el eje de las filas.
	 * @return Número de piezas contadas en una dirección y en un sentido.
	 */
	private int privCuentaPiezas(Color color, int filaCelda, int columnaCelda, int dirX, int dirY) {
		int numPiezas = 0;
				
		// Número total de filas y columnas
		int numFilas = this.obtenerNumeroFilas();
		int numColumnas = this.obtenerNumeroColumnas();
				
		for(int i=filaCelda, j=columnaCelda; i<numFilas && j<numColumnas && i>=0 && j>=0; i+=dirY, j+=dirX) {
			if(this.celdas[i][j].obtenerPieza() !=null) {
				if(this.celdas[i][j].obtenerPieza().obtenerColor() == color)
					numPiezas++;
				else
					break;
			}
		}
		return numPiezas;
	}
	
	/**
	 * Cuenta el número de piezas consecutivas. En una dirección y en ambos sentidos.
	 * 
	 * @param celda Celda inicial.
	 * @param direccion Dirección de busqueda.
	 * @return Numero de piezas del mismo color.
	 */
	public int contarPiezas(Celda celda, Direccion direccion) {
		int numPiezasMismoColor = 0;
		
		// Solo ejecutamos si la celda no esta vacia
		if(celda.estaVacia() == false) {
						
			// Fila y columna a estudiar
			int filaCelda = celda.obtenerFila();
			int columnaCelda = celda.obtenerColumna();
			
			// Color a estudiar
			Color color = celda.obtenerPieza().obtenerColor();
			
			// Direcciones
			int dirX = 1;
			int dirY = 1;
			
			switch(direccion) {
				case HORIZONTAL:
					dirY = 0;
					break;
					
				case VERTICAL:
					dirX = 0;
					break;
					
				case DIAGONAL_SO_NE:
					dirY = -1;
					break;
					
				case DIAGONAL_NO_SE:
					break;
			}
			// Cuenta hacia un lado
			numPiezasMismoColor += privCuentaPiezas(color, filaCelda, columnaCelda, dirX, dirY);
			// Cuenta hacia el otro lado
			numPiezasMismoColor += privCuentaPiezas(color, filaCelda, columnaCelda, -dirX, -dirY);
			numPiezasMismoColor--;			
		}
		return numPiezasMismoColor;
	}
	
	/**
	 * Obtiene el número de piezas del mismo color en el tablero.
	 * 
	 * @param color Color a buscar.
	 * @return Numero de piezas del mismo color.
	 */
	public int obtenerNumeroPiezas(Color color) {
		int mismoColor = 0;
		
		// Número total de filas y columnas
		int numFilas = this.obtenerNumeroFilas();
		int numColumnas = this.obtenerNumeroColumnas();
		
		for(int i=0; i<numFilas; i++)
			for(int j=0; j<numColumnas; j++)
				if(this.celdas[i][j].estaVacia() == false)
					if(this.celdas[i][j].obtenerPieza().obtenerColor() == color)
						mismoColor++;
				
		return mismoColor;
	}

	/**
	 * Obtiene el número de filas del tablero.
	 * 
	 * @return Número de filas.
	 */
	public int obtenerNumeroFilas() {
		return this.celdas.length;
	}
	
	/**
	 * Obtiene el número de columnas del tablero.
	 * 
	 * @return Número de columnas.
	 */
	public int obtenerNumeroColumnas() {
		return this.celdas[0].length; 
	}
	
	/**
	 *  Comprueba si la coordenada dada esta entre los límites del tablero.
	 *  
	 * @param fila Fila a estudiar.
	 * @param columna Columna a estudiar.
	 * @return Si está entre los límites.
	 */
	public boolean estaEnTablero(int fila, int columna) {
		boolean flag = false;
		
		if(fila>=0 && fila<obtenerNumeroFilas() && columna>=0 && columna<obtenerNumeroColumnas())
			flag = true;
				
		return flag;
	}
	
	/**
	 *  Devuelve si la celda esta vacia.
	 *  
	 * @return Celda vacia.
	 */
	public boolean estaCompleto() {
		boolean flag = true;
		
		// Número total de filas y columnas
		int numFilas = this.obtenerNumeroFilas();
		int numColumnas = this.obtenerNumeroColumnas();
		
		for(int i=0; i<numFilas; i++)
			for(int j=0; j<numColumnas; j++)
				if(this.celdas[i][j].estaVacia()) {
					flag = false;
				}

		return flag;
	}
	
	/**
	 *  Devuelve el tablero en formato de caracteres.
	 *  
	 *  @return Tablero compuesto de caracteres representativos de colores.
	 */
	public String toString() {
		char tablero[][] = new char[this.obtenerNumeroFilas()][this.obtenerNumeroColumnas()];
		String tabString = "";
		
		// Número total de filas y columnas
		int numFilas = this.obtenerNumeroFilas();
		int numColumnas = this.obtenerNumeroColumnas();
		
		for(int i=0; i<numFilas; i++) {
			for(int j=0; j<numColumnas; j++) {
				// Si la celda tiene una pieza
				if(this.celdas[i][j].obtenerPieza() != null)
					// Sacamos el caracter del color de esa pieza
					tablero[i][j] = this.celdas[i][j].obtenerPieza().obtenerColor().toChar();
				// Si la celda no tiene una pieza
				else
					// Rellenamos con el valor '-' como celda vacia
					tablero[i][j] = '-';
				
				// Rellenamos el string con el valor que toque en la celda
				tabString += tablero[i][j];
			}
			
			// If para evitar añadir último salto de línea
			if(i<(numFilas-1))
				tabString += "\n";
		}
		
		return tabString;
	}
}
