package domain;

import java.io.Serializable;

/**
 * Clase Dice
 *
 * @author Santiago Parra / Juan Vizcaino
 * @version v1.0
 */
public class Dice implements Serializable{
    private int numDice;

    /**
     * Constructor de la clase Dice
     *
     * @param num the num
     */
    public Dice(int num) {
        this.numDice = num;
    }

    /**
     * Hace que el dado tome un valor aletorio
     */
    public void rollDiceRandom() {
        int valorDado = (int) Math.floor(Math.random() * 6 + 1);
        if (valorDado == 7) {
            this.numDice = 1;
        } else {
            this.numDice = valorDado;
        }
    }

    /**
     * Delvuelve el valor del dado
     *
     * @return valor dado (int)
     */
    public int getNumOfTheDice() {
        return numDice;
    }
}
