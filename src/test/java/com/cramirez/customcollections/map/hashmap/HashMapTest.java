package com.cramirez.customcollections.map.hashmap;

import com.cramirez.customcollections.iterator.Iterator;
import com.cramirez.customcollections.iterator.ReversedIterator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HashMapTest {
    @Test
    void hashMapAdds() {
        // Given
        HashMap<Integer, String> hm = new HashMap<>();

        // When
        for (int i = 1; i < 25; i++) {
            hm.put(i, "MOCK DATA " + i);
        }

        // Then
        for (int i = 1; i < 25; i++) {
            assertEquals("MOCK DATA " + i, hm.get(i));
        }
        assertEquals(24, hm.size());
        assertFalse(hm.isEmpty());
    }

    @Test
    void putMethodWontAddAnyDuplicateKey() {
        // Given
        HashMap<Integer, String> hm = new HashMap<>();

        // When
        for (int i = 1; i < 25; i++) {
            assertNotNull(hm.put(i, "MOCK DATA " + i));
        }

        assertNull(hm.put(1, "MOCK DATA 1"));

        // Then
        for (int i = 1; i < 25; i++) {
            assertEquals("MOCK DATA " + i, hm.get(i));
        }
        assertEquals(24, hm.size());
        assertFalse(hm.isEmpty());
    }

    @Test
    void getMethodWillReturnNullWhenHashIsOutOfBounds() {
        // Given
        HashMap<Integer, String> hm = new HashMap<>();

        // When
        for (int i = 1; i < 10; i++) {
            assertNotNull(hm.put(i, "MOCK DATA " + i));
        }

        // Then
        assertNull(hm.get(95000000));
        assertEquals(9, hm.size());
        assertFalse(hm.isEmpty());
    }

    @Test
    void removeMethodWillReturnNull() {
        // Given
        HashMap<Integer, String> hm = new HashMap<>();

        // When / Then
        assertNull(hm.remove(1));
        assertEquals(0, hm.size());
        assertTrue(hm.isEmpty());
    }

    @Test
    void removeMethodWillReturnObject() {
        // Given
        HashMap<Integer, String> hm = new HashMap<>();

        // When
        for (int i = 1; i < 10; i++) {
            assertNotNull(hm.put(i, "MOCK DATA " + i));
        }

        // Then
        assertNotNull(hm.remove(1));
        assertEquals(8, hm.size());
    }

    @Test
    void removeMethodWillReturnNullWhenNotEmptyButGivenNonexistentValue() {
        // Given
        HashMap<Integer, String> hm = new HashMap<>();

        // When
        for (int i = 1; i < 10; i++) {
            assertNotNull(hm.put(i, "MOCK DATA " + i));
        }

        // Then
        assertNull(hm.remove(1000));
        assertEquals(9, hm.size());
    }

    @Test
    void iteratorWillNotIterateWhenHashMapEmpty() {
        // Given
        HashMap<Integer, String> hm = new HashMap<>();

        // When
        Iterator<Entry<Integer, String>> it = hm.iterator();

        // Then
        assertFalse(it.hasNext());
        assertNull(it.next());
    }

    @Test
    void iteratorWillIterateOnce() {
        // Given
        HashMap<Integer, String> hm = new HashMap<>();
        hm.put(1, "MOCK DATA 1");

        // When
        Iterator<Entry<Integer, String>> it = hm.iterator();

        // Then
        assertTrue(it.hasNext());
        assertNotNull(it.next());
        assertFalse(it.hasNext());
        assertNull(it.next());
    }

    @Test
    void iteratorWillIterateNTimes() {
        // Given
        HashMap<Integer, String> hm = new HashMap<>();
        int n = 10;

        for (int i = 1; i <= n; i++)
            hm.put(i, "MOCK DATA "+ i);

        // When
        Iterator<Entry<Integer, String>> it = hm.iterator();

        // Then
        for(int i = 1; i <= n; i++ ) {
            assertTrue(it.hasNext());
            assertNotNull(it.next());
        }
        assertFalse(it.hasNext());
        assertNull(it.next());
    }

    @Test
    void reversedIteratorWorks() {
        //Given
        HashMap<Integer, String> hm = new HashMap<>();
        int n = 1000;

        //When
        for(int i = 1; i <= n; i++) {
            hm.put(i, "MOCK DATA " + i);
        }

        //Then
        ReversedIterator<Entry<Integer, String>> it = hm.reversedIterator();
        for(int i = 1; i <= n; i++) {
            assertTrue(it.hasNext());
            assertNotNull(it.next());
        }

        assertFalse(it.hasNext());
        assertNull(it.next());
    }

    @Test
    void reversedIteratorWontHaveAnyNextWhenEmpty() {
        //Given
        HashMap<Integer, String> hm = new HashMap<>();

        //When
        ReversedIterator<Entry<Integer, String>> it = hm.reversedIterator();


        //Then
        assertFalse(it.hasNext());
    }

    @Test
    void hashMapWillSaveNegativeHashes() { //This test was med to test the line 233 on HashMap.class
        // Given
        HashMap<String, String> hm = new HashMap<>();

        // When / Then
        for(int i = 100; i < 1000; i++) {
            String key = "MOCK DATA " + i;
            String value = "MOCK" + i + "MOCK";
            assertNotNull(hm.put(key, value));
            assertTrue(key.hashCode() < 0);
        }

    }

    public static class EntryTest {
        @Test
        void equalsWillReturnTrueWhenSendingSameInstanceByReference() {
            // Given
            Entry<Integer, String> entry = new Entry<>(1, "MOCK DATA 1");

            // When
            Entry<Integer, String> entryTest1 = entry;

            //Then
            assertEquals(entryTest1, entry);
        }

        @Test
        void equalsWillReturnFalseWhenSendingNullInstance() {
            // Given
            Entry<Integer, String> entry = new Entry<>(1, "MOCK DATA 1");

            // When
            Entry<Integer, String> entryTest1 = null;

            //Then
            assertNotEquals(entry, entryTest1);
        }

        @Test
        void toStringTest() {
            // Given
            Entry<Integer, String> entry = new Entry<>(1, "MOCK DATA 1");

            // When / Then
            assertNotNull(entry.toString());
        }
    }
}
