package ua.org.bolt.collection;

import org.junit.*;
import static org.junit.Assert.*;
import ua.org.bolt.collection.exception.CollectionIsEmptyException;
import ua.org.bolt.collection.exception.CollectionIsFullException;

/**
 * Class CycleBufferTest
 *
 * @author AckiyBolt
 */
public class CycleBufferTest {

    CycleBuffer<Integer> underTest;

    @Before
    public void setUp() throws Exception {
        underTest = new CycleBuffer<>();
    }

    @Test
    public void testPush_PushTwoValues_SizeIncreasesByTwo() throws Exception {

        underTest.push(Integer.valueOf(0));
        underTest.push(Integer.valueOf(0));

        int expected = 2;
        int actual = underTest.size();

        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPush_PushNull_IllegalArgumentException() throws Exception {

        underTest.push(null);
    }

    @Test
    public void testPush_PushFiveValuesWhenMaxSizeIsFive_SizeIsFive() throws Exception {

        int elementsCount = 5;

        underTest = new CycleBuffer<>(elementsCount);

        underTest.push(Integer.valueOf(0));
        underTest.push(Integer.valueOf(0));
        underTest.push(Integer.valueOf(0));
        underTest.push(Integer.valueOf(0));
        underTest.push(Integer.valueOf(0));

        int expected = elementsCount;
        int actual = underTest.size();

        assertEquals(expected, actual);
    }

    @Test(expected = CollectionIsFullException.class)
    public void testPush_PushFiveValuesWhenMaxSizeIsFour_CollectionIsFullException() throws Exception {

        underTest = new CycleBuffer<>(4);

        underTest.push(Integer.valueOf(0));
        underTest.push(Integer.valueOf(0));
        underTest.push(Integer.valueOf(0));
        underTest.push(Integer.valueOf(0));
        underTest.push(Integer.valueOf(0));
    }

    @Test(expected = CollectionIsEmptyException.class)
    public void testPop_PopFromEmptyBuffer_CollectionIsEmptyException() throws Exception {

        underTest.pop();
    }

    @Test
    public void testPop_PopFromFilledBufferTwoElements_SizeDecreesByTwo() throws Exception {

        underTest.push(Integer.valueOf(0));
        underTest.push(Integer.valueOf(0));
        underTest.push(Integer.valueOf(0));

        underTest.pop();
        underTest.pop();

        int expected = 1;
        int actual = underTest.size();

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

        underTest.push(Integer.valueOf(expected = 1));
        underTest.push(Integer.valueOf(2));
        underTest.push(Integer.valueOf(3));


        int actual = underTest.peek();

        assertEquals(expected, actual);
    }

    @Test(expected = CollectionIsEmptyException.class)
    public void testPeek_PeekFromEmptyBuffer_CollectionIsEmptyException() throws Exception {

        underTest.peek();
    }

    @Test
    public void testGeneral_PushFiveElementsPopFour_LastPoppedIsFourAdded() throws Exception {

        int expected;

        underTest.push(Integer.valueOf(1));
        underTest.push(Integer.valueOf(2));
        underTest.push(Integer.valueOf(3));
        underTest.push(expected = Integer.valueOf(4));
        underTest.push(Integer.valueOf(5));

        underTest.pop();
        underTest.pop();
        underTest.pop();
        int actual = underTest.pop();

        assertEquals(expected, actual);
    }

    @Test
    public void testGeneral_PushTwoElementsPopTwo_BufferIsEmpty() throws Exception {

        underTest.push(Integer.valueOf(1));
        underTest.push(Integer.valueOf(2));

        underTest.pop();
        underTest.pop();

        int expected = 0;
        int actual = underTest.size();

        assertEquals(expected, actual);
    }
}
