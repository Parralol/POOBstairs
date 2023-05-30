package domain;

import java.util.Random;


/**
 * Clase Multiplicador
 */
public class Multiplicador {
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
