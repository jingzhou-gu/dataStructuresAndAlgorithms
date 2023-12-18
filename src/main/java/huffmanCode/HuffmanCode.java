package huffmanCode;

import java.util.*;

/**
 * @Author: gjz
 * @Date: 2023/12/18 14:25
 * @Description: TODO 赫夫曼编码
 */
public class HuffmanCode {
    public static void main(String[] args) {
        String context = "i like like like java do you like a java";
        byte[] bytes = context.getBytes();

        ArrayList<Node> list = getList(bytes);
        Node node = createHuffmanTree(list); //获取赫夫曼树
        preOrder(node); //遍历赫夫曼树

        HashMap<Byte, String> map = new HashMap<>();
        StringBuilder stringBuilder = new StringBuilder();
        Map<Byte, String> huffmanCode = getHuffmanCode(node, "", stringBuilder, map);  //获取赫夫曼编码
        System.out.println(huffmanCode);

        byte[] zip = zip(bytes, huffmanCode); //将byte数组按照赫夫曼编码压缩
        System.out.println(Arrays.toString(zip));
    }

    //将一个Byte数组转换为list集合
    public static ArrayList<Node> getList(byte[] bytes) {
        ArrayList<Node> nodes = new ArrayList<>();

        HashMap<Byte, Integer> map = new HashMap<>();
        for (Byte element : bytes) {  //遍历byte数组，将每个元素放入map集合
            map.put(element, map.getOrDefault(element, 0) + 1);  //key为不同的元素，value为出现的次数
        }

        for (Map.Entry<Byte, Integer> entry : map.entrySet()) { //将map集合的数遍历，放入集合中
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }

        return nodes; //返回集合
    }

    //创建赫夫曼树
    public static Node createHuffmanTree(ArrayList<Node> list) {
        while (list.size() > 1) {
            Collections.sort(list);

            Node leftNode = list.get(0);
            Node rightNode = list.get(1);
            Node parentNode = new Node(null, leftNode.value + rightNode.value);
            parentNode.left = leftNode;
            parentNode.right = rightNode;

            list.remove(leftNode);
            list.remove(rightNode);
            list.add(parentNode);
        }
        return list.get(0);
    }

    //获取赫夫曼编码，左路径为'0',右路径为'1'
    public static Map<Byte, String> getHuffmanCode(Node node, String code, StringBuilder stringBuilder, Map<Byte, String> map) {
        if (node == null) {
            return null;
        }
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder); //创建stringBuilder拼接路径
        stringBuilder2.append(code); //将当前路径添加到stringBuilder中
        if (node.data == null) {  //如果node.date为空，说明不是叶子结点，往下递归
            getHuffmanCode(node.left, "0", stringBuilder2, map);
            getHuffmanCode(node.right, "1", stringBuilder2, map);
        } else { //到达叶子结点，将数据和路径添加到map集合中
            map.put(node.data, stringBuilder2.toString());
        }
        return map;
    }

    //按照赫夫曼编码压缩
    public static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCode) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte element : bytes) {
            stringBuilder.append(huffmanCode.get(element)); //将每个byte按照赫夫曼编码添加到stringBuilder中
        }

        int length = 0;
        if (stringBuilder.length() % 8 == 0) { //确定新的byte数组的长度
            length = stringBuilder.length() / 8;
        } else {  //不足8位，多加一个字节
            length = stringBuilder.length() / 8 + 1;
        }

        byte[] newByte = new byte[length]; //创建转换后的新byte数组

        int index = 0;
        String substr;
        for (int i = 0; i < stringBuilder.length(); i += 8) {  //每8个转换成一个字节
            if (i + 8 > stringBuilder.length()) {  //stringBuilder.length()不能被8整除
                substr = stringBuilder.substring(i); //stringBuilder.length()如果最后不能被8整除，就截取到末尾
            } else {
                substr = stringBuilder.substring(i, i + 8); //每次截取8位
            }
            newByte[index] = (byte) Integer.parseInt(substr, 2); //将每次截取的8位准换成一个byte放入新数组中
            index++;
        }

        return newByte; //返回新数组
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

class Node implements Comparable<Node> {
    Byte data; //数据
    int value; //数据出现的次数
    Node left;
    Node right;

    public Node(Byte data, int value) {
        this.data = data;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }
}