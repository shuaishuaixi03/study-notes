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
