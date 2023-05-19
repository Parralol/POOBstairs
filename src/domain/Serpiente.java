package domain;

/**
 * Clase Serpiente
 *
 * @author Santiago Parra / Juan Vizcaino
 * @version v1.0
 */
public class Serpiente extends Casilla {
    public int id;
    public int[] ref;

    /**
     * constructor para la casilla tipo serpiente
     * 
     * @param pos
     */
    public Serpiente(int[] pos) {
        super(pos);
    }

    public int[] action() {
        return ref;
    }

    /**
     * define el id de la casilla
     * 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * retorna el id de la casilla
     */
    public int getId() {
        return id;
    }
}
