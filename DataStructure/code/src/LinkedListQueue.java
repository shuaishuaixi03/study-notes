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