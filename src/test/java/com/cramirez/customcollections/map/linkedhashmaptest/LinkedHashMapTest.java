package com.cramirez.customcollections.map.linkedhashmaptest;

import com.cramirez.customcollections.iterator.Iterator;
import com.cramirez.customcollections.iterator.ReversedIterator;
import com.cramirez.customcollections.map.hashmap.Entry;
import com.cramirez.customcollections.map.hashmap.HashMap;
import com.cramirez.customcollections.map.linkedhashmap.LinkedHashMap;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedHashMapTest {
    @Test
    void putMethodWillNotAddDuplicates() {
        // Given
        LinkedHashMap<Integer, String> lhm = new LinkedHashMap<>();

        // When / Then
        for(int i = 1; i < 100; i++) {
            assertNotNull(lhm.put(i, "MOCK DATA " + i));
            assertNull(lhm.put(i, "MOCK DATA " + i));
        }
    }

    @Test
    void getMethodWillFindExistentDataWithIndex() {
        // Given
        LinkedHashMap<Integer, String> lhm = new LinkedHashMap<>();

        // When
        for(int i = 1; i < 100; i++) {
            assertNotNull(lhm.put(i, "MOCK DATA " + i));
        }

        // Then
        for(int i = 1; i < 100; i++) {
            assertEquals("MOCK DATA " + i, lhm.get(i - 1));
        }
    }

    @Test
    void getMethodWillFindExistentDataWithKey() {
        // Given
        LinkedHashMap<Integer, String> lhm = new LinkedHashMap<>();

        // When
        for(int i = 1; i < 100; i++) {
            assertNotNull(lhm.put(i, "MOCK DATA " + i));
        }

        // Then
        for(int i = 1; i < 100; i++) {
            assertEquals("MOCK DATA " + i, lhm.get(new Integer(i)));
        }
    }

    @Test
    void removeMethodReturnsNonNullWhenRemovingAnExistentObject() {
        // Given
        LinkedHashMap<Integer, String> lhm = new LinkedHashMap<>();

        // When
        for(int i = 1; i < 10; i++) {
            assertNotNull(lhm.put(i, "MOCK DATA " + i));
        }

        // Then
        for(int i = 1; i < 10; i++) {
            String obj = lhm.remove(i);
            assertNotNull(obj);
            assertEquals("MOCK DATA " + i, obj);
        }
    }

    @Test
    void removeMethodReturnsNullWhenRemovingANonExistentObject() {
        // Given
        LinkedHashMap<Integer, String> lhm = new LinkedHashMap<>();

        // When
        for(int i = 1; i < 10; i++) {
            assertNotNull(lhm.put(i, "MOCK DATA " + i));
        }

        // Then
        for(int i = 100; i < 1000; i++) {
            assertNull(lhm.remove(i));
        }
    }

    @Test
    void removeMethodReturnsNonNullWhenRemovingLastObject() {
        // Given
        LinkedHashMap<Integer, String> lhm = new LinkedHashMap<>();

        // When
        for(int i = 1; i < 10; i++) {
            assertNotNull(lhm.put(i, "MOCK DATA " + i));
        }

        // Then
        String obj = lhm.remove(9);
        assertNotNull(obj);
        assertEquals("MOCK DATA 9" , obj);
        assertEquals(8, lhm.size());
        assertFalse(lhm.isEmpty());
    }

    @Test
    void iteratorWontWorkWhenEmpty() {
        // Given
        LinkedHashMap<Integer, String> lhm = new LinkedHashMap<>();

        // When / Then
        Iterator<Entry<Integer, String>> it = lhm.iterator();
        assertFalse(it.hasNext());
        assertThrows(NoSuchElementException.class, it::next);
        assertEquals(0, lhm.size());
        assertTrue(lhm.isEmpty());
    }

    @Test
    void iteratorWorksWhenNotEmpty() {
        // Given
        LinkedHashMap<Integer, String> lhm = new LinkedHashMap<>();
        int n = 100;

        // When
        for(int i = 1; i <= n; i++) {
            assertNotNull(lhm.put(i, "MOCK DATA " + i));
        }

        Iterator<Entry<Integer, String>> it = lhm.iterator();

        // Then
        for(int i = 1; i <= n; i++) {
            assertTrue(it.hasNext());
            assertNotNull(it.next());
        }
        assertFalse(it.hasNext());
        assertThrows(NoSuchElementException.class, it::next);
        assertEquals(n, lhm.size());
        assertFalse(lhm.isEmpty());
    }

    @Test
    void reversedIteratorWontWorkWhenEmpty() {
        // Given
        LinkedHashMap<Integer, String> lhm = new LinkedHashMap<>();

        // When / Then
        ReversedIterator<Entry<Integer, String>> it = lhm.reversedIterator();
        assertFalse(it.hasNext());
        assertThrows(NoSuchElementException.class, it::next);
        assertEquals(0, lhm.size());
        assertTrue(lhm.isEmpty());
    }

    @Test
    void reversedIteratorWorksWhenNotEmpty() {
        // Given
        LinkedHashMap<Integer, String> lhm = new LinkedHashMap<>();
        int n = 100;

        // When
        for(int i = 1; i <= n; i++) {
            assertNotNull(lhm.put(i, "MOCK DATA " + i));
        }

        ReversedIterator<Entry<Integer, String>> it = lhm.reversedIterator();

        // Then
        for(int i = 1; i <= n; i++) {
            assertTrue(it.hasNext());
            assertNotNull(it.next());
        }
        assertFalse(it.hasNext());
        assertThrows(NoSuchElementException.class, it::next);
        assertEquals(n, lhm.size());
        assertFalse(lhm.isEmpty());
    }
}
