package ch.hslu.ad.exercises.sw04;

import ch.hslu.ad.exercises.sw01.Allocation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashTableBucketsTest {

    @Test
    void testSize() {
        HashTableBuckets<Allocation> htb = new HashTableBuckets<>(10);
        htb.insert(new Allocation(223,61));
        htb.insert(new Allocation(23,88));
        htb.insert(new Allocation(46,2));
        htb.insert(new Allocation(54,45));
        assertEquals(4,htb.size());
    }

    @Test
    void testInsert() {
        HashTableBuckets<Allocation> htb = new HashTableBuckets<>(10);
        assertTrue(htb.insert(new Allocation(223,554)));
    }

    @Test
    void testGetElement() {
        HashTableBuckets<Allocation> htb = new HashTableBuckets<>(10);
        Allocation alloc1 = new Allocation(90,1);
        Allocation alloc2 = new Allocation(12,44);
        Allocation alloc3 = new Allocation(12,34);
        htb.insert(alloc1);
        htb.insert(alloc2);
        htb.insert(alloc3);
        assertEquals(alloc3, htb.getElement(alloc3));
    }

    @Test
    void testRemove() {
        HashTableBuckets<Allocation> htb = new HashTableBuckets<>(10);
        Allocation alloc = new Allocation(34,78);
        htb.insert(alloc);
        assertTrue(htb.remove(alloc));
    }
}
