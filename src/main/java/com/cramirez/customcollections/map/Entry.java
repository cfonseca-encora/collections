package com.cramirez.customcollections.map;

public class Entry<K, V> {
    private final K key;
    private final V value;

    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public Entry(K key) {
        this.key = key;
        this.value = null;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        final int FIRST_PRIME = 541;     // 100th prime number
        final int SECOND_PRIME = 1223;    // 200th prime number

        return FIRST_PRIME * SECOND_PRIME + ((key != null) ? key.hashCode() : 0);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;

        if(obj == null || getClass() != obj.getClass())
            return false;

        //noinspection unchecked
        Entry<K, V> instancedObj = (Entry<K, V>) obj;

        if(hashCode() == instancedObj.hashCode())
            return true;

        return key.equals(instancedObj.getKey());
    }

    @Override
    public String toString() {
        String[] keyStringSplit = key.getClass().getName().split("\\.");
        String keyString = keyStringSplit[keyStringSplit.length - 1];

        String[] valueStringSplit = (value != null) ? value.getClass().getName().split("\\.") : null;
        String valueString = (valueStringSplit != null) ? valueStringSplit[valueStringSplit.length - 1] : null;

        return "[Entry<" + keyString + ", " + valueString + ">" +
                "{'" +
                key +
                "': '" +
                value +
                "'}]";
    }
}
