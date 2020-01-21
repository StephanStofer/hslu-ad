package ch.hslu.ad.exercises.sw03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

    @Test
    void testGetValue() {
        Node node = new Node(23);
        assertTrue(node.getValue() == 23);
    }

    @Test
    void testGetLeft() {
        Node node = new Node(13);
        node.setLeft(new Node(8));
        assertTrue(node.getLeft().getValue() == 8);
    }

    @Test
    void testGetRight() {
        Node node = new Node(13);
        node.setRight(new Node(14));
        assertTrue(node.getRight().getValue() == 14);
    }
}