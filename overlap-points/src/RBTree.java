

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
    
    /**
     * Explain the function of a Nil maxNode?
     */
    private Node maxNode = new Node();

    /**
     * Class begins with a null Root and a reference to a Nil node, which
     * marks the last endpoint. As it is empty Root and Nil point to the same null
     */
    public RBTree() {
    	//Root parent, left and right child point to Nil
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

	
	//-----------------------Adrian's protected methods
	
	/**
	 * Note: MaxVal and MaxNode should not need additional methods
	 * they should be present in the Root's emax and maxval fields
	 */
	
	/*protected int getMaxVal(){
        return maxNode.point.getContainNum();
    }*/

    /*protected Node getMaxNode() {
        return maxNode;
    }*/
	
	@SuppressWarnings("unused")
	private void recalculate() {
        Node cur = Root.right;// Don't use Root.right
        if (cur != null){
            recalcTrav(cur);
            maxNode = cur.getMaxNode(cur, cur);
        }
    }

    private void recalcTrav(Node cur) {
        cur.point.setContainNum(cur.nodeSum(cur));
        if (cur.left != null)
            recalcTrav(cur.left);
        if (cur.right != null)
            recalcTrav(cur.right);
    }

	//-----------------------RBT Algorithms 
    
	protected void insertPair(Endpoint beg, Endpoint end) {
		// According to book, nodes to be inserted need to begin as RED
		Node left = new Node(beg, Nil);
		Node right = new Node(end, Nil);
		insert(left);
		insert(right);
	}

	private void insert(Node n) {

	}
	
	/**
	 * Rotates a node to the left
	 * @param axis
	 */
	@SuppressWarnings("unused")
	private void rotateLeft(Node axis) {
		if(axis.left == Nil) {
			System.out.println("Rotate Left Error, Nil newAxis");
		}
		
		Node newAxis = axis.right;
		
		axis.right = newAxis.left;//give newAxis Lchild a new daddy
		if(newAxis.left != Nil) {//tell newAxis Lchild it has a new daddy
			newAxis.left.parent = axis;
		}
		
		newAxis.parent = axis.parent;//give axis parent to newAxi
		//3 cases for axis parent telling it has a different child
		//it is either Nil(Root), a left or right child
		if(axis.parent == Nil) {//if it is Root, its parent is Nil
			Root = newAxis;//Set newAxis to Root
		}
		else if(axis.parent.right == axis) {
			axis.parent.right = newAxis;
		}
		else{
			axis.parent.left = newAxis;
		}
		
		//Reassign target nodes references to each other
		newAxis.left = axis;
		axis.parent = newAxis;
	}

	/**
	 * Rotates a node to the right
	 * @param axis
	 */
	@SuppressWarnings("unused")
	private void rotateRight(Node axis) {
		if(axis.left == Nil) {
			System.out.println("Rotate Right Error, Nil newAxis");
		}
		
		Node newAxis = axis.left;
		
		axis.left = newAxis.right;//give
		if(newAxis.right != Nil) {//if non Nil
			newAxis.right.parent = axis;//notify
		}
		
		newAxis.parent = axis.parent;//reassign parent
		//3 cases: Notify parent
		if(axis.parent == Nil) {
			Root = newAxis;
		}
		else if(axis.parent.right == axis) {
			axis.parent.right = newAxis;
		}
		else{
			axis.parent.left = newAxis;
		}
		
		//Reassign target nodes references to each other
		newAxis.right = axis;
		axis.parent = newAxis;
	}

}
