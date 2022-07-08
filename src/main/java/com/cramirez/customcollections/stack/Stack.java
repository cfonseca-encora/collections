package com.cramirez.customcollections.stack;

import java.util.NoSuchElementException;

public class Stack<E> {
    private Item<E> top;

    private int size;

    public Stack() {
    }

    public Stack(E top) {
        push(top);
    }

    public E push(E newTop) {
        Item<E> currentTop = top;
        top = new Item<>(newTop);
        top.below = currentTop;
        size++;
        return top.data;
    }

    public E pop() {
        if(size == 0)
            throw new NoSuchElementException("There is no item to pop out of this stack, it's empty");

        Item<E> lastTop = top;
        top = top.below;
        size--;
        return lastTop.data;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E top() {
        return (top != null) ? top.data : null;
    }


    private static class Item<E> {
        E data;


        Item<E> below;

        private Item(E data) {
            this.data = data;
        }
    }

}
