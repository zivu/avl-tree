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
        if (!checkBalance(node)) {
            rebalance(node);
        }
    }

    /**
     * checks what kind of rotation a tree needs and does it to restore balance;
     * @param node to restore balance to
     */
    public void rebalance(@NonNull Node node) {
        int maxNodesOnLeft = maxNodes(node.getLeft());
        if (node.getLeft() != null) {
            maxNodesOnLeft++;
        }

        int maxNodesOnRight = maxNodes(node.getRight());
        if (node.getRight() != null) {
            maxNodesOnRight++;
        }
        if (maxNodesOnLeft - maxNodesOnRight > 1) {
            int maxNodesOnLeftLeft = node.getLeft() != null ? maxNodes(node.getLeft().getLeft()) : 0;
            if (node.getLeft() != null && node.getLeft().getLeft() != null) {
                maxNodesOnLeftLeft++;
            }

            int maxNodesOnLeftRight = node.getLeft() != null ? maxNodes(node.getLeft().getRight()) : 0;
            if (node.getLeft() != null && node.getLeft().getRight() != null) {
                maxNodesOnLeftRight++;
            }

            if (maxNodesOnLeftLeft - maxNodesOnLeftRight >= 1) {
                rightRotate(node);
            } else {
                leftRightRotate(node);
            }
        } else {
            int maxNodesRightLeft = node.getRight() != null ? maxNodes(node.getRight().getLeft()) : 0;
            if (node.getRight() !=null && node.getRight().getLeft() != null) {
                maxNodesRightLeft++;
            }
            int maxNodesRightRight = node.getRight() != null ? maxNodes(node.getRight().getRight()) : 0;
            if (node.getRight() != null && node.getRight().getRight() != null) {
                maxNodesRightRight++;
            }
            if (maxNodesRightLeft - maxNodesRightRight >= 1) {
                rightLeftRotate(node);
            } else {
                leftRotate(node);
            }
        }
    }

    private void rightLeftRotate(Node node) {
        rightRotate(node.getRight());
        leftRotate(node);
    }

    private void leftRightRotate(Node node) {
        leftRotate(node.getLeft());
        rightRotate(node);
    }

    private void rightRotate(Node grandParent) {
        Node grandGrandParent = grandParent.getParent();
        Node parent = grandParent.getLeft();
        parent.setParent(grandGrandParent);
        if (grandGrandParent != null) {
            if (grandParent.equals(grandGrandParent.getLeft())) {
                grandGrandParent.setLeft(parent);
            } else {
                grandGrandParent.setRight(parent);
            }
        }
        grandParent.setParent(parent);
        grandParent.setLeft(null);
        Node tempRight = parent.getRight();
        parent.setRight(grandParent);
        if (tempRight != null) {
            tempRight.setParent(null);
            add(grandParent, tempRight);
        }
    }

    private void leftRotate(Node grandParent) {
        Node grandGrandParent = grandParent.getParent();
        Node parent = grandParent.getRight();
        parent.setParent(grandGrandParent);
        if (grandGrandParent != null) {
            if (grandParent.equals(grandGrandParent.getLeft())) {
                grandGrandParent.setLeft(parent);
            } else {
                grandGrandParent.setRight(parent);
            }
        }
        grandParent.setParent(parent);
        grandParent.setRight(null);
        Node tempRight = parent.getLeft();
        parent.setLeft(grandParent);
        if (tempRight != null) {
            tempRight.setParent(null);
            add(grandParent, tempRight);
        }
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
