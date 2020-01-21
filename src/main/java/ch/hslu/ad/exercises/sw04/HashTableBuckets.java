/**
 * HashTableArray-Class represents
 *
 * @author stofers
 * @Version V1.0
 * @date 2019-03-12
 */
package ch.hslu.ad.exercises.sw04;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class HashTableBuckets<T> implements HashTableBucketsInterface<T>{

    private static final Logger LOG = LogManager.getLogger(HashTableBuckets.class);

    private static final int DEFAULT_CAP = 10;
    private static final float LOAD_FACTOR = 1.5f;

    private int size = 0;
    private ArrayList<T>[] arrayLists;

    public HashTableBuckets(int size) {
            this.arrayLists = new ArrayList[Math.round(size*LOAD_FACTOR)];
        for (int i = 0; i < arrayLists.length; i++) {
            arrayLists[i] = new ArrayList<T>();
        }
    }

    /**
     * private Method to calculate Index position.
     * @param object
     * @return  int
     */
    private int calcIndex(T object){
        int hash = object.hashCode() % 10;
        LOG.debug("Calculated Index {} for object {}",hash,object.toString());
        return hash;
    }

    /**
     * Returns True if Object already exists.
     * @param object
     * @return boolean
     */
    private boolean containsObject(T object){
        int index = calcIndex(object);
        if (size() > 0 && index < arrayLists.length)
            return arrayLists[index].contains(object);
        else {return false;}
    }

    /**
     * {@inheritDoc}
     * checks first if object is in list.
     */
    @Override
    public boolean remove(T object){
        int index = calcIndex(object);
        if (containsObject(object)){
                arrayLists[index].remove(object);
                LOG.debug("Removed {}", object.toString());
                size--;
                return true;
            }
        else {return false;}
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size(){
        return this.size;
    }

    /**
     * Adds an object to a fixed array with index {@link #calcIndex(Object)}.
     * contains intern variable int index.
     * @param object T
     */
    @Override
    public boolean insert(T object) {
        //temporary Index variable
        int index = calcIndex(object);

        if (!containsObject(object)) {
            LOG.debug("Add {} into index {}", object.toString(), index);
            arrayLists[index].add(object);
            size++;
            return true;
        }
        else {return false;}
    }

    /**
     * Returns object at index {@link #calcIndex(Object)}.
     * @param object
     * @return Object in array
     */
    @Override
    public T getElement(T object) {
        int index = calcIndex(object);
        return arrayLists[index].get(arrayLists[index].indexOf(object));
    }


}
