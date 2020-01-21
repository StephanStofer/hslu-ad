package ch.hslu.ad.exercises.sw07.carParkMau;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * @author Maurizio Hostettler
 */
public class Statistics {

    private static final Logger LOG = LogManager.getLogger(Statistics.class);

    public static void printStats(int totalCars, List<Carpark> parklist) {
        int nokCars;
        int okCars = 0;
        System.out.println();
        LOG.info("Park statistics:");

        for (Carpark park : parklist) {
            okCars += park.getTotalCars();
            LOG.info(park.parkName + " had " + park.getTotalCars() + " cars");
        }
        nokCars = totalCars - okCars;

        LOG.info("");
        LOG.info("Car statistics");
        LOG.info("released : " + totalCars + " cars");
        LOG.info("with lot : " + okCars);
        LOG.info("without  : " + nokCars);
    }
}