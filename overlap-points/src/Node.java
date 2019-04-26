/**
 * Node unit of the Red Black tree
 *
 * @author Diego Realpe, diegort
 * @author Adrian Hamill, adrianh
 */
public class Node implements Comparable<Node>{

	private static final int RED = 0, BLACK = 1;

	protected final Endpoint point; // should not be modified

	// variables that can change
	protected Endpoint emax;
	protected Node parent, left, right;
	protected int color, maxval, val;

	/**
	 * Creates a valid node with default RED color ready to be inserted
	 *
	 * @param point
	 */
	public Node(Endpoint point, Node TNil) {
		this.color = RED;
		this.point = point;
		this.parent = this.left = this.right = TNil;
	}

	/**
	 * Creates an invalid Nil Node
	 */
	public Node() {
		// Nil node points to itself
		// Since the Root's parent is not "null", it is Nil
		this.parent = this.left = this.right = this;
		this.color = BLACK;
		this.point = new Endpoint(0, -1, 0);
		this.emax = this.point;
		this.val = 0;
		this.maxval = 0;
	}

    /**
     * Returns the parent of the current node
     * @return
     */
	public Node getParent() {
		return this.parent;
	}

    /**
     * Returns the left node of the current node
     * @return
     */
	public Node getLeft() {
		return this.left;
	}

    /**
     * Returns the right node of the current node
     * @return
     */
	public Node getRight() {
		return this.right;
	}

    /**
     * Returns the key of the current node, if called on nil it prints a message
     * @return
     */
	public int getKey() {
		if (point.getID() == -1) {
			System.out.println("Asking the Key of the Nil node?");
			return 0;
		}
		return this.point.getValue(); // moment in the timeline
	}

    /**
     * Returns the direction of the current node
     * @return
     */
	public int getP() {
		return this.point.getDir(); // +1 OR -1 according to start or end
	}

    /**
     * Returns the summation of the current node
     * @return
     */
	public int getVal() {
		return val;
	}

    /**
     * Returns the maximum summation of the current node
     * @return
     */
    public int getMaxVal() {
        return maxval;
    }

    /**
     * Returns the endpoint of the current node
     * @return
     */
	public Endpoint getEndpoint() {
		return this.point;
	}

    /**
     * Returns the endpoint corresponding to the maximum summation of the current node
     * @return
     */
	public Endpoint getEmax() {
		return emax;
	}

    /**
     * Returns the color of the current node
     * @return
     */
	public int getColor() {
		return this.color;
	}

	/**
	 * Method that assigns new value fields to the node after a new node has
	 * inserted onto it or it has rotated
	 */
	protected void recalc() {
		newVal();
		newMax();
		// newEmax();
		// newMaxVal();
	}

    /**
     * Recalculates the summation of the current node
     */
	private void newVal() {
		if (point.getID() != -1) {
			val = point.getDir();
			if (left.point.getID() != -1)
				val += left.val;
			if (right.point.getID() != -1)
				val += right.val;
		}
	}

    /**
     * Reassigns the maximum summation and maximum endpoint of the current node
     */
	private void newMax() {
		if (point.getID() != -1) {

			// calculating all 3 cases
			int case1 = left.maxval;
			int case2 = left.val + getP();
			int case3 = case2 + right.maxval;
			maxval = Math.max(Math.max(case1, case2), case3);

			if (maxval == case1) {
				emax = left.getEmax();
			} else if (maxval == case2) {
				emax = point;
			} else if (maxval == case3) {
				emax = right.getEmax();
			}
		}
	}
	
	/**
	 * Equals method
	 * @return
	 */
	@Override
	public boolean equals(Object o) {
	    if (o == null)
	        return false;
		if (this == o)
	        return true;
		if (!(o instanceof Node)) 
            return false; 
		Node n = (Node) o;
		return ((this.getEndpoint().getID() == n.getEndpoint().getID()) && (this.getEndpoint().getDir() == n.getEndpoint().getDir()));
	}
	
	/**
	 * Comparable interface
	 * @return
	 */
	@Override
	public int compareTo(Node n) {
		return this.getKey() - n.getKey();
	}

	//-----------------------------------------------------------------
	//Testing methods

	public boolean isNil(){
		return this.point.getID() == -1;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public void setMaxval(int maxval) {
		this.maxval = maxval;
	}

	public void setVal(int val) {
		this.val = val;
	}

	public void setEmax(Endpoint emax) {
		this.emax = emax;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getColorInEnum() {
		return this.color;
	}
}
