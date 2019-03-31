/**
 * Node unit of the Red Black tree
 *
 * @author diegort, adrianh
 */
public class Node {

	private static final int RED = 0, BLACK = 1;

	/**
	 * TODO: Eventually delete this variable, it is redundant it references the same
	 * value as point.getValue() timepos = Node.Key = point in the timeline
	 */
	private int timePos;
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
		// Nil node points to itself
		// Since the Root's parent is not "null", it is Nil
		this.point = null;
		val = 0;
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
		if (point == null) {
			System.out.println("I should't be asked this! Error!");
		}
		return this.timePos; // moment in the timeline
	}

	public int getP() {
		if (point == null)
			return 0;
		return this.point.getDir(); // +1 OR -1 according to start or end
	}

	public int getVal() {
		return val;
	}

    public int getMaxVal() {
        if (point == null)
            return 0;
        return maxval;
    }

	public Endpoint getEndpoint() {
		return this.point;
	}

	public Endpoint getEmax() {
		return emax;
	}

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

	private void newVal() {
		if (point != null) {
			val = point.getDir();
			if (left.point != null)
				val += left.val;
			if (right.point != null)
				val += right.val;
		}
	}

	private void newMax() {
		if (point != null) {

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
}
