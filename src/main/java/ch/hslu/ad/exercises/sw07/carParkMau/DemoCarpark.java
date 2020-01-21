package ch.hslu.ad.exercises.sw07.carParkMau;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Maurizio Hostettler
 */
public class DemoCarpark {

    private static final Logger LOG = LogManager.getLogger(DemoCarpark.class);

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService executor = Executors.newCachedThreadPool();
        List<Future<?>> futureList = new ArrayList<>();
        List<Carpark> parkList = new ArrayList<>();

        int numberOfCars = 1000;

        //Create n Parks
        for (int i = 0; i < 3; i++) {
            parkList.add(new Carpark("Park: " + (i + 1), 100));
        }


        // Create equal amount of different wait szenarios
        // -1 = wait forever, 0 = no wait, 1001 is +- 1001ms
        int[] waitSzenario = {-1, 0, 1001};

        // Create a bunch of callable-cors-future items and submit them to the executor
        for (int i = 0; i < numberOfCars; i++) {
            Runnable run = new Car(i, parkList, waitSzenario[i % 3], getIntelligence(i));
            Future<?> future = executor.submit(run);
            futureList.add(future);
            Thread.sleep(5);
        }

        // wait until all threads stopped
        for (Future<?> entries : futureList) {
            entries.get();
        }

        LOG.info("All cars left - terminating ");
        executor.shutdown();

        Statistics.printStats(numberOfCars, parkList);
    }


    private static boolean getIntelligence(int i) {
        return i % 11 == 0;
    }
}
