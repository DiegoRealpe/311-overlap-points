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

		//insert in the right place?
		assertEquals(Tree.getRoot().right, two);
		assertEquals(Tree.getRoot().right.parent, Tree.getRoot());

		//colors are correct?
		assertEquals(Tree.getRoot().right.color, RED);
		assertEquals(Tree.getRoot().color, BLACK);
		
		//vals are correct?
		assertEquals(Tree.getRoot().getVal(), 0);
		assertEquals(Tree.getRoot().right.getVal(), -1);
		
		//Maxval, emax are wrong
		
	}
	
	@Test
	public void testThirdInsertion() {
		testInit();
		Node one = new Node(new Endpoint(5, 0, 1), Tree.getNILNode());
		Tree.insert(one);
		Node two = new Node(new Endpoint(6, 0, 1), Tree.getNILNode());
		Tree.insert(two);
		Node three = new Node(new Endpoint(7, 0, -1), Tree.getNILNode());
		Tree.insert(three);

		//Did it rotate correctly
		assertEquals(two, Tree.getRoot());
		assertEquals(three.parent, two);
		assertEquals(two.right, three);
		assertEquals(one.parent, two);
		assertEquals(two.left, one);

		//colors are correct?
		assertEquals(one.color, RED);
		assertEquals(two.color, BLACK);
		assertEquals(three.color, RED);
		
		//vals are correct?
		assertEquals(Tree.getRoot().getVal(), 1);
		assertEquals(Tree.getRoot().right.getVal(), -1);
		assertEquals(Tree.getRoot().left.getVal(), 1);
		
		//Maxval, emax are wrong
		assertEquals(Tree.getRoot().getMaxVal(), 2);
		
	}
}
