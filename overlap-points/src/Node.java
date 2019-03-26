/**
 * Node unit of the Red Black tree
 * @author diegort
 *
 */
public class Node {
	
	@SuppressWarnings("unused")
	private static final int RED = 0, BLACK = 1;
	Node parent, left, right;
	int timePos, color;
	Endpoint point;
	
	public Node(/*needs a shitton of parameters*/) {
		
	}

	public Node getParent() {
		return parent;
	}
	
	public Node getLeft() {
		return left;
	}
	
	public Node getRight() {
		return right;
	}
	
	public int getKey() {
		return timePos; //moment in the timeline
	}
	
	public int getP() {
		return 1; // OR -1 according to start or end
	}
	
	public int getVal() {
		return 0;// formula
	}
	
	public int getMaxVal() {
		return 0;// formula
	}
	
	Endpoint getEndpoint() {
		return point;
	}
	
	Endpoint getEmax() {
		return null;// Endpoint owner of maxval
	}
	
	int getColor() {
		return color;
	}
	
}
