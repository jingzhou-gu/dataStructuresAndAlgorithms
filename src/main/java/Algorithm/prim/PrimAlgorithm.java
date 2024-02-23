package main.java.Algorithm.prim;

import java.util.Arrays;

/**
 * @Author: gjz
 * @Date: 2024/1/10 14:09
 * @Description: TODO 普利姆算法解决修路问题 （解决最小生成树问题）
 */
public class PrimAlgorithm {
    public static void main(String[] args) {
        char[] data = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int vertex = data.length;
        int[][] weight = {  //10000表示两个点不连通
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000}
        };
        MGraph mGraph = new MGraph(vertex, data, weight);
        MinTree minTree = new MinTree();
        minTree.showGraph(mGraph);
        minTree.prim(mGraph, 0);
    }
}

class MinTree {

    //编写prim算法,得到最小生成树,v为开始的节点下标
    public void prim(MGraph mGraph, int v) {
        int[] visited = new int[mGraph.vertex]; //用来存放节点是否访问过
        visited[v] = 1; //将开始节点设置为已访问过

        int h1 = -1; //用来存放矩阵下标
        int h2 = -1; //用来存放矩阵下标
        int minWeight = 10000; //存放最小权值，10000表示一个大数，可以为任意大的数
        int sumWeight = 0; //存放总的权值结果
        for (int k = 1; k < mGraph.vertex; k++) { //有N个顶点，就有N-1条边，需要N-1次循环
            //确定每一次生成的子图,找一个最小的权值
            for (int i = 0; i < mGraph.vertex; i++) {
                for (int j = 0; j < mGraph.vertex; j++) {
                    if(visited[i] == 0){ //优化：如果i为未访问过,直接跳过此次循环
                        break;
                    }
                    // i为已经访问过的结点的下标，j为未访问过的节点下标
                    if (visited[i] == 1 && visited[j] == 0 && mGraph.weight[i][j] < minWeight) {
                        minWeight = mGraph.weight[i][j]; //找到权值最小的赋给minWeight
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            System.out.println(mGraph.data[h1] + "->" + mGraph.data[h2] + " 权值为：" + minWeight); //找到最小权值路径
            visited[h2] = 1; //当前这个节点与h1为最小权值，标记为访问过
            sumWeight += minWeight; //总权值加上当前最小权值
            minWeight = 10000; //将minWeight重设置为10000
        }
        System.out.println("所有边的总权值为：" + sumWeight); //输出最终权值结果
    }

    //显示图的邻接矩阵
    public void showGraph(MGraph mGraph) {
        for (int[] link : mGraph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }
}

class MGraph {
    public int vertex; //图的节点个数
    public char[] data; //图的节点数据
    public int[][] weight; //图的边的权值

    public MGraph(int vertex, char[] data, int[][] weight) {
        this.vertex = vertex;
        this.data = data;
        this.weight = weight;
    }
}