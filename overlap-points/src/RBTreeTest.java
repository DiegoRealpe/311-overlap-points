import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RBTreeTest {

    // Instance variables
    private RBTree tree;
    private Node node1;
    private Node node2;
    private Node node3;
    private Node node4;
    private Node node5;
    private Node node6;

    @Before
    public void initialize() {
        tree = new RBTree();
        node1 = new Node(new Endpoint(1,1, 1), new Node());
        node2 = new Node(new Endpoint(2,2, 1), new Node());
        node3 = new Node(new Endpoint(3,3, 1), new Node());
        node4 = new Node(new Endpoint(4,4, 1), new Node());
        node5 = new Node(new Endpoint(5,5, 1), new Node());
        node6 = new Node(new Endpoint(6,6, 1), new Node());
    }

    @Test
    public void testGetRoot() {

        // Root should initially be NIL
        assertTrue(tree.getRoot().isNil());

        // Insert node, which is new root
        tree.insert(node1);
        assertFalse(tree.getRoot().isNil());
        assertEquals(tree.getRoot().getKey(), 1);

        // New insert keeps the same root as before
        tree.insert(node2);
        assertEquals(tree.getRoot().getKey(), 1);

        // Rotation results in new root
        tree.insert(node3);
        assertEquals(tree.getRoot().getKey(), 2);
    }

    @Test
    public void testGetNILNode() {
        Node nilNode = tree.getNILNode();
        assertTrue(nilNode.isNil());
        assertEquals(nilNode.getColorInEnum(), 1);
        tree.insert(node1);
        assertEquals(tree.getNILNode(), nilNode);   // NIL node shouldn't change after insert
    }

    @Test
    public void testGetSize() {
        assertEquals(tree.getSize(), 0);
        tree.insert(node1);
        assertEquals(tree.getSize(), 1);
        tree.insert(node2);
        assertEquals(tree.getSize(), 2);
        tree.insert(node3);
        assertEquals(tree.getSize(), 3);
    }

    @Test
    public void testGetHeight() {
        assertEquals(tree.getHeight(), 0);
        tree.insert(node3);
        assertEquals(tree.getHeight(), 1);
        tree.insert(node2);
        assertEquals(tree.getHeight(), 2);
        tree.insert(node4);
        assertEquals(tree.getHeight(), 2);
        tree.insert(node5);
        assertEquals(tree.getHeight(), 3);
        tree.insert(node6);
        assertEquals(tree.getHeight(), 3);  // Rotation keeps height at 3
    }
}
