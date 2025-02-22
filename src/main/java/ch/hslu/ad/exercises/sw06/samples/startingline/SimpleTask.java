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
package ch.hslu.ad.exercises.sw06.samples.startingline;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Task macht nichts besonders, nur warten, bis er losgelassen wird.
 */
public class SimpleTask implements Runnable {

    private static final Logger LOG = LogManager.getLogger(SimpleTask.class);
    private final StartingLine line;
    private volatile Thread runThread;

    /**
     * Erzeugt eine einfache Aufgabe, die an einem Synchronisationobjekt wartet,
     * bis weiterarbeiten kann.
     *
     * @param line Synchronisationojekt.
     */
    public SimpleTask(final StartingLine line) {
        this.line = line;
    }

    @Override
    public void run() {
        runThread = Thread.currentThread();
        LOG.info(runThread.getName() + " halted...");
        try {
            line.halt();
            LOG.info(runThread.getName() + " released...");
        } catch (InterruptedException ex) {
            LOG.debug(runThread.getName() + " interrupted...");
        }
    }

}
