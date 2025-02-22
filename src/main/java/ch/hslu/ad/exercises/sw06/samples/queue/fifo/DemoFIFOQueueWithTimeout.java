/*
 * Copyright 2019 Hochschule Luzern - Informatik.
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
package ch.hslu.ad.exercises.sw06.samples.queue.fifo;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Demonstration der BoundedFIFOQueue mit n Producer und m Consumer.
 */
public final class DemoFIFOQueueWithTimeout {

    private static final Logger LOG = LogManager.getLogger(DemoFIFOQueueWithTimeout.class);

    /**
     * Privater Konstruktor.
     */
    private DemoFIFOQueueWithTimeout() {
    }

    /**
     * Main-Demo.
     *
     * @param args not used.
     * @throws InterruptedException wenn Thread unterbrochen wird.
     */
    public static void main(final String[] args) throws InterruptedException {
        final Random random = new Random();
        final int nPros = 3;
        final ProducerWithTimeout[] producers = new ProducerWithTimeout[nPros];
        final ThreadGroup prosGroup = new ThreadGroup("Producer-Threads");
        final int mCons = 8;
        final Consumer[] consumers = new Consumer[mCons];
        final ThreadGroup consGroup = new ThreadGroup("Consumer-Threads");
        final BoundedFIFOQueue<Integer> queue = new BoundedFIFOQueue<>(50);
        for (int i = 0; i < nPros; i++) {
            producers[i] = new ProducerWithTimeout(queue, random.nextInt(10000));
            new Thread(prosGroup, producers[i], "Prod  " + (char) (i + 65)).start();
        }
        for (int i = 0; i < mCons; i++) {
            consumers[i] = new Consumer(queue);
            new Thread(consGroup, consumers[i], "Cons " + (char) (i + 65)).start();
        }
        while (prosGroup.activeCount() > 0) {
            Thread.yield();
        }
        TimeUnit.MILLISECONDS.sleep(100);
        consGroup.interrupt();
        int sumPros = 0;
        for (int i = 0; i < nPros; i++) {
            LOG.info("Prod " + (char) (i + 65) + " = " + producers[i].getSum());
            sumPros += producers[i].getSum();
        }
        int sumCons = 0;
        for (int i = 0; i < mCons; i++) {
            LOG.info("Cons " + (char) (i + 65) + " = " + consumers[i].getSum());
            sumCons += consumers[i].getSum();
        }
        LOG.info(sumPros + " = " + sumCons);
    }
}
