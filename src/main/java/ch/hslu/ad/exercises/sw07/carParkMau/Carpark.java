package ch.hslu.ad.exercises.sw07.carParkMau;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Maurizio Hostettler
 */
public class Carpark implements Comparable<Carpark> {

    private static final Logger LOG = LogManager.getLogger(Carpark.class);

    private volatile int freeParkingLots; //Semaphore
    private volatile int waitingCarCount;
    private final int totalParkingLots;
    public final String parkName;
    private int totalCars = 0;


    /**
     * Carpark acts as a Semaphore and there will bi multiple of them.
     */
    public Carpark(final String parkName, final int parkinglots) throws IllegalArgumentException {
        if (parkinglots < 0) {
            throw new IllegalArgumentException("are you sure you know what you are doing?");
        }
        this.totalParkingLots = parkinglots;
        this.freeParkingLots = parkinglots;
        this.parkName = parkName;

        LOG.debug("Carpark " + this.parkName + " with " + parkinglots + " parkinglots created");
    }

    public synchronized boolean acquire(int timeout) throws InterruptedException {
        if (freeParkingLots == 0) {
            waitingCarCount++;
            LOG.debug(parkName + " No free parkinglots. Waiting cars: " + waitingCarCount);
            if (timeout == -1) {
                this.wait();
            } else if (timeout > 0) { // this.wait(0) is the same as this.wait()
                this.wait(timeout);
            }
            waitingCarCount--;
        }
        if (freeParkingLots > 0) {
            freeParkingLots--;
            LOG.debug(parkName + ": Parkinglot ocupied. Available lots: " + freeParkingLots);
            return true;
        } else {
            return false;
        }

    }

    public synchronized void release() {
        if (freeParkingLots < totalParkingLots) {
            freeParkingLots++;
        }
        this.notify();
        totalCars++;
        LOG.debug(parkName + ": Parkinglot released. Empty lots: " + freeParkingLots);
    }

    public int getTotalCars() {
        return totalCars;
    }

    @Override
    public int compareTo(Carpark o) {
        return Integer.compare(this.freeParkingLots, o.freeParkingLots);
    }

    int getFreeParkingLots() {
        return freeParkingLots;
    }
}


