package ch.hslu.ad.exercises.sw08_09;

public interface GenericHeapInterface<E extends Comparable<E>> {

    void insert(E e);

    E getElementAt(int index);

    E getMax();

    boolean isEmpty();

    int size();

    int getParentIndex(int index);

    int getLeftChildIndex(int index);

    int getRightChildIndex(int index);
}
