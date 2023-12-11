package search;

/**
 * @Author: gjz
 * @Date: 2023/12/11 18:17
 * @Description: TODO 二分查找
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {-3, 2, 5, 5, 13, 14, 14, 46};
        System.out.println(binarySearch(arr, 0, arr.length - 1, 5));
    }

    //二分查找
    public static int binarySearch(int[] arr, int left, int right, int value) {
        if (left > right) {  //表示未查找到
            return -1;
        }

        int mid = (left + right) / 2;
        if (arr[mid] > value) {  //表示要查找的数比中间值小，往左递归
            return binarySearch(arr, left, mid - 1, value);
        } else if (arr[mid] < value) {  //表示要查找的数比中间值大，往右递归
            return binarySearch(arr, mid + 1, right, value);
        } else {
            return mid;  //表示arr[mid]==value，找到该数，返回下标
        }
    }
}
