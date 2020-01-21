/**
 * SimpleHashTable-Class represents
 *
 * @author stofers
 * @Version V1.0
 * @date 2019-03-12
 */
package ch.hslu.ad.exercises.sw04;

import ch.hslu.ad.exercises.sw01.Allocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class SimpleHashTable {

    private static final Logger LOG = LogManager.getLogger(SimpleHashTable.class);

    private HashTableArray<Allocation> hashTableArray;
    private int quantity;

    public SimpleHashTable(int quantity) {
        this.quantity = quantity;
        hashTableArray = new HashTableArray<>(quantity);
    }

    public void createArrayObjects(int quantity){
        for (int i = 0; i <= quantity; i++){
            hashTableArray.insert(new Allocation(new Random().nextInt(500),new Random().nextInt()));
        }
    }

    public void addObject(Object object){
        hashTableArray.insert(object);
    }


}
