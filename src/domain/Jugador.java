package domain;

import java.awt.Color;

/**
 * Clase Jugador
 *
 * @author Santiago Parra / Juan Vizcaino
 * @version v1.0
 */
public abstract class Jugador {

    private boolean multi;
    /**
     * The Nombre.
     */
    protected String nombre;
    private int turno;
    /**
     * The Color.
     */
    protected Color color;
    private boolean humano;
    /**
     * The Ficha.
     */
    protected Ficha ficha;
    private int numEscaleras;
    private int numSerpientes;
    private int numCasEsp;
    private int casMax;

    /**
     * constructor para la clase abstracta Jugador
     *
     * @param humano the humano
     * @param turno  the turno
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
        multi = false;
    }

    // METODOS PARA ALTERAR DATOS

    /**
     * Mueve la ficha del jugador
     *
     * @param pos the pos
     */
    public void movFicha(int[] pos) {
        ficha.changePos(pos);
    }

    // METODOS PARA ASIGNAR DATOS

    /**
     * define el nombre del jugador
     *
     * @param nombre the nombre
     */
    public void setName(String nombre) {
        this.nombre = nombre;
    }

    /**
     * define el color del jugador
     *
     * @param color the color
     */
    public void setColor(Color color) {
        this.color = color;
        ficha = new Ficha(color, ficha.getPos());
    }

    /**
     * incrementa la casilla maxima a la que ha llegado
     *
     * @param x the x
     * @param y the y
     */
    public void increaseMaxCas(int x, int y) {
        if (x > ficha.getPos()[0] && y > ficha.getPos()[1]) {
            int a = Integer.parseInt(x + "" + y);
            casMax = a;
        }
    }

    /**
     * incrementa el numero de casillas serpiente en las que ha caido
     */
    public void increaseSerp() {
        numSerpientes++;
        System.out.println(numSerpientes);
    }

    /**
     * incrementa el numero de casillas Escalera en las que ha caido
     */
    public void increaseEsc() {
        numEscaleras++;
    }

    /**
     * incrementa el numero de casillas casillas especiales en las que ha caido
     */
    public void increaseCasEsp() {
        numCasEsp++;
    }
    // METODOS PARA OBTENER DATOS

    /**
     * retorna el turno del jugador
     *
     * @return turno turno
     */
    public int getTurno() {
        return turno;
    }

    /**
     * retorna el color del jugador como un valor de Java awt
     *
     * @return color color
     */
    public Color getColor() {
        return color;
    }

    /**
     * retorna un valor booleano, si es true es humano, si es false es maquina.
     *
     * @return boolean boolean
     */
    public boolean isItHuman() {
        return humano;
    }

    /**
     * metodo para retornar el nombre del jugador
     *
     * @return name name
     */
    public String getName() {
        return nombre;
    }

    // DATOS DE FICHAS

    /**
     * retorna el color de la ficha
     *
     * @return color ficha
     */
    public Color getColorFicha() {
        return ficha.getColor();
    }

    /**
     * retorna la posicion de la ficha
     *
     * @return int [ ]
     */
    public int[] getPosFicha() {
        return ficha.getPos();
    }

    /**
     * Get simple pos int.
     *
     * @return the int
     */
    public int getSimplePos(){
        int[] pos = getPosFicha();
        int b= 0; 
        if (pos[1] != 10) {
            b = Integer.parseInt(pos[0] + "" + pos[1]);
        } else {
            b = Integer.parseInt(pos[0] + "" + 0);
        }
        return b;
    }

    /**
     * retorna la ficha del jugador
     *
     * @return ficha ficha
     */
    public Ficha getFicha() {
        return ficha;
    }

    /**
     * retorna el numero de casillas tipo escaleras en las que el jugador ha caido
     *
     * @return num esc
     */
    public int getNumEsc() {
        return numEscaleras;
    }

    /**
     * retorna el numero de casillas tipo Serpiente en las que el jugador ha caido
     *
     * @return num ser
     */
    public int getNumSer() {
        return numSerpientes;
    }

    /**
     * retorna el numero de casillas tipo especiales en las que el jugador ha caido
     *
     * @return num cas esp
     */
    public int getNumCasEsp() {
        return numCasEsp;
    }

    /**
     * retorna el numero de casillas en las que el jugador ha caido
     *
     * @return max cas
     */
    public int getMaxCas() {
        return casMax;
    }

    /**
     * Uso multi.
     */
    public void usoMulti(){
        multi = true;
    }

    /**
     * Multi usado boolean.
     *
     * @return the boolean
     */
    public boolean multiUsado(){
        return multi;
    }
}
