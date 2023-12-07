package sort;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @Author: gjz
 * @Date: 2023/12/7 12:33
 * @Description: TODO 插入排序
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {-2, 4, 2, 23, -5, 6, 3};
        insertSort(arr);
        System.out.println(Arrays.toString(arr));

        int[] a = new int[80000];
        for (int i = 0; i < 80000; i++) {
            a[i] = (int) (Math.random() * 8000000);
        }
        //测试80000数据排序，用时2s
        System.out.println(System.currentTimeMillis() / 1000); //1701943265
        insertSort(a);
        System.out.println(System.currentTimeMillis() / 1000); //1701943267

    }

    //插入排序
    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) { //需要对从下标为1到数组最后一个遍历一遍
            int value = arr[i];   //当前需要插入的元素value
            for (int j = i - 1; j >= 0; j--) { //对value之前的所有元素倒序遍历
                if (arr[j] > value) {  //当value小于arr[j],说明还没找到位置
                    arr[j + 1] = arr[j]; //将当前的arr[j]往后复制一位
                } else {  //当value大于等于arr[j],说明找到位置
                    arr[j + 1] = value;  //将value放在 arr[j+1]位置
                    break;  //跳出此次value元素的查找循环
                }
            }
            if (value < arr[0]) {  //如果value比arr[0]还小,说明value应放在第一个位置
                arr[0] = value;
            }
        }
    }
}
