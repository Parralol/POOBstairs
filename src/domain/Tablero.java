package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Clase Tablero
 *
 * @author Santiago Parra / Juan Vizcaino
 * @version v1.0
 */
public class Tablero {
    private ArrayList<Casilla> casillas;

    /**
     * Constructor para tablero
     */
    public Tablero() {
        casillas = new ArrayList<Casilla>();
        generateCasillasNormal();

    }

    // METODOS PARA CREAR O ASIGNAR DATOS

    /**
     * Realiza una jugada dado el turno y la posicion donde debe moverse
     * 
     * @param ficha
     * @param pos
     * @throws POOBStairsException
     */
    public int[] jugar(Ficha ficha, int[] pos) throws POOBStairsException {
        int b = 0;
        for (Casilla a : casillas) {
            if (a.getColor() == ficha.getColor()) {
                a.removeFicha();
            }
        }

        if (pos[1] != 10) {
            b = Integer.parseInt(pos[0] + "" + pos[1]);
        } else {
            b = Integer.parseInt(pos[0] + "" + 0);
        }

        // System.out.println(b + "---" + ficha.getColor() + pos[0] + "/" + pos[1]);
        // System.out.println(casillas.size() + "longitud casillas");
        if (b == 99) {
            throw new POOBStairsException(POOBStairsException.GANADOR);
        }
        if (b > 100) {
            int res = b - 100;
            b = 100 - res;
            char[] xd = String.valueOf(b).toCharArray();
            int un = Character.getNumericValue(xd[0]);
            int dos = Character.getNumericValue(xd[1]);
            int[] ay = { un, dos };
            pos = ay;
        }
        if (isItSerOrEsc(casillas.get(b))) {
            playSerOrEsc(casillas.get(b), b, ficha);
            return pos;
        } else {
            ficha.changePos(pos);
            casillas.get(b).setFicha(ficha);
            return pos;
        }
    }
    // METODOS PARA RETORNAR DATOS

    /**
     * Obtiene todas las casillas
     * 
     * @return
     */
    public ArrayList<Casilla> getCasillas() {
        return casillas;
    }

    public Casilla getCasilla(int x, int y) {
        Casilla res = null;
        int[] guard = { x, y };
        for (Casilla a : casillas) {
            if (Arrays.equals(a.getPos(), guard)) {
                res = a;
            }
        }
        return res;

    }
    // METODOS PRIVADOS

    /**
     * genera las casillas del tablero
     */
    private void generateCasillasNormal() {
        int id = 0;
        Random x = new Random();
        for (int i = 0; i <= 10; i++) {
            for (int j = 0; j <= 10; j++) {

                int[] pos = { i, j };
                if (validateCas(pos)) {
                    int p = x.nextInt(200);
                    // Casillas
                    if (p <= 130) {
                        if (p <= 70) {
                            casillas.add(new Cnormal(pos));
                        }
                        if (p >= 70 && p <= 80) {
                            // saltarina n
                            casillas.add(new Cnormal(pos));
                        }
                        if (p >= 80 && p <= 90) {
                            // saltarina inversa n
                            casillas.add(new Cnormal(pos));
                        }
                        if (p >= 90 && p <= 100) {
                            // Mortal
                            casillas.add(new Cnormal(pos));
                        }
                        if (p >= 100 && p <= 110) {
                            // avance
                            casillas.add(new Cnormal(pos));
                        }
                        if (p >= 110 && p <= 120) {
                            // retroceso
                            casillas.add(new Cnormal(pos));
                        }
                        if (p >= 120 && p <= 130) {
                            // preguntona
                            casillas.add(new Cnormal(pos));
                        }

                    } else { // serpientes y escaleras
                        if (p >= 131 && p <= 170) {
                            Serpiente xd = new Serpiente(pos);
                            xd.setId(id);
                            Serpiente xd2 = new Serpiente(generateRandom(pos[0], pos[1]));
                            xd2.setId(id);
                            casillas.add(xd);
                            casillas.add(xd2);
                            id++;
                        } else {
                            Escalera xd = new Escalera(pos);
                            Escalera xd2 = new Escalera(generateRandom(pos[0], pos[1]));
                            casillas.add(xd);
                            casillas.add(xd2);
                        }
                    }

                }
            }
        }
    }

    /**
     * genera una pos al azar
     * 
     * @param x
     * @param y
     * @return
     */
    private int[] generateRandom(int x, int y) {
        Random xd = new Random();
        while (true) {
            int f = x + (xd.nextInt(10) * (xd.nextBoolean() ? 1 : -1));
            int l = y + (xd.nextInt(10) * (xd.nextBoolean() ? 1 : -1));
            if (f < 10 && f > 1 && l < 10 && l > 1) {
                int[] res = { f, l };
                return res;

            }
        }

    }

    /**
     * valida que exista la casilla
     * 
     * @param pos
     * @return
     */
    private boolean validateCas(int[] pos) {
        boolean res = true;
        for (Casilla a : casillas) {
            if (Arrays.equals(pos, a.getPos())) {
                res = false;
            }
        }
        return res;
    }

    /**
     * verifica si es serpiente o escalera
     * 
     * @param a
     * @return
     */
    private boolean isItSerOrEsc(Casilla a) {
        boolean res = false;
        String clase = a.getClass().getName();
        if (clase == "Serpiente" || clase == "Escalera")
            res = true;
        return res;
    }

    /**
     * juega la casilla si es serpiente o escalera
     * 
     * @param b
     * @param prev
     * @param r
     * @return
     */
    private int[] playSerOrEsc(Casilla b, int prev, Ficha r) {
        int[] xd = null;
        int id = b.getId();
        for (Casilla a : casillas) {
            if (b.getClass().getName() == "Serpiente") {
                if (id == a.getId() && prev < convert(a.getPos())) {
                    xd = a.getPos();
                    r.changePos(xd);
                    a.setFicha(r);
                }
            }
            if (b.getClass().getName() == "Escalera") {
                if (id == a.getId() && prev > convert(a.getPos())) {
                    xd = a.getPos();
                    r.changePos(xd);
                    a.setFicha(r);
                }
            }
        }
        return xd;
    }

    /**
     * convierte un numero a entero
     * 
     * @param xd
     * @return
     */
    private int convert(int[] xd) {
        int b = 9165160;
        if (xd[1] != 10) {
            b = Integer.parseInt(xd[0] + "" + xd[1]);
        } else {
            b = Integer.parseInt(xd[0] + "" + 0);
        }
        return b;
    }

    public String getType(int x, int y) {
        // ToDO: retornar el tipo de ficha para el GUI
        return null;
    }
}
