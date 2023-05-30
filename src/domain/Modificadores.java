package domain;

import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Clase Modificadores
 *
 * @author Santiago Parra / Juan Vizcaino
 * @version v2.3
 */
public class Modificadores implements Serializable{

    private String[] tipo = { "Cambio", "Bonifica", "Penaliza" };

    /**
     * retorna un modificador de forma aleatoria
     *
     * @return modificadores modificadores
     */
    public String getModificadores() {
        int randomNum = ThreadLocalRandom.current().nextInt(0, 2 + 1);
        return tipo[randomNum];
    }
}
