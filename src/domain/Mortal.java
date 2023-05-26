package domain;

/**
 * Clase Mortal
 *
 * @author Santiago Parra / Juan Vizcaino
 * @version v1.0
 */
public class Mortal extends Cnormal {

    /**
     * constructor para la clase Mortal
     * 
     * @param pos
     */
    public Mortal(int[] pos) {
        super(pos);
    }

    @Override
    public int[] action() {
        return null;
    }
}
