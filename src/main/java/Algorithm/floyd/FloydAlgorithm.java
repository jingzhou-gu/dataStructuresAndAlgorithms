package main.java.Algorithm.floyd;

import java.util.Arrays;

/**
 * @Author: gjz
 * @Date: 2024/1/23 18:13
 * @Description: TODO 弗洛伊德算法
 */
public class FloydAlgorithm {
    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        final int N = 65535;
        int[][] matrix = {
                {0, 5, 7, N, N, N, 2},
                {5, 0, N, 9, N, N, 3},
                {7, N, 0, N, 8, N, N},
                {N, 9, N, 0, N, 4, N},
                {N, N, 8, N, 0, 5, 4},
                {N, N, N, 4, 5, 0, 6},
                {2, 3, N, N, 4, 6, 0}
        };
        Graph graph = new Graph(vertex.length, vertex, matrix);

        graph.floyd();
        graph.show();
    }
}

class Graph {
    char[] vertex; //顶点数组
    int[][] dis; //各顶点到其他顶点的距离
    int[][] pre; //到达目标顶点的前驱顶点

    public Graph(int length, char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.dis = matrix;
        this.pre = new int[length][length];

        for (int i = 0; i < length; i++) {
            Arrays.fill(pre[i], i);
        }
    }

    //弗洛伊德算法
    public void floyd(){
        int length = 0;
        // k为中间节点
        for (int k = 0; k < dis.length; k++) {
            // i为开始节点
            for (int i = 0; i < dis.length; i++) {
                // j为结束节点
                for (int j = 0; j < dis.length; j++) {
                    length = dis[i][k] + dis[k][j]; // 以k为中间点，从i->k->j的距离
                    if(length < dis[i][j]){  //从i->k->j的距离小于i->j的直接距离
                        dis[i][j] = length;  //更新i->j的距离
                        pre[i][j] = pre[k][j]; //更新它的前驱节点
                    }
                }
            }
        }
    }

    public void show() {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        for (int i = 0; i < dis.length; i++) {
            //输出pre数组的一行
            for (int j = 0; j < dis.length; j++) {
                System.out.print(vertex[pre[i][j]] + " ");
            }
            System.out.println();
            //输出dis数组的一行
            for (int j = 0; j < dis.length; j++) {
                System.out.print("(" + vertex[i] + "到" + vertex[j] + "的最短路径为" + dis[i][j] + ") ");
            }
            System.out.println();
            System.out.println();
        }
    }
}
