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
    //B 树的阶, 即节点允许的最多子树数目
    private int order;
    private BTreeNode<T> root = new BTreeNode<>(null);
    public BTree(int order){
        this.order = order;
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
        if (root.firstEntry == null || root.firstEntry.leftChild == null)
            return root;
        BTreeNode.Entry<T> entry = root.firstEntry;
        BTreeNode.Entry<T> preEntry = entry;
        while (entry != null) {
            if (entry.key > key)
                return locate(entry.leftChild, key);
            if (entry.key == key)
                return root;
            preEntry = entry;
            entry = entry.nextEntry;
        }
        return locate(preEntry.rightChild, key);
    }
    public void insertEntry(BTreeNode.Entry<T> entry){
        BTreeNode<T> node = locate(root, entry.key);
        insert(node, entry);
    }
    private void insert(BTreeNode<T> node, BTreeNode.Entry<T> entry){
        int entryNum = getEntryNum(node);
        if (entryNum < (order - 1))
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
            entry.nextEntry.leftChild = entry.rightChild;
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
        int entryNum = getEntryNum(node);
        BTreeNode<T> leftNode = new BTreeNode<>(null);
        leftNode.firstEntry = node.firstEntry;
        BTreeNode.Entry<T> entry0 = node.firstEntry;
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
        if (node == root) {
            root = new BTreeNode<>(null);
            root.firstEntry = medianEntry;
            leftNode.parent = rightNode.parent = root;
        } else {
            leftNode.parent = rightNode.parent = node.parent;
            insert(node.parent, medianEntry);
        }
    }
    /**
     * node 节点的记录数目
     */
    private int getEntryNum(BTreeNode<T> node){
        int entryNum;
        if (node.firstEntry == null)
            entryNum = 0;
        else {
            BTreeNode.Entry entry0 = node.firstEntry;
            entryNum = 1;
            while ((entry0 = entry0.nextEntry) != null)
                entryNum++;
        }
        return entryNum;
    }

    public void deleteEntryByKey(int key){
        BTreeNode<T> node = locateNodeByKey(key);
        BTreeNode.Entry<T> entry = locateEntryInNodeByKey(node, key);
        if(entry == null){
            System.out.println("deleteEntryByKey failed: entry contain key:" + key + " doesn't exist.");
            return;
        }
        if(node.firstEntry.leftChild == null){
            deleteEntryFromLeaf(node, entry);
        }else {
            deleteEntryFromInternal(entry);
        }
    }
    private BTreeNode.Entry<T> locateEntryInNodeByKey(BTreeNode<T> node, int key){
        if(node == null || node.firstEntry == null)
            return null;
        BTreeNode.Entry<T> entry = node.firstEntry;
        while (entry != null && entry.key != key)
            entry = entry.nextEntry;
        return entry;
    }
    private void deleteEntryFromLeaf(BTreeNode<T> node, BTreeNode.Entry<T> entry){
        if(node.firstEntry == entry){
            node.firstEntry = entry.nextEntry;
        }else {
            BTreeNode.Entry<T> entry0 = node.firstEntry;
            BTreeNode.Entry<T> preEntry = entry0;
            while (entry0 != null && entry0 != entry) {
                preEntry = entry0;
                entry0 = entry0.nextEntry;
            }
            if(entry0 == null){
                System.out.println("deleteEntryFromLeaf failed: 调用此方法前必须确保 entry 在 node 内。entry:" + entry + " node:" + node);
            }else
                preEntry.nextEntry = entry0.nextEntry;
        }
        if(getEntryNum(node) < (order-1)/2)
            rebalancingAfterDeleteEntry(node);
    }
    private void deleteEntryFromInternal(BTreeNode.Entry<T> entry){
        //找到 entry 左子树中 key 最大值的 entry
        BTreeNode.Entry<T> entry0 = entry.leftChild.firstEntry;
        BTreeNode.Entry<T> targetEntry = entry0;
        BTreeNode<T> targetNode = entry.leftChild;
        while (true) {
            while (entry0 != null) {
                targetEntry = entry0;
                entry0 = entry0.nextEntry;
            }
            if(targetEntry.leftChild == null)
                break;
            else {
                entry0 = targetEntry.rightChild.firstEntry;
                targetNode = targetEntry.rightChild;
            }
        }
        entry.key = targetEntry.key;
        entry.value = targetEntry.value;
        deleteEntryFromLeaf(targetNode, targetEntry);
    }

    /**
     * @param node 从 node 节点开始做平衡操作(可能会往树上层递归)
     */
    private void rebalancingAfterDeleteEntry(BTreeNode<T> node){
        BTreeNode.Entry<T> entry = node.parent.firstEntry;
        while (entry != null && entry.leftChild != node)
            entry = entry.nextEntry;
        if(entry != null && getEntryNum(entry.rightChild) > (order-1)/2){
            //rotate left
            BTreeNode.Entry<T> entry0 = node.firstEntry;
            while (entry0.nextEntry != null)
                entry0 = entry0.nextEntry;
            entry0.nextEntry = new BTreeNode.Entry<>(entry.key, entry.value);
            if(entry0.rightChild != null){
                entry0.nextEntry.leftChild = entry0.rightChild;
                entry0.nextEntry.rightChild = entry.rightChild.firstEntry.leftChild;
            }
            entry.key = entry.rightChild.firstEntry.key;
            entry.value = entry.rightChild.firstEntry.value;
            entry.rightChild.firstEntry = entry.rightChild.firstEntry.nextEntry;
            return;
        }
        entry = node.parent.firstEntry;
        while (entry != null && entry.rightChild != node)
            entry = entry.nextEntry;
        if(entry != null && getEntryNum(entry.leftChild) > (order-1)/2){
            //rotate right
            BTreeNode.Entry<T> entry1 = entry.leftChild.firstEntry;
            while (entry1.nextEntry.nextEntry != null)
                entry1 = entry1.nextEntry;
            BTreeNode.Entry<T> entry0 = new BTreeNode.Entry<>(entry.key, entry.value);
            entry0.nextEntry = node.firstEntry;
            entry.key = entry1.nextEntry.key;
            entry.value = entry1.nextEntry.value;
            if(node.firstEntry.leftChild != null){
                entry0.rightChild = node.firstEntry.leftChild;
                entry0.leftChild = entry1.nextEntry.rightChild;
            }
            node.firstEntry = entry0;
            entry1.nextEntry = null;
            return;
        }
        //merge
        entry = node.parent.firstEntry;
        BTreeNode.Entry<T> preEntry = entry;
        while (entry.leftChild != node && entry.rightChild != node) {
            preEntry = entry;
            entry = entry.nextEntry;
        }
        BTreeNode.Entry<T> entry0 = entry.leftChild.firstEntry;
        while (entry0.nextEntry != null)
            entry0 = entry0.nextEntry;
        entry0.nextEntry = new BTreeNode.Entry<>(entry.key, entry.value);
        entry0.nextEntry.nextEntry = entry.rightChild.firstEntry;
        entry.rightChild = null;
        if(entry0.rightChild != null){
            entry0.nextEntry.leftChild = entry0.rightChild;
            entry0.nextEntry.rightChild = entry0.nextEntry.nextEntry.leftChild;
        }
        if(entry.nextEntry != null) {
            entry.nextEntry.leftChild = entry.leftChild;
        }
        if(entry == node.parent.firstEntry){
            node.parent.firstEntry = entry.nextEntry;
        }else {
            preEntry.nextEntry = entry.nextEntry;
        }
        if(node.parent == root){
            if(root.firstEntry == null) {
                root = entry.leftChild;
            }
            return;
        }
        if(getEntryNum(node.parent) < (order-1)/2) {
            rebalancingAfterDeleteEntry(node.parent);
        }
    }
}
