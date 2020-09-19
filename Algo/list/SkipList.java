package list;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 概率性数据结构
 * 在计算机科学中，跳跃列表是一种数据结构
 * 它使得包含 n 个元素的有序序列的查找、插入、删除操作的平均时间复杂度都是 O(log n)，优于数组的 O(n) 复杂度
 */
public class SkipList<T>{
    private Node[] head;
    private boolean headArrayExtended;
    @SuppressWarnings("unchecked")
    public SkipList() {
        //head 的初始化
        head = new Node[1];
        head[0] = new Node(0, null, null, null, null);
        headArrayExtended = false;
    }
    static class Node<T>{
        int key;
        T value;
        Node<T> next;
        Node<T> prev;
        //下层的同 key 节点
        Node<T> nextLevelNode;
        @Override
        public String toString() {
            return "{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
        Node(int key, T value, Node<T> next, Node<T> prev, Node<T> nextLevelNode){
            this.key = key;
            this.value = value;
            this.next = next;
            this.prev = prev;
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
        return node;
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
    public void add(int key, T value) {
        headArrayExtended = false;
        int index = head.length - 1;
        Node<T> node = head[index];
        Node<T>[] recordNodes = new Node[head.length];
        Node<T> nextLevelNode;

        while (index > 0) {
            for (; node.next != null && node.next.key < key; node = node.next) ;
            if (node.next == null || node.next.key > key) {
                recordNodes[index--] = node;
                //到下层的前邻节点处继续查找
                if (node == head[index+1]) {
                    node = head[index];
                } else {
                    node = node.nextLevelNode;
                }
            } else {
                System.out.println("不能插入相同 key 的节点 key " + key);
                return;
            }
        }
        for (; node.next != null && node.next.key < key; node = node.next) ;
        node.next = new Node<>(key, value, node.next, node, null);
        nextLevelNode = node.next;
        while (!headArrayExtended) {
            //0.5 的命中概率, 概率性地选择是否往上层添加节点
            if (!ThreadLocalRandom.current().nextBoolean()) {
                return;
            }
            index++;
            extendHeadArrayIfNeeded(index);
            if (head[index] == null) {
                //head[index] == null ，意味着 head 数组被扩展了
                head[index] = new Node(0, null, null, null, null);
                Node<T> copiedNode = head[index - 1].next;
                head[index].next = new Node<>(copiedNode.key, copiedNode.value, null, head[index],  copiedNode);
                if (copiedNode.key != key) {
                    head[index].next.next = new Node<>(key, value, null, head[index].next, nextLevelNode);
                }
            }else {
                recordNodes[index].next = new Node<>(key, value, recordNodes[index].next, recordNodes[index], nextLevelNode);
                nextLevelNode = recordNodes[index].next;
            }
        }
    }

    /**
     * 从 head 数组的顶层开始(index == (length-1)), 查找被删除节点, 找到后删除之,
     * 依次向下直到进入 head 数组的底层, 删除对应的节点,
     * 删除节点后, 若 head 数组的最上层( index == (length-1) )已无数据节点, 则重建 head 数组以去掉数组最上层
     * @param key key
     */
    @SuppressWarnings("unchecked")
    public void delete(int key){
        int index = head.length-1;
        Node<T> node = head[index];

        while (index >= 0) {
            for (; node.next != null && node.next.key < key; node = node.next) ;
            if (node.next == null || node.next.key > key) {
                if (node == head[index] && index == 0) {
                    System.out.println("节点不存在，故不能被删除, key " + key);
                    return;
                } else {
                    //到下层的前邻节点处继续查找
                    if(node == head[index]){
                        node = head[index-1];
                    }else {
                        node = node.nextLevelNode;
                    }
                    index--;
                }
            } else {
                while (index > 0){
                    Node<T> tmpNode = node.next;
                    node.next = node.next.next;
                    node.next.prev = node;
                    if(tmpNode.prev == head[index] && tmpNode.next == null){
                        shrinkHeadArray();
                    }
                    node = tmpNode.nextLevelNode.prev;
                    index--;
                }
                node.next = node.next.next;
                return;
            }
            if (node == null) {
                System.out.println("节点不存在，故不能被删除, key " + key);
                return;
            }
        }
    }
    private void extendHeadArrayIfNeeded(int index){
        if ((head.length - 1) < index) {
            Node[] newHead = new Node[head.length+1];
            System.arraycopy(head, 0, newHead, 0, head.length);
            head = newHead;
            headArrayExtended = true;
        }
    }
    private void shrinkHeadArray(){
        Node[] newHead = new Node[head.length-1];
        System.arraycopy(head, 0, newHead, 0, head.length-1);
        head = newHead;
    }
    @Override
    @SuppressWarnings("unchecked")
    public String toString(){
        int index = head.length;
        StringBuilder stringBuilder = new StringBuilder();
        while (--index >= 0){
            Node<T> node = head[index].next;
            while (node != null){
                stringBuilder.append(node);
                node = node.next;
            }
            stringBuilder.append('\n');
        }
        return stringBuilder.toString();
    }
}
