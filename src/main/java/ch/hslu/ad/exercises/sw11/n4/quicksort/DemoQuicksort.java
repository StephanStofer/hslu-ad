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

import ch.hslu.ad.exercises.sw11.n41.init.RandomInitTask;
import ch.hslu.ad.exercises.sw11.n41.sum.SumTask;
import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Vergleich von verschiedenen Quicksort-Implementationen.
 */
public final class DemoQuicksort {

    private static final Logger LOG = LogManager.getLogger(DemoQuicksort.class);

    /**
     * Privater Konstruktor.
     */
    private DemoQuicksort() {
    }

    /**
     * Main-Demo.
     *
     * @param args not used.
     */
    public static void main(final String[] args) {
//        final int size = 1000;
        final int size = 300_000_000;
        long startTime, time;
        final Integer[] array = new Integer[size];
        final ForkJoinPool pool = new ForkJoinPool();
        RandomInitTask initTask = new RandomInitTask(array, 100);
        pool.invoke(initTask);
        SumTask sumTask = new SumTask(array);
        long result = pool.invoke(sumTask);
        LOG.info("Init. Checksum : " + result);
        final QuicksortTask sortTask = new QuicksortTask(array);
//        LOG.info(Arrays.toString(array));
        startTime = System.currentTimeMillis();
        pool.invoke(sortTask);
        time = System.currentTimeMillis()-startTime;
//        LOG.info(Arrays.toString(array));
        LOG.info("QuicksortTask  : {}ms",time);
        sumTask = new SumTask(array);
        result = pool.invoke(sumTask);
        LOG.info("Conc. Checksum : " + result);
        initTask = new RandomInitTask(array, 100);
        pool.invoke(initTask);
        sumTask = new SumTask(array);
        result = pool.invoke(sumTask);
        LOG.info("Init. Checksum : " + result);
        startTime = System.currentTimeMillis();
        QuicksortRecursive.quicksort(array);
        time = System.currentTimeMillis()-startTime;
        LOG.info("QuicksortRec.  : {}ms",time);
        sumTask = new SumTask(array);
        result = pool.invoke(sumTask);
        LOG.info("Recu. Checksum : " + result);
        initTask = new RandomInitTask(array, 100);
        pool.invoke(initTask);
        sumTask = new SumTask(array);
        result = pool.invoke(sumTask);
        LOG.info("Init. checksum : " + result);
        startTime = System.currentTimeMillis();
        Arrays.sort(array);
        time = System.currentTimeMillis()-startTime;
        LOG.info("Arrays.sort    : {}ms",time);
        sumTask = new SumTask(array);
        result = pool.invoke(sumTask);
        LOG.info("Sort checksum  : " + result);
    }
}
