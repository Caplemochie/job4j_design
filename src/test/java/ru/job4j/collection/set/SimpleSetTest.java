package ru.job4j.collection.set;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SimpleSetTest {

    @Test
    public void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(1));
        assertTrue(set.contains(1));
        assertFalse(set.add(1));
    }

    @Test
    public void whenAddNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(null));
        assertTrue(set.contains(null));
        assertFalse(set.add(null));
    }

    @Test
    public void whenContainsNotNull() {
        Set<Integer> set = new SimpleSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        assertTrue(set.contains(1));
        assertTrue(set.contains(2));
        assertFalse(set.contains(null));
    }

    @Test
    public void whenContainsNull() {
        Set<Integer> set = new SimpleSet<>();
        set.add(null);
        set.add(1);
        set.add(2);
        assertTrue(set.contains(null));
    }

}