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
    private double pesc;
    private double pserp;
    private double pesp;
    private int size;
    private ArrayList<Casilla> casillas;
    private ArrayList<Casilla> serpEsc;

    /**
     * Constructor para tablero
     */
    public Tablero(double pesc, double pserp, double pesp, int size) {
        //System.out.println(pesc);
        //System.out.println(pserp);
        //System.out.println(pesp);
        this.pesc = pesc;
        this.pesp = pesp;
        this.pserp= pserp;
        this.size = size;
        System.out.println(this.size);
        casillas = new ArrayList<Casilla>();
        serpEsc = new ArrayList<Casilla>();
        generateCasillasNormal();

    }

    // METODOS PARA CREAR O ASIGNAR DATOS

    /**
     * Realiza una jugada dado el turno y la posicion donde debe moverse
     *
     * @param ficha the ficha
     * @param pos   the pos
     * @return the int [ ]
     * @throws POOBStairsException the poob stairs exception
     */
    public int[] jugar(Ficha ficha, int[] pos) throws POOBStairsException {
        int b = 0;
        // printId();
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
        if (b == (size*size)- size) {
            // System.out.println(b + "---" + ficha.getColor() + pos[0] + "/" + pos[1]);
            // System.out.println(casillas.size() + "longitud casillas");
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
            pos = playSerOrEsc(casillas.get(b), b, ficha);
            // System.out.println(Arrays.toString(pos));
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
     * @return casillas casillas
     */
    public ArrayList<Casilla> getCasillas() {
        return casillas;
    }

    /**
     * retorna una casilla en especifico dada su coordenada x,y
     *
     * @param x the x
     * @param y the y
     * @return Casilla casilla
     */
    public Casilla getCasilla(int x, int y) {
        Casilla res = null;
        int[] guard = { x, y };
        for (Casilla a : casillas) {
            if (Arrays.equals(a.getPos(), guard)) {
                res = a;
                break;
                //System.out.println(Arrays.toString(a.getPos()) + "--->" + Arrays.toString(guard));
                //System.out.println("paso" +" --->" + a.getClass().getSimpleName() + "---> " + a.getId());
            }
        }
        //System.out.println(res.getClass().getSimpleName() + "---> ID"+ res.getId() + "----->" + Arrays.toString(res.getPos())) ;
        return res;

    }
    // METODOS PRIVADOS

    /**
     * genera las casillas del tablero
     */
    private void generateCasillasNormal() {
        int id = 0;
        Random x = new Random();
        // ArrayList<Casilla> especial = new ArrayList<Casilla>();
        int[] inic = { 0, 0 };
        int[] fin = { size, size-1 };
        for (int i = 0; i <= size; i++) {
            System.out.print(i);
            for (int j = 0; j <= size-1; j++) {
                System.out.println(j + "J");
                int[] pos = { i, j };
                
                //System.out.println(i);
                if (validateCas(pos)) {
                    int p = x.nextInt(101);
                    // System.out.println(p);
                    // Casillas
                    if (!pos.equals(inic) || !pos.equals(fin)) {
                        
                
                        //System.out.println(Double.compare((double)p, (double)p * pesp));
                        if (Double.compare((double)p, pesp*100) <= 0)   {
                            //System.out.println("especial");
                            genereateEspecial(p, pos);
                            //casillas.add(new Cnormal(pos));
                        }else if(Double.compare((double)p, pserp*100 ) <= 0){ 
                            //System.out.println("serpiente");
                            // System.out.println(Arrays.toString(posx));
                            int[] posx = generateRandom(pos[0], pos[1]);
                            id = generateSerp(id, pos, posx);
                           // System.out.println(id + "---" + "serp");
                        }else if(Double.compare((double)p, pesc*100 + pserp*100) <= 0){
                            //System.out.println("escalera");
                            int[] posx = generateRandom(pos[0], pos[1]);
                            id = generateEsc(id, pos, posx);
                            //System.out.println(id + "---" + "esc");
                        }else{
                            //System.out.println("nromal");
                            casillas.add(new Cnormal(pos));
                            }
                        }
                    }
                }
            }
        fuss(serpEsc);
        System.out.println(casillas.size() +" -->" + "tamaño casillas");
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
        int tam = this.size;
        while (true) {
            int f = x + (xd.nextInt(5) * (xd.nextBoolean() ? 1 : -1));
            int l = y + (xd.nextInt(5) * (xd.nextBoolean() ? 1 : -1));
            if (f - 1 <= (tam -1)  && f - 1 > 0 && l - 1 <= (tam-1) && l - 1 > 0) {
                int[] res = { f - 1, l - 1 };
                if (validateCasEsp(res)) {
                    return res;
                }

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
        //System.out.println(casillas.size());
        boolean res = true;
        for (Casilla a : casillas) {
            if (Arrays.equals(pos, a.getPos())) {
                res = false;
                break;
            }
        }
        return res;
    }

    /**
     * valida si se encuentran casillas especiales
     * 
     * @param pos
     * @return
     */
    private boolean validateCasEsp(int[] pos) {
        boolean res = true;
        for (Casilla a : serpEsc) {
            if (Arrays.equals(pos, a.getPos())) {
                res = false;
                break;
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
        if (a instanceof Serpiente || a instanceof Escalera)
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
        // System.out.println("esc" + " -- " + b.getId());
        for (Casilla a : casillas) {
            if (b instanceof Serpiente) {
                if (id == a.getId() && prev > convert(a.getPos())) {
                    xd = a.getPos();
                    r.changePos(xd);
                    a.setFicha(r);

                    // System.out.println(Arrays.toString(xd) + "---> ser id:" + a.getId());
                } else {
                    xd = b.getPos();
                }
            }
            if (b instanceof Escalera) {
                if (id == a.getId() && prev < convert(a.getPos())) {
                    xd = a.getPos();
                    r.changePos(xd);
                    a.setFicha(r);
                    // System.out.println(Arrays.toString(xd) + "---> esc id:" + a.getId());
                } else {
                    xd = b.getPos();
                }
            }
        }
        // System.out.println(Arrays.toString(xd));
        if (b instanceof Serpiente || b instanceof Escalera) {
            int[] res = { b.getPos()[0], b.getPos()[1] + 5 };
            xd = res;
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

    /**
     * fusiona las casillas
     * 
     * @param a
     */
    private void fuss(ArrayList<Casilla> a) {
        fussion(a);
    }

    /**
     * proceso mas complejo de fusion
     * 
     * @param a
     */
    private void fussion(ArrayList<Casilla> a) {
        for (int i = 0; i < casillas.size() - size; i++) {
            for (int j = 0; j < a.size(); j++) {
                if (i == 0) {
                    // System.out.println(a.get(j).getId() + "---> POS" +
                    // Arrays.toString(a.get(j).getPos()));
                }
                if (j == 0) {
                    // System.out.println("cas:" + Arrays.toString(casillas.get(i).getPos()));
                }
                if (Arrays.equals(casillas.get(i).getPos(), a.get(j).getPos())) {
                    casillas.set(i, a.get(j));
                    //System.out.println(a.get(j).getId() + " --> POS"+ Arrays.toString(a.get(j).getPos()));
                }
            }
        }
    }
    
    private void genereateEspecial(int p, int[] pos){
        if (p >= 0 && p <= 25) {
            // saltarina n
            casillas.add(new Saltarina_n(pos));
            // especial.add(new Saltarina_n(pos));
        }
        if (p > 25 && p <= 50) {
            // saltarina inversa n
            casillas.add(new Saltarina_inv(pos));
        }
        if (p > 50 && p <= 55) {
            // Mortal
            casillas.add(new Mortal(pos));
        }
        if (p > 55 && p <= 70) {
            // avance
            casillas.add(new Avance(pos));
        }
        if (p > 70 && p <= 80) {
            // retroceso
            casillas.add(new Retroceso(pos));
        }
        if (p > 80 && p <= 101) {
            // preguntona
            casillas.add(new Preguntona(pos));
        }
    }

    private int generateSerp(int id, int[] pos, int[] posx){
        casillas.add(new Cnormal(pos));
        Serpiente xd = new Serpiente(pos);
        xd.setId(id);
        Serpiente xd2 = new Serpiente(posx);
        xd2.setId(id);
        serpEsc.add(xd2);
        serpEsc.add(xd);
        id++;
        return id;
    }

    private int generateEsc(int id, int[] pos, int[] posx){
        if (pos[0] < 10) {
            casillas.add(new Cnormal(pos));
            Escalera xd = new Escalera(pos);
            Escalera xd2 = new Escalera(posx);
            xd.setId(id);
            xd2.setId(id);
            serpEsc.add(xd);
            serpEsc.add(xd2);
            id++;
            return id;
        } else {
            casillas.add(new Cnormal(pos));
            return id;
        }
    }
}
