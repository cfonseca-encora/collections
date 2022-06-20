package collections.list.linkedlist;

import collections.list.Iterator;

public class LinkedListIterator implements Iterator {
    Node node;

    public LinkedListIterator(Node node) {
        this.node = node;
    }

    @Override
    public boolean hasNext() {
        return node != null;
    }

    @Override
    public String next() {
        Node value = node;
        node = node.next;
        return value.data;
    }
}
