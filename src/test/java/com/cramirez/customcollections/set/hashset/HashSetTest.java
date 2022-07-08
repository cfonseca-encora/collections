package com.cramirez.customcollections.set.hashset;

import com.cramirez.customcollections.iterator.Iterator;
import com.cramirez.customcollections.iterator.ReversedIterator;
import com.cramirez.customcollections.list.arraylist.ArrayList;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


public class HashSetTest {
    @Test
    void addMethodDoNotDuplicate() {
        //Given
        HashSet<String> hs = new HashSet<>();

        //When
        hs.add("MOCK DATA 1");
        hs.add("MOCK DATA 1");
        hs.add("MOCK DATA 1");
        hs.add("MOCK DATA 2");
        hs.add("MOCK DATA 2");
        hs.add("MOCK DATA 2");
        hs.add("MOCK DATA 3");
        hs.add("MOCK DATA 3");
        hs.add("MOCK DATA 3");
        hs.add("MOCK DATA 4");
        hs.add("MOCK DATA 5");
        hs.add("MOCK DATA 6");
        hs.add("MOCK DATA 6");
        hs.add("MOCK DATA 6");
        hs.add("MOCK DATA 7");
        hs.add("MOCK DATA 7");
        hs.add("MOCK DATA 7");
        hs.add("MOCK DATA 8");
        hs.add("MOCK DATA 8");
        hs.add("MOCK DATA 8");
        hs.add("MOCK DATA 9");
        hs.add("MOCK DATA 9");
        hs.add("MOCK DATA 9");
        hs.add("MOCK DATA 10");
        hs.add("MOCK DATA 10");
        hs.add("MOCK DATA 10");

        //Then
        assertEquals(10, hs.size());
    }

    @Test
    void removeMethodWorks() {
        //Given
        HashSet<String> hs = new HashSet<>();

        for(int i = 1; i <= 100; i++) {
            hs.add("MOCK DATA " + i);
        }

        //When
        hs.remove("MOCK DATA 1");
        hs.remove("MOCK DATA 10");
        hs.remove("MOCK DATA 5");

        //Then
        assertFalse(hs.contains("MOCK DATA 1"));
        assertFalse(hs.contains("MOCK DATA 10"));
        assertFalse(hs.contains("MOCK DATA 5"));
        assertEquals(97, hs.size());
    }

    @Test
    void iteratorWorks() {
        //Given
        HashSet<String> hs = new HashSet<>();

        //When
        for(int i = 0; i < 1000; i++) {
            hs.add("MOCK DATA " + i);
        }

        //Then
        Iterator<String> it = hs.iterator();
        for(int i = 0; i < 1000; i++) {
            assertTrue(it.hasNext());
            assertNotNull(it.next());
        }

        assertFalse(it.hasNext());
        assertNull(it.next());
    }

    @Test
    void iteratorWontHaveAnyNextWhenEmpty() {
        //Given
        HashSet<String> hs = new HashSet<>();

        //When
        Iterator<String> it = hs.iterator();


        //Then
        assertFalse(it.hasNext());
    }

    @Test
    void reversedIteratorWorks() {
        //Given
        HashSet<String> hs = new HashSet<>();

        //When
        for(int i = 0; i < 1000; i++) {
            hs.add("MOCK DATA " + i);
        }

        //Then
        ReversedIterator<String> it = hs.reversedIterator();
        for(int i = 0; i < 1000; i++) {
            assertTrue(it.hasNext());
            assertNotNull(it.next());
        }

        assertFalse(it.hasNext());
        assertNull(it.next());
    }

    @Test
    void reversedIteratorWontHaveAnyNextWhenEmpty() {
        //Given
        HashSet<String> hs = new HashSet<>();

        //When
        ReversedIterator<String> it = hs.reversedIterator();


        //Then
        assertFalse(it.hasNext());
    }

    @Test
    void addMethodWorksOnBigSets() {
        //Given
        HashSet<String> hs = new HashSet<>();

        //When
        for(int i = 1; i <= 300000; i++) {
            hs.add("MOCK DATA " + i);
        }

        //Then
        for(int i = 1; i <= 300000; i++) {
            assertTrue(hs.contains("MOCK DATA " + i));
        }
    }

    @Test
    void containsWontFindAnyUnexistentData() {
        //Given
        HashSet<String> hs = new HashSet<>();

        //When
        for (int i = 1; i <= 1000; i++) {
            hs.add("MOCK DATA " + i);
        }

        //Then
        for (int i = 1; i <= 10000; i++) {
            assertFalse(hs.contains("MOCC DATA " + i));
        }
    }
}
