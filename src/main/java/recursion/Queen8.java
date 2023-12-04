package recursion;

/**
 * @Author: gjz
 * @Date: 2023/12/4 9:55
 * @Description: TODO 八皇后问题
 */
public class Queen8 {
    public static void main(String[] args) {
        int max = 8;  //设置皇后为8个
        int[] queen = new int[max];  //定义一个数组存放，i表示第i+1个皇后，数组的值表示在第几列 (queen[0]=2,表示在第三列)
        putPlace(queen, 0, max);  //运用递归遍历查找所有的方案
    }


    //queen表示放置位置的数组，n表示放置的第几个皇后，max表示总共有几个皇后
    public static void putPlace(int[] queen, int n, int max) {
        if (n == max) {  //n从0开始，当n==8时，表示所有皇后已经放好了
            show(queen); //展示数组的所有元素
            return;
        }
        for (int i = 0; i < max; i++) {  //表示从第1列位置开始，一直到第8列
            queen[n] = i;  // queen[0]=0 表示把第一个皇后放在第一列，随着i变化，放置列变化（不用考虑行，默认每个皇后一行）
            if (judge(queen, n)) {  //判断放置的当前位置与之前的所有皇后是否冲突
                putPlace(queen, n + 1, max); //与之前皇后不冲突，递归放置下一个皇后
            }
            //发生冲突进行循环，i+1继续，将皇后放置在后一列
        }
    }

    //判断放置的当前皇后与之前的所有皇后是否冲突，true表示不冲突
    public static boolean judge(int[] queen, int n) { //n表示当前放置的是第几个皇后
        for (int i = 0; i < n; i++) {  // i为之前放置的皇后
            //queen[i]==queen[n] 表示当前皇后与之前第i个皇后在同一列
            //Math.abs(n-i)==Math.abs(queen[n]-queen[i] 表示在同一斜线上 y1-y2=x1-x2
            if (queen[i] == queen[n] || Math.abs(n - i) == Math.abs(queen[n] - queen[i])) {
                return false;  //表示冲突
            }
        }
        return true;  //表示不冲突
    }

    //遍历数组
    public static void show(int[] queen) {
        for (int i = 0; i < queen.length; i++) {
            System.out.print(queen[i] + " ");
        }
        System.out.println();
    }
}
