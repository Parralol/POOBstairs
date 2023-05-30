package domain;

import java.awt.Color;
import java.io.*;

/**
 * Clase Casilla
 *
 * @author Santiago Parra / Juan Vizcaino
 * @version v1.0
 */
public abstract class Casilla implements Serializable{
    private int[] pos;
    private Color color;
    private Ficha ficha;

    /**
     * Constructor para la clase casilla
     *
     * @param pos the pos
     */
    public Casilla(int[] pos) {
        color = Color.WHITE;
        this.pos = pos;
    }

    /**
     * retorna el color de la casilla
     *
     * @return color color
     */
    public Color getColor() {
        return color;
    }

    /**
     * retorna la posicion de la casilla
     *
     * @return int [ ]
     */
    public int[] getPos() {
        action();
        return pos;
    }

    /**
     * retorna la ficha almacenada en la casilla
     *
     * @return ficha ficha
     */
    public Ficha getFicha() {
        return ficha;
    }

    /**
     * coloca la ficha
     *
     * @param ficha the ficha
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

    /**
     * define una accion y retorna un int[] en caso de ser necesario
     *
     * @return int [ ]
     */
    public abstract int[] action();

    /**
     * retorna el id de la casilla, si es normal o otro tipo retornara -1
     *
     * @return id id
     */
    public abstract int getId();

}
