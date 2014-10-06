package ua.org.bolt.generics;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class MultipleBoundedBoxTest {

    private static class Element
            extends MultipleBoundedBox.A
            implements MultipleBoundedBox.B, MultipleBoundedBox.C {}

    private MultipleBoundedBox<Element> undertest;

    @Before
    public void setUp() throws Exception {
        undertest = new MultipleBoundedBox<Element>();
    }

    @Test
    @Ignore
    public void testHierarchy_GivenEmptyBoxOfElements_WhenAddElement_ThanElementShouldHaveAllTypesAsParents() {

        undertest.put(new Element());
        Element actual = undertest.pop();

        assertTrue(actual instanceof MultipleBoundedBox.A);
        assertTrue(actual instanceof MultipleBoundedBox.B);
        assertTrue(actual instanceof MultipleBoundedBox.C);
    }
}