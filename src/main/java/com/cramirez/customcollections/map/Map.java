package com.cramirez.customcollections.map;

import com.cramirez.customcollections.iterator.Iterator;
import com.cramirez.customcollections.iterator.ReversedIterator;

public interface Map<K, V> {
    V put(K key, V value);
    V get(K key);
    V remove(K key);
    int size();
    boolean isEmpty();
    boolean containsKey(K key);
    boolean containsValue(V value);

    Iterator<Entry<K, V>> iterator();
    ReversedIterator<Entry<K, V>> reversedIterator();

}
