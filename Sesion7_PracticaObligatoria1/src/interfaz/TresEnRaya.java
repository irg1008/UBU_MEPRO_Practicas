package interfaz;

import java.util.Scanner;
import juego.control.ArbitroTresEnRaya;
import juego.modelo.Tablero;

// COMPLETAR IMPORTACIONES....

/**
 * Juego del <b>tres en raya</b>. Ejercicio práctico - Metodología de la Programación. <br>
 * 
 * 2º GII - Curso 2019-2020
 * 
 * @author Iván Ruiz Gázquez
 */
public class TresEnRaya {

	/**
	 * Número de argumentos máximo.
	 */
	private static final int NUM_ARGUMENTOS = 2;

	/**
	 * Arbitro.
	 */
	private static ArbitroTresEnRaya arbitro;
	
	/**
	 * Flujo de ejecución principal del juego.
	 * 
	 * @param args Nombres de los jugadores.
	 */
	public static void main(String[] args) {
		if (args.length != NUM_ARGUMENTOS) {
			mostrarAyuda();	
			args = new String[2];
			args[0] = "Abel";
			args[1] = "Cain";
			TresEnRaya.main(args);
		} else {
			int fila, columna;
			Scanner teclado = new Scanner(System.in);
			
			// COMIENZO
			arbitro = new ArbitroTresEnRaya();
	 		arbitro.registrarJugador(args[0]); 
	 		arbitro.registrarJugador(args[1]); 
			
	 		System.out.print("Comienza la partida!!\n\n\tJugador 1: "+args[0]+", Fichas: "+arbitro.getJugador(0).obtenerColor().toChar()+"\n\tJugador 2: "+args[1]+", Fichas: "+arbitro.getJugador(1).obtenerColor().toChar()+"\n");
	 		
	 		// PARTIDA
			while(!arbitro.estaAcabado()) {
				mostrarTablero(arbitro.obtenerTablero());
				System.out.print("\n"+arbitro.obtenerTurno().obtenerNombre()+" mueve con fichas "+arbitro.obtenerTurno().obtenerColor().toChar()+"\n");

				do {
					System.out.print("\n\tFila a colocar (1-3): ");
					fila = teclado.nextInt();
				} while(fila<1 || fila>arbitro.obtenerTablero().obtenerNumeroFilas());
				
				do {
					System.out.print("\n\tColumna a colocar (1-3): ");
					columna = teclado.nextInt();
				} while(columna<1 || columna>arbitro.obtenerTablero().obtenerNumeroColumnas());
				
				if(arbitro.esMovimientoLegal(fila-1, columna-1))
						arbitro.jugar(fila-1, columna-1);
				
				System.out.print("\n\n\n\n");
			}

			// FIN
			mostrarTablero(arbitro.obtenerTablero());
			if(arbitro.obtenerGanador() == null)
				System.out.print("\nPartida finalizada en TABLAS\n");
			else
				System.out.print("\nEl GANADOR de la partida es "+arbitro.obtenerGanador().obtenerNombre()+", Fichas: "+arbitro.obtenerGanador().obtenerColor().toChar()+"!!\n");
			
			System.out.print("\n\n Gracias por jugar al tres en raya. Vuelve cuando quieras.");
			
			teclado.close();
		}
	}

	/**
	 * Muestra la ayuda en línea para la inicialización correcta del juego.
	 */
	private static void mostrarAyuda() {
		// Colocar aquí lo que haga falta y checkear si todo lo demás está bien, así como ejecutar el checkear y documentar.
	}

	/**
	 * Muestra el estado actual del tablero.
	 * 
	 * @param tablero
	 *            tablero a pintar en pantalla.
	 */
	private static void mostrarTablero(Tablero tablero) {
		// PARTE TABLERO PARA INDICAR FILAS Y COLUMNAS
		String tableroString = arbitro.obtenerTablero().toString();
        String[] lines = tableroString.split("\n");
		System.out.print("\n\nTABLERO (turno = "+(arbitro.getTurno()+1)+")\n\n  123\n1 "+lines[0]+"\n2 "+lines[1]+"\n3 "+lines[2]+"\n");
	}
}