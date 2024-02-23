package main.java.Algorithm.horseChessboard;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * @Author: gjz
 * @Date: 2024/2/22 10:24
 * @Description: TODO 马踏棋盘算法
 */
public class HorseChessboard {
    private static int X; //棋盘的列数
    private static int Y; //棋盘的行数
    private static boolean[] visited; //标记棋盘的各个位置是否被访问过
    private static boolean finished; //如果为true，表示成功

    public static void main(String[] args) {
        X = 6;
        Y = 6;
        int row = 0;
        int column = 0;
        int[][] chessboard = new int[Y][X];
        visited = new boolean[X * Y];
        long start = System.currentTimeMillis();
        traversalChessboard(chessboard, row, column, 1);
        long end = System.currentTimeMillis();
        System.out.println("共耗时：" + (end - start) + "毫秒");

        for (int[] rows : chessboard) {
            for (int step : rows) {
                System.out.print(step + "\t");
            }
            System.out.println();
        }
    }

    public static void traversalChessboard(int[][] chessboard, int row, int column, int step) {
        chessboard[row][column] = step;
        visited[X * row + column] = true; //标记该位置为已访问

        //获取当前位置可以走的下一个位置的集合
        ArrayList<Point> ps = next(new Point(column, row));
        sort(ps); //对ps进行排序，对ps内所有的point对象的下一步的位置的数目，进行非递减排序
        while (!ps.isEmpty()) {
            Point p = ps.remove(0); //取出下一个可以走的点
            if (!visited[X * p.y + p.x]) { //判断该点是否被访问过
                traversalChessboard(chessboard, p.y, p.x, step + 1);
            }
        }

        //判断马儿是否完成了任务，使用step和应该走的步数比较
        //如果没有达到数量，则表示没有完成任务，将整个棋盘置0
        //说明：step < X * Y 的情况有两种，1.棋盘到目前位置仍然没有走完 2.棋盘处于一个回溯过程
        if (step < X * Y && !finished) {
            chessboard[row][column] = 0;
            visited[X * row + column] = false;
        } else {
            finished = true;
        }

    }

    //根据当前这一步的所有下一步的选择位置，对集合进行非递减排序
    public static void sort(ArrayList<Point> ps) {
        ps.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return next(o1).size() - next(o2).size();
            }
        });
    }

    //根据当前位置，计算马儿还能走哪些位置，并放入到一个集合中，最多有8个位置
    public static ArrayList<Point> next(Point curPoint) {
        ArrayList<Point> ps = new ArrayList<>();
        Point p1 = new Point();
        //判断从当前点出发的8个点是否能够走，能走就加入到集合
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }

        return ps;
    }

}

class Point {
    public int x;
    public int y;

    public Point() {

    }

    public Point(int column, int row) {
        x = column;
        y = row;
    }

    public Point(Point p) {
        x = p.x;
        y = p.y;
    }
}
