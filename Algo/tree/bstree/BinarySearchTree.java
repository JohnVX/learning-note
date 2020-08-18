package tree.bstree;

import java.util.function.Consumer;

/**
 * 一般的二叉查找树
 */
public class BinarySearchTree<T> {
    Node<T> root;
    private static boolean predecessorSuccessorButton = true;
    public static class Node<T>{
        int key;
        T value;
        Node<T> leftChild;
        Node<T> rightChild;
        Node(int key, T value){
            this.key = key;
            this.value = value;
            this.leftChild = this.rightChild = null;
        }
        public T getValue() {
            return value;
        }
        private void replace(int key, T value){
            this.key = key;
            this.value = value;
        }
        @Override
        public String toString(){
            if(this.leftChild == null && this.rightChild == null){
                return "[key=" + key + ", value=" + value + "]";
            }else {
                return "[key=" + key + ", value=" + value + ", leftChild=" + leftChild + ", rightChild=" + rightChild + "]";
            }
        }
    }
    public Node<T> lookup(int key){
        Node<T> node = root;
        while (node != null){
            if(node.key == key){
                return node;
            }else if(node.key < key){
                node = node.rightChild;
            }else {
                node = node.leftChild;
            }
        }
        return null;
    }
    public void insert(int key, T value){
        if(root == null){
            root = new Node<>(key, value);
            return;
        }
        Node<T> node = root;
        while(true){
            if(node.key == key){
                System.out.println("不可插入重复数据, key=" + key + ", value=" + node.value + ", 本次计划插入的 value=" + value);
                return;
            }
            if(node.key < key){
                if(node.rightChild == null){
                    node.rightChild = new Node<>(key, value);
                    return;
                }else {
                    node = node.rightChild;
                }
            }else {
                if(node.leftChild == null){
                    node.leftChild = new Node<>(key, value);
                    return;
                }else {
                    node = node.leftChild;
                }
            }
        }
    }
    public void delete(int key){
        Node<T> node = root;
        Node<T> parentNode = null;
        while (true){
            if(node.key == key){
                if(node.leftChild == null && node.rightChild == null){
                    if(node == root){
                        root = null;
                        return;
                    }
                    if(parentNode.leftChild == node){
                        parentNode.leftChild = null;
                    }else {
                        parentNode.rightChild = null;
                    }
                }else if(node.leftChild == null){
                    if(node == root){
                        root = root.rightChild;
                        return;
                    }
                    if(parentNode.leftChild == node){
                        parentNode.leftChild = node.rightChild;
                    }else {
                        parentNode.rightChild = node.rightChild;
                    }
                }else if(node.rightChild == null){
                    if(node == root){
                        root = root.leftChild;
                        return;
                    }
                    if(parentNode.leftChild == node){
                        parentNode.leftChild = node.leftChild;
                    }else {
                        parentNode.rightChild = node.leftChild;
                    }
                }else {
                    Node<T> replaceNode;
                    if(predecessorSuccessorButton){
                        replaceNode = findPredecessorNode(node);
                        predecessorSuccessorButton = false;
                    }else {
                        replaceNode = findSuccessorNode(node);
                        predecessorSuccessorButton = true;
                    }
                    assert replaceNode != null;
                    delete(replaceNode.key);
                    node.replace(replaceNode.key, replaceNode.value);
                }
                return;
            }
            parentNode = node;
            if(node.key < key){
                node = node.rightChild;
            }else {
                node = node.leftChild;
            }
            if(node == null){
                System.out.println("要删除的数据不存在, key=" + key);
                return;
            }
        }
    }
    private Node<T> findPredecessorNode(Node<T> startNode){
        if(startNode.leftChild == null){
            System.out.println("此节点无前邻节点");
            return null;
        }
        Node<T> node = startNode.leftChild;
        Node<T> rightNode = node.rightChild;
        while (rightNode != null){
            node = rightNode;
            rightNode = rightNode.rightChild;
        }
        return node;
    }
    private Node<T> findSuccessorNode(Node<T> startNode){
        if(startNode.rightChild == null){
            System.out.println("此节点无后继节点");
            return null;
        }
        Node<T> node = startNode.rightChild;
        Node<T> leftNode = node.leftChild;
        while (leftNode != null){
            node = leftNode;
            leftNode = leftNode.leftChild;
        }
        return node;
    }
    @Override
    public String toString(){
        if(root != null){
            return root.toString();
        }else {
            return "[empty tree]";
        }
    }
    public void traversal(Consumer<Node<T>> consumer){
        if(root == null){
            System.out.println("empty tree");
            return;
        }
        traversal0(root, consumer);
    }
    private void traversal0(Node<T> node, Consumer<Node<T>> consumer){
        if(node == null){
            return;
        }
        traversal0(node.leftChild, consumer);
        consumer.accept(node);
        traversal0(node.rightChild, consumer);
    }
}
