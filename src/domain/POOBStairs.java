package domain;

import java.util.ArrayList;

/**
 * clase POOBStairs.
 *
 * @author Santiago Parra / Juan Vizcaino
 * @version v1.0
 */
public class POOBStairs {
    private Dice dado;
    private ArrayList<Jugador> jugadores;
    private Tablero tablero;
    private int turno;
    private int njugadas;

    /**
     * constructor para la clase POOBStairs
     */
    public POOBStairs() {
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
    public void addJugador(Jugador a) throws POOBStairsException {
        boolean can = true;
        for (Jugador b : jugadores) {
            if (a.getColor() == b.getColor()) {
                can = false;
            }
        }
        if (a.getName() == null) {
            throw new POOBStairsException(POOBStairsException.JUGADOR_DEBE_TENER_NOMBRE);
        }
        if (can) {
            jugadores.add(a);
        } else {
            throw new POOBStairsException(POOBStairsException.NO_PUEDE_TENER_MISMO_COLOR);

        }
    }

    /**
     * metodo para moverse dado el numero del dado
     * 
     * @param numero
     */
    public void jugar(int numero) throws POOBStairsException {
        // System.out.println(numero);
        // System.out.println(turno - 1);
        int[] pos = jugadores.get(turno - 1).getPosFicha();
        if (pos[1] + numero > 10) {
            int xd = pos[1] + numero;
            int guarda = xd % 10;
            pos[0] = pos[0] + 1;
            pos[1] = guarda;
        } else {
            pos[1] += numero;
        }
        try {
            pos = tablero.jugar(jugadores.get(turno - 1).getFicha(), pos);
            jugadores.get(turno - 1).movFicha(pos);
        } catch (Exception e) {
            throw new POOBStairsException(getNombreJugadorEnTurno() + " " + POOBStairsException.GANADOR);
        }
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
     * returna TRUE si es humano, de lo contrario false
     * 
     * @return
     */
    public boolean JugadorEnTurnoEsHumano() {
        return jugadores.get(turno - 1).isItHuman();
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

    /**
     * obtiene al jugador en el turno pasado
     * 
     * @return
     */
    public Jugador getJugadorEnTurnoPasado() {
        return jugadores.get(turno - 2);
    }

    /**
     * Retorna todos los turnos en total
     * 
     * @return
     */
    public int getAllTurns() {
        return turno = jugadores.size();
    }

    /**
     * Retorna todas las fichas del juego
     * 
     * @param x
     * @param y
     * @return
     */
    public Ficha getFichas(int x, int y) {
        Ficha res = null;
        for (Jugador jugador : jugadores) {
            if (jugador.getFicha().getPos()[0] == x && jugador.getFicha().getPos()[1] == y) {
                res = jugador.getFicha();
            }
        }
        return res;
    }

    /**
     * cambia el turno del juego
     */
    public void changeTurn() {
        if (turno == jugadores.size()) {
            turno = 1;
        } else {
            turno++;
        }
    }

    /**
     * retorna un ArrayList<Jugador> de todos los jugadores
     * 
     * @return
     */
    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    // METODOS PRIVADOS

    /**
     * incrementa el turno
     */
    private void increaseTurno() {
        if (turno >= jugadores.size())
            turno = 1;
        else
            turno++;
    }

}
