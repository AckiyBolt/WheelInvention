package ua.bolt;

import org.junit.Before;
import org.junit.Test;
import ua.bolt.ex.FullMapException;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class HashMapTest {

    private HashMap undertest;

    @Before
    public void setUp() throws Exception {
        undertest = new HashMap(10);
    }

    @Test(expected = FullMapException.class)
    public void testPut_GivenFilledMap_WhenAddOneMoreNew_ThenFullMapException() throws Exception {

        addTenElements();

        undertest.put(666, 666L);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPut_GivenEmptyMap_WhenAddNullAsKey_ThenIllegalArgumentException() throws Exception {

        undertest.put(null, 20L);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPut_GivenEmptyMap_WhenAddNullAsValue_ThenIllegalArgumentException() throws Exception {

        undertest.put(20, null);
    }

    @Test
    public void testPut_GivenMapWithOneElement_WhenAddWithSameKey_ThenValueWillRewrite() throws Exception {

        undertest.put(20, 20L);

        undertest.put(20, 666L);

        Long expected = 666L;
        Long actual = undertest.get(20);

        assertEquals(expected, actual);
    }

    @Test
    public void testPutGet_GivenEmptyMap_WhenAddNegativePair_ThenExistInMap() throws Exception {

        undertest.put(-20, -20L);

        Long expected = -20L;
        Long actual = undertest.get(-20);

        assertEquals(expected, actual);
    }

    @Test
    public void testPutGet_GivenEmptyMap_WhenAddPairs_ThenBothExistInMap() throws Exception {
        undertest.put(20, 20L);
        undertest.put(666, 666L);


        Long expected1 = 20L;
        Long expected2 = 666L;

        Long actual1 = undertest.get(20);
        Long actual2 = undertest.get(666);

        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
    }

    @Test
    public void testGet_GivenFilledMap_WhenGetNotExist_ThenReturnNull() throws Exception {

        addTenElements();

        Long expected = null;
        Long actual = undertest.get(666);

        assertEquals(expected, actual);
    }

    @Test
    public void testSize_GivenEmptyMap_WhenAddTwoPairs_ThenSizeGrowsByTwo() throws Exception {

        addTenElements();

        int expected = 10;
        int actual = undertest.size();

        assertEquals(expected, actual);
    }

    @Test
    public void testSize_GivenMapWithOneElement_WhenAddWithSameKey_ThenSizeStillSame() throws Exception {

        undertest.put(20, 20L);

        undertest.put(20, 666L);

        int expected = 1;
        int actual = undertest.size();

        assertEquals(expected, actual);
    }

    @Test
    public void testCollisions() throws FullMapException {
        int initialCapacity = 1000;

        ArrayList<Long> numbers = new ArrayList<>(initialCapacity);

        for (long i = 0; i < initialCapacity; i++) {
            numbers.add(i);
        }

        HashMap map = new HashMap(initialCapacity);

        for(Long i: numbers) {
            map.put(i.intValue(), i);
        }

        for(Long i: numbers) {
            Long expected = i;
            Long actual = map.get(i.intValue());

            assertEquals(expected, actual);
        }
    }

    private void addTenElements() throws FullMapException {
        undertest.put(0, 0L);
        undertest.put(-1, -1L);
        undertest.put(2, 2L);
        undertest.put(-3, -3L);
        undertest.put(4, 4L);
        undertest.put(-5, -5L);
        undertest.put(6, 6L);
        undertest.put(-7, -7L);
        undertest.put(8, 8L);
        undertest.put(-9, -9L);
    }
}