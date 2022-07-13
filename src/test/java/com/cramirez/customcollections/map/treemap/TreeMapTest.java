package com.cramirez.customcollections.map.treemap;

import com.cramirez.customcollections.iterator.Iterator;
import com.cramirez.customcollections.iterator.ReversedIterator;
import com.cramirez.customcollections.map.Entry;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TreeMapTest {
    @Test
    void insertMethod() {
        // Given
        TreeMap<Integer, String> tree = new TreeMap<>();

        // When
        for(int i = 10; i > 0; i--) {
            tree.put(i, "MOCK DATA " + i);
        }

        //Then
        for(int i = 1; i <= 10; i++) {
            assertTrue(tree.containsKey(i));
        }
    }



    @Test
    void insertMethodWorksWhenDataInsertedIsNotSequential() {
        // Given
        TreeMap<Integer, String> tree = new TreeMap<>();

        // When
        tree.put(2, "MOCK DATA 2");
        tree.put(5, "MOCK DATA 5");
        tree.put(5, "MOCK DATA 5");
        tree.put(4, "MOCK DATA 4");
        tree.put(3, "MOCK DATA 3");
        tree.put(9, "MOCK DATA 9");
        tree.put(1, "MOCK DATA 1");
        tree.put(8, "MOCK DATA 8");
        tree.put(6, "MOCK DATA 6");
        tree.put(7, "MOCK DATA 7");
        tree.put(0, "MOCK DATA 0");

        //Then
        for(int i = 0; i < 10; i++) {
            assertTrue(tree.containsKey(i));
        }
    }

    @Test
    void deleteMethodWorksWhenDeletingRoot() {
        // Given
        TreeMap<Integer, String> tree = new TreeMap<>();
        for(int i = 0; i < 10; i++) {
            assertNotNull(tree.put(i, "MOCK DATA "+ i));
        }

        for(int i = 20; i >= 10; i--) {
            assertNotNull(tree.put(i, "MOCK DATA "+ i));
        }

        // When
        assertEquals("MOCK DATA 16", tree.remove(16));

        //Then
        for(int i = 0; i <= 20; i++) {
            if(i == 16)
                continue;
            assertNotNull(tree.get(i));
        }
    }

    @Test
    void deleteMethodWorksWhenDeletingLowestNumber() {
        // Given
        TreeMap<Integer, String> tree = new TreeMap<>();
        for(int i = 0; i < 100000; i++) {
            assertNotNull(tree.put(i, "MOCK DATA "+ i));
        }

        // When
        assertEquals("MOCK DATA 0", tree.remove(0));

        //Then
        for(int i = 1; i < 100000; i++) {
            assertNotNull(tree.get(i));
        }
    }

    @Test
    void deleteMethodWorksWhenDeletingHighestNumber() {
        // Given
        TreeMap<Integer, String> tree = new TreeMap<>();
        for(int i = 0; i < 100000; i++) {
            assertNotNull(tree.put(i, "MOCK DATA "+ i));
        }

        // When
        assertEquals("MOCK DATA 99999", tree.remove(99999));

        //Then
        for(int i = 0; i < 99999; i++) {
            assertNotNull(tree.get(i));
        }
        assertFalse(tree.containsKey(99999));
        assertEquals(99999, tree.size());
    }

    @Test
    void deleteMethodWorksWhenDeletingEveryNumber() { // this covers lines 102 to 111
        // Given
        TreeMap<Integer, String> tree = new TreeMap<>();
        for(int i = 0; i < 10; i++) {
            assertNotNull(tree.put(i, "MOCK DATA " + i));
        }


        // When
        for(int i = 9; i >= 0; i--) {
            assertEquals("MOCK DATA " + i, tree.remove(i));
        }

        //Then
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
    }

    @Test
    void deleteMethodWillReturnNullWhenDeletingNullTree() {
        // Given
        TreeMap<Integer, String> tree = new TreeMap<>();

        // When / Then
        assertNull(tree.remove(1));
        assertEquals(0, tree.size());
    }

    @Test
    void deleteMethodWorksWhenDataInsertedIsNotSequential2() { // This is almost repeated, but tests line 118
        // Given
        TreeMap<Integer, String> tree = new TreeMap<>();

        // When
        tree.put(3, "MOCK DATA 3");
        tree.put(1, "MOCK DATA 1");
        tree.put(7, "MOCK DATA 7");
        tree.put(0, "MOCK DATA 0");
        tree.put(8, "MOCK DATA 8");
        tree.put(2, "MOCK DATA 2");
        tree.put(5, "MOCK DATA 5");
        tree.put(9, "MOCK DATA 9");
        tree.put(4, "MOCK DATA 4");
        tree.put(6, "MOCK DATA 6");

        //Then
        assertNotNull(tree.remove(7));
        assertNotNull(tree.remove(9));
        assertNotNull(tree.remove(1));
        assertNotNull(tree.remove(8));
        assertNotNull(tree.remove(6));
        assertNotNull(tree.remove(5));
        assertNotNull(tree.remove(4));
        assertNotNull(tree.remove(3));
        assertNotNull(tree.remove(2));
        assertNotNull(tree.remove(0));

        assertEquals(0, tree.size());
    }

    @Test
    void containsValueWillFindExistentData() {
        // Given
        TreeMap<Integer, String> tree = new TreeMap<>();

        // When
        tree.put(0, "MOCK DATA 0");
        tree.put(1, "MOCK DATA 1");
        tree.put(2, "MOCK DATA 2");
        tree.put(3, "MOCK DATA 3");
        tree.put(4, "MOCK DATA 4");
        tree.put(5, "MOCK DATA 5");
        tree.put(6, "MOCK DATA 6");
        tree.put(7, "MOCK DATA 7");
        tree.put(8, "MOCK DATA 8");
        tree.put(9, "MOCK DATA 9");

        // Then
        for(int i = 0; i < 10; i++) {
            assertTrue(tree.containsValue("MOCK DATA " + i));
        }

        assertFalse(tree.containsValue("MOCK DATA 10"));
    }

    @Test
    void containsValueWontFindNonExistentData() {
        // Given
        TreeMap<Integer, String> tree = new TreeMap<>();

        // When / Then
        for(int i = 0; i < 10; i++) {
            assertFalse(tree.containsValue("MOCK DATA " + i));
        }

        assertEquals(0, tree.size());
    }

    @Test
    void iteratorWorksWhenNotEmpty() {
        // Given
        TreeMap<Integer, String> tree = new TreeMap<>();

        // When
        for(int i = 0; i < 10; i++) {
            assertNotNull(tree.put(i, "MOCK DATA " + i));
        }

        // Then
        Iterator<Entry<Integer, String>> it = tree.iterator();
        for(int i = 0; i < 10; i++) {
            assertTrue(it.hasNext());
            assertNotNull(it.next());
        }

        assertFalse(it.hasNext());

        assertEquals(10, tree.size());
    }

    @Test
    void iteratorDoesntWorkWhenEmpty() {
        // Given
        TreeMap<Integer, String> tree = new TreeMap<>();

        // When / Then
        Iterator<Entry<Integer, String>> it = tree.iterator();
        assertFalse(it.hasNext());
        assertThrows(NoSuchElementException.class, it::next);

        assertEquals(0, tree.size());
    }

    @Test
    void reversedIteratorWorksWhenNotEmpty() {
        // Given
        TreeMap<Integer, String> tree = new TreeMap<>();

        // When
        for(int i = 0; i < 10; i++) {
            assertNotNull(tree.put(i, "MOCK DATA " + i));
        }

        // Then
        ReversedIterator<Entry<Integer, String>> it = tree.reversedIterator();
        for(int i = 0; i < 10; i++) {
            assertTrue(it.hasNext());
            assertNotNull(it.next());
        }

        assertFalse(it.hasNext());

        assertEquals(10, tree.size());
    }

    @Test
    void reversedIteratorDoesntWorkWhenEmpty() {
        // Given
        TreeMap<Integer, String> tree = new TreeMap<>();

        // When / Then
        ReversedIterator<Entry<Integer, String>> it = tree.reversedIterator();
        assertFalse(it.hasNext());
        assertThrows(NoSuchElementException.class, it::next);

        assertEquals(0, tree.size());
    }

}
