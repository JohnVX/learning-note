package list;

/**
 * 概率性数据结构
 * 在计算机科学中，跳跃列表是一种数据结构
 * 它使得包含 n 个元素的有序序列的查找和插入操作的平均时间复杂度都是 O(log n)，优于数组的 O(n) 复杂度
 */
public class SkipList<T>{
    private Node<T>[] head, tail;
    static class Node<T>{
        int key;
        T value;
        //本层的后邻节点
        Node<T> next;
        //下层的同 key 节点
        Node<T> nextLevelNode;
        Node(int key, T value, Node<T> next, Node<T> nextLevelNode){
            this.key = key;
            this.value = value;
            this.next = next;
            this.nextLevelNode = nextLevelNode;
        }
    }
    /**
     * 从 head 数组的顶层开始查找, 本层查找不到, 则到下层的前邻节点处继续查找
     * @param key key
     * @return T
     */
    public T get(int key){
        Node<T> node = head[head.length-1].next;
        Node<T> prev = null;

        while (node != null && node.key != key){
            for(; node != null && node.key < key; prev = node, node = node.next);
            if(node == null || node.key > key){
                if(prev == null){
                    return null;
                }else {
                    //到下层的前邻节点处继续查找
                    node = prev.next.nextLevelNode;
                }
            }else {
                return node.value;
            }
        }
        if(node == null){
            return null;
        }else {
            return node.value;
        }
    }

    /**
     * 从 head 数组的底层开始, 查找被添加节点应在的位置, 本层添加好节点后, 概率性地选择是否往上层添加节点,
     * 若是, 则往上层添加节点, 并重复概率选择操作, 直到到达 head 数组的顶层,
     * 到达到达 head 数组的顶层后, 决定是否扩展 head 数组, 若是, 则扩展之
     * @param key key
     * @param value value
     */
    public void add(int key, T value){

    }
    public void delete(int key){

    }
}
