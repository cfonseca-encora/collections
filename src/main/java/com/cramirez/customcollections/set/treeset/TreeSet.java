package com.cramirez.customcollections.set.treeset;

import com.cramirez.customcollections.iterator.Iterator;
import com.cramirez.customcollections.iterator.ReversedIterator;
import com.cramirez.customcollections.set.Set;
import com.cramirez.customcollections.stack.Stack;
import org.jetbrains.annotations.NotNull;

//Implementation AVL Tree
public class TreeSet<E extends Comparable <E>> implements Set<E> {
    private TreeNode<E> root;

    private int size;

    static class TreeNode<E> {
        private E data;
        private int balance;
        private int height;
        private TreeNode<E> left;
        private TreeNode<E> right;
        private TreeNode<E> parent;

        TreeNode(E data, TreeNode<E> parent) {
            this.data = data;
            this.parent = parent;
        }
    }

    @Override
    public boolean add(E data) {
        if (root == null) {
            root = new TreeNode<>(data, null);
            size++;
            return true;
        }

        TreeNode<E> current = root;

        while (!current.data.equals(data)) {
            TreeNode<E> parent = current;

            boolean goLeft = current.data.compareTo(data) > 0;
            current = goLeft ? current.left : current.right;

            if (current == null) {
                if (goLeft) {
                    parent.left = new TreeNode<>(data, parent);
                } else {
                    parent.right = new TreeNode<>(data, parent);
                }

                setNewBalance(parent);
                size++;

                return true;
            }
        }

        return false;
    }


    @Override
    public E remove(E toDelete) {

        TreeNode<E> current = root;

        while (current != null) {
            TreeNode<E> treeNode = current;

            int comparative = toDelete.compareTo(treeNode.data);
            current = (comparative >= 0) ? treeNode.right : treeNode.left;

            if (comparative == 0) {
                E object = treeNode.data;
                delete(treeNode);
                size--;
                return object;
            }
        }

        return null;
    }

    private void delete(TreeNode<E> treeNode) {
        if (treeNode.left == null && treeNode.right == null) {
            if (treeNode.parent == null) {
                root = null;
            } else {
                TreeNode<E> parent = treeNode.parent;

                if (parent.left != null && parent.left.equals(treeNode)) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }

                setNewBalance(parent);
            }

            return;
        }

        TreeNode<E> child;
        if (treeNode.left != null) {
            child = treeNode.left;

            while (child.right != null) {
                child = child.right;
            }

        } else {
            child = treeNode.right;

            while (child.left != null){
                child = child.left;
            }

        }
        treeNode.data = child.data;
        delete(child);
    }

    public boolean contains(E data) {
        return contains(data, root);
    }

    private boolean contains(E data, TreeNode<E> current) {
        if (current == null) {
            return false;
        }

        if (data.equals(current.data)) {
            return true;
        }

        if (data.compareTo(current.data) < 0) {
            return contains(data, current.left);
        } else {
            return contains(data, current.right);
        }
    }

    private void setNewBalance(TreeNode<E> treeNode) {
        setBalance(treeNode);

        if (treeNode.balance == -2) {
            if (getHeight(treeNode.left.left) >= getHeight(treeNode.left.right)) {
                treeNode = rotateRight(treeNode);
            } else {
                treeNode = rotateLeftThenRight(treeNode);
            }
        } else if (treeNode.balance == 2) {
            if (getHeight(treeNode.right.right) >= getHeight(treeNode.right.left)) {
                treeNode = rotateLeft(treeNode);
            } else {
                treeNode = rotateRightThenLeft(treeNode);
            }
        }

        if (treeNode.parent != null) {
            setNewBalance(treeNode.parent);
        } else {
            root = treeNode;
        }
    }

    private TreeNode<E> rotateLeft(TreeNode<E> a) {
        TreeNode<E> b = a.right;
        b.parent = a.parent;

        a.right = b.left;

        if (a.right != null) {
            a.right.parent = a;
        }

        b.left = a;
        a.parent = b;

        if (b.parent != null) {
            if (b.parent.right != null && b.parent.right.equals(a)) {
                b.parent.right = b;
            } else {
                b.parent.left = b;
            }
        }

        setBalance(a, b);

        return b;
    }

    private TreeNode<E> rotateRight(TreeNode<E> a) {
        TreeNode<E> b = a.left;
        b.parent = a.parent;

        a.left = b.right;

        if (a.left != null) {
            a.left.parent = a;
        }

        b.right = a;
        a.parent = b;

        if (b.parent != null) {
            if (b.parent.right.equals(a)) {
                b.parent.right = b;
            } else {
                b.parent.left = b;
            }
        }

        setBalance(a, b);

        return b;
    }

    private TreeNode<E> rotateLeftThenRight(TreeNode<E> treeNode) {
        treeNode.left = rotateLeft(treeNode.left);
        return rotateRight(treeNode);
    }

    private TreeNode<E> rotateRightThenLeft(TreeNode<E> treeNode) {
        treeNode.right = rotateRight(treeNode.right);
        return rotateLeft(treeNode);
    }

    private int getHeight(TreeNode<E> treeNode) {
        if (treeNode == null) {
            return -1;
        }

        return treeNode.height;
    }

    @SafeVarargs
    private final void setBalance(@NotNull TreeNode<E>... treeNodes) {
        for (TreeNode<E> n : treeNodes) {
            setNewHeight(n);
            n.balance = getHeight(n.right) - getHeight(n.left);
        }
    }
    public void setNewHeight(TreeNode<E> treeNode) {
        if (treeNode != null) {
            treeNode.height = 1 + Math.max(getHeight(treeNode.left), getHeight(treeNode.right));
        }
    }

    public Iterator<E> iterator() {
        return new Iterator<E>() {

            TreeNode<E> current = root;
            final Stack<TreeNode<E>> stack;

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
            public E next() {
                TreeNode<E> node = stack.pop();
                E result = node.data;

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
    public ReversedIterator<E> reversedIterator() {
        return new ReversedIterator<E>() {

            TreeNode<E> current = root;
            final Stack<TreeNode<E>> stack;

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
            public E next() {
                TreeNode<E> node = stack.pop();
                E result = node.data;

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

    @Override
    public int size() {
        return size;
    }
}
