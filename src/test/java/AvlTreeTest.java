import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
        Assertions.assertEquals(0, maxNodes);
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
        Assertions.assertEquals(2, maxNodes);
    }

}