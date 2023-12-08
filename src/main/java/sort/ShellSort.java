package sort;

import java.util.Arrays;

/**
 * @Author: gjz
 * @Date: 2023/12/8 10:20
 * @Description: TODO 希尔排序
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        shellSort(arr);
        System.out.println(Arrays.toString(arr));

        int[] a = new int[80000];
        for (int i = 0; i < 80000; i++) {
            a[i] = (int) (Math.random() * 8000000);
        }
        //希尔排序80000数据不到1s
        System.out.println(System.currentTimeMillis() / 1000);  //1702010066
        shellSort(a);
        System.out.println(System.currentTimeMillis() / 1000);  //1702010066
    }

    //希尔排序
    public static void shellSort(int[] arr) {
        int value = 0;
        for (int tag = arr.length / 2; tag > 0; tag /= 2) { //tag为分的组数
            for (int i = tag; i < arr.length; i++) { //从tag开始向后遍历
                value = arr[i]; //将每次要插入的数先记录下来
                boolean flag = true; //判断是否找到位置
                int j = 0;
                for (j = i - tag; j >= 0; j -= tag) { //对每一组数据从后往前遍历
                    if (arr[j] > value) {   //当前的arr[j]大于value，则没找到位置
                        arr[j + tag] = arr[j];  //将这个数付给后面的位置
                    } else {  //找到插入位置
                        arr[j + tag] = value;  //将value插入到指定位置
                        flag = false;   //代表找到位置了
                        break; //插入数据后就跳出循环
                    }

                }
                if (flag) { //代表没找到位置，应该放在该组内第一个位置
                    arr[j + tag] = value;
                }
            }
        }
    }
}
