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
    private void resetCapacity(int newCapacity) {
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
