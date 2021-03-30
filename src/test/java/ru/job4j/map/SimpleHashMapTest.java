package ru.job4j.map;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;


public class SimpleHashMapTest {

    @Test
    public void whenInsertedThenGetReturnsCorrectUser() {
        SimpleHashMap<Integer, User> map = new SimpleHashMap<>();
        Calendar currentTime = Calendar.getInstance();
        map.insert(0, new User("Gleb", 0, currentTime));
        map.insert(1, new User("Artem", 1, currentTime));
        map.insert(2, new User("Tagir", 2, currentTime));
        assertThat(map.get(0).getName(), is("Gleb"));
        assertThat(map.get(1).getChildren(), is(1));
        assertEquals(map.get(2).getBirthday().getTimeInMillis(), currentTime.getTimeInMillis());
    }

    @Test
    public void whenInsertingByDuplicateKeyShouldReplacesValueAndReturnsTrue() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(0, "Gleb");
        map.insert(1, "Tagir");
        assertThat(map.insert(0, "Artem"), is(true));
        assertThat(map.insert(0, "Vova"), is(true));
        assertThat(map.get(0), is("Vova"));
    }

    @Test
    public void whenInvocationNullKeyThenResultsAreCorrect() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(0, "Gleb");
        map.insert(1, "Artem");
        assertNull(map.get(null));
        map = new SimpleHashMap<>();
        assertTrue(map.insert(null, "Tagir"));
        assertThat(map.getSize(), is(1));
        assertEquals(map.get(null), "Tagir");
    }

    @Test
    public void whenDeleteElementThenGetReturnsNull() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(0, "Gleb");
        map.insert(1, "Tagir");
        map.insert(2, "Artem");
        assertTrue(map.delete(2));
        assertNull(map.get(2));
        assertTrue(map.insert(4, "Vovan"));
        assertTrue(map.delete(4));
        assertNull(map.get(4));
    }

    @Test
    public void whenLoadDefaultIsReachedThenCapacityDoubles() {
        SimpleHashMap<Integer, User> newMap = new SimpleHashMap<>(4);
        Calendar birthday = new GregorianCalendar(1997, 5, 18);
        assertThat(newMap.size(), is(4));
        assertThat(newMap.getSize(), is(0));
        newMap.insert(3, new User("Tagir", 1, birthday));
        newMap.insert(4, new User("Rustem", 2, birthday));
        newMap.insert(5, new User("Artem", 3, birthday));
        assertThat(newMap.size(), is(8));
        assertThat(newMap.getSize(), is(3));
    }

    @Test
    public void iteratorHasNextBeforeAndAfterInvocation() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(5, "Tagir");
        map.insert(10, "Artem");
        map.insert(2, "Gleb");
        Iterator iterator = map.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.hasNext(), is(true));
        System.out.println(map.entrySet()[0]);
        assertThat(iterator.next(), is(map.entrySet()[2]));
        assertThat(iterator.next(), is(map.entrySet()[5]));
        iterator.hasNext();
        assertThat(iterator.next(), is(map.entrySet()[10]));
        assertThat(iterator.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void invocationNextWhenHasNotNextThrowsNSEE() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(0, "Tagir");
        map.insert(1, "Gleb");
        map.insert(2, "Artem");
        Iterator<String> iterator = map.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void invocationNextAfterChangingInnerStateThrowsCME() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(0, "Tagir");
        map.insert(1, "Gleb");
        map.insert(2, "Artem");
        Iterator<String> it = map.iterator();
        map.delete(0);
        it.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void invocationNextAfterInsertingByDuplicateKeyShouldThrowsCME() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(0, "Tagir");
        map.insert(1, "Gleb");
        map.insert(2, "Artem");
        Iterator<String> it = map.iterator();
        map.insert(1, "Vovan");
        it.next();
    }

    @Test
    public void whenInsertingUnconsecutiveKeysThenSecondCallNextShouldReturnExpectedValue() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(1, "Gleb");
        map.insert(10, "Artem");
        Iterator iterator = map.iterator();
        assertThat(iterator.next(), is(map.entrySet()[1]));
        assertThat(iterator.next(), is(map.entrySet()[10]));
        assertThat(iterator.hasNext(), is(false));
    }
}