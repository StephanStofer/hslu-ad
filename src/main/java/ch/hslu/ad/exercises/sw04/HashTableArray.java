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

public class HashTableArray<T> implements HashTableInterface{

    private static final Logger LOG = LogManager.getLogger(HashTableArray.class);

    private static final int DEFAULT_CAP = 10;
    private static final float LOAD_FACTOR = 1.5f;

    private int size = 0;
    private Object[] objects;

    public HashTableArray(int size) {
        this.objects = new Object[Math.round(size*LOAD_FACTOR)];
    }

    /**
     * private Method to calculate Index position.
     * @param object Object.
     * @return  int
     */
    //TODO: refactor to return object.hashCode(object) % 10;
    private int calcIndex(Object object){
        int hash = object.hashCode();
        int value = hash % 10;
        LOG.debug("HashIndex: {} ", value);
        return object.hashCode()%10;
    }

    /**
     * Calculates offset if index already had been taken.
     * @param startIndex integer.
     * @return integer
     */
    private int calcOffset(int startIndex){
        do {
            startIndex++;
        } while (!isEmpty(startIndex));
        return startIndex;
    }

    /**
     * Returns object in array.
     * @param value
     * @return
     */
    public Object searchByValue(Object value){
        int index = calcIndex(value);

        for (int i = index; objects[i] != null; i++) {
                LOG.debug("Assert {} with {}",objects[i].toString(), value.toString());
            if (getElement(i).equals(value)) {
                return objects[i];
            }
        }
        return null;
    }

    /**
     * Override array-object with a placeholder object Tombstone,
     * to prevent gaps in array in case of duplicate hashindexes.
     * @param value Object
     */
    public void remove(Object value){
        int index = calcIndex(value);
        for (int i = index; objects[i] != null; i++) {
            if (getElement(i).equals(value)) {
                LOG.debug("Removed {}", objects[i].toString());
                objects[i] = new Tombstone();
                size--;
            }
        }
    }

    /**
     * Returns the number of entries in the array.
     * @return
     */
    public int size(){
        return this.size;
    }

    /**
     * Returns true if array length == {@link #size()}.
     * @return boolean
     */
    public boolean isFull(){
        LOG.debug("Array length is: {} with {} entries taken.", objects.length,size());

        return objects.length == size();
    }

    public void printArray(){
        LOG.debug("start printing...");
        for (int i = 0; i < objects.length; i++){
            if (objects[i] != null){
                LOG.debug("{}",objects[i].toString());
            }
            else {
                LOG.debug("Index «{}» is empty", i);
            }
        }
        LOG.debug("all elements printed.");
    }

    /**
     * {@inheritDoc}
     * Adds an object to a fixed array with index {@link #calcIndex(Object)}.
     * contains intern variable int index.
     * @param object of type Object.
     */
    @Override
    public void insert(Object object) {
        //temporary Index variable
        int index = calcIndex(object);

        if(isEmpty(index)){
            objects[index] = object;
            size++;
            //LOG.debug("Add {} into index {}", object.toString(),index);
        }

        else if(!object.equals(getElement(index))){
            int offsetIndex = calcOffset(index);
            objects[offsetIndex] = object;
            size++;
            //LOG.debug("Add {} into index {}", object.toString(),offsetIndex);
        }
        else {
            throw new InternalError("This Object already exists.");
        }
    }

    /**
     * Returns object at index {@link #calcIndex(Object)}.
     * @param index int.
     * @return Object in array
     */
    @Override
    public Object getElement(int index) {

        return objects[index];
    }

    /**
     * returns true if Index is empty or contains a Tombstone.
     * @param index int.
     * @return boolean
     */
    @Override
    public boolean isEmpty(int index) {
        return objects[index] == null || (objects[index] instanceof Tombstone);
    }

    private class Tombstone<T>{

    }
}
