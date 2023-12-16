package tree;

/**
 * @ClassName：ArrayBinaryTreeDemo
 * @Author: gjz
 * @Date: 2023/12/16 14:03
 * @Description:TODO 顺序存储二叉树的遍历
 */
public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);
        arrayBinaryTree.preOrder(0);
        System.out.println();
        arrayBinaryTree.inOrder(0);
        System.out.println();
        arrayBinaryTree.postOrder(0);
    }
}

class ArrayBinaryTree {
    public int[] arr;

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    //顺序存储二叉树的前序遍历
    public void preOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空");
            return;
        }
        System.out.print(arr[index] + " ");
        if (2 * index + 1 < arr.length) {  //顺序存储二叉树，当前节点下标为n,左子结点为2n+1，右子节点为2n+2
            preOrder(2 * index + 1);
        }
        if (2 * index + 2 < arr.length) {
            preOrder(2 * index + 2);
        }
    }

    //顺序存储二叉树的中序遍历
    public void inOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空");
            return;
        }
        if (2 * index + 1 < arr.length) {
            inOrder(2 * index + 1);
        }
        System.out.print(arr[index] + " ");
        if (2 * index + 2 < arr.length) {
            inOrder(2 * index + 2);
        }
    }

    //顺序存储二叉树的后序遍历
    public void postOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空");
            return;
        }
        if (2 * index + 1 < arr.length) {
            postOrder(2 * index + 1);
        }
        if (2 * index + 2 < arr.length) {
            postOrder(2 * index + 2);
        }
        System.out.print(arr[index] + " ");
    }
}
