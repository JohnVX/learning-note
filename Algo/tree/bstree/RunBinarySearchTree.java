package bstree;

import static bstree.BinarySearchTree.Node;
public class RunBinarySearchTree {
    public static void main(String[] args) {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        tree.insert(50, "50");
        tree.insert(30, "30");
        tree.insert(80, "80");
        tree.insert(40, "40");
        tree.insert(90, "90");
        tree.insert(25, "25");
        tree.insert(70, "70");
        tree.insert(35, "35");
        tree.insert(45, "45");
        tree.insert(55, "55");
        tree.delete(90);
//        System.out.println(tree.lookup(40));
//        System.out.println(tree.toString());
//        tree.insert(50, "50");
//        tree.insert(40, "40");
//        tree.insert(30, "30");
//        tree.insert(45, "45");
//        tree.delete(40);
        tree.traversal((Node<String> node)-> System.out.print(node.getValue() + ", "));
    }
}
