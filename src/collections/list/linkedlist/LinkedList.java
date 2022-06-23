package collections.list.linkedlist;

import collections.list.Iterator;
import collections.list.List;
import collections.list.ReversedIterator;

import java.util.NoSuchElementException;

public class LinkedList<T> implements List<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    @Override
    public void add(T data) {
        Node<T> currentNode = new Node<T>(data);
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
    public void insert(int index, T data) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index " + index + " is out of the list's boundaries");

        Node<T> current = searchNode(index);
        Node<T> newNode = new Node(data);

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
    public T getAt(int index) {
        return (T) this.searchNode(index).data;
    }

    @Override
    public void setAt(int index, T data) {
        this.searchNode(index).data = data;
    }

    @Override
    public void remove(int index) {
        Node<T> toRemove = searchNode(index);

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
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext())
                    throw new NoSuchElementException("There is no such element next to this position");

                Node<T> value = node;
                node = node.next;
                return value.data;
            }
        };
    }

    public ReversedIterator<T> reverseIterator() {
        return new ReversedIterator<T>() {
            Node<T> node = tail;

            @Override
            public boolean hasPrevious() {
                return node != null;
            }

            @Override
            public T previous() {
                if (!hasPrevious())
                    throw new NoSuchElementException("There is no such element previous to this position");
                Node<T> value = node;
                node = node.previous;
                return value.data;
            }
        };
    }

    @Override
    public int size() {
        return size;
    }

    Node<T> searchNode(int index) {
        if(index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index " + index + " is out of the list's boundaries");

        int currentIndex = 0;
        Node<T> current = head;

        while(current != null && currentIndex < index) {
            currentIndex++;
            current = current.next;
        }

        return current;
    }

    @Override
    public String toString() {

        StringBuilder result = new StringBuilder("LinkedList{");

        Node<T> current = head;
        int index = 0;

        while(current != null && index < size) {
            result.append("node[").append(index).append("]: (contentClass=").append(current.data.getClass()).append(") ").append(current.data).append(", ");

            current = current.next;
            index++;
        }

        result.append("}");

        return result.toString();
    }
}
