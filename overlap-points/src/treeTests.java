import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.jupiter.api.Test;

public class treeTests {

	private static final int RED = 0, BLACK = 1;
	Intervals lineCollection;
	RBTree Tree;
	
	@Before
	public void testInit() {
		lineCollection = new Intervals();
		Tree = lineCollection.getRBTree();
	}

	@Test
	public void startingTest() {
		testInit();
		assertEquals(Tree.getRoot(), Tree.getNILNode());
		assertEquals(Tree.getRoot().parent, Tree.getNILNode());
		assertEquals(Tree.getRoot().right, Tree.getNILNode());
		assertEquals(Tree.getRoot().left, Tree.getNILNode());
	}
	
	/**
	 * Root should be node one and it should reference Nil
	 */
	@Test
	public void testOneInsertion() {
		testInit();
		Node one = new Node(new Endpoint(5, 0, 1), Tree.getNILNode());
		Tree.insert(one);

		assertEquals(Tree.getRoot(), one);
		assertEquals(Tree.getRoot().parent, Tree.getNILNode());
		assertEquals(Tree.getRoot().right, Tree.getNILNode());
		assertEquals(Tree.getRoot().left, Tree.getNILNode());
	}
	
	@Test
	public void testSecondInsertion() {
		testOneInsertion();
		
		Node two = new Node(new Endpoint(6, 0, -1), Tree.getNILNode());
		Tree.insert(two);

		assertEquals(Tree.getRoot().right, two);
		assertEquals(Tree.getRoot().right.parent, Tree.getRoot());

		assertEquals(Tree.getRoot().right.color, RED);
		assertEquals(Tree.getRoot().color, BLACK);
		
	}
}
