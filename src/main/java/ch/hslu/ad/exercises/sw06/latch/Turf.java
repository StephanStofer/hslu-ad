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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

/**
 * Eine Rennbahn für das Pferderennen.
 */
public final class Turf {

    private static final Logger LOG = LogManager.getLogger(Turf.class);

    /**
     * Privater Konstruktor.
     */
    private Turf() {
    }

    /**
     * Main-Demo.
     * @param args not used.
     */
    public static void main(final String[] args) {
        final Synch starterBox = new Latch();
        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 1; i < 16; i++) {
            Thread t = new Thread(new RaceHorse(starterBox), "Horse " + i);
            threads.add(t);
            t.start();
        }
        LOG.info("Start...");

        LOG.info("Race delayed ");
        starterBox.setTimeout(6000);

        starterBox.release();

        LOG.fatal("Race aborted!!");
        for (Thread t:threads
             ) {
            t.interrupt();

        }
    }
}
