package com.cramirez.customcollections.set.linkedhashset;

import com.cramirez.customcollections.iterator.Iterator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedHashSetTest {
    @Test
    void addMethod() {
        //Given
        LinkedHashSet<String> lhs = new LinkedHashSet<>();

        //Then
        for (int i = 1; i <= 10; i++) {
            lhs.add("MOCK DATA " + i);
        }

        //When
        Iterator<String> it = lhs.iterator();
        for (int i = 1; i <= 10; i++) {
            assertTrue(it.hasNext());
            assertNotNull(it.next());
        }
        assertFalse(it.hasNext());
    }

    @Test
    void addMethodWorksOnBigSets() {
        //Given
        LinkedHashSet<String> lhs = new LinkedHashSet<>();

        //Then
        for (int i = 1; i <= 100; i++) {
            lhs.add("MOCK DATA " + i);
        }

        //When
        Iterator<String> it = lhs.iterator();
        for (int i = 1; i <= 100; i++) {
            assertTrue(it.hasNext());
            assertNotNull(it.next());
        }
        assertFalse(it.hasNext());
    }

    @Test
    void addMethodDoesntAggregateRepeatedData() {
        //Given
        LinkedHashSet<String> lhs = new LinkedHashSet<>();

        //Then
        for (int i = 1; i <= 10; i++) {
            lhs.add("MOCK DATA 1");
        }

        for (int i = 1; i <= 10; i++) {
            lhs.add("MOCK DATA " + i);
        }

        //When
        Iterator<String> it = lhs.iterator();
        for (int i = 1; i <= 10; i++) {
            assertTrue(it.hasNext());
            String next = it.next();
            assertNotNull(next);
            assertEquals("MOCK DATA " + i, next);
        }

    }
}
