package btree;

/**
 * BTreeNode<T>
 * @param <T> 要存储的数据类型
 */
public class BTreeNode<T> {
    //B 树的阶, 即节点允许的最多子树数目
    static int order;
    //节点最小(最左)的记录
    Entry<T> firstEntry;
    BTreeNode<T> parent;
    static {
        BTreeNode.order = 5;
    }
    public static class Entry<T> {
        int key;
        T value;
        Entry<T> nextEntry;
        BTreeNode<T> leftChild;
        BTreeNode<T> rightChild;

        public Entry(int key, T value) {
            this.key = key;
            this.value = value;
        }
        @Override
        public java.lang.String toString() {
            return "key: " + key + ", value: " + value + ", nextEntry: " + nextEntry;
        }
    }
    BTreeNode(BTreeNode<T> parent) {
        this.parent = parent;
    }
    @Override
    public String toString(){
        return "[firstEntry: " + firstEntry + "], parent: " + parent;
    }
}
