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

//        AVLTree<String> tree = new AVLTree<>();
//        tree.insert(10, "A");
//        tree.insert(20, "B");
//        tree.insert(5, "D");
//        tree.insert(3, "E");
//        tree.insert(8, "C");
//        tree.delete(20);
//        System.out.println(tree.lookup(5));
//        tree.traversal((node) -> System.out.print("[" + node.getKey() + "," + node.getValue() + "] "));

//        AVLTree<String> tree = new AVLTree<>();
//        tree.insert(20, "A");
//        tree.insert(10, "B");
//        tree.insert(30, "C");
//        tree.insert(5, "G");
//        tree.insert(25, "D");
//        tree.insert(35, "E");
//        tree.insert(23, "F");
//        tree.insert(28, "H");
//        tree.delete(5);
//        System.out.println(tree.lookup(5));
//        tree.traversal((node) -> System.out.print("[" + node.getKey() + "," + node.getValue() + "] "));

//        AVLTree<String> tree = new AVLTree<>();
//        tree.insert(50, "A");
//        tree.insert(20, "B");
//        tree.insert(60, "D");
//        tree.insert(100, "I");
//        tree.insert(10, "E");
//        tree.insert(30, "C");
//        tree.insert(25, "F");
//        tree.insert(40, "G");
//        tree.delete(100);
//        System.out.println(tree.lookup(5));
//        tree.traversal((node) -> System.out.print("[" + node.getKey() + "," + node.getValue() + "] "));

        AVLTree<String> tree = new AVLTree<>();
        tree.insert(100, "A");
        tree.insert(50, "B");
        tree.insert(200, "C");
        tree.insert(20, "D");
        tree.insert(60, "E");
        tree.insert(150, "F");
        tree.insert(300, "G");
        tree.insert(10, "H");
        tree.insert(30, "I");
        tree.insert(90, "J");
        tree.insert(140, "K");
        tree.insert(180, "L");
        tree.insert(250, "M");
        tree.insert(400, "N");
        tree.insert(25, "O");
        tree.insert(40, "P");
        tree.insert(130, "Q");
        tree.insert(145, "R");
        tree.insert(160, "S");
        tree.insert(190, "T");
        tree.insert(500, "U");
        tree.insert(120, "V");
        tree.delete(90);
        tree.traversal((node) -> System.out.print("[" + node.getKey() + "," + node.getValue() + "] "));
    }
}
