package huffmanTree;

import java.util.ArrayList;
import java.util.Collections;


/**
 * @ClassName：HuffmanTree
 * @Author: gjz
 * @Date: 2023/12/17 23:37
 * @Description: TODO 创建赫夫曼树
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node node = huffmanTree(arr);
        preOrder(node);
    }

    //创建赫夫曼树的方法
    public static Node huffmanTree(int[] arr) {
        ArrayList<Node> list = new ArrayList<>();
        for (int element : arr) { //遍历arr数组
            list.add(new Node(element)); //将每个元素构建成node并添加到集合中
        }
        while (list.size() > 1) { //集合中的元素个数超过一个就循环
            Collections.sort(list);  //集合中的元素从小到大排序

            //取出权值最小的两颗二叉树
            Node leftNode = list.get(0);
            Node rightNode = list.get(1);

            //构建一颗新的二叉树，权值为之前的两树权值之和
            Node parentNode = new Node(leftNode.value + rightNode.value);
            parentNode.left = leftNode; //将之前的两棵树连接在新树的左右节点上
            parentNode.right = rightNode;

            list.remove(leftNode); //从集合中删掉处理过的两棵树
            list.remove(rightNode);
            list.add(parentNode); //将新树添加到集合中
        }
        return list.get(0); //返回集合中的唯一一个元素节点，树的根节点
    }

    //前序遍历
    public static void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node);
        preOrder(node.left);
        preOrder(node.right);
    }
}

class Node implements Comparable<Node> { //实现Comparable接口，这个类可以比较大小
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }
}