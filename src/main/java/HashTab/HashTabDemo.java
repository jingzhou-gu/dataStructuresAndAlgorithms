package HashTab;

import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @Author: gjz
 * @Date: 2023/12/14 14:43
 * @Description: TODO Hash表的实现
 */
public class HashTabDemo {
    public static void main(String[] args) {
        HashTab hashTab = new HashTab(7);
        hashTab.add(new Emp(1, "zs"));
        hashTab.add(new Emp(2, "ls"));
        hashTab.add(new Emp(8, "ww"));
        hashTab.add(new Emp(15, "zl"));
        hashTab.add(new Emp(23, "jz"));
        hashTab.list();
        hashTab.findById(5);
        hashTab.findById(2);
        hashTab.remove(23);
        hashTab.list();
    }
}

//Hash表，数组长度为size，即有size个链表
class HashTab {
    public int size;
    public EmpLinkedList[] empLinkedLists;

    public HashTab(int size) {
        this.size = size;
        this.empLinkedLists = new EmpLinkedList[size]; //构建长度为size的数组链表
        for (int i = 0; i < size; i++) {  //为数组每个元素进行初始化  ！！！
            empLinkedLists[i] = new EmpLinkedList();
        }
    }

    //hash表添加元素
    public void add(Emp emp) {
        int listNo = hashFun(emp.id); //确定该元素应该放置的链表序号
        empLinkedLists[listNo].add(emp);  //该链表添加元素
    }

    //显示所有链表元素
    public void list() {
        for (int i = 0; i < size; i++) { //遍历每条链表，显示
            empLinkedLists[i].list(i);
        }
    }

    //通过id查找元素
    public void findById(int id) {
        int listNo = hashFun(id); //确定该元素应该放置的链表序号
        Emp emp = empLinkedLists[listNo].findById(id); //通过id在对应链表查找元素
        if (emp != null) {
            System.out.println("在第" + (listNo + 1) + "条链表找到：" + emp.toString());
        } else {
            System.out.println("在链表中没有找到该雇员");
        }
    }

    //通过id删除元素
    public void remove(int id) {
        int listNo = hashFun(id); //确定该元素应该放置的链表序号
        empLinkedLists[listNo].remove(id); //删除该链表上的该元素
    }

    //对id取模，确定应该放置的链表序号
    public int hashFun(int id) {
        return id % size;
    }
}

//员工链表
class EmpLinkedList {
    public Emp head; //链表的头结点

    //链表添加元素
    public void add(Emp emp) {
        if (head == null) {
            head = emp;
            return;
        }
        Emp temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = emp;
    }

    //显示链表所有元素
    public void list(int no) {
        if (head == null) {
            System.out.println("第" + (no + 1) + "条链表为空");
            return;
        }
        System.out.print("第" + (no + 1) + "条链表为");
        Emp temp = head;
        while (temp != null) {
            System.out.print(temp.toString());
            temp = temp.next;
        }
        System.out.println();
    }

    //通过id查找是否存在该元素
    public Emp findById(int id) {
        if (head == null) {
            System.out.println("链表为空");
            return null;
        }
        Emp temp = head;
        while (temp != null) {
            if (temp.id == id) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    //删除元素
    public void remove(int id) {
        if (head == null) {
            System.out.println("该链表为空");
            return;
        }
        if (head.id == id) {  //如果删除元素为头元素，单独处理
            head = head.next;
            System.out.println("员工" + id + "号删除成功");
            return;
        }
        Emp temp = head;
        while (temp.next != null) {
            if (temp.next.id == id) {
                temp.next = temp.next.next;
                System.out.println("员工" + id + "号删除成功");
                return;
            }
            temp = temp.next;
        }
        System.out.println("不存在该元素");
    }
}

//员工类
class Emp {
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}