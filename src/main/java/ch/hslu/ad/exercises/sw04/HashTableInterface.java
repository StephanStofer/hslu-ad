package ch.hslu.ad.exercises.sw04;

public interface HashTableInterface<T> {

    /**
     * Add Objects to HashTableArray.
     * @Type   T for Generics
     * @param object
     */
    void insert(T object);
    /**
     * search Objects in HashTableArray.
     * @Type   T for Generics
     * @param index
     * @return Object
     */
    Object getElement(int index);
    /**
     * checks if index is empty.
     * @Type   boolean
     * @param index
     * @return boolean
     */
    boolean isEmpty(int index);
}
