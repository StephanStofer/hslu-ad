/**
 * DemoAdditionTask-Class represents
 *
 * @author stofers
 * @Version V1.0
 * @date 2019-03-24
 */
package ch.hslu.ad.exercises.sw05.addition;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class DemoAdditionTask {

    private static final Logger LOG = LogManager.getLogger(DemoAdditionTask.class);

    public DemoAdditionTask() {

    }

    public static void main(String[] args) {
//        AdditionTask at = new AdditionTask(23484577);
//        Thread thread = new Thread(at);
//
//        thread.start();
//
//        at.stopRequest();




        final int number = 10;
        final Thread[] threads = new Thread[number];
        for (int i = 0; i < number; i++) {
            threads[i] = new Thread(new AdditionTask(new Random().nextInt(1_000_000)));
            threads[i].setName("Thead"+i);
        }
        for (final Thread thread : threads) {
            thread.start();
            thread.interrupt();
//            thread.stop();
        }

    }

}
