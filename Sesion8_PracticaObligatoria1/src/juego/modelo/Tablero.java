package juego.modelo;
import juego.util.Sentido;

/**
 * Tablero compuesto por celdas. El tablero esta compuesto por n celdas, en las que insertamos piezas de distintas figuras a lo largo del juego.
 * 
 * @author Iván Ruiz Gázquez
 * @since JDK 11
 * @version 1.0
 * 
 */
public class Tablero {
	/**
	 * Número de filas.
	 */
	static final int NUMERO_FILAS = 8;
	
	/**
	 * Número de columnas.
	 */
	static final int NUMERO_COLUMNAS = 8;
	
	/**
	 * Celda del tablero.
	 */
	private Celda[][] celdas;
	
	/**
	 * Une todas las celdas en un array unidimensional.
	 */
	private Celda[] celdasLineales;
	/**
	 * Establecemos el numero de celdas del tablero.
	 */
	public Tablero() {
	
		this.celdas = new Celda[NUMERO_FILAS][NUMERO_COLUMNAS];
		this.celdasLineales = new Celda[NUMERO_FILAS*NUMERO_COLUMNAS];
		
		for(int i=0; i<NUMERO_FILAS; i++)
			for(int j=0; j<NUMERO_COLUMNAS; j++)
				celdas[i][j] = new Celda(i, j);
	}
	
	/**
	 * Coloca la pieza en la celda.
	 * 
	 * @param pieza Pieza.
	 * @param celda Celda.
	 */
	public void colocar(Pieza pieza, Celda celda) {
		pieza.establecerCelda(celda);
		celda.establecerPieza(pieza);
	}
	
	/**
	 * Coloca la pieza en una posición dada.
	 * 
	 * @param pieza Pieza a colocar.
	 * @param fila Fila.
	 * @param columna Columna.
	 */
	public void colocar(Pieza pieza, int fila, int columna) {
		pieza.establecerCelda(this.obtenerCelda(fila, columna));
		this.obtenerCelda(fila, columna).establecerPieza(pieza);
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
	 * Obtiene la celda según la notación algebraica.
	 * 
	 * @param texto Coordenadas.
	 * @return Celda.
	 */
	public Celda obtenerCeldaParaNotacionAlgebraica(String texto) {
		char letra = texto.charAt(0);
		char numero = texto.charAt(1);
								
		if(letra>=97 && letra<97+NUMERO_COLUMNAS && numero>=49 && numero<49+NUMERO_FILAS)
			return this.obtenerCelda((int)(56-numero), (int)(letra-97));
		
		return null;
	}
	
	/**
	 * Obtiene las celdas.
	 * 
	 * @return Celdas.
	 */
	public Celda[] obtenerCeldas() {
		if(this.celdas.length != 0) {
			int k=0;
			for(int i=0; i<this.celdas.length; i++)
				for(int j=0; j<this.celdas[0].length; j++, k++) {
					this.celdasLineales[k] = this.celdas[i][j];
				}
			return this.celdasLineales;
		}
		
		return null;
	}
	
	/**
	 * Obtienen la coordenada en notación algebraica.
	 * 
	 * @param celda Celda del tablero.
	 * @return Coordenada.
	 */
	public String obtenerCoordenadaEnNotacionAlgebraica(Celda celda) {
		int letra = celda.obtenerColumna();
		int numero = celda.obtenerFila();
				
		if(letra>=0 && letra<NUMERO_COLUMNAS && numero>=0 && numero<NUMERO_FILAS)
			return (char)(letra+97)+""+(char)(56-numero);
		
		return null;
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
	 * Obtiene el número de filas del tablero.
	 * 
	 * @return Número de filas.
	 */
	public int obtenerNumeroFilas() {
		return this.celdas.length;
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
	 * Obtiene el sentido desde la celda origen a la celda destino.
	 * 
	 * @param origen Celda origen.
	 * @param destino Celda destino.
	 * @return Sentido.
	 */
	public Sentido obtenerSentido(Celda origen, Celda destino) {
		int filaOrigen, columnaOrigen, filaDestino, columnaDestino;
		Sentido sentido = null;
		
		filaOrigen = origen.obtenerFila();
		columnaOrigen = origen.obtenerColumna();
		filaDestino = destino.obtenerFila();
		columnaDestino = destino.obtenerColumna();
		
		if(filaOrigen == filaDestino && columnaOrigen != columnaDestino) {
			if(columnaOrigen-columnaDestino < 0)
				sentido = Sentido.HORIZONTAL_E;
			else
				sentido = Sentido.HORIZONTAL_O;
		}
		else if(filaOrigen != filaDestino && columnaOrigen == columnaDestino) {
			if(filaOrigen-filaDestino < 0)
				sentido = Sentido.VERTICAL_S;
			else
				sentido = Sentido.VERTICAL_N;
		}
		else if(filaOrigen != filaDestino && columnaOrigen != columnaDestino) {
			if (Math.abs(filaOrigen-filaDestino) == Math.abs(columnaOrigen-columnaDestino)) {
				if(filaOrigen-filaDestino < 0 && columnaOrigen-columnaDestino < 0)
					sentido = Sentido.DIAGONAL_SE;
				else if(filaOrigen-filaDestino < 0 && columnaOrigen-columnaDestino > 0)
					sentido = Sentido.DIAGONAL_SO;
				else if(filaOrigen-filaDestino > 0 && columnaOrigen-columnaDestino < 0)
					sentido = Sentido.DIAGONAL_NE;
				else if(filaOrigen-filaDestino > 0 && columnaOrigen-columnaDestino > 0)
					sentido = Sentido.DIAGONAL_NO;
			}
		}
		return sentido;
	}
	
	/**
	 *  Devuelve el tablero en formato de caracteres.
	 *  
	 *  @return Tablero compuesto de caracteres representativos de colores.
	 */
	public String toString() {
		String tablero[][] = new String[this.obtenerNumeroFilas()][this.obtenerNumeroColumnas()];
		String tabString = "\n"+NUMERO_FILAS+" ";
		
		// Número total de filas y columnas
		int numFilas = this.obtenerNumeroFilas();
		int numColumnas = this.obtenerNumeroColumnas();
		
		for(int i=0; i<numFilas; i++) {
			for(int j=0; j<numColumnas; j++) {
				// Si la celda tiene un tipo
				if(this.celdas[i][j].obtenerPieza() != null)
					// Sacamos el caracter del tipo en esa posición.
					tablero[i][j] = (" "+this.celdas[i][j].obtenerPieza().obtenerTipo().toChar()+""+this.celdas[i][j].obtenerPieza().obtenerColor().toChar());
				// Si la celda no tiene una pieza
				else
					// Rellenamos con el valor '-' como celda vacia
					tablero[i][j] = " --";
				
				// Rellenamos el string con el valor que toque en la celda
				tabString += tablero[i][j];
			}
			
			// If para evitar añadir último salto de línea
			if(i<(numFilas-1))
				tabString += "\n"+(NUMERO_FILAS-(i+1))+" ";
		}
		tabString += "\n\n   a  b  c  d  e  f  g  h";
		return tabString;
	}
}
