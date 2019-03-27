import javax.swing.*;

/**
 * Class representing the Self Balancing tree of endpoints
 *
 * @author diegort
 */
public class RBTree {
    private static final int RED = 0, BLACK = 1;
    private Node Root = new Node(-1, -1, null);

    public RBTree() {
        Root.parent = Root;
        Root.left = null;
        Root.right = null;
    }

    public boolean isEmpty() {
        return Root.right == null;
    }

    public Node getRoot() {
        return Root;
    }

    public Node getNINode() {
        return null;
    }

    public int getSize() {
        Node cur = Root;
        while (cur.right != null) cur = cur.right;
        return cur.point.getID();
    }

    public int getHeight() {
        if (isEmpty()) {
            return 0;
        } else {
            return Depth(Root.right, 1);
        }
    }

    protected void insertPair(Endpoint beg, Endpoint end) {
        Node left = new Node(beg.getValue(), BLACK, beg);
        Node right = new Node(end.getValue(), BLACK, end);
        insert(left);
        insert(right);
    }

    private int Depth(Node n, int depth) {
        if (n == null) return depth--;
        int lDepth = Depth(n.left, depth++);
        int rDepth = Depth(n.right, depth++);

        if (lDepth >= rDepth) return lDepth;
        else return rDepth;
    }

    private void insert(Node right) {

    }
}
