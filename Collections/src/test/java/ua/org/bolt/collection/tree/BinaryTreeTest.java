package ua.org.bolt.collection.tree;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * Class BinaryTreeTest
 *
 * @author AckiyBolt
 */
public class BinaryTreeTest {

    private BinaryTree<Integer, Integer> underTest;

    @Before
    public void setUp() throws Exception {
        underTest = new BinaryTree<>();
    }

    @Test
    public void testPut_SixPairs_SizeIncreaseBySix() {
        underTest.put(40, 40);
        underTest.put(20, 20);
        underTest.put(30, 30);
        underTest.put(50, 50);
        underTest.put(70, 70);
        underTest.put(10, 10);

        int actual = underTest.size();
        int expected = 6;
        assertEquals(expected, actual);
    }

    @Test
    public void testPut_SixPairs_TreeIsBalanced() {
        // root node
        underTest.put(50, 50);
        // left branch
        underTest.put(40, 40);
        underTest.put(30, 30);
        // right branch
        underTest.put(60, 60);
        underTest.put(70, 70);
        // right-left node
        underTest.put(55, 55);

        BinaryTree.Node<Integer, Integer> root = underTest.getRoot();
        BinaryTree.Node<Integer, Integer> left = root.left;
        BinaryTree.Node<Integer, Integer> right = root.right;
        BinaryTree.Node<Integer, Integer> expected;

        expected = new BinaryTree.Node<Integer, Integer>(50, 50);
        assertEquals(expected, root);


        expected = new BinaryTree.Node<Integer, Integer>(40, 40);
        assertEquals(expected, left);

        left = left.left;
        expected = new BinaryTree.Node<Integer, Integer>(30, 30);
        assertEquals(expected, left);


        expected = new BinaryTree.Node<Integer, Integer>(60, 60);
        assertEquals(expected, right);

        right = right.right;
        expected = new BinaryTree.Node<Integer, Integer>(70, 70);
        assertEquals(expected, right);


        expected = new BinaryTree.Node<Integer, Integer>(55, 55);
        assertEquals(expected, root.right.left);
    }

    @Test
    public void testPut_TwoPairsWithSameKeyAndValue_SizeIncreaseByOne() {
        underTest.put(2, 1);
        underTest.put(2, 1);

        int actual = underTest.size();
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    public void testPut_TwoPairsWithSameKeyAndDifferentValues_SizeIncreaseByOne() {
        underTest.put(1, 1);
        underTest.put(1, 2);

        int actual = underTest.size();
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    public void testPut_TwoPairsWithSameKeyAndDifferentValues_ValuesHasBeenReplaced() {
        underTest.put(1, 1);
        underTest.put(1, 2);

        Integer actual = underTest.get(Integer.valueOf(1));
        Integer expected = 2;
        assertEquals(expected, actual);
    }

    @Test
    public void testGet_AddValueAndGetIt_SizeIsOne() {
        underTest.put(1, 1);

        Integer actual = underTest.get(Integer.valueOf(1));
        Integer expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    public void testRemove_ThreePairsAddThenRemoveTwoOfThem_SizeIsOne () {

        underTest.put(1, 1);
        underTest.put(2, 2);
        underTest.put(3, 3);
        underTest.remove(1);
        underTest.remove(2);

        int actual = underTest.size();
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Ignore @Test
    public void isAdded () {

    }

    @Ignore @Test
    public void nodeRemovedAndValueReturnedToCaller () {

    }
}
