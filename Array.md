# 数组是什么

* 概念：
  * 数组可以认为是存放多个同一类型的元素的一种数据结构。
* 特点：
  * 数组中元素的存储地址是连续的。
  * 可以通过数组的索引来访问对应的元素
    * 比如在Java中通过int[] arr = new int[]{0,1,2,3}创建了一个大小为4的int型数组，可以通过arr[0]访问值为0的int型元素。
* 适用场景：
  * 数组的索引与元素的值有特定关系
    * 比如创建一个数组来存放成绩排名和成绩，其中数组索引表示成绩排名，对应索引的元素存放对应的成绩。在Java中，可以通过int[] score = new int[100]创建大小为100的int型数组，score数组可以存放100个int型元素。score[0] 就可以表示排名为1（索引+1，也可以忽略score[0]，浪费一个int型空间，用score[1]表示排名为1的成绩）的成绩。
  * 查找、修改操作频繁
    * 因为数组可以通过索引访问对应的元素，比如访问arr[3]就可以取得arr[3]的值，查找元素的时间复杂度为O(1)。
    * 要修改arr[3]的值的时间复杂度也为O(1)，直接使用一条赋值语句就可以了:arr[3]=e（e为你要赋给它的值）
* 不足：
  * 插入和删除元素可能需要花费大量时间。
    * 因为数组中元素的地址空间是连续的，所以你要插入元素或删除元素，必须移动一些元素给插入的元素腾出一个地址空间或者覆盖掉要删除元素的地址空间。
  * 声明数组的同时必须指定数组的空间大小。万一以后存放的元素太少，浪费了数组空间。存放的元素很多，数组没有多余的空间。可以通过动态数组解决。

* 动态数组的实现过程（Java）
  * 创建一个Array类，使用泛型，类中有两个私有的成员变量，一个成语变量泛型数组data[]用来存放数据，data[]的长度用来表示数组的容量，另一个成员变量int型size表示数组中元素个数。成员方法除了基本的构造方法、增删改查等，额外有一个resetCapacity()方法重新设置数组的容量，用来在给数组添加元素时扩容和删除元素时缩容来解决空间浪费和空间不足的情况，为了避免复杂度的震荡（比如当size==data.length，扩容，当size==data.length/2时，缩容，在size=data.length时，重复执行先添加一个元素后删除一个元素的操作，数组就会不断扩容、然后缩容），所以让数组的缩容变懒，只有size==data.length/4时将数组容量缩容为原容量的一半，当size==data.length时，数组容量扩容为原容量的两倍。每次容量变化为什么不是一个固定的值，比如原数组的容量和元素个数都是10000个，你每次扩容10个，万一后面要添加1000个元素，你必须扩容100次。那你说扩容1000个，万一我只添加10个元素，又浪费了空间，所以扩容和缩容应该根据场景按原数组容量的倍数来变化，可以减少扩容次数和空间浪费。
* 实现代码：

```java
public class Array<E> {
    private E[] data;
    // 数组中元素的个数
    private int size;
    // 构造函数，根据传入的参数构建容量为capacity的数组
    public Array(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }
    // 无参构造函数，默认容量为10
    public Array() {
        this(10);
    }
    // 返回数组的容量大小
    public int getCapacity() {
        return data.length;
    }
    // 返回数组中的元素个数
    public int getSize() {
        return size;
    }
    // 返回数组是否为空
    public boolean isEmpty() {
        return size == 0;
    }
    // 向数组中下标为index的位置添加元素e
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add Failed. Require index >= 0 And index <=size");
        }
        if (size == data.length) {
            resetCapacity(data.length * 2);
        }
        for (int i = size - 1; i >= index; i --) {
            data[i + 1] =  data[i];
        }
        data[index] = e;
        size ++;
    }
    // 在数组开头添加元素e
    public void addFirst(E e) {
        add(0, e);
    }
    // 在数组末尾添加元素
    public void addLast(E e) {
        add(size, e);
    }
    // 获取下标为index的元素
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get Failed. Require index >= 0 And index < size");
        }
        return data[index];
    }
    // 获取数组开头的元素
    public E getFirst() {
        return get(0);
    }
    // 获取数组结尾的元素
    public E getLast() {
        return get(size - 1);
    }
    // 修改下标为index的元素值为e
    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set Failed. Require index >= 0 And index < size");
        }
        data[index] = e;
    }
    // 查找数组中是否包含元素e
    public boolean contains(E e) {
        for (int i = 0; i < size; i ++) {
            if (data[i].equals(i)) {
                return true;
            }
        }
        return false;
    }
    // 将数组中第一个出现的元素s替换成元素e
    public void replace(E s, E e) {
        int index = find(s);
        if (index != -1) {
            set(index, e);
        }
    }
    //查找元素e在数组中第一个出现的下标，如果不存在返回-1
    public int find (E e) {
        for (int i = 0; i < size; i ++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }
    // 删除下标为index的元素，返回删除元素的值
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove Failed. Require index >= 0 And index < size");
        }
        E res = data[index];
        for (int i = index + 1; i <  size; i ++) {
            data[i - 1] = data[i];
        }
        size --;
        data[size] = null; // 让Java的垃圾回收器回收
        if (size == data.length / 4 && data.length / 2 != 0) {
            resetCapacity(data.length / 2);
        }
        return res;
    }
    // 删除数组开头的元素，返回删除元素的值
    public E removeFirst() {
        return remove(0);
    }
    // 删除数组末尾的元素，返回删除元素的值
    public E removeLast() {
        return remove(size - 1);
    }
    // 删除数组中第一个出现的元素e
    public void removeElement(E e) {
        int index = find(e);
        if (index != -1) {
            remove(index);
        }
    }
    // 数组扩容，容量变为原容量的两倍
    public void resetCapacity(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i ++) {
            newData[i] = data[i];
        }
        data = newData;
    }
    // 输出数组的内容
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array：size：%d，captcity：%d\n", size, data.length));
        res.append("[");
        for (int i = 0; i < size; i ++) {
            res.append(data[i]);
            if (i != size - 1) {
                res.append(", ");
            }
        }
        res.append("]");
        return res.toString();
    }
}

```

* 测试代码

```java
public class Main {
    public static void main(String[] args) {
        Array<Integer> array = new Array<>(6);
        for (int i = 0; i < 10; i++) {
            array.addFirst(i);
        }
        array.removeElement(3);
        array.removeFirst();
        array.replace(7, 77);
        System.out.println(array);
    }
}
```