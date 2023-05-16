package domain;

import java.awt.Color;

/**
 * Clase Jugador
 *
 * @author Santiago Parra / Juan Vizcaino
 * @version v1.0
 */
public abstract class Jugador {

    protected String nombre;
    private int turno;
    protected Color color;
    private boolean humano;
    protected Ficha ficha;
    private int numEscaleras;
    private int numSerpientes;
    private int numCasEsp;
    private int casMax;

    /**
     * constructor para Jugador
     */
    public Jugador(boolean humano, int turno) {
        numEscaleras = 0;
        numSerpientes = 0;
        numCasEsp = 0;
        casMax = 0;
        int[] pos = { 0, 0 };
        ficha = new Ficha(color, pos);
        this.humano = humano;
        this.turno = turno;
    }

    // METODOS PARA ALTERAR DATOS
    public void movFicha(int[] pos) {
        ficha.changePos(pos);
    }

    // METODOS PARA ASIGNAR DATOS

    /**
     * define el nombre del jugador
     * 
     * @param nombre
     */
    public void setName(String nombre) {
        this.nombre = nombre;
    }

    /**
     * define el color del jugador
     * 
     * @param color
     */
    public void setColor(Color color) {
        this.color = color;
        ficha = new Ficha(color, ficha.getPos());
    }

    public void increaseMaxCas(int x, int y) {
        if (x > ficha.getPos()[0] && y > ficha.getPos()[1]) {
            int a = Integer.parseInt(x + "" + y);
            casMax = a;
        }
    }

    public void increaseSerp() {
        numSerpientes++;
    }

    public void increaseEsc() {
        numEscaleras++;
    }

    public void increaseCasEsp() {
        numCasEsp++;
    }
    // METODOS PARA OBTENER DATOS

    /**
     * retorna el turno del jugador
     * 
     * @return
     */
    public int getTurno() {
        return turno;
    }

    /**
     * retorna el color del jugador como un valor de Java awt
     * 
     * @return
     */
    public Color getColor() {
        return color;
    }

    /**
     * retorna un valor booleano, si es true es humano, si es false es maquina.
     * 
     * @return
     */
    public boolean isItHuman() {
        return humano;
    }

    /**
     * metodo para retornar el nombre del jugador
     * 
     * @return
     */
    public String getName() {
        return nombre;
    }

    // DATOS DE FICHAS

    /**
     * retorna el color de la ficha
     * 
     * @return
     */
    public Color getColorFicha() {
        return ficha.getColor();
    }

    /**
     * retorna la posicion de la ficha
     * 
     * @return
     */
    public int[] getPosFicha() {
        return ficha.getPos();
    }

    /**
     * retorna la ficha del jugador
     * 
     * @return
     */
    public Ficha getFicha() {
        return ficha;
    }

    public int getNumEsc() {
        return numEscaleras;
    }

    public int getNumSer() {
        return numSerpientes;
    }

    public int getNumCasEsp() {
        return numCasEsp;
    }

    public int getMaxCas() {
        return casMax;
    }
}
