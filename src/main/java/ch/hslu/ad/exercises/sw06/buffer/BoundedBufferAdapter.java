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
package ch.hslu.ad.exercises.sw06.buffer;

import java.rmi.UnexpectedException;
import java.util.ArrayDeque;
import java.util.TimeZone;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Puffer nach dem First In First Out Prinzip mit einer begrenzten Kapazität.
 * Der Puffer ist thread sicher.
 *
 * @param <T> Elememente des Buffers
 */
public final class BoundedBufferAdapter<T> implements Buffer<T> {

    private final ArrayDeque<T> queue;
    private final Semaphore putSema;
    private final Semaphore takeSema;
    private AtomicInteger capacity;
    private AtomicInteger count = new AtomicInteger(0);

    /**
     * Erzeugt einen Puffer mit bestimmter Kapazität.
     *
     * @param n Kapazität des Puffers
     */
    public BoundedBufferAdapter(final int n) {
        capacity = new AtomicInteger(n);
        queue = new ArrayDeque<>(n);
        putSema = new Semaphore(n);
        takeSema = new Semaphore(0);
    }

    private void increment() {
        count.incrementAndGet();
    }

    private void decrement() {
        count.decrementAndGet();
    }

    private int getCount() {
        return count.get();
    }

    private int getCapacity(){
        return capacity.get();
    }


    @Override
    public void put(final T elem) throws InterruptedException {
        putSema.acquire();
        synchronized (queue) {
            queue.addFirst(elem);
            increment();
        }
        takeSema.release();
    }

    @Override
    public T get() throws InterruptedException {
        takeSema.acquire();
        T elem;
        synchronized (queue) {
            elem = queue.removeLast();
            decrement();
        }
        putSema.release();
        return elem;
    }

    @Override
    public boolean put(T elem, long millis) throws InterruptedException {
        while (full()){
            this.wait(millis);
            if (full()){
                return false;
            }
        }
        put(elem);
        return true;
    }

    @Override
    public T get(long millis) throws InterruptedException {
        boolean acquire = takeSema.tryAcquire(millis, TimeUnit.MILLISECONDS);
        T elem;
        if (acquire) {
            synchronized (queue) {
                elem = queue.removeLast();
            }
            decrement();
            putSema.release();
            return elem;
        } else throw new InterruptedException();
    }

    @Override
    public T first() throws InterruptedException {
        takeSema.acquire();
        T first;
        synchronized (queue){
            first = queue.removeFirst();
            decrement();
        }
        putSema.release();
        return first;
    }

    @Override
    public T last() throws InterruptedException {
        return get();
    }

    @Override
    public boolean empty() {
        return queue.isEmpty();
    }

    @Override
    public boolean full() {
        return getCount() == getCapacity();
    }

    @Override
    public int size() {
        return queue.size();
    }
}
