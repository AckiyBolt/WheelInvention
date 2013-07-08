package ua.org.bolt.collection;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * Class LinkedStackTest
 *
 * @author AckiyBolt
 */

public class LinkedStackTest {

    private LinkedStack<Integer> underTest;

    @Before
    public void setUp() throws Exception {
        underTest = new LinkedStack<>();
    }

    @Test
    public void testPush_PushTwoElements_SizeBecomeTwo() throws Exception {

        underTest.push(Integer.valueOf(0));
        underTest.push(Integer.valueOf(0));

        int expected = 2;
        int actual = underTest.size();

        assertEquals(expected, actual);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testPush_PushNull_IllegalArgumentException() throws Exception {
        underTest.push(null);
    }

    @Test
    public void testPop_PushTwoElementsAndPopOne_SizeBecomeOne() throws Exception {

        underTest.push(Integer.valueOf(0));
        underTest.push(Integer.valueOf(0));

        underTest.pop();

        int expected = 1;
        int actual = underTest.size();

        assertEquals(expected, actual);
    }

    @Test
    public void testPop_PushThreeElementsAndPopTwo_SecondPushedElementIsTheSecondPushed() throws Exception {

        underTest.push(Integer.valueOf(0));
        underTest.push(Integer.valueOf(1));
        underTest.push(Integer.valueOf(2));

        underTest.pop();

        Integer expected = 1;
        Integer actual = underTest.pop();

        assertEquals(expected, actual);
    }

    @Test
    public void testPop_PushOneAndPopThreeElements_ThirdPopedElementIsNull() throws Exception {

        underTest.push(Integer.valueOf(0));
        underTest.pop();
        underTest.pop();

        Object expected = null;
        Integer actual = underTest.pop();

        assertEquals(expected, actual);
    }

    @Test
    public void testPeek_PeekFromFilledBufferElement_SizeStayTheSame() throws Exception {

        underTest.push(Integer.valueOf(0));

        int expected = underTest.size();
        underTest.peek();
        int actual = underTest.size();

        assertEquals(expected, actual);
    }

    @Test
    public void testPeek_PeekFromFilledBufferElement_PeekedElementIsTheFirstPushed() throws Exception {

        int expected;

        underTest.push(Integer.valueOf(1));
        underTest.push(Integer.valueOf(2));
        underTest.push(Integer.valueOf(expected = 3));


        int actual = underTest.peek();

        assertEquals(expected, actual);
    }

    @Test
    public void testPeek_PeekFromEmptyBuffer_Null() throws Exception {

        Object expected = null;
        Integer actual = underTest.pop();

        assertEquals(expected, actual);
    }
}