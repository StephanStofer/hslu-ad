/**
 * Balls-Class represents
 *
 * @author stofers
 * @Version V1.0
 * @date 2019-03-19
 */
package ch.hslu.ad.exercises.sw05.balls;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class BallDemo {

    private static final Logger LOG = LogManager.getLogger(BallDemo.class);

    private final int numberOfBalls = 5;

    private ArrayList<Ball> balls = new ArrayList<>(numberOfBalls);
    private final Thread[] threads = new Thread[numberOfBalls];

    public BallDemo() {

    }

    private void createBalls(){
        for (int i = 0; i<numberOfBalls; i++){
            balls.add(new Ball());

        }
    }

    private void runThreads() {
        for (int i = 0; i < threads.length; i++) {
            int finalI = i;
            threads[i] = new Thread(() -> balls.get(finalI).moveToBottom());
        }
        for (final Thread thread: threads
             ) {
            thread.start();
        }
    }

    public static void main(String[] args) {
        BallDemo demo = new BallDemo();
        demo.createBalls();
        demo.runThreads();

    }
}
