package LinkedList;

/**
 * @ClassName：DoubleLinkedListDemo
 * @Author: gjz
 * @Date: 2023/11/29 23:28
 * @Description:
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        HeroNode2 h1 = new HeroNode2(1, "宋江");
        HeroNode2 h2 = new HeroNode2(2, "卢俊义");
        HeroNode2 h3 = new HeroNode2(3, "吴用");
        HeroNode2 h4 = new HeroNode2(4, "武松");
        HeroNode2 h5 = new HeroNode2(5, "林冲");
//        doubleLinkedList.addNode(h1);
//        doubleLinkedList.addNode(h2);
//        doubleLinkedList.addNode(h4);
//        doubleLinkedList.addNode(h5);
//        doubleLinkedList.addNode(h3);
        doubleLinkedList.addNodeByOrder(h2);
        doubleLinkedList.addNodeByOrder(h1);
        doubleLinkedList.addNodeByOrder(h3);
        doubleLinkedList.addNodeByOrder(h5);
        doubleLinkedList.addNodeByOrder(h4);


        doubleLinkedList.show();
        doubleLinkedList.reverseShow();
        System.out.println("---------------");

        doubleLinkedList.deleteNode(1);
        doubleLinkedList.deleteNode(3);
        doubleLinkedList.show();
        System.out.println("-----------------");

        doubleLinkedList.updateNode(new HeroNode2(2, "卢俊义2"));
        doubleLinkedList.show();

    }
}

class DoubleLinkedList {
    private HeroNode2 head = new HeroNode2(0, " ");

    public HeroNode2 getHead() {
        return head;
    }


    //向双向链表的尾部添加新节点
    public void addNode(HeroNode2 newNode) {
        HeroNode2 temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;    //链表的最后一个节点的next指向新节点
        newNode.prev = temp;    //新节点的prev指向链表最后一个节点
    }

    //按照序号添加节点数据到双向链表
    public void addNodeByOrder(HeroNode2 newNode) {
        HeroNode2 temp = head;
        boolean flag = false;
        while (temp.next != null) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no <= newNode.no) { //当前节点的下一个节点的序号小于等于新节点，指针向后移动
                temp = temp.next;
            } else if (temp.next.no > newNode.no) { //当前节点的下一个节点的序号比新节点大，找到插入位置
                flag = true;
                break;
            }
        }
        if (flag) {   //找到中间插入位置
            newNode.next = temp.next; //指向要注意顺序，否则链表指向混乱
            newNode.prev = temp;
            temp.next.prev = newNode;
            temp.next = newNode;   //这个要放置在最后，否则会混乱
        } else {   //插入到末尾
            temp.next = newNode;
            newNode.prev = temp;
        }
    }

    //更新双向链表数据
    public void updateNode(HeroNode2 heroNode) {
        HeroNode2 temp = head;
        boolean flag = false;
        while (temp.next != null) {
            temp = temp.next;
            if (temp.no == heroNode.no) {
                flag = true;   //找到该节点
                break;
            }
        }
        if (flag) {  //找到该节点
            temp.name = heroNode.name;   //更新节点内容
        } else {
            System.out.println("未找到该节点");
        }
    }

    //根据序号删除双向链表数据
    public void deleteNode(int no) {
        if (head.next == null) {
            System.out.println("该链表为空");
            return;
        }
        HeroNode2 temp = head.next;
        boolean flag = false;
        while (temp != null) {
            if (temp.no == no) {  //找到要删除的序号节点
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.prev.next = temp.next;   //将要删除节点的下一个节点地址赋给要删除节点的上一个节点的next
            if (temp.next != null) {  //如果要删除节点为最后一个节点，不执行里面操作，否则会空指针异常
                temp.next.prev = temp.prev; //将要删除节点的上一个节点地址赋给要删除节点的下一个节点的prev
            }
        } else {
            System.out.println("该序号的节点不存在");
        }

    }

    //遍历双向链表
    public void show() {  //与单链表遍历相同
        if (head.next == null) {
            System.out.println("该链表为空");
            return;
        }
        HeroNode2 temp = head.next;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }

    //倒序打印双向链表的所有数据
    public void reverseShow() {
        if (head.next == null) {
            System.out.println("链表为空");
        }
        HeroNode2 temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        while (temp != head) {
            System.out.println(temp.toString());
            temp = temp.prev;
        }
    }

}

//节点类
class HeroNode2 {
    public int no;
    public String name;
    public HeroNode2 prev;
    public HeroNode2 next;

    public HeroNode2(int no, String name) {
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