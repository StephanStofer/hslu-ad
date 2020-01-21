package ch.hslu.ad.exercises.sw12;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntegerCheckTest {

    @Test
    void testIsWordLanguage() {
        assertTrue(IntegerCheck.isWordLanguage("0"));
        assertTrue(IntegerCheck.isWordLanguage("010"));
        assertTrue(IntegerCheck.isWordLanguage("01110"));
        assertTrue(IntegerCheck.isWordLanguage("0111010"));
        assertTrue(IntegerCheck.isWordLanguage("0101110"));
        assertTrue(IntegerCheck.isWordLanguage("0101010"));
        assertFalse(IntegerCheck.isWordLanguage("1"));
        assertFalse(IntegerCheck.isWordLanguage("011"));
        assertFalse(IntegerCheck.isWordLanguage("00"));
        assertFalse(IntegerCheck.isWordLanguage("011100111"));
        assertFalse(IntegerCheck.isWordLanguage("01011110"));
    }
}