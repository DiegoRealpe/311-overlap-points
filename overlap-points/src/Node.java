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
	protected Endpoint point, emax;

	// variables that can change
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
		return val;
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
	
	public int getMaxVal() {
		return maxval;
	}

	public void recalculateNode(){
		//Make sure that the emax is assigned to Nil if it is the last endpoint
		//I can't assign a node Nill to an endpoint emax
		if (point != null){
			if (left.point == null && right.point == null){
				val = point.getDir();
				emax = point;
				if (val < 0){
					maxval = 0;
				} else {
					maxval = emax.getDir();
				}
			} else if (left.point != null && right.point == null){
				val = left.val + point.getDir();
				if (left.emax.getValue() >= val){
					maxval = left.emax.getValue();
					emax = left.emax;
				}
				else {
					maxval = val;
					emax = point;
				}
			} else if (this.left.point == null){
				val = right.val + point.getDir();
				if (right.emax.getValue() > val){
					maxval = right.emax.getValue();
					emax = right.emax;
				}
				else {
					maxval = val;
					emax = point;
				}
			} else {
				val = left.val + right.val + point.getDir();
				if (left.emax.getValue() >= val && left.emax.getValue() >= right.emax.getValue()){
					maxval = left.emax.getValue();
					emax = left.emax;
				} else if (val > left.emax.getValue() && val > right.emax.getValue()){
					maxval = val;
					emax = point;
				} else if (right.emax.getValue() > val && right.emax.getValue() > left.emax.getValue()){
					maxval = right.emax.getValue();
					emax = right.emax;
				}
			}
		}
	}
}
