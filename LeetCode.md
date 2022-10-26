# [77. 组合](https://leetcode.cn/problems/combinations/)

**解题思路：**

直接用回溯法列举出所有组合，取出满足条件的组合，将结果返回。

![image-20220920001253090](C:\Users\wcx\AppData\Roaming\Typora\typora-user-images\image-20220920001253090.png)

> 图中红色为剪枝操作

```java
class Solution {
    // res用来存放找到的所有k个数的组合
    private List<List<Integer>> res = new ArrayList<>();
    // path用来存放中间结果
    private List<Integer> path = new ArrayList<>();
    public List<List<Integer>> combine(int n, int k) {
        backtracking(n, k, 1);
        return res;
    }
    private void backtracking(int n, int k, int index) {
        // 当path.size()==k时，说明找到了一个k个数的组合
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }
        // 剪枝优化，当剩下的数和path中的数的总数量不足k个时，无需再遍历
        for (int i = index; i <= n - (k - path.size()) + 1; i ++) {
            // 处理当前数字
            path.add(i);
            // 递归
            backtracking(n, k, i + 1);
            // 回溯，撤销对上一个数字的处理
            path.remove(path.size() - 1);
        }
    }
}
```

# [216. 组合总和 III](https://leetcode.cn/problems/combination-sum-iii/)

**解题思路：**

回溯法，列出所有1到9中k个数的组合，并找出满足其组合的和等于n的结果放入结果集中。

```java
class Solution {
    // res存放结果集
    private List<List<Integer>> res = new ArrayList<>();
    // path存放中间结果
    private List<Integer> path = new ArrayList<>();
    public List<List<Integer>> combinationSum3(int k, int n) {
        backtracking(n, 0, k, 1);
        return res;
    }
    private void backtracking(int target, int sum, int k, int index) {
        // 如果path中有k个数
        if (path.size() == k) {
            // 判断这k个数的和是否等于target
            if (sum == target) {
                res.add(new ArrayList<>(path));
            }
            // k个数的和不等于target时，直接返回
            return;
        }
        // 剪枝操作, 剩下的数字和path中的数的总数量不足k个时，后面遍历无意义
        for (int i = index; i <= 9 - (k - path.size()) + 1; i ++) {
            sum += i;
            path.add(i);
            backtracking(target, sum, k, i + 1);
            // 回溯，撤销之前的处理
            sum -= i;
            path.remove(path.size() - 1);
        }
    }
}
```

# [17. 电话号码的字母组合](https://leetcode.cn/problems/letter-combinations-of-a-phone-number/)

**解题思路：**

回溯法，用res存放结果，因为中间结果涉及到字符串的增删，所有中间结果用StringBuilder类，注意这个是要在每个数字代表的字符串中取一个字符（是在不同集合中各取一个元素，可以用一个变量index代表递归层数），数字和字符串之间的映射可以通过一个字符数组来实现。

![image-20220920110127344](C:\Users\wcx\AppData\Roaming\Typora\typora-user-images\image-20220920110127344.png)

```java
class Solution {
    // 用res存放结果集
    private List<String> res = new ArrayList<>();
    // 用StringBuilder类来存放中间字母组合
    private StringBuilder sb = new StringBuilder();
    public List<String> letterCombinations(String digits){
        if (digits == null || digits.length() == 0) {
            return res;
        }
        // 判断输入是否合法
        for (int i = 0; i < digits.length(); i++) {
            if (digits.charAt(i) < '2' || digits.charAt(i) > '9') {
                 System.out.println("输入错误");
                 System.exit(1);
            }
        }
        String[] str = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        backtracking(digits, str, 0);
        return res;
    }
    private void backtracking(String digits, String[] str, int index) {
        // index表示递归层数，如果index==digits.length(),说明已经从index个数字代表的不同集合中各取出一个字符
        if (index == digits.length()) {
            res.add(sb.toString());
            return;
        }
        // s为当前数字代表的字符串
        // 比如ditgits="23",index = 0时,s = str[2] = "abc"
        String s = str[digits.charAt(index) - '0'];
        for (int i = 0; i < s.length(); i ++) {
            sb.append(s.charAt(i));
            // 递归
            backtracking(digits, str, index + 1);
            // 回溯，撤销上次处理的字符
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
```

# [39. 组合总和](https://leetcode.cn/problems/combination-sum/)

**解题思路：**

回溯法，因为数组中的数字可以重复选取，所以下一次递归中循环的开始下标为index，而不是index + 1，递归结束条件是sum >= tartget，但因为数组中的元素都是正整数，可以先将数组中的元素按从小到大的顺序排列，当sum + nums[i] > target时，就没必要再遍历，此时递归结束条件可以是sum == target。

![image-20220921165741668](C:\Users\wcx\AppData\Roaming\Typora\typora-user-images\image-20220921165741668.png)

```java
class Solution {
    // res存放结果集
    private List<List<Integer>> res = new ArrayList<>();
    // path存放中间结果
    private List<Integer> path = new ArrayList<>(); 
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates.length == 0 || candidates == null) {
            return res;
        }
        // 对candidates数组排序，方便后面剪枝优化
        Arrays.sort(candidates);
        backtracking(candidates, target, 0, 0);
        return res;
    }
    private void backtracking(int[] nums, int target, int sum, int index) {
        // 当前和等于目标和，终止程序
        if (sum == target) {
            res.add(new ArrayList<>(path));
            return;
        }
        // 剪枝优化，当sum + nums[i] > target时，后面的遍历无意义，因为nums数组中无负数
        for (int i = index; i < nums.length && sum + nums[i] <= target; i ++) {
            sum += nums[i];
            path.add(nums[i]);
            // 递归，注意是i，而不是i + 1，表明可以重复处理当前数字
            backtracking(nums, target, sum, i);
            // 回溯，撤销处理
            sum -= nums[i];
            path.remove(path.size() - 1);
        }
    }
}
```

# [40. 组合总和 II](https://leetcode.cn/problems/combination-sum-ii/)

**解题思路：**

回溯法，**难点：**数组中有重复的元素，但结果集中要求不能有重复的组合，注意数组中的每个元素只能使用1次。**可以用一个used[]的布尔数组来判断同一次递归中的元素是否被使用来解决问题，或者在循环中直接判断if(i > index && nums[i] == nums[i -1])**，如果为真，说明同一次遍历中使用了大小相等的元素，需要跳过。需要先将原数组排序以便剪枝优化和去重

```java
class Solution {
    private List<List<Integer>> res = new ArrayList<>();
    private List<Integer> path = new ArrayList<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) {
            return res;
        }
        // 先对数组进行排序，方便后面剪枝优化和去重
        Arrays.sort(candidates);
        backtracking(candidates, target, 0, 0);
        return res;
    }
    private void backtracking(int[] nums, int target, int sum, int index) {
        // 当前和等于目标和，终止
        if (sum == target) {
            res.add(new ArrayList<>(path));
            return;
        }
        // 剪枝优化, 如果后面的和已经大于目标和，因为是正整数，没必要再遍历
        for (int i = index; i < nums.length && nums[i] + sum <= target; i ++) {
            //避免相等的元素在同一次遍历中被选多次
            if (i > index && nums[i] == nums[i - 1]) {
                continue;
            }
            path.add(nums[i]);
            // 递归 + 回溯，注意是i + 1，说明同一个元素不能被重复选
            backtracking(nums, target, sum + nums[i], i + 1);
            path.remove(path.size() - 1);
        }
    }
}
```

```java
class Solution {
    private List<List<Integer>> res = new ArrayList<>();
    private List<Integer> path = new ArrayList<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) {
            return res;
        }
        // 先对数组进行排序，方便后面剪枝优化和去重
        Arrays.sort(candidates);
        // 声明一个used[]布尔数组，判断这个元素是否在同一次递归中使用
        boolean[] used = new boolean[candidates.length];
        backtracking(candidates, target, 0, 0, used);
        return res;
    }
    private void backtracking(int[] nums, int target, int sum, int index, boolean[] used) {
        // 当前和等于目标和，终止
        if (sum == target) {
            res.add(new ArrayList<>(path));
            return;
        }
        // 剪枝优化, 如果后面的和已经大于目标和，因为是正整数，没必要再遍历
        for (int i = index; i < nums.length && nums[i] + sum <= target; i ++) {
            // 说明nums[i - 1]这次在一次遍历中使用，当nums[i]==nums[i-1]，不能使用
            // 否则会有重复的组合
            if (i > 0 && nums[i] == nums[i - 1] && used[i - 1] == false) {
                continue;
            }
            used[i] = true;
            path.add(nums[i]);
            // 递归 + 回溯，注意是i + 1，说明同一个元素不能被重复选
            backtracking(nums, target, sum + nums[i], i + 1, used);
            used[i] = false;
            path.remove(path.size() - 1);
        }
    }
}
```

# [131. 分割回文串](https://leetcode.cn/problems/palindrome-partitioning/)

**解题思路：**

回溯法，列举每次开始时字符串的截取位置，如果截取的子串是回文子串，就把它加入到结果集中，当开始截取位置超过s的长度，就找到了一组结果，加入到res，然后起始截取位置加1，开始递归寻找，没有找到的话，就回溯，撤销上一次处理，继续寻找。

```java
class Solution {
    private List<List<String>> res = new ArrayList<>();
    // 存放以填入的回文子串
    private List<String> path = new ArrayList<>(); 
    public List<List<String>> partition(String s) {
        if (s == null || s.length() == 0) {
            return res;
        }
        backtracking(s, 0);
        return res;
    }
    private void backtracking(String s, int index) {
        // 如果截取的位置已经超过s的长度，说明找到了一组结果
        if (index >= s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }
        // index代表切割的起始位置，为了防止重复切割
        for (int i = index; i < s.length(); i ++) {
            // 如果切割的子串是回文子串，就把它加入到path
            if (isPalindromes(s, index, i)) {
                String str = s.substring(index, i + 1);
                path.add(str);
            } else {
                continue;
            }
            // 寻找起始位置为i + 1的字串
            backtracking(s, i + 1);
            // 回溯，撤销上一次加入的回文子串
            path.remove(path.size() - 1);
        }
    }
    private boolean isPalindromes(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start ++;
            end --;
        }
        return true;
    }
}
```

# [93. 复原 IP 地址](https://leetcode.cn/problems/restore-ip-addresses/)

**解题思路：**解题思考和分割回文串差不多，需要改一下终止的判断条件和剪枝的条件。

```java
class Solution {
    // res存放结果集
    private List<String> res = new ArrayList<>();
    // 因为涉及到对字符串的增删操作，用StingBuilder类存放中间结果
    private StringBuilder sb = new StringBuilder();
    public List<String> restoreIpAddresses(String s) {
        if (s == null) {
            System.out.println("字符串s指向空指针");
            System.exit(1);
        }
        if (s.length() > 12 || s.length() < 4) {
            return res;
        }
        backtracking(s, 0, 0);
        return res;
    }
    // 其中index表示开始截取的位置，ipNum表示有效ip段的数目
    private void backtracking(String s, int index, int ipNum) {
        // 如果有四段ip并且开始截取位置到s的末尾，sb加入res
        if (ipNum == 4 && index == s.length()) {
            res.add(sb.toString());
            return;
        }
        // 如果sb中有4个有效ip段但index还未到s的末尾或者起始截至位置已经到s的末尾但还未找到4段有效ip段，直接返回
        if (ipNum == 4 || index == s.length()) {
            return;
        }
        // ip段中的长度小于4，且值在[0, 255]
        for (int i = index; i < s.length() && i - index + 1 <= 3 && Integer.valueOf(s.substring(index, i + 1)) >= 0
                    && Integer.valueOf(s.substring(index, i + 1)) <= 255; i ++) {
            // 比如024，这样是无效ip段
            if (i - index + 1 > 1 && s.charAt(index) == '0') {
                continue;
            }
            //将有效ip段拼接到sb末尾
            sb.append(s.substring(index, i + 1));
            // 如果有效ip段的数量等于3，说明已经有3段ip，最后一段不需要加，否则就变成0.0.0.0.的形式了
            if (ipNum < 3) {
                sb.append(".");
            }
            // 递归 + 回溯（对ipNum的回溯处理）
            backtracking(s, i + 1, ipNum + 1);
            // 删除sb的最后一个ip段，进行回溯处理
            sb.delete(index + ipNum, i + ipNum + 2);
        }
    }
}
```

# [78. 子集](https://leetcode.cn/problems/subsets/)

**解题思路：**

本题要求一个数组的所有子集，回溯法，只需把递归过程中的涉及到的结果都加入到结果集中就行。

```java
class Solution {
    private List<List<Integer>> res = new ArrayList<>();
    private List<Integer> path = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        if (nums.length == 0 || nums == null) {
            return res;
        }
        backtracking(nums, 0);
        return res;
    }
    private void backtracking(int[] nums, int startIndex) {
        // 要在递归终止前加入res,否则会漏掉自身
        res.add(new ArrayList<>(path));
        // 开始下标位置等于数组结尾，终止
        if (startIndex == nums.length) {
            return;
        }
       	for (int i = startIndex; i < nums.length; i ++) {
            path.add(nums[i]);
            // 开始下标+1，数组中的元素不能重复选取
            backtracking(nums, i + 1);
            // 回溯，移除上一次加入的元素
            path.remove(path.size() - 1);
            
        }
    }
}
```

# [90. 子集 II](https://leetcode.cn/problems/subsets-ii/)

**解题思路：**数组中有重复的元素，但不能重复的组合，这题还是用回溯法解题，只不过需要去重，有两种方法，法一，用一个used[]数组记录一个元素是否在一次递归中使用，如果nums[i] = nums[i -1]&&used[i - 1] == false，则跳过当前遍历，法二，直接用i > stratIndex && nums[i] == nums[i -1]来判断是否需要去重。

```java
class Solution {
    // res用来存放最终结果集
    private List<List<Integer>> res = new ArrayList<>();
    // path用来存放中间值 
    private List<Integer> path = new ArrayList<>();
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        // 对数组排序，为去重做准备
        Arrays.sort(nums);
        backtracking(nums, 0);
        return res;
    }
   	private void backtracking(int[] nums, int startIndex) {
        // 在递归终止前，加入结果集，否则会漏掉自身
        res.add(new ArrayList<>(path))
        // 开始下标位置等于数组末尾，返回
        if (startIndex == nums.length) {
            return;
        } 
        for (int i = startIndex; i < nums.length; i ++) {
            // 为真，说明有重复的集合，去重
            if (i > 0 && i > startIndex && nums[i] == nums[i - 1]) {
                continue;
            }
            path.add(nums[i]);
            // 递归
            backtracking(nums, i + 1);
            path.remove(path.size() - 1);
        }
    }
}
```

# [491. 递增子序列](https://leetcode.cn/problems/increasing-subsequences/)

**题解：**本题不能对数组排序后再进行去重，因为我们要找到递增子序列的结果集。可以有一个哈希表来判断该元素是否在同一个循环中，也就是同一层中是否重复使用来去重。

```java
class Solution {
    // 存放中间结果
    List<Integer> path = new ArrayList<>();
    // 存放所有递增子序列
	List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> findSubsequences(int[] nums) {
        if (nums == null || nums.length == 0) {
            return res;
        }
        backtracking(nums, 0);
        return res;
    }
    private void backtracking(int[] nums, int startIndex) {
        if (path.size() > 1) {
            res.add(new ArrayList<>(path));
            // 这里不需要return，否则会漏掉长度大于2的一些结果
        }
        // 用一个哈希数组来表示一个元素是否使用过
        int[] used = new int[201];
        for (int i = startIndex; i < nums.length; i ++) {
            if ((!path.isEmpty() && nums[i] < path.get(path.size() - 1)) || used[nums[i] + 100] == 1) {
                continue;
            }
            // 标记该元素已经在本层中使用过
            used[nums[i] + 100] = 1;
            path.add(nums[i]);
            // 递归
            backtracking(nums, i + 1);
            // 回溯， 撤销上一次的处理
            path.remove(path.size() - 1);
        }
    }
}
```

# [46. 全排列](https://leetcode.cn/problems/permutations/)

**题解：**可以用回溯法解决，数组中不包含重复元素，只需额外使用一个used[]数组来表示一个元素是否使用过。

![image-20220929210312694](C:\Users\wcx\AppData\Roaming\Typora\typora-user-images\image-20220929210312694.png)

```java
class Solution {
    // 存放中间结果
    private List<Integer> path = new ArrayList<>();
    // 存放结果集
    private List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) {
            return res;
        }
        boolean[] used = new boolean[nums.length];
        backtracking(nums, used);
        return res;
    }
    private void backtracking(int[] nums,  boolean[] used) {
        // 说明找到了一组排列，返回
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i ++) {
            // 说明这个元素已经使用
            if (used[i] == true) {
                continue;
            }
            used[i] = true;
            path.add(nums[i]);
            backtracking(nums, used);
            used[i] = false;
            path.remove(path.size() - 1);
        }
    }
}
```

# [47. 全排列 II](https://leetcode.cn/problems/permutations-ii/)

**解题思路：**数组中有重复元素，但不能有重复的排列，所以需要去重。可以用一个used[]数组来标识一个元素是否一个递归中或一次循环中使用。

![image-20220929213521824](C:\Users\wcx\AppData\Roaming\Typora\typora-user-images\image-20220929213521824.png)

```java
class Solution {
    List<Integer> path = new ArrayList<>();
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums == null || nums.length == 0) {
            return res;
        }
        // 排序，为去重做准备
        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        backtracking(nums, used);
        return res;
    }
    private void backtracking(int[] nums, boolean[] used) {
        // 说明找到一组排列
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
       	for (int i = 0; i < nums.length; i ++) {
            // 对同一层去重
            /**
                也可以对同一循环中的递归去重
                if(i > 0 && nums[i] == nums[i - 1] && used[i - 1] == true)
                可以自己画树来理解递归回溯的过程
            */
            if (i > 0 && nums[i] == nums[i - 1] && used[i - 1] == false) {
                continue;
            }
            // 这个元素还没有使用过
            if (used[i] == false) {
                used[i] = true;
                path.add(nums[i]);
                // 递归
                backtracking(nums, used);
                used[i] = false;
                path.remove(path.size() - 1);
            }
        }
    }
}
```

