/**
 * Node-Class represents
 *
 * @author stofers
 * @Version V1.0
 * @date 2019-03-06
 */
package ch.hslu.ad.exercises.sw03;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Node {

    private static final Logger LOG = LogManager.getLogger(Node.class);

    private int value;
    private Node left;
    private Node right;

    public Node(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}
