package domain;

import java.lang.Math;
import java.util.Random;


public class Multiplicador {
    private Random rand;

    public int returnMult(){
        return rand.nextInt(1-5) + 1;
    }
}
