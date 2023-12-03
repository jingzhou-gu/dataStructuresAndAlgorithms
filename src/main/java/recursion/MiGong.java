package recursion;

/**
 * @ClassName：MiGong
 * @Author: gjz
 * @Date: 2023/12/3 20:41
 * @Description: TODO 走迷宫的递归解法
 */
public class MiGong {
    public static void main(String[] args) {
        int[][] map = new int[8][7];

        for (int i = 0; i < map.length; i++) { //将地图的左右两边挡住
            map[i][0] = 1;
            map[i][6] = 1;
        }
        for (int i = 0; i < map[0].length; i++) { //将地图的上下两边挡住
            map[0][i] = 1;
            map[7][i] = 1;
        }
        map[3][1] = 1; //设置一些障碍
        map[3][2] = 1;
        map[5][4] = 1;
        map[6][4] = 1;

        for (int[] rows : map) {  //打印原始地图
            for (int row : rows) {
                System.out.print(row + "\t");
            }
            System.out.println();
        }
        System.out.println("-------------------");

        setWay(map, 1, 1); //探索路径

        for (int[] rows : map) { //打印探索后的地图，2表示通路
            for (int row : rows) {
                System.out.print(row + "\t");
            }
            System.out.println();
        }

    }

    //map地图为8行7列，从点[1][1]走到点[6][5]， i j 表示从哪个点出发
    //0表示未走过，1表示墙，2表示通路，3表示已经走过，但是是死路
    //探索顺序按照  下->右->上->左 寻找，通路则返回true，否则返回false
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) { //表示找到终点
            return true;
        } else {
            if (map[i][j] == 0) {  //表示该点未走过
                map[i][j] = 2;  //先假定该点为通路
                if (setWay(map, i + 1, j)) { //向下走
                    return true;
                } else if (setWay(map, i, j + 1)) {  //向右走
                    return true;
                } else if (setWay(map, i - 1, j)) {  //向上走
                    return true;
                } else if (setWay(map, i, j - 1)) {  //向左走
                    return true;
                } else {
                    map[i][j] = 3;  //都走不通，表示死路
                    return false;
                }
            } else {  //不为0，则为1或2或3，都返回false
                return false;
            }
        }
    }
}
