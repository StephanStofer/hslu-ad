/**
 * IntegerCheck-Class represents
 *
 * @author stofers
 * @Version V1.0
 * @date 2019-05-13
 */
package ch.hslu.ad.exercises.sw12;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IntegerCheck {

    private static final Logger LOG = LogManager.getLogger(IntegerCheck.class);

    public IntegerCheck() {

    }

    /**
     * Returns true if String starts with a zero. Can be followed by a grouped odd number of 1
     */
//    public static boolean isWordLanguage(final String string){
//        Pattern p = Pattern.compile("[0](1++0)*?");  // working, without odd 1's
////        Pattern p = Pattern.compile("(01*?)*?[0]{1}?");
//        Matcher m = p.matcher(string);
//
//        return m.matches();
//
//    }

    public static boolean isWordLanguage(final String string){
        if (Integer.parseInt(String.valueOf(string.charAt(0))) == 0 && Integer.parseInt(String.valueOf(string.charAt(string.length()-1))) == 0) {
            if (string.length()==1) return true;
            int count = 0;
            for (int i = 1; i < string.length()-1; i++) {
                int value = Integer.parseInt(String.valueOf(string.charAt(i)));
                if (value == 1) {
                    count++;
                }
                else {
                    if (Integer.parseInt(String.valueOf(string.charAt(i+1))) == 0) {
                        break;
                    }
                    else {
                        count = 0;
                    }
                }
            }
            return (count % 2 == 1);
        }
        return false;
    }



    public static void main(String[] args) {
        String input;
        Scanner scanner = new Scanner(System.in);
        LOG.debug("Initializing Scanner..");
        do {
            LOG.info("Geben Sie einen beliebige Zahl ein");
            input = scanner.next();
            try {
                LOG.info("Sie haben diesen Wert eingegeben: {}.", input );
                if (isWordLanguage(input)){
                    LOG.info("Er entspricht der DEA.");
                }
                else {
                    LOG.warn("Er entspricht nicht der DEA.");
                }

            } catch (NumberFormatException Scanner) {
                if (!input.equals("exit")) {
                    LOG.error("Geben Sie Zahlenwerte oder 'exit' zum Beenden ein");
                }
            }
        }
        while (!input.equals("exit"));
    }
}
