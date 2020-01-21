package ch.hslu.ad.exercises.sw03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeTest {

    @Test
    void testContainsNode(){

    }

    @Test
    void testAdd(){
        BinaryTree bt = new BinaryTree();
        bt.add(12);
        assertTrue(bt.containsNode(12));
    }

    @Test
    void testInitializer(){
        BinaryTree bt = new BinaryTree();
        assertFalse(bt.containsNode(3));
    }

    @Test
    void testValueExists(){
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.add(23);
        binaryTree.add(23);
        binaryTree.getRoot().getValue();

    }

    @Test
    void testSearch() {
        BinaryTree bt = new BinaryTree();
        bt.add(12);
        bt.add(4);
        bt.add(16);
        bt.add(2);
        bt.add(6);
        bt.add(13);
        bt.add(1);
        bt.add(5);
        bt.add(7);
        bt.add(28);
        bt.add(31);

        assertTrue(bt.containsNode(6));
        assertTrue(bt.containsNode(31));

        assertFalse(bt.containsNode(14));
    }

    @Test
    void testInOrder() {
        BinaryTree bt = new BinaryTree();
        bt.add(12);
        bt.add(4);
        bt.add(16);
        bt.add(2);
        bt.add(6);
        bt.add(13);
        bt.add(1);

        bt.traverseInOrder(bt.getRoot());
    }

    @Test
    void testPreOrder() {
        BinaryTree bt = new BinaryTree();
        bt.add(12);
        bt.add(4);
        bt.add(16);
        bt.add(2);
        bt.add(6);
        bt.add(13);
        bt.add(1);

        bt.traversePreOrder(bt.getRoot());
    }

    @Test
    void testPostOrder() {
        BinaryTree bt = new BinaryTree();
        bt.add(12);
        bt.add(4);
        bt.add(16);
        bt.add(2);
        bt.add(6);
        bt.add(13);
        bt.add(1);

        bt.traversePostOrder(bt.getRoot());
    }
}