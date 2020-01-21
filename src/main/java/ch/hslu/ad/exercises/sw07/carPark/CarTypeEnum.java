package ch.hslu.ad.exercises.sw07.carPark;

import java.util.Random;

public enum CarTypeEnum {
    /**
     * Type1 cars go to parking1 and waits until a slot is available.
     */
    Type1,

    /**
     * Type2 cars the first empty parking starting with parking 1. If all are full, it goes home.
     */
    Type2,

    /**
     * Type3 similar to type 2, but wait at every parking for individual period.
     */
    Type3,

    /**
     * Type4 cars goes directly to the biggest parking, if full it goes home.
     */
    Type4;

    public static CarTypeEnum getRandomTypeEnum(){
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}
