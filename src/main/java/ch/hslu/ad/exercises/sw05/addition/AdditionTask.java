/**
 * AdditionTask-Class represents
 *
 * @author stofers
 * @Version V1.0
 * @date 2019-03-24
 */
package ch.hslu.ad.exercises.sw05.addition;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AdditionTask implements Runnable {

    private static final Logger LOG = LogManager.getLogger(AdditionTask.class);

    private volatile Thread runThread;
    private volatile boolean isStopped = false;

    private int number;
    private int n;

    public AdditionTask(int number) {
        this.number = number;
        this.n = number;
    }

    public void stopRequest() { isStopped = true;
        if (runThread != null) {
            runThread.interrupt(); }
    }

    public boolean isStopped() { return isStopped;
    }

    @Override
    public void run() {
        this.runThread = Thread.currentThread(); // Initialisierungsphase
        long sum = 0;
        // Arbeitsphase
        while (number>0 || !isStopped())
        {
            sum += number%10;
            number=number/10;
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Aufräumphase
        if (!isStopped()) {
           LOG.info(runThread.getName() + ": Checksum of " + n + " -> " + sum);
        } else {
            LOG.info(runThread.getName() + ": interrupted."); }

    }
}
