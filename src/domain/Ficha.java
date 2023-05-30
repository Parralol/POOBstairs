package domain;

import java.awt.Color;
import java.io.Serializable;

/**
 * Clase Ficha
 *
 * @author Santiago Parra / Juan Vizcaino
 * @version v1.0
 */
public class Ficha implements Serializable{
    /**
     * The Color.
     */
    public Color color;
    /**
     * The Pos.
     */
    public int[] pos;

    /**
     * constructor para ficha
     *
     * @param color the color
     * @param pos   the pos
     */
    public Ficha(Color color, int[] pos) {
        this.color = color;
        this.pos = pos;
    }

    /**
     * Cambia la posicion de la ficha
     *
     * @param pos the pos
     */
    public void changePos(int[] pos) {
        this.pos = pos;
    }

    /**
     * obtiene la posicion de la pos
     *
     * @return int [ ]
     */
    public int[] getPos() {
        return pos;
    }

    /**
     * retorna el color de la ficha
     *
     * @return color color
     */
    public Color getColor() {
        return color;
    }
}
