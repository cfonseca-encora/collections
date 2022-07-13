package com.cramirez.customcollections.map.treemap;

import com.cramirez.customcollections.iterator.Iterator;
import com.cramirez.customcollections.iterator.ReversedIterator;
import com.cramirez.customcollections.map.Map;
import com.cramirez.customcollections.map.Entry;
import com.cramirez.customcollections.stack.Stack;
import org.jetbrains.annotations.NotNull;

import java.util.NoSuchElementException;
import java.util.Objects;

public class TreeMap <K extends Comparable<K>, V> implements Map<K, V> {

    private MapNode<K, V> root;

    private int size;

    static class MapNode<K, V> {
        private Entry<K, V> entry;
        private int balance;
        private int height;
        private MapNode<K, V> left;
        private MapNode<K, V> right;
        private MapNode<K, V> parent;

        MapNode(Entry<K, V> data, MapNode<K, V> parent) {
            this.entry = data;
            this.parent = parent;
        }
    }
    @Override
    public V put(K key, V value) {
        Entry<K, V> newObject = new Entry<>(key, value);

        if (root == null) {
            root = new MapNode<>(newObject, null);
            size++;
            return value;
        }


        MapNode<K, V> current = root;

        while (!current.entry.getKey().equals(key)) {
            MapNode<K, V> parent = current;

            boolean goLeft = current.entry.getKey().compareTo(key) > 0;
            current = goLeft ? current.left : current.right;

            if (current == null) {
                if (goLeft) {
                    parent.left = new MapNode<>(newObject, parent);
                } else {
                    parent.right = new MapNode<>(newObject, parent);
                }

                setNewBalance(parent);
                size++;

                return value;
            }
        }

        current.entry = newObject;
        return value;
    }

    @Override
    public V get(K key) {
        return containsKey(key, root);
    }

    @Override
    public V remove(K key) {
        MapNode<K, V> current = root;

        while (current != null) {
            MapNode<K, V> mapNode = current;

            int comparative = key.compareTo(mapNode.entry.getKey());
            current = (comparative >= 0) ? mapNode.right : mapNode.left;

            if (comparative == 0) {
                V object = mapNode.entry.getValue();
                delete(mapNode);
                size--;
                return object;
            }
        }

        return null;
    }
    
    private void delete(MapNode<K, V> mapNode) {
        if (mapNode.left == null && mapNode.right == null) {
            if (mapNode.parent == null) {
                root = null;
            } else {
                MapNode<K, V> parent = mapNode.parent;

                if (parent.left != null && parent.left.entry.getKey().equals(mapNode.entry.getKey())) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }

                setNewBalance(parent);
            }

            return;
        }

        MapNode<K, V> child;
        if (mapNode.left != null) {
            child = mapNode.left;

            while (child.right != null) {
                child = child.right;
            }

        } else {
            child = mapNode.right;

            while (child.left != null){
                child = child.left;
            }

        }
        mapNode.entry = child.entry;
        delete(child);
    }

    @Override
    public boolean containsKey(K key) {
        return containsKey(key, root) != null;
    }

    @Override
    public boolean containsValue(V value) {
        Iterator<Entry<K, V>> it = iterator();

        while(it.hasNext()){
            Entry<K, V> entry = it.next();
            if(Objects.equals(entry.getValue(), value))
                return true;
        }

        return false;
    }

    private V containsKey(K key, MapNode<K, V> current) {
        if (current == null) {
            return null;
        }

        if (key.equals(current.entry.getKey())) {
            return current.entry.getValue();
        }

        if (key.compareTo(current.entry.getKey()) < 0) {
            return containsKey(key, current.left);
        } else {
            return containsKey(key, current.right);
        }
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new Iterator<Entry<K, V>>() {

            MapNode<K, V> current = root;
            final Stack<MapNode<K, V>> stack;

            {
                stack = new Stack<>();

                while (current != null) {
                    stack.push(current);
                    current = current.left;
                }
            }

            @Override
            public boolean hasNext() {
                return !stack.isEmpty();
            }

            @Override
            public Entry<K, V> next() {
                MapNode<K, V> node = stack.pop();
                Entry<K, V> result = node.entry;

                if (node.right != null) {
                    node = node.right;

                    while (node != null) {
                        stack.push(node);
                        node = node.left;
                    }
                }

                return result;
            }
        };
    }

    @Override
    public ReversedIterator<Entry<K, V>> reversedIterator() {
        return new ReversedIterator<Entry<K, V>>() {

            MapNode<K, V> current = root;
            final Stack<MapNode<K, V>> stack;

            {
                stack = new Stack<>();

                while (current != null) {
                    stack.push(current);
                    current = current.right;
                }
            }

            @Override
            public boolean hasNext() {
                return !stack.isEmpty();
            }

            @Override
            public Entry<K, V> next() {
                MapNode<K, V> node = stack.pop();
                Entry<K, V> result = node.entry;

                if (node.left != null) {
                    node = node.left;

                    while (node != null) {
                        stack.push(node);
                        node = node.right;
                    }
                }

                return result;
            }
        };
    }

    private void setNewBalance(MapNode<K, V> mapNode) {
        setBalance(mapNode);

        if (mapNode.balance == -2) {
            if (getHeight(mapNode.left.left) >= getHeight(mapNode.left.right)) {
                mapNode = rotateRight(mapNode);
            } else {
                mapNode = rotateLeftThenRight(mapNode);
            }
        } else if (mapNode.balance == 2) {
            if (getHeight(mapNode.right.right) >= getHeight(mapNode.right.left)) {
                mapNode = rotateLeft(mapNode);
            } else {
                mapNode = rotateRightThenLeft(mapNode);
            }
        }

        if (mapNode.parent != null) {
            setNewBalance(mapNode.parent);
        } else {
            root = mapNode;
        }
    }

    private MapNode<K, V> rotateLeft(MapNode<K, V> a) {
        MapNode<K, V> b = a.right;
        b.parent = a.parent;

        a.right = b.left;

        if (a.right != null) {
            a.right.parent = a;
        }

        b.left = a;
        a.parent = b;

        if (b.parent != null) {
            if (b.parent.right != null && b.parent.right.entry.getKey().equals((a.entry.getKey()))) {
                b.parent.right = b;
            } else {
                b.parent.left = b;
            }
        }

        setBalance(a, b);

        return b;
    }

    private MapNode<K, V> rotateRight(MapNode<K, V> a) {
        MapNode<K, V> b = a.left;
        b.parent = a.parent;

        a.left = b.right;

        if (a.left != null) {
            a.left.parent = a;
        }

        b.right = a;
        a.parent = b;

        if (b.parent != null) {
            if (b.parent.right.entry.getKey().equals(a.entry.getKey())) {
                b.parent.right = b;
            } else {
                b.parent.left = b;
            }
        }

        setBalance(a, b);

        return b;
    }

    private MapNode<K, V> rotateLeftThenRight(MapNode<K, V> mapNode) {
        mapNode.left = rotateLeft(mapNode.left);
        return rotateRight(mapNode);
    }

    private MapNode<K, V> rotateRightThenLeft(MapNode<K, V> mapNode) {
        mapNode.right = rotateRight(mapNode.right);
        return rotateLeft(mapNode);
    }

    private int getHeight(MapNode<K, V> mapNode) {
        if (mapNode == null) {
            return -1;
        }

        return mapNode.height;
    }

    @SafeVarargs
    private final void setBalance(@NotNull MapNode<K, V>... treeNodes) {
        for (MapNode<K, V> n : treeNodes) {
            setNewHeight(n);
            n.balance = getHeight(n.right) - getHeight(n.left);
        }
    }
    public void setNewHeight(MapNode<K, V> mapNode) {
        if (mapNode != null) {
            mapNode.height = 1 + Math.max(getHeight(mapNode.left), getHeight(mapNode.right));
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
