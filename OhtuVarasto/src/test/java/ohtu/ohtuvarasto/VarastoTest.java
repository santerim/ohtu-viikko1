package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        varasto = new Varasto(10, 0);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void konstr() {
        varasto = new Varasto(-1);
        varasto = new Varasto(0);
        varasto = new Varasto(1,1);
        varasto = new Varasto(1,2);
        varasto = new Varasto(-1,2);
        varasto = new Varasto(-1,-1);
        varasto.toString();
    }

    /**
     * Test of getSaldo method, of class Varasto.
     */
    @Test
    public void testGetSaldo() {
        varasto.lisaaVarastoon(1.0);
        assertTrue(varasto.getSaldo() == 1.0);
    }

    /**
     * Test of getTilavuus method, of class Varasto.
     */
    @Test
    public void testGetTilavuus() {
        assertTrue(varasto.getTilavuus() == 10);
    }

    /**
     * Test of paljonkoMahtuu method, of class Varasto.
     */
    @Test
    public void testPaljonkoMahtuu() {
        varasto.lisaaVarastoon(5.0);
        assertTrue(varasto.paljonkoMahtuu() == 5.0);
    }

    /**
     * Test of lisaaVarastoon method, of class Varasto.
     */
    @Test
    public void testLisaaVarastoon() {
        assertTrue(varasto.paljonkoMahtuu() == 10.0);
        varasto.lisaaVarastoon(5.0);
        assertTrue(varasto.paljonkoMahtuu() == 5.0);
    }
    
    @Test
    public void testLisaaVarastoonNegatiivinenLuku() {
        varasto.lisaaVarastoon(-5.0);
        assertTrue(varasto.paljonkoMahtuu() == 10.0);
    }
    
    @Test
    public void testLisaaVarastoonNiinPaljonKuinMahtuu() {
        varasto.lisaaVarastoon(varasto.paljonkoMahtuu());
        assertTrue(varasto.paljonkoMahtuu() == 0.0);
    }
    
    @Test
    public void testLisaaVarastoonEnemmanKuinMahtuu() {
        varasto.lisaaVarastoon(11.0);
        assertTrue(varasto.paljonkoMahtuu() == 0.0);
        assertTrue(varasto.getSaldo() == 10.0);
    }

    /**
     * Test of otaVarastosta method, of class Varasto.
     */
    @Test
    public void testOtaVarastosta() {
        varasto.lisaaVarastoon(5.0);
        assertTrue(varasto.paljonkoMahtuu() == 5.0);
        varasto.otaVarastosta(5.0);
        assertTrue(varasto.paljonkoMahtuu() == 10.0);
    }
    
    @Test
    public void testOtaVarastostaEnemmanKuinSiellaOn() {
        varasto.lisaaVarastoon(5.0);
        varasto.otaVarastosta(6.0);
        assertTrue(varasto.getSaldo() == 0.0);
    }
    
    @Test
    public void testOtaVarastostaNegatiivinenMaara() {
        varasto.lisaaVarastoon(5.0);
        double otettu = varasto.otaVarastosta(-5.0);
        assertTrue(otettu == 0.5);
    }

    /**
     * Test of toString method, of class Varasto.
     */
    @Test
    public void testToString() {
        assertEquals("saldo = 0.0, vielä tilaa 10.0", varasto.toString());
    }
}