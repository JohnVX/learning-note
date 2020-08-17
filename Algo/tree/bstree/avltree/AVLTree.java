package tree.bstree.avltree;

/**
 * 在计算机科学中， AVL 树是最早被发明的自平衡二叉查找树。在 AVL 树中，任一节点对应的两棵子树的最大高度差为1，因此它也被称为高度平衡树
 */
public class AVLTree<T>{
    private AVLNode root;
    public class AVLNode{
        AVLNode parent;
        //平衡因子: 左子树高度 - 右子树高度
        int balanceFactor;
        private int key;
        private T value;
        private AVLNode leftChild;
        private AVLNode rightChild;
        private AVLNode(int key, T value, AVLNode parent){
            this.parent = parent;
            balanceFactor = 0;
            this.key = key;
            this.value = value;
            this.leftChild = this.rightChild = null;
        }
    }
    public void insert(int key, T value){
        if(root == null){
            root = new AVLNode(key, value, null);
            return;
        }
        AVLNode node = root;
        while(true){
            if(node.key == key){
                System.out.println("不可插入重复数据, key=" + key + ", value=" + node.value + ", 本次计划插入的 value=" + value);
                return;
            }
            if(node.key < key){
                if(node.rightChild == null){
                    node.rightChild = new AVLNode(key, value, node);
                    node.balanceFactor--;
                    break;
                }else {
                    node = node.rightChild;
                }
            }else {
                if(node.leftChild == null){
                    node.leftChild = new AVLNode(key, value, node);
                    node.balanceFactor++;
                    break;
                }else {
                    node = node.leftChild;
                }
            }
        }
        AVLNode rotateRoot = null;
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
    private void rotate(AVLNode node){
        assert node != null && (node.balanceFactor == -2 || node.balanceFactor == 2);

        if(node.balanceFactor == 2 && node.leftChild.balanceFactor == 1){
            left2(node);
        }
        if(node.balanceFactor == -2 && node.rightChild.balanceFactor == -1){
            right2(node);
        }
        if(node.balanceFactor == 2 && node.leftChild.balanceFactor == -1){
            leftRight(node);
        }
        if(node.balanceFactor == -2 && node.rightChild.balanceFactor == 1){
            rightLeft(node);
        }
    }
    private void left2(AVLNode node){

    }
    private void right2(AVLNode node){

    }
    private void leftRight(AVLNode node){

    }
    private void rightLeft(AVLNode node){

    }
    public void delete(int key){

    }
}
