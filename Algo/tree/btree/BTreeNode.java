package tree.btree;

/**
 * BTreeNode<T>
 * @param <T> B 树节点中存储的数据类型
 */
public class BTreeNode<T> {
    //节点最小(最左)的记录
    Entry<T> firstEntry;
    BTreeNode<T> parent;
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
        public String toString() {
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
