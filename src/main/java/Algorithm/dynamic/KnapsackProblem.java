package main.java.Algorithm.dynamic;

/**
 * @Author: gjz
 * @Date: 2024/1/4 16:18
 * @Description: TODO 动态规划算法解决背包问题
 */
public class KnapsackProblem {
    public static void main(String[] args) {
        int[] weight = {1,4,3}; //每个物品的重量
        int[] value = {1500,3000,2000}; //每个物品的价值
        int m = 4; //背包的重量
        int n = value.length; //物品的个数

        // v[i][j]表示在前i个物品中能够装入容量为j的背包中的最大价值
        int[][] v = new int[n+1][m+1];

        //初始化第一行和第一列，0个物品和0容量的最大价值都为0，可以不处理，默认就是0
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0;
        }
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;
        }

        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[0].length; j++) {
                if(weight[i-1] > j){
                    v[i][j] = v[i-1][j];
                }else{
                    v[i][j] = Math.max(v[i-1][j],value[i-1] + v[i-1][j-weight[i-1]]);
                }
            }
        }


    }
}
