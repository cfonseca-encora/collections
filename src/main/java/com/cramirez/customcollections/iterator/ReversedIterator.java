package com.cramirez.customcollections;

public interface ReversedIterator<T> {
    boolean hasPrevious();
    T previous();
}
