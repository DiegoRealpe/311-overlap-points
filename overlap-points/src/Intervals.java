/**
 * @author diegort, adrianh
 */
public class Intervals {

	private static final int START = 1, END = -1;
	private RBTree intervalTree = new RBTree();
	private int IDcounter = 0;

	public Intervals() {
		// Leave empty, default initialize everything
	}

	public void intervalInsert(int a, int b) {
		// Making interval with two endpoints
		// checking which is start and which is end
		int firstval = Math.min(a, b);
		Endpoint beginning = new Endpoint(firstval, IDcounter, START);
		int secondval = Math.max(a, b);
		Endpoint end = new Endpoint(secondval, IDcounter, END);
		// inserting interval into tree
		intervalTree.insertPair(beginning, end);
	}

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

	public RBTree getRBTree() {
		return intervalTree;
	}
}
