/**
 * Sort-Class represents
 *
 * @author stofers
 * @Version V1.0
 * @date 2019-04-09
 */
package ch.hslu.ad.exercises.sw08_09;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class Sort {

    private static final Logger LOG = LogManager.getLogger(Sort.class);

    public Sort() {

    }

    /**
     * Sorts int-array in ascending order. Uses Insertion Sort algorithm.
     * Iterates trough array and shifts higher value to the right.
     * @param a unsorted Array.
     */
    public static <T extends Comparable<T>> void insertionSort(final T[] a){
        insertionSort(a,0,a.length);
    }

    public static <T extends Comparable<T>> void insertionSort(final T[] a, int left, int right) {
        int j;
        T k;
        for (int i = left+1; i < right; i++) {
            k = a[i];
            j = i;
            while (a[j-1].compareTo(k) > 0) {    // left value > than right
                a[j] = a[j - 1];    // shift right
                a[j-1] = k;         // replace lefty with k.
                if (j > 1) {
                    j--;
                }
            }
        }
    }

    /**
     * Sorts int-array in ascending order. Uses Selection Sort algorithm.
     * Search for smallest value in the array, and move it to the left.
     * @param a unsorted Array.
     */
    public static <T extends Comparable<T>> void selectedSort(final T[] a) {
        int valIndex = 0;
        for (int i = 1; i < a.length; i++){
            int j = i;
            T lowest=a[i-1];          // sets a[0] as lowest
            while (j < a.length) {
                if (a[j].compareTo(lowest) < 0) {
                    lowest = a[j];      //
                    valIndex = j;
                }
                j++;
            }
            a[valIndex]=a[i-1];
            a[i-1]=lowest;
        }
    }

    /**
     * Sorts int-array in ascending order. Uses Selection Sort algorithm with only one while loop.
     * Search for smallest value in the array, and move smallest value to the left.
     * @param a unsorted Array.
     */
    public static <T extends Comparable<T>> void selectedSort2(final T[] a) {
        int length = a.length;
        int valIndex = 0;
        T lowest=a[0];          // sets a[0] as lowest
        int i = 1, j = 1;
            while (i < length) {
                if (a[i].compareTo(lowest) < 0) {
                    lowest = a[i];      //
                    valIndex = i;
                }
                    if ( i == length-1){
                        a[valIndex]=a[j-1];
                        a[j-1]=lowest;
                        lowest=a[j];
                        i=j;
                        j++;
                    }
                i++;
            }
    }


    /**
     * Sorts int-array in ascending order. Uses Bubble Sort algorithm.
     * Iterates trough array and shift bigger value to the right.
     * @param a unsorted Array.
     */
    public static <T extends Comparable<T>> void bubbleSort(final T[] a){
        int length = a.length-1;
        int i = 0;
        while (i < length){
            if (a[i].compareTo(a[i+1])>0) {      // left value > than right
                T val = a[i + 1]; // save right value
                a[i + 1] = a[i];    // shift right
                a[i] = val;         // replace left
            }
            if (i == length-1){
                length--;
                i=-1;
            }
            i++;
        }
    }

    /**
     * Sorts int-array in ascending order. Uses bubble Sort algorithm.
     * Move bigger value to the right. Aborts when nothing to swap.
     * @param a unsorted Array.
     */
    public static <T extends Comparable<T>> void bubbleSort2(final T[] a){
        int length = a.length-1;
        int i = 0;
        boolean swapped = false;
        while (i < length){
            if (a[i].compareTo(a[i+1])>0) {      // left value > than right
                T val = a[i + 1]; // save right value
                a[i + 1] = a[i];    // shift right
                a[i] = val;         // replace left
                swapped = true;     // swapped values
            }
            if (i == length-1){
                if (swapped) {
                    length--;
                    i = -1;
                    swapped=false;
                }
                else {
                    break;
                }
            }
            i++;

        }
    }

    /**
     * Sorts int-array in ascending order. Use Shellsort algorithm. NOT FINISHED.
     * @param a unsorted Array.
     */
    public static void shellSort(final int[] a){
        int j, k;
        int factor = 4;
        for (int i = 1; i < a.length/factor; i++) {
            k = a[i]*factor;
            j = i;
            while (a[j-1] > k) {    // left value > than right
                a[j] = a[j - 1];    // shift right
                a[j-1] = k;         // replace lefty with k.
                if (j > 1) {
                    j--;
                    factor = 2;
                }
            }
        }
    }

    /**
     * Vertauscht zwei bestimmte Zeichen im Array.
     *
     * @param a Zeichen-Array
     * @param firstIndex Index des ersten Zeichens
     * @param secondIndex Index des zweiten Zeichens
     */
    private static final void exchange(final Object[] a, final int firstIndex, final int secondIndex) {
        Object tmp;
        tmp = a[firstIndex];
        a[firstIndex] = a[secondIndex];
        a[secondIndex] = tmp;
    }

    /**
     * Overloads {@code quickSort} with default values.
     * @param a
     */
    public static <T extends Comparable<T>> void quickSort(final T[] a){
        quickSort(a,0,a.length-1);
    }

    /**
     * quickSort algorithm always pick most right element in a given range and use it as pivot. Use left/right subarray
     * for further recursive sorting.
     * @param a
     * @param left
     * @param right
     */

    public static <T extends Comparable<T>> void quickSort(final T[] a, final int left, final int right) {
        int up = left;          // linke Grenze
        int down = right - 1;   // rechte Grenze (ohne Trennelement)
        T t = a[right];      // rechtes Element als Trennelement
        boolean allChecked = false;
        do {
            while (a[up].compareTo(t) < 0) {
                up++;   // suche grösseres (>=) Element von links an
            }
            while ((a[down].compareTo(t) > 0) && (down > up)) {
                down--;
            }
            if (down > up) {
                exchange(a, up, down);
                up++; down--;
            } else {
                allChecked = true;
            }
        } while (!allChecked);
        exchange(a, up, right);
        if (left < (up - 1)) quickSort(a, left, (up - 1));      // linke Hälfte
        if ((up + 1) < right) quickSort(a, (up + 1), right);    // rechte Hälfte, ohne T’Elt.
    }

    /**
     * Overloads {@code quickInsertionSort} with default values.
     * @param a
     */
    public static <T extends Comparable<T>> void quickInsertionSort(final T[] a){
        quickInsertionSort(a,0,a.length-1);
    }

    /**
     * quickInsertionSort algorithm always pick most right element in a given range and use it as pivot. Use left/right subarray
     * for further recursive sorting. If array length is smaller than m, it delegates rest of sorting to {@code insertionSort}.
     * @param a
     * @param left
     * @param right
     */
    public static <T extends Comparable<T>> void quickInsertionSort(final T[] a, final int left, final int right) {
        int m = 20; // when smaller than 25 -> insertionSort
        if (right - left >= m) {
            quickSort(a,0,a.length-1);
        }
        else {
            insertionSort(a,left,right);
        }
    }

    /**
     * heapSort algorithm creates heap-Collection and sorts objects in ascending order.
     * @param a
     * @param <T>
     */
    public static final <T extends Comparable<T>> void heapSort(T[] a){
        GenericFixedSizeHeap<T> heap = new GenericFixedSizeHeap<>(a.length);
        for (int i = 0; i < a.length; i++){
            heap.insert(a[i]);
        }
        for (int i = a.length - 1; i >= 0; i--) {
            a[i] = heap.getMax();
        }
    }

    /**
     * Returns randomly created Character objects in an array.
     * @param length
     * @return Character[]
     */
    public static final Character[] randomChars(final int length){
        Character[] charArray = new Character[length];
        Random rnd = new Random();
        for (int i=0; i< charArray.length; i++){
            charArray[i] = (char) (rnd.nextInt(26) + 'a');
        }
        return charArray;
    }

    /**
     * Returns randomly created Integer objects in an array.
     * @param length
     * @return
     */
    public static final Integer[] randomInts(final int length){
        Integer[] intArray = new Integer[length];
        Random rnd = new Random();
        for (int i=0; i< intArray.length; i++){
            intArray[i] = rnd.nextInt(length);
        }
        return intArray;
    }
}
