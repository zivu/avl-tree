import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class AvlTreeTest {

    @Test
    public void shouldAddRootWhenThereIsNone() {
        //given
        Integer data = 10;
        AvlTree tree = new AvlTree();
        //when
        tree.add(data);
        //then
        Assertions.assertNotNull(tree.getRoot());
    }

    @Test
    public void shouldThrowNPEWhenProvidedWithNullDataToAdd() {
        //given
        Integer nullData = null;
        AvlTree tree = new AvlTree();
        //when
        Assertions.assertThrows(NullPointerException.class, () -> tree.add(nullData));
        //then NPE is thrown
    }

    @Test
    public void shouldAddSmallerNodeToTheLeftOfRoot() {
        //given
        AvlTree tree = new AvlTree();
        Integer root = 10;
        Integer left = 7;
        //when
        tree.add(root);
        tree.add(left);
        //then
        Assertions.assertNotNull(tree.getRoot().getLeft());
    }

    @Test
    public void shouldAddGreaterNodeToTheRightOfRoot() {
        //given
        AvlTree tree = new AvlTree();
        Integer root = 10;
        Integer greater = 12;
        //when
        tree.add(root);
        tree.add(greater);
        //then
        Assertions.assertNotNull(tree.getRoot().getRight());
    }

    @Test
    public void shouldReturnNPEForNullInput() {
        //given
        AvlTree avlTree = new AvlTree();
        Node nullNode = null;
        //when
        Assertions.assertThrows(NullPointerException.class, () -> avlTree.checkBalance(nullNode));
        //then NPE is thrown
    }

    @Test
    public void shouldReturnTrueIfTreeIsBalanced() {
        //given
        AvlTree avlTree = new AvlTree();
        Node root = new Node(5);
        root.setLeft(new Node(4));
        root.setRight(new Node(8));
        //when
        boolean balanced = avlTree.checkBalance(root);
        //then
        Assertions.assertTrue(balanced);
    }

    @Test
    public void shouldReturnFalseIfTreeIsImbalanced() {
        //given
        AvlTree avlTree = new AvlTree();
        Node root = new Node(5);
        Node left = new Node(4);
        left.setLeft(new Node(3));
        root.setLeft(left);
        //when
        boolean balanced = avlTree.checkBalance(root);
        //then
        Assertions.assertFalse(balanced);
    }

    @Test
    public void shouldReturnZeroIfTreeHasNoChildren() {
        //given
        AvlTree avlTree = new AvlTree();
        Node root = new Node(5);
        //when
        int maxNodes = avlTree.maxNodes(root);
        //then
        assertEquals(0, maxNodes);
    }

    @Test
    public void shouldReturnLongestRootToLeafPath() {
        //given
        AvlTree avlTree = new AvlTree();
        Node left = new Node(4);
        left.setLeft(new Node(3));
        Node root = new Node(5);
        root.setLeft(left);
        //when
        int maxNodes = avlTree.maxNodes(root);
        //then
        assertEquals(2, maxNodes);
    }

    @Test
    public void shouldThrowNPEWhenProvidedWithNullArgument() {
        //given
        Node nullNode = null;
        AvlTree avlTree = new AvlTree();
        //when
        Assertions.assertThrows(NullPointerException.class, () -> avlTree.rebalance(nullNode));
        //then NPE is thrown
    }

    @Test
    public void shouldDoRightRotation() {
        //given
        AvlTree avlTree = new AvlTree();
        Node grandParentNode = new Node(30);
        Node parent = new Node(20);
        parent.setParent(grandParentNode);
        Node child = new Node(10);
        child.setParent(parent);
        grandParentNode.setLeft(parent);
        parent.setLeft(child);
        //when
        avlTree.rebalance(grandParentNode);
        //then
        assertNull(parent.getParent());
        assertNull(grandParentNode.getLeft());
        assertEquals(grandParentNode.getParent(), parent);
        assertEquals(parent.getRight(), grandParentNode);
    }

    @Test
    public void shouldDoLeftRotation() {
        //given
        AvlTree avlTree = new AvlTree();
        Node grandParent = new Node(10);
        Node parent = new Node(20);
        Node child = new Node(30);
        grandParent.setRight(parent);
        parent.setRight(child);
        child.setParent(parent);
        parent.setParent(grandParent);
        //when
        avlTree.rebalance(grandParent);
        //then
        assertEquals(parent, grandParent.getParent());
        assertNull(grandParent.getRight());
        assertNull(parent.getParent());
        assertEquals(grandParent, parent.getLeft());
    }

    @Test
    public void shouldDoLeftRightRotation() {
        //given
        AvlTree avlTree = new AvlTree();
        Node grandParent = new Node(30);
        Node parent = new Node(20);
        Node child = new Node(25);
        grandParent.setLeft(parent);
        parent.setRight(child);
        child.setParent(parent);
        parent.setParent(grandParent);
        //when
        avlTree.rebalance(grandParent);
        //then
        assertNull(parent.getRight());
        assertEquals(child, parent.getParent());
        assertEquals(parent, child.getLeft());
        assertEquals(grandParent, child.getRight());
        assertNull(child.getParent());
        assertEquals(child, grandParent.getParent());
        assertNull(grandParent.getLeft());
    }

    @Test
    public void shouldDoRightLeftRotation() {
        //given
        AvlTree avlTree = new AvlTree();
        Node grandParent = new Node(20);
        Node parent = new Node(30);
        Node child = new Node(25);
        child.setParent(parent);
        parent.setParent(grandParent);
        grandParent.setRight(parent);
        parent.setLeft(child);
        //when
        avlTree.rebalance(grandParent);
        //then
        assertNull(grandParent.getRight());
        assertEquals(child, grandParent.getParent());
        assertNull(child.getParent());
        assertEquals(grandParent, child.getLeft());
        assertEquals(parent, child.getRight());
        assertEquals(child, parent.getParent());
        assertNull(parent.getLeft());
    }

}
