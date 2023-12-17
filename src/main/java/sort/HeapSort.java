package sort;

import java.util.Arrays;

/**
 * @ClassName：HeapSort
 * @Author: gjz
 * @Date: 2023/12/17 15:17
 * @Description: TODO 堆排序
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {4, 10, 8, 6, 9};
        adjustHeap(arr, 0, 5);
        System.out.println(Arrays.toString(arr));

        heapSort(arr);
        System.out.println(Arrays.toString(arr));

        int[] a = new int[80000000];
        //测试80000数据不到1s   8000000数据需要2s  8千万数据需要22s
        for (int i = 0; i < 80000000; i++) {
            a[i] = (int) (Math.random() * 80000000);
        }
        System.out.println(System.currentTimeMillis() / 1000);
        heapSort(a);
        System.out.println(System.currentTimeMillis() / 1000);

    }

    //堆排序
    public static void heapSort(int[] arr) {
        int temp = 0;
        //1.将一个无序序列构建成一个堆，升序选大顶堆，降序选小顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
        //2.将堆顶元素与末尾元素交换，将最大元素沉到数组末端
        //3.重新调整结构，使其满足堆定义，再交换堆顶元素与末尾元素，反复执行 调整+交换 步骤，直到整个序列有序
        for (int i = arr.length - 1; i > 0; i--) {
            temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, i);
        }
    }

    //调整成局部以i为顶点的大顶堆
    //完成以i为对应的非叶子节点的树调整成大顶堆
    //i表示非叶子节点在数组中的索引，length表示对多少个元素继续调整
    public static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i];  //取出当前元素的值，保存在temp中
        //j = i*2+1  即 j 为 i 的左子节点
        for (int j = i * 2 + 1; j < length; j = j * 2 + 1) {  //
            if (j + 1 < length && arr[j] < arr[j + 1]) { //找出左右子节点中大的那个节点
                j++;
            }
            if (temp < arr[j]) { //如果子节点大于父节点
                arr[i] = arr[j]; //将较大的数赋给当前节点
                i = j; // i 指向 j，继续循环
            } else {
                break;
            }
        }
        arr[i] = temp;  //将temp的值放在调整后的位置上
    }
}
