package sort;

import java.util.Arrays;

/**
 * @ClassName：QuickSort
 * @Author: gjz
 * @Date: 2023/12/10 0:54
 * @Description: TODO 快速排序
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {2, 4, 16, 18, 4, 16, 20, 34, 17, 19, 42};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));

        int[] a = new int[80000];
        for (int i = 0; i < 80000; i++) {
            a[i] = (int) (Math.random() * 8000000);
        }
        //测试80000数据排序不到1s   8000000数据只需1s
        System.out.println(System.currentTimeMillis() / 1000); //1702193787
        quickSort(a, 0, a.length - 1);
        System.out.println(System.currentTimeMillis() / 1000); //1702193787
    }

    //快速排序
    public static void quickSort(int[] arr, int left, int right) {
        if (left >= right) { //当left和right相等时，说明只有1个元素，不用再递归排序了
            return;
        }
        int l = left;  //l为从左往右移动的指针
        int r = right; //r为从右往左移动的指针
        int temp = 0;
        while (l < r) {  //说明两个指针还未相遇   ！！！第一个元素arr[left]作为每次的中轴数
            while (l < r && arr[r] >= arr[left]) {  //右指针找到一个比中轴数小的数退出循环
                r--;
            }
            while (l < r && arr[l] <= arr[left]) {  //左指针找到一个比中轴数大的数退出循环
                l++;
            }

            if (l == r) {  //如果此时l==r,说明所有数都到位，l和r指的数是中轴数该放的位置且该位置的数是小于等于arr[left]的
                temp = arr[r];   //将中轴数和该位置的数交换
                arr[r] = arr[left];
                arr[left] = temp;
            } else {  //l和r未重合，仅需交换arr[l]和arr[r]即可
                temp = arr[r];
                arr[r] = arr[l];
                arr[l] = temp;
            }
        }
        quickSort(arr, left, r - 1);  //递归调用左循环，右边界为 r-1
        quickSort(arr, l + 1, right); //递归调用右循环，左边界为 l+1
    }

//    public static void quickSort(int[] arr,int left,int right){
//        int l = left;
//        int r = right;
//        int middle = arr[(l+r)/2];
//        int temp = 0;
//        while(l < r){
//            while(arr[l]<middle){
//                l++;
//            }
//            while(arr[r]>middle){
//                r--;
//            }
//            if(l>=r){
//                break;
//            }
//            temp = arr[l];
//            arr[l] = arr[r];
//            arr[r] = temp;
//
//            if(arr[l]==middle){
//                r--;
//            }
//            if(arr[r]==middle){
//                l++;
//            }
//        }
//        if(l==r){
//            l++;
//            r--;
//        }
//        if(left<r){
//            quickSort(arr,left,r);
//        }
//        if(right>l){
//            quickSort(arr,l,right);
//        }
//    }
}
