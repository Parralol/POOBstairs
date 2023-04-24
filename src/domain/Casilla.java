package domain;

import java.awt.Color;

/**
 * Clase Casilla
 *
 * @author Santiago Parra / Juan Vizcaino
 * @version v1.0
 */
public abstract class Casilla {
    public int[] pos;
    public Color color;

    /**
     * Constructor para la clase casilla
     * 
     * @param pos
     */

    public Casilla(int[] pos) {
        color = Color.WHITE;
        this.pos = pos;
    }
}
