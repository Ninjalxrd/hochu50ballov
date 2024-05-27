package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

class DoubleLinkedListWithGenericsTest {

    private DoubleLinkedListWithGenerics<Integer> list;

    @BeforeEach
    void setUp() {
        list = new DoubleLinkedListWithGenerics<Integer>();
    }

    @Test
    void testIsEmpty() {
        assertTrue(list.isEmpty());
        list.add(1);
        assertFalse(list.isEmpty());
    }

    @Test
    void testSize() {
        assertEquals(0, list.size());
        list.add(1);
        assertEquals(1, list.size());
    }

    @Test
    void testAdd() {
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(3, list.size());
        assertEquals(1, (int) list.findId(0));
        assertEquals(2, (int) list.findId(1));
        assertEquals(3, (int) list.findId(2));
    }

    @Test
    void testAddId() {
        list.add(1);
        list.add(2);
        list.addId(1, 3);
        assertEquals(3, list.size());
        assertEquals(1, (int) list.findId(0));
        assertEquals(3, (int) list.findId(1));
        assertEquals(2, (int) list.findId(2));
    }

    @Test
    void testAddFirst() {
        list.addFirst(1);
        list.addFirst(2);
        assertEquals(2, list.size());
        assertEquals(2, (int) list.findId(0));
        assertEquals(1, (int) list.findId(1));
    }

    @Test
    void testRemoveId() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.removeId(1);
        assertEquals(2, list.size());
        assertEquals(1, (int) list.findId(0));
        assertEquals(3, (int) list.findId(1));
    }

    @Test
    void testRemoveFirst() {
        list.add(1);
        list.add(2);
        list.removeFirst();
        assertEquals(1, list.size());
        assertEquals(2, (int) list.findId(0));
    }

    @Test
    void testRemoveLast() {
        list.add(1);
        list.add(2);
        list.removeLast();
        assertEquals(1, list.size());
        assertEquals(1, (int) list.findId(0));
    }

    @Test
    void testFindId() {
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(2, (int) list.findId(1));
    }

    @Test
    void testFindElement() {
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(1, list.findElement(2));
    }

    @Test
    void testClear() {
        list.add(1);
        list.add(2);
        list.clear();
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }

    @Test
    void testIterator() {
        list.add(1);
        list.add(2);
        list.add(3);

        Iterator<Integer> iterator = list.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(1, (int) iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(2, (int) iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(3, (int) iterator.next());
        assertFalse(iterator.hasNext());

        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    void testIteratorRemove() {
        list.add(1);
        list.add(2);
        list.add(3);

        Iterator<Integer> iterator = list.iterator();
        iterator.next();
        iterator.remove();
        assertEquals(2, list.size());
        assertEquals(2, (int) list.findId(0));
        assertEquals(3, (int) list.findId(1));
    }
}