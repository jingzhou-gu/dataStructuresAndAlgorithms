package main.java.Algorithm.kmp;

/**
 * @Author: gjz
 * @Date: 2024/1/5 14:21
 * @Description: TODO 暴力匹配算法
 */
public class ViolenceMatch {
    public static void main(String[] args) {
        String s1 = "asabcedabcddfe";
        String s2 = "abcd";
        System.out.println(violenceMatch(s1,s2));
    }

    //暴力匹配算法
    public static int violenceMatch(String s1, String s2) {
        int i = 0; //s1的指针
        int j = 0; //s2的指针
        while (i < s1.length()) { //s1的指针遍历完，退出循环
            if (s1.charAt(i) == s2.charAt(j)) { //匹配到相同字符时，s1和s2指针都向后移
                i++;
                j++;
            } else { //s1和s2字符不同
                i = i - j + 1; //回退到第一个相同值的后一位
                j = 0; //回退到首位
            }
            if (j == s2.length()) { //找到与s2完全匹配的字符串
                return i - j; //返回第一个相同值的下标
            }
        }
        return -1; //没有完全匹配的，返回-1
    }
}
