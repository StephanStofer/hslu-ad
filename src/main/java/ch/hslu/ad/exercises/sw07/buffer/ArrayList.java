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
package ch.hslu.ad.exercises.sw07.buffer;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Puffer mit einer begrenzten Kapazität. Der Puffer ist thread sicher.
 * @param <T> Elememente des Buffers
 */
public class ArrayList<T> {

    private final BlockingDeque<T> deque;
    private final Semaphore putSema;
    private final Semaphore takeSema;
    private AtomicInteger capacity;
    private AtomicInteger count = new AtomicInteger(0);
    private AtomicInteger failed = new AtomicInteger(0);
    /**
     * Erzeugt einen Puffer mit bestimmter Kapazität.
     * @param n Kapazität des Puffers
     */
    public ArrayList(final int n) {
        deque = new LinkedBlockingDeque<>(n);
        capacity = new AtomicInteger(n);
        putSema = new Semaphore(n);
        takeSema = new Semaphore(0);
    }

    /**
     * Fügt ein Element in den Puffer ein, wenn dies möglich ist, wenn nicht muss der Schreiber
     * warten.
     * @param elem Element zum Einfügen.
     * @throws InterruptedException falls das Warten unterbrochen wird.
     */
    public void put(final T elem) throws InterruptedException {
        putSema.acquire();
        synchronized (deque) {
            deque.addFirst(elem);
            increment();
        }
        takeSema.release();    }

    public void increment() {
        count.incrementAndGet();
    }

    public void decrement() {
        count.decrementAndGet();
    }

    public int getCount() {
        return count.get();
    }

    public int getCapacity(){
        return capacity.get();
    }

    public int getFailed() {
        return failed.get();
    }

    /**
     * Fügt ein Element am Anfang in den Puffer ein, wenn dies möglich ist, wenn nicht muss der
     * Schreiber warten.
     * @param elem Element zum Einfügen.
     * @throws InterruptedException falls das Warten unterbrochen wird.
     */
    public void push(final T elem) throws InterruptedException {
        putSema.acquire();
        synchronized (deque) {
            deque.addFirst(elem);
            increment();
        }
        takeSema.release();
    }

    /**
     * Ein Element T speichern oder nach einem Timeout abbrechen. Falls der Puffer voll ist, warten
     * bis ein Platz frei wird.
     * @param elem zu speicherndes Element.
     * @param millis Timeout bis zum Abbruch.
     * @return true, wenn Element gespeichert wurde, false, wenn Timeout eingetreten ist.
     * @throws InterruptedException falls das Warten unterbrochen wird.
     */
    public boolean put(final T elem, final long millis) throws InterruptedException {
        while (full()){
            this.wait(millis);
            if (full()){
                return false;
            }
        }
        push(elem);
        return true;
    }

    /**
     * Liest und entfernt ein Element aus dem Puffer, wenn dies möglich ist, wenn nicht muss der
     * Leser warten.
     * @return gelesenes Element.
     * @throws InterruptedException falls das Warten unterbrochen wird.
     */
    public T get() throws InterruptedException {
        takeSema.acquire();
        T elem;
        synchronized (deque) {
            elem = deque.removeLast();
            decrement();
        }
        putSema.release();
        return elem;
    }

    /**
     * Liest und entfernt ein Element am Anfang aus dem Puffer, wenn dies möglich ist, wenn nicht
     * muss der Leser warten.
     * @return gelesenes Element.
     * @throws InterruptedException falls das Warten unterbrochen wird.
     */
    public T front() throws InterruptedException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Liest und entfernt ein Element am Ende aus dem Puffer, wenn dies möglich ist, wenn nicht muss
     * der Leser warten.
     * @return gelesenes Element.
     * @throws InterruptedException falls das Warten unterbrochen wird.
     */
    public T back() throws InterruptedException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Liest und entfernt ein Element aus dem Puffer, wenn dies innert einer definierten Zeit
     * möglich ist.
     * @param millis Anzahl Millisekunden, bis das Warten beendet wird.
     * @return gelesenes Element wenn erfolgreich, sonst null.
     * @throws InterruptedException falls das Warten unterbrochen wird.
     */
    public T get(final long millis) throws InterruptedException {
        boolean acquire = takeSema.tryAcquire(millis, TimeUnit.MILLISECONDS);
        if (acquire) {
            return get();
        } else throw new InterruptedException();
    }

    /**
     * Liefert true, wenn der Puffer leer ist.
     * @return true, wenn der Puffer leer ist.
     */
    public boolean empty() {
        return deque.isEmpty();
    }

    /**
     * Liefert true, wenn der Puffer voll ist.
     * @return true, wenn der Puffer voll ist.
     */
    public boolean full() {
        return getCount() == getCapacity();
    }

    /**
     * Liefert die Anzahl Elemente im Puffer.
     * @return Anzahl Elemente im Puffer.
     */
    public int size() {
        return deque.size();
    }
}
