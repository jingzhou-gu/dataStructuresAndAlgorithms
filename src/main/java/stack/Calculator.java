package stack;

/**
 * @Author: gjz
 * @Date: 2023/12/1 16:08
 * @Description: TODO 用栈实现计算器
 */
public class Calculator {
    public static void main(String[] args) {
        ArrayStack2 numStack = new ArrayStack2(10); //用来存放数字的栈
        ArrayStack2 operStack = new ArrayStack2(10); //用来存放字符的栈
        String expression = "2+13*2-90";
        int index = 0; //用于expression指针的移动
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' '; //将每次扫描的结果存到ch
        String keepNum = "";  //用于拼接多位数
        while (true) {
            ch = expression.substring(index, index + 1).charAt(0);  //从表达式中取出一个字符
            if (operStack.isOper(ch)) {  //判断是运算符
                if (!operStack.isEmpty()) { //判断运算符栈不为空
                    //如果符号栈有操作符，就进行比较，如果当前操作符优先级小于等于栈中的操作符，就需要从栈中pop两个数
                    //在符号栈中pop一个符号，进行运算，将得到的结果插入数栈，然后将当前操作符插入符号栈
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) { //当前操作符优先级小于等于栈中的操作符
                        num1 = numStack.pop();  //从栈中pop两个数
                        num2 = numStack.pop();
                        oper = operStack.pop();  //符号栈中pop一个符号
                        res = operStack.cal(num1, num2, oper); //进行运算得到结果
                        numStack.push(res);  //将得到的结果插入数栈
                        operStack.push(ch); //将当前操作符插入符号栈
                    } else {  //当前操作符优先级大于栈中的操作符，直接入栈
                        operStack.push(ch);
                    }
                } else { //判断运算符栈为空，直接入栈
                    operStack.push(ch);
                }
            } else {   //判断不是运算符
                //1.当处理多位数时，不能发现一个数就立即入栈，因为可能是多位数
                //2.在处理时，要向expression的表达式的index后再看一位，如果是数就进行扫描，是符号才入栈
                //3.因此需要定一个变量 字符串，用于拼接
                keepNum = keepNum + ch; //拼接当前字符

                if (index == expression.length() - 1) { //先判断当前索引是不是最后一位
                    numStack.push(Integer.parseInt(keepNum));  //是最后一位直接入栈
                } else { //不是最后一位，查看下一位是不是符号
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) { //查看下一位是不是符号
                        numStack.push(Integer.parseInt(keepNum)); //下一位是符号，直接将数字入栈
                        keepNum = "";  //入栈后要将多位数字符串清空！！！
                    }
                }
            }
            index++;
            if (index >= expression.length()) { //判断指针走到字符串最后
                break;
            }
        }

        //表达式扫描完毕，就顺序的从数栈和符号栈pop出相应的数据进行运算
        while (!operStack.isEmpty()) {  //当符号栈中无元素，运算完毕
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = operStack.cal(num1, num2, oper);
            numStack.push(res);
        }
        System.out.println("最后表达式" + expression + "的结果为：" + numStack.pop());

    }
}

class ArrayStack2 {  //用数组实现栈
    private int maxSize;
    private int[] stack;
    private int top = -1;   //top指向栈顶元素位置

    public ArrayStack2(int num) {
        maxSize = num;
        stack = new int[maxSize];
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    //获取栈顶元素，但不出栈
    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空");
        }
        return stack[top];
    }

    //判断是否是运算符号
    public boolean isOper(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    //判断符号优先级,数字越大优先级越高
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        }
        if (oper == '+' || oper == '-') {
            return 0;
        }
        return -1; //假定目前表达式只有 + - * /
    }

    //对两个数进行运算
    public int cal(int num1, int num2, int oper) {
        int result = 0;
        switch (oper) {
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num2 / num1;
                break;
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num2 - num1;
                break;
            default:
                break;
        }
        return result;
    }

    //入栈
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈已满，无法插入");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空，无数据异常");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //遍历栈,从栈顶开始遍历
    public void show() {
        if (isEmpty()) {
            System.out.println("栈为空，无数据");
            return;
        }
        //从栈顶开始遍历
        for (int i = top; i >= 0; i--) {
            System.out.println("stack[" + i + "] = " + stack[i]);
        }
    }
}
