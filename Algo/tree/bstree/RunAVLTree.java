package tree.bstree;

public class RunAVLTree {
    public static void main(String[] args) {
//        AVLTree<String> tree = new AVLTree<>();
//        tree.insert(1, "A");
//        tree.insert(2, "B");
//        tree.insert(4, "D");
//        tree.insert(5, "E");
//        tree.insert(3, "C");
//        tree.delete(2);
//        System.out.println(tree.lookup(5));
//        tree.traversal((node) -> System.out.print("[" + node.getKey() + "," + node.getValue() + "] "));

        AVLTree<String> tree = new AVLTree<>();
        tree.insert(10, "A");
        tree.insert(20, "B");
        tree.insert(5, "D");
        tree.insert(3, "E");
        tree.insert(8, "C");
        tree.delete(20);
        System.out.println(tree.lookup(5));
        tree.traversal((node) -> System.out.print("[" + node.getKey() + "," + node.getValue() + "] "));
    }
}
