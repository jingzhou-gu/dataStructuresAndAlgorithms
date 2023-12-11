package search;

/**
 * @ClassName：InsertValueSearch
 * @Author: gjz
 * @Date: 2023/12/11 22:58
 * @Description: TODO 插值查找，适用于数据量大且分布比较均匀的情况，否则不如二分查找
 */
public class InsertValueSearch {
    public static void main(String[] args) {
        int[] a = {1, 2, 3, 3, 5, 8, 12};
        System.out.println(insertValueSearch(a, 0, a.length - 1, 8));
    }

    //插值查找
    public static int insertValueSearch(int[] arr, int left, int right, int value) {
        System.out.println("dfdf");
        // value<arr[left]和 value>arr[right] 必须判断，否则mid可能导致数组下标越界
        if (left > right || value < arr[left] || value > arr[right]) {
            return -1;
        }
        //防止下面mid出现 除0 错误
        if (arr[left] == arr[right]) { //如果arr[left]==arr[right]，则value就等于arr[left]
            return 0;
        }
        //插值查找的mid计算公式
        int mid = left + (right - left) * (value - arr[left]) / (arr[right] - arr[left]);

        if (arr[mid] > value) {  //表示要查找的数比中间值小，往左递归
            return insertValueSearch(arr, left, mid - 1, value);
        } else if (arr[mid] < value) {  //表示要查找的数比中间值大，往右递归
            return insertValueSearch(arr, mid + 1, right, value);
        } else {
            return mid;  //表示arr[mid]==value，找到该数，返回下标
        }
    }
}
