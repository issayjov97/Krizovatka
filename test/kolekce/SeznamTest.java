package kolekce;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 *
 * @author kasi
 */
public class SeznamTest {

    public SeznamTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {

    }
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void testGetPocet() throws KolekceException {
        System.out.println("getPocet");
        Seznam instance = new Seznam();
        instance.pridej(2);
        instance.pridej(20);
        instance.pridej(243);
        instance.odeberPrvni();
        instance.odeberPosledni();
        Object expResult = 1;
        Object result = instance.getPocet();
        assertEquals(expResult, result);
    }

    @Test(expected = KolekceException.class)
    public void test001NastavPrvni() throws Exception {
        System.out.println("nastavPrvni");
        Seznam instance = new Seznam();
        instance.nastavPrvni();
    }

    @Test
    public void test002NastavPrvni() throws Exception {
        System.out.println("nastavPrvni");
        Seznam instance = new Seznam();
        instance.pridej(1);
        instance.nastavPrvni();
        assertEquals(instance.zpristupni(), 1);
    }

    @Test(expected = KolekceException.class)
    public void tes001tJeDalsi() throws KolekceException {
        System.out.println("jeDalsi");
        Seznam instance = new Seznam();
        instance.pridej(1);
        instance.pridej(3);
        instance.jeDalsi();
    }

    @Test
    public void tes002tJeDalsi() throws KolekceException {
        System.out.println("jeDalsi");
        Seznam instance = new Seznam();
        instance.pridej(1);
        instance.pridej(3);
        instance.nastavPrvni();
        assertTrue(instance.jeDalsi());
    }

    @Test(expected = KolekceException.class)
    public void test001PrejdiNaDalsi() throws KolekceException {
        System.out.println("prejdiNaDalsi");
        Seznam instance = new Seznam();
        instance.pridej(1);
        instance.prejdiNaDalsi();

    }

    @Test
    public void testJePrazdny() throws KolekceException {
        System.out.println("jePrazdny");
        Seznam instance = new Seznam();
        instance.pridej(1);
        assertFalse(instance.jePrazdny());
    }

    @Test
    public void testPridej() throws Exception {
        System.out.println("pridej");
        Seznam instance = new Seznam();
        instance.pridej(1);
        instance.pridej(2);
        instance.pridej(3);
        Object result = 3;
        assertEquals(instance.getPocet(), result);

    }

    @Test(expected = KolekceException.class)
    public void test001OdeberPrvni() throws Exception {
        System.out.println("odeberPrvni");
        Seznam instance = new Seznam();
        instance.odeberPrvni();
    }

    @Test
    public void test002OdeberPrvni() throws Exception {
        System.out.println("odeberPrvni");
        Seznam instance = new Seznam();
        instance.pridej(2);
        instance.pridej(3);
        Object result = instance.odeberPrvni();
        Object expResult = 2;
        assertEquals(expResult, result);

    }

    @Test
    public void test001Iterator() throws KolekceException {
        System.out.println("iterator");
        Seznam instance = new Seznam();
        instance.pridej(2);
        instance.pridej(3);
        Iterator it = instance.iterator();
        assertEquals(it.next(), 2);
        assertEquals(it.next(), 3);

    }

    @Test(expected = NoSuchElementException.class)
    public void test002Iterator() {
        System.out.println("iterator");
        Seznam instance = new Seznam();
        Iterator it = instance.iterator();
        Object data = it.next();
        assertEquals(data, data);

    }

    public void test003Iterator() throws KolekceException {
        System.out.println("iterator");
        Seznam instance = new Seznam();
        instance.pridej(2);
        instance.pridej(3);
        instance.pridej(34);
        Object res = 2;
        Iterator it = instance.iterator();
        Object data = it.next();
        assertEquals(data, res);
        Object data2 = it.next();
        assertEquals(data2, 3);

    }

    @Test
    public void test001Zpristupni() throws Exception {
        System.out.println("zpristupni");
        Seznam instance = new Seznam();
        instance.pridej(2);
        instance.pridej(3);
        instance.nastavPrvni();
        assertEquals(2, instance.zpristupni());
    }

    @Test(expected = KolekceException.class)
    public void test002Zpristupni() throws Exception {
        System.out.println("zpristupni");
        Seznam instance = new Seznam();
        instance.zpristupni();
    }

    @Test
    public void testToArray_0args() throws KolekceException {
        System.out.println("toArray");
        Seznam instance = new Seznam();
        instance.pridej(1);
        instance.pridej(2);
        instance.pridej(3);
        Object[] expResult = {1, 2, 3};
        Object[] result = instance.toArray();
        assertArrayEquals(expResult, result);

    }

    @Test
    public void test001ToArray_GenericType() throws KolekceException {
        System.out.println("toArray");
        Object[] array = new Object[10];
        Seznam instance = new Seznam();
        instance.pridej(1);
        instance.pridej(2);
        instance.pridej(3);
        instance.pridej(4);
        instance.pridej(5);
        Object[] result = instance.toArray(array);
        assertArrayEquals(array, result);

    }

    @Test(expected = IllegalArgumentException.class)
    public void test003ToArray_GenericType() throws KolekceException {
        System.out.println("toArray");
        Object[] array = new Object[1];
        Seznam instance = new Seznam();
        instance.pridej(1);
        instance.pridej(2);
        instance.pridej(3);
        Object[] result = instance.toArray(array);

    }

    @Test
    public void test001OdeberPosledni() throws Exception {
        System.out.println("odeberPosledni");
        Seznam<Integer> instance = new Seznam();
        instance.pridej(2);
        instance.pridej(3);
        instance.pridej(4);
        instance.pridej(5);
        Object result1 = instance.odeberPosledni();
        Object result2 = instance.odeberPosledni();
        assertEquals(5, result1);
        assertEquals(4, result2);

    }

    @Test(expected = KolekceException.class)
    public void test002OdeberPosledni() throws Exception {
        System.out.println("odeberPosledni");
        Seznam<Integer> instance = new Seznam();
        instance.odeberPosledni();

    }

    @Test
    public void testZrus() throws KolekceException {
        System.out.println("zrus");
        Seznam instance = new Seznam();
        instance.pridej(3);
        instance.pridej(4);
        instance.pridej(5);
        instance.zrus();
        assertTrue(instance.jePrazdny());
    }

}
