/**
 * TheSetInterface-Class represents
 *
 * @author stofers
 * @Version V1.0
 * @date 2019-06-03
 */
package ch.hslu.ad.exercises.JavaTrail.SetInterface;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class TheSetInterface {

    private static final Logger LOG = LogManager.getLogger(TheSetInterface.class);

    public TheSetInterface() {

    }

    public static void main(String[] args) {
        String[] stringArray = {"i", "came", "i", "saw", "i", "left"};


        Set<String> distinctWords = Arrays.asList(stringArray).stream()
                .collect(Collectors.toSet());
        System.out.println(distinctWords.size()+
                " distinct words: " +
                distinctWords);



        Set<String> s = new TreeSet<>();
        for (String a : stringArray)
            s.add(a);
        System.out.println(s.size() + " distinct words: " + s);
    }
}
