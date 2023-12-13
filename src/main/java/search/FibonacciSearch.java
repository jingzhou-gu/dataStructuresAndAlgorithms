package search;

import java.util.Arrays;

/**
 * @Author: gjz
 * @Date: 2023/12/12 14:15
 * @Description: TODO 斐波那契查找算法
 */
public class FibonacciSearch {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(fib(10)));

        int[] arr = {2, 4, 13, 15, 21, 36, 355};
        System.out.println(fibSearch(arr, 36, fib(10)));
    }

    //获得一个斐波那契数列的数组，数组长度为斐波那契数列个数
    public static int[] fib(int cnt) {
        int[] f = new int[cnt];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < cnt; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    //斐波那契数列查找算法
    public static int fibSearch(int[] arr, int value, int[] fib) {
        int k = 0;
        while (arr.length > fib[k]) { //寻找和原数组最接近且大于等于原数组长度的斐波那契数
            k++;
        }
        //创建一个数组长度为斐波那契数的数组，将原数组的数据拷贝到新数组，不足的用原数组最后一位填补
        int[] temp = Arrays.copyOf(arr, fib[k]);
        for (int i = arr.length; i < temp.length; i++) {
            temp[i] = arr[arr.length - 1];
        }

        int left = 0;
        int right = arr.length - 1;
        int mid = 0;
        while (left <= right) {
            mid = left + fib[k - 1] - 1;
            if (temp[mid] > value) {  //向左遍历
                right = mid - 1;
                k--;  //左边有f[k-1]个数,原来fib[k] = fib[k-1] + fib[k-2], fib[k-1] = fib[k-2] + fib[k-3]
            } else if (temp[mid] < value) {
                left = mid + 1;  //向右遍历
                k -= 2;  //右边有f[k-2]个数  f[k-2] = fib[k-3] + fib[k-4]
            } else {
                if (mid <= right) {
                    return mid;
                } else {
                    return right;
                }
            }
        }
        return -1;
    }
}
