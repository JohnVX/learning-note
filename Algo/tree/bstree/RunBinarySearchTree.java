package bstree;

public class RunBinarySearchTree {
    public static void main(String[] args) {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        tree.insert(50, "a");
        tree.insert(30, "b");
        tree.insert(80, "c");
        tree.insert(40, "d");
        tree.insert(90, "e");
        tree.insert(25, "f");
        tree.insert(70, "g");
        tree.insert(35, "h");
        tree.insert(45, "i");
        tree.insert(55, "j");
        tree.delete(50);
        System.out.println(tree.lookup(40));
        System.out.println(tree.toString());
    }
}
