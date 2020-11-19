package juego.control;
import juego.modelo.Tablero;
import juego.modelo.Celda;
import juego.modelo.pieza.Alfil;
import juego.modelo.pieza.Caballo;
import juego.modelo.pieza.Dama;
import juego.modelo.pieza.Peon;
import juego.modelo.pieza.Pieza;
import juego.modelo.pieza.Rey;
import juego.modelo.pieza.Torre;
import juego.util.Sentido;
import juego.modelo.Color;
import juego.modelo.CoordenadasIncorrectasException;

/**
 * Arbitro del Ajedrez. Con el controlamos que se cumplan determinadas condiciones.
 *
 * @author Iván Ruiz Gázquez
 * @since JDK 11
 * @version 2.0
 *
 */
public class Arbitro {
	/**
	 * Tablero.
	 */
	private Tablero tablero;

	/**
	 * Turno de la partida.
	 */
	private int numeroJugada;

	/**
	 * Color del jugador que le toca mover.
	 */
	private Color colorTurno;
	
	/**
	 * Rey negro.
	 */
	private Rey reyNegro;
	
	/**
	 * Rey blanco.
	 */
	private Rey reyBlanco;

	/**
	 * Constructor del arbitro.
	 *
	 * @param tablero Tablero.
	 */
	public Arbitro(Tablero tablero) {
		this.setTablero(tablero);
		this.setTurno(0);
		this.setColorTurno(null);
	}
	
	/**
	 * Método que guarda los reyes para acceso más rápido.
	 */
	private void setReyes() {
		this.reyBlanco = (Rey) this.detectarPosicionReySegunColor(Color.BLANCO).obtenerPieza();	
		this.reyNegro = (Rey) this.detectarPosicionReySegunColor(Color.NEGRO).obtenerPieza();
	}
	
	/**
	 * Método que salva los reyes pasados por parámetro.
	 * 
	 * @param negro Rey blanco.
	 * @param blanco Rey negro.
	 */
	private void setReyes(Rey negro, Rey blanco) {
		this.reyBlanco = blanco;
		this.reyNegro = negro;
	}
	
	/**
	 * Setter del turno. Coloca el color del turno y actualiza el turno (número).
	 *
	 * @see #obtenerNumeroJugada
	 * @param turno Número del turno.
	 */
	private void setTurno(int turno) {
		this.numeroJugada = turno;
	}
	
	/**
	 * Método que establece el color del jugador que toca jugar.
	 * 
	 * @param color Color.
	 */
	private void setColorTurno(Color color) {
		this.colorTurno = color;
	}

	/**
	 * Setter del tablero.
	 *
	 * @see #getTablero
	 * @param tablero Tablero.
	 */
	private void setTablero(Tablero tablero) {
		this.tablero = tablero;
	}
	
	/**
	 * Devuelve el rey dado un color, referenciado al rey del tablero.
	 * 
	 * @param color Color del rey.
	 * @return Rey.
	 */
	private Rey getRey(Color color) {
		if(color == Color.BLANCO) {
			return this.reyBlanco;
		}
		else {
			return this.reyNegro;
		}
	}

	/**
	 * Getter del tablero.
	 *
	 * @see #setTablero
	 * @return Tablero.
	 */
	private Tablero getTablero() {
		return this.tablero;
	}
	
	/**
	 * Crea una lista inicial pasandole el color de las piezas a generar.
	 * 
	 * @param color Color de las piezas.
	 * @return La lista con los objetos en orden.
	 */
	private Pieza[] crear(Color color) {
		
		Pieza[] ordenPiezas = {
			new Torre(color),
			new Caballo(color),
			new Alfil(color),
			new Dama(color),
			new Rey(color),
			new Alfil(color),
			new Caballo(color),
			new Torre(color)
		};
		
		return ordenPiezas;
	}
	
	/**
	 * Crea un pieza. Pasándole un char identificador y un color. Usado para detección de jaque.
	 * 
	 * @param pieza Char de la pieza.
	 * @param color Color de la pieza.
	 * @return Nuevo objeto pieza de distinto tipo.
	 */
	private Pieza crear(char pieza, Color color) {
		switch(pieza) {
			case 'P': return new Peon(color);
			case 'A': return new Alfil(color);
			case 'D': return new Dama(color);
			case 'R': return new Rey(color);
			case 'T': return new Torre(color);
			case 'C': return new Caballo(color);
		}
		return null;
	}
	
	/**
	 * Comprueba que las coordenadas pasadas son correctas.
	 * 
	 * @param coordenadas Coordenadas corectas.
	 * @throws CoordenadasIncorrectasException Excepción de coordenadas erróneas.
	 */
	private void comprobarCoordenadasCorrectas(int[][] coordenadas ) throws CoordenadasIncorrectasException {
		for(int j=0; j<coordenadas[0].length; j++) {
			if(coordenadas[0][j]<0 || coordenadas[0][j]>this.getTablero().obtenerNumeroFilas()) {
				throw new CoordenadasIncorrectasException("La fila de las coordenadas es incorrecta: "+coordenadas[0][j]);
			}
			else if(coordenadas[1][j]<0 || coordenadas[1][j]>this.getTablero().obtenerNumeroColumnas()) {
				throw new CoordenadasIncorrectasException("La columna de las coordenadas es incorrecta: "+coordenadas[1][j]);
			}
			else if((coordenadas[0][j]<0 || coordenadas[0][j]>this.getTablero().obtenerNumeroFilas())
					&& (coordenadas[1][j]<0 || coordenadas[1][j]>this.getTablero().obtenerNumeroColumnas())) {
				throw new CoordenadasIncorrectasException("La fila y la columna de las coordenadas son incorrectas: \nFila->"+coordenadas[0][j]+"\nColumna->"+coordenadas[1][j]);
			}
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
		if(fila < 0 || fila >= this.getTablero().obtenerNumeroFilas()
				|| columna < 0 || columna >= this.getTablero().obtenerNumeroColumnas()) {
			return true;
		}
		return false;
	}

	/**
	 * Observa si hay alguna pieza en medio a la hora de mover.
	 *
	 * @param origen Desde el origen.
	 * @param destino Hasta el destino.
	 * @return Devuelve si la hay o no.
	 */
	private boolean hayPiezaEnMedio(Celda origen, Celda destino) {
		try {
			for(int i=0; i<this.getTablero().obtenerCeldasEntreMedias(origen, destino).size(); i++) {
				if(!this.getTablero().obtenerCeldasEntreMedias(origen, destino).get(i).estaVacia()) {
					return true;
				}
			}
			return false;
		}
		catch(CoordenadasIncorrectasException e) {
			throw new RuntimeException("No se ha podido obtener si hay una pieza en medio\n", e);
		}
	}

	/**
	 * Requisitos para el movimiento general.
	 * Lógica común a todas las piezas:<br><ul>
	 *  <li>Pueden mover solo si el destino esta vacio o hay enemigo.</li></ul>
	 *
	 * @param origen Dessde el origen.
	 * @param destino Hasta el destino.
	 * @return Devuelve si es legal.
	 */
	private boolean esLegalGeneral(Celda origen, Celda destino) {
		boolean esLegal = false;
		if((!origen.estaVacia() && (destino.estaVacia()) || destino.obtenerColorDePieza() != origen.obtenerColorDePieza())
			&& (origen.obtenerColorDePieza() == this.obtenerTurno() || this.obtenerTurno() == null)) {
			esLegal = true;
		}
				
		return esLegal;
	}

	/**
	 * Detecta la posición del rey de un color en el tablero.
	 *
	 * @param color Color del rey.
	 * @return Posición del rey.
	 */
	private Celda detectarPosicionReySegunColor(Color color) {
		try {
		Celda celdaRey = null;

		for(int i=0; i<this.getTablero().obtenerNumeroFilas(); i++) {
			for(int j=0; j<this.getTablero().obtenerNumeroColumnas(); j++) {
				if(!this.getTablero().obtenerCelda(i, j).estaVacia()) {
					if(this.getTablero().obtenerCelda(i, j).obtenerPieza().toChar() == 'R'
						&& this.getTablero().obtenerCelda(i, j).obtenerPieza().obtenerColor() == color) {
						celdaRey = this.getTablero().obtenerCelda(i, j);
					}
				}
			}
		}

		return celdaRey;
		}
		catch(CoordenadasIncorrectasException e) {
			throw new RuntimeException("Ha ocurrido un problema detectando la posición del rey", e);
		}
	}
	/********************************************/

	/**
	 * Cambia el turno.
	 */
	public void cambiarTurno() {
		if(this.obtenerTurno() == null) {
			this.setColorTurno(Color.BLANCO);
		}
		else {
			if(this.obtenerTurno() == Color.BLANCO) {
				this.setColorTurno(Color.NEGRO);
			}
			else {
				this.setColorTurno(Color.BLANCO);
			}
		}
	}

	/**
	 * Coloca el tablero en su estado inicial.
	 */
	public void colocarPiezas() {
		try {
			Pieza[][] piezasBlancas = new Pieza[2][this.getTablero().obtenerNumeroColumnas()];
			Pieza[][] piezasNegras = new Pieza[2][this.getTablero().obtenerNumeroColumnas()];
					
			for(int i=0; i<this.getTablero().obtenerNumeroColumnas(); i++) {
				piezasBlancas[0][i] = new Peon(Color.BLANCO);
				piezasNegras[1][i] = new Peon(Color.NEGRO);
			}	
			
			piezasBlancas[1] = this.crear(Color.BLANCO);
			piezasNegras[0] = this.crear(Color.NEGRO);
			
			for(int i=0; i<piezasBlancas.length; i++)
				for(int j=0; j<piezasBlancas[0].length; j++) {
					this.getTablero().colocar(piezasBlancas[i][j], this.getTablero().obtenerCelda(6+i, j));
					this.getTablero().colocar(piezasNegras[i][j], this.getTablero().obtenerCelda(i, j));
				}
	
			this.setColorTurno(Color.BLANCO);
			this.setReyes();
		}
		catch(CoordenadasIncorrectasException e) {
			throw new RuntimeException("No se han podido colocar la spiezas iniciales", e);
		}
	}
	
	/**
	 * Coloca las piezas pasadas en las coordenadas.
	 * 
	 * @param piezas Piezas a colocar.
	 * @param coordenadas Coordenadas en las que colocar las piezas.
	 * @param negro Rey negro.
	 * @param blanco Rey blanco.
	 * @throws CoordenadasIncorrectasException Excepción de coordenadas erróneas.
	 */
	public void colocarPiezas(Pieza[] piezas, int[][] coordenadas, Rey negro, Rey blanco) throws CoordenadasIncorrectasException {
		try {
			this.comprobarCoordenadasCorrectas(coordenadas);
			if(piezas.length == coordenadas.length) {
				for(int i=0; i<piezas.length; i++) {
					this.getTablero().obtenerCelda(coordenadas[i][0], coordenadas[i][1]).eliminarPieza();
					this.getTablero().colocar(piezas[i], this.getTablero().obtenerCelda(coordenadas[i][0], coordenadas[i][1]));
				}
			}
			this.setReyes(negro, blanco);
		}
		catch(CoordenadasIncorrectasException e) {
			throw new CoordenadasIncorrectasException("Error en la colocación de piezas para testeo", e);
		}
	}

	/**
	 * Comprueba la legalidad del movimiento entre celdas.
	 *
	 * @param origen Celda origen.
	 * @param destino Celda destino.
	 * @return Si es legal o no.
	 * @throws CoordenadasIncorrectasException Excepción de coordenadas erróneas.
	 */
	public boolean esMovimientoLegal(Celda origen, Celda destino) throws CoordenadasIncorrectasException {
		try {
			this.estaFueraDeTablero(origen, destino);
			boolean esLegal = false;
			if(this.esLegalGeneral(origen, destino)) {
				Sentido sentido = this.getTablero().obtenerSentido(origen, destino);
				esLegal = origen.obtenerPieza().esCorrectoMoverA(destino, sentido, this.hayPiezaEnMedio(origen, destino));
			}
			return esLegal;
		}
		catch (CoordenadasIncorrectasException e){
			throw new CoordenadasIncorrectasException("El movimiento legal no se ha podido comprobar", e);
		}
	}

	/**
	 * Comprueba si el rey está en jaque.
	 *
	 * @param color Color del rey a comprobar.
	 * @return Si el rey está en jaque.
	 */
	public boolean estaEnJaque(Color color) {
		try {
			this.getTablero().toString();
			// Celda del rey.
			Celda celdaRey = null;
			boolean enJaque = false;
			Color turno = this.obtenerTurno();
	
			// Detecta la posición del rey.
			celdaRey = this.getRey(color).obtenerCelda();
	
			// Comprueba si algún enemigo hace jaque.
			for(int i=0; i<this.getTablero().obtenerNumeroFilas(); i++) {
				for(int j=0; j<this.getTablero().obtenerNumeroColumnas(); j++) {
					if(!this.getTablero().obtenerCelda(i, j).estaVacia()) {
						this.setColorTurno(this.getTablero().obtenerCelda(i, j).obtenerColorDePieza());
						if(this.esMovimientoLegal(this.getTablero().obtenerCelda(i, j), celdaRey)) {
							enJaque = true;
						}
					}
				}
			}
						
			this.setColorTurno(turno);
			return enJaque;
		}
		catch(CoordenadasIncorrectasException e) {
			throw new RuntimeException("Error en la comprobación del jaque del rey "+color+"\n", e);
		}
	}

	/**
	 * Comprueba si el movimiento de la pieza hace que algún rey pase a estar en jaque.
	 *
	 * @param origen Origen del movimineto.
	 * @param destino Destino del movimiento.
	 * @return Devuelve si el pasaría a estar en jaque.
	 * @throws CoordenadasIncorrectasException Excepción de coordenadas erróneas.
	 */
	public boolean estaEnJaqueTrasSimularMovimientoConTurnoActual(Celda origen, Celda destino) throws CoordenadasIncorrectasException {
		try {
			this.estaFueraDeTablero(origen, destino);
			boolean estaEnJaque = false;
			Pieza piezaOrigen = this.crear(origen.obtenerPieza().toChar(), origen.obtenerColorDePieza());
			Pieza piezaDestino = null;
	
			if(!destino.estaVacia()) {
				piezaDestino = this.crear(destino.obtenerPieza().toChar(), destino.obtenerColorDePieza());
			}
			
			// Colocamos la pieza origen en el destino
			this.getTablero().colocar(origen.obtenerPieza(), destino);
			origen.eliminarPieza();
			
			this.setReyes();
			
			// Comprobamos.
			if(this.estaEnJaque(destino.obtenerColorDePieza())) {
				estaEnJaque = true;
			}
	
			// Reestablecemos las piezas movidas.
			this.getTablero().colocar(piezaOrigen, origen);
			destino.eliminarPieza();
			if(piezaDestino != null) {
				this.getTablero().colocar(piezaDestino, destino);
			}
			
			this.setReyes();
	
			return estaEnJaque;
		}
		catch(CoordenadasIncorrectasException e) {
			throw new CoordenadasIncorrectasException("No se podido comprobar el rey en jaque", e);
		}
	}
	
	/**
	 * Mueve una pieza.
	 *
	 * @param origen De una celda origen.
	 * @param destino A una celda destino.
	 * @throws CoordenadasIncorrectasException Excepción de coordenadas erróneas.
	 */
	public void mover(Celda origen, Celda destino) throws CoordenadasIncorrectasException {
		try {
			this.estaFueraDeTablero(origen, destino);
			
			// Eliminamos pieza destino si la hubiera.
			if(!destino.estaVacia()) {
				destino.eliminarPieza();
			}
	
			Pieza piezaOrigen = origen.obtenerPieza();
	
			// Colocamos la pieza origen en el destino.
			this.getTablero().colocar(piezaOrigen, destino);
			
			// Actualizamos posición de los reyes
			if(origen.obtenerPieza().toChar() == 'R') {
				this.setReyes();
			}
			
			this.setTurno(this.obtenerNumeroJugada()+1);
	
			// Marcamos que es el primer movimiento si así fuera.
			if(piezaOrigen.esPrimerMovimiento()) {
				piezaOrigen.marcarPrimerMovimiento();
			}
	
			// Borramos la pieza del origen
			origen.eliminarPieza();
		}
		catch(CoordenadasIncorrectasException e) {
			throw new CoordenadasIncorrectasException("El movimiento no se ha podido realizar", e);
		}
	}

	/**
	 * Obtiene la jugada realizada en notación algebraica.
	 *
	 * @param origen Origen movimieno.
	 * @param destino Destino movimiento.
	 * @return La notación de la jugada.
	 * @throws CoordenadasIncorrectasException Excepción de coordenadas erróneas.
	 */
	public String obtenerJugadaEnNotacionAlgebraica(Celda origen, Celda destino) throws CoordenadasIncorrectasException {
		String coordenadas = this.getTablero().obtenerCoordenadaEnNotacionAlgebraica(origen);
		coordenadas += ""+this.getTablero().obtenerCoordenadaEnNotacionAlgebraica(destino);

		return coordenadas;
	}

	/**
	 * Obtiene el número de jugada.
	 *
	 * @see #setTurno
	 * @return Número de jugada.
	 */
	public int obtenerNumeroJugada() {
		// Turno actual = numero jugadas.
		return this.numeroJugada;
	}

	/**
	 * Obtiene el color del turno acual.
	 *
	 * @return Color del turno actual.
	 */
	public Color obtenerTurno() {
		return this.colorTurno;
	}
}
