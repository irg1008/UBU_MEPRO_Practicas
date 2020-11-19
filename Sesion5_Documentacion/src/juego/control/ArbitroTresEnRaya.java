package juego.control;
import juego.modelo.Tablero;
import juego.modelo.Jugador;

/**
 * 
 *
 *  @author: Iván Ruiz Gázquez
 * 
 */

public class ArbitroTresEnRaya {
	private static final int NUM_GANADOR = 3;
	private int numeroJugadores;
	private boolean juegoAcabado;
	private Tablero tablero;
	
	public ArbitroTresEnRaya() {
		tablero = new Tablero(3, 3);		
	}
	
	public void registrarJugador(String nombre) {
		
	}
	
	public Jugador obtenerTurno() {
		return null;
	}
	
	public Jugador obtenerGanador( ) {
		return null;
	}
	
	public void jugar(int x, int y) {
		
	}
	
	public boolean esMovimientoLegal(int x, int y) {
		return false;
	}
	
	public Tablero obtenerTablero() {
		return tablero;
	}
	
	public boolean estaAcabado() {
		return false;
	}
}
