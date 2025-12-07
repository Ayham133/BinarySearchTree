import java.util.NoSuchElementException;

/**
 * AVLTree
 *
 * It's a BinarySearchTree but Alaways Balanced.
 *
 * Author: Ayham Mahmoud Atallah.
 * Date of Creat: Sat/Dec/6/2025. at 4:43PM UTC+2.
 * Last edited at:
 *
 */
public class AVLTree {
    Node root;

    public AVLTree() {
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
        System.out.println();
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
     * Returns true if the tree is balanced, false otherwise.
     *
     * @throws NoSuchElementException
     */
    public boolean isBalanced() {
        if (isEmpty())
            throw new NoSuchElementException("Root is NUll");

        return isBalancedHelper(root) <= 1;
    }

    /**
     * isBalanced Hleper
     * 
     * @param root
     */
    private int isBalancedHelper(Node root) {
        if (root == null)
            return 0;

        int hightDiff = Math.abs(heightHelper(root.getLeft()) - heightHelper(root.getRight()));

        return hightDiff;
    }

    /**
     * rotate the node to the right and returns the new root.
     *
     * @param root
     * @return new root
     */
    private Node rotateRight(Node root) {
        Node newRoot = root.getLeft();
        // to preseve the right subTree.
        Node temp = newRoot.getRight();

        // update the right subTree of the newRoot to be the root.
        newRoot.setRight(root);
        // Reconecte the preseveed subTree in temp to the left subTree of the original
        // root now is just cild for newRoot.
        root.setLeft(temp);

        // Now we need to update the height data in each node for future checking for
        // balancing.
        root.setHight(Math.max(heightHelper(root.getLeft()), heightHelper(root.getRight())));
        newRoot.setHight(Math.max(heightHelper(newRoot.getLeft()), heightHelper(newRoot.getRight())));

        // now return the new root.
        return newRoot;
    }

    /**
     * rotate the node to the left and returns the new root.
     *
     * @param root
     * @return new root
     */
    private Node rotateLeft(Node root) {
        Node newRoot = root.getRight();
        // to preseve the left subTree.
        Node temp = newRoot.getLeft();

        // update the left subTree of the newRoot to be the root.
        newRoot.setLeft(root);
        // Reconecte the preseveed subTree in temp to the right subTree of the original
        // root now is just child for newRoot.
        root.setRight(temp);

        // Now we need to update the height data in each node for future checking for
        // balancing.
        root.setHight(Math.max(heightHelper(root.getLeft()), heightHelper(root.getRight())));
        newRoot.setHight(Math.max(heightHelper(newRoot.getLeft()), heightHelper(newRoot.getRight())));

        // now return the new root.
        return newRoot;
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

        root = insertHelper(root, data);

    }

    /**
     * insertHelper method.
     */
    private Node insertHelper(Node root, int data) {
        if (root == null)
            return new Node(data);

        if (root.getData() < data)
            root.setRight(insertHelper(root.getRight(), data));
        else if (root.getData() > data)
            root.setLeft(insertHelper(root.getLeft(), data));
        else
            return root;

        // updating the height of the node to check wether the root is balanced or not.
        root.setHight(1 + Math.max(heightHelper(root.getLeft()), heightHelper(root.getRight())));

        // getting the nodeHeightBalance to check if the node is balanced or what the
        // type of inbalancing is it.
        int nodeHeightBalanceValue = nodeHeightBalance(root);

        // RR - L
        if (nodeHeightBalanceValue < -1 && root.getRight().getData() < data)
            root = rotateLeft(root);

        // LL -R
        // when the balance is over one and the node we added is on the left of the left
        // node of the root
        if (nodeHeightBalanceValue > 1 && root.getLeft().getData() > data)
            root = rotateRight(root);

        // LR - LR
        // when the balance is over one and the node we added is on the right of the
        // left node of the root
        // here we do two rotations one to the left for the root.getLeft and
        // one to the right for the root.
        if (nodeHeightBalanceValue > 1 && root.getLeft().getData() < data) {
            root.setLeft(rotateLeft(root.getLeft()));
            root = rotateRight(root);
        }

        // RL - RL
        // when the balance is less than -1 and the node we added is on the left of the
        // right node of the root
        // here we do two rotations one to the right for the root.getRight and
        // one to the left for the root.
        if (nodeHeightBalanceValue < -1 && root.getRight().getData() > data) {
            root.setRight(rotateRight(root.getRight()));
            root = rotateLeft(root);
        }
        return root;
    }

    /**
     * Returns the balance number for checking if the node is balance or not.
     */
    private int nodeHeightBalance(Node root) {
        if (root == null)
            return 0;
        return heightHelper(root.getLeft()) - heightHelper(root.getRight());
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

        // case there is just the root.
        if (isLeaf(root)) {
            Node oldValue = root;
            root = null;
            return oldValue;
        }

        Node oldValue = null;

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

        oldValue = new Node(targetNode.getData());

        // Case of a leaf node.
        if (isLeaf(targetNode)) {

            // when the laef node is on the left side.
            if (targetNode == parentNode.getLeft())
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

            if (parentNode == null) {
                root = childNode;
                return oldValue;
            }
            // define the position of the targetNode node is it in the left part or the
            // right part.
            if (targetNode == parentNode.getLeft())
                parentNode.setLeft(childNode);
            else
                parentNode.setRight(childNode);

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

        System.out.print("After deleting: ");
        preOrderPrint();

        int nodeHeightBalanceValue = nodeHeightBalance(parentNode);

        // RR - L
        if (nodeHeightBalanceValue < -1 && nodeHeightBalance(parentNode.getRight()) <= 0)
            root = rotateLeft(parentNode);

        // LL - R
        if (nodeHeightBalanceValue > 1 && nodeHeightBalance(parentNode.getLeft()) >= 0)
            root = rotateRight(parentNode);

        // LR - LR
        if (nodeHeightBalanceValue > 1 && nodeHeightBalance(parentNode.getLeft()) < 0) {
            root = rotateLeft(parentNode);
            root = rotateRight(root);
        }

        System.out.print("After balancing: ");
        preOrderPrint();

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

}
