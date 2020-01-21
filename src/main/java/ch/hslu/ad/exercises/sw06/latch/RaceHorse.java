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
package ch.hslu.ad.exercises.sw06.latch;

import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Ein Rennpferd, das durch ein Startsignal losläuft. Nach einer zufälligen Zeit kommt es im Ziel
 * an.
 */
public final class RaceHorse implements Runnable {

    private static final Logger LOG = LogManager.getLogger(RaceHorse.class);
    private final Synch startSignal;
    private volatile Thread runThread;
    private final Random random;

    /**
     * Erzeugt ein Rennpferd, das in die Starterbox eintritt.
     * @param startSignal Starterbox.
     */
    public RaceHorse(Synch startSignal) {
        this.startSignal = startSignal;
        this.random = new Random();
    }

    @Override
    public void run() {
        runThread = Thread.currentThread();
        LOG.info("Rennpferd " + runThread.getName() + " geht in die Box.");
        try {
            startSignal.acquire();
            LOG.info("Rennpferd " + runThread.getName() + " laeuft los...");
            Thread.sleep(random.nextInt(3000));
            LOG.info("Rennpferd " + runThread.getName() + " ist im Ziel.");
        } catch (InterruptedException ex) {
            //LOG.fatal("{} Rennabbruch!!",Thread.currentThread().getName());
            LOG.debug("{} läuft zurück zur Box..", Thread.currentThread().getName());
        }
        Thread.currentThread().interrupt();
    }
}
