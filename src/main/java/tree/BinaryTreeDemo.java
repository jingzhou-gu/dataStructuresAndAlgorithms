package tree;

/**
 * @ClassName：BinaryTree
 * @Author: gjz
 * @Date: 2023/12/14 21:13
 * @Description:
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        HeroNode node1 = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "卢俊义");
        HeroNode node3 = new HeroNode(3, "吴用");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");
        node1.left = node2;
        node1.right = node3;
        node3.right = node4;
        node3.left = node5;

        BinaryTree binaryTree = new BinaryTree();
        binaryTree.preOrder(node1);
        binaryTree.inOrder(node1);
        binaryTree.postOrder(node1);
    }
}

class BinaryTree {
    public HeroNode root;

    //前序遍历
    public void preOrder(HeroNode node) {
        if (node == null) {
            return;
        }
        System.out.println(node);
        preOrder(node.left);
        preOrder(node.right);
    }

    //中序遍历
    public void inOrder(HeroNode node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.println(node);
        inOrder(node.right);
    }

    //后序遍历
    public void postOrder(HeroNode node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node);
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