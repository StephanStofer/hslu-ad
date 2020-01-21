/*
 * Copyright 2018 Hochschule Luzern - Informatik.
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
package ch.hslu.ad.exercises.sw05.samples.workitem.display;

/**
 * Anzeige von Work Items.
 */
public final class DemoQueue {

    /**
     * Anzeige eines Work Items für eine Sekunde.
     *
     * @param args not used.
     * @throws InterruptedException wird hier nicht passieren.
     */
    public static void main(final String[] args) throws InterruptedException {
        DisplayQueue displayQueue = new DisplayQueue();
        for (int i = 1; i <= 5; i++) {
            displayQueue.enqueue("Schritt " + i);
        }
        displayQueue.startWorker();
    }
}
