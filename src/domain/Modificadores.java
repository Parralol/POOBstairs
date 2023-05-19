package domain;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Clase Modificadores
 *
 * @author Santiago Parra / Juan Vizcaino
 * @version v1.0
 */
public class Modificadores {

    private String[] tipo = { "Cambio", "Bonifica", "Penaliza" };

    /**
     * retorna un modificador de forma aleatoria
     * 
     * @return
     */
    public String getModificadores() {
        int randomNum = ThreadLocalRandom.current().nextInt(0, 2 + 1);
        return tipo[randomNum];
    }
}
