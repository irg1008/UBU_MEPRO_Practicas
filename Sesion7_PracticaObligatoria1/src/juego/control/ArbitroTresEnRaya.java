package juego.control;

import juego.modelo.Jugador;
import juego.modelo.Tablero;
import juego.modelo.Celda;
import juego.modelo.Pieza;
import juego.modelo.Color;

import juego.util.Direccion;

/**
 * Arbitro del tres en raya. Con el controlamos que se cumplan determinadas condiciones.
 * 
 * @author Iván Ruiz Gázquez
 * @since JDK 11
 * @version 1.0
 * 
 */
public class ArbitroTresEnRaya {
	
	/**
	 * Número de piezas consecutivas para ganar.
	 */
	private static final int NUM_GANADOR = 3;

	/**
	 * Número de jugadores de la partida.
	 */
	private int numeroJugadores;
	
	/**
	 * Estado del juego.
	 */
	private boolean juegoAcabado;
	
	/**
	 * Tablero de la partida.
	 */
	private Tablero tablero;
	
	/**
	 * Array de jugadores.
	 */
	private Jugador[] jugadores;
	
	/**
	 * Turno de la partida.
	 */
	private int turnoActual;
	
	/**
	 * Jugador ganador.
	 */
	private Jugador jugadorGanador;
	
	/**
	 * Ultima celda colocada.
	 */
	//private Celda ultimaCeldaColocada;
	
	/**
	 * Constructor del Arbitro.
	 */
	public ArbitroTresEnRaya() {
		setNumeroJugadores(2);
		setTamJugadores(getNumeroJugadores());
		setTablero(3,3);
		setTurno(0);
		setJuegoAcabado(false);
		resetGanador();
	}
	
	//<!---->
	/**
	 * Setter del numero de jugadores.
	 * 
	 * @see #getNumeroJugadores
	 * @param num Jugadores.
	 */
	private void setNumeroJugadores(int num) {
		this.numeroJugadores = num;
	}
	/**
	 * Getter del numero de jugadores.
	 * 
	 * @see #setNumeroJugadores
	 * @return Numero de jugadores.
	 */
	private int getNumeroJugadores() {
		return this.numeroJugadores;
	}
	
	/**
	 * Setter del tamaño del array de jugadores.
	 * 
	 * @param n Numero de jugadores.
	 */
	private void setTamJugadores(int n) {
		this.jugadores = new Jugador[n]; 
	}
	/**
	 * Setter de el array de objetos de la clase Jugador.
	 * 
	 * @see #getJugador
	 * @param num Numero de jugador.
	 * @param nombre Nombre del jugador.
	 * @param color Color del jugador.
	 */
	private void setJugador(int num, String nombre, Color color) {
		this.jugadores[num] = new Jugador(nombre, color);
	}
	/**
	 * Getter de un jugador del array.
	 * 
	 * @see #setJugador
	 * @param num Posición del array.
	 * @return Jugador en dicha posición.
	 */
	public Jugador getJugador(int num) {
		if(this.jugadores[num] == null)
			return null;
		else
			return this.jugadores[num];
	}
	
	/**
	 * Coloca el ganador a null al comienzo de la partida.
	 */
	private void resetGanador() {
		this.jugadorGanador = null;
	}
	/**
	 * Establece el jugador ganador.
	 * 
	 * @see #getJugadorGanador
	 * @param num Numero del jugador en el array de jugadores.
	 */
	private void setJugadorGanador(int num) {
		this.jugadorGanador = getJugador(num);
	}
	/**
	 * Retorna el jugador ganador.
	 * 
	 * @see #setJugadorGanador
	 * @return Jugador ganador.
	 */
	private Jugador getJugadorGanador() {
		if(this.jugadorGanador == null)
			return null;
		else
			return this.jugadorGanador;
	}
	
	/**
	 * Establece un tablero.
	 * 
	 * @param x Filas.
	 * @param y Columnas.
	 */
	private void setTablero(int x, int y) {
		this.tablero = new Tablero(x, y);
	}
	/**
	 * Obtiene el tablero.
	 * 
	 * @return Tablero.
	 */
	public Tablero obtenerTablero() {
		return this.tablero;
	}
	
	/**
	 * Cambia el turno del juego.
	 * 
	 * @see #getTurno
	 * @param turno Turno de la partida.
	 */
	private void setTurno(int turno) {
		this.turnoActual = turno;
	}
	/**
	 * Obtiene el turno actual de la partida.
	 * 
	 * @see #setTurno
	 * @return Turno de la partida.
	 */
	public int getTurno() {
		return this.turnoActual;
	}
	
	/**
	 * Coloca el estado del juego en acabado o no finalizado todavía.
	 * 
	 * @see #getJuegoAcabado
	 * @param acabado Estado del juego.
	 */
	private void setJuegoAcabado(boolean acabado) {
		this.juegoAcabado = acabado;
	}
	/**
	 * Obtiene el estado del juego.
	 * 
	 * @see #setJuegoAcabado
	 * @return Estado del juego.
	 */
	private boolean getJuegoAcabado() {
		return this.juegoAcabado;
	}
	
	/**
	 * Establece la última celda colocada.
	 * 
	 * @see #getUltimaCelda
	 * @param celda Ultima celda.
	 */
	//private void setUltimaCelda(Celda celda) {
		//this.ultimaCeldaColocada = celda;
	//}
	/**
	 * Devuelve la última celda colocada.
	 * 
	 * @see #setUltimaCelda
	 * @return Última celda.
	 */
	//private Celda getUltimaCelda() {
		//return this.ultimaCeldaColocada;
	//}
	//<!---->
	
	/**
	 * Registra jugadores dado un nombre. Coloca Blanco al primero y Negro al segundo.
	 * 
	 * @param nombre Nombre del juegador a registrar.
	 */
	public void registrarJugador(String nombre) {
		if(getJugador(0) == null)
			setJugador(0, nombre, Color.BLANCO);
		else
			setJugador(1, nombre, Color.NEGRO);
	}
	
	/**
	 * Obtiene el jugador que le toca en este turno.
	 * 
	 * @return Jugador que le toca jugar.
	 */
	public Jugador obtenerTurno() {	
		if(getJugador(1) == null)
			return null;
		else {
			int turno = getTurno();
			
			if(turno%2 == 0)
				turno = 0;
			else
				turno = 1;
					
			return getJugador(turno);
		}
	}
	
	/**
	 * Devuelve si el movimiento es legal.
	 * 
	 * @param x Fila.
	 * @param y Columna
	 * @return Legal o no legal.
	 */
	public boolean esMovimientoLegal(int x, int y) {
		boolean movLegal = true;
		
		if(!obtenerTablero().obtenerCelda(x, y).estaVacia()
				|| !obtenerTablero().estaEnTablero(x, y)
					|| getJuegoAcabado())
			movLegal = false;
		
		return movLegal;
	}
	
	/**
	 * Comprueba si la partida ha acabado.
	 * 
	 * @return Devuelve si la partida ha finalizado.
	 */
	public boolean estaAcabado() {
		if(obtenerTablero().estaCompleto()
				|| obtenerGanador() != null)
			setJuegoAcabado(true);
						
		return getJuegoAcabado();
	}
	
	/**
	 * Juega en la casilla indicada.
	 * 
	 * @param x Fila.
	 * @param y Columna.
	 */
	public void jugar(int x, int y) {
		Celda celda = obtenerTablero().obtenerCelda(x, y);
		Pieza pieza = obtenerTurno().generarPieza();
		
		obtenerTablero().colocar(pieza, celda);
		
		//setUltimaCelda(celda);
		
		setTurno(getTurno()+1);
	}
	
	/**
	 * Obtiene el ganado si le hay.
	 * 
	 * @return Ganador.
	 */
	public Jugador obtenerGanador() {
		for(int i=0; i<obtenerTablero().obtenerNumeroFilas(); i++)
			for(int j=0; j<obtenerTablero().obtenerNumeroColumnas(); j++)
				for(Direccion direccion : Direccion.values())
					if(obtenerTablero().contarPiezas(obtenerTablero().obtenerCelda(i, j), direccion)
							== NUM_GANADOR)
						for(int k=0; k<getNumeroJugadores(); k++)
							if(getJugador(k).obtenerColor()
									== obtenerTablero().obtenerCelda(i, j).obtenerPieza().obtenerColor())
								setJugadorGanador(k);
						
		return getJugadorGanador();
	}
}
