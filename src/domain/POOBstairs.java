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
        turno = 1;
        njugadas = 0;
    }

    /**
     * agrega un jugador a tablero
     * 
     * @param a
     */
    public void addJugador(Jugador a) throws POOBstairsException {
        boolean can = true;
        for (Jugador b : jugadores) {
            if (a.getColor() == b.getColor()) {
                can = false;
            }
        }
        if (a.getName() == null) {
            throw new POOBstairsException(POOBstairsException.JUGADOR_DEBE_TENER_NOMBRE);
        }
        if (can) {
            jugadores.add(a);
        } else {
            throw new POOBstairsException(POOBstairsException.NO_PUEDE_TENER_MISMO_COLOR);

        }
    }

    /**
     * metodo para moverse dado el numero del dado
     * 
     * @param numero
     */
    public void jugar(int numero) {
        int[] pos = jugadores.get(turno - 1).getPosFicha();
        if (pos[1] + numero > 10) {
            int xd = pos[1] + numero;
            int guarda = xd % 10;
            pos[0] = pos[0] + 1;
            pos[1] = guarda;
        }
        jugadores.get(turno - 1).movFicha(pos);
        tablero.jugar(jugadores.get(turno - 1).getFicha(), pos);
        increaseTurno();
    }
    // METODOS PARA RETORNAR DATOS

    /**
     * Obtiene el nombre del jugador en turno
     * 
     * @return
     */
    public String getNombreJugadorEnTurno() {
        return jugadores.get(turno - 1).getName();
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

    public Jugador getJugadorEnTurnoPasado() {
        return jugadores.get(turno - 2);
    }
    // METODOS PRIVADOS

    private void increaseTurno() {
        if (turno > jugadores.size())
            turno = 1;
        else
            turno++;
    }
}
