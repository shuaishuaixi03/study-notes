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