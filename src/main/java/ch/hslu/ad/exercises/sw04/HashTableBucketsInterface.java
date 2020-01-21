package ch.hslu.ad.exercises.sw04;

public interface HashTableBucketsInterface<T> {

    /**
     * Add an Object in a BucketArray.
     * @Type   T for Generics
     * @param object T
     */
    boolean insert(T object);

    /**
     * Removes an Object out of Bucket.
     * @Type    T for Generics
     * @param object T
     */
    boolean remove(T object);

    /**
     * search Objects in HashTableArray.
     * @Type   T for Generics
     * @param object T
     * @return Object
     */
    T getElement(T object);

    /**
     * Return number of entries in the arrayLists.
     * @Type   int
     * @return int
     */
    int size();
}
