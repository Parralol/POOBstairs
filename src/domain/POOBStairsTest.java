package domain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.util.Arrays;

import org.junit.*;

/**
 * The test class POOBStairsTest
 *
 * @author Santiago Parra / Juan Vizcaino
 * @version v1.0
 */
public class POOBStairsTest {
    private Jugador j1;
    private Jugador j2;
    private Jugador j3;
    private Jugador j4;

    private POOBStairs prueba;

    /*
     * constructor para la clase de testeo
     */
    public POOBStairsTest() {

        j1 = new Humano(1);
        j1.setName("Parralol");
        j1.setColor(Color.BLACK);
        j2 = new Humano(2);
        j2.setName("VizcaGod");
        j2.setColor(Color.BLUE);
        j3 = new Humano(3);
        j3.setName("xd");
        j3.setColor(Color.GREEN);
        j4 = new Humano(4);
        j4.setName(null);
        j4.setColor(Color.ORANGE);

        prueba = new POOBStairs();
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp() {
        int[] pos = { 0, 9 };
        j1.movFicha(pos);
        try {
            prueba.addJugador(j1);
            prueba.addJugador(j2);
        } catch (POOBStairsException e) {
            assertFalse(true);
        }
    }

    /**
     * Prueba que verifica que no permita el mismo color
     */
    @Test
    public void shouldNotAllowSameColor() {
        Jugador fallo = new Humano(5);
        fallo.setColor(Color.ORANGE);
        try {
            prueba.addJugador(j4);
            prueba.addJugador(fallo);
            assertTrue(false);
        } catch (POOBStairsException e) {
            assertTrue(e.getMessage(), true);
        }
    }

    /**
     * prueba que verifica que la ficha se mueve correctamente.
     */
    @Test
    public void shouldMoveCorrectly() {
        int[] posf = { 1, 5 };
        prueba.jugar(6);
        if (prueba.getNombreJugadorEnTurno().equals("VizcaGod") && prueba.getTurnoActual() == 2
                && prueba.getJugadorEnTurnoPasado().getPosFicha().equals(posf)) {
            assertTrue(false);
        } else {
            assertFalse(prueba.getNombreJugadorEnTurno() + "-" + prueba.getTurnoActual() + "<--turno"
                    + Arrays.toString(prueba.getJugadorEnTurnoPasado().getPosFicha()) + "<-- posicion ficha", false);
        }
    }

    /**
     * Tears down the test fixture.
     * Called after every test case method.
     */

    @After
    public void tearDown() {
    }

}