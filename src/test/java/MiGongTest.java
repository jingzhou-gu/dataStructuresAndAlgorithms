/**
 * @ClassName：MiGongTest
 * @Author: gjz
 * @Date: 2023/12/4 21:15
 * @Description:
 */
public class MiGongTest {
    public static void main(String[] args) {
        int[][] miGong = new int[8][8];
        for (int i = 0; i < 8; i++) {
            miGong[0][i] = 1;
            miGong[7][i] = 1;
        }
        for (int i = 0; i < 8; i++) {
            miGong[i][0] = 1;
            miGong[i][7] = 1;
        }
        miGong[2][1] = 1;
        miGong[2][4] = 1;
        miGong[5][2] = 1;
        miGong[5][3] = 1;
        miGong[5][4] = 1;
        miGong[5][5] = 1;
        miGong[5][6] = 1;

        setWay(miGong, 1, 1);

        for (int[] items : miGong) {
            for (int item : items) {
                System.out.print(item + " ");
            }
            System.out.println();
        }
    }

    //从（1,1）走到（7,7）  1为墙，2为通路，3为死路，0为未走过   下右上左
    public static boolean setWay(int[][] miGong, int i, int j) {
        if (miGong[6][6] == 2) {
            return true;
        }
        if (miGong[i][j] == 0) {
            miGong[i][j] = 2;
            if (setWay(miGong, i + 1, j)) {
                return true;
            } else if (setWay(miGong, i, j + 1)) {
                return true;
            } else if (setWay(miGong, i - 1, j)) {
                return true;
            } else if (setWay(miGong, i, j - 1)) {
                return true;
            } else {
                miGong[i][j] = 3;
                return false;
            }
        } else {
            return false;
        }


    }
}
