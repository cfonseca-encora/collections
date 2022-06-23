package collections.list.linkedlist;

import collections.list.linkedlist.Node;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;


public class LinkedListIteratorTest {

    @Test
    void iteratorConstructorReturnsIteratorWhenReceivingNode(){
        LinkedListIterator li = new LinkedListIterator(Mockito.mock(Node.class));
        assertNotEquals(li, null);
    }

    @Test
    void hasNextMethodReturnsTrueWhenNodeIsNotNull(){
        LinkedListIterator li = new LinkedListIterator(Mockito.mock(Node.class));
        assertTrue(li.hasNext());
    }

    @Test
    void hasNextMethodReturnsFalseWhenNodeIsNull(){
        LinkedListIterator li = new LinkedListIterator(null);
        assertFalse(li.hasNext());
    }

    @Test
    void nextMethodReturnsCurrentNode(){
        Node node = new Node("MOCK NODE 1");
        LinkedListIterator li = new LinkedListIterator(node);
        assertEquals(node.data, li.next());
    }
}
