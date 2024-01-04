package main.java.Algorithm.dynamic;

/**
 * @Author: gjz
 * @Date: 2024/1/4 16:18
 * @Description: TODO 动态规划算法解决背包问题
 */
public class KnapsackProblem {
    public static void main(String[] args) {
        int[] weight = {1, 4, 3}; //每个物品的重量
        int[] value = {1500, 3000, 2000}; //每个物品的价值
        int m = 4; //背包的重量
        int n = value.length; //物品的个数

        // v[i][j]表示在前i个物品中能够装入容量为j的背包中的最大价值
        int[][] v = new int[n + 1][m + 1];
        //用来存放选取物品的标志
        int[][] path = new int[n + 1][m + 1];

        //初始化第一行和第一列，0个物品和0容量的最大价值都为0，可以不处理，默认就是0
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0;
        }
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;
        }

        //i表示第i个物品，j表示背包容量
        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[0].length; j++) {
                if (weight[i - 1] > j) {  //如果当前第i个物品重量大于背包容量
                    v[i][j] = v[i - 1][j]; //最大价值取前i-1个物品在当前容量的最大价值
                } else {  //如果当前第i个物品重量小于等于背包容量
                    //取前i-1个物品在当前容量的最大价值 与 当前物品价值+前i-1个物品在剩余容量的最大价值 的较大值
                    //v[i][j] = Math.max(v[i - 1][j], value[i - 1] + v[i - 1][j - weight[i - 1]]);

                    //当前物品价值+前i-1个物品在剩余容量的最大价值 大于 前i-1个物品在当前容量的最大价值
                    if (v[i - 1][j] < value[i - 1] + v[i - 1][j - weight[i - 1]]) {
                        v[i][j] = value[i - 1] + v[i - 1][j - weight[i - 1]];
                        path[i][j] = 1; //表示第i个物品被选取
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }
//           i
//         j     j0     j1      j2      j3      j4
//           i0   0	    0	    0	    0	    0
//           i1   0	    1500	1500	1500	1500
//           i2   0	    1500	1500	1500	3000
//           i3   0	    1500	1500	2000	3500

        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[0].length; j++) {
                System.out.print(v[i][j] + "\t");
            }
            System.out.println();
        }
        for (int i = 0; i < path.length; i++) {
            for (int j = 0; j < path[0].length; j++) {
                System.out.print(path[i][j] + "\t");
            }
            System.out.println();
        }

        //倒着查找
        int i = path.length - 1; //行的最大下标
        int j = path[0].length - 1; //列的最大下标
        while (i > 0 && j > 0) {
            if (path[i][j] == 1) {
                System.out.println("第" + i + "个物品放入背包");
                j = j - weight[i - 1]; //放入第i个物品后，计算背包剩余容量，weight[i - 1]为第i个物品的容量
            }
            i--;
        }

    }
}
