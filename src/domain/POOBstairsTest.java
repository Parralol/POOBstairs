package domain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.util.Arrays;

import org.junit.*;

/**
 * The test class POOBstairsTest
 *
 * @author Santiago Parra / Juan Vizcaino
 * @version v1.0
 */
public class POOBstairsTest {
    private Jugador j1;
    private Jugador j2;
    private Jugador j3;
    private Jugador j4;
    private Tablero tablero;

    private POOBstairs prueba;

    /*
     * constructor para la clase de testeo
     */
    public POOBstairsTest() {

        j1 = new Humano(1);
        j1.setName("Parralol");
        j1.setColor(Color.BLACK);
        j2 = new Humano(2);
        j2.setName("VizcaGod");
        j2.setColor(Color.BLUE);
        j3 = new Humano(3);
        j4 = new Humano(4);

        tablero = new Tablero();
        prueba = new POOBstairs();
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp() {
    }

    @Test
    public void shouldNotAllowSameColor() {

    }

    @Test
    public void shouldMoveCorrectly() {
        int[] pos = { 0, 9 };
        int[] posf = { 1, 5 };
        j1.movFicha(pos);
        prueba.addJugador(j1);
        prueba.addJugador(j2);
        prueba.jugar(6);
        if (prueba.getNombreJugadorEnTurno() == "VizcaGod" && prueba.getTurnoActual() == 2
                && prueba.getJugadorEnTurnoPasado().getPosFicha() == posf) {
            assertTrue(true);
        } else {
            assertFalse(prueba.getNombreJugadorEnTurno() + "-" + prueba.getTurnoActual() + "<--turno"
                    + Arrays.toString(prueba.getJugadorEnTurnoPasado().getPosFicha()) + "<-- posicion ficha", false);
        }
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown() {
    }

}
