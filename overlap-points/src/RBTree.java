

/**
 * Class representing the Self Balancing tree of endpoints
 *
 * @author diegort, adrianh
 */
public class RBTree {
	private static final int RED = 0, BLACK = 1;
	private final Node Nil = new Node();
    private Node Root = Nil;
    
    /**
     * Explain the function of a Nil maxNode? I think it should be removed.
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

	/**
	 * Method that performs a normal BST insertion
	 * Balances in the end
	 * @param n
	 */
	private void insert(Node n) {
		Node previous = Nil;
		Node traverse = Root;

		//Iterate down the tree to leaf where n belongs
		while(traverse != Nil) {
			previous = traverse;
			if(n.getKey() < traverse.getKey()) {
				traverse = traverse.left;
			}
			else {
				traverse = traverse.right;
			}
		}
		
		n.parent = previous; //Give inserted node a Daddy
		//Update Daddy, set it as a L or R child or if it is the Root
		if(previous == Nil) {//if parent is Nil, then it must be the Root
			Root = n; //Assign to Root
		}
		else if(n.getKey() < previous.getKey()) {
			previous.left = n; //Assign to left
		}
		else {
			previous.right = n; //Assign to right
		}
		
		//Default Nil assignments to n children and color done in constructor
		//TODO Calculate new node's emax, maxval, val
		
		RB_Insert_Fixup(n);
	}
	
	private void RB_Insert_Fixup(Node n){
		 //TODO
		//Inserted node is RED by default, so it cant break the black path property
		//Only property it can break is being child of a RED
		//while loop checks against that
		while(n.parent.color == RED) {
			if(n.parent == n.parent.parent.left) {
				Node uncle = n.parent.parent.right;
				//Case 1: Recolor
				if(uncle.color == RED) {//Recolor uncle, n.p and n to hold amount of nodes
					n.parent.color = BLACK;
					uncle.color = BLACK;
					n.parent.parent.color = RED;
					n = n.parent.parent; //Move pointer up 2 and rerun loop
				}
				//Case 2 & 3: Restructure (Only runs once)
				else {
					//Case 2: Fix < shape into / shape
					if(n == n.parent.right) { 
						n = n.parent; //Set shoulder as axis
						rotateLeft(n);
					}
					//Case 3: Rotate / shape into ^ shape
					//Child is assumed to be RED
					n.parent.color = BLACK; //newAxis needs to be BLACK after rotation to stay balanced
					n.parent.parent.color = RED; //new right child has to be RED to not mess up balance
					rotateRight(n.parent.parent);//axis
				}
			}
			else {
				//TODO symmetrical for other uncle
			}
		}
		//Asserts property 2 in case it got recolored
		//setting it to black cannot break black path propery either
		Root.color = BLACK;
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
