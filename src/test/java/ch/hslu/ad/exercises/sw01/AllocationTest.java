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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

/**
 * Testfälle für {@link Allocation}.
 */
class AllocationTest {

    /**
     * Testfall für {@link Allocation#getAddress()}.
     */
    @Test
    void testGetAddress() {
        assertEquals(10, new Allocation(10, 100).getAddress());
    }

    /**
     * Testfall für {@link Allocation#getSize()}.
     */
    @Test
    void testGetSize() {
        assertThat(new Allocation(50, 20).getSize()).isEqualTo(20);
    }

    /**
     * Testfälle für {@link Allocation#toString()}.
     */
    @Test
    void testToString() {
        assertThat(new Allocation(5, 6).toString()).contains("Allocation").contains("5").contains("6");
    }

    /**
     * Test auf Equals-Contract.
     */
    @Test
    void testEqualsContract() {
        EqualsVerifier.forClass(Allocation.class).withIgnoredFields("size").verify();
    }
}
