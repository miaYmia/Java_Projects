import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class BinarySearchTreeTest {
	BinarySearchTree<Integer> bst = new BinarySearchTree<>(); // intialize tree
	@BeforeEach
	void setUp() {
		//add integers to the tee
		/*    4
			/   \
		   2	 8
		  / \    / \
		 1  3   5  10
		       /
		      7
		 */
		bst.add(4); // root
		bst.add(2);
		bst.add(8);
		bst.add(1);
		bst.add(3);
		bst.add(5);
		bst.add(10);
		bst.add(7);
	}
	@Test
	void testDepth() {
		// test depth of several nodes
		assertEquals(3, bst.depth(7), "Depth of node 7 should be 3"); // a node from the right subtree
		assertEquals(0, bst.depth(4), "Depth of root node should be 0"); // test root node
		assertEquals(1, bst.depth(2), "Depth of node 2 should be 1"); // a node from the left subtree
		// test a non-existent node
		assertEquals(-1, bst.depth(100), "Depth of a non-existent node should be -1"); // a node from the left subtree
	}

	@Test
	void testFindNode() {

		// test finding root
		assertEquals(4, bst.findNode(4).value, "Node 4 should have value 4");
		//test find leaves
		assertEquals(1, bst.findNode(1).value, "Node 4 should have value 1");
		assertEquals(7, bst.findNode(7).value, "Node 4 should have value 7");
		// test non-existent node
		assertNull(bst.findNode(100), "findNode should return null for a non-existent node");

		assertNull(bst.findNode(null),"findNode should return null for null node");
		// test null node

	}

	@Test
	void testHeight() {

		// test root node
		assertEquals(3,  BinarySearchTree.height(bst.findNode(4)), "height of root 10 should be 3");
		// test height of several nodes
		assertEquals(0, BinarySearchTree.height(bst.findNode(7)), "height of leaf 7 should be 0"); // a node from the right subtree
		assertEquals(0, BinarySearchTree.height(bst.findNode(1)), "height of leaf 1 should be 0"); // a node from the left subtree
		// test a non-existent node
		assertEquals(-1, BinarySearchTree.height(bst.findNode(100)), "height of a non-existent node should be -1");

		// test null node returns -1
		assertEquals(-1, BinarySearchTree.height(null), "height of a null node should be -1");
	}

	@Test
	void testIsBalancedNode() {

		// test root is balanced
		assertTrue(BinarySearchTree.isBalancedNode(bst.findNode(4)), "the root node is balanced");
		// test left subtree root node
		assertTrue(BinarySearchTree.isBalancedNode(bst.findNode(2)), "the root node of left subtree is balanced");
		// test right subtree root node
		assertTrue(BinarySearchTree.isBalancedNode(bst.findNode(8)), "the root node of right subtree balanced");
		// test leaf node
		assertTrue(BinarySearchTree.isBalancedNode(bst.findNode(7)), "the leaf node of right subtree balanced");

		// now let's make the tree not balanced
		bst.add(0);
		bst.add(-1);
		assertFalse(BinarySearchTree.isBalancedNode(bst.findNode(2)), "the root node of left subtree is not balanced");

		// test null node
		assertTrue(BinarySearchTree.isBalancedNode(null), "All null nodes are balanced");

	}

	@Test
	void testIsBalancedTree() {

		// our bst tree should be balanced
		assertTrue(bst.isBalanced(), "This tree is balanced");

		// let's set up a new tree
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();

		// test the empty tree
		assertTrue(tree.isBalanced(), "An empty tree is balanced");

		// let's add a root node
		tree.add(10);
		assertTrue(tree.isBalanced()); 	// the tree with just one node should be balanced

		// let's make the new tree not balanced
		tree.add(9);
		tree.add(8);
		// now the tree is not balanced
		assertFalse(tree.isBalanced(), "The tree has left height of 2 and right height of 0. It is unbalanced");
	}
}