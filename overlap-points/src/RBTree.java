
/**
 * Class representing the Self Balancing tree of endpoints
 *
 * @author Diego Realpe, diegort
 * @author Adrian Hamill, adrianh
 */
public class RBTree {
	private static final int RED = 0, BLACK = 1;
	private final Node nil = new Node();
	private Node root = nil;

	/**
	 * Class begins with a null root and a reference to a Nil node, which marks the
	 * last endpoint. As it is empty root and Nil point to the same null
	 */
	public RBTree() {
	}

	/**
	 * Checks if the tree is empty
	 * @return
	 */
	public boolean isEmpty() {
		return root == nil;
	}

	/**
	 * Returns the root to the current tree
	 * @return
	 */
	public Node getRoot() {
		return root;
	}

	/**
	 * Returns the nil node of the tree
	 * @return
	 */
	public Node getNILNode() {
		return nil;
	}

	/**
	 * Performs a recursive traversal and counts internal nodes
	 * @return
	 */
	public int getSize() {
		if (!isEmpty())
			return recCount(root);
		return 0;
	}

	/**
	 * base: if n is leaf, counts 1 
	 * step: returns 1(itself) + left nodes + right nodes
	 * @param n
	 * @return
	 */
	private int recCount(Node n) {
		if (n.left == nil && n.right == nil) {
			return 1;
		} else {
			int nodeCount = 1;
			if (n.left != nil) {
				nodeCount += recCount(n.left);
			}
			if (n.right != nil) {
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
			return recDepth(root);
		}
		return 0;
	}

	/**
	 * base: if non nil node with only nil children, height 1
	 * step: returns 1(itself) + Max between left and right depths
	 * @param n
	 * @return
	 */
	private int recDepth(Node n) {
		if (n.left == nil && n.right == nil) {
			return 1;
		} else {
			int lDepth = 0, rDepth = 0;
			if (n.left != nil) {
				lDepth = recDepth(n.left);
			}
			if (n.right != nil) {
				rDepth = recDepth(n.right);
			}
			return Math.max(lDepth, rDepth) + 1;
		}
	}

	// -----------------------RBT Algorithms

	/**
	 * Takes in the two endpoints creates nodes for them and passes them to the insert function
	 * @param beg
	 * @param end
	 */
	protected void insertPair(Endpoint beg, Endpoint end) {
		// According to book, nodes to be inserted need to begin as RED
		Node left = new Node(beg, nil);
		Node right = new Node(end, nil);
		insert(left);
		insert(right);
	}

	/**
	 * First call of a recursive insertion algorithms
	 * @param n
	 */
	protected void insert(Node n) {
		recInsert(nil, root, n);
		RB_Insert_Fixup(n);
	}

	/**
	 * Recursion algorithm that traverses down the RBT looking to insert a
	 * new node n. When recursion ends, it recalculates the parent of the subtree
	 * on each call until it reaches nil.
	 * @param previous Node parent of traverse
	 * @param traverse Node to be traversed through (or replaced if nil)
	 * @param n        Node to be inserted
	 */
	private void recInsert(Node previous, Node traverse, Node n) {
		if (traverse != nil) {
			if (n.getKey() < traverse.getKey()) {
				recInsert(traverse, traverse.left, n);
			} else {
				recInsert(traverse, traverse.right, n);
			}
			//Recursion returned with recalculated children, recalculate parent 
			traverse.recalc();
			return;
		} 
		else { // base
			n.parent = previous; // Give inserted node a Daddy
			// Update Daddy, set it as a L or R child or if it is the root
			if (previous == nil) {// if parent is nil, then it must be the root
				root = n; // Assign to root
			} else if (n.getKey() < previous.getKey()) {
				previous.left = n; // Assign to left
			} else {
				previous.right = n; // Assign to right
			}
			/**
			 * Constructor already assigned nil children and RED color for new node Adrian's
			 * function calculates other values for new leaf node
			 */
			n.recalc();
			return; //returns back to the root and recalculates parent nodes as it goes up
		}
	}

	/**
	 * Method fixes a violation of the Red-Black properties that can arise from
	 * inserting a red node into the tree. R3 - Red cannot be child of Red or root
	 * cannot be Red
	 * @param n
	 */
	private void RB_Insert_Fixup(Node n) {
		// Inserted node is RED by default, so it cant break the black path property
		// Only property it can break is being child of a RED
		// while loop checks against that
		while (n.parent.color == RED) {
			if (n.parent == n.parent.parent.left) {
				Node uncle = n.parent.parent.right;
				// Case 1: Recolor
				if (uncle.color == RED) {// Recolor uncle, n.p and n to hold amount of nodes
					n.parent.color = BLACK;
					uncle.color = BLACK;
					n.parent.parent.color = RED;
					n = n.parent.parent; // Move pointer up 2 and rerun loop
				}
				// Case 2 & 3: Restructure (Only runs once)
				else {
					// Case 2: Fix < shape into / shape
					if (n == n.parent.right) {
						n = n.parent; // Set shoulder as axis
						rotateLeft(n);
					}
					// Case 3: Rotate / shape into ^ shape
					// Child is assumed to be RED
					n.parent.color = BLACK; // newAxis needs to be BLACK after rotation to stay balanced
					n.parent.parent.color = RED; // new right child has to be RED to not mess up balance
					rotateRight(n.parent.parent);// axis
				}
			} else { // else, red parent is a right child
				Node uncle = n.parent.parent.left;
				// Case 1
				if (uncle.color == RED) {
					n.parent.color = BLACK;
					uncle.color = BLACK;
					n.parent.parent.color = RED;
					n = n.parent.parent; // Move pointer up 2 and rerun loop
				}
				// Case 2 & 3
				else {
					// Case 2: Fix > shape into \ shape
					if (n == n.parent.left) {
						n = n.parent;
						rotateRight(n);
					}
					// Recolor to be R-B-R
					n.parent.color = BLACK;
					n.parent.parent.color = RED;
					rotateLeft(n.parent.parent);
				}
			}
		}
		// Asserts property 2 in case it got recolored
		// setting it to black cannot break black path propery either
		root.color = BLACK;
	}

	/**
	 * Rotates a node to the left
	 * 
	 * @param axis
	 */
	private void rotateLeft(Node axis) {
		if (axis.right == nil) {
			System.out.println("Rotate Left Error, nil newAxis");
		}

		Node newAxis = axis.right;

		axis.right = newAxis.left;// give newAxis Lchild a new daddy
		if (newAxis.left != nil) {// tell newAxis Lchild it has a new daddy
			newAxis.left.parent = axis;
		}

		newAxis.parent = axis.parent;// give axis parent to newAxi
		// 3 cases for axis parent telling it has a different child
		// it is either nil(root), a left or right child
		if (axis.parent == nil) {// if it is root, its parent is nil
			root = newAxis;// Set newAxis to root
		} else if (axis.parent.right == axis) {
			axis.parent.right = newAxis;
		} else {
			axis.parent.left = newAxis;
		}

		// Reassign target nodes references to each other
		newAxis.left = axis;
		axis.parent = newAxis;

		// After every rotation, recalculate the three nodes that were affected
		newAxis.right.recalc();
		newAxis.left.recalc();
		newAxis.recalc();
	}

	/**
	 * Rotates a node to the right
	 * 
	 * @param axis
	 */
	private void rotateRight(Node axis) {
		if (axis.left == nil) {
			System.out.println("Rotate Right Error, nil newAxis");
		}

		Node newAxis = axis.left;

		axis.left = newAxis.right;// give
		if (newAxis.right != nil) {// if non nil
			newAxis.right.parent = axis;// notify
		}

		newAxis.parent = axis.parent;// reassign parent
		// 3 cases: Notify parent
		if (axis.parent == nil) {
			root = newAxis;
		} else if (axis.parent.right == axis) {
			axis.parent.right = newAxis;
		} else {
			axis.parent.left = newAxis;
		}

		// Reassign target nodes references to each other
		newAxis.right = axis;
		axis.parent = newAxis;

		// After every rotation, recalculate the three nodes that were affected
		newAxis.right.recalc();
		newAxis.left.recalc();
		newAxis.recalc();
	}
}
