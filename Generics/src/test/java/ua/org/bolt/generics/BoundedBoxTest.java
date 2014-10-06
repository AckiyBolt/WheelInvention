package ua.org.bolt.generics;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BoundedBoxTest {

    private BoundedBox<Integer> typifiedLink_typifiedObject;
    private BoundedBox untypifiedLink_untypifiedObject;
    private BoundedBox untypifiedLink_typifiedObject;
    private BoundedBox<Integer> typifiedLink_UntypifiedObject;

    @Before
    public void setUp() throws Exception {

        typifiedLink_typifiedObject = new BoundedBox<Integer>();
        untypifiedLink_untypifiedObject = new BoundedBox();
        untypifiedLink_typifiedObject = new BoundedBox<Integer>();
        typifiedLink_UntypifiedObject = new BoundedBox();
    }

    @Test
    public void test_TypifiedLink_typifiedObject__GivenKosherBox_WhenPutInteger_ThenPopInteger() {

        Integer expected = Integer.valueOf(0);
        typifiedLink_typifiedObject.put(Integer.valueOf(0));

        Object actual = typifiedLink_typifiedObject.pop();
        assertTrue(actual instanceof  Integer);

        Integer actualInt = (Integer)actual;
        assertTrue(actualInt.equals(expected));
    }

    @Test
    public void test_TypifiedLink_typifiedObject__GivenKosherBox_WhenPutInteger_ThenPopIntegerAsInteger() {

        Integer expected = Integer.valueOf(0);
        typifiedLink_typifiedObject.put(Integer.valueOf(0));

        Integer actual = typifiedLink_typifiedObject.pop();
        assertTrue(actual.equals(expected));
    }

    @Test
    public void test_untypifiedLink_untypifiedObject__GivenCheatingBox_WhenPutObject_ThenPopObject() {

        untypifiedLink_untypifiedObject.put(new Object());

        Object actual = untypifiedLink_untypifiedObject.pop();
        assertFalse(actual instanceof  Number);
        assertTrue(actual.getClass().equals(Object.class));
    }

    @Test
    public void test_untypifiedLink_typifiedObject__GivenCheatingBox_WhenPutObject_ThenPopObject() {

        untypifiedLink_typifiedObject.put(new Object());

        Object actual = untypifiedLink_typifiedObject.pop();
        assertFalse(actual instanceof  Number);
        assertTrue(actual.getClass().equals(Object.class));
    }

    @Test
    public void test_typifiedLink_UntypifiedObject__GivenKosherBox_WhenPutInteger_ThenPopInteger() {

        Integer expected = Integer.valueOf(0);
        typifiedLink_UntypifiedObject.put(Integer.valueOf(0));

        Object actual = typifiedLink_UntypifiedObject.pop();
        assertTrue(actual instanceof  Integer);

        Integer actualInt = (Integer)actual;
        assertTrue(actualInt.equals(expected));
    }

    /*
     * This:
     *      Integer actual = typedLink.pop();
     * Would compile to this:
     *      Integer actual = (Integer)typedLink.pop();
     *
     * Because link "typedLink" has <Integer> type. But we have Object in Box
     */
    @Test(expected = ClassCastException.class)
    public void testTypification_GivenUntypifiedLinkAndObject_WhenChangeLinkToTypedByInteger_ThenGetClassCastExWhilePop() {

        untypifiedLink_untypifiedObject.put(new Object());

        BoundedBox<Integer> typedLink = untypifiedLink_untypifiedObject;

        Integer actual = typedLink.pop();
    }
}
