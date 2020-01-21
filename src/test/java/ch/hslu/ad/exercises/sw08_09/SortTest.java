package ch.hslu.ad.exercises.sw08_09;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

class SortTest {

    private static final Logger LOG = LogManager.getLogger(SortTest.class);
    final int size = 1_000;
//    final int size = 50;
    Random random = new Random();
    long startTime;
    long time;
    long startTimeQS;
    long timeQS;
    long startTimeQIS;
    long timeQIS;


    @Test
    void testSortRandomArray(){
        Integer[] intArray = new Integer[size];
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = random.nextInt(size);
        }
        Integer[] intArray2 = Arrays.copyOf(intArray,intArray.length);
        Integer[] intArray3 = Arrays.copyOf(intArray,intArray.length);
        Integer[] intArray4 = Arrays.copyOf(intArray,intArray.length);
        LOG.info("####### testSortRandomArray ########");
//        LOG.info("send to insertionSort Method...");
//        LOG.info(Arrays.toString(intArray));

        startTime = System.currentTimeMillis();
        Sort.insertionSort(intArray);
        time = System.currentTimeMillis() - startTime;

        LOG.info("insertionSort: Array sorted in {}ms",time);
//        LOG.info(Arrays.toString(intArray));

//        LOG.info("send to selectedSort Method...");
//        LOG.info(Arrays.toString(intArray2));

        startTime = System.currentTimeMillis();
        Sort.selectedSort(intArray2);
        time = System.currentTimeMillis() - startTime;

        LOG.info("selectedSort: Array sorted in {}ms",time);
//        LOG.info(Arrays.toString(intArray2));

//        LOG.info("send to bubbleSort Method...");
//        LOG.info(Arrays.toString(intArray3));

        startTime = System.currentTimeMillis();
        Sort.bubbleSort(intArray3);
        time = System.currentTimeMillis() - startTime;

        LOG.info("bubbleSort: Array sorted in {}ms",time);
//        LOG.info(Arrays.toString(intArray3));

//        LOG.info("send to bubbleSort2 Method");
//        LOG.info(Arrays.toString(intArray4));

        startTime = System.currentTimeMillis();
        Sort.bubbleSort2(intArray4);
        time = System.currentTimeMillis() - startTime;

        LOG.info("bubbleSort2: Array sorted in {}ms",time);
//        LOG.info(Arrays.toString(intArray4));
    }


    @Test
    void testSortSortedArray(){
        Integer[] intArray = new Integer[size];
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = i;
        }
        Integer[] intArray2 = Arrays.copyOf(intArray,intArray.length);
        Integer[] intArray3 = Arrays.copyOf(intArray,intArray.length);
        Integer[] intArray4 = Arrays.copyOf(intArray,intArray.length);
        LOG.info("####### testSortSortedArray ########");
//        LOG.info("send to insertionSort Method...");
//        LOG.info(Arrays.toString(intArray));

        startTime = System.currentTimeMillis();
        Sort.insertionSort(intArray);
        time = System.currentTimeMillis() - startTime;

        LOG.info("insertionSort: Array sorted in {}ms",time);
//        LOG.info(Arrays.toString(intArray));

//        LOG.info("send to selectedSort Method...");
//        LOG.info(Arrays.toString(intArray2));

        startTime = System.currentTimeMillis();
        Sort.selectedSort(intArray2);
        time = System.currentTimeMillis() - startTime;

        LOG.info("selectedSort: Array sorted in {}ms",time);
//        LOG.info(Arrays.toString(intArray2));

//        LOG.info("send to bubbleSort Method...");
//        LOG.info(Arrays.toString(intArray3));

        startTime = System.currentTimeMillis();
        Sort.bubbleSort(intArray3);
        time = System.currentTimeMillis() - startTime;

        LOG.info("bubbleSort: Array sorted in {}ms",time);
//        LOG.info(Arrays.toString(intArray3));

//        LOG.info("send to bubbleSort2 Method");
//        LOG.info(Arrays.toString(intArray4));

        startTime = System.currentTimeMillis();
        Sort.bubbleSort2(intArray4);
        time = System.currentTimeMillis() - startTime;

        LOG.info("bubbleSort2: Array sorted in {}ms",time);
//        LOG.info(Arrays.toString(intArray4));
    }

    @Test
    void testSortReversedArray(){
        Integer[] intArray = new Integer[size];
        for (int i = size-1; i >= 1; i--) {
            intArray[size-i] = i;
        }
        Integer[] intArray2 = Arrays.copyOf(intArray,intArray.length);
        Integer[] intArray3 = Arrays.copyOf(intArray,intArray.length);
        Integer[] intArray4 = Arrays.copyOf(intArray,intArray.length);
        LOG.info("####### testSortReversedArray ########");
//        LOG.info("send to insertionSort Method...");
//        LOG.info(Arrays.toString(intArray));

        startTime = System.currentTimeMillis();
        Sort.insertionSort(intArray);
        time = System.currentTimeMillis() - startTime;

        LOG.info("insertionSort: Array sorted in {}ms",time);
//        LOG.info(Arrays.toString(intArray));

//        LOG.info("send to selectedSort Method...");
//        LOG.info(Arrays.toString(intArray2));

        startTime = System.currentTimeMillis();
        Sort.selectedSort(intArray2);
        time = System.currentTimeMillis() - startTime;

        LOG.info("selectedSort: Array sorted in {}ms",time);
//        LOG.info(Arrays.toString(intArray2));

//        LOG.info("send to bubbleSort Method...");
//        LOG.info(Arrays.toString(intArray3));

        startTime = System.currentTimeMillis();
        Sort.bubbleSort(intArray3);
        time = System.currentTimeMillis() - startTime;

        LOG.info("bubbleSort: Array sorted in {}ms",time);
//        LOG.info(Arrays.toString(intArray3));

//        LOG.info("send to bubbleSort2 Method");
//        LOG.info(Arrays.toString(intArray4));

        startTime = System.currentTimeMillis();
        Sort.bubbleSort2(intArray4);
        time = System.currentTimeMillis() - startTime;

        LOG.info("bubbleSort2: Array sorted in {}ms",time);
//        LOG.info(Arrays.toString(intArray4));

    }

    /**
     * Quicksort with 6 arrays.
     */
    @Test
    void testQuickSort(){
        int length = 500_000;
        Character[] a = Sort.randomChars(length);
        Integer[] b = Sort.randomInts(length);
        Integer[] b1 = Sort.randomInts(length);
        Integer[] b2 = Sort.randomInts(length);
        Integer[] b3 = Sort.randomInts(length);
        Integer[] b4 = Sort.randomInts(length);
        Integer[] b5 = Sort.randomInts(length);
//        Integer[] b1 = Arrays.copyOf(b,b.length);
//        Integer[] b2 = Arrays.copyOf(b,b.length);
//        Integer[] b3 = Arrays.copyOf(b,b.length);
//        Integer[] b4 = Arrays.copyOf(b,b.length);
//        Integer[] b5 = Arrays.copyOf(b,b.length);
        LOG.info("####### testQuickSort ########");
//        LOG.info(Arrays.toString(b));

        startTime = System.currentTimeMillis();

        Sort.quickSort(b);
        time = System.currentTimeMillis() - startTime;

        LOG.info("quickSort: Array b sorted in {}ms, {}",time);
//        LOG.info(Arrays.toString(b));

        startTime = System.currentTimeMillis();

        Sort.quickSort(b1);
        time = System.currentTimeMillis() - startTime;

        LOG.info("quickSort: Array b1 sorted in {}ms, {}",time);

        startTime = System.currentTimeMillis();

        Sort.quickSort(b2);
        time = System.currentTimeMillis() - startTime;

        LOG.info("quickSort: Array b2 sorted in {}ms, {}",time);

        startTime = System.currentTimeMillis();

        Sort.quickSort(b3);
        time = System.currentTimeMillis() - startTime;

        LOG.info("quickSort: Array b3 sorted in {}ms, {}",time);

        startTime = System.currentTimeMillis();

        Sort.quickSort(b4);
        time = System.currentTimeMillis() - startTime;

        LOG.info("quickSort: Array b4 sorted in {}ms",time);

        startTime = System.currentTimeMillis();

        Sort.quickSort(b5);
        time = System.currentTimeMillis() - startTime;

        LOG.info("quickSort: Array b5 sorted in {}ms",time);
    }

    /**
     * quickInsertionSort with 6 fixed arrays.
     */
    @Test
    void testQuickInsertionSort(){
        int length = 500_000;
//        Integer[] b = Sort.randomInts(500);
        Integer[] b = Sort.randomInts(length);
//        Integer[] b1 = Arrays.copyOf(b,b.length);
//        Integer[] b2 = Arrays.copyOf(b,b.length);
//        Integer[] b3 = Arrays.copyOf(b,b.length);
//        Integer[] b4 = Arrays.copyOf(b,b.length);
//        Integer[] b5 = Arrays.copyOf(b,b.length);
        Integer[] b1 = Sort.randomInts(length);
        Integer[] b2 = Sort.randomInts(length);
        Integer[] b3 = Sort.randomInts(length);
        Integer[] b4 = Sort.randomInts(length);
        Integer[] b5 = Sort.randomInts(length);
        LOG.info("####### testQuickInsertionSort ########");
//        LOG.info(Arrays.toString(b));

        startTime = System.currentTimeMillis();

        Sort.quickInsertionSort(b);
        time = System.currentTimeMillis() - startTime;

        LOG.info("quickInsertionSort: Array b sorted in {}ms",time);
//        LOG.info(Arrays.toString(b));

        startTime = System.currentTimeMillis();

        Sort.quickInsertionSort(b1);
        time = System.currentTimeMillis() - startTime;

        LOG.info("quickInsertionSort: Array b1 sorted in {}ms",time);

        startTime = System.currentTimeMillis();

        Sort.quickInsertionSort(b2);
        time = System.currentTimeMillis() - startTime;

        LOG.info("quickInsertionSort: Array b2 sorted in {}ms",time);

        startTime = System.currentTimeMillis();

        Sort.quickInsertionSort(b3);
        time = System.currentTimeMillis() - startTime;

        LOG.info("quickInsertionSort: Array b3 sorted in {}ms",time);

        startTime = System.currentTimeMillis();

        Sort.quickInsertionSort(b4);
        time = System.currentTimeMillis() - startTime;

        LOG.info("quickInsertionSort: Array b4 sorted in {}ms",time);

        startTime = System.currentTimeMillis();

        Sort.quickInsertionSort(b5);
        time = System.currentTimeMillis() - startTime;

        LOG.info("quickInsertionSort: Array b5 sorted in {}ms",time);
    }

    /**
     * quickSort compared to quickInsertionSort
     */
    @Test
    void testQuickSortComparison(){
        Integer[] a1 = Sort.randomInts(500_000);
        Integer[] b1 = Arrays.copyOf(a1,a1.length);
        Integer[] a2 = Sort.randomInts(500_000);
        Integer[] b2 = Arrays.copyOf(a2,a2.length);
        Integer[] a3 = Sort.randomInts(500_000);
        Integer[] b3 = Arrays.copyOf(a3,a3.length);
        LOG.info("####### testQuickSortComparison ########");
        startTimeQS = System.currentTimeMillis();
        Sort.quickInsertionSort(a1);
        timeQS = System.currentTimeMillis() - startTimeQS;
        LOG.info("quickSort: Array 1 sorted in {}ms",timeQS);

        startTimeQIS = System.currentTimeMillis();
        Sort.quickInsertionSort(b1);
        timeQIS = System.currentTimeMillis() - startTimeQIS;
        LOG.info("quickInsertionSort: Array 1 sorted in {}ms",timeQIS);
        LOG.info("Quotient of {}ms and {}ms is {}", timeQS,timeQIS,((double)timeQS/(double)timeQIS));

        startTime = System.currentTimeMillis();
        Sort.quickInsertionSort(a2);
        timeQS = System.currentTimeMillis() - startTimeQS;
        LOG.info("quickSort: Array 2 sorted in {}ms",timeQS);

        startTime = System.currentTimeMillis();
        Sort.quickInsertionSort(b2);
        timeQIS = System.currentTimeMillis() - startTimeQIS;
        LOG.info("quickInsertionSort: Array 2 sorted in {}ms",timeQIS);
        LOG.info("Quotient of {}ms and {}ms is {}", timeQS,timeQIS,((double)timeQS/(double)timeQIS));

        startTime = System.currentTimeMillis();
        Sort.quickInsertionSort(a3);
        timeQS = System.currentTimeMillis() - startTimeQS;
        LOG.info("quickSort: Array 3 sorted in {}ms",timeQS);

        startTime = System.currentTimeMillis();
        Sort.quickInsertionSort(b3);
        timeQIS = System.currentTimeMillis() - startTimeQIS;
        LOG.info("quickInsertionSort: Array 3 sorted in {}ms",timeQIS);
        LOG.info("Quotient of {}ms and {}ms is {}", timeQS,timeQIS,((double)timeQS/(double)timeQIS));
    }

    @Test
    void testHeapSort(){
//        Integer[] a1 = Sort.randomInts(500);
        Integer[] a1 = Sort.randomInts(500_000);
        LOG.info("####### testHeapSort ########");
//        LOG.info(Arrays.toString(a1));
        startTime = System.currentTimeMillis();

        Sort.heapSort(a1);
        time = System.currentTimeMillis() - startTime;

        LOG.info("HeapSort: Array sorted in {}ms",time);
//        LOG.info(Arrays.toString(a1));
    }

    /**
     * Fixed value array, easier to test/debug.
     */
    @Test
    void testFixedDevArray(){
        String s = "CKWAMN";
        Character[] array = new Character[5];
//        array[0] = 13;
//        array[1] = 73;
//        array[2] = 144;
//        array[3] = 52;
//        array[4] = 2;
        array[0] = s.charAt(0);
        array[1] = s.charAt(1);
        array[2] = s.charAt(2);
        array[3] = s.charAt(3);
        array[4] = s.charAt(4);
        LOG.info("––––––– Fixed Array send to Method ––––––––––");
        LOG.info(Arrays.toString(array));

        startTime = System.currentTimeMillis();
        Sort.insertionSort(array);
        time = System.currentTimeMillis() - startTime;

        LOG.info("––––––– Fixed Array sorted in {}ms ––––––––––",time);
        LOG.info(Arrays.toString(array));
    }
}
