package sort;


import java.util.Arrays;

/**
 * @Author: gjz
 * @Date: 2023/12/11 16:02
 * @Description: TODO 基数排序
 */
public class RadixSort {
    public static void main(String[] args) {
        int[] arr = {3, 56, 2, 34, 5, 13, 5, 8, 56, 28};
        radixSort(arr);
        System.out.println(Arrays.toString(arr));

        int[] a = new int[80000];
        for (int i = 0; i < 80000; i++) {
            a[i] = (int) (Math.random() * 8000000);
        }
        //测试80000数据不到1s   8000000数据需要1s  8千万数据需要8s
        System.out.println(System.currentTimeMillis() / 1000);
        radixSort(a);
        System.out.println(System.currentTimeMillis() / 1000);
    }

    //基数排序
    public static void radixSort(int[] arr) {
        int maxValue = arr[0];
        for (int i = 0; i < arr.length; i++) { //找出里面最大的数，确定位数
            if (arr[i] > maxValue) {
                maxValue = arr[i];
            }
        }
        int maxLength = (maxValue + "").length(); //确定最大的数的位数，决定循环次数

        //定义一个二维数组，表示10个桶，每个桶就是一个一维数组
        int[][] bucket = new int[10][arr.length]; //定义10个桶，每个桶最多可以存放arr.length个数
        int[] bucketOfCount = new int[10]; //每个里面存放当前桶的数据个数 buckOfCount[0]=3表示0号桶有三个数

        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {  //i决定总共要进行几次入桶排序  n决定取哪一位(个、十、百)
            for (int j = 0; j < arr.length; j++) {  //遍历数组的每一位数
                int index = arr[j] / n % 10;  //index为应放在的对应桶的号码
                bucket[index][bucketOfCount[index]] = arr[j]; //将这个数放在对应桶的对应位置上
                bucketOfCount[index]++; //对应桶的记录个数+1
            }

            //将每个桶的数据按桶顺序再放回arr数组
            int cnt = 0; //数组arr的索引
            for (int j = 0; j < bucketOfCount.length; j++) { //遍历每个桶
                if (bucketOfCount[j] != 0) {  //如果桶内个数为0，则跳过这个桶
                    for (int k = 0; k < bucketOfCount[j]; k++) { //遍历桶内的每个元素，放回arr数组
                        arr[cnt] = bucket[j][k];
                        cnt++;
                    }
                    bucketOfCount[j] = 0;  //  ！！！放完数据后将对应桶的数据个数置为0，下次循环重新计数
                }
            }
        }
    }
}
