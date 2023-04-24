package domain;

import java.util.ArrayList;

/**
 * clase POOBstairs.
 *
 * @author Santiago Parra / Juan Vizcaino
 * @version v1.0
 */
public class POOBstairs {
    private Dice dado;
    private ArrayList<Jugador> jugadores;
    private Tablero tablero;
    private int turno;
    private int njugadas;

    /**
     * constructor para la clase POOBstairs
     */
    public POOBstairs() {
        jugadores = new ArrayList<Jugador>();
        dado = new Dice(0);
        tablero = new Tablero();
        turno = 0;
        njugadas = 0;
    }

    /**
     * agrega un jugador a tablero
     * 
     * @param a
     */
    public void addJugador(Jugador a) {
        jugadores.add(a);
    }

    /**
     * metodo para moverse dado el numero del dado
     * 
     * @param numero
     */
    public void jugar(int numero) {
        int[] pos = jugadores.get(turno).getPosFicha();
        if (pos[1] + numero > 10) {
            int xd = pos[1] + numero;
            int guarda = xd % 10;
            pos[0] = pos[0] + 1;
            pos[1] = guarda;
        }
        jugadores.get(turno).movFicha(pos);
        tablero.jugar(jugadores.get(turno).getFicha(), pos);

    }
    // METODOS PARA RETORNAR DATOS

    /**
     * Obtiene el nombre del jugador en turno
     * 
     * @return
     */
    public String getNombreJugadorEnTurno() {
        return jugadores.get(turno).getName();
    }

    /**
     * retorna el turno actual
     * 
     * @return
     */
    public int getTurnoActual() {
        return turno;
    }

    /**
     * retorna el numero de jugadas totales en la partida
     * 
     * @return
     */
    public int getNumeroJugadas() {
        return njugadas;
    }

    /**
     * lanza el dado
     * 
     * @return
     */
    public int rollDice() {
        dado.rollDiceRandom();
        return dado.getNumOfTheDice();
    }

}
