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
            jugadores.get(turno - 1).movFicha(pos);
            tablero.jugar(jugadores.get(turno - 1).getFicha(), pos);
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
        if (turno >= jugadores.size())
            turno = 1;
        else
            turno++;
    }

    public int getAllTurns() {
        return turno = jugadores.size();
    }

    public Ficha getFichas(int x, int y) {
        Ficha res = null;
        for (Jugador jugador : jugadores) {
            if (jugador.getFicha().getPos()[0] == x && jugador.getFicha().getPos()[1] == y) {
                res = jugador.getFicha();
            }
        }
        return res;
    }

    public void changeTurn() {
        if (turno == jugadores.size()) {
            turno = 1;
        } else {
            turno++;
        }
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }
}
