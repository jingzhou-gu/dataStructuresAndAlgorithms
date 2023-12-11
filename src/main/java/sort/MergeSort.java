package sort;

import java.util.Arrays;

/**
 * @Author: gjz
 * @Date: 2023/12/11 10:34
 * @Description: TODO 归并排序
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {2, 4, 6, 7, 9, 1, 3, 5};
        int[] temp = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, temp);
        System.out.println(Arrays.toString(arr));

        int[] a = new int[80000];
        for (int i = 0; i < 80000; i++) {
            a[i] = (int) (Math.random() * 800000);
        }
        //测试80000数据不到1s   8000000数据需要2s  8千万数据需要22s
        int[] tt = new int[a.length];
        System.out.println(System.currentTimeMillis() / 1000);
        mergeSort(a, 0, a.length - 1, tt);
        System.out.println(System.currentTimeMillis() / 1000);
    }

    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        int mid = (left + right) / 2;
        if (left < right) {
            mergeSort(arr, left, mid, temp);  //拆分的过程
            mergeSort(arr, mid + 1, right, temp);
            merge(arr, left, right, mid, temp);  //合并
        }
    }

    //合并的方法
    public static void merge(int[] arr, int left, int right, int mid, int[] temp) {
        int i = left; //第一个从left->mid, 第二个从mid+1 -> right
        int j = mid + 1;
        int t = 0;
        while (i <= mid && j <= right) { //两个合并的过程
            if (arr[i] <= arr[j]) {   //将小的值付给temp数组
                temp[t] = arr[i];
                t++;
                i++;
            } else {
                temp[t] = arr[j];
                t++;
                j++;
            }
        }
        while (i <= mid) {   //说明j全部赋值完了，将i剩余的赋给temp
            temp[t] = arr[i];
            i++;
            t++;
        }
        while (j <= right) { //说明i全部赋值完了，将j剩余的赋给temp
            temp[t] = arr[j];
            j++;
            t++;
        }

        int tempLeft = left; //将temp里从left到right下标的赋给arr数组
        t = 0;
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            tempLeft++;
            t++;
        }
    }
}
