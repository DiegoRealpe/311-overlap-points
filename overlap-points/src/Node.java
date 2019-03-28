/**
 * Node unit of the Red Black tree
 *
 * @author diegort, adrianh
 */
public class Node {

	private static final int RED = 0, BLACK = 1;
	
	/**
	 * TODO: Eventually delete this variable, it is redundant
	 * it references the same value as point.getValue()
	 * timepos = Node.Key = point in the timeline
	 */
	private int timePos;
	protected Endpoint point;

	// variables that can change
	protected Node parent, left, right;
	protected int color;

	/**
	 * Creates a valid node with default RED color ready to be inserted
	 *
	 * @param point
	 */
	public Node(Endpoint point, Node TNil) {
		this.timePos = point.getValue();
		this.color = RED;
		this.point = point;
		this.parent = this.left = this.right = TNil;
	}

	/**
	 * Creates an invalid Nil Node
	 */
	public Node() {
		this.color = BLACK;
		//Nil node points to itself
		//Since the Root's parent is not "null", it is Nil
		this.parent = this.left = this.right = this; 
	}

	public Node getParent() {
		return this.parent;
	}

	public Node getLeft() {
		return this.left;
	}

	public Node getRight() {
		return this.right;
	}

	public int getKey() {
		return this.timePos; // moment in the timeline
	}

	public int getP() {
		return this.point.getDir(); // +1 OR -1 according to start or end
	}

    public int getVal() {
        if (this.left != null) {
            return nodeSum(this.left);
        } else {
            return 1;
        }
    }

    public Endpoint getEndpoint() {
        return this.point;
    }

    public Endpoint getEmax() {
        Node cur = getRoot(this);
        if (cur.right != null) {
            cur = cur.right;
            return getMaxNode(cur, cur).point;
        }
        else return null;
    }

	public int getColor() {
		return this.color;
	}
	
	public int getMaxVal() {
		//returns whatever Adrian's logic does
		return 0;
	}
	
	//----------------------Our Custom protected methods

    protected int nodeSum(Node n){
        if (n.left == null){
            return n.point.getDir();
        } else {
            return nodeSum(n.left) + n.point.getDir() + nodeSum(n.right);
        }
    }

    protected Node getMaxNode(Node n, Node maxNode) {
        if (n.point.getContainNum() >= maxNode.point.getContainNum()
                && n.point.getValue() <= maxNode.point.getValue()){
            maxNode = n;
        }
        Node lnode = new Node(), rnode = new Node();
        if (n.left != null) lnode = getMaxNode(n.left, maxNode);
        if (n.right != null) rnode = getMaxNode(n.right, maxNode);
        if (lnode.point.getContainNum() >= rnode.point.getContainNum()) return lnode;
        else return rnode;
    }

	private Node getRoot(Node n) {
		while (n.parent != null) {
			n = n.parent;
		}
		n = n.right; // why n.right?
		return n;
	}
}
