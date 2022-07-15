package com.cramirez.customcollections.list.linkedlist;

class Node<T> {
    Node<T> next;
    Node<T> previous;
    T data;

    public Node(T data) {
        this.data = data;
    }
}
