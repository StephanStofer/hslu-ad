/**
 * AllocList-Class represents
 *
 * @author stofers
 * @Version V1.0
 * @date 2019-03-04
 */
package ch.hslu.ad.exercises.sw02;

import ch.hslu.ad.exercises.sw01.Allocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AllocList {

    private static final Logger LOG = LogManager.getLogger(AllocList.class);
    private Allocation[] allocations;

    public AllocList(int size) {
        int i = size;
        //for (int i = 0; i < size; i++) {
            allocations = new Allocation[i];
        //}
    }

    public void add(Allocation allocation){
        allocations = new Allocation[1];
    }

}
