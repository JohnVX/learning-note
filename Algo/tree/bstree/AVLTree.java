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
        //If the balance factor temporarily becomes ±2, this has to be repaired by an appropriate rotation after which the subtree has the same height as before
        AVLNode<T> rotateRoot = null;
        while(node != this.root){
            //The retracing can stop if the balance factor becomes 0 implying that the height of that subtree remains unchanged.
            if(node.balanceFactor == 0){
                break;
            }
            if (node.parent.leftChild == node) {
                node.parent.balanceFactor++;
                if(node.parent.balanceFactor > 1){
                    rotateRoot = node.parent;
                    break;
                }
            } else{
                node.parent.balanceFactor--;
                if(node.parent.balanceFactor < -1){
                    rotateRoot = node.parent;
                    break;
                }
            }
            node = node.parent;
        }
        if(rotateRoot != null){
            rotateForInsertion(rotateRoot);
        }
    }
    private void rotateForInsertion(AVLNode<T> node){
        assert node != null && (node.balanceFactor == -2 || node.balanceFactor == 2);

        if(node.balanceFactor == 2 && ((AVLNode)node.leftChild).balanceFactor == 1){
            rightRotation(node, RotationForType.Insertion);
            return;
        }
        if((node.balanceFactor == -2) && ((AVLNode)node.rightChild).balanceFactor == -1){
            leftRotation(node, RotationForType.Insertion);
            return;
        }
        if(node.balanceFactor == 2 && ((AVLNode)node.leftChild).balanceFactor == -1){
            leftRightRotation(node, RotationForType.Insertion);
            return;
        }
        if(node.balanceFactor == -2 && ((AVLNode)node.rightChild).balanceFactor == 1){
            rightLeftRotation(node, RotationForType.Insertion);
        }
    }
    enum RotationForType {
        Insertion, Deletion
    }
    /**
     * 删除节点
     * @param key 要被删除节点的 key
     */
    @Override
    public void delete(int key){
        AVLNode<T> parentNode = deleteO(key);
        if(parentNode == null) return;
        AVLNode<T> rotateRoot;

        rotateRoot = backtraceForDeletion(parentNode);
        //从底层节点开始做旋转, 然后向上进行旋转
        if(rotateRoot != null){
            rotateForDeletion(rotateRoot);
        }
    }
    /**
     * 向上回溯更新树节点的平衡因子
     * @param node 节点
     * @return 需要做旋转操作的节点
     */
    private AVLNode<T> backtraceForDeletion(AVLNode<T> node){
        AVLNode<T> rotateRoot = null;
        while (node != null){
            if(node.balanceFactor < -1 || node.balanceFactor > 1){
                rotateRoot = node;
            }
            //node.balanceFactor != 0, 说明此次的删除节点操作没有使以 node 为根的子树高度降低, 因此更上层树的平衡情况不变,此时无需继续向上回溯更新树节点的平衡因子了
            if(node.balanceFactor != 0){
                break;
            }
            if(node.parent != null){
                if(node.parent.leftChild == node){
                    node.parent.balanceFactor--;
                }else {
                    node.parent.balanceFactor++;
                }
            }
            node = node.parent;
        }
        return rotateRoot;
    }
    /**
     * 查找节点 -> 删除节点 -> 更新被删除节点的父节点的平衡因子
     * @param key 要删除的节点的 key
     * @return parentNode 被删除的节点的父节点
     */
    private AVLNode<T> deleteO(int key){
        AVLNode<T> node = (AVLNode<T>)root;
        AVLNode<T> parentNode = null;
        while (true){
            if(node.key == key) {
                if (node.leftChild == null && node.rightChild == null) {
                    if (node == root) {
                        root = null;
                        return null;
                    }
                    if (parentNode.leftChild == node) {
                        parentNode.leftChild = null;
                        parentNode.balanceFactor--;
                    } else {
                        parentNode.rightChild = null;
                        parentNode.balanceFactor++;
                    }
                } else if (node.leftChild == null) {
                    if (node == root) {
                        root = root.rightChild;
                        ((AVLNode<T>)root).parent = null;
                        return null;
                    }
                    if (parentNode.leftChild == node) {
                        parentNode.leftChild = node.rightChild;
                        parentNode.balanceFactor--;
                    } else {
                        parentNode.rightChild = node.rightChild;
                        parentNode.balanceFactor++;
                    }
                    ((AVLNode<T>)node.rightChild).parent = parentNode;
                } else if (node.rightChild == null) {
                    if (node == root) {
                        root = root.leftChild;
                        ((AVLNode<T>)root).parent = null;
                        return null;
                    }
                    if (parentNode.leftChild == node) {
                        parentNode.leftChild = node.leftChild;
                        parentNode.balanceFactor--;
                    } else {
                        parentNode.rightChild = node.leftChild;
                        parentNode.balanceFactor++;
                    }
                    ((AVLNode<T>)node.leftChild).parent = parentNode;
                } else {
                    //找前邻节点, 这一定是个叶子节点, delete0 的递归次数一定只有 1
                    Node<T> replaceNode = findPredecessorNode(node);
                    parentNode = deleteO(replaceNode.key);
                    node.replace(replaceNode.key, replaceNode.value);
                }
                return parentNode;
            }
            parentNode = node;
            if(node.key < key){
                node = (AVLNode<T>)node.rightChild;
            }else{
                node = (AVLNode<T>)node.leftChild;
            }
            if(node == null){
                System.out.println("要删除的数据不存在, key=" + key);
                return null;
            }
        }
    }
    /**
     * 删除操作的旋转条件和插入操作的略有不同,
     * 并且对于删除操作, 做完一次旋转后, 还需向上回溯寻找可能的失衡节点并对其做旋转
     * @param node 要旋转的节点
     */
    private void rotateForDeletion(AVLNode<T> node){
        if (node != null) {
            AVLNode<T> parent = node.parent;
            AVLNode<T> rotationRoot;
            if (node.balanceFactor == 2) {
                int factor = ((AVLNode) node.leftChild).balanceFactor;
                if (factor == 1 || factor == 0) {
                    rightRotation(node, RotationForType.Deletion);
                } else if (factor == -1) {
                    leftRightRotation(node, RotationForType.Deletion);
                }
            } else if (node.balanceFactor == -2) {
                int factor = ((AVLNode) node.rightChild).balanceFactor;
                if (factor == -1 || factor == 0) {
                    leftRotation(node, RotationForType.Deletion);
                } else if (factor == 1) {
                    rightLeftRotation(node, RotationForType.Deletion);
                }
            }
            node = parent;
            rotationRoot = backtraceForDeletion(node);
            if(rotationRoot != null){
                rotateForDeletion(rotationRoot);
            }
        }
    }
    private void rightRotation(AVLNode<T> node, RotationForType rotationForType){
        AVLNode<T> pivot = (AVLNode<T>)node.leftChild;

        adjustParentRelationForOneRotation(node, pivot, rotationForType);

        if(pivot.rightChild != null) {
            ((AVLNode)pivot.rightChild).parent = node;
        }
        node.leftChild = pivot.rightChild;
        pivot.rightChild = node;
        if(rotationForType == RotationForType.Insertion) {
            pivot.balanceFactor = node.balanceFactor = 0;
        } else if (rotationForType == RotationForType.Deletion) {
            node.balanceFactor--;
            pivot.balanceFactor--;
        }
    }
    private void leftRotation(AVLNode<T> node, RotationForType rotationForType){
        AVLNode<T> pivot = (AVLNode<T>)node.rightChild;

        adjustParentRelationForOneRotation(node, pivot, rotationForType);

        if(pivot.leftChild != null){
            ((AVLNode<T>)pivot.leftChild).parent = node;
        }
        node.rightChild = pivot.leftChild;
        pivot.leftChild = node;
        if(rotationForType == RotationForType.Insertion){
            pivot.balanceFactor = node.balanceFactor = 0;
        }else if(rotationForType == RotationForType.Deletion){
            node.balanceFactor++;
            pivot.balanceFactor++;
        }
    }
    private void leftRightRotation(AVLNode<T> node, RotationForType rotationForType){
        AVLNode<T> leftNode = (AVLNode<T>)node.leftChild;
        AVLNode<T> pivot = (AVLNode<T>)leftNode.rightChild;

        adjustParentRelationForTwoRotation(node, pivot, leftNode, rotationForType);
        adjustChildRelationForRotation(pivot, leftNode, node);
    }
    private void rightLeftRotation(AVLNode<T> node, RotationForType rotationForType){
        AVLNode<T> rightNode = (AVLNode<T>)node.rightChild;
        AVLNode<T> pivot = (AVLNode<T>)rightNode.leftChild;

        adjustParentRelationForTwoRotation(node, pivot, rightNode, rotationForType);
        adjustChildRelationForRotation(pivot, node, rightNode);
    }
    private void adjustChildRelationForRotation(AVLNode<T> pivot, AVLNode<T> node1, AVLNode<T> node2){
        node1.parent = pivot;
        node2.parent = pivot;
        if(pivot.leftChild != null){
            ((AVLNode)pivot.leftChild).parent = node1;
        }
        if(pivot.rightChild != null){
            ((AVLNode)pivot.rightChild).parent = node2;
        }
        node1.rightChild = pivot.leftChild;
        node2.leftChild = pivot.rightChild;
        pivot.leftChild = node1;
        pivot.rightChild = node2;
        node1.balanceFactor = node2.balanceFactor = 0;
    }
    private void adjustParentRelationForOneRotation(AVLNode<T> node, AVLNode<T> pivot, RotationForType rotationForType){
        adjustParentRelationForRotation(node, pivot, null, rotationForType);
    }
    private void adjustParentRelationForTwoRotation(AVLNode<T> node, AVLNode<T> pivot, AVLNode<T> child, RotationForType rotationForType){
        adjustParentRelationForRotation(node, pivot, child, rotationForType);
    }
    private void adjustParentRelationForRotation(AVLNode<T> node, AVLNode<T> pivot, AVLNode<T> child, RotationForType rotationForType){
        AVLNode<T> parent = node.parent;

        if(node == root){
            root = pivot;
        }else {
            if(parent.leftChild == node){
                parent.leftChild = pivot;
                if(rotationForType == RotationForType.Deletion){
                    if(child != null){
                        if(child.balanceFactor == -1 || child.balanceFactor == 1){
                            parent.balanceFactor--;
                        }
                    }else {
                        if(pivot.balanceFactor == -1 || pivot.balanceFactor == 1){
                            parent.balanceFactor--;
                        }
                    }
                }
            }else {
                parent.rightChild = pivot;
                if(rotationForType == RotationForType.Deletion){
                    if(child != null) {
                        if (child.balanceFactor == -1 || child.balanceFactor == 1) {
                            parent.balanceFactor++;
                        }
                    }else {
                        if (pivot.balanceFactor == -1 || pivot.balanceFactor == 1) {
                            parent.balanceFactor++;
                        }
                    }
                }
            }
        }
        node.parent = pivot;
        pivot.parent = parent;
    }
}
