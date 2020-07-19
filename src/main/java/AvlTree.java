import lombok.Getter;
import lombok.NonNull;

@Getter
public class AvlTree {

    private Node root;
    private int currentSize = 0;

    /**
     * Adds input data to the tree.
     * If there is no root, new node will be placed as a root.
     * @param data to be added
     */
    public void add(@NonNull Integer data) {
        Node newNode = new Node(data);
        if (root == null) {
            root = newNode;
            return;
        }
        add(root, newNode);
    }

    private void add(Node node, Node nodeToBeAdded) {
        if (node.getData() - nodeToBeAdded.getData() >= 0) {
            if (node.getLeft() == null) {
                node.setParent(node);
                node.setLeft(nodeToBeAdded);
                currentSize++;
            } else {
                add(node.getLeft(), nodeToBeAdded);
            }
        } else {
            if (node.getRight() == null) {
                node.setParent(node);
                node.setRight(nodeToBeAdded);
                currentSize++;
            } else {
                add(node.getRight(), nodeToBeAdded);
            }
        }
        checkBalance(node);
    }

    /**
     * @param node to be checked for AVL balance,
     *             i.e. that left node minus right yields  -2 < result <= 1
     * @return true if the input node has balance,
     * false otherwise
     */
    public boolean checkBalance(@NonNull Node node) {
        int leftNodes = maxNodes(node.getLeft());
        if (node.getLeft() != null) {
            leftNodes++;
        }
        int rightNodes = maxNodes(node.getRight());
        if (node.getRight() != null) {
            rightNodes++;
        }
        int differenceInNodes = leftNodes - rightNodes;
        return -2 < differenceInNodes && differenceInNodes <= 1;
    }

    /**
     *
     * @param parent to be checked for whether left or right child has the longest root-to-leaf path
     * @return 0 if there are no children, or longest number of nodes from root-to-leaf
     */
    public int maxNodes(Node parent) {
        if (parent == null) {
            return 0;
        }
        int maxNodesOnLeft = maxNodes(parent.getLeft());
        if (parent.getLeft() != null) {
            maxNodesOnLeft++;
        }
        int maxNodesOnRight = maxNodes(parent.getRight());
        if (parent.getRight() != null) {
            maxNodesOnRight++;
        }
        return Math.max(maxNodesOnLeft, maxNodesOnRight);
    }

}
