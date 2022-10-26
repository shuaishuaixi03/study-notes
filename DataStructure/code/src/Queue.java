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
