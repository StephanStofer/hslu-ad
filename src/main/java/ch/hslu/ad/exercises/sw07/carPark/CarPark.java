/**
 * carPark-Class represents
 *
 * @author stofers
 * @Version V1.0
 * @date 2019-04-02
 */
package ch.hslu.ad.exercises.sw07.carPark;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class CarPark {

    private static final Logger LOG = LogManager.getLogger(CarPark.class);

    private final int parkId;
    private ArrayList<Car> carPark;
    private int slotsTaken;


    public CarPark(int parkId, int size) {
        this.parkId = parkId;
        this.carPark = new ArrayList<>(size);
    }

    public synchronized boolean enter(Car car){
        if (slotsTaken < carPark.size()){
            this.slotsTaken++;
            car.setFoundSlot();
            this.carPark.add(car);
            return true;
        }
        else new IllegalStateException("All Slots are taken");
        return false;
    }

    public synchronized void leave(Car car){
        this.slotsTaken--;
        this.carPark.remove(car);

    }

    public synchronized int getFreeSlots(){
        return carPark.size() - this.slotsTaken;
    }

    @Override
    public String toString() {
        return ("Parking " +parkId+" with " +carPark.size() + " slots, with " +getFreeSlots() + " free slots.");
    }
}
