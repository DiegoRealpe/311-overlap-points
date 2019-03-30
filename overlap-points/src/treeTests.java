import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.jupiter.api.Test;

public class treeTests {
	
	Intervals lineCollection;
	RBTree Tree;
	
	@Before
	public void testInitial() {
		lineCollection = new Intervals();
		Tree = lineCollection.getRBTree();
	}

	@Test
	public void startingTest() {
		assertEquals(Tree.getRoot(), Tree.getNILNode());
		assertEquals(Tree.getRoot().right, Tree.getNILNode());
		assertEquals(Tree.getRoot().left, Tree.getNILNode());
	}
	
	@Test
	public void testOneInsertion() {
		
	}
	
}
