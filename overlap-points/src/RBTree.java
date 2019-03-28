

/**
 * Class representing the Self Balancing tree of endpoints
 *
 * @author diegort
 */
public class RBTree {
    private static final int RED = 0, BLACK = 1;
    private Node Nil = new Node();
    private Node Root = Nil;

    /**
     * Class begins with a null Root and a reference to a Nil node, which
     * marks the last endpoint. As it is empty Root and Nil point to the same null
     */
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
    	//According to book, nodes to be inserted need to begin as red
        Node left = new Node(beg); 
        Node right = new Node(end);
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
