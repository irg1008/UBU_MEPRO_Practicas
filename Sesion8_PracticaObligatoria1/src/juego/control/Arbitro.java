package juego.control;

import juego.modelo.Tablero;

import juego.modelo.Celda;
import juego.modelo.Pieza;
import juego.modelo.Tipo;
import juego.util.Sentido;
import juego.modelo.Color;

//import juego.util.Sentido;

/**
 * Arbitro del Ajedrez. Con el controlamos que se cumplan determinadas condiciones.
 *
 * @author Iván Ruiz Gázquez
 * @since JDK 11
 * @version 1.0
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
	private int turno;

	/**
	 * Color del jugador que le toca mover.
	 */
	private Color colorTurno;

	/**
	 * Constructor del arbitro.
	 *
	 * @param tablero Tablero.
	 */
	public Arbitro(Tablero tablero) {
		setTablero(tablero);
		setTurno(0);
		setColorTurno(null);
	}

	/**
	 * Método que establece el color del jugador que toca jugar.
	 * 
	 * @see #getColorTurno
	 * @param color Color.
	 */
	private void setColorTurno(Color color) {
		this.colorTurno = color;
	}
	
	/**
	 * Devuelve el color del jugador que le toca jugar.
	 * 
	 * @see #setColorTurno
	 * @return Color.
	 */
	private Color getColorTurno() {
		return this.colorTurno;
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
	 * Getter del tablero.
	 *
	 * @see #setTablero
	 * @return Tablero.
	 */
	private Tablero getTablero() {
		return this.tablero;
	}


	/**
	 * Setter del turno. Coloca el color del turno y actualiza el turno (número).
	 *
	 * @see #getTurno
	 * @param turno Número del turno.
	 */
	private void setTurno(int turno) {
		this.turno = turno;
	}


	/**
	 * Getter del turno en color.
	 *
	 * @see #setTurno
	 * @return Número del turno.
	 */
	private int getTurno() {
		return this.turno;
	}

	/**
	 * Observa si hay alguna pieza en medio a la hora de mover.
	 *
	 * @param origen Desde el origen.
	 * @param destino Hasta el destino.
	 * @return Devuelve si la hay o no.
	 */
	private boolean piezaEnMedio(Celda origen, Celda destino) {
		Sentido sentido = this.getTablero().obtenerSentido(origen, destino);
		if(!origen.tieneCoordenadasIguales(destino)) {
			int i = origen.obtenerFila() + sentido.obtenerDesplazamientoEnFilas();
			int j = origen.obtenerColumna() + sentido.obtenerDesplazamientoEnColumnas();

			while(this.getTablero().obtenerCelda(i, j) != destino) {
				if(!this.getTablero().obtenerCelda(i, j).estaVacia())
					return true;
				i+=sentido.obtenerDesplazamientoEnFilas();
				j+=sentido.obtenerDesplazamientoEnColumnas();
			}
		}
		return false;
	}

	/**
	 * Requisitos para el movimiento general.
	 * Lógica común a todas las piezas:
	 * > Pueden mover a celdas vacias (menos Peón) y celdas enemigas.
	 * > No pueden mover si colocan al Rey en jaque con ello.
	 * > No pueden mover si hay alguna pieza en medio (menos Caballo, él si puede).
	 *
	 * @param origen Dessde el origen.
	 * @param destino Hasta el destino.
	 * @return Devuelve si es legal.
	 */
	private boolean esLegalGeneral(Celda origen, Celda destino) {
		boolean esLegal = false;
		if((destino.estaVacia() || origen.obtenerColorDePieza() != destino.obtenerColorDePieza())
				&& (this.getTablero().obtenerSentido(origen, destino) != null
					|| origen.obtenerPieza().obtenerTipo() == Tipo.CABALLO)
						&& (origen.obtenerColorDePieza() == this.obtenerTurno()
							|| this.obtenerTurno() == null))
			esLegal = true;
				
		return esLegal;
	}

	/**
	 * Método que comprueba la legalidad de los peones blancos.
	 * 
	 * @param origen Celda origen.
	 * @param destino Celda destino.
	 * @param sentido Sentido desde el origen hasta el destino.
	 * 
	 * @return Si es legal o no el movimiento.
	 */
	private boolean esLegalPeonBlanco(Celda origen, Celda destino, Sentido sentido) {
		if((sentido == Sentido.DIAGONAL_NE || sentido == Sentido.DIAGONAL_NO)
				&& destino.obtenerFila()-origen.obtenerFila() == -1 && !destino.estaVacia())
			return true;
		else if(sentido == Sentido.VERTICAL_N && destino.estaVacia()) {
			if(!origen.obtenerPieza().esPrimerMovimiento()
				&& destino.obtenerFila()-origen.obtenerFila() == -1)
				return true;
			else if(origen.obtenerPieza().esPrimerMovimiento()
				&& (destino.obtenerFila()-origen.obtenerFila() == -2 || destino.obtenerFila()-origen.obtenerFila() == -1))
				return true;
		}
		return false;
	}
	
	/**
	 * Método que comprueba la legalidad de los peones negros.
	 * 
	 * @param origen Celda origen.
	 * @param destino Celda destino.
	 * @param sentido Sentido desde el origen hasta el destino.
	 * 
	 * @return Si es legal o no el movimiento.
	 */
	private boolean esLegalPeonNegro(Celda origen, Celda destino, Sentido sentido) {
		if((sentido == Sentido.DIAGONAL_SE || sentido == Sentido.DIAGONAL_SO)
				&& destino.obtenerFila()-origen.obtenerFila() == 1 && !destino.estaVacia())
			return true;
		else if(sentido == Sentido.VERTICAL_S && destino.estaVacia()) {
			if(destino.obtenerFila()-origen.obtenerFila() == 1)
				return true;
			else if(origen.obtenerPieza().esPrimerMovimiento()
				&& destino.obtenerFila()-origen.obtenerFila() == 2)
				return true;
		}
		return false;
	}
	
	/**
	 * Comprueba la legalidad del movimiento del peón.
	 *
	 * @param origen Desde el origen.
	 * @param destino Hasta el destino.
	 * @param sentido En un sentido específico.
	 * @return Si es legal o no.
	 */
	private boolean esLegalPeon(Celda origen, Celda destino, Sentido sentido) {
		if(!this.piezaEnMedio(origen, destino)) {
			if(origen.obtenerPieza().obtenerColor() == Color.BLANCO) {
				return this.esLegalPeonBlanco(origen, destino, sentido);
			}

			else if(origen.obtenerPieza().obtenerColor() == Color.NEGRO) {
				return this.esLegalPeonNegro(origen, destino, sentido);
			}
		}
		return false;
	}

	/**
	 * Comprueba la legalidad de los movimientos de la torre.
	 *
	 * @param origen Desde el origen.
	 * @param destino Hasta el destino.
	 * @param sentido Dado un sentido.
	 * @return Devuelve si es legal o no.
	 */
	private boolean esLegalTorre(Celda origen, Celda destino, Sentido sentido) {
		if(!this.piezaEnMedio(origen, destino)) {
			if(sentido == Sentido.HORIZONTAL_E || sentido == Sentido.HORIZONTAL_O
					|| sentido == Sentido.VERTICAL_N || sentido == Sentido.VERTICAL_S)
				return true;
		}
		return false;
	}

	/**
	 * Comprueba la legalidad del alfil.
	 *
	 * @param origen Desde el origen.
	 * @param destino Hasta el destino.
	 * @param sentido Dado un sentido.
	 * @return Devuelve si es legal o no.
	 */
	private boolean esLegalAlfil(Celda origen, Celda destino, Sentido sentido) {
		if(!this.piezaEnMedio(origen, destino)) {
			if(sentido == Sentido.DIAGONAL_NE || sentido == Sentido.DIAGONAL_NO
					|| sentido == Sentido.DIAGONAL_SE || sentido == Sentido.DIAGONAL_SO)
				return true;
		}
		return false;
	}

	/**
	 * Comprueba la legalidad de las piezas que mueven en salto. También el rey, ya que mueve en todas direcciones de forma limitada.
	 *
	 * @param movFila Direccion de movimiento de la fila.
	 * @param movCol Dirección de movimiento de la columna.
	 * @param origen Origen del movimiento.
	 * @param destino Destino del movimiento.
	 * @return Devuelve si el movimiento es posible.
	 */
	private boolean esLegalMoverEnSalto(int movFila, int movCol, Celda origen, Celda destino) {
		boolean puedeSaltar = false;

		if(Math.abs(origen.obtenerFila()-destino.obtenerFila()) == movFila
			&& Math.abs(origen.obtenerColumna()-destino.obtenerColumna()) == movCol)
			puedeSaltar = true;
		else if(Math.abs(origen.obtenerFila()-destino.obtenerFila()) == movCol
			&& Math.abs(origen.obtenerColumna()-destino.obtenerColumna()) == movFila)
			puedeSaltar = true;

		return puedeSaltar;
	}

	/**
	 * Comprueba la legalidad de los movimientos con el caballo.
	 *
	 * @param origen Desde origen.
	 * @param destino Hasta destino.
	 * @param sentido Dado un sentido.
	 * @return Devuelve si es legal o no.
	 */
	private boolean esLegalCaballo(Celda origen, Celda destino, Sentido sentido) {
		return esLegalMoverEnSalto(2, 1, origen, destino);
	}

	/**
	 * Legalidad de los movimientos de la dama.
	 *
	 * @param origen Desde origen.
	 * @param destino Hasta destino.
	 * @param sentido Dado un sentido.
	 * @return Devuelve si es legal o no.
	 */
	private boolean esLegalDama(Celda origen, Celda destino, Sentido sentido) {
		// Dama se mueve como alfil y torre juntos.
		if(this.esLegalAlfil(origen, destino, sentido) || this.esLegalTorre(origen, destino, sentido))
			return true;
		return false;
	}

	/**
	 * Rey se mueve en x y + una sola celda. Se puede cambiar a movimiento de dama en una celda.
	 *
	 * @param origen Desde origen.
	 * @param destino Hasta destino.
	 * @param sentido Dado un sentido.
	 * @return Devuelve si es legal o no.
	 */
	private boolean esLegalRey(Celda origen, Celda destino, Sentido sentido) {
		if(this.esLegalMoverEnSalto(1, 1, origen, destino) || this.esLegalMoverEnSalto(0, 1, origen, destino))
			return true;
		return false;
	}

	/**
	 * Detecta la posición del rey de un color en el tablero.
	 *
	 * @param color Color del rey.
	 * @return Posición del rey.
	 */
	private Celda detectaLaPosicionDelReySegunColor(Color color) {
		Celda celdaRey = null;

		for(int i=0; i<this.getTablero().obtenerNumeroFilas(); i++)
			for(int j=0; j<this.getTablero().obtenerNumeroColumnas(); j++)
				if(!this.getTablero().obtenerCelda(i, j).estaVacia())
					if(this.getTablero().obtenerCelda(i, j).obtenerPieza().obtenerTipo() == Tipo.REY
						&& this.getTablero().obtenerCelda(i, j).obtenerPieza().obtenerColor() == color)
						celdaRey = this.getTablero().obtenerCelda(i, j);

		return celdaRey;
	}

	/*********/

	/**
	 * Cambia el turno.
	 */
	public void cambiarTurno() {
		if(this.obtenerTurno() == null)
			this.setColorTurno(Color.BLANCO);
		else
		if(this.obtenerTurno() == Color.BLANCO)
			this.setColorTurno(Color.NEGRO);
		else
			this.setColorTurno(Color.BLANCO);
	}

	/**
	 * Coloca el tablero en su estado inicial.
	 */
	public void colocarPiezas() {
		Pieza[][] piezasBlancas = new Pieza[2][this.getTablero().obtenerNumeroColumnas()];
		Pieza[][] piezasNegras = new Pieza[2][this.getTablero().obtenerNumeroColumnas()];
		
		Tipo[] ordenTipos = {Tipo.TORRE, Tipo.CABALLO, Tipo.ALFIL, Tipo.DAMA, Tipo.REY, Tipo.ALFIL, Tipo.CABALLO, Tipo.TORRE};

		// Guarda Piezas En Array
		for(int i=0; i<this.getTablero().obtenerNumeroColumnas(); i++) {
			piezasBlancas[0][i] = new Pieza(Tipo.PEON, Color.BLANCO);
			piezasNegras[1][i] = new Pieza(Tipo.PEON, Color.NEGRO);
			for(int k=0; k<this.getTablero().obtenerNumeroColumnas(); k++) {
				piezasBlancas[1][k] = new Pieza(ordenTipos[k], Color.BLANCO);
				piezasNegras[0][k] = new Pieza(ordenTipos[k], Color.NEGRO);
			}
		}
		
		// Coloca Piezas En Tablero
		for(int i=0; i<piezasBlancas.length; i++)
			for(int j=0; j<piezasBlancas[0].length; j++)
				this.getTablero().colocar(piezasBlancas[i][j], this.getTablero().obtenerCelda(6+i, j));

		for(int i=0; i<piezasNegras.length; i++)
			for(int j=0; j<piezasNegras[0].length; j++)
				this.getTablero().colocar(piezasNegras[i][j], this.getTablero().obtenerCelda(i, j));

		this.setColorTurno(Color.BLANCO);
	}

	/**
	 * Coloca piezas de un tipo concreto con un color concreto en unas coordenadas dadas.
	 *
	 * @param tipo Tipo de pieza.
	 * @param color Color de la pieza.
	 * @param coordenadas Coordenadas en la que colocar la pieza.
	 */
	public void colocarPiezas(Tipo[] tipo, Color[] color, int[][] coordenadas) {
		if(tipo.length == color.length && tipo.length == coordenadas.length)
			for(int i=0; i<tipo.length; i++) {
				this.getTablero().obtenerCelda(coordenadas[i][0], coordenadas[i][1]).eliminarPieza();
				this.getTablero().colocar(new Pieza(tipo[i], color[i]), this.getTablero().obtenerCelda(coordenadas[i][0], coordenadas[i][1]));
			}
	}

	/**
	 * Comprueba la legalidad del movimiento entre celdas.
	 *
	 * @param origen Celda origen.
	 * @param destino Celda destino.
	 * @return Si es legal o no.
	 */
	public boolean esMovimientoLegal(Celda origen, Celda destino) {
		boolean esLegal = false;
		if(!origen.estaVacia()) {
			if(this.esLegalGeneral(origen, destino)) {
				Pieza pieza = origen.obtenerPieza();
				Sentido sentido = this.getTablero().obtenerSentido(origen, destino);
				// Peón.
				if(pieza.obtenerTipo() == Tipo.PEON)
					esLegal = this.esLegalPeon(origen, destino, sentido);
				// Torre.
				else if(pieza.obtenerTipo() == Tipo.TORRE)
					esLegal = this.esLegalTorre(origen, destino, sentido);
				// Alfil.
				else if(pieza.obtenerTipo() == Tipo.ALFIL)
					esLegal = this.esLegalAlfil(origen, destino, sentido);
				// Caballo.
				else if(pieza.obtenerTipo() == Tipo.CABALLO)
					esLegal = this.esLegalCaballo(origen, destino, sentido);
				// Dama.
				else if(pieza.obtenerTipo() == Tipo.DAMA)
					esLegal = this.esLegalDama(origen, destino, sentido);
				// Rey.
				else if(pieza.obtenerTipo() == Tipo.REY)
					esLegal = this.esLegalRey(origen, destino, sentido);
			}
		}
		return esLegal;
	}

	/**
	 * Comprueba si el rey está en jaque.
	 *
	 * @param color Color del rey a comprobar.
	 * @return Si el rey está en jaque.
	 */
	public boolean estaEnJaque(Color color) {
		// Celda del rey.
		Celda celdaRey = null;
		boolean enJaque = false;
		Color turno = this.obtenerTurno();

		// Detecta la posición del rey.
		celdaRey = this.detectaLaPosicionDelReySegunColor(color);

		// Comprueba si algún enemigo hace jaque.
		for(int i=0; i<this.getTablero().obtenerNumeroFilas(); i++)
			for(int j=0; j<this.getTablero().obtenerNumeroColumnas(); j++)
				if(!this.getTablero().obtenerCelda(i, j).estaVacia()) {
					this.setColorTurno(this.getTablero().obtenerCelda(i, j).obtenerColorDePieza());
					if(this.esMovimientoLegal(this.getTablero().obtenerCelda(i, j), celdaRey))
						enJaque = true;
				}
					
		this.setColorTurno(turno);
		return enJaque;
	}

	/**
	 * Comprueba si el movimiento de la pieza hace que algún rey pase a estar en jaque.
	 *
	 * @param origen Origen del movimineto.
	 * @param destino Destino del movimiento.
	 * @return Devuelve si el pasaría a estar en jaque.
	 */
	public boolean estaEnJaqueTrasSimularMovimientoConTurnoActual(Celda origen, Celda destino) {
		boolean estaEnJaque = false;
		Pieza piezaOrigen = new Pieza(origen.obtenerPieza().obtenerTipo(), origen.obtenerColorDePieza());
		Pieza piezaDestino = null;

		if(!destino.estaVacia())
			piezaDestino = new Pieza(destino.obtenerPieza().obtenerTipo(), destino.obtenerColorDePieza());

		// Colocamos la pieza origen en el destino
		this.getTablero().colocar(origen.obtenerPieza(), destino);
		origen.eliminarPieza();
		
		// Comprobamos.
		if(this.estaEnJaque(destino.obtenerColorDePieza()))
			estaEnJaque = true;

		// Reestablecemos las piezas movidas.
		this.getTablero().colocar(piezaOrigen, origen);
		destino.eliminarPieza();
		if(!destino.estaVacia())
			this.getTablero().colocar(piezaDestino, destino);

		return estaEnJaque;
	}

	/**
	 * Mueve una pieza.
	 *
	 * @param origen De una celda origen.
	 * @param destino A una celda destino.
	 */
	public void mover(Celda origen, Celda destino) {
		// Eliminamos pieza destino si la hubiera.
		if(!destino.estaVacia())
			destino.eliminarPieza();

		Pieza piezaOrigen = origen.obtenerPieza();

		// Colocamos la pieza origen en el destino.
		this.getTablero().colocar(piezaOrigen, destino);
		
		this.setTurno(getTurno()+1);

		// Marcamos que es el primer movimiento si así fuera.
		if(piezaOrigen.esPrimerMovimiento())
			piezaOrigen.marcarPrimerMovimiento();

		// Borramos la pieza del origen
		origen.eliminarPieza();
	}

	/**
	 * Obtiene la jugada realizada en notación algebraica.
	 *
	 * @param origen Origen movimieno.
	 * @param destino Destino movimiento.
	 * @return La notación de la jugada.
	 */
	public String obtenerJugadaEnNotacionAlgebraica(Celda origen, Celda destino) {
		String coordenadas = this.getTablero().obtenerCoordenadaEnNotacionAlgebraica(origen);
		coordenadas += ""+this.getTablero().obtenerCoordenadaEnNotacionAlgebraica(destino);

		return coordenadas;
	}

	/**
	 * Obtiene el número de jugada.
	 *
	 * @return Número de jugada.
	 */
	public int obtenerNumeroJugada() {
		// Turno actual = numero jugadas.
		return this.getTurno();
	}

	/**
	 * Obtiene el color del turno acual.
	 *
	 * @return Color del turno actual.
	 */
	public Color obtenerTurno() {
		return this.getColorTurno();
	}
}
