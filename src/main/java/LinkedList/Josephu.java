package LinkedList;

/**
 * @Author: gjz
 * @Date: 2023/11/30 17:10
 * @Description: TODO
 */
public class Josephu { //约瑟夫问题
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(15);
        circleSingleLinkedList.show();
        circleSingleLinkedList.countBoy(3, 6, 15);
    }
}

class CircleSingleLinkedList {  //环形单向链表
    private Boy first = null;   //头节点

    //创建一个数量为nums的环形链表
    public void addBoy(int nums) {  //nums表示要添加的小孩个数
        if (nums < 1) {
            System.out.println("数量输入错误");
            return;
        }
        if (nums == 1) {   //只有1个小孩时，自己的下一个指向自己
            first = new Boy(1);
            first.setNext(first);
            return;
        }
        Boy temp = null;
        for (int i = 0; i < nums; i++) {   //依次往后添加小孩
            Boy boy = new Boy(i + 1);
            if (i == 0) {
                first = boy;
                temp = boy;
            } else {
                temp.setNext(boy);
                temp = temp.getNext();  //temp指向最后一个小孩
            }
        }
        temp.setNext(first); //将最后一个小孩的下一个指向first头节点
    }

    //遍历环形链表
    public void show() {
        if (first == null) {
            System.out.println("链表为空");
            return;
        }
        Boy temp = first;
        while (true) {
            System.out.println(temp.toString());
            if (temp.getNext() == first) {  //表示遍历完成
                break;
            }
            temp = temp.getNext();
        }
    }

    //小孩出圈的序列展示  startNo:从第几个小孩开始数   countNum:数几个小孩   nums:最初圈中的小孩数量
    public void countBoy(int startNo, int countNum, int nums) {
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("输入参数不合法");
            return;
        }

        Boy last = first;

        //last一直跟在first后面，指向最后一个
        while (last.getNext() != first) {
            last = last.getNext();
        }

        //从第startNo小孩开始数，将first和last移动相同的次数，将first指向这个小孩
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            last = last.getNext();
        }

        while (first != last) {
            //将first和last同时移动countNum-1次，然后让first指向的小孩出圈
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                last = last.getNext();
            }
            System.out.println("小孩" + first.getNo() + "号出圈"); //first指向的小孩出圈
            first = first.getNext(); //将first指向的小孩出圈
            last.setNext(first); //将first指向的小孩出圈
        }
        System.out.println("最后一个小孩" + first.getNo() + "号出圈"); //循环完毕，只剩最后一个小孩
    }
}

class Boy {
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Boy{" + "no=" + no + '}';
    }
}