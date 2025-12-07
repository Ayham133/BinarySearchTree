/**
 * Main
 */
public class Main {

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        tree.insert(50);
        tree.insert(60);
        tree.insert(20);
        tree.insert(55);

        tree.preOrderPrint();

        tree.delete(20);
    }
}
