public class LoopQueue<E> implements Queue<E> {
    private E[] data;
    // front 指向队首元素的位置，tail指向队尾元素的下一个位置
    private int front, tail;
    // 这里的size可以用(tail - front + data.length) % data.length替换
    private int size;

    public LoopQueue(int capacity) {
        data = (E[]) new Object[capacity + 1];
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue() {
        this(10);
    }

    public int getCapacity() {
        return data.length - 1;
    }

    @Override
    public void enqueue(E e) {
        if ((tail + 1) % data.length == front) {
            resetCapacity(getCapacity() * 2);
        }
        data[tail] = e;
        // 队尾指针移动
        tail = (tail + 1) % getCapacity();
        size ++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Dequeue Failed. Queue is Empty");
        }
        E res = data[front];
        data[front] = null;
        // 队头指针移动
        front = (front + 1) % data.length;
        size --;
        if (size == getCapacity() / 4 && data.length / 2 != 0) {
            resetCapacity(data.length / 2);
        }
        return res;
    }

    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("GetFront Failed. Queue is Empty");
        }
        return data[front];
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resetCapacity(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity + 1];
        for (int i = 0; i < size; i ++) {
            // 注意这里不是newData[i] = data[i]，而是newData[i] = data[(i + front) % data.length]
            newData[i] = data[(i + front) % data.length];
        }
        data = newData;
        front = 0;
        tail = size;
    }
}
