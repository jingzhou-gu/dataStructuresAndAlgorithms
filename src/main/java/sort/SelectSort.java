package sort;

import java.util.Arrays;

/**
 * @Author: gjz
 * @Date: 2023/12/7 11:23
 * @Description: TODO 选择排序
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {3, 6, -2, 8, 32};
        selectSort(arr);
        System.out.println(Arrays.toString(arr));

        int[] a = new int[80000];
        for (int i = 0; i < 80000; i++) {
            a[i] = (int) (Math.random() * 8000000);
        }
        //测试80000数据排序，需要大概5s
        System.out.println(System.currentTimeMillis() / 1000);  //1701923111
        selectSort(a);
        System.out.println(System.currentTimeMillis() / 1000);  //1701923116
    }

    //选择排序
    public static void selectSort(int[] arr) {
        int minIndex = 0;  //存放每个大循环里的最小值的下标
        int minValue = 0;  //存放每个大循环里的最小值
        for (int i = 0; i < arr.length - 1; i++) {  //总共要进行 arr.length - 1 次大循环
            minIndex = i;
            minValue = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < minValue) {  //每个循环里找出比当前最小值小的数，替换到最小值，并记录最小值的索引
                    minValue = arr[j];
                    minIndex = j;
                }
            }
            if (minIndex != i) { //优化，如果minIndex==i,说明最小值就是刚开始的值，不用交换
                arr[minIndex] = arr[i];
                arr[i] = minValue;
            }
        }
    }
}
