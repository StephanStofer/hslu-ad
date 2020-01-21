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

import java.util.TreeMap;

/**
 * Eine Synchronisationshilfe, die es ermöglicht, einen oder mehrere Threads warten zu lassen, bis
 * diese durch andere Threads aufgeweckt werden. Latches sperren so lange, bis sie einmal ausgelöst
 * werden. Danach sind sie frei passierbar.
 */
public class Latch implements Synch {

    private static final Logger LOG = LogManager.getLogger(Latch.class);

    //Timeout in ms
    private int timeout;
    private volatile boolean lock;

    public Latch(){
        this.timeout = 0;
        this.lock = true;
    }


    @Override
    public void acquire() throws InterruptedException {
        synchronized (this){
            try {
                LOG.info("{} Acquire and wait", Thread.currentThread().getName());
                this.wait();
                //LOG.info("go!");
            } catch (InterruptedException iex){
                LOG.error("Abort!! ",iex.getMessage());
            }
        }
    }

    @Override
    public void release() {
        if (timeout > 0){
            try {
                LOG.warn("Race delayed");
                Thread.sleep(timeout);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        synchronized (this){
            this.lock = false;
            this.notifyAll();
            LOG.info("{} released", Thread.currentThread().getName());
        }
    }

    @Override
    public void setTimeout(int millisec) {
        if (millisec > 0){
            this.timeout = millisec;
        }
        else LOG.error("Value not permitted");
    }

}
