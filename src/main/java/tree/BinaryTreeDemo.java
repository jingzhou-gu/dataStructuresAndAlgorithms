package tree;

import jdk.management.resource.internal.inst.SocketOutputStreamRMHooks;

import java.net.SocketTimeoutException;

/**
 * @ClassName：BinaryTree
 * @Author: gjz
 * @Date: 2023/12/14 21:13
 * @Description: TODO 二叉树
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        HeroNode node1 = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "卢俊义");
        HeroNode node3 = new HeroNode(3, "吴用");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");
        HeroNode node6 = new HeroNode(6, "武松");
        node1.left = node2;
        node2.left = node6;
        node1.right = node3;
        node3.right = node4;
        node3.left = node5;
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.root = node1;
        binaryTree.preOrder();
//        binaryTree.inOrder(node1);
//        binaryTree.postOrder(node1);
        System.out.println("-----------");
        System.out.println(binaryTree.preOrderSearch(1));

        System.out.println("-------------");
        binaryTree.remove(6);
        binaryTree.preOrder();
    }
}

//二叉树
class BinaryTree {
    public HeroNode root; //根节点

    //前序遍历
    public void preOrder() {
        preOrder(root);
    }
    public void preOrder(HeroNode node) {
        if (node == null) {
            return;
        }
        System.out.println(node); //遍历当前节点
        preOrder(node.left);  //遍历左子树
        preOrder(node.right); //遍历右子树
    }

    //前序查找
    public HeroNode preOrderSearch(int no) {
        return preOrderSearch(no, root);
    }
    public HeroNode preOrderSearch(int no, HeroNode root) {
        if (root == null) {
            return null;
        }
        if (root.no == no) {
            return root;
        }
        HeroNode temp = null;
        temp = preOrderSearch(no, root.left);
        if (temp != null) {
            return temp;
        }
        temp = preOrderSearch(no, root.right);
        return temp;
    }

    //中序遍历
    public void inOrder() {
        inOrder(root);
    }
    public void inOrder(HeroNode node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.println(node);
        inOrder(node.right);
    }

    //后序遍历
    public void postOrder() {
        postOrder(root);
    }
    public void postOrder(HeroNode node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node);
    }

    //删除元素
    public void remove(int no) {
        if (root == null) {
            System.out.println("该二叉树为空");
            return;
        }
        if (root.no == no) {  //判断根节点
            System.out.println(root.toString() + "删除成功");
            root = null;
            return;
        }
        remove(no, root); //删除元素不是根节点，进行左右遍历
    }
    public void remove(int no, HeroNode root) {
        if (root.left != null && root.left.no == no) {  //判断当前节点的左子树是否是删除元素
            root.left = null;
            return;
        }
        if (root.right != null && root.right.no == no) { //判断当前节点的右子树是否是删除元素
            root.right = null;
            return;
        }
        if (root.left != null) {  //当前节点的左子树不为空
            remove(no, root.left); //递归进行左子树的左右子树遍历
        }
        if (root.right != null) { //当前节点的右子树不为空
            remove(no, root.right); //递归进行右子树的左右子树遍历
        }
    }
}

class HeroNode {
    public int no;
    public String name;
    public HeroNode left;
    public HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeroNode{" + "no=" + no + ", name='" + name + '\'' + '}';
    }
}