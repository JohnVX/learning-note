package tree.bstree.avltree;

public class RunAVLTree {
    public static void main(String[] args) {
        AVLTree<String> tree = new AVLTree<>();
        tree.insert(50, "50");
        tree.insert(10, "10");
        tree.insert(5, "5");
        //tree.lookup(50);
        //tree.traversal((node) -> System.out.print(node.getValue() + ","));
    }
}
