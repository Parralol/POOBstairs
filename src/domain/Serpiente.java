package domain;


/**
 * Clase Serpiente
 *
 * @author Santiago Parra / Juan Vizcaino
 * @version v1.0
 */
public class Serpiente extends Casilla {
    /**
     * The Id.
     */
    public int id;
    private String[] posible = { "Normal", "Debil", "Super", "Dual" };

    /**
     * constructor para la casilla tipo serpiente
     *
     * @param pos the pos
     */
    public Serpiente(int[] pos) {
        super(pos);
    }

    public int[] action() {
        return null;
    }

    /**
     * define el id de la casilla
     *
     * @param id the id
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
