package domain;

/**
 * Clase Humano
 *
 * @author Santiago Parra / Juan Vizcaino
 * @version v1.0
 */
public class Humano extends Jugador {

    /**
     * constructor para la clase Humano
     * 
     * @param turno
     */
    public Humano(int turno) {
        super(true, turno);
    }
}
