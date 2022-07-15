package com.cramirez.customcollections.stack;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class StackTest {
    @Test
    void pushAndPopMethodWorksInOrder() { // this covers lines 102 to 111
        // Given
        Stack<String> stack = new Stack<>();
        for(int i = 0; i < 10; i++) {
            assertNotNull(stack.push("MOCK DATA "+ i));
        }


        // When
        for(int i = 9; i >= 0; i--) {
            assertEquals("MOCK DATA " + i, stack.pop());
        }

        //Then
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }

    @Test
    void stackGeneratesANewObjectWithDataConstructor() { // this covers lines 102 to 111
        // Given
        Stack<String> stack = new Stack<>("MOCK DATA");


        // When /Then
        assertFalse(stack.isEmpty());
        assertEquals(1, stack.size());
        assertEquals("MOCK DATA", stack.top());

    }

    @Test
    void stackWillFailWhenAskingForPop() { // this covers lines 102 to 111
        // Given
        Stack<String> stack = new Stack<>();


        // When /Then
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
        assertNull(stack.top());
        assertThrows(NoSuchElementException.class, stack::pop);
    }
}
