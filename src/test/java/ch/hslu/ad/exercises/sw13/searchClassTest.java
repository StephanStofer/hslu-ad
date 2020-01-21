package ch.hslu.ad.exercises.sw13;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.junit.jupiter.api.Assertions.*;

class searchClassTest {

    private static final Logger LOG = LogManager.getLogger(searchClassTest.class);
    String searchPattern = "Ananas";
    String searchString1 = "Ananas";
    String searchString2 = "Ich heisse Anna und esse Ananas sehr gerne.";
    String searchString3 = "Kofi Anan Nein, Annan ist eine Legende. Wie Anna mag auch er Ananas";
    String searchString4 = "Dieser Satz hat nichts mit Früchten zu tun.";

    @Test
    void testStateSearch() {
        assertEquals(0,SearchClass.stateSearch(searchString1));
        assertEquals(25,SearchClass.stateSearch(searchString2));
        assertEquals(61,SearchClass.stateSearch(searchString3));
        assertEquals(-1,SearchClass.stateSearch(searchString4));
    }

    @Test
    void testInitNext(){
        String searchString1 = "EISBEIN";
        String searchString2 = "ANANAS";
        assertArrayEquals(new int[]{-1,0,0,0,0,1,2},SearchClass.initNext(searchString1));
        assertArrayEquals(new int[]{-1,0,0,1,2,3},SearchClass.initNext(searchString2));
    }

    @Test
    void testQuickSearch(){
        assertEquals(0,SearchClass.quickSearch(searchString1,searchPattern));
        assertEquals(25,SearchClass.quickSearch(searchString2,searchPattern));
        assertEquals(61,SearchClass.quickSearch(searchString3,searchPattern));
        assertEquals(-1,SearchClass.quickSearch(searchString4,searchPattern));
    }

    @Test
    void testQuickSearchPerformance(){
        long startTime, time;
        int testRuns = 10;
        int position;
        String searchPatternTXT = "Ananas"; // epicurean barbarity, Emphysema, Castration, Ananas
        String searchString = null;
        try {
            searchString = whenReadFileContentsIntoString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 1; i <= testRuns; i++){
            startTime = System.currentTimeMillis();
                position = SearchClass.quickSearch(searchString, searchPatternTXT);
                time = System.currentTimeMillis();
                LOG.info("Testrun {}: {} (pos. {}) found in {}ms", i, searchPatternTXT, position, time - startTime);
        }
    }

    /**
     * Read File and save content into a String.
     */
    private String whenReadFileContentsIntoString()
            throws IOException {
        String file = "/Users/stofers/Dropbox/__Studium__/BScI/AD/ad_excercises/src/main/java/ch/hslu/ad/exercises/sw13/animalCastration.txt";
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder builder = new StringBuilder();
        String currentLine = reader.readLine();
        while (currentLine != null) {
            builder.append(currentLine);
            builder.append("n");
            currentLine = reader.readLine();
        }
        reader.close();

        return builder.toString();
    }
}