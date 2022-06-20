package collections.list.arraylist;

import collections.list.Iterator;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArrayListTest {
    private static Logger log = LoggerFactory.getLogger(ArrayListTest.class);

    @Test
    void dataListStartsWithSize10(){
        //Given
        ArrayList al = new ArrayList();

        //When
        int size = al.dataList.length;

        //Then
        assertEquals(10, size);
    }

    @Test
    void addMethodWorksUpdatesSizeAndStillHave9Slots() {
        //Given
        ArrayList al = new ArrayList();

        //When
        al.add("MOCK DATA 1");

        //Then
        assertEquals(1, al.size());
        assertEquals(10, al.dataList.length);
    }

    @Test
    void addMethodWorksAndResizesItSelf() {
        //Given
        ArrayList al = new ArrayList();

        //When
        al.add("MOCK DATA 1");
        al.add("MOCK DATA 2");
        al.add("MOCK DATA 3");
        al.add("MOCK DATA 4");
        al.add("MOCK DATA 5");
        al.add("MOCK DATA 6");
        al.add("MOCK DATA 7");
        al.add("MOCK DATA 8");
        al.add("MOCK DATA 9");
        al.add("MOCK DATA 10");
        al.add("MOCK DATA 11");


        //Then
        assertEquals(11, al.size());
        assertEquals(20, al.dataList.length);
    }

    @Test
    void insertMethodWorksWhenIndexIsMinorThanActualArrayLength() {
        //Given
        ArrayList al = new ArrayList();
        al.add("MOCK DATA 1");
        al.add("MOCK DATA 2");
        al.add("MOCK DATA 3");
        al.add("MOCK DATA 4");
        al.add("MOCK DATA 5");
        al.add("MOCK DATA 6");
        al.add("MOCK DATA 7");
        al.add("MOCK DATA 8");
        al.add("MOCK DATA 9");
        al.add("MOCK DATA 10");

        //When
        al.insert(9, "MOCK DATA 10 REPLACEMENT");

        //Then
        assertEquals("MOCK DATA 10 REPLACEMENT", al.getAt(9));
    }

    @Test
    void insertMethodFailsWhenIndexIsGreaterThanActualArrayLength() {
        //Given
        ArrayList al = new ArrayList();

        //When
        al.add("MOCK DATA 1");
        al.add("MOCK DATA 2");
        al.add("MOCK DATA 3");
        al.add("MOCK DATA 4");
        al.add("MOCK DATA 5");
        al.add("MOCK DATA 6");
        al.add("MOCK DATA 7");
        al.add("MOCK DATA 8");
        al.add("MOCK DATA 9");
        al.add("MOCK DATA 10");

        //Then
        assertThrows(IndexOutOfBoundsException.class, () -> al.insert(10, "MOCK DATA 10 REPLACEMENT"));
    }

    @Test
    void insertMethodWorksWhenInsertsAtTheBeginning() {
        //Given
        ArrayList al = new ArrayList();

        //When
        al.add("MOCK DATA 1");
        al.add("MOCK DATA 2");
        al.add("MOCK DATA 3");
        al.add("MOCK DATA 4");
        al.add("MOCK DATA 5");
        al.add("MOCK DATA 6");
        al.add("MOCK DATA 7");
        al.add("MOCK DATA 8");
        al.add("MOCK DATA 9");
        al.add("MOCK DATA 10");

        al.insert(0, "MOCK DATA 1 REPLACEMENT");

        //Then
        assertEquals("MOCK DATA 1 REPLACEMENT", al.getAt(0));
    }

    @Test
    void insertMethodWorksWhenInsertsAtTheEnd() {
        //Given
        ArrayList al = new ArrayList();

        //When
        al.add("MOCK DATA 1");
        al.add("MOCK DATA 2");
        al.add("MOCK DATA 3");
        al.add("MOCK DATA 4");
        al.add("MOCK DATA 5");
        al.add("MOCK DATA 6");
        al.add("MOCK DATA 7");
        al.add("MOCK DATA 8");
        al.add("MOCK DATA 9");
        al.add("MOCK DATA 10");

        al.insert(9, "MOCK DATA 10 REPLACEMENT");

        //Then
        assertEquals("MOCK DATA 10 REPLACEMENT", al.getAt(9));
    }

    @Test
    void insertMethodWorksAndUpdatesSize() {
        //Given
        ArrayList al = new ArrayList();

        //When
        al.add("MOCK DATA 1");
        al.add("MOCK DATA 2");

        //Then
        assertEquals(2, al.size());
    }

    @Test
    void secondConstructorSetsArraySizeToCustom() {
        //Given
        ArrayList al = new ArrayList(5);

        //When
        int length = al.dataList.length;

        //Then
        assertEquals(5, length);
    }

    @Test
    void getAtWorksWhenIndexIsInsideBounds() {
        //Given
        ArrayList al = new ArrayList();

        //When
        al.add("MOCK DATA 1");
        String result = al.getAt(0);

        //Then
        assertEquals("MOCK DATA 1", result);
    }

    @Test
    void getAtFailsWhenIndexIsOutOfBounds() {
        //Given
        ArrayList al = new ArrayList();

        //When
        al.add("MOCK DATA 1");

        //Then
        assertThrows(IndexOutOfBoundsException.class, () -> al.getAt(10));
    }

    @Test
    void setAtWorksWhenIndexIsInsideOfBounds() {
        //Given
        ArrayList al = new ArrayList();

        //When
        al.add("MOCK DATA 1");
        al.add("MOCK DATA 2");
        al.setAt(1,"MOCK DATA 2 REPLACEMENT");

        //Then
        assertEquals("MOCK DATA 2 REPLACEMENT", al.getAt(1));
    }

    @Test
    void setAtFailsWhenIndexIsOutOfBounds() {
        //Given
        ArrayList al = new ArrayList();

        //When
        al.add("MOCK DATA 1");

        //Then
        assertThrows(IndexOutOfBoundsException.class, () -> al.setAt(10, ""));
    }

    @Test
    void removeSuccessWhenIndexIsInsideOfBounds() {
        //Given
        ArrayList al = new ArrayList();

        //When
        al.add("MOCK DATA 1");
        al.add("MOCK DATA 2");

        al.remove(1);
        int size = al.size();

        //Then
        assertEquals(1, size);
    }

    @Test
    void removeFailsWhenIndexIsOutOfBounds() {
        //Given
        ArrayList al = new ArrayList();

        //When
        al.add("MOCK DATA 1");
        al.add("MOCK DATA 2");

        //Then
        assertThrows(IndexOutOfBoundsException.class, () -> al.remove(10));
    }

    @Test
    void removeAllSuccess() {
        //Given
        ArrayList al = new ArrayList();

        //When
        al.add("MOCK DATA 1");
        al.add("MOCK DATA 2");

        al.removeAll();

        //Then
        assertEquals(0, al.size());
    }

    @Test
    void iteratorReturnsAnIteratorType() {
        //Given
        ArrayList al = new ArrayList();

        //When
        al.add("MOCK DATA 1");

        Iterator it = al.iterator();

        //Then
        assertEquals("collections.list.arraylist.ArrayList.ArrayListIterator", it.getClass().getCanonicalName());
    }

    @Test
    void toStringSuccess() {
        //Given
        ArrayList al = new ArrayList();

        //When
        al.add("MOCK DATA 1");

        String result = al.toString();

        //Then
        assertEquals("ArrayList{" + "size=" + al.size() +", dataList=" + Arrays.toString(al.dataList) +'}', result);
    }

}
