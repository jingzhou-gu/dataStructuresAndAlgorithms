package search;

import java.util.ArrayList;

/**
 * @Author: gjz
 * @Date: 2023/12/11 18:17
 * @Description: TODO 二分查找
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {-3, 2, 5, 5, 13, 14, 14, 46};
        System.out.println(binarySearch(arr, 0, arr.length - 1, 5));

        System.out.println(binarySearch2(arr, 0, arr.length - 1, 5));
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

    //用二分查找返回  所有等于value的值的下标
    public static ArrayList<Integer> binarySearch2(int[] arr, int left, int right, int value) {
        if (left > right) {
            return new ArrayList<Integer>();
        }
        int mid = (left + right) / 2;
        if (arr[mid] > value) {
            return binarySearch2(arr, left, mid - 1, value);
        } else if (arr[mid] < value) {
            return binarySearch2(arr, mid + 1, right, value);
        } else {
            ArrayList<Integer> list = new ArrayList<>(); //创建一个集合用于存储所有满足条件的下标
            int l = mid;  //从当前满足条件的mid下标向左遍历，找到等于value的所有下标
            while (l >= 0 && arr[l] == value) {
                l--;
                if (arr[l] == value) {
                    list.add(l);
                }
            }
            list.add(mid); //将满足条件的mid下标加入集合
            int r = mid;  //从当前满足条件的mid下标向右遍历，找到等于value的所有下标
            while (r <= right && arr[r] == value) {
                r++;
                if (arr[r] == value) {
                    list.add(r);
                }
            }
            return list;
        }
    }
}
