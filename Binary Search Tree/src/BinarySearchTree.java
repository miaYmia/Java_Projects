public class BinarySearchTree<E extends Comparable<E>> {
    @SuppressWarnings("unused")
    private static final long serialVersionUID = 29384723897L;

    class Node {
        E value;
        Node left = null;
        Node right = null;

        Node(E value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "( " + value + " )";
        }
    }

    protected Node root = null;
    protected int size = 0;

    /**
     * Adds value to BST and returns true only if value is non-null and does not
     * already exist in the BST.
     * 
     * @param val Value to be added to BST
     * @return True only if element was added to BST
     */
    public boolean add(E val) {
        if (val == null)
            return false;
        int size = this.size;
        this.root = add(this.root, val);
        return size != this.size;
    }

    /**
     * Adds a value to the tree starting at Node n, returning the node occupying the
     * same position as was given, which may be a new node containing the given
     * value if the given node is null, or it may be an existing node if that node
     * already contains the given value.
     * 
     * @param n   The subtree to which to add the value
     * @param val The value to add
     * @return The updated subtree root (possibly null) after adding the value
     */
    private Node add(Node n, E val) {
        if (n == null) {
            this.size++;
            return new Node(val);
        }

        int cmp = val.compareTo(n.value);
        if (cmp < 0)
            n.left = add(n.left, val);
        else if (cmp > 0)
            n.right = add(n.right, val);
        return n;
    }

    /**
     * Removes value from BST and returns true only if value is non-null and existed
     * in BST.
     * 
     * @param val The value to remove
     * @return True only if the value was removed from the BST
     */
    public boolean remove(E val) {
        if (val == null)
            return false;
        int size = this.size;
        this.root = remove(this.root, val);
        return size != this.size;
    }

    /**
     * Removes a value from the tree starting at Node n, returning the node
     * occupying the same position as was given (which can be null if the given node
     * gets removed).
     * 
     * The below code follows the BST node deletion algorithm described in Data
     * Structures and Algorithms in Java, Section 11.1.2, page 464.
     * 
     * @param n   The subtree from which to remove the value
     * @param val The value to remove
     * @return The updated subtree root (possibly null) after removing the value
     */
    private Node remove(Node n, E val) {
        if (n == null)
            return null;
        int cmp = val.compareTo(n.value);
        if (cmp < 0)
            n.left = remove(n.left, val);
        else if (cmp > 0)
            n.right = remove(n.right, val);
        else if (n.left == null) {
            /*
             * The value of n.right is the correct return value for this method whether it
             * is null or a Node. Thus there is no need for a (n.left == null && n.right ==
             * null) branch.
             */
            this.size--;
            return n.right;
        } else if (n.right == null) {
            this.size--;
            return n.left;
        } else {
            E pred = maxValue(n.left);
            n.value = pred;
            n.left = remove(n.left, pred);
        }

        return n;
    }

    /**
     * Returns the maximum value contained in the subtree rooted at the given Node,
     * or null if the given subtree is empty.
     * 
     * @param n The subtree from which to search for the maximum value
     * @return The value of the node at the rightmost position, which holds the
     *         maximum value of the represented sorted set
     */
    protected E maxValue(Node n) {
        if (n == null)
            return null;
        if (n.right == null)
            return n.value;
        return maxValue(n.right);
    }

    /*
     * ASSIGNMENT METHODS: Implement the methods below.
     */

    /**
     * Given a value that is stored in this BST, this method returns the
     * corresponding Node that holds it, or null if the value is null or does
     * not exist in this BST.
     * 
     * @param val The value to find in the BST
     * @return The Node containing the value if it exists in this BST, else null
     */
    protected Node findNode(E val) {
        if (val == null) return null; // check if input val is null

        Node current = root;
        while (current != null) { // start at the root and travese the tree
            int compare = val.compareTo(current.value); //get the result from comparing value to the current node
            if (compare < 0) current = current.left; // if val is less than current node, move current to left node
            else if (compare > 0) current = current.right; // if val is bigger than current node, move current to left node
            else return current; // if val is neither bigger nor smaller, meaning val == current, return current
        }
        // keep on iterating until current becomes a null node. If nothing is returned from while loop, return null
        return null;
    }


    /**
     * Given a value, this method returns the depth of its Node, or -1 if the
     * value is null or does not exist in this BST. The depth is defined as the
     * number of edges from that node to the root.
     * 
     * @param val The value of the node from which to calculate depth
     * @return The number of edges from the node containing the given value in
     *         this BST to the root of the BST, or -1 if value does not exist
     *         in the BST
     */
    protected int depth(E val) {
        if (val == null) return -1; // handle null input

        Node current = root; // set current to root so we start traversing at root
        int depth = 0; // start depth at 0

        while (current != null) {// start at the root and traverse the tree
            int compare = val.compareTo(current.value); //get the result from comparing value to the current node
            if (compare < 0 ) {
                current= current.left; // if val is less than current node, move current to left node
                depth ++; // increment depth
            } else if (compare > 0 ) {
                current= current.right; // if val is less than current node, move current to left node
                depth ++; // increment depth
            } else {
                return depth; // found the node and return depth
            }
        }
        return -1; // if after while loop we didn't find anything, return -1
    }

    /**
     * For the subtree rooted at the given Node, this method returns the height
     * of the subtree. The height of a subtree is defined as the maximum number
     * of edges between the root of the subtree and any descendant leaf. The
     * height of a leaf is 0. The height of an empty subtree, represented as a
     * null Node, is defined to be -1.
     * 
     * @param n Node representing a subtree rooted at that node
     * @return The height of the given subtree
     */
    protected static int height(BinarySearchTree<?>.Node n) {
        if (n == null) return -1;
        // recursively calculate the height of the left subtree
        int leftHeight = height(n.left);
        // recursively calculate the height of the right subtree
        int rightHeight = height(n.right);
        // the height of the current node is the max height of its subtrees + 1
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * Given null, returns true. Given an instance of Node, returns true only if
     * the absolute value of the difference in heights of its left and right
     * children is less than 2.
     * 
     * @param n The node to test for balance
     * @return True only if node is null or is non-null and the absolute value
     *         of the difference in heights of its left and right children is
     *         less than 2
     */
    protected static boolean isBalancedNode(BinarySearchTree<?>.Node n) {
        if (n == null) return true;

        // recursively calculate the height of the left subtree
        int leftHeight = height(n.left);
        // recursively calculate the height of the right subtree
        int rightHeight = height(n.right);

        // return true if the absolute difference is less than 2
        return Math.abs(leftHeight - rightHeight) < 2;
    }

    /**
     * This method returns false if and only if there exists any node in the
     * tree for which isBalancedNode(n) returns false; otherwise it returns
     * true.
     * 
     * @return True only if the entire tree is balanced
     */
    public boolean isBalanced() {
        // use the helper method and start the check at root node
        return checkBalance(root) != -1;
    }

    /**
     * helper method to check if the subtree rooted at n is balanced or not
     * @param n the current root of the tree
     * @return height of subtree if balanced; -1 if not balanced
     */
    private int checkBalance(Node n) {
        // an empty subtree is balanced and has height 0
        if (n == null) return 0;
        // recursively check the height and balance of the left subtree
        int leftHeight = checkBalance(n.left);
        // if left subtree is unbalance, move back up to the isBalanced() method so it can return false
        if (leftHeight == -1) return -1;

        // do the same for right subtree. Recursively check the height and balance of the left subtree
        int rightHeight = checkBalance(n.right);
        // if left subtree is unbalance, move back up to the isBalanced() method so it can return false
        if (rightHeight == -1) return -1;

        // then check if the current node is balanced, return -1 if unbalanced
        if (Math.abs(leftHeight - rightHeight) > 1) return -1;

        // after everything, return the height of the current subtree if balanced
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * OPTIONAL CHALLENGE: Produces the same result as isBalanced() but does so
     * in O(n) time.
     * 
     * Ungraded. Do not attempt until after you have a working isBalanced() for
     * grading.
     * 
     * @return True only if the entire tree is balanced
     */
    public boolean isBalancedFast() {
        /*
        Note: same implementation as isBalanced() because that is O(n).
        We call checkBalance(Node n) method recursively, starting at the root and traverse each node of the tree only once.
        For each node, we do a fixed amount of work at O(1) efficiency. Leading to O(n) efficiency, with n = the number of nodes in the tree.
         */
        return checkBalance(root) != -1;
    }
}
