package ch.hslu.ad.exercises.sw07.carParkMau;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;

/**
 * @author Maurizio Hostettler
 */
public class CarparkComperator implements Comparator<Carpark> {

    private static final Logger LOG = LogManager.getLogger(CarparkComperator.class);

    @Override
    public int compare(Carpark park1, Carpark park2) {
        return Integer.compare(park1.getFreeParkingLots(), park2.getFreeParkingLots());
    }
}
