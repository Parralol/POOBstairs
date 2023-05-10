package domain;

import java.awt.Color;

/**
 * Clase Casilla
 *
 * @author Santiago Parra / Juan Vizcaino
 * @version v1.0
 */
public abstract class Casilla {
    private int[] pos;
    private Color color;
    private Ficha ficha;

    /**
     * Constructor para la clase casilla
     * 
     * @param pos
     */

    public Casilla(int[] pos) {
        color = Color.WHITE;
        this.pos = pos;
    }

    /**
     * retorna el color de la casilla
     * 
     * @return
     */
    public Color getColor() {
        return color;
    }

    /**
     * retorna la posicion de la casilla
     * 
     * @return
     */
    public int[] getPos() {
        return pos;
    }

    /**
     * retorna la ficha almacenada en la casilla
     * 
     * @return
     */
    public Ficha getFicha() {
        return ficha;
    }

    /**
     * coloca la ficha
     * 
     * @param ficha
     */
    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }

    /**
     * remueve la ficha de la casilla
     */
    public void removeFicha() {
        ficha = null;
    }

    public abstract int[] action();

}
