/**
 * Ball-Class represents
 *
 * @author stofers
 * @Version V1.0
 * @date 2019-03-19
 */
package ch.hslu.ad.exercises.sw05.balls;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Ball {

    private static final Logger LOG = LogManager.getLogger(Ball.class);

    private Circle circle;
    private String[] colors = new String[]{"red","black","blue","yellow","green","magenta"};

    public Ball() {
        this.circle = new Circle(getRandomInt(20,50),getRandomInt(0,600),getRandomInt(10,200),colors[getRandomInt(0,5)]);
        circle.makeVisible();
        }

    public void moveToBottom(){
        int speed = getRandomInt(1, 10);
        int pixelsToBottom = (400-circle.getY()-(circle.getDiameter()));
        for (int i = 0; i < (pixelsToBottom / speed); i++) {
            circle.moveVertical(speed);
        }
        Canvas.getCanvas().wait(getRandomInt(400,1700));
        circle.makeInvisible();

    }

    /**
     * Return a random Int-value in a specific Range ex: [5,10]. Method use {@link Math class}.
     * @param minRange interval start value
     * @param maxRange interval end value
     * @return Random int
     */
    public static int getRandomInt(int minRange, int maxRange){
        return minRange + (int)(Math.random() * ((maxRange - minRange) + 1));
    }

}
