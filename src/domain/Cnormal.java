package domain;

/**
 * Clase Cnormal
 *
 * @author Santiago Parra / Juan Vizcaino
 * @version v1.0
 */
public class Cnormal extends Casilla {

    /**
     * Constructor para la casilla tipo normal
     * 
     * @param pos
     */
    public Cnormal(int[] pos) {
        super(pos);
    }

    public int[] action() {
        return null;
    }

    public int getId() {
        return -1;
    }
}
