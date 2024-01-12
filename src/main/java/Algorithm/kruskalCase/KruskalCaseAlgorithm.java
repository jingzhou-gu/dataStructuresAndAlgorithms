package main.java.Algorithm.kruskalCase;

import java.util.Arrays;

/**
 * @Author: gjz
 * @Date: 2024/1/12 14:04
 * @Description: TODO 克鲁斯卡尔算法
 */
public class KruskalCaseAlgorithm {
    public static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = {
                {0, 12, INF, INF, INF, 16, 14},
                {12, 0, 10, INF, INF, 7, INF},
                {INF, 10, 0, 3, 5, 6, INF,},
                {INF, INF, 3, 0, 4, INF, INF,},
                {INF, INF, 5, 4, 0, 2, 8,},
                {16, 7, 6, INF, 2, 0, 9},
                {14, INF, INF, INF, 8, 9, 0}
        };

        KruskalCase kruskalCase = new KruskalCase(vertexs, matrix);
        System.out.println(kruskalCase.edgeNum);
        kruskalCase.show();
        EData[] edges = kruskalCase.getEdges();
        System.out.println(Arrays.toString(edges));
        kruskalCase.sortEdges(edges);
        System.out.println(Arrays.toString(edges));
    }
}

class KruskalCase {
    public int edgeNum; //边的个数
    public char[] vertexs; //顶点数组
    public int[][] matrix; //邻接矩阵
    public static final int INF = Integer.MAX_VALUE; //表示两个顶点不能相通

    public KruskalCase(char[] vertexs, int[][] matrix) {
        this.vertexs = vertexs;
        this.matrix = matrix;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix[0].length; j++) {
                if (matrix[i][j] != INF) {
                    edgeNum++;
                }
            }
        }
    }

    //克鲁斯卡尔算法
    public void kruskal(){
        int index = 0; //结果数组的下标
        EData[] results = new EData[vertexs.length-1]; //存放最终结果集
        int[] ends = new int[vertexs.length-1]; //存放下标为i的顶点对应的终点的下标

        EData[] edges = getEdges(); //获取所有边的数组
        sortEdges(edges); //对边的数组进行排序

        for (int i = 0; i < edges.length; i++) {  //对所有边进行遍历
            int h1 = getPosition(edges[i].start);
            int h2 = getPosition(edges[i].end);

            int end1 = getEnd(ends, h1);
            int end2 = getEnd(ends, h2);

            if(end1 != end2){
                results[index++] = edges[i];
                ends[end1] = end2;
            }
        }

        System.out.println(Arrays.toString(results));

    }

    //显示矩阵
    public void show() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.printf("%12d", matrix[i][j]);
            }
            System.out.println();
        }
    }

    //获取下标为i的顶点对应的终点的下标，ends：记录了各顶点对应的终点是哪个， i：传入的顶点的下标
    public int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }

    //对边进行排序
    public void sortEdges(EData[] edges) {
        for (int i = 0; i < edges.length; i++) {
            for (int j = 0; j < edges.length - 1 - i; j++) {
                if (edges[j].weight > edges[j + 1].weight) {
                    EData temp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = temp;
                }
            }
        }
    }

    //获取图中的边，放入EData数组中
    public EData[] getEdges() {
        int index = 0;
        EData[] edges = new EData[edgeNum];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix.length; j++) {
                if (matrix[i][j] != INF) {
                    edges[index++] = new EData(vertexs[i], vertexs[j], matrix[i][j]);
                }
            }
        }
        return edges;
    }

    //返回顶点对应的下标
    public int getPosition(char vertex) {
        for (int i = 0; i < vertexs.length; i++) {
            if (vertexs[i] == vertex) {
                return i;
            }
        }
        return -1;
    }
}

//一个对象表示一条边
class EData {
    public char start;
    public char end;
    public int weight;

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EData{" + "start=" + start + ", end=" + end + ", weight=" + weight + '}';
    }
}