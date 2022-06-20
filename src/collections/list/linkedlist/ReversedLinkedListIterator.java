package collections.list.linkedlist;

import collections.list.Iterator;
import collections.list.ReversedIterator;

public class ReversedLinkedListIterator  implements ReversedIterator {

    Node node;

    public ReversedLinkedListIterator(Node node) {
        this.node = node;
    }

    @Override
    public boolean hasPrevious() {
        return node != null;
    }

    @Override
    public String previous() {
        Node value = node;
        node = node.previous;
        return value.data;
    }
}
