package ch.hslu.ad.exercises.sw04;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Stack;

class PerformanceTest {

    private static final Logger LOG = LogManager.getLogger(PerformanceTest.class);

    private static ArrayList<Integer> testElements = new ArrayList<>();
    private final int numberOfObjects = 1_000_000;

    private static void fillTestElements(int numberOfObjects){
        for(var i = 0; i < numberOfObjects; i++){
            testElements.add(i);
        }
    }

    @Test
    void testPerformance(){
        fillTestElements(numberOfObjects);

        myStack<Integer> myStack = new myStack<>(numberOfObjects);
        Stack<Integer> stack = new java.util.Stack<>();
        ArrayDeque<Integer> deque = new ArrayDeque<>(numberOfObjects);
        HashTableArray<Integer> hashTableArray = new HashTableArray<>(numberOfObjects);
        HashTableBuckets<Integer> hashTableBuckets = new HashTableBuckets<>(10);

        // regular java stack
        long startTime = System.currentTimeMillis();
        for(Integer element : testElements){
            stack.push(element);
        }
        for(int i = 0; i < numberOfObjects; i++){
            stack.pop();
        }

        long time = System.currentTimeMillis() - startTime;

        LOG.info("Java Stack took: {}ms" ,time);


        // my stack implementation
        startTime = System.currentTimeMillis();

        for(Integer element : testElements){
            myStack.push(element);
        }
        for(int i = 0; i < numberOfObjects; i++){
            myStack.pop();
        }

        time = System.currentTimeMillis() - startTime;

        LOG.info("My Stack took: {}ms" ,time);

        // deque ArrayDeque
        startTime = System.currentTimeMillis();

        for(Integer element : testElements){
            deque.push(element);
        }
        for(int i = 0; i < numberOfObjects; i++){
            deque.pop();
        }

//        time = System.currentTimeMillis() - startTime;
//
//        LOG.info("Deque took: {}ms" ,time);
//
//        // HashTable Array
//        startTime = System.currentTimeMillis();
//
//        for(Integer element : testElements){
//            hashTableArray.insert(element);
//        }
//        for(int i = 0; i < numberOfObjects; i++){
//            hashTableArray.getElement(i);
//        }
//
//        time = System.currentTimeMillis() - startTime;
//
//        LOG.info("HashTable Array took: {}ms" ,time);
//
//        // HashTable Bucket Array
//        startTime = System.currentTimeMillis();
//
//        for(Integer element : testElements){
//            hashTableBuckets.insert(element);
//        }
//        for(int i = 0; i < numberOfObjects; i++){
//            hashTableBuckets.getElement(i);
//        }
//
//        time = System.currentTimeMillis() - startTime;
//
//        LOG.info("HashTable Bucket Array took: {}ms" ,time);
    }
}