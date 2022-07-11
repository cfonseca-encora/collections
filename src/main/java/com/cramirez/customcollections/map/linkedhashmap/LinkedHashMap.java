package com.cramirez.customcollections.map.linkedhashmap;

import com.cramirez.customcollections.iterator.Iterator;
import com.cramirez.customcollections.iterator.ReversedIterator;
import com.cramirez.customcollections.list.linkedlist.LinkedList;
import com.cramirez.customcollections.map.hashmap.Entry;
import com.cramirez.customcollections.map.hashmap.HashMap;

public class LinkedHashMap<K, V> extends HashMap<K, V> {

    private final LinkedList<Entry<K, V>> sortedData;

    public LinkedHashMap() {
        super();
        this.sortedData = new LinkedList<>();
    }

    @Override
    public V put(K key, V value) {
        V result = super.put(key, value);
        if (result != null)
            sortedData.add(new Entry<>(key, value));

        return result;
    }

    @Override
    public V get(K key) {
        return super.get(key);
    }

    public V get(int index) {
        return sortedData.getAt(index).getValue();
    }

    @Override
    public V remove(K key) {
        V result = super.remove(key);

        if(result == null)
            return null;

        int index = 0;
        Iterator<Entry<K, V>> it = sortedData.iterator();
        while(it.hasNext()) {
            Entry<K, V> current = it.next();
            if(current.equals(new Entry<>(key))) {
                sortedData.remove(index);
                break;
            }

            index++;
        }

        return result;
    }

    @Override
    public int size() {
        return sortedData.size();
    }

    @Override
    public boolean isEmpty() {
        return sortedData.isEmpty();
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return sortedData.iterator();
    }

    @Override
    public ReversedIterator<Entry<K, V>> reversedIterator() {
        return sortedData.reversedIterator();
    }
}
