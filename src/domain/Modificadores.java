package domain;

import java.util.concurrent.ThreadLocalRandom;

public class Modificadores {

    private String[] tipo = { "Cambio", "Bonifica", "Penaliza" };

    public String getModificadores() {
        int randomNum = ThreadLocalRandom.current().nextInt(0, 2 + 1);
        return tipo[randomNum];
    }
}
