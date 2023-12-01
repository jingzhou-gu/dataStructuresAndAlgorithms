package stack;

import java.util.Scanner;

/**
 * @ClassName：ArrayStack
 * @Author: gjz
 * @Date: 2023/12/1 0:11
 * @Description:
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(5);
        Scanner scanner = new Scanner(System.in);
        String key = "";
        boolean loop = true;
        while (loop){
            System.out.println("push:表示添加元素");
            System.out.println("pop:表示取出元素");
            System.out.println("show:表示展示元素");
            System.out.println("exit:表示退出");
            System.out.println("请输入：");
            key= scanner.next();
            switch (key){
                case "push":
                    System.out.println("请输入元素");
                    int value = scanner.nextInt();
                    arrayStack.push(value);
                    break;
                case "pop":
                    try {
                        int pop = arrayStack.pop();
                        System.out.println("出栈数据是："+pop);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "show":
                    arrayStack.show();
                    break;
                case "exit":
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

class ArrayStack{  //用数组实现栈
    private int maxSize;
    private int[] stack;
    private int top=-1;   //top指向栈顶元素位置

    public ArrayStack(int num){
        maxSize = num;
        stack =new int[maxSize];
    }

    public boolean isFull(){
        return top==maxSize-1;
    }

    public boolean isEmpty(){
        return top==-1;
    }

    //入栈
    public void push(int value){
        if(isFull()){
            System.out.println("栈已满，无法插入");
            return;
        }
        top++;
        stack[top]=value;
    }

    //出栈
    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("栈为空，无数据异常");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //遍历栈,从栈顶开始遍历
    public void show(){
        if(isEmpty()){
            System.out.println("栈为空，无数据");
            return;
        }
        //从栈顶开始遍历
        for (int i = top; i >=0 ; i--) {
            System.out.println("stack["+i+"] = " + stack[i]);
        }
    }
}