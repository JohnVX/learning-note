package tree.bstree;

/**
 * 在计算机科学中， AVL 树是最早被发明的自平衡二叉查找树。在 AVL 树中，任一节点对应的两棵子树的最大高度差为1，因此它也被称为高度平衡树
 */
public class AVLTree<T> extends BinarySearchTree<T> {
    public static class AVLNode<T> extends Node<T>{
        AVLNode<T> parent;
        //平衡因子: 左子树高度 - 右子树高度
        int balanceFactor;
        private AVLNode(int key, T value, AVLNode<T> parent){
            super(key, value);
            this.parent = parent;
            balanceFactor = 0;
        }
    }
    @Override
    public void insert(int key, T value){
        if(root == null){
            root = new AVLNode<>(key, value, null);
            return;
        }
        AVLNode<T> node = (AVLNode<T>)root;
        while(true){
            if(node.key == key){
                System.out.println("不可插入重复数据, key=" + key + ", value=" + node.value + ", 本次计划插入的 value=" + value);
                return;
            }
            if(node.key < key){
                if(node.rightChild == null){
                    node.rightChild = new AVLNode<>(key, value, node);
                    node.balanceFactor--;
                    break;
                }else {
                    node = (AVLNode<T>) node.rightChild;
                }
            }else {
                if(node.leftChild == null){
                    node.leftChild = new AVLNode<>(key, value, node);
                    node.balanceFactor++;
                    break;
                }else {
                    node = (AVLNode<T>)node.leftChild;
                }
            }
        }
        AVLNode<T> rotateRoot = null;
        while(node != this.root){
            if (node.parent.leftChild == node) {
                node.parent.balanceFactor++;
                if(node.parent.balanceFactor > 1 && rotateRoot == null){
                    rotateRoot = node.parent;
                }
            } else{
                node.parent.balanceFactor--;
                if(node.parent.balanceFactor < -1 && rotateRoot == null){
                    rotateRoot = node.parent;
                }
            }
            node = node.parent;
        }
        if(rotateRoot != null){
            rotate(rotateRoot);
        }
    }
    private void rotate(AVLNode<T> node){
        assert node != null && (node.balanceFactor == -2 || node.balanceFactor == 2);

        if(node.balanceFactor == 2 && ((AVLNode)node.leftChild).balanceFactor == 1){
            left2(node);
        }
        if(node.balanceFactor == -2 && ((AVLNode)node.rightChild).balanceFactor == -1){
            right2(node);
        }
        if(node.balanceFactor == 2 && ((AVLNode)node.leftChild).balanceFactor == -1){
            leftRight(node);
        }
        if(node.balanceFactor == -2 && ((AVLNode)node.rightChild).balanceFactor == 1){
            rightLeft(node);
        }
    }
    private void left2(AVLNode<T> node){
        AVLNode<T> parent = node.parent;
        AVLNode<T> pivot = (AVLNode<T>)node.leftChild;

        if(node == root){
           root = pivot;
        }else {
            if(parent.leftChild == node){
                parent.leftChild = pivot;
            }else {
                parent.rightChild = pivot;
            }
        }
        pivot.parent = parent;
        node.parent = pivot;
        if(pivot.rightChild != null) {
            ((AVLNode)pivot.rightChild).parent = node;
        }
        node.leftChild = pivot.rightChild;
        pivot.rightChild = node;
        pivot.balanceFactor = node.balanceFactor = 0;
    }
    private void right2(AVLNode<T> node){
        AVLNode<T> parent = node.parent;
        AVLNode<T> pivot = (AVLNode<T>)node.rightChild;

        if(node == root){
            root = pivot;
        }else {
            if(parent.leftChild == node){
                parent.leftChild = pivot;
            }else {
                parent.rightChild = pivot;
            }
        }
        pivot.parent = parent;
        node.parent = pivot;
        if(pivot.leftChild != null) {
            ((AVLNode)pivot.leftChild).parent = node;
        }
        node.rightChild = pivot.leftChild;
        pivot.leftChild = node;
        pivot.balanceFactor = node.balanceFactor = 0;
    }
    private void leftRight(AVLNode<T> node){
        AVLNode<T> parent = node.parent;
        AVLNode<T> leftNode = (AVLNode<T>)node.leftChild;
        AVLNode<T> pivot = (AVLNode<T>)leftNode.rightChild;

        if(node == root){
            root = pivot;
        }else {
            if(parent.leftChild == node){
                parent.leftChild = pivot;
            }else {
                parent.rightChild = pivot;
            }
        }
        pivot.parent = parent;
        leftNode.parent = pivot;
        node.parent = pivot;
        if(pivot.leftChild != null) {
            ((AVLNode)pivot.leftChild).parent = leftNode;
        }
        if(pivot.rightChild != null) {
            ((AVLNode)pivot.rightChild).parent = node;
        }
        leftNode.rightChild = pivot.leftChild;
        node.leftChild = pivot.rightChild;
        pivot.leftChild = leftNode;
        pivot.rightChild = node;
        node.balanceFactor = leftNode.balanceFactor = 0;
    }
    private void rightLeft(AVLNode<T> node){
        AVLNode<T> parent = node.parent;
        AVLNode<T> rightNode = (AVLNode<T>)node.rightChild;
        AVLNode<T> pivot = (AVLNode<T>)rightNode.leftChild;

        if(node == root){
            root = pivot;
        }else {
            if(parent.leftChild == node){
                parent.leftChild = pivot;
            }else {
                parent.rightChild = pivot;
            }
        }
        pivot.parent = parent;
        node.parent = pivot;
        rightNode.parent = pivot;
        if(pivot.leftChild != null){
            ((AVLNode)pivot.leftChild).parent = node;
        }
        if(pivot.rightChild != null){
            ((AVLNode)pivot.rightChild).parent = rightNode;
        }
        node.rightChild = pivot.leftChild;
        rightNode.leftChild = pivot.rightChild;
        pivot.leftChild = node;
        pivot.rightChild = rightNode;
        node.balanceFactor = rightNode.balanceFactor = 0;
    }
    @Override
    public void delete(int key){

    }
}
