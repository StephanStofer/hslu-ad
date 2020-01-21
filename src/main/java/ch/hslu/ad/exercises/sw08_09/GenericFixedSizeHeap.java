/**
 * GenericFixedSizeHeap-Class represents
 *
 * @author stofers
 * @Version V1.0
 * @date 2019-04-22
 */
package ch.hslu.ad.exercises.sw08_09;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class GenericFixedSizeHeap<E extends Comparable<E>> implements GenericHeapInterface<E> {

    private static final Logger LOG = LogManager.getLogger(GenericFixedSizeHeap.class);

    private Object[] elements;
    private int size;

    public GenericFixedSizeHeap(int length) {
        this.elements = new Object[length];
    }

    /**
     * Swaps two objects in List.
     * @param a first object.
     * @param b second object.
     */
    private void swap(int a, int b) {
        Object tmp = elements[a];
        elements[a] = elements[b];
        elements[b] = tmp;
    }

    /**
     * Iterate top-down trough binary tree list.
     */
    private void sink() {
        final int size = size();
        boolean sunk = false;
        int l = 1, f = 0, r = 2;
        while (!sunk && (l < size || r < size)) {
            E father = getElementAt(f);
            E left = l < size ? getElementAt(l) : father;
            E right = r < size ? getElementAt(r) : father;
            if (father.compareTo(left) < 0 || father.compareTo(right) < 0) {
                int biggerChildIndex = left.compareTo(right) > 0 ? l : r;
                swap(f, biggerChildIndex);
                f = biggerChildIndex;
                l = getLeftChildIndex(f);
                r = getRightChildIndex(f);
            } else {
                sunk = true;
            }
        }
    }

    /**
     * Iterate bottom-up.
     * @param i
     */
    private void raise(int i) {
        boolean risen = false;
        while (!risen) {
            int father = getParentIndex(i);
            if (getElementAt(i).compareTo(getElementAt(father)) > 0) {
                swap(i, father);
                i = father;
                if (i == 0) {
                    risen = true;
                }
            } else {
                risen = true;
            }
        }
    }


    @Override
    public void insert(E e) {
        this.size++;
        int newElementIndex = size() - 1;
        elements[newElementIndex] = e;
        raise(newElementIndex);

    }

    @Override
    public E getElementAt(int index) {
        return (E)elements[index];
    }

    @Override
    public E getMax() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty.");
        }
        E max = (E)elements[0];
        elements[0] = elements[size()-1];     // replace elements in List
        elements[size()-1] = null;
        this.size--;
        sink();
        return max;
    }

    @Override
    public int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    @Override
    public int getLeftChildIndex(int index) {
        return 2 * index + 1;
    }

    @Override
    public int getRightChildIndex(int index) {
        return 2 * index + 2;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return this.size;
    }
}
