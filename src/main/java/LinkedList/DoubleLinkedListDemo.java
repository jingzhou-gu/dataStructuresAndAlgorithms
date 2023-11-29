package LinkedList;

/**
 * @ClassName：DoubleLinkedListDemo
 * @Author: gjz
 * @Date: 2023/11/29 23:28
 * @Description:
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {

    }
}

class DoubleLinkedList{
    private HeroNode2 head = new HeroNode2(0, " ");

    public HeroNode2 getHead() {
        return head;
    }


    //添加节点
    public void addNode(HeroNode2 newNode) {

    }

    //更新双向链表数据
    public void updateNode(HeroNode2 heroNode) {

    }

    //根据序号删除双向链表数据
    public void deleteNode(int no) {

    }

    //遍历双向链表
    public void show() {  //与单链表遍历相同

    }

}

//节点类
class HeroNode2 {
    public int no;
    public String name;
    public HeroNode2 per;
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