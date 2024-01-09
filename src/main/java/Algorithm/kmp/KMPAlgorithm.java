package main.java.Algorithm.kmp;

import java.util.Arrays;

/**
 * @Author: gjz
 * @Date: 2024/1/9 10:14
 * @Description: TODO KMP算法求解包含子串
 */
public class KMPAlgorithm {
    public static void main(String[] args) {
        String str1 = "ABACABAD";
        String str2 = "ABAD";
        int[] next = kmpNext(str1);

        System.out.println(Arrays.toString(next));
        System.out.println(kmpSearch(str1, str2, next));
    }

    //kmp算法,str1为主串,str2为子串,next为部分匹配表
    public static int kmpSearch(String str1, String str2, int[] next) {
        int i = 0; //主串的下标索引
        int j = 0; //子串的下标索引
        while (i < str1.length()) {
            if (str1.charAt(i) == str2.charAt(j)) {
                i++;
                j++;
            } else if (j > 0) { //进行子串的部分匹配
                j = next[j - 1]; //从子串相同部分后面接着比较
            } else { //当j为0时,对i进行移动
                i++;
            }
            if (j == str2.length()) {
                return i - j;
            }
        }
        return -1;
    }

    //获取一个字符串(子串)的部分匹配值表
    public static int[] kmpNext(String str) {
        int[] next = new int[str.length()]; //部分匹配值表
        next[0] = 0;
        // i为部分匹配值表的下标
        for (int i = 1, j = 0; i < next.length; i++) {
            while (j > 0 && str.charAt(i) != str.charAt(j)) {
                j = next[j - 1];
            }
            if (str.charAt(i) == str.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
