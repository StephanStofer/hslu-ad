/**
 * Car-Class represents
 *
 * @author stofers
 * @Version V1.0
 * @date 2019-04-02
 */
package ch.hslu.ad.exercises.sw07.carPark;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.Callable;

public class Car implements Runnable {

    private static final Logger LOG = LogManager.getLogger(Car.class);
    private static final int MINPARKTIME = 50; //in milliseconds

    private final int carId;
    private CarTypeEnum typeEnum;
    private int parkTime; //in milliseconds
    private boolean foundSlot;

    private List<CarPark> parkList;

    public Car(int carId, List<CarPark> parkList, CarTypeEnum typeEnum, int parkTime) {
        this.carId = carId;
        this.parkList = parkList;
        this.typeEnum = typeEnum;
        if (parkTime >= MINPARKTIME){
            this.parkTime = parkTime;
        }
        else this.parkTime = MINPARKTIME;
    }

    public Car(int carId, List<CarPark> parkList){
        this.carId = carId;
        this.parkList = parkList;
        this.typeEnum = CarTypeEnum.getRandomTypeEnum();
        this.parkTime = getRandomInt(MINPARKTIME,200);
    }

    /**
     * Return a random Int-value in a specific Range ex: [5,10]. Method use {@link Math class}.
     * @param minRange interval start value
     * @param maxRange interval end value
     * @return Random int
     */
    private int getRandomInt(int minRange, int maxRange){
        return minRange + (int)(Math.random() * ((maxRange - minRange) + 1));
    }

    public void setFoundSlot() {
        this.foundSlot = true;
    }

    public boolean getFoundSlots(){
        return this.foundSlot;
    }

    @Override
    public void run() {
        for (CarPark carPark: parkList){
            if (carPark.getFreeSlots() != 0)
            carPark.enter(this);
        LOG.info("{} entred in {}", this, carPark.toString());
        }
    }

    @Override
    public String toString() {
        return "Car " +this.carId;
    }
}
