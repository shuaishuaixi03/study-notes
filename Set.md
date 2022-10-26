# 集合(Set)

* 概念
  * 里面没有重复元素的一种数据结构

* 应用

  * 客户统计、词汇量统计

* 实现

  * 定义集合的接口

  ```java
  public interface Set<E> {
      void add(E e); // 添加元素
      void remove(E e); // 删除元素
      boolean contains(E e); // 是否包含元素e
      int getSize(); // 获取集合大小
      boolean isEmpty(); // 判断集合是否为空
  }
  ```

  * 用二叉搜索树作为集合的底层实现

  ```java
  public class BSTSet<E extends Comparable<E>> implements Set<E> {
      private BST<E> bst;
      public BSTSet() {
          bst = new BST<>();
      }
      @Override
      public int getSize() {
          return bst.size();
      }
      @Override
      public boolean isEmpty() {
          return bst.isEmpty();
      }
      @Override
      public void add(E e) {
          bst.add(e);
      }
      @Override
      public boolean contains(E e) {
          return bst.contains(e);
      }
      @Override
      public void remove(E e) {
          return bst.remove(e);
      }
  }
  ```

  * 基于LinkedList(我自己实现的链表类)的集合实现

  ```java
  public class LinkedListSet<E> implements Set<E> {
      private LinkedList<E> list;
      public LinkedListSet() {
          list = new LinkedList();
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
      public boolean contains(E e) {
          return list.contains(e);
      }
      @Override
      public ssoioi
  }
  ```

  

  * 应用实现（统计一本书有多少不同个单词）

* 