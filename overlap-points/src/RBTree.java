

/**
 * Class representing the Self Balancing tree of endpoints
 *
 * @author diegort
 */
public class RBTree {
    private static final int RED = 0, BLACK = 1;
    private Node Nil = new Node(), maxNode = new Node(null);
    private Node Root = Nil;
    private int treeDepth;

    /**
     * Class begins with a null Root and a reference to a Nil node, which
     * marks the last endpoint. As it is empty Root and Nil point to the same null
     */
    public RBTree() {
        Root.parent = Root;
        Root.left = null;
        Root.right = null;
        Root.point = new Endpoint(-1, -1, 0);
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
            return treeDepth;
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

    private void recalculate() {
        Node cur = Root.right;
        if (cur != null){
            recalcTrav(cur);
            maxNode = cur.getMaxNode(cur, cur);
            Depth(Root.right, 1);
        }
    }

    private void recalcTrav(Node cur) {
        cur.point.setContainNum(cur.nodeSum(cur));
        if (cur.left != null)
            recalcTrav(cur.left);
        if (cur.right != null)
            recalcTrav(cur.right);
    }

    public int getMaxVal(){
        return maxNode.point.getContainNum();
    }

    public Node getMaxNode() {
        return maxNode;
    }
}
