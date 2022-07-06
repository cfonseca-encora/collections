package com.cramirez.customcollections.set.linkedhashset;

import com.cramirez.customcollections.iterator.Iterator;
import com.cramirez.customcollections.iterator.ReversedIterator;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedHashSetTest {
    @Test
    void addMethod() {
        // Given
        LinkedHashSet<String> lhs = new LinkedHashSet<>();

        // When
        for (int i = 1; i <= 10; i++) {
            lhs.add("MOCK DATA " + i);
        }

        // Then
        Iterator<String> it = lhs.iterator();
        for (int i = 1; i <= 10; i++) {
            assertTrue(it.hasNext());
            assertNotNull(it.next());
        }
        assertFalse(it.hasNext());
    }

    @Test
    void addMethodWorksOnBigSets() {
        // Given
        LinkedHashSet<String> lhs = new LinkedHashSet<>();

        // When
        for (int i = 1; i <= 100000; i++) {
            lhs.add("MOCK DATA " + i);
        }

        // Then
        Iterator<String> it = lhs.iterator();
        for (int i = 1; i <= 100000; i++) {
            assertTrue(it.hasNext());
            assertNotNull(it.next());
        }
        assertFalse(it.hasNext());
    }

    @Test
    void addMethodDoesntAggregateRepeatedData() {
        // Given
        LinkedHashSet<String> lhs = new LinkedHashSet<>();

        // When
        for (int i = 1; i <= 10; i++) {
            lhs.add("MOCK DATA 1");
        }

        for (int i = 1; i <= 10; i++) {
            lhs.add("MOCK DATA " + i);
        }

        // Then
        Iterator<String> it = lhs.iterator();
        for (int i = 1; i <= 10; i++) {
            assertTrue(it.hasNext());
            String next = it.next();
            assertNotNull(next);
            assertEquals("MOCK DATA " + i, next);
        }
        assertEquals(10, lhs.size());

    }

    @Test
    void removeMethodWorksNormally() {
        // Given
        LinkedHashSet<String> lhs = new LinkedHashSet<>();

        // When
        for (int i = 1; i <= 10; i++) {
            lhs.add("MOCK DATA " + i);
        }

        // Then
        lhs.remove("MOCK DATA 1");
        lhs.remove("MOCK DATA 10");
        Iterator<String> it = lhs.iterator();
        for (int i = 2; i <= 9; i++) {
            assertTrue(it.hasNext());
            String next = it.next();
            assertNotNull(next);
            assertEquals("MOCK DATA " + i, next);
        }
        assertEquals(8, lhs.size());
    }

    @Test
    void removeMethodsWontCrashWhenLinkedHashSetIsEmpty() {
        // Given
        LinkedHashSet<String> lhs = new LinkedHashSet<>();

        // When
        for (int i = 1; i <= 10; i++) {
            lhs.remove("MOCK DATA " + i);
        }

        // Then
        Iterator<String> it = lhs.iterator();
        assertFalse(it.hasNext());
        assertThrows(NoSuchElementException.class, () -> it.next());
        assertEquals(0, lhs.size());
    }

    @Test
    void containsMethodWillFindSomeExistentData() {
        // Given
        LinkedHashSet<String> lhs = new LinkedHashSet<>();
        for (int i = 1; i <= 10; i++) {
            lhs.add("MOCK DATA " + i);
        }

        // When / Then
        for(int i = 1; i <= 10; i++) {
            assertTrue(lhs.contains("MOCK DATA " + i));
        }
    }

    @Test
    void containsMethodWontFindUnexistentData() {
        // Given
        LinkedHashSet<String> lhs = new LinkedHashSet<>();
        for (int i = 1; i <= 10; i++) {
            lhs.add("MOCK DATA " + i);
        }

        // When / Then
        for(int i = 11; i <= 20; i++) {
            assertFalse(lhs.contains("MOCK DATA " + i));
        }
    }

    @Test
    void iteratorMethodWillWorkWithNotEmptySet(){
        // Given
        LinkedHashSet<String> lhs = new LinkedHashSet<>();

        // When
        for (int i = 1; i <= 10; i++) {
            lhs.add("MOCK DATA " + i);
        }

        // Then
        Iterator<String> it = lhs.iterator();
        for (int i = 1; i <= 10; i++) {
            assertTrue(it.hasNext());
            String var = it.next();
            assertNotNull(var);
            assertEquals("MOCK DATA " + i, var);
        }

    }

    @Test
    void iteratorMethodWontWorkWithEmptySet(){
        // Given
        LinkedHashSet<String> lhs = new LinkedHashSet<>();

        // When / Then
        Iterator<String> it = lhs.iterator();
        assertFalse(it.hasNext());
        assertThrows(NoSuchElementException.class, () -> it.next());
    }

    @Test
    void reversedIteratorMethodWillWorkWithNotEmptySet(){
        // Given
        LinkedHashSet<String> lhs = new LinkedHashSet<>();

        // When
        for (int i = 1; i <= 10; i++) {
            lhs.add("MOCK DATA " + i);
        }

        // Then
        ReversedIterator<String> it = lhs.reversedIterator();
        for (int i = 10; i >= 1; i--) {
            assertTrue(it.hasNext());
            String var = it.next();
            assertNotNull(var);
            assertEquals("MOCK DATA " + i, var);
        }

    }

    @Test
    void reversedIteratorMethodWontWorkWithEmptySet(){
        // Given
        LinkedHashSet<String> lhs = new LinkedHashSet<>();

        // When / Then
        ReversedIterator<String> it = lhs.reversedIterator();
        assertFalse(it.hasNext());
        assertThrows(NoSuchElementException.class, () -> it.next());
    }
}
