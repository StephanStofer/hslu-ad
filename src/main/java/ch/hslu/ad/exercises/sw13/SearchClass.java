/**
 * SearchClass-Class represents
 *
 * @author stofers
 * @Version V1.0
 * @date 2019-05-19
 */
package ch.hslu.ad.exercises.sw13;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SearchClass {

    private static final Logger LOG = LogManager.getLogger(SearchClass.class);

    public SearchClass() {

    }

    /**
     * Durchsucht eine Zeichenkette mittels optimiertem Suchautomaten nach dem Pattern "Ananas".
     *
     * @param a Zeichenkette, die durchsucht wird.
     * @return Index der Fundstelle oder -1, falls Pattern in a nicht gefunden wurde.
     */
    public static int stateSearch(final String a) {
        int i = 0;        //index to string a
        int iFoundAt = 0; //index where first Char found
        String state = "noMatch"; // means "nothing found"
        final int notFound = -1;
        do {
            switch (state) {
                case "noMatch": //z0
                    if (a.charAt(i) == 65) {    // 65 = A
                        state = "A";
                        iFoundAt = i;
                    }
                    break;
                case "A": //z1
                    if (a.charAt(i) == 110) {    // 110 = n
                        state = "AN";
                    }
                    if (a.charAt(i) == 65) {
                        iFoundAt = i;
                    }
                    break;
                case "AN": //z2
                    if (a.charAt(i) == 97) {    // 97 = a
                        state = "ANA";
                    } else {
                        state = "noMatch";
                    }
                    break;
                case "ANA":
                    if (a.charAt(i) == 110) {    // 110 = n
                        state = "ANAN";
                    } else {
                        state = "A";
                    }
                    break;
                case "ANAN":
                    if (a.charAt(i) == 97) {    // 97 = a
                        state = "ANANA";
                    } else {
                        state = "AN";
                    }
                    break;
                case "ANANA":
                    if (a.charAt(i) == 115) {    // 115 = s
                        state = "ANANAS";
                    } else {
                        state = "ANA";
                    }
                    break;
            }
            i++;
        } while ((state != a) && (i < a.length()));
        if (state.equals("ANANAS")) {
            return (iFoundAt); // position of pattern start

        }
        return notFound;
    }

    /**
     * Erzeugt für das Pattern einen Musterautomaten.
     *
     * @param p Pattern, nach dem später gesucht werden soll. * @return Musterautomat in Form eines int-Arrays.
     */
    public static int[] initNext(final String p) {
        final int m = p.length();
        final int[] next = new int[m];
        int i = 0;
        int j = -1;
        next[0] = -1;
        // special value! (-1 = no reference to a following state)
        do {
            if ((j == -1) || (p.charAt(i) == p.charAt(j))) { // (j == -1) must be first operand!
                i++;
                j++;
                next[i] = j;
            } else {
                j = next[j];
            }
        } while (i < (m - 1));
        return next;
    }

    public static int kmpSearch(final String a, final String p) {
        final int n = a.length();
        final int m = p.length();
        int i = 0; // index to string a
        int j = 0; // index to pattern p respectively state

        // 1. generate next
        int[] next = initNext(p);
        // 2. search for p
        do {
            if ((j == -1) || (a.charAt(i) == p.charAt(j))) { // (j == -1) must be first
                i++; // process next character
                j++;
            } else {
                j = next[j]; // go to next state
            }
        } while ((j < m) && (i < n));

        if (j == m) {
            return (i - m);
        } else {
            return -1;
        }
    }

    /**
     * Durchsucht eine Zeichenkette mittels quickSearch.
     *
     * @param a Zeichenkette, die durchsucht wird.
     * @return Index der Fundstelle oder -1, falls Pattern in a nicht gefunden wurde.
     */
    public static int quickSearch(final String a, final String p) {
        final int n = a.length();
        final int m = p.length();
        final int range = 65_536; // -> Unicode-Range
        final int[] shift = new int[range];
        // init shift-array
        for (int i = 0; i < range; i++) {
            shift[i] = m + 1;
        }
        // overwrite fields according pattern
        for (int i = 0; i < m; i++) {
            shift[p.charAt(i)] = (m - i);
        }
        // search
        int i = 0; // index to string
        int j = 0; // index to pattern p
        do {
            if (a.charAt(i + j) == p.charAt(j)) { // match
                j++;
            } else { // mismatch
                if ((i + m) < n) { // a.charAt(i1+m) is not outside a
                    i += shift[a.charAt(i + m)]; // jump forward
                    j = 0;
                } else {
                    break; // (mismatch) && (no shift is possible)
                }
            }
        }
        while ((j < m) && ((i + m) <= n));
        // (pattern p not found) && (end of a not reached)
        if (j == m) {
            return i;
        // pattern found
        } else {
            return -1;
        // pattern not found
        }
    }
}