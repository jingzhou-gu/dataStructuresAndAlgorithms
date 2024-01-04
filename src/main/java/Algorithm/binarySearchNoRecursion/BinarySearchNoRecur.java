package main.java.Algorithm.binarySearchNoRecursion;

/**
 * @Author: gjz
 * @Date: 2024/1/4 10:53
 * @Description: TODO 非递归的二分查找算法
 */

public class BinarySearchNoRecur {
    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 5, 7, 9, 13, 45, 53};
        System.out.println(binarySearchNoRecur(arr, 2));
        System.out.println(binarySearch(arr, 2, 0, arr.length - 1));
    }

    //非递归的二分查找算法
    public static int binarySearchNoRecur(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int middle = 0;
        while (left <= right) {
            middle = (left + right) / 2;
            if (arr[middle] > target) {
                right = middle - 1;
            } else if (arr[middle] < target) {
                left = middle + 1;
            } else {
                return middle;
            }
        }
        return -1;
    }

    //二分递归查找
    public static int binarySearch(int[] arr, int target, int left, int right) {
        if (left > right) {
            return -1;
        }
        int middle = (left + right) / 2;
        if (arr[middle] > target) {
            return binarySearch(arr, target, left, middle - 1);
        } else if (arr[middle] < target) {
            return binarySearch(arr, target, middle + 1, right);
        } else {
            return middle;
        }
    }
}
