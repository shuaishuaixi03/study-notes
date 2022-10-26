# 链表

* 概念：
  * 链表是一种真正的动态数据结构，之前的动态数组是依靠改变静态数组的容量来支持动态。而链表不同，链表是由节点组成，每个节点分为数据域和指针域，数据域存放数据，而指针域指向下一个节点，当需要向链表中添加元素时，只需创建一个新的节点，让新节点的数据域存放元素，让链表最后一个节点的指针域指向这个新节点，新节点的指针域指向null，表示这是链表的最后一个节点，这种向链表中添加元素的方法称作尾插法。还可以让新节点的指向域指向链表的头节点从而向链表中添加节点，这种方法称为头插法。
  
* 特点：
  * 元素的存储地址空间是不连续的，链表是通过节点的指针域将各个节点串起来，当需要添加元素时会声明一个新的节点，所以链表有很高的空间利用率，但在链表中查找元素、修改元素需要遍历整个链表，查找、修改操作效率不好，不过向链表中插入元素不需要移动其它元素，所以链表的删除、添加元素操作效率好。
  
* 适用场景
  * 一些添加、删除操作较多但查找、修改操作较少
  * 作为一些其它动态数据结构（B+树、B树）的底层实现
  
* 链表实现（Java，添加元素）（不用虚拟头节点）
  
  ```java
  public class LinkedList<E> {
      private class Node {
          public E e;
          public Node next;
          
          public Node(E e, Node next) {
              this.e = e;
              this.next = next;
          }
          public Node(E e) {this(e, null);}
          public Node() {this(null, null);}
          @Override
          public String toString() {return e.toString();}
      }
      private Node head; // 链表的头节点
      private int size; // 记录链表中的元素
      
      public LinkedList() {
          head = null;
          size = 0;
      }
      
      // 获取链表中的元素个数
      public int getSize() {
          return size;
      }
      // 判断链表是否为空
      public boolean isEempty() {
          return size == 0;
      }
      // 向链表头添加元素
      public void addFirst(E e) {
          Node node = new Node(e);
          node.next = head;
          head = node;
          // 上面三行代码等同于head = new Node(e, head);
         	size ++;
      }
      // 向链表中间指定位置插入元素
      public void add(int index, E e) {
          if (index < 0 || index > size) {
              throw new IllegalArgumentException("Add failed. Illegal index");
          }
          if (index == 0) {
              addFirst(e);
          } else {
              // pre记录插入位置的前一个节点
              Node pre = head;
              for (int i = 0; i < index - 1; i++) {
                  pre = pre.next;
              }
              pre.next = new Node(e, pre.next);
              size ++;
          }
      }
      // 向链表末尾添加元素
      public void addLast(E e) {
          add(size, e);
      }
  }
  ```
  
  > 在上面的链表实现过程中，向链表头插入元素和向链表指定位置插入元素逻辑上有些区别，可以用一个虚拟头节点来指向头节点，使两者逻辑一样。
  
* 链表实现（Java，增删改查）（使用虚拟头节点）

```java
public class LinkedList<E> {
    private class Node {
        public E e;
        public Node next;
        public Node (E e, Node next) {
            this.e = e;
            this.next = next;
        }
        public Node(E e) {this(e, null);}
        public Node() {this(null, null);}
        @Override
        public String toString() {
            return e.toString();
        }
    }
    private Node dummyHead;
    private int size;
    public LinkedList() {
        dummyHead = new Node(null, null);
        size = 0;
    }
    // 返回链表中元素个数
    public int getSize() {
        return size;
    }
    // 判断链表是否为空
    public boolean isEmpty() {
        return size == 0;
    }
    // 向链表中指定位置插入元素
    public void add (int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Illegal index");
        }
        Node pre = dummyHead;
        for (int i = 0; i < index; i ++) {
            pre = pre.next;
        }
        pre.next = new Node(e, pre.next);
        size ++;
    }
    // 向链表头插入元素
    public void addFirst(E e) {
        add(0, e);
    }
    // 向链表末尾添加元素
    public void addLast(E e) {
        add(size, e);
    }
    // 获得链表中指定位置的元素
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get Failed.Illegal index");
        }
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i ++) {
            cur = cur.next;
        }
        return cur.e;
    }
    // 取得链表头的元素
    public E getFirst() {
        return get(0);
    }
    // 取得链表尾的元素
    public E getLast() {
        return get(size - 1);
    }
    // 修改链表中指定位置的元素
    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set Failed. Illegal index");
        }
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i ++) {
            cur = cur.next;
        }
        cur.e = e;
    }
    // 查找链表中是否包含元素e
    public boolean contains(E e) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.equals(e)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }
    // 删除链表中指定位置的元素并返回
    public E delete(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Delete Failed. Illegal idnex");
        }
        Node pre = dummyHead;
        for (int i = 0; i < index; i ++) {
            pre = pre.next;
        }
        Node res = pre.next;
        pre.next = res.next;
        res.next = null; // 让删除的节点从链表中脱离
        size --;
        return res.e;
    }
    // 删除链表头的元素并返回
    public E deleteFirst() {
        return delete(0);
    }
    // 删除链表尾的元素并返回
    public E deleteLast() {
        return delete(size - 1);
    }
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (Node cur = dummyHead.next; cur != null; cur = cur.next) {
            res.append(cur + "->");
        }
        res.append("NULL");
        return res.toString();
    }
}
```

**测试代码**

```java
public class Main {
    // 测试运行opCount个入队和出队操作的时间
    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < 5; i ++) {
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }
        linkedList.deleteFirst();
        System.out.println(linkedList);
        linkedList.deleteLast();
        System.out.println(linkedList);
    }
}
```

**使用链表实现栈**

```java
public interface Stack<E> {
    // 将元素e入栈
    void push(E e);
    // 弹出栈顶的元素e，并返回e
    E pop();
    // 取出栈顶的元素e
    E peek();
    // 获取栈的元素个数
    int getSize();
    // 判断栈是否为空
    boolean isEmpty();
}
```

```java
public class LinkedListStack<E> implements Stack<E> {
    private LinkedList<E> list;
    public LinkedListStack() {
        list = new LinkedList<>();
    }
    @Override
    public void push(E e) {
        list.addFirst(e);
    }
    @Override
    public E pop() {
        return list.deleteFirst();   
    }
    @Override
    public E peek() {
        return list.get(0);
    }
    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack: top ");
        res.append(list);
        return res.toString();
    }
}
```

**链表栈和动态数组栈的性能测试**

```java
import java.util.Random;
public class Main {
    // 测试运行opCount个入队和出队操作的时间
    private static double testStack(Stack<Integer> stack, int opCount) {
        long startTime = System.nanoTime();
        Random random = new Random();
        for (int i = 0; i < opCount; i ++) {
            stack.push((Integer.MAX_VALUE));
        }
        for (int i = 0; i < opCount; i ++) {
            stack.pop();
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }
    public static void main(String[] args) {
        int opCount = 10000;
        ArrayStack<Integer> arrayStack = new ArrayStack<>();
        System.out.println("ArrayStack: 进行" + opCount + "次入栈、出栈的时间: " + testStack(arrayStack, opCount) + "s");
        LinkedListStack<Integer> linkedListStack = new LinkedListStack<>();
        System.out.println("LinkedListStack: 进行" + opCount + "次入栈、出栈的时间: " + testStack(linkedListStack, opCount) + "s");
    }
}
```

> 这两者间的性能没有太大差异，一般是链表栈比数组栈快一些，因为数组栈开辟空间需要一些时间，而它们出栈、入栈的时间复杂度都是O(1)。可能数组栈比链表栈快一点，与JVMs、操作系统等各种因素相关，但两者之间算法的差距不大。

**使用链表实现队列**

> 因为链表不是对称的，而队列需要一端添加元素，一端取出元素，但可以用一个尾指针指向链表尾部，头指针作为队首（因为从尾指针删除元素时间复杂度为O(N)，而从头指针删除元素时间复杂度为O(1)），尾指针作为队尾，这里不使用虚拟头节点。

```java
public interface Queue<E> {
    // 入队
    void enqueue(E e);
    // 出队
    E dequeue();
    // 取出队首元素
    E getFront();
    // 获得队列中元素个数
    int getSize();
    // 判断队列是否为空
    boolean isEmpty();
}
```

```java
public class LinkedListQueue<E> implements Queue<E> {
    private class Node {
        public E e;
        public Node next;
        public Node (E e, Node next) {
            this.e = e;
            this.next = next;
        }
        public Node (E e) {
            this(e, null);
        }
       	@Override
        public String toString() {
            return e.toString();
        }
    }
    private Node head, tail;
    private int size;
    public LinkedListQueue() {
        head = null;
        tail = null;
        size = 0;
    } 
    @Override
    public int getSize() {
        return size;
    }
    @Override
   	public boolean isEmpty() {
        return size == 0;
    }
    @Override
    public void enqueue(E e) {
        if (tail == null) {
            tail = new Node(e);
            head = tail;
        } else {
            tail.next = new Node(e);
            tail = tail.next;
        }
        size ++;
    }
    @Override
   	public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Dequeue Failed. Queue is Empty");
        }
        Node res = head;
        head = head.next;
        res.next = null; // 让出队节点脱离链表，方便被回收
        if (head == null) {
            tail = null;
        }
        size --;
        return res.e;
    }

    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Queue is Empty");
        }
        return head.e;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue: front");
        for (Node cur = head; cur != null; cur = cur.next) {
            res.append(cur + "->");
        }
        res.append("NULL tail");
        return res.toString();
    }
}
```

**队列、循环队列、链表队列测试**

```java
import java.util.Random;
public class Main {
    // 测试运行opCount个入队和出队操作的时间
    private static double testQueue(Queue<Integer> queue, int opCount) {
        long startTime = System.nanoTime();
        Random random = new Random();
        for (int i = 0; i < opCount; i ++) {
            queue.enqueue(Integer.MAX_VALUE);
        }
        for (int i = 0; i < opCount; i ++) {
            queue.dequeue();
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }
    public static void main(String[] args) {
        int opCount = 100000;
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        System.out.println("ArrayQueue: 进行" + opCount + "次入队、出队的时间: " + testQueue(arrayQueue, opCount) + "s");
        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        System.out.println("LoopQueue: 进行" + opCount + "次入队、出队的时间: " + testQueue(loopQueue, opCount) + "s");
        LinkedListQueue<Integer> linkedListQueue = new LinkedListQueue<>();
        System.out.println("LinkedListQueue: 进行" + opCount + "次入队、出队的时间: " + testQueue(linkedListQueue, opCount) + "s");
    }
}
```

> 可以发现普通队列所用时间较长，与循环队列和链表队列相差百倍，是因为普通队列的出队的时间复杂度为O(n)，另外两个队列出队的时间复杂度为O(1)。

# 链表相关问题

#### [203. 移除链表元素](https://leetcode.cn/problems/remove-linked-list-elements/)

LeetCode上移除链表元素：

**解法一（不使用虚拟头节点）**

```java
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        // 因为头节点可能就是要移除的节点，需要特殊处理
        while (head != null && head.val == val) {
            ListNode del = head;
            head = head.next;
            del.next = null; // 让被删除节点脱离链表，内存好被回收
        }
        if (head == null) {
            return head;
        }
        // pre记录要删除节点的前一个节点
        ListNode pre = head;
        while (pre.next != null) {
            if (pre.next.val == val) {
               	ListNode del = pre.next;
                pre.next = pre.next.next;
                del.next = null; // 让被删除节点脱离链表，内存好被回收
            } else {
                pre = pre.next;
            }
        }
        return head;
    }
}
```

> 因为OJ平台执行程序后会自动释放内存，所以让被删除节点脱离链表，好让垃圾回收器回收这一步操作可以不用执行，代码可以简化为：

```java
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        // 因为头节点可能就是要移除的节点，需要特殊处理
        while (head != null && head.val == val) {
            head = head.next;
        }
        if (head == null) {
            return head;
        }
        // pre记录要删除节点的前一个节点
        ListNode pre = head;
        while (pre.next != null) {
            if (pre.next.val == val) {
                pre.next = pre.next.next;
            } else {
                pre = pre.next;
            }
        }
        return head;
    }
}
```

**解法二（使用虚拟头节点）**

```java
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode pre = dummyHead;
        // 因为使用了虚拟头节点，所以头节点有前一个节点，处理逻辑和其它节点一样
        while (pre.next != null) {
            if (pre.next.val == val) {
                pre.next = pre.next.next;
            } else {
                pre = pre.next;
            }
        }
        return dummyHead.next;
    }
}
```

**解法三（递归）**

> 递归的本质就是将原来的问题装换成更小的问题，然后解决。在链表中移除对应的元素，首先可以把一个链表拆分为一个头节点和由头节点后所有节点组成的一个更短的链表，在这个更短的链表中再删除对应的元素，所以又要将这个更短的链表拆分，不断拆分，知道这个短链表为空，短链表为空就是递归的终止条件。要是递归没有终止条件，递归会不断进行，而递归需要函数调用和系统栈空间，最终程序会抛出栈溢出异常。

```java
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        head.next = removeElements(head.next, val);
        return head.val == val ? head.next : head;
    }
}
```

