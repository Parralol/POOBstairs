package domain;

import java.awt.Color;

/**
 * Clase Ficha
 *
 * @author Santiago Parra / Juan Vizcaino
 * @version v1.0
 */
public class Ficha {
    public Color color;
    public int[] pos;

    /**
     * constructor para ficha
     * 
     * @param color
     * @param pos
     */
    public Ficha(Color color, int[] pos) {
        this.color = color;
        this.pos = pos;
    }

    /**
     * Cambia la posicion de la ficha
     * 
     * @param pos
     */
    public void changePos(int[] pos) {
        this.pos = pos;
    }

    /**
     * obtiene la posicion de la pos
     * 
     * @return
     */
    public int[] getPos() {
        return pos;
    }

    /**
     * retorna el color de la ficha
     * 
     * @return
     */
    public Color getColor() {
        return color;
    }
}
