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
package ch.hslu.ad.exercises.sw11.n4.mergesort;

import ch.hslu.ad.exercises.sw11.n41.init.RandomInitTask;
import ch.hslu.ad.exercises.sw11.n41.sort.SortTask;
import ch.hslu.ad.exercises.sw11.n41.sum.SumTask;

import java.util.TimeZone;
import java.util.concurrent.ForkJoinPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Performance Vergleich der Mergesort-Implementation.
 */
public final class DemoMergesort {

    private static final Logger LOG = LogManager.getLogger(DemoMergesort.class);

    /**
     * Privater Konstruktor.
     */
    private DemoMergesort() {
    }

    /**
     * Main-Demo.
     *
     * @param args not used.
     */
    public static void main(final String[] args) {
        final int size = 100_000_000;
        final int tests = 3;
        long averageTimeMST = 0;
        long averageTimeST = 0;
        for (int i = 1; i <= tests; i++) {
            LOG.info("Testrun: {}",i);
            final Integer[] array = new Integer[size];
            final ForkJoinPool pool = new ForkJoinPool();
            RandomInitTask initTask = new RandomInitTask(array, 100_000_000);
            pool.invoke(initTask);
            SumTask sumTask = new SumTask(array);
            long result = pool.invoke(sumTask);
            LOG.info("Init. Checksum : " + result);
            final MergesortTask sortTask = new MergesortTask(array);
            long startTime, time;
            startTime = System.currentTimeMillis();
            pool.invoke(sortTask);
            time = System.currentTimeMillis() - startTime;
            averageTimeMST = averageTimeMST + time;
            LOG.info("Conc.Mergesort with Threshold {} : {}ms", MergesortTask.getTHRESHOLD(), time);
            sumTask = new SumTask(array);
            result = pool.invoke(sumTask);
            LOG.info("Merge Checksum : " + result);
            initTask = new RandomInitTask(array, 100_000_000);
            pool.invoke(initTask);
            sumTask = new SumTask(array);
            result = pool.invoke(sumTask);
            LOG.info("Init. checksum : " + result);
            startTime = System.currentTimeMillis();
            MergesortRecursive.mergeSort(array);
            time = System.currentTimeMillis() - startTime;
            averageTimeST = averageTimeST + time;
            LOG.info("MergesortRec. with Threshold {} : {}ms", SortTask.getTHRESHOLD(), time);
            sumTask = new SumTask(array);
            result = pool.invoke(sumTask);
            LOG.info("Sort checksum  : " + result);

            LOG.info("– – – – – – – – – – – – – – – – – –");
        }
            LOG.info("MergeSort-Average : {}ms", averageTimeMST/tests);
            LOG.info("MergeSortRec-Average : {}ms", averageTimeST/tests);
    }
}
