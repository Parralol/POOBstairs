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

    /**
     * define la accion de una casilla normal
     */
    public int[] action() {
        int[] res = { 1, -1 };
        return res;
    }

    /**
     * retorna el id de la casilla
     */
    public int getId() {
        return -1;
    }
}
