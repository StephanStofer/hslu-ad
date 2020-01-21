/*
 * Copyright 2019 Roland Gisler, HSLU Informatik, Switzerland.
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
package ch.hslu.ad.exercises.sw01;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

/**
 * Modell für einen Speicherbereich. Immutable implementiert.
 */
public final class Allocation {
    private static final Logger LOG = LogManager.getLogger(Allocation.class);

    private final int size;
    private final int address;

    /**
     * Erzeugt einen Block Addresse und Grösse.
     * @param address Startadresse.
     * @param size Grösse.
     */
    public Allocation(final int address, final int size) {
        this.address = address;
        this.size = size;
    }

    /**
     * Liefert die Startadresse.
     * @return Startadresse.
     */
    public int getAddress() {
        return this.address;
    }

    /**
     * Liefert die Grösse.
     * @return Grösse.
     */
    public int getSize() {
        return this.size;
    }

    /*
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Allocation)) {
            return false;
        }
        final Allocation alloc = (Allocation) other;
        LOG.debug("Just used equals()");
        return this.address == alloc.address;
    }

    /*
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        LOG.debug("Just used hashCode()");
        return Objects.hashCode(this.address);
    }

    /*
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Allocation[Address:" + this.address + "; Size:" + this.size + "]";
    }
}
