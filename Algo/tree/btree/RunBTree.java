package tree.btree;

public class RunBTree {
    public static void main(String[] args) {
        tree.btree.BTree<String> btree = new BTree<>(5);
        btree.insertEntry(new BTreeNode.Entry<>(10, "shuwei"));
        btree.insertEntry(new BTreeNode.Entry<>(20, "lili"));
        btree.insertEntry(new BTreeNode.Entry<>(30, "john"));
        btree.insertEntry(new BTreeNode.Entry<>(40, "sara"));
        btree.insertEntry(new BTreeNode.Entry<>(50, "reese"));
        btree.insertEntry(new BTreeNode.Entry<>(60, "trump"));
        btree.insertEntry(new BTreeNode.Entry<>(70, "70"));
        btree.insertEntry(new BTreeNode.Entry<>(80, "80"));
        btree.insertEntry(new BTreeNode.Entry<>(90, "90"));
        btree.insertEntry(new BTreeNode.Entry<>(100, "100"));
        btree.insertEntry(new BTreeNode.Entry<>(110, "110"));
        btree.insertEntry(new BTreeNode.Entry<>(120, "120"));
        btree.insertEntry(new BTreeNode.Entry<>(130, "130"));
        btree.insertEntry(new BTreeNode.Entry<>(140, "140"));
        btree.insertEntry(new BTreeNode.Entry<>(150, "150"));
        btree.insertEntry(new BTreeNode.Entry<>(160, "160"));
        btree.insertEntry(new BTreeNode.Entry<>(170, "170"));
        btree.deleteEntryByKey(90);
        BTreeNode<String> node = btree.locateNodeByKey(40);
        System.out.println(node.toString());
    }
}