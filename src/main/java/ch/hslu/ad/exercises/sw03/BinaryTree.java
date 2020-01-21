/**
 * BinaryTree-Class represents
 *
 * @author stofers
 * @Version V1.0
 * @date 2019-03-06
 */
package ch.hslu.ad.exercises.sw03;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BinaryTree {

    private static final Logger LOG = LogManager.getLogger(BinaryTree.class);

    private Node root;

    public BinaryTree() {
        //root = new Node(32);
    }

    private Node addRec(Node current, int value) {
        if (current == null){
            return new Node(value);
        }
        if (value < current.getValue()){
            current.setLeft(addRec(current.getLeft(), value));
        }
        else if (value > current.getValue()){
            current.setRight(addRec(current.getRight(), value));
        }
        else {
            LOG.info("Value already exists");
            return current;
        }
        return current;
    }

    public void add(int value){
        root = addRec(root, value);
    }

    public Node getRoot() {
        return root;
    }

    private boolean containsNodeRecursive(Node current, int value) {
        if (current == null) {
            LOG.info("current is null -> return false");
            return false;
        }
        if (value == current.getValue()) {
            LOG.info("current is null -> return true");
            return true;
        }
        LOG.info("Recursive Search with Node value {} and int value {}", current.getValue(), value);
        return value < current.getValue()
                ? containsNodeRecursive(current.getLeft(), value)   //return true
                : containsNodeRecursive(current.getRight(), value); //return false
    }

    public boolean containsNode(int value) {
        LOG.info("Contains Tree Value: {}?", value);
        return containsNodeRecursive(root, value);
    }

    public void traverseInOrder(Node node) {
        if (node != null) {
            traverseInOrder(node.getLeft());
            LOG.info("InOrder: " + node.getValue());
            traverseInOrder(node.getRight());
        }
    }

    public void traversePreOrder(Node node) {
        if (node != null) {
            LOG.info("PreOrder: " + node.getValue());
            traversePreOrder(node.getLeft());
            traversePreOrder(node.getRight());
        }
    }

    public void traversePostOrder(Node node) {
        if (node != null) {
            traversePostOrder(node.getLeft());
            traversePostOrder(node.getRight());
            LOG.info("PostOrder: " + node.getValue());
        }
    }

    @Override
    public String toString() {
        if (root != null) {

            return "BinaryTree{" +
                    "root=" + root.getValue() +
                    '}';
        }
        else {
            return "BinaryTree is empty";
        }
    }
}
