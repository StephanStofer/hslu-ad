package ch.hslu.ad.exercises.sw07.carParkMau;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;

/**
 * @author Maurizio Hostettler
 */
public class Car implements Runnable {

    private static final Logger LOG = LogManager.getLogger(Car.class);

    private final int PARK_TIME = randomInt(1000, 4000);
    private final int CAR_ID;
    private boolean isIntelligent;
    private boolean parkFound;
    private int waitTime;

    private List<Carpark> parklist;

    // Type 1 car waits at the first Parkinglot until there's an empty space.
    public Car(int id, List<Carpark> parklist, int waitTime, boolean isIntelligent) {
        this.CAR_ID = id;
        this.parklist = parklist;
        this.waitTime = waitTime;
        this.isIntelligent = isIntelligent;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(randomInt(200, 600)); //Time to drive to the parkinglot

            if (isIntelligent) {
                int bestpark = parklist.indexOf(Collections.max(parklist));
                if (parklist.get(bestpark).acquire(0) == true) {
                    parkOk(parklist.get(bestpark));
                }
            } else {
                for (Carpark park : parklist) {
                    if (park.acquire(waitTime) == true) {
                        parkOk(park);
                        break;
                    } else {
                        parkFound = false;
                        LOG.warn("Car " + CAR_ID + ": I have enough... I go for the next parkinglot");
                    }
                }
            }
            if (!parkFound) {
                LOG.error("Car " + CAR_ID + ": This was the last one... the heck I'm going home");
            }

        } catch (InterruptedException e) {
            System.err.println("interruptedException" + e);
        }
    }


    //
    private void parkOk(Carpark park) throws InterruptedException {
        LOG.debug("Car " + CAR_ID + ": Yay I got a parkinglot! I'm gona stay here for " + PARK_TIME);
        parkFound = true;
        Thread.sleep(PARK_TIME);
        park.release();
    }

    private static int randomInt(int min, int max) {
        return (int) ((Math.random() * ((max - min) + 1)) + min);
    }

}
