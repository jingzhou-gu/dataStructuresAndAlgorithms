package main.java.Algorithm.dijkstra;

import java.util.Arrays;

/**
 * @Author: gjz
 * @Date: 2024/1/15 10:53
 * @Description: TODO 迪杰斯特拉算法
 */
public class DijkstraAlgorithm {
    public static void main(String[] args) {
        char[] vertex = {'A','B','C','D','E','F','G'};
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;
        matrix[0] = new int[]{N,5,7,N,N,N,2};
        matrix[1] = new int[]{5,N,N,9,N,N,3};
        matrix[2] = new int[]{7,N,N,N,8,N,N};
        matrix[3] = new int[]{N,9,N,N,N,4,N};
        matrix[4] = new int[]{N,N,8,N,N,5,4};
        matrix[5] = new int[]{N,N,N,4,5,N,6};
        matrix[6] = new int[]{2,3,N,N,4,6,N};

        Graph graph = new Graph(vertex,matrix);

    }
}

class Graph{
    public char[] vertex;
    public int[][] matrix;

    public Graph(char[] vertex,int[][] matrix){
        this.vertex = vertex;
        this.matrix = matrix;
    }
    public void showGraph(){
        for (int[] link : matrix) {
            System.out.println(Arrays.toString(link));
        }
    }

    public void dijkstra(int index){
        VisitedVertex vv = new VisitedVertex(vertex.length, index);
    }
}

//已访问顶点集合
class VisitedVertex{
    public int[] isVisited; //记录各顶点是否被访问过
    public int[] pre_visited; //每个下标对应的值为它的前一个顶点下标，会动态更新
    public int[] dis; //记录出发点到其他各顶点的最短距离，出发点到自己距离为0

    public VisitedVertex(int length,int index){ //length为顶点个数，index为顶点下标
        isVisited = new int[length];
        pre_visited = new int[length];
        Arrays.fill(dis,65535);
        dis[index] = 0;
    }
}