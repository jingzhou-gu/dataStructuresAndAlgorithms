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
        System.out.println("---------------------------");

        //将中缀表达式转换成后缀表达式
        String expression = "1+((2+3)*4)-15";
        List<String> infixExpression = toInfixExpression(expression);
        System.out.println(infixExpression);
        List<String> suffixExpress = parseSuffixExpression(infixExpression);
        System.out.println(suffixExpress);

        System.out.println(calculate(suffixExpress));
    }

    //将中缀表达式转换到集合中，方便取出
    public static List<String> toInfixExpression(String expression) {
        List<String> list = new ArrayList<>();
        int index = 0;
        String keepStr = ""; //用于拼接多位数
        while (index < expression.length()) {  //当索引未达到末尾
            String s = expression.substring(index, index + 1); //获取当前索引的字符
            if (s.matches("\\d+")) {  //当前索引为数字
                keepStr += s; //拼接到字符串上
                if (index + 1 < expression.length() && expression.substring(index + 1, index + 2).matches("\\d+")) {
                    //当下一位仍然是数字切没超出字符串长度，直接往下走
                } else {  //当下一位不是数字，直接将当前字符串加到集合，并清空字符串
                    list.add(keepStr);
                    keepStr = "";
                }
            } else {  //当前索引为非数字，直接加入集合
                list.add(expression.substring(index, index + 1));
            }
            index++;
        }
        return list;
    }


    /*
     * 1.初始化两个栈：运算符栈s1和储存中间结果栈s2
     * 2.从左至右扫描中缀表达式
     * 3.遇到操作数时压入s2
     * 4.遇到运算符时，比较其与s1栈顶运算符的优先级
     *    4.1如果s1为空，或栈顶运算符为"(",则直接将此运算符入s1栈
     *    4.2否则，若优先级比栈顶运算符的高，也将运算符压入s1
     *    4.3否则，将s1栈顶的运算符弹出并压入到s2中，再次转到4.1与s1中新的栈顶运算符比较
     * 5.遇到括号时：
     *    5.1如果是左括号"(",则直接压入s1
     *    5.2如果是右括号")",则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
     * 6.重复步骤2至5，直到表达式的最右边
     * 7.将s1剩余的运算符依次弹出并压入s2
     * 8.依次弹出s2中的元素并逆序输出，即为中缀表达式对应的后缀表达式
     */
    //中缀表达式转换成后缀表达式
    public static List<String> parseSuffixExpression(List<String> infixExpression) {
        Stack<String> s1 = new Stack<>();
        List<String> s2 = new ArrayList<>(); //s2栈不存在弹栈操作，直接用集合代替
        for (String str : infixExpression) {
            if (str.matches("\\d+")) {
                s2.add(str);
            } else if (str.equals("(")) {
                s1.push(str);
            } else if (str.equals(")")) {
                //如果是右括号")",则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
                while (!s1.peek().equals("(")) { //循环直到遇见"("
                    s2.add(s1.pop());
                }
                s1.pop();  // ！！！将"("弹出s1，消除小括号
            } else {
                //当前字符的优先级小于等于s1栈顶的运算符，将s1栈顶的运算符弹出并压入到s2中，再次转到4.1与s1中新的栈顶运算符比较
                while (s1.size() != 0 && operPriority(str) <= operPriority(s1.peek())) {
                    s2.add(s1.pop());
                }
                //还需要将str压入栈
                s1.push(str);
            }
        }
        while (s1.size() != 0) {  //将s1剩余的运算符依次弹出并压入s2
            s2.add(s1.pop());
        }
        return s2;
    }

    //获取字符串的优先级
    public static int operPriority(String str) {
        int res = -1;
        if ("+".equals(str) || "-".equals(str)) {
            res = 1;
        }
        if ("*".equals(str) || "/".equals(str)) {
            res = 2;
        }
        return res;
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
