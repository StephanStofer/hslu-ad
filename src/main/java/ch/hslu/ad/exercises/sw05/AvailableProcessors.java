/**
 * AvailableProcessors-Class represents
 *
 * @author stofers
 * @Version V1.0
 * @date 2019-03-19
 */
package ch.hslu.ad.exercises.sw05;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AvailableProcessors {

    private static final Logger LOG = LogManager.getLogger(AvailableProcessors.class);

    public AvailableProcessors() {

    }

    public static void main(String[] args) {
        // Anzahl der Prozessoren abfragen
        final int nr = Runtime.getRuntime().availableProcessors();
        LOG.info("Available processors " + nr);
        // Eigenschaften des main-AvailableProcessors
        final Thread self = Thread.currentThread();
        LOG.info("Name : " + self.getName());
        LOG.info("Priority : " + self.getPriority());
        LOG.info("ID : " + self.getId());
    }

}
