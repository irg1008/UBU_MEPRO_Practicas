package juego.modelo;
import java.util.ArrayList;
import java.util.List;
import juego.util.Sentido;
import juego.modelo.pieza.Pieza;

/**
 * Tablero compuesto por celdas. El tablero esta compuesto por n celdas, en las que insertamos piezas de distintas clases a lo largo del juego.
 * 
 * @author Iván Ruiz Gázquez
 * @since JDK 11
 * @version 2.0
 * 
 */
public class Tablero {
	/**Número de filas.*/
	public static final int NUMERO_FILAS = 8;
	
	/**Número de columnas.*/
	public static final int NUMERO_COLUMNAS = 8;
	
	/**Lista de listas horizontales, es decir, todas las celdas.*/
	private List<ArrayList<Celda>> celdas;
	
	/**
	 * Establecemos el numero de celdas del tablero.
	 */
	public Tablero() {
	
		this.celdas = new ArrayList<ArrayList<Celda>>(this.obtenerNumeroFilas());
		
		for(int i=0; i<this.obtenerNumeroFilas(); i++) {
			celdas.add(new ArrayList<Celda>(this.obtenerNumeroColumnas()));
			for(int j=0; j<this.obtenerNumeroColumnas(); j++)
				celdas.get(i).add(new Celda(i, j));
		}
	}
	
	/*************************PRIVATE********************************/
	
	/**
	 * Comprueba si tiene que mandar una excepción.
	 * 
	 * @param fila Dada la fila.
	 * @param columna Dada la columna.
	 * @throws CoordenadasIncorrectasException Excepción de coordenadas.
	 */
	private void compruebaExcepcion(int fila, int columna) throws CoordenadasIncorrectasException {
		if(this.estaFueraDeTablero(fila, columna)) {
			throw new CoordenadasIncorrectasException("\nCoordenadas Incorrectas: \n   Fila->"+fila+", Columna->"+columna);	
		}
	}
	
	/**
	 * Comprueba si tiene que mandar una excepción.
	 * 
	 * @param origen Dada una celda origen.
	 * @param destino Dada una celda destino.
	 * @throws CoordenadasIncorrectasException Excepción de coordenadas.
	 */
	private void compruebaExcepcion(Celda origen, Celda destino) throws CoordenadasIncorrectasException {
		if(this.estaFueraDeTablero(origen, destino)) {
			throw new CoordenadasIncorrectasException("\nCoordenadas Incorrectas, celda origen o destino fuera de tablero: \n   FilaOrigen->"+origen.obtenerFila()+", ColumnaOrigen->"+origen.obtenerColumna()+"\n   FilaDestino->"+destino.obtenerFila()+", ColumnaDestino->"+destino.obtenerColumna());
		}
	}
	
	/**
	 * Comprueba si la celda destino u origen está fuera de tablero.
	 * 
	 * @param origen Celda origen.
	 * @param destino Celda destino.
	 * @return Si está o no en tablero.
	 */
	private boolean estaFueraDeTablero(Celda origen, Celda destino) {
		if(this.estaFueraDeTablero(origen.obtenerFila(), origen.obtenerColumna())
				|| this.estaFueraDeTablero(destino.obtenerFila(), destino.obtenerColumna())) {
			return true;
		}
		return false;
	}
	
	/**
	 * Comprueba si la fila y columna dada se encuentra en el tablero.
	 * 
	 * @param fila Fila.
	 * @param columna Columna.
	 * @return Si está o no en el tablero.
	 */
	private boolean estaFueraDeTablero(int fila, int columna) {
		if(fila < 0 || fila >= this.obtenerNumeroFilas()
				|| columna < 0 || columna >= this.obtenerNumeroColumnas()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Devuelve el sentido en horizontal.
	 * 
	 * @param filaOrigen Fila origen.
	 * @param filaDestino Fila destino.
	 * @param columnaOrigen Columna origen.
	 * @param columnaDestino Columna destino.
	 * @return Sentido.
	 */
	private Sentido esSentidoHorizontal(int filaOrigen, int filaDestino, int columnaOrigen, int columnaDestino) {
		if(columnaOrigen-columnaDestino < 0) {
			return Sentido.HORIZONTAL_E;
		}
		else {
			return Sentido.HORIZONTAL_O;
		}
	}
	
	/**
	 * Devuelve el sentido en vertical.
	 * 
	 * @param filaOrigen Fila origen.
	 * @param filaDestino Fila destino.
	 * @param columnaOrigen Columna origen.
	 * @param columnaDestino Columna destino.
	 * @return Sentido.
	 */
	private Sentido esSentidoVertical(int filaOrigen, int filaDestino, int columnaOrigen, int columnaDestino) {
		if(filaOrigen-filaDestino < 0) {
			return Sentido.VERTICAL_S;
		}
		else {
			return Sentido.VERTICAL_N;
		}
	}
	
	/**
	 * Devuelve el sentido en diagonal.
	 * 
	 * @param filaOrigen Fila origen.
	 * @param filaDestino Fila destino.
	 * @param columnaOrigen Columna origen.
	 * @param columnaDestino Columna destino.
	 * @return Sentido.
	 */
	private Sentido esSentidoDiagonal(int filaOrigen, int filaDestino, int columnaOrigen, int columnaDestino) {
		if(filaOrigen-filaDestino < 0 && columnaOrigen-columnaDestino < 0) {
			return Sentido.DIAGONAL_SE;
		}
		else if(filaOrigen-filaDestino < 0 && columnaOrigen-columnaDestino > 0) {
			return Sentido.DIAGONAL_SO;
		}
		else if(filaOrigen-filaDestino > 0 && columnaOrigen-columnaDestino < 0) {
			return Sentido.DIAGONAL_NE;
		}
		else if(filaOrigen-filaDestino > 0 && columnaOrigen-columnaDestino > 0) {
			return Sentido.DIAGONAL_NO;
		}
		else {
			return null;
		}
	}
	
	/*************************PUBLIC********************************/
	
	/**
	 * Coloca la pieza en la celda.
	 * 
	 * @param pieza Pieza a colocar.
	 * @param celda Celda en la que colocar la pieza.
	 * @throws CoordenadasIncorrectasException Lanzamiento excepción coordenadas incorrectas.
	 */
	public void colocar(Pieza pieza, Celda celda) throws CoordenadasIncorrectasException {
		try {
			this.compruebaExcepcion(celda.obtenerFila(), celda.obtenerColumna());
			pieza.establecerCelda(celda);
			celda.establecerPieza(pieza);
		}
		catch(CoordenadasIncorrectasException e) {
			throw new CoordenadasIncorrectasException("No ha sido posible colocar la pieza", e);
		}
	}
	
	/**
	 * Coloca la pieza en una posición dada.
	 * 
	 * @param pieza Pieza a colocar.
	 * @param fila Fila.
	 * @param columna Columna.
	 * @throws CoordenadasIncorrectasException Excepción de coordenadas incorrectas.
	 */
	public void colocar(Pieza pieza, int fila, int columna) throws CoordenadasIncorrectasException {
		try {
			this.compruebaExcepcion(fila, columna);
			pieza.establecerCelda(this.obtenerCelda(fila, columna));
			this.obtenerCelda(fila, columna).establecerPieza(pieza);
		}
		catch(CoordenadasIncorrectasException e) {
			throw new CoordenadasIncorrectasException("No ha sido posible colocar la pieza", e);
		}
	}
	
	/**
	 * Obtiene la celda si está entre los límites del tablero.
	 * 
	 * @param fila Fila de la celda.
	 * @param columna Columna de la celda.
	 * @return Retorna la celda o nulo.
	 * @throws CoordenadasIncorrectasException Excepción de coordenadas. 
	 */
	public Celda obtenerCelda(int fila, int columna) throws CoordenadasIncorrectasException {
		try {
			this.compruebaExcepcion(fila, columna);
			return this.celdas.get(fila).get(columna);
		}
		catch(CoordenadasIncorrectasException e) {
			throw new CoordenadasIncorrectasException("\nError en la obtención de celdas", e);
		}
	}
	
	/**
	 * Obtiene todas las celdas en una misma lista.
	 * 
	 * @return Lista de celdas.
	 */
	public List<Celda> obtenerCeldas() {
		List<Celda> celdasLineales = new ArrayList<Celda>(this.obtenerNumeroColumnas()*this.obtenerNumeroFilas());
		if(this.celdas.size() != 0) {
			for(int i=0; i<this.obtenerNumeroFilas(); i++) {
				celdasLineales.addAll(this.celdas.get(i));
			}
			
			return celdasLineales;
		}
		return null;
	}
	
	/**
	 * Obtiene una lista con todas las celdas entre medias.
	 * 
	 * @param origen Desde el origen.
	 * @param destino Hasta el destino.
	 * @return Devuelve la lista de celdas.
	 * @throws CoordenadasIncorrectasException Coordenadas incorrectas.
	 */
	public List<Celda> obtenerCeldasEntreMedias(Celda origen, Celda destino) throws CoordenadasIncorrectasException {
		try {
			this.compruebaExcepcion(origen, destino);
			
			List<Celda> celdaEntreMedias = new ArrayList<Celda>();
			
			if(!origen.tieneCoordenadasIguales(destino)) {
					Sentido sentido = this.obtenerSentido(origen, destino);
					if(sentido != null) {
						int i = origen.obtenerFila() + sentido.obtenerDesplazamientoEnFilas();
						int j = origen.obtenerColumna() + sentido.obtenerDesplazamientoEnColumnas();
						
						while(!this.obtenerCelda(i, j).tieneCoordenadasIguales(destino)) {
							celdaEntreMedias.add(this.obtenerCelda(i, j));		
							i += sentido.obtenerDesplazamientoEnFilas();
							j += sentido.obtenerDesplazamientoEnColumnas();
						}
					}
				}
			
			return celdaEntreMedias;
		}
		catch(CoordenadasIncorrectasException e) {
			throw new CoordenadasIncorrectasException("\nError en la obtención de las celdas entre medias", e);
		}
	}
	
	/**
	 * Obtiene la celda según la notación algebraica.
	 * 
	 * @param texto Coordenadas.
	 * @return Celda.
	 * @throws CoordenadasIncorrectasException Excepción de coordenadas incorrectas.
	 */
	public Celda obtenerCeldaParaNotacionAlgebraica(String texto) throws CoordenadasIncorrectasException {
		try {
			char letra = texto.charAt(0);
			char numero = texto.charAt(1);
			
			if(letra<'a' || letra>'a'+this.obtenerNumeroColumnas()) {
				throw new CoordenadasIncorrectasException("\nLetra incorrecta\nLetra debe estar entre a y h y es: "+letra);
			}
			else if(numero<'1' || numero>'1'+this.obtenerNumeroFilas()) {
				throw new CoordenadasIncorrectasException("\nNúmero incorrecto\nNúmero debe estar entre 1 y 8 y es: "+numero);
			}
			else if((numero<'1' || numero>'1'+this.obtenerNumeroFilas()) && (letra<'a' || letra>'a'+this.obtenerNumeroColumnas())) {
				throw new CoordenadasIncorrectasException("\nLetra y número incorrecto\nLetra debe estar entre a y h y es: "+letra+"\nNúmero debe estar entre 1 y 8 y es: "+numero);
			} else {
				return this.obtenerCelda((int)('8'-numero), (int)(letra-'a'));	
			}
		}
		catch(CoordenadasIncorrectasException e) {
			throw new CoordenadasIncorrectasException("Error en conversión a celda", e);
		}
	}
	
	/**
	 * Obtienen la coordenada en notación algebraica.
	 * 
	 * @param celda Celda del tablero.
	 * @return Coordenada.
	 * @throws CoordenadasIncorrectasException Excepción de coordenadas incorrectas.
	 */
	public String obtenerCoordenadaEnNotacionAlgebraica(Celda celda) throws CoordenadasIncorrectasException {
		try {
			int letra = celda.obtenerColumna();
			int numero = celda.obtenerFila();
			
			if(letra<0 || letra>=this.obtenerNumeroColumnas()) {
				throw new CoordenadasIncorrectasException("El valor de la columna no es válido: "+letra);
			}
			else if(numero<0 || numero>=this.obtenerNumeroFilas()) {
				throw new CoordenadasIncorrectasException("El valor de la fila no es válido: "+numero);
			}
			else if((numero<0 || numero>=this.obtenerNumeroFilas()) && (letra<0 || letra>=this.obtenerNumeroColumnas())) {
				throw new CoordenadasIncorrectasException("Valor de columna no válido: "+letra+"\nValor de fila no válido "+numero);	
			}
			else {
				return (char)(letra+'a')+""+(char)('8'-numero);
			}
		}
		catch(CoordenadasIncorrectasException e) {
			throw new CoordenadasIncorrectasException("Error en conversión a notación algebráica", e);
		}
	}
	
	/**
	 * Obtiene el número de columnas del tablero.
	 * 
	 * @return Número de columnas.
	 */
	public int obtenerNumeroColumnas() {
		return NUMERO_COLUMNAS; 
	}
	
	/**
	 * Obtiene el número de filas del tablero.
	 * 
	 * @return Número de filas.
	 */
	public int obtenerNumeroFilas() {
		return NUMERO_FILAS;
	}
	
	/**
	 * Obtiene el número de piezas del mismo color en el tablero.
	 * 
	 * @param color Color a buscar.
	 * @return Numero de piezas del mismo color.
	 */
	public int obtenerNumeroPiezas(Color color) {
		try {
			int mismoColor = 0;
			
			// Número total de filas y columnas
			int numFilas = this.obtenerNumeroFilas();
			int numColumnas = this.obtenerNumeroColumnas();
			
			for(int i=0; i<numFilas; i++) {
				for(int j=0; j<numColumnas; j++) {
					if(this.obtenerCelda(i, j).estaVacia() == false) {
						if(this.obtenerCelda(i, j).obtenerPieza().obtenerColor() == color)
							mismoColor++;
					}
				}
			}
					
			return mismoColor;
		}
		catch(CoordenadasIncorrectasException e) {
			throw new RuntimeException ("No se ha podido obtener el número de piezas\n", e);
		}
	}
	
	/**
	 * Obtiene el sentido desde la celda origen a la celda destino.
	 * 
	 * @param origen Celda origen.
	 * @param destino Celda destino.
	 * @return Sentido.
	 * @throws CoordenadasIncorrectasException Excepción de coordenadas incorrectas.
	 */
	public Sentido obtenerSentido(Celda origen, Celda destino) throws CoordenadasIncorrectasException {
		try {
			this.compruebaExcepcion(origen, destino);
			
			int filaOrigen = origen.obtenerFila();
			int columnaOrigen = origen.obtenerColumna();
			int filaDestino = destino.obtenerFila();
			int columnaDestino = destino.obtenerColumna();
			Sentido sentido = null;
			
			// Horizontal
			if(filaOrigen == filaDestino && columnaOrigen != columnaDestino) {
				sentido = this.esSentidoHorizontal(filaOrigen, filaDestino, columnaOrigen, columnaDestino);
			}
			
			// Vertical
			else if(filaOrigen != filaDestino && columnaOrigen == columnaDestino) {
				sentido = this.esSentidoVertical(filaOrigen, filaDestino, columnaOrigen, columnaDestino);
			}
		
			// Diagonal
			else if(filaOrigen != filaDestino && columnaOrigen != columnaDestino) {
				if (Math.abs(filaOrigen-filaDestino) == Math.abs(columnaOrigen-columnaDestino))
					sentido = this.esSentidoDiagonal(filaOrigen, filaDestino, columnaOrigen, columnaDestino);
			}
			return sentido;
		}
		catch(CoordenadasIncorrectasException e) {
			throw new CoordenadasIncorrectasException("\nError en la correcta obtención del sentido", e);
		}
	}
	
	/**
	 *  Devuelve el tablero en formato de caracteres.
	 *  
	 *  @return Tablero compuesto de caracteres representativos de colores.
	 */
	public String toString() {
		try {
			// Número total de filas y columnas
			int numFilas = this.obtenerNumeroFilas();
			int numColumnas = this.obtenerNumeroColumnas();
			
			String tablero[][] = new String[numFilas][numColumnas];
			String tabString = "";
			
			for(int i=0; i<numFilas; i++) {
				tabString += ("\n"+(numFilas-i)+" ");
				for(int j=0; j<numColumnas; j++) {
					// Si la celda tiene un tipo
					if(this.obtenerCelda(i, j).obtenerPieza() != null) {
						// Sacamos el caracter del tipo en esa posición.
						tablero[i][j] = (" "+this.obtenerCelda(i, j).obtenerPieza().toChar()+""+this.obtenerCelda(i, j).obtenerPieza().obtenerColor().toChar());
					}
					else {
						tablero[i][j] = " --";
					}
					
					// Rellenamos el string con el valor que toque en la celda
					tabString += tablero[i][j];
				}
			}
			tabString += "\n\n   a  b  c  d  e  f  g  h";
			return tabString;
		}
		catch(CoordenadasIncorrectasException e) {
			throw new RuntimeException("No se ha podido recrear el tablero\n", e);
		}
	}
}

