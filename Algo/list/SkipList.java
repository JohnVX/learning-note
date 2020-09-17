package list;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 概率性数据结构
 * 在计算机科学中，跳跃列表是一种数据结构
 * 它使得包含 n 个元素的有序序列的查找和插入操作的平均时间复杂度都是 O(log n)，优于数组的 O(n) 复杂度
 */
public class SkipList<T>{
    private Node[] head;
    @SuppressWarnings("unchecked")
    public SkipList() {
        //head 的初始化
        head = new Node[1];
        head[0] = new Node(0, null, null, null);
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
    public T get(int key){
        Node<T> node = getNode(key);
        if(node != null){
            return node.value;
        }else {
            return null;
        }
    }
    /**
     * 从 head 数组的顶层开始查找, 本层查找不到, 则到下层的前邻节点处继续查找
     * @param key key
     */
    @SuppressWarnings("unchecked")
    private Node<T> getNode(int key){
        Node<T> prev = null;
        Node<T> node = head[head.length-1].next;

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
                return node;
            }
        }
        if(node == null){
            return null;
        }else {
            return node;
        }
    }
    /**
     * 从 head 数组的顶层开始(index == (length-1)), 查找被添加节点应在的位置,最终在底层定位到位置,
     * 本层添加好节点后, 概率性地选择是否往上层添加节点,
     * 若是, 则往上层添加节点, 并重复概率选择操作, 若否，则操作结束
     * 进入 head 数组上层前, 决定是否扩展 head 数组, 若是, 则扩展之
     * @param key key
     * @param value value
     */
    @SuppressWarnings("unchecked")
    public void add(int key, T value){
        int index = head.length-1;
        Node<T> prev = head[index];
        Node<T> node = head[index].next;
        Node<T>[] recordNodes = new Node[head.length];

        while (index > 0){
            for(; node != null && node.key < key; prev = node, node = node.next);
            if(node == null || node.key > key){
                recordNodes[index--] = prev;
                //到下层的前邻节点处继续查找
                if(prev == head[index+1]){
                    node = head[index];
                }else {
                    node = prev.nextLevelNode;
                }
            }else {
                System.out.println("不能插入相同 key 的节点 key " + key);
                return;
            }
        }
        for(; node != null && node.key < key; prev = node, node = node.next);
        prev.next = new Node<>(key, value, node, null);
        //0.5 的命中概率, 概率性地选择是否往上层添加节点
        if (ThreadLocalRandom.current().nextBoolean()){
            index++;
            nextLevelNode = prev.next;
        } else {
            return;
        }








        Node<T> nextLevelNode = null;
        while (index > 0) {
            if((head.length-1) < index){
                extendHeadArray();
            }
            if (head[index] == null) {
                head[index] = new Node(0, null, null, null);
                if(index > 0){
                    Node<T> copiedNode = head[index-1].next;
                    if(copiedNode.key != key){
                        //构建上层的第一个数据节点
                        head[index].next = new Node<>(copiedNode.key, copiedNode.value, null, copiedNode);
                    }else {
                        //添加第一个节点
                        head[index].next = new Node<>(key, value, null, copiedNode);
                        return;
                    }
                }else {
                    //添加第一个节点
                    head[index].next = new Node<>(key, value, null, null);
                    break;
                }
            }
            Node<T> prev = head[index];
            Node<T> node = prev.next;
            //从 head 数组的顶层开始(index == (length-1)), 查找被添加节点应在的位置
            for (; node != null && node.key < key; prev = node, node = node.next) ;
            //node == null or node.key >= key


            prev.next = new Node<>(key, value, node, nextLevelNode);
            //0.5 的命中概率, 概率性地选择是否往上层添加节点
            if (ThreadLocalRandom.current().nextBoolean()){
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
    //todo 应从 head 数组的顶层开始(index == (length-1)), 查找被添加节点应在的位置
    public void delete(int key){
        Node<T> node = getNode(key);
        if(node == null){
            System.out.println("节点不存在，故不能被删除, key " + key);
        }else {

        }
    }
    private void extendHeadArray(){
        Node[] newHead = new Node[head.length+1];
        System.arraycopy(head, 0, newHead, 0, head.length);
        head = newHead;
    }
}
