/**
 * Node
 */
public class Node {

    int data;
    Node left;
    Node right;
    int hight;

    public Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }

    public Node() {
        left = null;
        right = null;
    }

    public void setHight(int hight) {
        this.hight = hight;
    }

    public int getHight() {
        return hight;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

}
