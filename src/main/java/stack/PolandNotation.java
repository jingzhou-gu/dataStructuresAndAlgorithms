package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @ClassName：PolandNotation
 * @Author: gjz
 * @Date: 2023/12/2 17:01
 * @Description:
 */
public class PolandNotation {
    public static void main(String[] args) {
        //先定义逆波兰表达式 （3+4）x5-6 => 3 4 + 5 * 16 -
        String suffixExpression = "3 4 + 5 * 16 -"; //逆波兰表达式（后缀表达式）
        List<String> list = getListString(suffixExpression); //将字符串放入集合，方便遍历
        int res = calculate(list); //调用计算方法
        System.out.println("表达式 " + suffixExpression + "的结果为 " + res);
    }

    //将一个逆波兰表达式，依次将数字和符号放入到 ArrayList中
    public static List<String> getListString(String str) {
        String[] strings = str.split(" ");
        List<String> list = new ArrayList<>();
        for (String string : strings) {
            list.add(string);
        }
        return list;
    }

    //将集合中的逆波兰表达式进行拆解运算
    public static int calculate(List<String> list) {
        Stack<String> stack = new Stack<>();
        int num1 = 0;
        int num2 = 0;
        int res = 0;
        for (String str : list) {
            if (str.matches("\\d+")) {  //正则表达式匹配到数字
                stack.push(str);  //将数字入栈
            } else {  //匹配到运算符，从栈中弹出两个数进行运算
                num2 = Integer.parseInt(stack.pop());
                num1 = Integer.parseInt(stack.pop());
                if ("+".equals(str)) {
                    res = num1 + num2;
                } else if ("-".equals(str)) {
                    res = num1 - num2;
                } else if ("*".equals(str)) {
                    res = num1 * num2;
                } else if ("/".equals(str)) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符错误");
                }
                stack.push(res + "");  //将运算结果入栈
            }
        }
        return Integer.parseInt(stack.pop());  //返回栈中的最终结果
    }
}
