package domain;

/**
 * Clase Escalera
 *
 * @author Santiago Parra / Juan Vizcaino
 * @version v1.0
 */
public class Escalera extends Casilla {
    public int id;
    public int[] ref;

    /**
     * Constructor para la casilla tipo escalera
     * 
     * @param pos
     */
    public Escalera(int[] pos) {
        super(pos);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int[] action() {
        return null;
    }

    public int getId() {
        return id;
    }
}
