
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
	 * Performs an inorder traversal and counts internal nodes
	 * 
	 * @return
	 */
	public int getSize() {
		if (!isEmpty())
			return countPostorder(Root);
		return 0;
	}

	private int countPostorder(Node n) {
		if (n.left == Nil && n.right == Nil) {
			return 1;
		} else {
			if (n.left != Nil) {
				// TODO
			}
			return 0;
		}
	}

	public int getHeight() {
		if (!isEmpty()) {
			return Depth(Root.right, 1);
		}
		return 0;
	}

	protected void insertPair(Endpoint beg, Endpoint end) {
		// According to book, nodes to be inserted need to begin as red
		Node left = new Node(beg, Nil);
		Node right = new Node(end, Nil);
		insert(left);
		insert(right);
	}

	private int Depth(Node n, int depth) {
		if (n == null)
			return depth--;
		int lDepth = Depth(n.left, depth++);
		int rDepth = Depth(n.right, depth++);

		if (lDepth >= rDepth)
			return lDepth;
		else
			return rDepth;
	}

	private void insert(Node right) {

	}
}
