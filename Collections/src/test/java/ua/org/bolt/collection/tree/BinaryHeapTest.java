package ua.org.bolt.collection.tree;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Class BinaryHeapTest
 *
 * @author AckiyBolt
 */
public class BinaryHeapTest {

    private BinaryHeap<Integer> underTest;

    @Before
    public void setUp() throws Exception {
        underTest = new BinaryHeap<>();
    }

    @Test
    public void testAdd_AddTwoElements_SizeBecomeTwo () {
        underTest.add(0);
        underTest.add(1);

        int expected = 2;
        int actual = underTest.size();

        assertEquals(expected, actual);
    }

    @Test
    public void testAdd_AddSevenDescSortedElements_NoBalanceNeeded () {

        List<Integer> elements = new ArrayList<>();
        elements.add(7);
        elements.add(6);
        elements.add(5);
        elements.add(4);
        elements.add(3);
        elements.add(2);
        elements.add(1);

        for( Integer element : elements)
            underTest.add(element);

        List<Integer> expected = elements;
        List<Integer> actual = underTest.getAsList();

        assertEquals(expected, actual);
    }

    @Test
    public void testAdd_AddSevenAscSortedElements_TreeIsBalanced() {

        List<Integer> elements = new ArrayList<>();
        elements.add(1);
        elements.add(2);
        elements.add(3);
        elements.add(4);
        elements.add(5);
        elements.add(6);
        elements.add(7);

        for( Integer element : elements)
            underTest.add(element);

        checkBalance();
    }

    @Test
    public void testHeapify_CallItWithSevenAscSortedElements_TreeIsBalanced() {

        List<Integer> elements = underTest.getAsList();
        elements.add(1);
        elements.add(7);
        elements.add(6);
        elements.add(5);
        elements.add(4);
        elements.add(3);
        elements.add(2);

        underTest.heapify(0);

        checkBalance();
    }

    @Test
    public void testAddAll_AscSortedCollectionWithSevenElements_TreeIsBalanced() {

        List<Integer> elements = new LinkedList<>();
        elements.add(1);
        elements.add(2);
        elements.add(3);
        elements.add(4);
        elements.add(5);
        elements.add(6);
        elements.add(7);

        underTest.addAll(elements);

        checkBalance();
    }

    @Test
    public void testParametrizedConstructor_AscSortedCollectionWithSevenElements_TreeIsBalanced() {

        List<Integer> elements = new LinkedList<>();
        elements.add(1);
        elements.add(2);
        elements.add(3);
        elements.add(4);
        elements.add(5);
        elements.add(6);
        elements.add(7);

        underTest = new BinaryHeap<>(elements);

        checkBalance();
    }

    @Test
    public void testPopRoot_PopElement_TreeIsBalanced() {

        underTest.add(8);
        underTest.add(7);
        underTest.add(6);
        underTest.add(5);
        underTest.add(4);
        underTest.add(3);
        underTest.add(2);
        underTest.add(1);

        underTest.popRoot();

        checkBalance();
    }

    @Test
    public void testPopRoot_PopElement_ElementIsMax() {

        underTest.add(8);
        underTest.add(7);
        underTest.add(6);
        underTest.add(5);
        underTest.add(4);
        underTest.add(3);
        underTest.add(2);
        underTest.add(1);

        Integer actual = underTest.popRoot();
        Integer expected = 8;

        assertEquals(expected, actual);
    }

    private void checkBalance() {

        List<Integer> elements = underTest.getAsList();

        assertTrue("Root lower than it's children: " + elements, elements.get(0) > elements.get(1) && elements.get(0) > elements.get(2));
        assertTrue("Left root child lower than it's children: " + elements, elements.get(1) > elements.get(3) && elements.get(1) > elements.get(4));
        assertTrue("Right root child lower than it's children: " + elements, elements.get(2) > elements.get(5) && elements.get(2) > elements.get(6));
    }
}
