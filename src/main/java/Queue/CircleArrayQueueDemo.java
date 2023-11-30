package Queue;

import java.util.Scanner;

/**
 * @ClassName：CircleArrayQueueDemo
 * @Author: gjz
 * @Date: 2023/11/27 21:45
 * @Description:
 */
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        CircleArrQueue circleArrQueue = new CircleArrQueue(4);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("a: 添加元素");
            System.out.println("g: 获取元素");
            System.out.println("s: 显示元素");
            System.out.println("h: 显示头部元素");
            System.out.println("e: 退出");
            key = scanner.next().charAt(0);
            switch (key) {
                case 'a':
                    System.out.println("请输入数字：");
                    int num = scanner.nextInt();
                    circleArrQueue.addQueue(num);
                    break;
                case 's':
                    circleArrQueue.showQueue();
                    break;
                case 'h':
                    try {
                        int head = circleArrQueue.headQueue();
                        System.out.println("头部元素为：" + head);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'g':
                    try {
                        int value = circleArrQueue.getQueue();
                        System.out.println("获取元素为：" + value);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }
}

class CircleArrQueue {  //环形数组队列
    private int front;  //指向队列的第一个元素位置
    private int rear;   //指向队列最后一个元素的后一个位置
    private int maxSize;
    private int[] arr;

    public CircleArrQueue(int arrMaxSize) {
        front = 0;
        rear = 0;
        maxSize = arrMaxSize;
        arr = new int[maxSize];
    }

    public boolean isFull() {  //队列差一个元素则认为满了，为了区分空队列和满队列
        return (rear + maxSize - front) % maxSize == maxSize - 1;
    }

    public boolean isEmpty() {
        return front == rear;
    }

    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("队列已经满了");
            return;
        }
        arr[rear] = n;
        rear = (rear + 1) % maxSize;
    }

    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列已经为空");
        }
        int n = arr[front];
        front = (front + 1) % maxSize;
        return n;
    }

    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列已经为空");
        }
        return arr[front];
    }

    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列已经为空");
            return;
        }
        for (int i = front; i < front + realSize(); i++) {
            System.out.println("arr[" + (i % maxSize) + "] = " + arr[i % maxSize]);
        }
    }

    public int realSize() {
        return (rear + maxSize - front) % maxSize;
    }
}