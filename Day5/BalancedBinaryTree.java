class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class BalancedBinaryTree {
    // Helper class to store the result of subtree balance status and its height
    static class TreeInfo {
        boolean isBalanced;
        int height;

        TreeInfo(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }

    // Main method to check if the tree is balanced
    public boolean isBalanced(TreeNode root) {
        return checkBalance(root).isBalanced;
    }

    // Helper method to check the balance of the tree and calculate the height
    private TreeInfo checkBalance(TreeNode node) {
        // An empty tree is balanced and has a height of -1
        if (node == null) {
            return new TreeInfo(true, -1);
        }

        // Check balance and height of left subtree
        TreeInfo left = checkBalance(node.left);
        if (!left.isBalanced) {
            return new TreeInfo(false, 0);
        }

        // Check balance and height of right subtree
        TreeInfo right = checkBalance(node.right);
        if (!right.isBalanced) {
            return new TreeInfo(false, 0);
        }

        // Current node is balanced if the height difference between left and right subtree is <= 1
        boolean isBalanced = Math.abs(left.height - right.height) <= 1;

        // Height of the current node is max height of left or right subtree plus one
        int height = Math.max(left.height, right.height) + 1;

        return new TreeInfo(isBalanced, height);
    }

    // Main method to test the implementation
    public static void main(String[] args) {
        BalancedBinaryTree treeChecker = new BalancedBinaryTree();

        // Test Case 1: Unbalanced Tree
        TreeNode unbalancedRoot = new TreeNode(1);
        unbalancedRoot.left = new TreeNode(2);
        unbalancedRoot.right = new TreeNode(3);
        unbalancedRoot.left.left = new TreeNode(4);
        unbalancedRoot.left.left.left = new TreeNode(5); // Adding additional node to make it unbalanced

        System.out.println("Is the first tree balanced? " + treeChecker.isBalanced(unbalancedRoot)); // Output: false

        // Test Case 2: Balanced Tree
        TreeNode balancedRoot = new TreeNode(1);
        balancedRoot.left = new TreeNode(2);
        balancedRoot.right = new TreeNode(3);
        balancedRoot.left.left = new TreeNode(4);
        balancedRoot.left.right = new TreeNode(5);
        balancedRoot.right.left = new TreeNode(6);
        balancedRoot.right.right = new TreeNode(7);

        System.out.println("Is the second tree balanced? " + treeChecker.isBalanced(balancedRoot)); // Output: true

        // Test Case 3: Another Balanced Tree with slightly different structure
        TreeNode anotherBalancedRoot = new TreeNode(1);
        anotherBalancedRoot.left = new TreeNode(2);
        anotherBalancedRoot.right = new TreeNode(3);
        anotherBalancedRoot.left.left = new TreeNode(4);
        anotherBalancedRoot.left.right = new TreeNode(5);
        anotherBalancedRoot.right.left = new TreeNode(6);

        System.out.println("Is the third tree balanced? " + treeChecker.isBalanced(anotherBalancedRoot)); // Output: true
    }
}
