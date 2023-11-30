package stack;

/**
 * @ClassName：ArrayStack
 * @Author: gjz
 * @Date: 2023/12/1 0:11
 * @Description:
 */
public class ArrayStackDemo {
    public static void main(String[] args) {

    }
}

class ArrayStack{
    private int maxSize;
    private int[] stack;
    private int top=-1;

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

    }

    //出栈
    public void pop(){

    }

    //遍历栈,从栈顶开始遍历
    public void show(){

    }
}