/**
 * Main
 */
public class Main {

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();

        bst.insert(12);
        bst.insert(15);
        bst.insert(5);
        bst.insert(16);
        bst.insert(6);
        bst.insert(4);

        bst.postOrderPrint();
        System.out.println();

        System.out.println(bst.delete(6).getData());

        System.out.println();
        bst.postOrderPrint();

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
