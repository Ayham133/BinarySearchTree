import java.util.NoSuchElementException;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(3);
        tree.insert(2);
        tree.insert(1);
        tree.insert(5);
        tree.insert(6);
        tree.insert(4);

        tree.preOrderPrint();
        System.out.println("is BST: " + isValidBinarySearchTree(tree.getRoot()));

    }

    static Node mergeTwoBinaryTrees(Node root1, Node root2) {
        if (root1 == null || root2 == null)
            return (root1 == null) ? root1 : root2;

        BinarySearchTree newTree = new BinarySearchTree();
        newTree.getRoot().setData(root1.getData() + root2.getData());

        mergeTwoBinaryTreesHelper(newTree.getRoot(), root1, root2);

        return newTree.getRoot();
    }

    private static void mergeTwoBinaryTreesHelper(Node newRoot, Node root1, Node root2) {
        if (root1 == null && root2 == null)
            return;

        newRoot.setRight(new Node(getDataFromNode(root1.getRight()) + getDataFromNode(root2.getRight())));
        newRoot.setLeft(new Node(getDataFromNode(root1.getLeft()) + getDataFromNode(root2.getLeft())));

        mergeTwoBinaryTreesHelper(newRoot, root1.getRight(), root2.getRight());
        mergeTwoBinaryTreesHelper(newRoot, root1.getLeft(), root2.getLeft());

    }

    private static int getDataFromNode(Node node) {
        return (node != null) ? node.getData() : 0;
    }

    /**
     * Returns true if this tree is a binary serach tree, fasle otherwise.
     */
    static boolean isValidBinarySearchTree(Node root) {
        if (root == null)
            throw new NoSuchElementException();

        return validateTree(root, root.getLeft().getData(), root.getRight().getData());
    }

    private static boolean validateTree(Node root, int left, int right) {
        if (root == null)
            return true;

        if (!(root.getData() > left && root.getData() < right))
            return false;

        return (validateTree(root.getLeft(), left, root.getData())
                && validateTree(root.getRight(), root.getData(), right));
    }
}
