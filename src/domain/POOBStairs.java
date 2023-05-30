package domain;

import java.util.ArrayList;

import persistence.POOBstairsIO;

import java.io.*;
/**
 * clase POOBStairs.
 *
 * @author Santiago Parra / Juan Vizcaino
 * @version v3.7
 */
public class POOBStairs  implements Serializable{
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
        turno = 1;
        njugadas = 0;
    }

    public void createTablero(int pesc, int pserp, int pesp, int size){
        tablero = new Tablero((double) pesc /100 ,(double) pserp/100,(double) pesp /100, size);
    }
    /**
     * agrega un jugador a tablero
     *
     * @param a the a
     * @throws POOBStairsException the poob stairs exception
     */
    public void addJugador(Jugador a) throws POOBStairsException {
        boolean canC = true;
        boolean canN = true;
        for (Jugador b : jugadores) {
            if (a.getColor() == b.getColor() ) {
                canC = false;
            }if(a.getName() == b.getName()){
                canN = false;
            }
        }
        if (a.getName() == null || a.getName() == "") {
            throw new POOBStairsException(POOBStairsException.JUGADOR_DEBE_TENER_NOMBRE);
        }
        if (canC && canN) {
            jugadores.add(a);
        } else {
            if(!canC){
                throw new POOBStairsException(POOBStairsException.NO_PUEDE_TENER_MISMO_COLOR);
            }if(!canN){
                throw new POOBStairsException(POOBStairsException.NO_PUEDEN_TENER_EL_MISMO_NOMBRE);
            }
        }
    }

    public void Save(File filename){
        try{
            POOBstairsIO.saveO1(filename, this);
        }catch(Exception e){

        }
    }

    public void open(File filename) throws POOBStairsException{
        POOBstairsIO.abrirO1(filename);
    }

    public void setTablero(POOBStairs a){
        this.tablero = a.getTablero();
        this.jugadores = a.getJugadores();
        this.dado = a.getDado();
        this.njugadas = a.getNumeroJugadas();
        this.turno = a.getTurno();
        this.multi = a.getMulti();
    }

    public Tablero getTablero(){
        return tablero;
    }

    public Dice getDado() {
        return dado;
    }

    public Multiplicador getMulti() {
        return multi;
    }

    public int getTurno() {
        return turno;
    }

    public int getNjugadas() {
        return njugadas;
    }
    /**
     * metodo para moverse dado el numero del dado
     *
     * @param numero the numero
     * @throws POOBStairsException the poob stairs exception
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

    /**
     * Should multiply int.
     *
     * @return the int
     */
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
     * @return nombre nombre jugador en turno
     */
    public String getNombreJugadorEnTurno() {
        return jugadores.get(turno - 1).getName();
    }

    /**
     * returna TRUE si es humano, de lo contrario false
     *
     * @return boolean boolean
     */
    public boolean JugadorEnTurnoEsHumano() {
        return jugadores.get(turno - 1).isItHuman();
    }

    /**
     * retorna el turno actual
     *
     * @return turno turno actual
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
     * @return int int
     */
    public int rollDice() {
        dado.rollDiceRandom();
        return dado.getNumOfTheDice();
    }

    /**
     * obtiene al jugador en el turno pasado
     *
     * @return Jugador jugador en turno pasado
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
     * @return int all turns
     */
    public int getAllTurns() {
        return turno = jugadores.size();
    }

    /**
     * Retorna la ficha del juego
     *
     * @param x the x
     * @param y the y
     * @return Ficha fichas
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
     * @param x the x
     * @param y the y
     * @return Casilla casillas
     */
    public Casilla getCasillas(int x, int y) {
        return tablero.getCasilla(x, y);
    }

    /**
     * retorna una casilla en especifico
     *
     * @param x
     * @param y
     * @return Casilla all casillas
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
     * @return jugadores jugadores
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
