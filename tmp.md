## [706. 设计哈希映射](https://leetcode.cn/problems/design-hashmap/)

**思路一：**用一个大小为10的6次方+1的大小来对应哈希表的键值对映射。用空间换时间。缺点：元素太多，存不下去，元素太少浪费空间。

```java
class MyHashMap {
    private int[] map;
    public MyHashMap() {
        map = new int[1000001];
        for (int i = 0; i < 1000001; i ++) {
            map[i] = -1;
        }
    }
    
    public void put(int key, int value) {
        map[key] = value;
    }
    
    public int get(int key) {
        return map[key];
    }
    
    public void remove(int key) {
        map[key] = -1;
    }
}
```

**思路二：** 数组+链表实现，平衡时间和空间，用链地址法实现。

```java
class MyHashMap {
    // 定义一个内部类，来存放键值对
    class Pair {
        private int key;
        private int value;
        public Pair(int key, int value) {
            this.key = key;
            this.value = value;
        }
        public int getKey() {
            return key;
        }
        public int getValue() {
            return value;
        }
       	public void setValue(int value) {
            this.value = value;
        }
    }
    private final static int Bash = 769;
    private LinkedList[] data;
    public MyHashMap() {
        data = new LinkedList[Bash];
        for (int i = 0; i < Bash; i ++) {
            data[i] = new LinkedList<Pair>();
        }
    }
    public void put(int key, int value) {
        int index = hash(key);
        Iterator<Pair> iterator = data[index].iterator();
        while (iterator.hasNext()) {
            Pair pair = iterator.next();
            if (pair.getKey() == key) {
                pair.setValue(value);
                return;
            }
        }
        data[index].offerLast(new Pair(key, value));
    }
    public int get(int key) {
        int index = hash(key);
        Iterator<Pair> iterator = data[index].iterator();
        while (iterator.hasNext()) {
            Pair pair = iterator.next();
            if (pair.getKey() == key) {
                return pair.getValue();
            }
        }
        return -1;
    }
    public void remove(int key) {
        int index = hash(key);
        Iterator<Pair> iterator = data[index].iterator();
        while (iterator.hasNext()) {
            Pair pair = iterator.next();
            if (pair.getKey() == key) {
                data[index].remove(pair);
                return;
            }
        }
    }
    private static int hash(int n) {
        return n % Bash;
    }
}
```

## [56. 合并区间](https://leetcode.cn/problems/merge-intervals/)

**解题思路：**先将intervals数组按左边界从小到大排序，要是下一个区间的左边界小于或等于区间的右边界，说明这两个区间可以合并成一个区间，同时更新右边界的值为两个右边界中的最大值，比如[1,5],[2,3],[4,5]可以合并成[1,5]。先将[1,5],[2,3]合并成[1,5]，再合并[4,5]为[1,5]。

```java
class Solution {
    public int[][] merge(int[][] intervals) {
        List<int[]> res = new ArrayList<>();
        // 按左边界从小到大排序
        Arrays.sort(intervals, (o1, o2) -> Integer.compare(o1[0], o2[0]));
        int left = intervals[0][0];
        int right = intervals[0][1];
        for (int i = 1; i < intervals.length; i ++) {
            // 如果左边界的值小于最大右边界的值，说明区间可以合并，同时更新最大右边界的值
            if (intervals[i][0] <= right) {
                right = Math.max(right, intervals[i][1]);
            }
            // 否则，加入结果集，同时初始化left,right
            else {
                res.add(new int[]{left, right});
                left = intervals[i][0];
                right = intervals[i][1];
            }
        }
        // 加入最后一个的合并区间
        res.add(new int[]{left, right});
        return res.toArray(new int[res.size()][]);
    }
}
```

```java
class Solution {
    public static int[][] merge(int[][] intervals) {
        List<int[]> res = new ArrayList<>();
        // 按左边界从小到大排序
        Arrays.sort(intervals, (o1, o2) -> Integer.compare(o1[0], o2[0]));
        // 先加入一个区间，再判断下一个区间是否可以合并
        res.add(intervals[0]);
        for (int i = 1; i < intervals.length; i ++) {
            // 如果左边界小于或等于最大右边界，说明可以合并区间，同时更新最大右边界的值
            if (res.get(res.size() - 1)[1] >= intervals[i][0]) {
                res.get(res.size() - 1)[1] = Math.max(res.get(res.size() - 1)[1], intervals[i][1]);
            } else {
                res.add(intervals[i]);
            }
        }
        return res.toArray(new int[res.size()][]);
    }
}
```

## [48. 旋转图像](https://leetcode.cn/problems/rotate-image/)

**思路一**：通过旋转模拟，由外向内，每一次都交换四个元素的位置，直到一层元素都顺时针旋转完毕。

```java
class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // 旋转圈数
        int loop = n / 2;
        // 起始边界位置
        int start = 0;
        // 结束边界位置
        int end = n - 1;
        while (loop -- > 0) {
            for (int index = 0; start + index < end; index ++) {
                int temp = matrix[start][start + index];
                matrix[start][start + index] = matrix[end - index][start];
                matrix[end - index][start] = matrix[end][end - index];
                matrix[end][end - index] = matrix[start + index][end];
                matrix[start + index][end] = temp;
            }
            start ++;
            end --;
        }
    }
}
```

**思路二:**向将数组上下翻转，后沿着主对角线翻转，最终可以达到将数组顺时针旋转90度的效果。

```java
class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // 上下翻转
        for (int i = 0; i < n / 2; i ++) {
            for (int j = 0; j < n; j ++) {
                int temp = matrix[n - 1 - i][j];
                matrix[n - 1 - i][j] = matrix[i][j];
                matrix[i][j] = temp;
            }
        }
        // 沿着主对角线翻转
        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < i; j ++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }
}
```

