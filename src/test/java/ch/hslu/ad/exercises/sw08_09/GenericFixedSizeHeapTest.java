package ch.hslu.ad.exercises.sw08_09;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenericFixedSizeHeapTest {

    GenericFixedSizeHeap<Integer> heap = new GenericFixedSizeHeap<>(50);
    Integer[] int1 = Sort.randomInts(50);

    @Test
    void testInsert() {
        heap.insert(34);
        assertEquals(34, heap.getElementAt(0).intValue());
        assertEquals(34, heap.getMax().intValue());
    }

    @Test
    void testRemove() {
        heap.insert(12);
        heap.insert(14);
        heap.insert(1);
        heap.insert(4);
        heap.insert(7);
        heap.getMax();
        assertEquals(12,heap.getMax().intValue());
    }

}