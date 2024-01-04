package main.java.Algorithm.divideAndConquer;

/**
 * @Author: gjz
 * @Date: 2024/1/4 14:18
 * @Description: TODO 分治算法
 */
public class HanoiTower {
    public static void main(String[] args) {
        hanoiTower(3,'A','B','C');
    }

    //汉诺塔问题
    public static void hanoiTower(int num,char a,char b,char c){
        if (num == 1) { //如果只有一个盘子，直接从a到c
            System.out.println("第1个盘子：" + a + "->" + c);
        } else {
            //如果有盘子数大于等于2,我们总是可以看做是两个盘  1.最下面的一个盘 2.最下面盘的上面的所有盘
            //先把最下面盘的上面的所有盘从a到b,移动过程借助c
            hanoiTower(num - 1, a, c, b);
            //把最下面盘从a到c
            System.out.println("第" + num + "个盘子：" + a + "->" + c);
            //把中间串的所有盘从b到c,移动过程借助a
            hanoiTower(num - 1, b, a, c);
        }
    }
}
