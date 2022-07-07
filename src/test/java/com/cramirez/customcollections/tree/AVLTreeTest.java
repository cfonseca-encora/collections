package com.cramirez.customcollections.tree;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AVLTreeTest {

    @Test
    void insertMethod() {
        // Given
        AVLTree<Integer> tree = new AVLTree<>();

        // When
        for(int i = 10; i > 0; i--) {
            tree.insert(i);
        }

        //Then
        for(int i = 1; i <= 10; i++) {
            assertTrue(tree.contains(i));
        }
    }



    @Test
    void insertMethodWorksWhenDataInsertedIsNotSequential() {
        // Given
        AVLTree<Integer> tree = new AVLTree<>();

        // When
        tree.insert(2);
        tree.insert(5);
        tree.insert(5);
        tree.insert(4);
        tree.insert(3);
        tree.insert(9);
        tree.insert(1);
        tree.insert(8);
        tree.insert(6);
        tree.insert(7);
        tree.insert(0);

        //Then
        for(int i = 0; i < 10; i++) {
            assertTrue(tree.contains(i));
        }
    }

    @Test
    void deleteMethodWorksWhenDeletingRoot() {
        // Given
        AVLTree<String> tree = new AVLTree<>();
        for(int i = 0; i < 10; i++) {
            assertTrue(tree.insert("MOCK DATA "+ i));
        }

        for(int i = 20; i >= 10; i--) {
            assertTrue(tree.insert("MOCK DATA "+ i));
        }

        // When
        assertTrue(tree.delete("MOCK DATA 16"));

        //Then
        for(int i = 0; i <= 20; i++) {
            if(i == 16)
                continue;
            assertTrue(tree.contains("MOCK DATA " + i));
        }
    }

    @Test
    void deleteMethodWorksWhenDeletingLowestNumber() {
        // Given
        AVLTree<String> tree = new AVLTree<>();
        for(int i = 0; i < 100000; i++) {
            assertTrue(tree.insert("MOCK DATA "+ i));
        }

        // When
        assertTrue(tree.delete("MOCK DATA 0"));

        //Then
        for(int i = 1; i < 100000; i++) {
            assertTrue(tree.contains("MOCK DATA " + i));
        }
    }

    @Test
    void deleteMethodWorksWhenDeletingHighestNumber() {
        // Given
        AVLTree<String> tree = new AVLTree<>();
        for(int i = 0; i < 100000; i++) {
            assertTrue(tree.insert("MOCK DATA "+ i));
        }

        // When
        assertTrue(tree.delete("MOCK DATA 99999"));

        //Then
        for(int i = 0; i < 99999; i++) {
            assertTrue(tree.contains("MOCK DATA " + i));
        }
        assertFalse(tree.contains("MOCK DATA 99999"));
    }

    @Test
    void deleteMethodWorksWhenDeletingEveryNumber() { // this covers lines 102 to 111
        // Given
        AVLTree<String> tree = new AVLTree<>();
        for(int i = 0; i < 10; i++) {
            assertTrue(tree.insert("MOCK DATA "+ i));
        }


        // When
        for(int i = 0; i < 10; i++) {
            assertTrue(tree.delete("MOCK DATA " + i));
        }

        //Then
        for(int i = 0; i < 10; i++) {
            assertFalse(tree.contains("MOCK DATA " + i));
        }
    }
}
