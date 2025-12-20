import java.util.*;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.REUtil;

/**
 * BinaryTree
 *
 * one of the greatest files ever existed.
 *
 * Author: Ayham Mahmoud Atallah.
 * Date of Strat: wed/December/3/2025.
 * Last Edited on: Sat/December/20/2025. at 8:45PM UTC+2.
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
     *
     * Root Left Right
     */
    public void preOrderPrint() {
        preOrderPrint(root);
        System.out.println();
    }

    /**
     * preOrderPrint Helper.
     *
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
     *
     * Left Right Root
     */
    public void postOrderPrint() {
        postOrderPrint(root);
        System.out.println();
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
     *
     * Left Root Right
     */
    public void inOrderPrint() {
        inOrderPrint(root);
        System.out.println();
    }

    private void inOrderPrint(Node node) {
        // Base Case.
        if (node == null)
            return;

        inOrderPrint(node.getLeft());
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
        if (curr == null && data < parent.getData()) {
            parent.setLeft(newNode);
            return;
        }
        if (curr == null && data == parent.getData())
            return;

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
     * Deletse and returns the node with the given target.
     * 
     * @param target
     * @return
     */
    public Node delete(int target) {
        if (isEmpty())
            throw new NoSuchElementException("Root is null");

        Node oldValue = new Node();

        // First Search for the node before the target node.
        Node parentNode = null;
        Node targetNode = root;

        while (targetNode != null && targetNode.getData() != target) {
            parentNode = targetNode;
            if (target < targetNode.getData())
                targetNode = targetNode.getLeft();
            else
                targetNode = targetNode.getRight();
        }

        // the Binary Search Tree doesn't containe the target.
        if (targetNode == null)
            return null;

        oldValue = targetNode;

        // Case of a leaf node.
        if (isLeaf(targetNode)) {
            // when the targetNode is the root.
            if (targetNode == root) {
                root = null;
            }
            // when the laef node is on the left side.
            else if (targetNode == parentNode.getLeft())
                parentNode.setLeft(null);

            // when the leaf node is on the right side.
            // if it was not the left side then it sure to be the right one.
            else
                parentNode.setRight(null);

        }
        // when the targetNode has one node.
        // conect the parentNode to the child.
        else if (hasOneChild(targetNode)) {
            Node childNode = (targetNode.getLeft() != null) ? targetNode.getLeft() : targetNode.getRight();

            // define the position of the targetNode node is it in the left part or the
            // right part.
            if (targetNode == parentNode.getLeft())
                parentNode.setLeft(childNode);
            else
                parentNode.setRight(childNode);

        }

        // when the targetNode is a parent of two nodes
        // get the smallest node.
        else {

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

    /**
     * Returns arraylist that has the longest ptha in this BST.
     */
    public void printLongestPath() {
        if (isEmpty()) {
            System.out.println("tree is Empty");
            return;
        }

        printLongestPathHelper(root);
        System.out.println();
    }

    /**
     * printLongestPath Helper
     */
    private void printLongestPathHelper(Node root) {
        // Base Case.
        if (root == null)
            return;

        System.out.print(root.getData() + " ");

        if (heightHelper(root.getLeft()) <= heightHelper(root.getRight())) {
            printLongestPathHelper(root.getRight());
        } else
            printLongestPathHelper(root.getLeft());

    }

    /**
     * Returns the number of good nodes in this BinarySearchTree.
     * a good node is a node were the data of it is the max from the root to this
     * node.
     */
    public int countGoodNodes() {
        if (isEmpty())
            throw new NoSuchElementException();

        return countGoodNodesHelper(root, Integer.MIN_VALUE);
    }

    private int countGoodNodesHelper(Node root, int maxValue) {
        if (root == null)
            return 0;

        if (root.getData() >= maxValue) {
            maxValue = root.getData();
            return 1 + countGoodNodesHelper(root.getLeft(), maxValue) + countGoodNodesHelper(root.getRight(), maxValue);
        } else {
            return countGoodNodesHelper(root.getLeft(), maxValue) + countGoodNodesHelper(root.getRight(), maxValue);
        }

    }

    /**
     * Returns the kth smallest element in this BST.
     */
    public int kthSmallestElement(int k) {
        if (isEmpty() || k < 0)
            throw new NoSuchElementException();

        if (k == 0)
            return getRoot().getData();

        return kthSmallestElementHelper(root, k);
    }

    private int kthSmallestElementHelper(Node root, int k) {
        Stack<Integer> smallestElements = new Stack<>();
        Node current = root;

        while (current != null) {
            smallestElements.push(current.getData());
            current = current.getLeft();
        }

        if (k > smallestElements.size())
            return root.getData();

        int kthSmallestElement = smallestElements.peek();
        while (k != 0) {
            kthSmallestElement = smallestElements.pop();
            k--;
        }
        return kthSmallestElement;
    }

    /**
     * Returns sum of the k smallest element in this BST.
     */
    public int sumOfKthSmallestElement(int k) {
        if (isEmpty() || k < 0)
            throw new NoSuchElementException();

        if (k == 0)
            return getRoot().getData();

        return sumOfKthSmallestElementHelper(root, k);
    }

    private int sumOfKthSmallestElementHelper(Node root, int k) {
        Stack<Integer> smallestElements = new Stack<>();
        Node current = root;

        while (current != null) {
            smallestElements.push(current.getData());
            current = current.getLeft();
        }

        if (k > smallestElements.size())
            k = smallestElements.size();

        int sum = smallestElements.pop();
        k--;
        while (k != 0) {
            sum += smallestElements.pop();
            k--;
        }

        return sum;
    }

    /**
     * Returns the number of the non leaf nodes in this BST.
     */
    public int countNonLeafNodes() {
        if (isEmpty())
            return 0;

        return countNonLeafNodesHelper(root);
    }

    private int countNonLeafNodesHelper(Node root) {
        if (root == null || (root.getLeft() == null && root.getRight() == null))
            return 0;
        return 1 + countNonLeafNodesHelper(root.getLeft()) + countNonLeafNodesHelper(root.getRight());
    }

    /**
     * Returns the first leaf node.
     */
    public Node FirstParentDontHaveAChild() {
        if (isEmpty())
            throw new NoSuchElementException();

        return FirstParentDontHaveAChildHelper(getRoot());

    }

    private Node FirstParentDontHaveAChildHelper(Node root) {
        if (root == null || isLeaf(root))
            return root;

        int leftHeight = heightHelper(root.getLeft());
        int rightHeight = heightHelper(root.getRight());

        return (leftHeight < rightHeight) ? FirstParentDontHaveAChildHelper(root.getLeft())
                : FirstParentDontHaveAChildHelper(root.getRight());
    }

    /**
     * Prints all paths and the sum of the path.
     */
    public void printAllPathsAndSum() {
        if (isEmpty())
            throw new NoSuchElementException();

        List<Integer> currentPath = new ArrayList<>();
        printAllPathsAndSumHelper(getRoot(), currentPath);
    }

    private void printAllPathsAndSumHelper(Node root, List<Integer> currentPath) {
        if (root == null)
            return;

        currentPath.add(root.getData());

        if (isLeaf(root)) {
            System.out.println(currentPath + ": " + sumPath(currentPath, 0));
        } else {
            printAllPathsAndSumHelper(root.getLeft(), currentPath);
            printAllPathsAndSumHelper(root.getRight(), currentPath);
        }

        currentPath.remove(currentPath.size() - 1);
    }

    private int sumPath(List<Integer> path, int i) {
        if (i > path.size() - 1)
            return 0;

        return path.get(i) + sumPath(path, i + 1);
    }

    /**
     * Returns the maximum sum in a subtree, it could be the whole tree.
     */
    public int maximumSumInSubtree() {
        if (isEmpty())
            return 0;

        maximum = Integer.MIN_VALUE;
        return maximumSumInSubtreeHelper(getRoot());
    }

    private int maximum = Integer.MIN_VALUE;

    /**
     * maximumSumInSubtree Helper method.
     */
    private int maximumSumInSubtreeHelper(Node root) {
        if (root == null)
            return 0;

        int currentSumOfSubTree = root.getData() + maximumSumInSubtreeHelper(root.getLeft())
                + maximumSumInSubtreeHelper(root.getRight());

        if (currentSumOfSubTree > this.maximum)
            this.maximum = currentSumOfSubTree;

        return currentSumOfSubTree;
    }
}
