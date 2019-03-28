/**
 * Node unit of the Red Black tree
 *
 * @author diegort, adrianh
 */
public class Node {

	private static final int RED = 0, BLACK = 1;
	// variables that shouldn't change (timePos a.k.a Key)
	private int timePos;
	private Endpoint point;

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
			return sum(this.left);
		} else {
			return 1;
		}
	}

	public int getMaxVal() {
		Node cur = getRoot(this);
		return getMaxSum(cur, 0);
	}

	Endpoint getEndpoint() {
		return this.point;
	}

	Endpoint getEmax() {
		Node cur = getRoot(this);
		return getMaxEnd(cur, 0, cur);
	}

	int getColor() {
		return this.color;
	}

	private int sum(Node n) {
		if (n.left == null) {
			return n.point.getDir();
		} else {
			return sum(n.left) + n.point.getDir() + sum(n.right);
		}
	}

	private int getMaxSum(Node n, int max) {
		int curSum = sum(n);
		if (curSum > max)
			max = curSum;
		if (n.left != null)
			getMaxSum(n.left, max);
		if (n.right != null)
			getMaxSum(n.right, max);
		return max;
	}

	private Endpoint getMaxEnd(Node n, int max, Node maxNode) {
		int curSum = sum(n);
		if (curSum > max) {
			max = curSum;
			maxNode = n;
		}
		if (n.left != null)
			getMaxEnd(n.left, max, maxNode);
		if (n.right != null)
			getMaxEnd(n.right, max, maxNode);
		return maxNode.point;
	}

	private Node getRoot(Node n) {
		while (n.parent != null) {
			n = n.parent;
		}
		n = n.right; // why n.right?
		return n;
	}
}
