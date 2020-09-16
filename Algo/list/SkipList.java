package list;

/**
 * 概率性数据结构
 * 在计算机科学中，跳跃列表是一种数据结构
 * 它使得包含 n 个元素的有序序列的查找和插入操作的平均时间复杂度都是 O(log n)，优于数组的 O(n) 复杂度
 */
public class SkipList<T>{
    Node[] head, tail;
    static class Node<T>{
        int key;
        T value;
        Node prev, next;
        Node(int key, T value, Node prev, Node next){
            this.key = key;
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }
    public T get(int key){
        return null;
    }
    public void add(int key, T value){

    }
    public void delete(int key){

    }
}
