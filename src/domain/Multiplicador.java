package domain;

import java.io.Serializable;
import java.util.Random;


/**
 * Clase Multiplicador
 */
public class Multiplicador implements Serializable{
    private Random rand;

    /**
     * Return mult int.
     *
     * @return the int
     */
    public int returnMult(){
        return rand.nextInt(1-5) + 1;
    }
}
