package com.cramirez.customcollections.list.linkedlist;

import com.cramirez.customcollections.iterator.Iterator;
import com.cramirez.customcollections.iterator.ReversedIterator;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedListTest {

    @Test
    void addMethodAddsNewNode() {
        //Given
        LinkedList<String> l = new LinkedList<>();

        //When
        l.add("MOCK DATA");

        //Then
        assertEquals(l.getAt(0), "MOCK DATA");
    }

    @Test
    void addMethodAddsNewNodeAndUpdatesSize() {
        //Given
        LinkedList<String> l = new LinkedList<>();

        //When
        l.add("MOCK DATA NODE 1");
        l.add("MOCK DATA NODE 2");

        //Then
        assertEquals(l.size(), 2);
    }

    @Test
    void insertMethodAddsNewNodeAtAssignedPositionAndUpdatesSize() {
        //Given
        LinkedList<String> l = new LinkedList<>();
        l.add("MOCK DATA NODE 1");
        l.add("MOCK DATA NODE 2");

        //When
        l.insert(1, "MOCK DATA NODE 2 REPLACEMENT");

        //Then
        assertEquals("MOCK DATA NODE 1", l.getAt(0));
        assertEquals("MOCK DATA NODE 2 REPLACEMENT", l.getAt(1));
        assertEquals("MOCK DATA NODE 2", l.getAt(2));
    }

    @Test
    void insertMethodAtTheBeginningIsCorrect(){
        //Given
        LinkedList<String> l = new LinkedList<>();
        l.add("MOCK DATA NODE 1");
        l.add("MOCK DATA NODE 2");
        //When
        l.insert(0, "MOCK DATA 1 REPLACEMENT");
        //Then
        assertEquals("MOCK DATA 1 REPLACEMENT", l.getAt(0));
    }

    @Test
    void insertMethodAtTheEndIsCorrect(){
        //Given
        LinkedList<String> l = new LinkedList<>();
        l.add("MOCK DATA NODE 1");
        l.add("MOCK DATA NODE 2");
        //When
        l.insert(1, "MOCK DATA 1 REPLACEMENT");
        //Then
        assertEquals("MOCK DATA 1 REPLACEMENT", l.getAt(1));
    }

    @Test
    void insertMethodCrashesWhenGivingIndexOutOfBounds() {
        //Given
        LinkedList<String> l = new LinkedList<>();

        //When
        l.add("MOCK DATA NODE 1");
        l.add("MOCK DATA NODE 2");

        //Then
        assertThrows(IndexOutOfBoundsException.class, () -> l.insert(4, "MOCK DATA NODE 3"));
    }

    @Test
    void getAtMethodReturnsCorrectIndex() {
        //Given
        LinkedList<String> l = new LinkedList<>();

        //When
        l.add("MOCK DATA NODE 1");
        l.add("MOCK DATA NODE 2");

        //Then
        assertEquals("MOCK DATA NODE 2", l.getAt(1));
    }

    @Test
    void getAtCrashesGivenAnIndexOutOfBounds() {
        //Given
        LinkedList<String> l = new LinkedList<>();

        //When
        l.add("MOCK DATA NODE 1");
        l.add("MOCK DATA NODE 2");

        //Then
        assertThrows(IndexOutOfBoundsException.class, () -> l.getAt(3));
    }

    @Test
    void setAtReturnsAndSetInTheCorrectIndex() {
        //Given
        LinkedList<String> l = new LinkedList<>();
        l.add("MOCK DATA NODE 1");
        l.add("MOCK DATA NODE 2");
        l.add("MOCK DATA NODE 3");

        //When
        l.setAt(1, "MODIFYING MOCK DATA NODE 2");

        //Then
        assertEquals("MODIFYING MOCK DATA NODE 2", l.getAt(1));
    }

    @Test
    void setAtReturnsAndSetAtTheLastIndex() {
        //Given
        LinkedList<String> l = new LinkedList<>();
        l.add("MOCK DATA NODE 1");
        l.add("MOCK DATA NODE 2");
        l.add("MOCK DATA NODE 3");

        //When
        l.setAt(2, "MODIFYING MOCK DATA NODE 3");

        //Then
        assertEquals("MODIFYING MOCK DATA NODE 3", l.getAt(2));
    }

    @Test
    void setAtCrashesWhenGivenIndexOutOfBounds() {
        //Given
        LinkedList<String> l = new LinkedList<>();

        //When
        l.add("MOCK DATA NODE 1");
        l.add("MOCK DATA NODE 2");


        //Then
        assertThrows(NullPointerException.class, () -> l.setAt(2, "MODIFYING MOCK DATA NODE 2"));
    }

    @Test
    void removeMethodDeletesSuccessfully() {
        //Given
        LinkedList<String> l = new LinkedList<>();
        l.add("MOCK DATA NODE 1");
        l.add("MOCK DATA NODE 2");
        l.add("MOCK DATA NODE 3");
        l.add("MOCK DATA NODE 4");
        l.add("MOCK DATA NODE 5");
        l.add("MOCK DATA NODE 6");
        l.add("MOCK DATA NODE 7");
        l.add("MOCK DATA NODE 8");
        l.add("MOCK DATA NODE 9");
        l.add("MOCK DATA NODE 10");

        //When
        l.remove(1);

        //Then
        assertEquals("MOCK DATA NODE 3", l.getAt(1));
    }

    @Test
    void removeMethodDeletesSuccessfullyAtTheBeginning() {
        //Given
        LinkedList<String> l = new LinkedList<>();
        l.add("MOCK DATA NODE 1");
        l.add("MOCK DATA NODE 2");
        l.add("MOCK DATA NODE 3");
        l.add("MOCK DATA NODE 4");
        l.add("MOCK DATA NODE 5");
        l.add("MOCK DATA NODE 6");
        l.add("MOCK DATA NODE 7");
        l.add("MOCK DATA NODE 8");
        l.add("MOCK DATA NODE 9");
        l.add("MOCK DATA NODE 10");

        //When
        l.remove(0);

        //Then
        assertEquals("MOCK DATA NODE 2", l.getAt(0));
    }

    @Test
    void removeMethodDeletesSuccessfullyAtTheEnd() {
        //Given
        LinkedList<String> l = new LinkedList<>();
        l.add("MOCK DATA NODE 1");
        l.add("MOCK DATA NODE 2");
        l.add("MOCK DATA NODE 3");
        l.add("MOCK DATA NODE 4");
        l.add("MOCK DATA NODE 5");
        l.add("MOCK DATA NODE 6");
        l.add("MOCK DATA NODE 7");
        l.add("MOCK DATA NODE 8");
        l.add("MOCK DATA NODE 9");
        l.add("MOCK DATA NODE 10");

        //When
        l.remove(9);

        //Then
        assertThrows(NullPointerException.class, () -> l.getAt(9));
    }

    @Test
    void removeAllExecutesCorrectly() {
        //Given
        LinkedList<String> l = new LinkedList<>();
        l.add("MOCK DATA NODE 1");
        l.add("MOCK DATA NODE 2");
        l.add("MOCK DATA NODE 3");
        l.add("MOCK DATA NODE 4");
        int size = l.size();

        //When
        l.removeAll();

        //Then
        assertNotEquals(size, l.size());
    }

    @Test
    void iteratorMethodReturnsLinkedListIteratorWithCurrentNodeAsHead() {
        //Given
        LinkedList<String> l = new LinkedList<>();
        l.add("MOCK DATA NODE 1");

        //When
        Iterator<String> it = l.iterator();

        //Then
        assertEquals(l.getAt(0), it.next());
    }

    @Test
    void sizeUpdatesWhenUsingMethodAdd() {
        //Given
        LinkedList<String> l = new LinkedList<>();
        l.add("MOCK DATA NODE 1");
        l.add("MOCK DATA NODE 2");
        int size = l.size();

        //When
        l.add("MOCK DATA NODE 3");

        //Then
        assertEquals(size + 1, l.size());
    }

    @Test
    void sizeUpdatesWhenUsingMethodInsert() {
        //Given
        LinkedList<String> l = new LinkedList<>();
        l.add("MOCK DATA NODE 1");
        l.add("MOCK DATA NODE 2");
        int size = l.size();

        //When
        l.insert(1, "MOCK DATA NODE 2 REPLACEMENT");

        //Then
        assertEquals(size + 1, l.size());
    }

    @Test
    void sizeUpdatesWhenUsingMethodRemove() {
        //Given
        LinkedList<String> l = new LinkedList<>();
        l.add("MOCK DATA NODE 1");
        l.add("MOCK DATA NODE 2");
        l.add("MOCK DATA NODE 3");
        l.add("MOCK DATA NODE 4");
        l.add("MOCK DATA NODE 5");
        l.add("MOCK DATA NODE 6");
        l.add("MOCK DATA NODE 7");
        l.add("MOCK DATA NODE 8");
        l.add("MOCK DATA NODE 9");
        l.add("MOCK DATA NODE 10");
        int size = l.size();

        //When
        l.remove(1);

        //Then
        assertEquals(size - 1, l.size());
    }

    @Test
    void sizeUpdatesWhenUsingMethodRemoveAll() {
        //Given
        LinkedList<String> l = new LinkedList<>();
        l.add("MOCK DATA NODE 1");
        l.add("MOCK DATA NODE 2");

        //When
        l.removeAll();

        //Then
        assertEquals(0, l.size());
    }

    @Test
    void searchNodeMethodReturnsFirstNode() {
        //Given
        LinkedList<String> l = new LinkedList<>();

        //When
        l.add("MOCK DATA NODE 1");
        l.add("MOCK DATA NODE 2");
        l.add("MOCK DATA NODE 3");

        //Then
        assertEquals("MOCK DATA NODE 1", l.getAt(0));
    }

    @Test
    void searchNodeMethodReturnsLastNode() {
        //Given
        LinkedList<String> l = new LinkedList<>();

        //When
        l.add("MOCK DATA NODE 1");
        l.add("MOCK DATA NODE 2");
        l.add("MOCK DATA NODE 3");

        //Then
        assertEquals("MOCK DATA NODE 3", l.getAt(2));
    }

    @Test
    void toStringReturnsString(){
        //Given
        LinkedList<String> l = new LinkedList<>();

        //When
        l.add("MOCK DATA NODE 1");
        l.add("MOCK DATA NODE 2");
        l.add("MOCK DATA NODE 3");

        //Then
        assertEquals(String.class, l.toString().getClass());
    }

    @Test
    void iteratorWorks(){
        //Given
        LinkedList<String> l = new LinkedList<>();

        //When
        l.add("MOCK DATA 1");
        l.add("MOCK DATA 2");
        l.add("MOCK DATA 3");
        l.add("MOCK DATA 4");
        l.add("MOCK DATA 5");
        l.add("MOCK DATA 6");
        l.add("MOCK DATA 7");
        l.add("MOCK DATA 8");
        l.add("MOCK DATA 9");
        l.add("MOCK DATA 10");

        //Then
        Iterator<String> it = l.iterator();
        assertTrue(it.hasNext());

        assertEquals("MOCK DATA 1", it.next());
        assertTrue(it.hasNext());

        assertEquals("MOCK DATA 2", it.next());
        assertTrue(it.hasNext());

        assertEquals("MOCK DATA 3", it.next());
        assertTrue(it.hasNext());

        assertEquals("MOCK DATA 4", it.next());
        assertTrue(it.hasNext());

        assertEquals("MOCK DATA 5", it.next());
        assertTrue(it.hasNext());

        assertEquals("MOCK DATA 6", it.next());
        assertTrue(it.hasNext());

        assertEquals("MOCK DATA 7", it.next());
        assertTrue(it.hasNext());

        assertEquals("MOCK DATA 8", it.next());
        assertTrue(it.hasNext());

        assertEquals("MOCK DATA 9", it.next());
        assertTrue(it.hasNext());

        assertEquals("MOCK DATA 10", it.next());
        assertFalse(it.hasNext());
    }

    @Test
    void reversedIteratorWorks(){
        //Given
        LinkedList<String> l = new LinkedList<>();

        l.add("MOCK DATA 10");
        l.add("MOCK DATA 9");
        l.add("MOCK DATA 8");
        l.add("MOCK DATA 7");
        l.add("MOCK DATA 6");
        l.add("MOCK DATA 5");
        l.add("MOCK DATA 4");
        l.add("MOCK DATA 3");
        l.add("MOCK DATA 2");
        l.add("MOCK DATA 1");

        //When
        ReversedIterator<String> it = l.reversedIterator();

        //Then
        assertTrue(it.hasNext());

        assertEquals("MOCK DATA 1", it.next());
        assertTrue(it.hasNext());

        assertEquals("MOCK DATA 2", it.next());
        assertTrue(it.hasNext());

        assertEquals("MOCK DATA 3", it.next());
        assertTrue(it.hasNext());

        assertEquals("MOCK DATA 4", it.next());
        assertTrue(it.hasNext());

        assertEquals("MOCK DATA 5", it.next());
        assertTrue(it.hasNext());

        assertEquals("MOCK DATA 6", it.next());
        assertTrue(it.hasNext());

        assertEquals("MOCK DATA 7", it.next());
        assertTrue(it.hasNext());

        assertEquals("MOCK DATA 8", it.next());
        assertTrue(it.hasNext());

        assertEquals("MOCK DATA 9", it.next());
        assertTrue(it.hasNext());

        assertEquals("MOCK DATA 10", it.next());
        assertFalse(it.hasNext());
    }

    @Test
    void iteratorFailsWhenAttemptingToGetIndexOutOfBounds(){
        //Given
        LinkedList<String> l = new LinkedList<>();

        //When
        l.add("MOCK DATA 1");
        l.add("MOCK DATA 2");
        l.add("MOCK DATA 3");
        l.add("MOCK DATA 4");
        l.add("MOCK DATA 5");

        //Then
        Iterator<String> it = l.iterator();
        assertTrue(it.hasNext());

        assertEquals("MOCK DATA 1", it.next());
        assertTrue(it.hasNext());

        assertEquals("MOCK DATA 2", it.next());
        assertTrue(it.hasNext());

        assertEquals("MOCK DATA 3", it.next());
        assertTrue(it.hasNext());

        assertEquals("MOCK DATA 4", it.next());
        assertTrue(it.hasNext());

        assertEquals("MOCK DATA 5", it.next());

        assertThrows(NoSuchElementException.class, it::next);
    }

    @Test
    void reversedIteratorFailsWhenAttemptingToGetIndexOutOfBounds(){
        //Given
        LinkedList<String> l = new LinkedList<>();

        //When
        l.add("MOCK DATA 5");
        l.add("MOCK DATA 4");
        l.add("MOCK DATA 3");
        l.add("MOCK DATA 2");
        l.add("MOCK DATA 1");

        //Then
        ReversedIterator<String> it = l.reversedIterator();
        assertTrue(it.hasNext());

        assertEquals("MOCK DATA 1", it.next());
        assertTrue(it.hasNext());

        assertEquals("MOCK DATA 2", it.next());
        assertTrue(it.hasNext());

        assertEquals("MOCK DATA 3", it.next());
        assertTrue(it.hasNext());

        assertEquals("MOCK DATA 4", it.next());
        assertTrue(it.hasNext());

        assertEquals("MOCK DATA 5", it.next());

        assertThrows(NoSuchElementException.class, it::next);
    }

    @Test
    void isEmpty() {
        LinkedList<String> ll = new LinkedList<>();
        assertTrue(ll.isEmpty());
        ll.add("MOCK DATA 1");
        assertFalse(ll.isEmpty());
    }
}
