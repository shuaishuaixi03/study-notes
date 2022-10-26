# 队列(Queue)

* 概念：
  * 一种线性结构
* 特点：
  * 只能在一端添加元素，在另一端取出元素。添加元素的一端通常称为队尾，取出元素的一端通常称为队首。
  * 元素是先进先出(First In First Out)
* 应用场景：
  * 消息队列
  * 操作系统中的进程管理相关的就绪队列、运行队列
  * 广度优先遍历
  * 生活中的场景：排队
* 实现思路和栈一样。[(66条消息) 实现基本数据结构之栈_小肸coding的博客-CSDN博客](https://blog.csdn.net/m0_51321956/article/details/127135017?spm=1001.2014.3001.5501)
* **Queue\<E>接口**

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

* **ArrayQueue\<E>类**

```java
public class ArrayQueue<E> implements Queue<E> {
    private Array<E> array;
    public ArrayQueue(int capacity) {
        array = new Array<>(capacity);
    }
    public ArrayQueue() {
        array = new Array<>();
    }
    public int getCapacity() {
        return array.getCapacity();
    }
    @Override
    public void enqueue(E e) {
        array.addLast(e);
    }

    @Override
    public E dequeue() {
        return array.removeFirst();
    }

    @Override
    public E getFront() {
        return array.getFirst();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }
    
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue: ");
        res.append("front [");
        for (int i = 0; i < array.getSize(); i ++) {
            res.append(array.get(i));
            if (i != array.getSize() - 1) {
                res.append(", ");
            }
        }
        res.append("] tail");
        return res.toString();
    }
}
```

**测试代码**

```
public class Main {
    public static void main(String[] args) {
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        for (int i = 0; i < 5; i ++) {
            arrayQueue.enqueue(i);
            System.out.println(arrayQueue);
        }
        arrayQueue.dequeue();
        arrayQueue.dequeue();
        System.out.println(arrayQueue);
    }
}
```

> 上面实现的队列其中出队的时间复杂度为O(n)，因为它是将动态数组的第一个元素删除并返回，还需要将后面的元素都向前移动一位。但循环队列使用front指针指向队首，tail指针指向队尾来时出队和入队的时间复杂度都为O(1)。当front==tail，队列为空，当(tail + 1) % capacity == front队列为满（浪费了一个空间以此来区分队列为空和队列为满时判断条件不一样）。

**循环队列LoopQueue\<E>实现**

```java
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
```

**队列和循环队列的性能比较测试（大概）**

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
        int opCount = 10000;
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        System.out.println("ArrayQueue: 进行" + opCount + "次入队、出队的时间: " + testQueue(arrayQueue, opCount) + "s");
        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        System.out.println("LoopQueue: 进行" + opCount + "次入队、出队的时间: " + testQueue(loopQueue, opCount) + "s");
    }
}
```

> 测试结果发现两者之间的时间差异很大，因为普通队列的出队复杂度为O(n)，而循环队列出队的时间复杂度为O(1)。
