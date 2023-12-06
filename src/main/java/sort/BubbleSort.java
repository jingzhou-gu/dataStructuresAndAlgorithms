package sort;

import java.util.Arrays;

/**
 * @ClassName：BubbleSort
 * @Author: gjz
 * @Date: 2023/12/6 23:49
 * @Description: TODO 冒泡排序
 */
public class BubbleSort {
    public static void main(String[] args) {
//        int[] arr = {3,9,-1,10,-2};
        int[] arr = {-1, 3, 9, 13, 10};
        int[] arr2 = {3, 9, -1, 10, -2};

        int[] a = new int[80000];
        for (int i = 0; i < 80000; i++) {
            a[i] = (int) (Math.random() * 8000000);
        }
        //测试80000个随机数，冒泡排序需要10s左右
        System.out.println(System.currentTimeMillis() / 1000); //1701880384
        bubbleSort(a);
        System.out.println(System.currentTimeMillis() / 1000); //1701880394

        bubbleSort(arr);
        System.out.println("---------------------");
        Arrays.sort(arr2);
        System.out.println(Arrays.toString(arr2));

    }

    //冒泡排序
    public static void bubbleSort(int[] arr) {
        int temp = 0;
        for (int i = 0; i < arr.length - 1; i++) {  //第i次大循环,每次大循环将一个大数放置在末尾
            boolean flag = true;   //用来优化排序，当一次大循环没有进行过一次交换，说明已经有序了，可以跳出循环了
            for (int j = 0; j < arr.length - 1 - i; j++) { //每次访问相邻的两个元素
                if (arr[j] > arr[j + 1]) { //将相邻的两个中的大数放在后面
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = false;  //进行过交换，则置为false
                }
            }
//            System.out.println("第"+(i+1)+"趟后的结果：");
//            System.out.println(Arrays.toString(arr));
            if (flag) {  //当为true时，说明一次大循环没有进行过交换
                break; //跳出循环
            }
        }
    }

}
