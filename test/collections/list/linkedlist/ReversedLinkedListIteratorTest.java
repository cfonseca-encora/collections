package collections.list.linkedlist;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReversedLinkedListIteratorTest {


    @Test
    void reversedIteratorConstructorReturnsIteratorWhenReceivingNode(){
        ReversedLinkedListIterator rli = new ReversedLinkedListIterator(Mockito.mock(Node.class));
        assertNotEquals(rli, null);
    }

    @Test
    void hasPreviousMethodReturnsTrueWhenNodeIsNotNull(){
        ReversedLinkedListIterator rli = new ReversedLinkedListIterator(Mockito.mock(Node.class));
        assertTrue(rli.hasPrevious());
    }

    @Test
    void hasPreviousMethodReturnsFalseWhenNodeIsNull(){
        ReversedLinkedListIterator rli = new ReversedLinkedListIterator(null);
        assertFalse(rli.hasPrevious());
    }

    @Test
    void nextMethodReturnsCurrentNode(){
        Node node = new Node("MOCK NODE 1");
        ReversedLinkedListIterator rli = new ReversedLinkedListIterator(node);
        assertEquals(node.data, rli.previous());
    }
}
