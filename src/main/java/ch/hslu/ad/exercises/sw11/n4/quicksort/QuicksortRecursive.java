/*
 * Copyright 2019 Hochschule Luzern Informatik.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ch.hslu.ad.exercises.sw11.n4.quicksort;

/**
 * Codevorlage zu RecursiveAction für die Sortierung eines int-Arrays.
 */
public class QuicksortRecursive {

    /**
     * public method exposed to client, sorts given array using QuickSort
     * Algorithm in Java.
     *
     * @param array input array.
     */
    public static void quicksort(Integer[] array) {
        QuicksortRecursive.quicksort(array, 0, array.length - 1);
    }

    /**
     * Recursive quicksort logic.
     *
     * @param array input array.
     * @param startIdx start index of the array.
     * @param endIdx end index of the array.
     */
    public static void quicksort(Integer[] array, int startIdx, int endIdx) {
        int up = startIdx;          // linke Grenze
        int down = endIdx - 1;   // rechte Grenze (ohne Trennelement)
        Integer t = array[endIdx];      // rechtes Element als Trennelement
        boolean allChecked = false;
        do {
            while (array[up].compareTo(t) < 0) {
                up++;   // suche grösseres (>=) Element von links an
            }
            while ((array[down].compareTo(t) > 0) && (down > up)) {
                down--;
            }
            if (down > up) {
                exchange(array, up, down);
                up++; down--;
            } else {
                allChecked = true;
            }
        } while (!allChecked);
        exchange(array, up, endIdx);
        if (startIdx < (up - 1)) quicksort(array, startIdx, (up - 1));      // linke Hälfte
        if ((up + 1) < endIdx) quicksort(array, (up + 1), endIdx);    // rechte Hälfte, ohne T’Elt.
    }

    /**
     * Divides array from pivot, left side contains elements less than Pivot
     * while right side contains elements greater than pivot.
     *
     * @param array array to partitioned.
     * @param left lower bound of the array.
     * @param right upper bound of the array.
     * @return the partition index.
     */
    public static int partition(Integer[] array, int left, int right) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void exchange(final Integer[] array, final int i, final int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
