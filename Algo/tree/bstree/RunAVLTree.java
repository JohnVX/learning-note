package tree.bstree;

public class RunAVLTree {
    public static void main(String[] args) {
        AVLTree<String> tree = new AVLTree<>();
        tree.insert(1, "A");
        tree.insert(2, "B");
        tree.insert(4, "D");
        tree.insert(5, "E");
        tree.insert(3, "C");
        tree.delete(2);
        System.out.println(tree.lookup(5));
        tree.traversal((node) -> System.out.print(node.getValue() + ","));
    }
}
