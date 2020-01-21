/**
 * CarParkSimulation-Class represents
 *
 * @author stofers
 * @Version V1.0
 * @date 2019-04-02
 */
package ch.hslu.ad.exercises.sw07.carPark;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CarParkSimulation {

    private static final Logger LOG = LogManager.getLogger(CarParkSimulation.class);

    private static final int CAR_PARKS = 3;
    private static final int CARS = 1000;

    private ArrayList<CarPark> carParks = new ArrayList<>(CAR_PARKS);
    private ArrayList<Car> cars = new ArrayList<>(CARS);
    private List<Future<?>> futureList = new ArrayList<>();

    private int succeeded = 0;
    private int failed = 0;

    public CarParkSimulation() {

    }

    public void runSimulation() throws Exception {
        for (int i = 1; i <= CAR_PARKS; i++){
            carParks.add(new CarPark(i,i * 200));
        }

        for (int j = 1; j <= CARS; j++){
            cars.add(new Car(j, carParks));
        }

        final ExecutorService executor = Executors.newFixedThreadPool(CARS);

        for (Car car : cars
             ) {
            Runnable run = car;
            Future<?> future = executor.submit(run);
            futureList.add(future);
            Thread.sleep(5);

        }

        for (Future<?> entries: futureList
             ) {
            entries.get();
        }


        LOG.info("executed");



    }


    public static void main(String[] args) throws Exception {
        new CarParkSimulation().runSimulation();

    }

}
