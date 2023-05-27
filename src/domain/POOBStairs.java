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
    private Multiplicador multi;
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
        //System.out.println(turno - 1);
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
            checkCasi(pos);
            pos = isMovModified(pos);
            // System.out.println(jugadores.get(turno - 1).getName());
            pos = tablero.jugar(jugadores.get(turno - 1).getFicha(), pos);
            jugadores.get(turno - 1).movFicha(pos);
            jugadores.get(turno - 1).increaseMaxCas(pos[0], pos[1]);
            
        } catch (POOBStairsException e) {
            throw new POOBStairsException(getNombreJugadorEnTurno() + " " + POOBStairsException.GANADOR);

        }
        increaseTurno();
    }

    public int shouldMultiply(){
        int res = 1;
        if(getJugadorEnTurnoPasado().getSimplePos() - 99 <=10  && jugadores.get(turno - 1).multiUsado()){
                jugadores.get(turno - 1).usoMulti();
                res =  multi.returnMult();
                
        }
        return res;
    }
    // METODOS PARA RETORNAR DATOS

    /**
     * Obtiene el nombre del jugador en turno
     * 
     * @return nombre
     */
    public String getNombreJugadorEnTurno() {
        return jugadores.get(turno - 1).getName();
    }

    /**
     * returna TRUE si es humano, de lo contrario false
     * 
     * @return boolean
     */
    public boolean JugadorEnTurnoEsHumano() {
        return jugadores.get(turno - 1).isItHuman();
    }

    /**
     * retorna el turno actual
     * 
     * @return turno
     */
    public int getTurnoActual() {
        return turno;
    }

    /**
     * retorna el numero de jugadas totales en la partida
     * 
     * @return numero de jugadas
     */
    public int getNumeroJugadas() {
        return njugadas;
    }

    /**
     * lanza el dado
     * 
     * @return int
     */
    public int rollDice() {
        dado.rollDiceRandom();
        return dado.getNumOfTheDice();
    }

    /**
     * obtiene al jugador en el turno pasado
     * 
     * @return Jugador
     */
    public Jugador getJugadorEnTurnoPasado() {
        if(turno == 1){
            return jugadores.get(turno - 1 );
        }else{
            return jugadores.get(turno - 2 );
        }
    }

    /**
     * Retorna todos los turnos en total
     * 
     * @return int
     */
    public int getAllTurns() {
        return turno = jugadores.size();
    }

    /**
     * Retorna la ficha del juego
     * 
     * @param x
     * @param y
     * @return Ficha
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
     * retorna una casilla en especifico
     * 
     * @param x
     * @param y
     * @return Casilla
     */
    public Casilla getCasillas(int x, int y) {
        return tablero.getCasilla(x, y);
    }

    /**
     * retorna una casilla en especifico
     * 
     * @param x
     * @param y
     * @return Casilla
     */
    public ArrayList<Casilla> getAllCasillas() {
        return tablero.getCasillas();
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
     * @return jugadores
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

    /**
     * revisa si la casilla es especial
     * 
     * @param pos
     */
    private void checkCasi(int[] pos) {
        if(tablero.getCasilla(jugadores.get(turno - 1).getPosFicha()[0], jugadores.get(turno - 1).getPosFicha()[1])!= null){
        if (tablero.getCasilla(jugadores.get(turno - 1).getPosFicha()[0],
                jugadores.get(turno - 1).getPosFicha()[1]) instanceof Serpiente) {
            jugadores.get(turno - 1).increaseSerp();
        } else if (tablero.getCasilla(jugadores.get(turno - 1).getPosFicha()[0],
                jugadores.get(turno - 1).getPosFicha()[1]) instanceof Escalera) {
            jugadores.get(turno - 1).increaseEsc();
        }if (tablero.getCasilla(jugadores.get(turno - 1).getPosFicha()[0], jugadores.get(turno - 1).getPosFicha()[1]).action() == null) {
            jugadores.get(turno - 1).increaseCasEsp();
        }
    }
}

    /**
     * verifica si el movimiento se altera
     * 
     * @param pos
     * @return pos
     */
    private int[] isMovModified(int[] pos) {
        if (tablero.getCasilla(jugadores.get(turno - 1).getPosFicha()[0],
                jugadores.get(turno - 1).getPosFicha()[1]) instanceof Mortal) {
            int[] xd = { 0, 0 };
            pos = xd;
        }

        if (tablero.getCasilla(jugadores.get(turno - 1).getPosFicha()[0],
                jugadores.get(turno - 1).getPosFicha()[1]) instanceof Saltarina_n) {
            int[] xd = { pos[0], pos[1] + 4 };
            pos = xd;
        }
        if (tablero.getCasilla(jugadores.get(turno - 1).getPosFicha()[0],
                jugadores.get(turno - 1).getPosFicha()[1]) instanceof Saltarina_inv) {
            int[] xd = { pos[0], pos[1] - 3 };
            pos = xd;
        }

        return pos;
    }

}
