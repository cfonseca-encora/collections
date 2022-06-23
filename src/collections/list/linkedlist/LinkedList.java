package collections.list.linkedlist;

import collections.list.Iterator;
import collections.list.List;
import collections.list.ReversedIterator;

public class LinkedList implements List {

    private Node head;
    private Node tail;
    private int size;

    @Override
    public void add(String data) {
        Node currentNode = new Node(data);
        if(head == null) {
            head = currentNode;
            tail = currentNode;

        } else {
            tail.next = currentNode;
            currentNode.previous = tail;
            tail = currentNode;
            tail.next = null;
        }
        size++;
    }

    @Override
    public void insert(int index, String data) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index "+index+" is out of the list's boundaries");

        Node current = searchNode(index);
        Node newNode = new Node(data);

        if (index == size - 1)
            tail = newNode;

        if (index == 0)
            head = newNode;
        else
            current.previous.next = newNode;


        newNode.next = current;
        newNode.previous = current.previous;
        current.previous = newNode;
        size++;
    }

    @Override
    public String getAt(int index) {
        return this.searchNode(index).data;
    }

    @Override
    public void setAt(int index, String data) {
        this.searchNode(index).data = data;
    }

    @Override
    public void remove(int index) {
        Node toRemove = searchNode(index);

        if (index == 0)
            head = toRemove.next;

        if (index == size - 1)
            tail = toRemove.previous;

        if (toRemove.previous != null)
            toRemove.previous.next = toRemove.next;

        if (toRemove.next != null)
            toRemove.next.previous = toRemove.previous;

        size--;
    }

    @Override
    public void removeAll() {
        if(head != null)
            head = null;

        if(tail != null)
            tail = null;

        size = 0;
    }

    @Override
    public Iterator iterator() {
        return new LinkedListIterator(head);
    }

    public ReversedIterator reverseIterator() {
        return new ReversedLinkedListIterator(tail);
    }

    @Override
    public int size() {
        return size;
    }

    Node searchNode(int index) {
        if(index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index " + index + " is out of the list's boundaries");

        int currentIndex = 0;
        Node current = head;

        while(current != null && currentIndex < index) {
            currentIndex++;
            current = current.next;
        }

        return current;
    }

    @Override
    public String toString() {

        String result = "LinkedList{";

        Node current = head;
        int index = 0;

        while(current != null && index < size) {
            result += "node["+ (index) +"]: (contentClass=" + current.data.getClass() + ") " + current.data + ", ";

            current = current.next;
            index++;
        }

        result += "}";

        return result;
    }
}
