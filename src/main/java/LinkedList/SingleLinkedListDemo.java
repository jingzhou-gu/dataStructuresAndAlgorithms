package LinkedList;

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

    }
}

//单向链表工具类
class SingleLinkedList {
    private HeroNode head = new HeroNode(0, " ");

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
            temp = temp.next;
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
    public void deleteNode(int no) {
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