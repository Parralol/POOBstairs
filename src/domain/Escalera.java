package domain;


/**
 * Clase Escalera
 *
 * @author Santiago Parra / Juan Vizcaino
 * @version v1.0
 */
public class Escalera extends Casilla {
    private int id;
    private String[] posible = { "Normal", "Debil", "Super", "Dual" };
    private String actual;

    /**
     * Constructor para la casilla tipo escalera
     *
     * @param pos the pos
     */
    public Escalera(int[] pos) {
        super(pos);
    }

    /**
     * define el Id de la casilla
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * define la accion de la escalera
     */
    public int[] action() {
        if(actual == null){
            
        }
        return null;
    }

    /**
     * retorna el id de la casilla
     */
    public int getId() {
        return id;
    }
}
