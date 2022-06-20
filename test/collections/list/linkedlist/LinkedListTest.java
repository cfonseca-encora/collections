package collections.list.linkedlist;

import collections.list.Iterator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedListTest {

    @Test
    void addMethodAddsNewNode() {
        //Given
        LinkedList l = new LinkedList();

        //When
        l.add("MOCK DATA");

        //Then
        assertEquals(l.getAt(0), "MOCK DATA");
    }

    @Test
    void addMethodAddsNewNodeAndUpdatesSize() {
        //Given
        LinkedList l = new LinkedList();

        //When
        l.add("MOCK DATA NODE 1");
        l.add("MOCK DATA NODE 2");

        //Then
        assertEquals(l.size(), 2);
    }

    @Test
    void insertMethodAddsNewNodeAtAssignedPositionAndUpdatesSize() {
        //Given
        LinkedList l = new LinkedList();
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
        LinkedList l = new LinkedList();
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
        LinkedList l = new LinkedList();
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
        LinkedList l = new LinkedList();

        //When
        l.add("MOCK DATA NODE 1");
        l.add("MOCK DATA NODE 2");

        //Then
        assertThrows(IndexOutOfBoundsException.class, () -> {
            l.insert(4, "MOCK DATA NODE 3");
        });
    }

    @Test
    void getAtMethodReturnsCorrectIndex() {
        //Given
        LinkedList l = new LinkedList();

        //When
        l.add("MOCK DATA NODE 1");
        l.add("MOCK DATA NODE 2");

        //Then
        assertEquals("MOCK DATA NODE 2", l.getAt(1));
    }

    @Test
    void getAtCrashesGivenAnIndexOutOfBounds() {
        //Given
        LinkedList l = new LinkedList();

        //When
        l.add("MOCK DATA NODE 1");
        l.add("MOCK DATA NODE 2");

        //Then
        assertThrows(IndexOutOfBoundsException.class, () -> {
            l.getAt(3);
        });
    }

    @Test
    void setAtReturnsAndSetInTheCorrectIndex() {
        //Given
        LinkedList l = new LinkedList();
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
        LinkedList l = new LinkedList();
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
        LinkedList l = new LinkedList();

        //When
        l.add("MOCK DATA NODE 1");
        l.add("MOCK DATA NODE 2");


        //Then
        assertThrows(NullPointerException.class, () -> {
            l.setAt(2, "MODIFYING MOCK DATA NODE 2");
        });
    }

    @Test
    void removeMethodDeletesSuccesfully() {
        //Given
        LinkedList l = new LinkedList();
        l.add("MOCK DATA NODE 1");
        l.add("MOCK DATA NODE 2");
        l.add("MOCK DATA NODE 3");
        l.add("MOCK DATA NODE 4");

        //When
        l.remove(1);

        //Then
        assertEquals("MOCK DATA NODE 3", l.getAt(1));
    }

    @Test
    void removeMethodDeletesSuccesfullyAtTheBeginning() {
        //Given
        LinkedList l = new LinkedList();
        l.add("MOCK DATA NODE 1");
        l.add("MOCK DATA NODE 2");
        l.add("MOCK DATA NODE 3");
        l.add("MOCK DATA NODE 4");

        //When
        l.remove(0);

        //Then
        assertEquals("MOCK DATA NODE 2", l.getAt(0));
    }

    @Test
    void removeMethodDeletesSuccesfullyAtTheEnd() {
        //Given
        LinkedList l = new LinkedList();
        l.add("MOCK DATA NODE 1");
        l.add("MOCK DATA NODE 2");
        l.add("MOCK DATA NODE 3");
        l.add("MOCK DATA NODE 4");

        //When
        l.remove(3);

        //Then
        assertThrows(NullPointerException.class, () -> {
            l.getAt(3);
        });
    }

    @Test
    void removeAllExecutesCorrectly() {
        //Given
        LinkedList l = new LinkedList();
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
    void iteratorMethodReturnsLinkedListIterator() {
        //Given
        LinkedList l = new LinkedList();
        l.add("MOCK DATA NODE 1");

        //When
        Iterator it = l.iterator();

        //Then
        assertEquals(LinkedListIterator.class, it.getClass());
    }

    @Test
    void iteratorMethodReturnsLinkedListIteratorWithCurrentNodeAsHead() {
        //Given
        LinkedList l = new LinkedList();
        l.add("MOCK DATA NODE 1");

        //When
        Iterator it = l.iterator();

        //Then
        assertEquals(l.getAt(0), it.next());
    }

    @Test
    void sizeUpdatesWhenUsingMethodAdd() {
        //Given
        LinkedList l = new LinkedList();
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
        LinkedList l = new LinkedList();
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
        LinkedList l = new LinkedList();
        l.add("MOCK DATA NODE 1");
        l.add("MOCK DATA NODE 2");
        int size = l.size();

        //When
        l.remove(1);

        //Then
        assertEquals(size - 1, l.size());
    }

    @Test
    void sizeUpdatesWhenUsingMethodRemoveAll() {
        //Given
        LinkedList l = new LinkedList();
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
        LinkedList l = new LinkedList();

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
        LinkedList l = new LinkedList();

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
        LinkedList l = new LinkedList();

        //When
        l.add("MOCK DATA NODE 1");
        l.add("MOCK DATA NODE 2");
        l.add("MOCK DATA NODE 3");

        //Then
        assertEquals(String.class, l.toString().getClass());
    }

    @Test
    void iteratorReturnsAnIteratorObject() {
        //Given
        LinkedList l = new LinkedList();

        //When
        Class clazz = l.iterator().getClass();

        //Then
        assertTrue(clazz == new LinkedListIterator(Mockito.mock(Node.class)).getClass());
    }

    @Test
    void reverseIteratorReturnsAnIteratorObject() {
        //Given
        LinkedList l = new LinkedList();

        //When
        Class clazz = l.reverseIterator().getClass();

        //Then
        assertEquals(new ReversedLinkedListIterator(Mockito.mock(Node.class)).getClass(), clazz);
    }
}
