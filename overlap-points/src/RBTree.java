
/**
 * Class representing the Self Balancing tree of endpoints
 *
 * @author diegort, adrianh
 */
public class RBTree {
	@SuppressWarnings("unused")
	private static final int RED = 0, BLACK = 1;
	private final Node Nil = new Node();
	private Node Root = Nil;

	public RBTree() {
		/**
		 * Class begins with a null Root and a reference to a Nil node, which marks the
		 * last end point. As it is empty Root and Nil point to the same null
		 */
	}

	public boolean isEmpty() {
		return Root == Nil;
	}

	public Node getRoot() {
		return Root;
	}

	public Node getNILNode() {
		return Nil;
	}

	/**
	 * Performs a recusive traversal and counts internal nodes
	 * @return
	 */
	public int getSize() {
		if (!isEmpty())
			return recCount(Root);
		return 0;
	}

	/**
	 * base: if n is leaf, counts 1
	 * step: returns 1(itself) + left nodes + right nodes
	 * @param n
	 * @return
	 */
	private int recCount(Node n) {
		if (n.left == Nil && n.right == Nil) {
			return 1;
		} else {
			int nodeCount = 1;
			if (n.left != Nil) {
				nodeCount += recCount(n.left);
			}
			if(n.right != Nil) {
				nodeCount += recCount(n.right);
			}
			return nodeCount;
		}
	}

	/**
	 * Performs a recursive traversal and obtains the longest simple path
	 * @return
	 */
	public int getHeight() {
		if (!isEmpty()) {
			return recDepth(Root);
		}
		return 0;
	}
	
	/**
	 * base: if non Nil node with only Nil children, height 1
	 * step: returns 1(itself) + Max between left and right depths
	 * @param n
	 * @return
	 */
	private int recDepth(Node n) {
		if (n.left == Nil && n.right == Nil) {
			return 1;
		} else {
			//PS: Nil nodes are height 0
			int lDepth = 0, rDepth = 0;
			if (n.left != Nil) {
				lDepth = recDepth(n.left);
			}
			if(n.right != Nil) {
				rDepth = recDepth(n.right);
			}
			return Math.max(lDepth, rDepth) + 1;
		}
	}

	protected void insertPair(Endpoint beg, Endpoint end) {
		// According to book, nodes to be inserted need to begin as RED
		Node left = new Node(beg, Nil);
		Node right = new Node(end, Nil);
		insert(left);
		insert(right);
	}

	//-----------------------RBT Algorithms

	private void insert(Node right) {

	}
}
