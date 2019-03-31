/**
 * @author Diego Realpe, diegort
 * @author Adrian Hamill, adrianh
 */
public class Intervals {

	private static final int START = 1, END = -1;
	private RBTree intervalTree = new RBTree();
	private int IDcounter = 0;

	public Intervals() {
		// Leave empty, default initialize everything
	}

	/**
	 * Takes in the integers of our interval and makes endpoints out of them and adds them into the tree
	 * @param a
	 * @param b
	 */
	public void intervalInsert(int a, int b) {
		// Making interval with two endpoints
		// checking which is start and which is end
		int firstval = Math.min(a, b);
		Endpoint beginning = new Endpoint(firstval, IDcounter, START);
		int secondval = Math.max(a, b);
		Endpoint end = new Endpoint(secondval, IDcounter, END);
		IDcounter++;
		// inserting interval into tree
		intervalTree.insertPair(beginning, end);
	}

	/**
	 * Takes in an interval ID and deletes the corresponding interval
	 * @param intervalID
	 * @return
	 */
	public boolean intervalDelete(int intervalID) {
		return false; // this probably aint gonna change
	}

	/**
	 * Returns the value of the Endpoint with highest overlap NOT necessarily the
	 * root. but instead returns the emax of the entire tree, found in root
	 * 
	 * @return
	 */
	public int findPOM() {
		return intervalTree.getRoot().getMaxVal();
	}

	/**
	 * Returns the Red black tree that has been created by the above methods
	 * @return
	 */
	public RBTree getRBTree() {
		return intervalTree;
	}
}
