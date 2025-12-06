import java.util.NoSuchElementException;

/**
 * BinaryTree
 *
 * one of the greatest files ever existed.
 *
 * Author: Ayham Mahmoud Atallah.
 * Date of Strat: wed/December/3/2025.
 * Last Edited on: Friday/December/5/2025.
 *
 */
public class BinarySearchTree {
    Node root;

    public BinarySearchTree() {
        root = null;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    private boolean isEmpty() {
        return root == null;
    }

    /**
     * Prints All the nodes in this Binary Search Tree.
     */
    public void preOrderPrint() {
        preOrderPrint(root);
    }

    /**
     * preOrderPrint Helper.
     */
    private void preOrderPrint(Node node) {
        // Base Case.
        if (node == null)
            return;

        System.out.print(" " + node.getData());
        preOrderPrint(node.getLeft());
        preOrderPrint(node.getRight());
    }

    /**
     * PostOrder.
     */
    public void postOrderPrint() {
        postOrderPrint(root);
    }

    private void postOrderPrint(Node node) {
        // Base Case.
        if (node == null)
            return;

        preOrderPrint(node.getLeft());
        preOrderPrint(node.getRight());
        System.out.print(" " + node.getData());
    }

    /**
     * inOrder.
     */
    public void inOrderPrint() {
        inOrderPrint(root);
    }

    private void inOrderPrint(Node node) {
        // Base Case.
        if (node == null)
            return;

        preOrderPrint(node.getLeft());
        System.out.print(" " + node.getData());
        preOrderPrint(node.getRight());
    }

    /**
     * add value to this Binary Search Tree.
     * 
     * @param data
     */
    public void add(int data) {
        if (isEmpty()) {
            root.setData(data);
            return;
        }

        Node newNode = new Node(data);
        Node curr = root;
        Node parent = null;

        while (true) {
            parent = curr;

            if (data > curr.getData()) {
                curr = curr.getRight();
                if (curr == null) {
                    parent.setRight(newNode);
                    break;
                }
            } else {
                curr = curr.getLeft();

                if (curr == null) {
                    parent.setLeft(newNode);
                    break;
                }
            }
        }

    }

    /**
     * add value to this Binary Search Tree.
     * Using Recurstion.
     */
    public void insert(int data) {

        if (isEmpty()) {
            root = new Node(data);
            return;
        }

        Node newNode = new Node(data);
        Node curr = root;
        Node parent = null;
        insertHelper(parent, curr, newNode);

    }

    /**
     * insertHelper method.
     */
    private void insertHelper(Node parent, Node curr, Node newNode) {
        int data = newNode.getData();

        // Base Case.
        if (curr == null && data > parent.getData()) {
            parent.setRight(newNode);
            return;
        }
        if (curr == null && data <= parent.getData()) {
            parent.setLeft(newNode);
            return;
        }

        if (data > curr.getData()) {
            insertHelper(curr, curr.getRight(), newNode);
        } else {
            insertHelper(curr, curr.getLeft(), newNode);
        }
    }

    /**
     * Returns the number of leves in this Binary Search Tree.
     */
    public int height() {
        return heightHelper(root);
    }

    /**
     * hight Helper method
     */
    private int heightHelper(Node root) {
        if (root == null)
            return 0;

        return 1 + Math.max(heightHelper(root.getLeft()), heightHelper(root.getRight()));
    }

    /**
     * Returns true if the this node has at least one child, fasle if it doesn't
     * have any child.
     */
    private boolean isParent(Node node) {
        return node.getLeft() != null || node.getRight() != null;
    }

    /**
     * Retuns true if the given node is leaf (doesn't have childs), false otherwise.
     */
    private boolean isLeaf(Node node) {
        return node.getLeft() == null && node.getRight() == null;
    }

    /**
     * printLeaf
     */
    public void printLeaf() {
        if (root == null)
            return;

        printLeafHelper(root);
        System.out.println();
    }

    /**
     * printLeaf Helper Method.
     *
     * Traverse through the tree and if the current node is leaf then print it.
     */
    private void printLeafHelper(Node root) {
        if (root == null)
            return;

        if (isLeaf(root)) {
            System.out.print(root.getData() + " ");
            return;
        }

        printLeafHelper(root.getLeft());
        printLeafHelper(root.getRight());
    }

    /**
     * printParent
     */
    public void printParent() {
        if (isEmpty())
            throw new NoSuchElementException("Root is Null");
        printParentHelper(root);
        System.out.println();
    }

    /**
     * printParent helper method.
     *
     * Traverse through the tree and if the current node is parent then print it.
     */
    private void printParentHelper(Node root) {
        // Base Case.
        if (root == null)
            return;

        if (isParent(root)) {
            System.out.print(root.getData() + " ");
        }

        printParentHelper(root.getLeft());
        printParentHelper(root.getRight());
    }

    /**
     * Returns the number of leaf nodes in theis Binary Search Tree.
     *
     * @throws NoSuchElementException
     */
    public int countLeaf() {
        if (isEmpty())
            throw new NoSuchElementException("Root is Null");
        return countLeafHelper(root);
    }

    /**
     * contLeaf helper method
     * 
     * @param root
     */
    private int countLeafHelper(Node root) {
        if (root == null)
            return 0;

        if (isLeaf(root))
            return 1 + (countLeafHelper(root.getLeft()) + countLeafHelper(root.getRight()));

        return (countLeafHelper(root.getLeft()) + countLeafHelper(root.getRight()));
    }

    /**
     * countParent
     *
     * Returns the number of parent nodes int this binary search tree.
     * 
     * @throws NoSuchElementException
     */
    public int countParent() {
        if (isEmpty())
            throw new NoSuchElementException("Root is Null");
        return countParentHelper(root);
    }

    /**
     * countParentHelper method.
     */
    private int countParentHelper(Node root) {
        if (root == null)
            return 0;

        if (isParent(root))
            return 1 + (countParentHelper(root.getLeft()) + countParentHelper(root.getRight()));

        return (countParentHelper(root.getLeft()) + countParentHelper(root.getRight()));
    }

    /**
     * print only the left children in this BST.
     * 
     * @throws NoSuchElementException
     */
    public void printLeftChildOnly() {
        if (isEmpty())
            throw new NoSuchElementException("Root is Null");
        printLeftChildOnlyHelper(root);
        System.out.println();
    }

    /**
     * printLeftChildOnly Helper Method.
     */
    private void printLeftChildOnlyHelper(Node root) {
        // Base Case.
        if (root == null || isLeaf(root))
            return;

        if (root.getLeft() != null)
            System.out.print(root.getLeft().getData() + " ");

        printLeftChildOnlyHelper(root.getLeft());
        printLeftChildOnlyHelper(root.getRight());
    }

    /**
     * print only the right children in this BST.
     */
    public void printRightChildOnly() {
        if (isEmpty())
            throw new NoSuchElementException("Root is Null");
        printRightChildOnlyHelper(root);
        System.out.println();
    }

    /**
     * printRightChildOnly Helper Method.
     */
    private void printRightChildOnlyHelper(Node root) {
        if (root == null || isLeaf(root))
            return;

        if (root.getRight() != null)
            System.out.print(root.getRight().getData() + " ");

        printRightChildOnlyHelper(root.getLeft());
        printRightChildOnlyHelper(root.getRight());
    }

    /**
     * Returns the minimum value in this BST.
     *
     * By Traversle to the far left child we find the minimum value in this BST.
     */
    public int minimumElement() {
        if (isEmpty())
            throw new NoSuchElementException("Root is Null");
        return minimumElementHelper(root);
    }

    /**
     * minimumElement Helper Method.
     */
    private int minimumElementHelper(Node root) {
        if (root.getLeft() == null)
            return root.getData();

        return minimumElementHelper(root.getLeft());
    }

    /**
     * Returns the maximum value in this BST.
     */
    public int maximumElement() {
        if (isEmpty())
            throw new NoSuchElementException("Root is Null");
        return maximumElementHelper(root);
    }

    /**
     * maximumElement Helper Method.
     */
    private int maximumElementHelper(Node root) {
        if (root.getRight() == null)
            return root.getData();

        return maximumElementHelper(root.getRight());
    }

    /**
     * returns true if the element is found in this Binary Search Tree, false if the
     * element was not found in this BST.
     *
     * We used Binary Search Algorithm here for searching the target (element)
     * the time complixty is O(Logn) -- pretty fast.
     * 
     * @param target
     * @throws NoSuchElementException
     */
    public boolean search(int target) {
        if (isEmpty())
            throw new NoSuchElementException("Root is Null");

        return searchElement(root, target) != null;
    }

    /**
     * serach helper method
     * Returns the node address if the node was found in this binary Search Tree,
     * null if the node was not found in this binary Search tree.
     * 
     * @param root
     * @param target
     */
    private Node searchElement(Node root, int target) {
        if (root == null)
            return null;

        if (root.getData() == target)
            return root;

        if (root.getData() > target)
            return searchElement(root.getLeft(), target);
        else
            return searchElement(root.getRight(), target);
    }

    /**
     * Todo:
     * dlete method.
     */
    public Node delete(int target) {
        if (isEmpty())
            throw new NoSuchElementException("Root is null");

        // case there is just the root.
        if (root.getLeft() == null && root.getRight() == null) {
            Node oldValue = root;
            root = null;
            return oldValue;
        }

        Node oldValue = new Node();

        // First Search for the node before the target node.
        Node nodeBefore = getNodeBeforeThis(root, null, target);
        Node targetNode = new Node();

        if (nodeBefore.getLeft() != null && nodeBefore.getLeft().getData() == target)
            targetNode = nodeBefore.getLeft();
        else if (nodeBefore.getRight() != null && nodeBefore.getRight().getData() == target)
            targetNode = nodeBefore.getRight();

        oldValue = targetNode;

        // Case of a leaf node.
        if (isLeaf(targetNode)) {

            // when the laef node is on the left side.
            if (targetNode == nodeBefore.getLeft())
                nodeBefore.setLeft(null);

            // when the leaf node is on the right side.
            // if it was not the left side then it sure to be the right one.
            else
                nodeBefore.setRight(null);

        }
        // when the targetNode is a parent of one node.
        // conect the nodeBefore to the child.
        else if (hasOneChild(targetNode)) {

            // when the child is on the left side.
            if (targetNode.getLeft() != null) {

                // define the position of the targetNode node is it in the left part or the
                // right part.
                if (targetNode == nodeBefore.getLeft())
                    nodeBefore.setLeft(targetNode.getLeft());
                else
                    nodeBefore.setRight(targetNode.getLeft());

            }
            // when the child is on the right.
            else if (targetNode.getRight() != null) {

                // define the position of the targetNode node is it in the left part or the
                // right part.
                if (targetNode == nodeBefore.getLeft())
                    nodeBefore.setLeft(targetNode.getRight());
                else
                    nodeBefore.setRight(targetNode.getRight());
            }
        }

        // when the targetNode is a parent of two nodes
        // get the smallest node.
        else if (isParent(targetNode)) {

            Node tempParent = targetNode;
            Node temp = targetNode.getRight();

            while (temp.getLeft() != null) {
                tempParent = temp;
                temp = temp.getLeft();
            }

            // copy the value from temp to targetNode.
            targetNode.setData(temp.getData());

            // Delete the temp Node.
            if (tempParent.getLeft() == temp)
                tempParent.setLeft(temp.getRight());
            else
                tempParent.setRight(temp.getRight());

        }

        return oldValue;
    }

    /**
     * Returns the Address of the node before the target node.
     */
    private Node getNodeBeforeThis(Node root, Node prev, int target) {
        if (root == null)
            return root;

        if (root.getData() == target)
            return prev;

        if (root.getData() > target)
            return getNodeBeforeThis(root.getLeft(), root, target);
        else
            return getNodeBeforeThis(root.getRight(), root, target);
    }

    /**
     * Returns true if the node has one Child, false if the has more or no Child.
     * 
     * @param node
     */
    private boolean hasOneChild(Node node) {
        if (node.getLeft() != null && node.getRight() == null)
            return true;
        else if (node.getLeft() == null && node.getRight() != null)
            return true;
        else
            return false;
    }

}
