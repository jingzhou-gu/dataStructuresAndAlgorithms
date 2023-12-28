package main.java.graph;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Author: gjz
 * @Date: 2023/12/28 14:08
 * @Description: TODO
 */
public class Graph {
    public ArrayList<String> vertexList; //存储结点集合
    public int[][] edges; //存储图对应的邻结矩阵
    public int numOfEdges; //表示边的数目
    public boolean[] isVisited; //表示该下标i的节点是否访问过

    public Graph(int n){ //n为结点个数
        vertexList = new ArrayList<>(n);
        edges = new int[n][n];
        numOfEdges = 0;
        isVisited = new boolean[n];
    }

    public static void main(String[] args){
        int n = 5;
        String[] vertexList = {"A","B","C","D","E"};
        Graph graph = new Graph(n);
        for (String vertex : vertexList) {
            graph.insertVertex(vertex);
        }
        graph.insertEdge(0,1,1); // A-B
        graph.insertEdge(0,2,1); // A-C
        graph.insertEdge(1,2,1); // B-C
        graph.insertEdge(1,3,1); // B-D
        graph.insertEdge(1,4,1); // B-E

        graph.showGraph();
    }

    //深度优先算法遍历
    public void dfs(boolean[] isVisited,int i){
        System.out.println(vertexList.get(i));

        int firstNeighbor = getFirstNeighbor(i);

    }

    //查询当前结点的第一个邻接结点下标
    public int getFirstNeighbor(int i){ //i为当前结点下标
        for (int j = 0; j < vertexList.size(); j++) { //从下标0开始遍历
            if(edges[i][j]>0){ //如果权值大于0，说明是连通的
                return j; //返回第一个相邻接点下标,并返回
            }
        }
        return -1; //未找到邻接点，返回-1
    }

    //查询上一个节点的下一个邻接结点下标
    public int getNextNeighbor(int i,int j){ // i:上一个邻接点的下标 , j:上一个结点的当前邻接点下标
        for (int k = j+1; k < vertexList.size(); k++) { //从当前临结点的下一个下标 j+1 开始查找
            if(edges[i][k]>0){ //如果权值大于0，说明是连通的
                return k; //返回下一个邻结点下标
            }
        }
        return -1; //未找到下一个邻接点下标，返回-1
    }

    //插入结点
    public void insertVertex(String vertex){
        vertexList.add(vertex);
    }

    //添加边    v1,v2表示点的下标
    public void insertEdge(int v1,int v2,int weight){
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

    //返回结点个数
    public int getNumOfVertex(){
        return vertexList.size();
    }

    //得到边的个数
    public int getNumOfEdges(){
        return numOfEdges;
    }

    //返回结点i(下标)对应的数据
    public String getValueByIndex(int i){
        return vertexList.get(i);
    }

    //获取v1,v2的权值
    public int getWeight(int v1,int v2){
        return edges[v1][v2];
    }

    //显示图对应的矩阵
    public void showGraph(){
        for (int[] edge : edges) {
            System.out.println(Arrays.toString(edge));
        }
    }
}
