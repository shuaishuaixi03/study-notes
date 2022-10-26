# 二分搜索树(Binary Search Tree) 

* 概念
  * 一种特殊的二叉树。二叉树是一种动态的数据结构，有且只有一个根节点，每个节点有一个数据域，两个指针域，分别指向它的左右孩子节点。每一个节点最多有两个孩子节点，最少有0个孩子节点（没有孩子的节点称为叶子节点）。二叉树具有递归结构，每个节点的左子树也是二叉树。二分搜索树是一种对应每个节点的值一定大于其左子树的所有值，小于其右子树的所有值的特殊二叉树。

**实现代码**

```java
import java.util.LinkedList;
import java.util.Queue;
public class BST<E extends Comparable<E>> {
    private class Node {
        public E e;
        public Node left, right;
        public Node (E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }
    // 根节点
    private Node root;
    // 元素个数
    private int size;
    public BST() {
        root = null;
        size = 0;
    }
    // 返回二分搜索树中元素个数
    public int size() {
        return size;
    }
    // 判断二分搜索树是否为空
    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
    // 向二分搜索树中添加新元素e
    public void add(E e) {
        // 如果root为空，添加的新元素为root
        if (root == null) {
            root = new Node(e);
            size ++;
        } else {
            add(root, e);
        }
    }
    // 向以node为根节点的二分搜索树中插入元素e，递归实现
    private void add(Node node, E e) {
        // 插入元素等于node的值，不插入
        if (e.equals(node.e)) {
            return;
        } else if (e.compareTo(node.e) < 0 && node.left = null) {
            // 
            node.left = new Node(e);
            size ++;
            return;
        } else if (e.compareTo(node.e) > 0 && node.right == null) {
            node.right = new Node(e);
            size ++;
            return;
        }
        // 找到比e小的最后一个根节点或比e大的最后一个根结点
        if (e.compareTo(node.s) < 0) {
            add(node.left, e);
        } else {
            add(node.right, e);
        }
    }
    */
    
    public void add(E e) {
        root = add(root, e);
    }
    private Node add(Node node, E e) {
        if (node == null) {
            size ++;
            return new Node(e);
        }
        if (e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            node.right = add(node.right, e);
        }
        return node;
    }
    // 查看二分搜索树是否包含元素e
    public boolean contains(E e) {
        return contains(root, e);
    }
    // 查看以node为根节点的二分搜索树是否包含元素e
    private boolean contains(Node node, E e) {
        if (node == null) {
            return false;
        }
        if (e.compareTo(node.e) == 0) {
            return true;
        } else if (e.compareTo(node.e) < 0) {
            return contains(node.left, e);
        } else {
            return contains(node.right, e);
        }
    }
    // 二分搜索树的前序遍历
    public void preOrder() {
        preOrder(root);
    }
    // 以node为根节点的二分搜索树的前序遍历
    private void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }
    // 二分搜索树的层次遍历，按层打印
    public void levelOrder() {
        Queue<Node> queue = new LinkedList<>();
        if (root == null) {
            return;
        }
        queue.add(root);
        while (!queue.isEmpty()) {
            int count = queue.size();
            for (int i = 0; i < count; i ++) {
                Node cur = queue.poll();
                System.out.print(cur.e + " ");
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
            System.out.println("");
        }
    }
    
    // 返回二分搜索树的最小元素
    public E minimum() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is Empty");
        }
        return minimum(root).e;
    }
    // 返回以node为根节点的二分搜索树的最小值所在的节点
    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }
    
    // 返回二分搜索树的最大元素
    public E maximum() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is Empty");
        }
        return maximum(root).e;
    }
    // 返回以node为根节点的二分搜索树的最大值所在的节点
    private Node maximum(Node node) {
        if (node.right == null) {
            return node;
        }
        return maximum(node.right);
    }
    
    // 删除以node为根节点的二分搜索树的最小值节点，返回删除后二分搜索树新的根节点
    private Node removeMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size --;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }
    // 从二分搜索树中删除最小值所在节点，返回最小值
    public E removeMin() {
        E res = minimum();
        root = removeMin(root);
        return res;
    }
    // 删除以node为根节点的二分搜索树的最大值节点，返回删除后二分搜索树新的根节点
    private Node removeMax(Node node) {
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size --;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }
    // 从二分搜索树中删除最大值所在节点，返回最大值
    public E removeMax() {
        E res = maximum();
        root = removeMax(root);
        return res;
    }
    
    /**
    删除二分搜索树中的任意节点：
    特殊情况：节点node左右都有孩子。法一：找到node节点的右子树中的最小值节点min，这个节点是node节点的后继，用min这个节点替换node，min的左子树为node之前的左子树，右子树为删除min节点后的右子树。法二：找到node节点中左子树中最大值的节点max，这个节点是node节点前驱max，用max这个节点替换node,max的右子树为node之前的右子树，左子树为删除max节点后的左子树。
    */
    // 从二分搜索树中删除元素为e的节点
    public void remove(E e) {
        root = remove(root, e);
    }
    // 删除以node为根节点的二分搜索树中值为e的节点，递归实现
    // 返回删除节点后新的根节点
    private Node remove(Node node, E e) {
        if (node == null) {
            return null;
        }
        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right, e);
            return node;
        } else {
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size --;
                return rightNode;
            }
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size --;
                return leftNode;
            }
            // 要删除的节点左右都有孩子
            // 后继节点successor实现
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;
            
            /*
            // 前驱节点precursor实现
            Node precursor = maximum(node.right);
            precursor.left = removeMax(node.left);
            precursor.right = node.right;
            **/
            
            node.left = node.right = null;
            return successor;
        }
        
    }
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }
    // 生成以node为根节点，深度为depth的描述二分搜索树的字符串，前序遍历方式
    private void generateBSTString(Node node, int depth, StringBuilder res) {
        if (node == null) {
            res.append(generateDepthString(depth) + "null\n");
			return;
        }
        res.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }
    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i ++) {
            res.append("--");
        }
        return res.toString();
    }
}
```

**测试代码**

```java
public class Main {
    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        bst.add(5);
        bst.add(4);
        bst.add(6);
        bst.add(3);
        bst.add(7);

        bst.levelOrder();
        bst.preOrder();
        System.out.println(bst.toString());
        bst.removeMax();
        bst.levelOrder();
        bst.remove(4);
        bst.levelOrder();
    }
}
```

