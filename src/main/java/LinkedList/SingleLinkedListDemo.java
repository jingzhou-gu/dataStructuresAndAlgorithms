package LinkedList;

import java.util.Stack;

/**
 * @ClassName：SingleLinkedListDemo
 * @Author: gjz
 * @Date: 2023/11/27 22:40
 * @Description:
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        HeroNode h1 = new HeroNode(1, "宋江");
        HeroNode h2 = new HeroNode(2, "卢俊义");
        HeroNode h3 = new HeroNode(3, "吴用");
        HeroNode h4 = new HeroNode(4, "武松");

        singleLinkedList.addNodeByOrder(h2);
        singleLinkedList.addNodeByOrder(h4);
        singleLinkedList.addNodeByOrder(h1);
        singleLinkedList.addNodeByOrder(h3);

        singleLinkedList.updateNode(new HeroNode(3, "吴用3"));
        singleLinkedList.deleteNode(1);
        singleLinkedList.show();

        System.out.println(singleLinkedList.getLength());

        singleLinkedList.reverseList();
        singleLinkedList.show();

        singleLinkedList.findLastIndexNode(2);

        System.out.println("-----------");
        singleLinkedList.reverseShow();

        System.out.println("***************");
        HeroNode hh1 = new HeroNode(1, "aa");
        HeroNode hh2 = new HeroNode(5, "bb");
        HeroNode hh3 = new HeroNode(8, "cc");
        HeroNode hh4 = new HeroNode(3, "dd");
        HeroNode hh5 = new HeroNode(9, "ee");
        HeroNode hh6 = new HeroNode(17, "ff");
        hh1.next = hh2;
        hh2.next = hh3;
        hh4.next = hh5;
        hh5.next = hh6;
        HeroNode hNode = singleLinkedList.mergeNode(hh1, hh4);

    }
}

//单向链表工具类
class SingleLinkedList {
    private HeroNode head = new HeroNode(0, " ");

    public HeroNode getHead() {
        return head;
    }

    //添加一个新的节点数据到链表尾部
    public void addNode(HeroNode newNode) {
        HeroNode temp = head;
        while (true) {
            if (temp.next == null) { //到达链表尾部
                break;
            }
            temp = temp.next;
        }
        temp.next = newNode;
    }

    //按照序号添加节点数据到链表
    public void addNodeByOrder(HeroNode newNode) {
        HeroNode temp = head;
        while (true) {
            if (temp.next == null) {  //到达链表结尾，直接插入
                temp.next = newNode;
                break;
            }
            if (temp.next.no > newNode.no) { //找到插入位置
                newNode.next = temp.next;
                temp.next = newNode;
                break;
            } else if (temp.next.no == newNode.no) {
                System.out.println("该序号节点已经存在");
                break;
            }
            temp = temp.next;  //临时指针向后移动
        }
    }

    //更新链表数据
    public void updateNode(HeroNode heroNode) {
        if (head.next == null) {
            System.out.println("该链表为空");
            return;
        }
        HeroNode temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {  //已经遍历完毕
                break;
            }
            if (temp.no == heroNode.no) {  //找到该节点
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = heroNode.name;  //更新该节点
        } else {
            System.out.println("该序号的节点未找到");
        }
    }

    //根据序号删除链表数据
    public void deleteNode(int no) {  //单向链表只能找到要删除的节点的前一个节点
        HeroNode temp = head;
        boolean flag = false;

        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == no) { //找到该节点
                flag = true; //表示该节点存在
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.println("要删除的序号" + no + "节点未找到");
        }
    }

    //获取链表节点个数
    public int getLength() {
        if (head.next == null) {
            return 0;
        }
        int num = 0;
        HeroNode temp = head.next;
        while (temp != null) {
            num++;
            temp = temp.next;
        }
        return num;
    }

    //获取链表 倒数 第index个节点的数据
    public HeroNode findLastIndexNode(int index) {
        if (head.next == null) {
            return null;
        }
        int length = getLength();  //获取链表的节点个数
        if (index <= 0 || index > length) {
            return null;
        }
        HeroNode temp = head.next;
        for (int i = 0; i < length - index; i++) {   //指针往后移动length-index次
            temp = temp.next;
        }
        System.out.println(temp);
        return temp;
    }

    //单链表的反转
    public void reverseList() {
        if (head.next == null || head.next.next == null) {
            return;    //不存在节点或只存在一个节点则直接返回
        }
        HeroNode newHeadNode = new HeroNode(0, " ");
        HeroNode temp = head.next;   //指向链表的当前节点
        HeroNode next = null;   //指向当前节点的下一个节点
        while (temp != null) {
            next = temp.next;  //指向当前节点的下一个节点
            temp.next = newHeadNode.next; //将当前节点以头插法的方式插入到新链表
            newHeadNode.next = temp;      //将当前节点以头插法的方式插入到新链表
            temp = next; //将当前节点指针移动到下一位置
        }
        head.next = newHeadNode.next;  //将头结点指向新联表的第一个节点
    }


    //合并两个有序的单链表，合并之后的链表依然有序
    public HeroNode mergeNode(HeroNode h1, HeroNode h2) {
        if (h1 == null) {
            return h2;
        }
        if (h2 == null) {
            return h1;
        }
        HeroNode newHead = new HeroNode(0, " "); //创建一个新链表的头结点
        HeroNode temp = newHead;    //创建一个新链表的向后移动的指针
        while (h1 != null && h2 != null) {
            if (h1.no <= h2.no) {
                temp.next = h1; //将链表1的值赋给新链表的移动指针的下一位
                temp = temp.next; //向后移动新链表指针
                h1 = h1.next;  //向后移动链表1的指针
            } else {
                temp.next = h2; //将链表2的值赋给新链表的移动指针的下一位
                temp = temp.next;  //向后移动新链表指针
                h2 = h2.next;  //向后移动链表2的指针
            }
        }
        if (h1 == null) {
            temp.next = h2;  //链表1的值已取完，将链表2的剩余值添加到新链表尾部
        } else {
            temp.next = h1;  //链表2的值已取完，将链表1的剩余值添加到新链表尾部
        }
        return newHead.next;
    }

    //展示链表所有数据
    public void show() {
        if (head.next == null) {
            System.out.println("该链表为空");
            return;
        }
        HeroNode temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp.toString());
            temp = temp.next;
        }
    }

    //倒序打印链表的所有数据
    public void reverseShow() {
        if (head.next == null) {
            System.out.println("该链表为空");
            return;
        }
        int length = getLength();    //获取链表长度
        Stack<HeroNode> stack = new Stack<HeroNode>();
        HeroNode temp = head.next;
        while (temp != null) {
            stack.push(temp);    //将链表数据压入栈中
            temp = temp.next;
        }
        while (stack.size() > 0) {
            System.out.println(stack.pop());  //将栈中数据依次打印
        }

//        HeroNode[] heroNodes = new HeroNode[length];
//        HeroNode temp = head.next;
//        for (int i = 0; i < heroNodes.length; i++) {
//            heroNodes[i] = temp;
//            temp=temp.next;
//        }
//        for (int i = heroNodes.length-1; i >= 0; i--) {
//            System.out.println(heroNodes[i]);
//        }

    }

}

//节点类
class HeroNode {
    public int no;
    public String name;
    public HeroNode next;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}