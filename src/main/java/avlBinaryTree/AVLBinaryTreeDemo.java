package main.java.avlBinaryTree;

/**
 * @Author: gjz
 * @Date: 2023/12/26 16:59
 * @Description: TODO 平衡二叉树的旋转
 */
public class AVLBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {4,3,6,5,7,8};
        AVLBinaryTree avlBinaryTree = new AVLBinaryTree();
        for (int i = 0; i < arr.length; i++) {
            avlBinaryTree.add(new Node(arr[i]));
        }
        avlBinaryTree.inOrder();

        System.out.println(avlBinaryTree.leftHeight(avlBinaryTree.root));
        System.out.println(avlBinaryTree.rightHeight(avlBinaryTree.root));
    }
}

class AVLBinaryTree {
    public Node root;

    //左旋转，以node为根节点
    public void leftRotate(Node node) {
        Node newNode = new Node(node.value); //创建一个新节点，值等于根节点
        newNode.left = node.left; //根节点的左子结点赋给新节点的左子结点
        newNode.right = node.right.left; //根节点的右子节点的左子树赋给新节点的右子节点
        node.value = node.right.value; //根节点的右子节点的值赋给根节点
        node.right = node.right.right; //根节点的右子节点的右子节点赋给根节点的右子节点
        node.left = newNode; //新节点赋给根节点的左子结点
    }

    //返回以node为根节点的树的高度
    public int height(Node node) {
        return Math.max(node.left == null ? 0 : height(node.left), node.right == null ? 0 : height(node.right)) + 1;
    }

    //返回node节点的左子树的高度
    public int leftHeight(Node node) {
        if (node.left == null) {
            return 0;
        }
        return height(node.left);
    }

    //返回node节点的右子树的高度
    public int rightHeight(Node node) {
        if (node.right == null) {
            return 0;
        }
        return height(node.right);
    }

    //增加一个节点
    public void add(Node node) {
        if (root == null) {
            root = node;
            return;
        }
        if (node == null) {
            return;
        }
        add(node, root);
    }
    public void add(Node node, Node temp) { //node为需要添加的节点，tmp为当前比较的节点
        if (node.value < temp.value) {  //要添加的节点值比当前节点的值小
            if (temp.left == null) {  //当前节点的左子结点为空，直接添加在左子结点
                temp.left = node;
            } else {
                add(node, temp.left); //将当前节点改为左子节点，继续递归
            }
        } else { //要添加的节点值大于等于当前节点的值
            if (temp.right == null) { ////当前节点的右子结点为空，直接添加在右子结点
                temp.right = node;
            } else {
                add(node, temp.right); //将当前节点改为右子节点，继续递归
            }
        }

        if (rightHeight(temp) - leftHeight(temp) > 1) {
            leftRotate(temp);
        }
    }


    //中序遍历
    public void inOrder() {
        if (root == null) {
            System.out.println("二叉树为空");
            return;
        }
        inOrder(root);
    }
    public void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.println(node);
        inOrder(node.right);
    }
}

class Node {
    public int value;
    public Node left;
    public Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" + "value=" + value + '}';
    }
}
