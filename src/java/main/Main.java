
/**
 * Main
 */
public class Main {

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(0);
        tree.insert(-3);
        tree.insert(-10);
        tree.insert(2);
        tree.insert(1);

        tree.preOrderPrint();
        LCA(tree.getRoot(), -3, 2);
        System.out.println();

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
        return validateTree(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static boolean validateTree(Node root, int left, int right) {
        if (root == null)
            return true;

        if (root.getData() <= left || root.getData() >= right)
            return false;

        return (validateTree(root.getLeft(), left, root.getData())
                && validateTree(root.getRight(), root.getData(), right));
    }

    static Node sortedArrayToBST(int array[]) {
        if (array.length == 0 || array == null)
            return null;

        return sortedArrayToBSTHelper(array, 0, array.length - 1);
    }

    private static Node sortedArrayToBSTHelper(int[] array, int left, int right) {
        if (left > right)
            return null;

        int midPoint = (left + right) / 2;
        int midValue = array[midPoint];

        Node root = new Node(midValue);

        root.setLeft(sortedArrayToBSTHelper(array, left, midPoint - 1));
        root.setRight(sortedArrayToBSTHelper(array, midPoint + 1, right));

        return root;
    }

    static Node LCA(Node root, Node p, Node q) {
        if (root == null)
            return null;

        else if (root == p || root == q)
            return root;

        else if (root.getLeft() == p && root.getRight() == q) {
            return root;
        }

        else if (root.getData() > p.getData() && root.getData() > q.getData()) {
            return LCA(root.getLeft(), p, q);
        } else if (root.getData() < p.getData() && root.getData() < q.getData()) {
            return LCA(root.getRight(), p, q);
        } else {
            return root;
        }
    }

}
