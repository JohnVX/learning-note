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
public class BTree {
    public static void main(String[] args) {

    }
    static class BTreeNode<T>{
        private static int order;
        private Entry<T> firstEntry;
        private BTreeNode parent;

        static {
            BTreeNode.order = 5;
        }
        private static void setOrder(int order){
            BTreeNode.order = order;
        }
        private static int getOrder(){
            return BTreeNode.order;
        }
        private static class Entry<T>{
            private int key;
            private T value;
            private Entry<T> nextEntry;
            private BTreeNode leftChild;
            private BTreeNode rightChild;

            private Entry(int key, T value){
                this.key = key;
                this.value = value;
            }
        }
        private static boolean isRoot(BTreeNode node){
            return node.parent == null;
        }
        private static boolean isLeaf(BTreeNode node){
            Entry entry = node.firstEntry;
            if(entry.leftChild != null)
                return false;
            Entry preEntry = entry;
            while((entry = entry.nextEntry) != null){
                if(entry.leftChild != null)
                    return false;
                preEntry = entry;
            }
            return preEntry.rightChild == null;
        }
        @SuppressWarnings("unchecked")
        private BTreeNode(BTreeNode parent){
            this.parent = parent;
        }
        @SuppressWarnings("unchecked")
        private BTreeNode<T> locate(BTreeNode root, int key){
            if(isLeaf(root) || root.firstEntry.key == key)
                return root;
            Entry entry = root.firstEntry;
            Entry preEntry = entry;
            while((entry = entry.nextEntry) != null){
                preEntry = entry;
                if(entry.key == key)
                    return root;
                if(entry.key > key)
                    return locate(entry.leftChild, key);
            }
            return locate(preEntry.rightChild, key);
        }
        private void insert(BTreeNode<T> root, int key, T value){
            BTreeNode<T> node = locate(root, key);
            Entry entry = node.firstEntry;
            int order = 2;
            while((entry = entry.nextEntry) != null)
                order++;
            if(order < BTreeNode.order)
                insert0(node, key, value);
            //else //order == BTreeNode.order
        }
        private void insert0(BTreeNode<T> node, int key , T value){
            Entry<T> newEntry = new Entry<>(key, value);
            if(key < node.firstEntry.key){
                newEntry.nextEntry = node.firstEntry;
                newEntry.rightChild = node.firstEntry.leftChild;
                node.firstEntry = newEntry;
                return;
            }
            Entry<T> entry = node.firstEntry;
            while(entry.key < key)
                entry = entry.nextEntry;
            newEntry.nextEntry = entry;
            //
        }
    }
}
