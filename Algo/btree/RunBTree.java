package btree;

public class RunBTree {
    public static void main(String[] args) {
        BTree<String> btree = new BTree<>();
        btree.insertEntry(new BTreeNode.Entry<>(1, "shuwei"));
        btree.insertEntry(new BTreeNode.Entry<>(2, "lili"));
        btree.insertEntry(new BTreeNode.Entry<>(3, "john"));
        btree.insertEntry(new BTreeNode.Entry<>(4, "sara"));
        btree.insertEntry(new BTreeNode.Entry<>(5, "reese"));
        btree.insertEntry(new BTreeNode.Entry<>(6, "trump"));
        btree.insertEntry(new BTreeNode.Entry<>(7, "holy"));
        btree.insertEntry(new BTreeNode.Entry<>(8, "jack"));
        btree.insertEntry(new BTreeNode.Entry<>(9, "pony"));
        BTreeNode<String> node = btree.locateNodeByKey(4);
        System.out.println(node.toString());
    }
}