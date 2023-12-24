package binarySortTree;

/**
 * @ClassName：BinarySortTreeDemo
 * @Author: gjz
 * @Date: 2023/12/23 0:16
 * @Description: TODO 二叉排序树的添加、遍历、删除
 */

public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }

        binarySortTree.inOrder();

        binarySortTree.remove(3);
        System.out.println("-----");
        binarySortTree.inOrder();
    }
}

class BinarySortTree {
    public Node root; //根节点

    //增加一个节点
    public void add(Node node) {
        if (root == null) {
            root = node;
            return;
        }
        add(node, root);
    }
    public void add(Node node, Node temp) { //node为需要添加的节点，tmp为当前比较的节点
        if (node == null) {
            return;
        }
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
    }

    //删除一个节点
    public void remove(int value) {
        Node target = search(value); //查找要删除的节点
        if (target == null) {
            return;
        }
        Node parent = searchParent(value); //查找要删除节点的父节点
        if (parent == null && root.left == null && root.right == null) {  //要删除的节点为根节点且没有左右子树
            root = null; //直接将根节点置为null
            return;
        }
        //对删除节点的类型进行判断
        if (target.left == null && target.right == null) { //删除的节点为叶子结点
            if (parent.left != null && target.value == parent.left.value) {  //删除的节点为父节点的左子树
                parent.left = null;
            } else if (parent.right != null && target.value == parent.right.value) { //删除的节点为父节点的右子树
                parent.right = null;
            }
        } else if (target.left != null && target.right != null) { //删除的节点有左右两颗子树
            int minValue = delRightTreeMin(target); //删除以node为根节点的右子树中的最小节点，且返回最小节点的值
            target.value = minValue;  //用最小节点的值替换要删除的节点的值
        } else { //剩余情况为删除的节点有一颗左子树或右子树
            if (parent == null) { //说明删除的是根节点
                if (target.left != null) {  //删除的节点有一颗左子树
                    root = target.left;  //将删除的节点的左子结点赋给根节点
                } else {
                    root = target.right; //否则将删除的节点的右子结点赋给根节点
                }
                return;
            }
            //说明删除的不是根节点
            if (target.left != null) {  //删除的节点有一颗左子树
                if (parent.left != null && parent.left.value == target.value) { //删除的节点为父节点的左子树
                    parent.left = target.left; //将删除节点的左子树赋给父节点的左子树
                } else { //删除的节点为父节点的右子树
                    parent.right = target.left; //将删除节点的左子树赋给父节点的右子树
                }
            } else { //删除的节点有一颗右子树
                if (parent.left != null && parent.left.value == target.value) { //删除的节点为父节点的左子树
                    parent.left = target.right; //将删除节点的右子树赋给父节点的左子树
                } else { //删除的节点为父节点的右子树
                    parent.right = target.right; //将删除节点的右子树赋给父节点的右子树
                }
            }
        }
    }

    //删除以node为根节点的右子树中的最小节点，且返回最小节点的值
    public int delRightTreeMin(Node node) {
        Node temp = node.right; //从node的右子树开始遍历
        while (temp.left != null) { //找到最小的节点
            temp = temp.left; //往左子树遍历
        }
        remove(temp.value); //删除掉最小节点
        return temp.value; //返回最小节点的值
    }

    //查询节点
    public Node search(int value) {
        if (root == null) {
            return null;
        }
        return search(value, root);
    }
    public Node search(int value, Node node) {
        if (value == node.value) {  //找到节点
            return node;
        } else if (value < node.value) {  //要找的值比当前遍历节点的值小
            if (node.left == null) {  //并且当前节点的左子结点为空，找不到
                return null;
            } else {
                return search(value, node.left); //并且当前节点的左子结点不为空，左子结点递归
            }
        } else { //要找的值大于等于当前遍历节点的值
            if (node.right == null) { //并且当前节点的右子结点为空，找不到
                return null;
            } else {
                return search(value, node.right); //并且当前节点的右子结点不为空，右子结点递归
            }
        }
    }

    //查询父节点
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        }
        if (value == root.value) { //要找的值等于根节点，则不存在父节点
            return null;
        }
        return searchParent(value, root);
    }
    public Node searchParent(int value, Node node) {
        if (node.left != null && value < node.value) {   //要找的值比当前节点小并且当前节点左子节点不为空
            if (value == node.left.value) { //要找的值等于当前节点左子结点的值，则当前节点为父节点，找到
                return node;
            } else {
                return searchParent(value, node.left); //否则，往左子结点递归
            }
        } else if (node.right != null && value >= node.value) { //要找的值比当前节点大并且当前节点右子节点不为空
            if (value == node.right.value) { //要找的值等于当前节点右子结点的值，则当前节点为父节点，找到
                return node;
            } else {
                return searchParent(value, node.right); //否则，往右子结点递归
            }
        } else {
            return null; //其他情况则不存在父节点
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
        return "Node{" +
                "value=" + value +
                '}';
    }
}
