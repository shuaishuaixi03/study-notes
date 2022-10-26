# 栈

* 概念：
  * 栈是一种线性结构
* 特点
  * 只能在一端添加元素和取出元素，这一端通常称为栈顶
  * 栈中的元素是后进先出(Last In First Out)。
* 应用场景
  * 编辑器的撤销操作（Undo）、括号匹配
  * 程序调用的系统栈
* 实现思路
  * 创建一个泛型接口Stack\<E>，包含push()、pop()、peek()、getSize()、isEmpty()这五个方法，分别对应入栈、弹出栈顶一个元素、取出栈顶一个元素、取得栈中元素个数、判断栈是否为空这五个操作。创建一个ArrayStack\<E>类实现Stack\<E>接口，类中声明一个私有的成员变量为之前自己创建的动态数组类Array的array。[(66条消息) 实现基本数据结构之数组_小肸coding的博客-CSDN博客](https://blog.csdn.net/m0_51321956/article/details/127134276?spm=1001.2014.3001.5501)
* 实现代码

**Scack\<E>接口**

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

**ArrayStack\<E>类**

```java
public class ArrayStack<E> implements Stack<E>{
    private Array<E> array;
    public ArrayStack(int capacity) {
        array = new Array<>(capacity);
    }
    public ArrayStack() {
        array = new Array<>();
    }
    public int getCapacity() {
        return array.getCapacity();
    }
    @Override
    public void push(E e) {
        array.addLast(e);
    }

    @Override
    public E pop() {
        return array.removeLast();
    }

    @Override
    public E peek() {
        return array.getLast();
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
        res.append("Stack: ");
        res.append("[");
        for (int i = 0; i < array.getSize(); i ++) {
            res.append(array.get(i));
            if (i != array.getSize() - 1) {
                res.append(", ");
            }
        }
        res.append("] top");
        return res.toString();
    }
}
```

* 测试代码

```java
public class Main {
    public static void main(String[] args) {
        ArrayStack<Integer> arrayStack = new ArrayStack<>();
        for (int i = 0; i < 6; i ++) {
            arrayStack.push(i);
            System.out.println(arrayStack);
        }
        arrayStack.pop();
        System.out.println(arrayStack);
    }
}
```

#### leetcode[20. 有效的括号](https://leetcode.cn/problems/valid-parentheses/)

```java
class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i ++) {
            char c = s.charAt(i);
            if (c == '[' || c == '(' || c == '{') {
                stack.push(c);
            } else {
                if (!stack.isEmpty() && isMatchBBracket(stack.peek(), c)) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
    private boolean isMatchBBracket(char a, char b) {
        return (a == '(' && b == ')') || (a == '[' && b == ']') || (a == '{' && b == '}');
    }
}
```

