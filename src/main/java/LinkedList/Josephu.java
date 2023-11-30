package LinkedList;

/**
 * @Author: gjz
 * @Date: 2023/11/30 17:10
 * @Description: TODO
 */
public class Josephu {
    public static void main(String[] args) {

    }
}

class CircleSingleLinkedList{  //环形单向链表
    private Boy first = null;   //头节点

    //创建一个数量为nums的环形链表
    public void addBoy(int nums){  //nums表示要添加的小孩个数
        if(nums<1){
            System.out.println("数量输入错误");
            return;
        }
        Boy temp = null;
        for (int i = 0; i < nums; i++) {
            Boy boy = new Boy(i);
            if(i==0){
                first = boy;
                temp = boy;
            }else{
                temp.setNext(boy);
                temp = temp.getNext();
            }
        }
    }

    //遍历环形链表
    public void show(){

    }

    //小孩出圈的序列展示  startNo:从第几个小孩开始数   countNum:数几个小孩   nums:最初圈中的小孩数量
    public void countBoy(int startNo,int countNum,int nums){

    }
}

class Boy{
    private int no;
    private Boy next;
    public Boy(int no){
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
}