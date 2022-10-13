package nl.han.ica.datastructures;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HANLinkedListTest {

    HANLinkedList<Integer> list = new HANLinkedList<Integer>();

    @Test
    void testAddFirst(){
        int firstItem = 1;
        int secondFirstItem = 2;

        list.addFirst(firstItem);

        assertEquals(list.getFirst(), firstItem);

        list.addFirst(secondFirstItem);

        assertEquals(list.getFirst(), secondFirstItem);

    }

    @Test
    void testInsert(){
        int firstItem = 1;
        int secondFirstItem = 2;
        int itemToInsert = 3;

        list.addFirst(firstItem);
        list.addFirst(secondFirstItem);

        list.insert(1, itemToInsert);

        assertEquals(list.get(1), itemToInsert);
        assertEquals(list.get(2), firstItem);
        assertEquals(list.get(0), secondFirstItem);
    }

    @Test
    void testDelete(){
        int[] items = {1, 2, 3};

        for(int item : items){
            list.addFirst(item);
        }

        list.delete(1);

        assertEquals(list.get(0), items[2]);
        assertEquals(list.get(1), items[0]);
    }

    @Test
    void testClear(){
        int[] items = {1, 2, 3};

        for(int item : items){
            list.addFirst(item);
        }

        list.clear();

        assertEquals(list.getSize(), 0);
    }

    @Test
    void testRemoveFirst(){
        int[] items = {1, 2, 3};

        for(int item : items){
            list.addFirst(item);
        }

        list.removeFirst();

        assertEquals(list.get(0), items[1]);
        assertEquals(list.get(1), items[0]);
    }

    @Test
    void testSize(){
        int[] items = {1, 2, 3};

        for(int item : items){
            list.addFirst(item);
        }

        assertEquals(list.getSize(), items.length);
    }

}
