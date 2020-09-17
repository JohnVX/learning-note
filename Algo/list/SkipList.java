package list;

import java.util.Random;

/**
 * 概率性数据结构
 * 在计算机科学中，跳跃列表是一种数据结构
 * 它使得包含 n 个元素的有序序列的查找和插入操作的平均时间复杂度都是 O(log n)，优于数组的 O(n) 复杂度
 */
public class SkipList<T>{
    private Object[] head;
    private Random random;
    public SkipList() {
        //head 的初始化
        head = new Object[1];
        head[0] = null;

        random = new Random(System.currentTimeMillis());
    }
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
    @SuppressWarnings("unchecked")
    public T get(int key){
        Node<T> node = (Node<T>)head[head.length-1];
        Node<T> prev = null;

        while (node != null && node.key != key){
            for(; node != null && node.key < key; prev = node, node = node.next);
            if(node == null || node.key > key){
                if(prev == null){
                    return null;
                }else {
                    //到下层的前邻节点处继续查找
                    node = prev.nextLevelNode;
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
     * 从 head 数组的底层开始( index == 0 ), 查找被添加节点应在的位置, 本层添加好节点后, 概率性地选择是否往上层添加节点,
     * 若是, 则往上层添加节点, 并重复概率选择操作, 若否，则操作结束
     * 进入 head 数组上层前, 决定是否扩展 head 数组, 若是, 则扩展之
     * @param key key
     * @param value value
     */
    @SuppressWarnings("unchecked")
    public void add(int key, T value){
        int index = 0;
        Node<T> nextLevelNode = null;
        while (true) {
            if(head.length < (index+1)){
                extendHeadArray();
            }
            Node<T> node = (Node<T>)head[index];
            if (node == null) {
                if(index > 0){
                    Node<T> copiedNode = (Node<T>)head[index-1];
                    if(copiedNode.key != key){
                        //构建上层的第一个数据节点
                        head[index] = new Node<>(copiedNode.key, copiedNode.value, null, copiedNode);
                        node = (Node<T>)head[index];
                    }else {
                        //添加第一个节点
                        head[index] = new Node<>(key, value, null, copiedNode);
                        return;
                    }
                }else {
                    //添加第一个节点
                    head[index] = new Node<>(key, value, null, null);
                    return;
                }
            }
            Node<T> prev = (Node<T>)head[index];
            for (; node != null && node.key < key; prev = node, node = node.next) ;
            //node == null or node.key >= key
            prev.next = new Node<>(key, value, node, nextLevelNode);
            //0.5 的命中概率, 概率性地选择是否往上层添加节点
            if (random.nextInt(10000) < 5000) {
                index++;
                nextLevelNode = prev.next;
            } else {
                return;
            }
        }
    }

    /**
     * 从 head 数组的底层开始, 查找要被删除的节点, 找到后删除之,
     * 依次向上进入 head 数组的上层, 删除对应的节点,
     * 删除节点后, 若 head 数组的最上层( index == (length-1) )已无数据节点, 则重建 head 数组以去掉数组最上层
     * @param key key
     */
    public void delete(int key){

    }
    private void extendHeadArray(){
        int newLength = head.length * 2;
        Object[] newHead = new Object[newLength];
        System.arraycopy(head, 0, newHead, 0, head.length);
        head = newHead;
    }
}
