package domain;

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

    private Dice dado;

    /*
     * constructor para la clase de testeo
     */
    public POOBstairsTest() {
        j1 = new Humano(1);
        j2 = new Humano(2);
        j3 = new Humano(3);
        j4 = new Humano(4);

        dado = new Dice(0);

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
