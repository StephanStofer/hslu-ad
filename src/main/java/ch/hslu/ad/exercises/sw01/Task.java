/*
  Task-Class represents

  @author stofers
 * @Version V1.0
 * @date 2019-02-24
 */
package ch.hslu.ad.exercises.sw01;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Task {

    private static final Logger LOG = LogManager.getLogger(Task.class);
    private int count;
    private int sleepTime = 5000;

    private Task() {

    }

    private void task1(){
        count++;
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void task2(){
        count++;
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void task3(){
        count++;
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void task4(){
        count++;
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void task(final int n) {
        task1();
        task1();
        task1();
        task1();
        for (int i = 0; i < n; i++) {
            task2();
            task2();
            task2();
        }
        for (int j = 0; j < n; j++) {
            task3();
            task3();
        }
        LOG.info("Number of calls: " +count);
    }

    public static void main(String[] args) {
        Task task = new Task();
        task.task(3);
    }
}
