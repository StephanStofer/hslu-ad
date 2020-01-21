/**
 * JoinAndSleep-Class represents
 *
 * @author stofers
 * @Version V1.0
 * @date 2019-03-24
 */
package ch.hslu.ad.exercises.sw05;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JoinAndSleep implements Runnable{

    private static final Logger LOG = LogManager.getLogger(JoinAndSleep.class);
    private int timeToSleep;
    //private Thread thread = new Thread();

    public JoinAndSleep(int time) {
        this.timeToSleep = time;
    }


    @Override
    public void run() {
        try {
            LOG.info("Thread {} sleep for {}ms.", Thread.currentThread().getId(),timeToSleep);
            Thread.sleep(timeToSleep);
            LOG.info("Thread {} is awake..", Thread.currentThread().getId());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException{
        Thread thread1 = new Thread(new JoinAndSleep(2000));
        Thread thread2 = new Thread(new JoinAndSleep(3000));
        Thread thread3 = new Thread(new JoinAndSleep(4000));

        thread3.start();
        LOG.info("Thread {} started..",thread3.getId());
        thread3.join();
        LOG.info("Thread {} returned..",thread3.getId());

        thread2.start();
        LOG.info("Thread {} started..",thread2.getId());
        thread2.join();
        LOG.info("Thread {} returned..",thread2.getId());

        thread1.start();
        LOG.info("Thread {} started..",thread1.getId());
        thread1.join();
        LOG.info("Thread {} returned..",thread1.getId());


//        JoinAndSleep js1 = new JoinAndSleep(2000);
//        JoinAndSleep js2 = new JoinAndSleep(3000);
//        JoinAndSleep js3 = new JoinAndSleep(4000);
//        js3.thread.start();
//        js2.thread.start();
//        js1.thread.start();
    }
}

