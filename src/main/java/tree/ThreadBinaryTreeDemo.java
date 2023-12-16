package tree;

/**
 * @ClassName：ThreadBinaryTreeDemo
 * @Author: gjz
 * @Date: 2023/12/16 18:13
 * @Description: TODO 线索化二叉树
 */
public class ThreadBinaryTreeDemo {
    public static void main(String[] args) {
        Node node1 = new Node(1, "zs");
        Node node2 = new Node(2, "ls");
        Node node3 = new Node(3, "ww");
        Node node4 = new Node(4, "zl");
        Node node5 = new Node(5, "jz");
        Node node6 = new Node(6, "cx");

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;

        ThreadBinaryTree threadBinaryTree = new ThreadBinaryTree();
        threadBinaryTree.root = node1;
        threadBinaryTree.inThreadNode();

        System.out.println(node5.left);
        System.out.println(node5.right);

        threadBinaryTree.inThreadList();
    }
}

class ThreadBinaryTree {
    public Node root;  //根节点
    public Node pre;  //上一个节点

    //中序线索化二叉树
    public void inThreadNode() {
        inThreadNode(root);
    }
    public void inThreadNode(Node node) {
        if (node == null) {
            return;
        }
        inThreadNode(node.left); //线索化左子树
        if (node.left == null) {  //线索化当前节点的左子树
            node.left = pre;  //当前节点的左子节点指向前驱节点
            node.leftType = 1; //当前节点的左子节点类型为前驱结点
        }
        if (pre != null && pre.right == null) { //处理后继节点
            pre.right = node; //让前驱节点的右指针指向当前节点
            pre.rightType = 1; //修改前驱结点的右指针类型为后继节点类型
        }
        pre = node; //每处理一个节点，让当前节点是下一个节点的前驱结点 ！！！
        inThreadNode(node.right); //线索化右子树
    }

    //中序线索化二叉树的遍历
    public void inThreadList() {
        inThreadList(root);
    }
    public void inThreadList(Node node) {
        if (node == null) {
            return;
        }
        while (node != null) {
            while (node.leftType != 1) { //找到节点的左子结点类型为前驱节点的，为开始节点
                node = node.left; //未找到，就往左子遍历
            }
            System.out.println(node);
            while (node.rightType == 1) {
                node = node.right;
                System.out.println(node);
            }
            node = node.right;
        }
    }
}

class Node {
    public int no;
    public String name;
    public Node left;
    public Node right;
    public int leftType;  //左子节点类型，0代表左子树，1代表指向前驱结点
    public int rightType; //右子节点类型，0代表右子树，1代表指向后继节点

    public Node(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Node{" + "no=" + no + ", name='" + name + '\'' + '}';
    }
}