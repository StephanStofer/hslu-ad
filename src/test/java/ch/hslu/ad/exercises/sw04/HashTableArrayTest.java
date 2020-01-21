package ch.hslu.ad.exercises.sw04;

import ch.hslu.ad.exercises.sw01.Allocation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashTableArrayTest {

    @Test
    void testAddArrayObject(){
        HashTableArray<Allocation> hashTableArray = new HashTableArray<>(10);
        Allocation allocation = new Allocation(555,12);
        hashTableArray.insert(allocation);
        assertEquals(allocation, hashTableArray.getElement(5));
    }


    @Test
    void testNotInArray(){
        HashTableArray<Allocation> hashTableArray = new HashTableArray<>(10);
        Allocation allocation = new Allocation(34,111);
        Allocation allocNotAdded = new Allocation(23,24);
        hashTableArray.insert(allocation);
        assertNull(hashTableArray.getElement(3));
    }
    
    @Test
    void testIndexAlreadyTaken(){
        HashTableArray<Allocation> hashTableArray = new HashTableArray<>(10);
        Allocation alloc1 = new Allocation(555,12);
        Allocation alloc2 = new Allocation(355,3);
        hashTableArray.insert(alloc1);
        hashTableArray.insert(alloc2);

    }

    @Test
    void testGetElement(){
        HashTableArray<Allocation> hashTableArray = new HashTableArray<>(10);
        Allocation allocation1 = new Allocation(13,278);
        Allocation allocation2 = new Allocation(23,14);
        hashTableArray.insert(allocation1);
        hashTableArray.insert(allocation2);
        assertEquals(allocation1, hashTableArray.getElement(3));
        assertEquals(allocation2, hashTableArray.getElement(4));
    }

    @Test
    void testSize(){
        HashTableArray<Allocation> hashTableArray = new HashTableArray<>(10);
        hashTableArray.insert(new Allocation(776,12));
        hashTableArray.insert(new Allocation(2,3));
        assertEquals(2, hashTableArray.size());
    }

    @Test
    void testIsFull(){
        HashTableArray<Allocation> hashTableArray = new HashTableArray<>(1);
        hashTableArray.insert(new Allocation(11,12));
        hashTableArray.insert(new Allocation(10,12));
        assertTrue(hashTableArray.isFull());
    }

    @Test
    void testSearchByValue(){
        HashTableArray<Allocation> hashTableArray = new HashTableArray<>(10);
        Allocation allocation1 = new Allocation(22,18);
        Allocation allocation2 = new Allocation(42,18);
        Allocation allocation3 = new Allocation(32,9);
        hashTableArray.insert(allocation1);
        hashTableArray.insert(allocation2);
        hashTableArray.insert(allocation3);
        assertEquals(allocation3, hashTableArray.searchByValue(allocation3));
    }

    @Test
    void testRemove(){
        HashTableArray<Allocation> hashTableArray = new HashTableArray<>(10);
        Allocation allocation1 = new Allocation(99,18);
        Allocation allocation2 = new Allocation(89,18);
        Allocation allocation3 = new Allocation(79,9);
        hashTableArray.insert(allocation1);
        hashTableArray.insert(allocation2);
        hashTableArray.insert(allocation3);
        hashTableArray.remove(allocation2);
        assertNull(hashTableArray.searchByValue(allocation2));
    }

    @Test
    void testPrintArray(){
        HashTableArray<Allocation> hashTableArray = new HashTableArray<>(10);
        Allocation allocation1 = new Allocation(22,18);
        Allocation allocation2 = new Allocation(42,18);
        Allocation allocation3 = new Allocation(32,9);
        hashTableArray.insert(allocation1);
        hashTableArray.insert(allocation2);
        hashTableArray.insert(allocation3);
        hashTableArray.printArray();
    }
}
