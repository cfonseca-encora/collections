package com.cramirez.customcollections.set;

import com.cramirez.customcollections.iterator.Iterator;
import com.cramirez.customcollections.iterator.ReversedIterator;

public interface Set<E> {
    boolean add(E object);
    E remove(E object);
    boolean contains(E object);
    Iterator<E> iterator();
    ReversedIterator<E> reversedIterator();
    int size();
}
