package bstree.avltree;

import bstree.BinarySearchTree;

/**
 * 在计算机科学中，AVL树是最早被发明的自平衡二叉查找树。在AVL树中，任一节点对应的两棵子树的最大高度差为1，因此它也被称为高度平衡树
 */
public class AVLTree<T> extends BinarySearchTree<T> {
    public class AVLNode extends Node<T>{
        AVLNode parent;
        private AVLNode(int key, T value){
            super(key, value);
            parent = null;
        }
    }
    @Override
    public void insert(int key, T value){

    }
    @Override
    public void delete(int key){

    }
}
