package btree;

/**
 * B Tree
 *
 * 根据 Knuth 的定义，一个 m 阶的 B 树是一个有以下属性的树：
 *
 * 1.每一个节点最多有 m 个子节点
 * 2.每一个非叶子节点（除根节点）最少有 ⌈m/2⌉ 个子节点
 * 3.如果根节点不是叶子节点，那么它至少有两个子节点
 * 4.有 k 个子节点的非叶子节点拥有 k − 1 个键
 * 5.所有的叶子节点都在同一层
 *
 * 每一个内部节点的键将节点的子树分开。例如，如果一个内部节点有3个子节点（子树），那么它就必须有两个键： a1 和 a2 。左边子树的所有值都必须小于 a1 ，中间子树的所有值都必须在 a1 和a2 之间，右边子树的所有值都必须大于 a2 。
 *
 */
public class BTree<T>{
    private BTreeNode<T> root = new BTreeNode<>(null);
    private static boolean isRoot(BTreeNode node) {
        return node.parent == null;
    }
    private static boolean isLeaf(BTreeNode node) {
        BTreeNode.Entry entry = node.firstEntry;
        return entry == null || entry.leftChild == null;
    }

    /**
     * 在 root 这颗树上查找 key 所在(或所应在)的 B 树节点
     */
    public BTreeNode<T> locateNodeByKey(int key) {
        return locate(root, key);
    }
    /**
     * @param root 树的根节点
     * @param key  记录的 key
     * @return key 对应记录所在(或所应在)的 B 树节点
     */
    private BTreeNode<T> locate(BTreeNode<T> root, int key) {
        if (isLeaf(root) || root.firstEntry.key == key)
            return root;
        if (root.firstEntry.key > key)
            return locate(root.firstEntry.leftChild, key);
        BTreeNode.Entry<T> entry = root.firstEntry;
        BTreeNode.Entry<T> preEntry = entry;
        while ((entry = entry.nextEntry) != null) {
            preEntry = entry;
            if (entry.key == key)
                return root;
            if (entry.key > key)
                return locate(entry.leftChild, key);
        }
        return locate(preEntry.rightChild, key);
    }

    /**
     * 往树 root 上插入一个记录
     */
    public void insertEntry(BTreeNode.Entry<T> entry){
        insertFromRoot(root, entry);
    }
    /**
     * @param root  树的根节点
     * @param entry 要插入的记录
     */
    private void insertFromRoot(BTreeNode<T> root, BTreeNode.Entry<T> entry) {
        BTreeNode<T> node = locate(root, entry.key);
        insert0(node, entry);
    }
    private void insert0(BTreeNode<T> node, BTreeNode.Entry<T> entry){
        int entryNum;
        if (node.firstEntry == null)
            entryNum = 0;
        else {
            BTreeNode.Entry entry0 = node.firstEntry;
            //node 节点的记录数目
            entryNum = 1;
            while ((entry0 = entry0.nextEntry) != null)
                entryNum++;
        }
        if (entryNum < (BTreeNode.order - 1))
            insertWithoutSplit(node, entry);
        else insertWithSplit(node, entry);
    }
    private void insertWithoutSplit(BTreeNode<T> node, BTreeNode.Entry<T> entry) {
        if(node.firstEntry == null){
            node.firstEntry = entry;
            return;
        }
        if (entry.key < node.firstEntry.key) {
            entry.nextEntry = node.firstEntry;
            node.firstEntry = entry;
            entry.rightChild = entry.nextEntry.leftChild;
            return;
        }
        BTreeNode.Entry<T> entry0 = node.firstEntry;
        BTreeNode.Entry<T> preEntry = entry0;
        while (entry0.key < entry.key && entry0.nextEntry != null) {
            preEntry = entry0;
            entry0 = entry0.nextEntry;
        }
        if (entry0.key > entry.key) {
            preEntry.nextEntry = entry;
            entry.nextEntry = entry0;
            preEntry.rightChild = entry.leftChild;
            entry0.leftChild = entry.rightChild;
        } else {
            entry0.nextEntry = entry;
            entry0.rightChild = entry.leftChild;
        }
    }
    private void insertWithSplit(BTreeNode<T> node, BTreeNode.Entry<T> entry) {
        insertWithoutSplit(node, entry);
        BTreeNode.Entry<T> entry0 = node.firstEntry;
        //node 节点的记录数目
        int entryNum = 1;
        while ((entry0 = entry0.nextEntry) != null)
            entryNum++;
        BTreeNode<T> leftNode = new BTreeNode<>(null);
        leftNode.firstEntry = node.firstEntry;
        entry0 = node.firstEntry;
        for (int i = 0; i < (entryNum / 2) - 1; i++) {
            entry0 = entry0.nextEntry;
        }
        BTreeNode<T> rightNode = new BTreeNode<>(null);
        rightNode.firstEntry = entry0.nextEntry.nextEntry;
        BTreeNode.Entry<T> medianEntry = entry0.nextEntry;
        medianEntry.nextEntry = null;
        entry0.nextEntry = null;
        medianEntry.leftChild = leftNode;
        medianEntry.rightChild = rightNode;
        if (isRoot(node)) {
            root = new BTreeNode<>(null);
            root.firstEntry = medianEntry;
            leftNode.parent = rightNode.parent = root;
        } else {
            leftNode.parent = rightNode.parent = node.parent;
            insert0(node.parent, medianEntry);
        }
    }

}
