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

import ch.hslu.ad.exercises.sw08_09.Sort;
import java.util.concurrent.RecursiveAction;

/**
 * Codevorlage zu RecursiveAction für die Sortierung eines int-Arrays.
 */
@SuppressWarnings("serial")
public final class QuicksortTask extends RecursiveAction {

    private static int THRESHOLD;
    private final Integer[] array;
    private final int min;
    private final int max;

    /**
     * Erzeugt einen Array-Sortier Task.
     *
     * @param array Interger-Array.
     */
    public QuicksortTask(Integer[] array) {
        this(array, 0, array.length - 1);
    }

    private QuicksortTask(final Integer[] array, final int min, final int max) {
        this.array = array;
        this.min = min;
        this.max = max;
        THRESHOLD = (array.length / (Runtime.getRuntime().availableProcessors()+1));
    }

    @Override
    protected void compute() {
        if (max-min<=1){
            return;
        }
        if(max - min < THRESHOLD ){
            Sort.quickSort(array,min,max);
        }
        else{
//            int mid = min + (max - min) / 2;
//            mid = partition(mid);
            int mid = partition(min, max);
            invokeAll(new QuicksortTask(array,min,mid-1), new QuicksortTask(array,mid+1,max));
        }
    }

    private int partition(int min, int max){
        int up = min;          // linke Grenze
        int down = max - 1;
        Integer t = array[max];      // rechtes Element als Trennelement
        boolean allChecked = false;
        do {
            while (array[up].compareTo(t) < 0) {
                up++;   // suche grösseres (>=) Element von links an
            }
            while ((array[down].compareTo(t) > 0) && (down > up)) {
                down--;
            }
            if (down > up) {
                swap(up, down);
                up++; down--;
            } else {
                allChecked = true;
            }
        } while (!allChecked);
        swap(up, max);
        return up;
    }

//    private int partition(int mid){
//        Integer pivotValue = array[mid];
//
//        swap(mid, max);
//
//        int storeIndex = min;
//        for (int i=min; i<max; i++){
//            if (array[i].compareTo(pivotValue) < 0){
//                swap(i, storeIndex);
//                storeIndex++;
//            }
//        }
//
//        swap(storeIndex, max);
//
//        return storeIndex;
//
//    }


    private void swap(int i, int j){
        if (i != j){
            Integer iValue = array[i];

            array[i] = array[j];
            array[j] = iValue;
        }
    }
}
