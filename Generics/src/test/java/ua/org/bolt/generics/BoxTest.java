package ua.org.bolt.generics;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BoxTest {

    private Box<Object> undertest;

    @Before
    public void setUp() throws Exception {
        undertest = new Box<Object>() {};
    }

    @Test
    public void testSize__GivenEmptyBox_WhenAddOneElementPutThanSecond_ThenSizeIncreasedStepByStep() {

        int expected = 1;
        undertest.put(new Object());
        int actual = undertest.size();
        assertEquals(expected, actual);

        expected = 2;
        undertest.put(new Object());
        actual = undertest.size();
        assertEquals( expected, actual );
    }

    @Test
    public void testSize__GivenBoxWithTwoElements_WhenPopOneElement_ThenSizeDecreasedByOne() {

        int expected = 1;

        undertest.put(new Object());
        undertest.put(new Object());

        undertest.pop();

        int actual = undertest.size();

        assertEquals( expected, actual );
    }

    @Test
    public void testClear__GivenBoxWithTwoElements_WhenClearBox_ThenSizeBecomeZero() {

        int expected = 0;

        undertest.put(new Object());
        undertest.put(new Object());

        undertest.clear();

        int actual = undertest.size();

        assertEquals( expected, actual );
    }
}