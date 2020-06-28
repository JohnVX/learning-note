package btree;

public class RunBTree {
    public static void main(String[] args) {
        BTree<String> btree = new BTree<>(5);
        btree.insertEntry(new BTreeNode.Entry<>(10, "shuwei"));
        btree.insertEntry(new BTreeNode.Entry<>(20, "lili"));
        btree.insertEntry(new BTreeNode.Entry<>(30, "john"));
        btree.insertEntry(new BTreeNode.Entry<>(40, "sara"));
        btree.insertEntry(new BTreeNode.Entry<>(50, "reese"));
        btree.insertEntry(new BTreeNode.Entry<>(11, "trump"));
        btree.insertEntry(new BTreeNode.Entry<>(12, "holy"));
        btree.insertEntry(new BTreeNode.Entry<>(13, "jack"));
        btree.deleteEntryByKey(11);
        BTreeNode<String> node = btree.locateNodeByKey(10);
        System.out.println(node.toString());
    }
}