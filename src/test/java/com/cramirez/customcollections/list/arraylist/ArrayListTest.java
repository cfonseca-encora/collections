package com.cramirez.customcollections.list.arraylist;

import com.cramirez.customcollections.iterator.Iterator;
import com.cramirez.customcollections.iterator.ReversedIterator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayListTest {

    @Test
    void dataListStartsWithSize10(){
        //Given
        ArrayList<String> al = new ArrayList<>();

        //When
        int capacity = al.capacity();
        int size = al.size();
        
        //Then
        assertEquals(10, capacity);
        assertEquals(0, size);
    }


    @Test
    void addMethodWorksUpdatesSizeAndStillHave9Slots() {
        //Given
        ArrayList<String> al = new ArrayList<>();

        //When
        al.add("MOCK DATA 1");

        //Then
        assertEquals(1, al.size());
        assertEquals(10, al.capacity());
    }

    @Test
    void addMethodWorksAndResizesItSelf() {
        //Given
        ArrayList<String> al = new ArrayList<>();

        //When
        al.add("MOCK DATA 1");
        al.add("MOCK DATA 2");
        al.add("MOCK DATA 3");
        al.add("MOCK DATA 4");
        al.add("MOCK DATA 5");
        al.add("MOCK DATA 6");
        al.add("MOCK DATA 7");
        al.add("MOCK DATA 8");
        al.add("MOCK DATA 9");
        al.add("MOCK DATA 10");
        al.add("MOCK DATA 11");


        //Then
        assertEquals(11, al.size());
        assertEquals(20, al.capacity());
    }

    @Test
    void insertMethodWorksWhenIndexIsMinorThanActualArrayLength() {
        //Given
        ArrayList<String> al = new ArrayList<>();
        al.add("MOCK DATA 1");
        al.add("MOCK DATA 2");
        al.add("MOCK DATA 3");
        al.add("MOCK DATA 4");
        al.add("MOCK DATA 5");
        al.add("MOCK DATA 6");
        al.add("MOCK DATA 7");
        al.add("MOCK DATA 8");
        al.add("MOCK DATA 9");
        al.add("MOCK DATA 10");

        //When
        al.insert(9, "MOCK DATA 10 REPLACEMENT");

        //Then
        assertEquals(11, al.size());
        assertEquals("MOCK DATA 10 REPLACEMENT", al.getAt(9));
    }

    @Test
    void insertMethodFailsWhenIndexIsGreaterThanActualArrayLength() {
        //Given
        ArrayList<String> al = new ArrayList<>();

        //When
        al.add("MOCK DATA 1");
        al.add("MOCK DATA 2");
        al.add("MOCK DATA 3");
        al.add("MOCK DATA 4");
        al.add("MOCK DATA 5");
        al.add("MOCK DATA 6");
        al.add("MOCK DATA 7");
        al.add("MOCK DATA 8");
        al.add("MOCK DATA 9");
        al.add("MOCK DATA 10");

        //Then
        assertThrows(IndexOutOfBoundsException.class, () -> al.insert(10, "MOCK DATA 10 REPLACEMENT"));
    }

    @Test
    void insertMethodWorksWhenInsertsAtTheBeginning() {
        //Given
        ArrayList<String> al = new ArrayList<>();

        //When
        al.add("MOCK DATA 1");
        al.add("MOCK DATA 2");
        al.add("MOCK DATA 3");
        al.add("MOCK DATA 4");
        al.add("MOCK DATA 5");
        al.add("MOCK DATA 6");
        al.add("MOCK DATA 7");
        al.add("MOCK DATA 8");
        al.add("MOCK DATA 9");
        al.add("MOCK DATA 10");

        al.insert(0, "MOCK DATA 1 REPLACEMENT");

        //Then
        assertEquals("MOCK DATA 1 REPLACEMENT", al.getAt(0));
    }

    @Test
    void insertMethodWorksWhenInsertsAtTheEnd() {
        //Given
        ArrayList<String> al = new ArrayList<>();

        //When
        al.add("MOCK DATA 1");
        al.add("MOCK DATA 2");
        al.add("MOCK DATA 3");
        al.add("MOCK DATA 4");
        al.add("MOCK DATA 5");
        al.add("MOCK DATA 6");
        al.add("MOCK DATA 7");
        al.add("MOCK DATA 8");
        al.add("MOCK DATA 9");
        al.add("MOCK DATA 10");

        al.insert(9, "MOCK DATA 10 REPLACEMENT");

        //Then
        assertEquals("MOCK DATA 10 REPLACEMENT", al.getAt(9));
    }

    @Test
    void insertMethodWorksAndUpdatesSize() {
        //Given
        ArrayList<String> al = new ArrayList<>();

        //When
        al.add("MOCK DATA 1");
        al.add("MOCK DATA 2");

        //Then
        assertEquals(2, al.size());
    }

    @Test
    void secondConstructorSetsArraySizeToCustom() {
        //Given
        ArrayList<String> al = new ArrayList<>(5);

        //When
        int length = al.capacity();

        //Then
        assertEquals(5, length);
    }

    @Test
    void getAtWorksWhenIndexIsInsideBounds() {
        //Given
        ArrayList<String> al = new ArrayList<>();

        //When
        al.add("MOCK DATA 1");
        String result = al.getAt(0);

        //Then
        assertEquals("MOCK DATA 1", result);
    }

    @Test
    void getAtFailsWhenIndexIsOutOfBounds() {
        //Given
        ArrayList<String> al = new ArrayList<>();

        //When
        al.add("MOCK DATA 1");

        //Then
        assertThrows(IndexOutOfBoundsException.class, () -> al.getAt(10));
    }

    @Test
    void setAtWorksWhenIndexIsInsideOfBounds() {
        //Given
        ArrayList<String> al = new ArrayList<>();

        //When
        al.add("MOCK DATA 1");
        al.add("MOCK DATA 2");
        al.setAt(1,"MOCK DATA 2 REPLACEMENT");

        //Then
        assertEquals("MOCK DATA 2 REPLACEMENT", al.getAt(1));
    }

    @Test
    void setAtFailsWhenIndexIsOutOfBounds() {
        //Given
        ArrayList<String> al = new ArrayList<>();

        //When
        al.add("MOCK DATA 1");

        //Then
        assertThrows(IndexOutOfBoundsException.class, () -> al.setAt(10, ""));
    }

    @Test
    void removeSuccessWhenIndexIsInsideOfBounds() {
        //Given
        ArrayList<String> al = new ArrayList<>();

        //When
        al.add("MOCK DATA 1");
        al.add("MOCK DATA 2");

        al.remove(1);
        int size = al.size();

        //Then
        assertEquals(1, size);
    }

    @Test
    void removeFailsWhenIndexIsOutOfBounds() {
        //Given
        ArrayList<String> al = new ArrayList<>();

        //When
        al.add("MOCK DATA 1");
        al.add("MOCK DATA 2");

        //Then
        assertThrows(IndexOutOfBoundsException.class, () -> al.remove(10));
    }

    @Test
    void removeAllSuccess() {
        //Given
        ArrayList<String> al = new ArrayList<>();

        //When
        al.add("MOCK DATA 1");
        al.add("MOCK DATA 2");

        al.removeAll();

        //Then
        assertEquals(0, al.size());
    }

    @Test
    void toStringSuccess() {
        //Given
        ArrayList<String> al = new ArrayList<>();

        //When
        al.add("MOCK DATA 1");

        String result = al.toString();

        //Then
        assertEquals("ArrayList{" + "size=" + al.size() +", dataList=" + Arrays.toString(al.getDataArray()) +'}', result);
    }

    @Test
    void iteratorWorks(){
        //Given
        ArrayList<String> al = new ArrayList<>();

        //When
        al.add("MOCK DATA 1");
        al.add("MOCK DATA 2");
        al.add("MOCK DATA 3");
        al.add("MOCK DATA 4");
        al.add("MOCK DATA 5");
        al.add("MOCK DATA 6");
        al.add("MOCK DATA 7");
        al.add("MOCK DATA 8");
        al.add("MOCK DATA 9");
        al.add("MOCK DATA 10");

        //Then
        Iterator<String> it = al.iterator();
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
        ArrayList<String> al = new ArrayList<>();

        //When
        al.add("MOCK DATA 10");
        al.add("MOCK DATA 9");
        al.add("MOCK DATA 8");
        al.add("MOCK DATA 7");
        al.add("MOCK DATA 6");
        al.add("MOCK DATA 5");
        al.add("MOCK DATA 4");
        al.add("MOCK DATA 3");
        al.add("MOCK DATA 2");
        al.add("MOCK DATA 1");

        //Then
        ReversedIterator<String> it = al.reverseIterator();
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
        ArrayList<String> al = new ArrayList<>();

        //When
        al.add("MOCK DATA 1");
        al.add("MOCK DATA 2");
        al.add("MOCK DATA 3");
        al.add("MOCK DATA 4");
        al.add("MOCK DATA 5");

        //Then
        Iterator<String> it = al.iterator();
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
        ArrayList<String> al = new ArrayList<>();

        //When
        al.add("MOCK DATA 5");
        al.add("MOCK DATA 4");
        al.add("MOCK DATA 3");
        al.add("MOCK DATA 2");
        al.add("MOCK DATA 1");

        //Then
        ReversedIterator<String> it = al.reverseIterator();
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
    void removeMethodReassignsRemainingDataOnArray() {
        // Given
        ArrayList<String> al = new ArrayList<>();
        al.add("MOCK DATA 1");
        al.add("MOCK DATA 2");
        al.add("MOCK DATA 3");
        al.add("MOCK DATA 4");

        // When
        al.remove(1);

        // Then
        assertEquals("MOCK DATA 1", al.getAt(0));
        assertEquals("MOCK DATA 3", al.getAt(1));
        assertEquals("MOCK DATA 4", al.getAt(2));
        assertEquals(3, al.size());

    }

}
