package com.cramirez.customcollections.tree;

import com.cramirez.customcollections.iterator.Iterator;
import com.cramirez.customcollections.iterator.ReversedIterator;
import com.cramirez.customcollections.set.treeset.TreeSet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TreeSetTest {

    @Test
    void insertMethod() {
        // Given
        TreeSet<Integer> tree = new TreeSet<>();

        // When
        for(int i = 10; i > 0; i--) {
            tree.add(i);
        }

        //Then
        for(int i = 1; i <= 10; i++) {
            assertTrue(tree.contains(i));
        }
    }



    @Test
    void insertMethodWorksWhenDataInsertedIsNotSequential() {
        // Given
        TreeSet<Integer> tree = new TreeSet<>();

        // When
        tree.add(2);
        tree.add(5);
        tree.add(5);
        tree.add(4);
        tree.add(3);
        tree.add(9);
        tree.add(1);
        tree.add(8);
        tree.add(6);
        tree.add(7);
        tree.add(0);

        //Then
        for(int i = 0; i < 10; i++) {
            assertTrue(tree.contains(i));
        }
    }

    @Test
    void deleteMethodWorksWhenDeletingRoot() {
        // Given
        TreeSet<String> tree = new TreeSet<>();
        for(int i = 0; i < 10; i++) {
            assertTrue(tree.add("MOCK DATA "+ i));
        }

        for(int i = 20; i >= 10; i--) {
            assertTrue(tree.add("MOCK DATA "+ i));
        }

        // When
        assertEquals("MOCK DATA 16", tree.remove("MOCK DATA 16"));

        //Then
        for(int i = 0; i <= 20; i++) {
            if(i == 16)
                continue;
            assertTrue(tree.contains("MOCK DATA " + i));
        }
    }

    @Test
    void deleteMethodWorksWhenDeletingLowestNumber() {
        // Given
        TreeSet<String> tree = new TreeSet<>();
        for(int i = 0; i < 100000; i++) {
            assertTrue(tree.add("MOCK DATA "+ i));
        }

        // When
        assertEquals("MOCK DATA 0", tree.remove("MOCK DATA 0"));

        //Then
        for(int i = 1; i < 100000; i++) {
            assertTrue(tree.contains("MOCK DATA " + i));
        }
    }

    @Test
    void deleteMethodWorksWhenDeletingHighestNumber() {
        // Given
        TreeSet<String> tree = new TreeSet<>();
        for(int i = 0; i < 100000; i++) {
            assertTrue(tree.add("MOCK DATA "+ i));
        }

        // When
        assertEquals("MOCK DATA 99999", tree.remove("MOCK DATA 99999"));

        //Then
        for(int i = 0; i < 99999; i++) {
            assertTrue(tree.contains("MOCK DATA " + i));
        }
        assertFalse(tree.contains("MOCK DATA 99999"));
    }

    @Test
    void deleteMethodWorksWhenDeletingEveryNumber() { // this covers lines 102 to 111
        // Given
        TreeSet<String> tree = new TreeSet<>();
        for(int i = 0; i < 10; i++) {
            assertTrue(tree.add("MOCK DATA "+ i));
        }


        // When
        for(int i = 0; i < 10; i++) {

            assertEquals("MOCK DATA " + i, tree.remove("MOCK DATA " + i));
        }

        //Then
        for(int i = 0; i < 10; i++) {
            assertFalse(tree.contains("MOCK DATA " + i));
        }
    }

    @Test
    void deleteMethodWillReturnNullWhenDeletingNullTree() {
        // Given
        TreeSet<String> tree = new TreeSet<>();

        // When / Then
        assertNull(tree.remove("MOCK DATA"));
    }

    @Test
    void iteratorWorksAndHasNextNodesInOrder() {
        //Given
        TreeSet<Integer> tree = new TreeSet<>();

        //When
        for(int i = 999; i >= 0; i--) {
            assertTrue(tree.add(i));
        }

        Iterator<Integer> it = tree.iterator();

        //Then
        for(int i = 0; i < 1000; i++) {
            assertTrue(it.hasNext());
            assertEquals(i, it.next());
        }
    }

    @Test
    void reversedIteratorWorksAndHasNextNodesInOrder() {
        //Given
        TreeSet<Integer> tree = new TreeSet<>();

        //When
        for(int i = 0; i < 10; i++) {
            assertTrue(tree.add(i));
        }

        ReversedIterator<Integer> it = tree.reversedIterator();

        //Then
        for(int i = 9; i >= 0; i--) {
            assertTrue(it.hasNext());
            assertEquals(i, it.next());
        }
        assertEquals(10, tree.size());
    }

}
