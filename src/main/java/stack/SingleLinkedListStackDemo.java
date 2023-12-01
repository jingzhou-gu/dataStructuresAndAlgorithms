package stack;

/**
 * @Author: gjz
 * @Date: 2023/12/1 11:08
 * @Description:
 */
public class SingleLinkedListStackDemo {
    public static void main(String[] args) {
        SingeleLinkedListStack singeleLinkedListStack = new SingeleLinkedListStack();
        singeleLinkedListStack.push(new Node(3));
        singeleLinkedListStack.push(new Node(5));
        singeleLinkedListStack.push(new Node(6));
        singeleLinkedListStack.push(new Node(2));
        singeleLinkedListStack.push(new Node(8));
        singeleLinkedListStack.show();
        System.out.println("-------------");
        Node pop1 = singeleLinkedListStack.pop();
        System.out.println(pop1.toString());
        Node pop2 = singeleLinkedListStack.pop();
        System.out.println(pop2.toString());
        singeleLinkedListStack.show();

    }
}

class SingeleLinkedListStack { //用单链表实现栈
    private Node head = new Node(-1);

    //入栈
    public void push(Node node) {  //将新来的数据节点以头插入的方式插入
        if (head.getNext() == null) {
            head.setNext(node);
        } else {
            node.setNext(head.getNext());
            head.setNext(node);
        }
    }

    //出栈
    public Node pop() {
        if (head.getNext() == null) {
            throw new RuntimeException("栈为空异常");
        }
        Node temp = head.getNext();
        head.setNext(temp.getNext());
        return temp;
    }

    //遍历栈
    public void show() {
        if (head.getNext() == null) {
            System.out.println("栈为空");
            return;
        }
        Node temp = head.getNext();
        while (temp != null) {
            System.out.println(temp.toString());
            temp = temp.getNext();
        }
    }
}

class Node {
    private int value;
    private Node next;

    public Node(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}