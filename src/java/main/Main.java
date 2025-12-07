/**
 * Main
 */
public class Main {

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        AVLTree tree = new AVLTree();

        tree.insert(10);
        tree.insert(20);
        tree.insert(50);

        System.out.println(tree.isBalanced());
        tree.preOrderPrint();
        // bst.printLeftChildOnly();
        //
        // bst.printRightChildOnly();
        //
        // bst.printLeaf();
        // System.out.println(bst.countLeaf());
        //
        // bst.printParent();
        // System.out.println(bst.countParent());
        //
        // bst.preOrderPrint();
        // System.out.println();
        //
        // System.out.println("BST Height is: " + bst.height());
        //
        // System.out.println("Search for 14: " + bst.search(14));
        //
        // System.out.println("The Minimum Value in this BST is: " +
        // bst.minimumElement());
        //
        // System.out.println("The Maximum Value in this BST is: " +
        // bst.maximumElement());
    }
}
