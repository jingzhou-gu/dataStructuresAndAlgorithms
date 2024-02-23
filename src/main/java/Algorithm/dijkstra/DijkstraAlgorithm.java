package main.java.Algorithm.dijkstra;

import java.util.Arrays;

/**
 * @Author: gjz
 * @Date: 2024/1/15 10:53
 * @Description: TODO 迪杰斯特拉算法 （解决最短路径问题）
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
        graph.showGraph();
        graph.dijkstra(0);
    }
}

class Graph{
    public char[] vertex; //顶点数组
    public int[][] matrix; //顶点矩阵列表
    public int[] pre_visited; //每个下标对应的值为它的前一个顶点下标(前驱顶点),会动态更新
    public int[] isVisited; //记录各顶点是否被访问过
    public int[] dis; //记录出发点到其他各顶点的最短距离，出发点到自己距离为0

    public Graph(char[] vertex,int[][] matrix){
        this.vertex = vertex;
        this.matrix = matrix;
        isVisited = new int[vertex.length];
        pre_visited = new int[vertex.length];
        dis = new int[vertex.length];
        Arrays.fill(dis,65535);
    }

    //展示矩阵
    public void showGraph(){
        for (int[] link : matrix) {
            System.out.println(Arrays.toString(link));
        }
    }

    //获取下一个距离该顶点最近的访问顶点下标
    public int getNewVertex(){
        int min = 65535;
        int index = 0;
        for (int i = 0; i < isVisited.length; i++) {
            if(isVisited[i]==0 && dis[i] < min){ //从未被访问的顶点里面找到离当前顶点最近的顶点
                min = dis[i];
                index = i;
            }
        }
        isVisited[index] = 1; //找到该顶点后，将该顶点置为已访问过
        return index; //返回该顶点下标
    }

    //迪杰斯特拉算法, index为开始顶点下标
    public void dijkstra(int index){
        dis[index] = 0; //将起始点的距离置为0
        for (int i = 0; i < vertex.length; i++) { //有几个顶点，就要遍历多少次
            index = getNewVertex(); //获取下一个最近的新顶点
            for (int j = 0; j < vertex.length; j++) {
                //起点到j点的距离 = 起点到当前顶点的距离 + 当前顶点到j点的距离
                int length = dis[index] + matrix[index][j];
                if(length < dis[j]){ //新的距离小于之前的dis距离
                    dis[j] = length; //更新dis距离
                    pre_visited[j] = index; //将当前顶点设置为j的前驱节点下标
                }
            }
        }
        //输出结果
        System.out.println(Arrays.toString(isVisited));
        System.out.println(Arrays.toString(pre_visited));
        System.out.println(Arrays.toString(dis));
    }
}