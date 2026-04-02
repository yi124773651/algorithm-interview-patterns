# 程序员面试算法设计题 — 模式识别速查手册

> **核心理念**: 拒绝题海战术，建立「题目特征 → 解题模式」的条件反射。每个模式只吃透1~2道经典题，以不变应万变。

---

## 一、题目特征 → 解题模式 快速索引表

```
┌─────────────────────────────────────────────────────────────────────────┐
│                    题目特征 → 模式 速查决策树                            │
├─────────────────────────────────────────────────────────────────────────┤
│                                                                         │
│  题目要求是什么？                                                        │
│  │                                                                      │
│  ├─ 连续子数组/子串 的最值或满足条件 ──────→ 【滑动窗口】                  │
│  ├─ 有序数组/链表 中找配对/目标值 ─────────→ 【双指针】                    │
│  ├─ 链表中检测环/找中点 ──────────────────→ 【快慢指针】                   │
│  ├─ 区间重叠/合并/插入 ──────────────────→ 【合并区间】                    │
│  ├─ 1~N范围数组找缺失/重复 ──────────────→ 【循环排序】                   │
│  ├─ 树/图的逐层处理 ─────────────────────→ 【BFS】                       │
│  ├─ 树/图的路径/回溯/连通性 ─────────────→ 【DFS/回溯】                   │
│  ├─ 排列/组合/子集枚举 ─────────────────→ 【回溯法】                      │
│  ├─ 有序/旋转数组查找 ──────────────────→ 【二分查找】                     │
│  ├─ 前K大/小/频率最高 ──────────────────→ 【堆(TopK)】                    │
│  ├─ 数据流中位数/动态最大最小 ──────────→ 【双堆】                        │
│  ├─ 多个有序列表归并 ───────────────────→ 【K路归并】                      │
│  ├─ 选/不选决策 + 容量/条件约束 ────────→ 【动态规划(背包)】               │
│  ├─ 最优子结构 + 重叠子问题 ────────────→ 【动态规划】                     │
│  ├─ 每步取局部最优即全局最优 ────────────→ 【贪心】                        │
│  ├─ 依赖关系/执行顺序 ─────────────────→ 【拓扑排序】                     │
│  ├─ 集合合并/连通分量 ─────────────────→ 【并查集】                       │
│  ├─ 区间批量加减操作 ──────────────────→ 【差分数组】                      │
│  ├─ 区间和/前缀统计 ───────────────────→ 【前缀和】                       │
│  └─ 括号匹配/表达式求值/单调性 ────────→ 【栈/单调栈】                    │
│                                                                         │
└─────────────────────────────────────────────────────────────────────────┘
```

### 更精细的特征关键词识别

| 看到这些关键词/特征 | 立刻想到这个模式 | 复杂度提示 |
|:---|:---|:---|
| "连续子数组"、"窗口大小为K"、"最长/最短子串满足..." | **滑动窗口** | O(n) |
| "有序数组"、"两数之和"、"三数之和"、"接雨水" | **双指针** | O(n) |
| "链表是否有环"、"链表中间节点"、"快乐数" | **快慢指针** | O(n) |
| "会议室"、"区间合并"、"插入区间" | **合并区间** | O(nlogn) |
| "1到N的数组"、"缺失的数字"、"找重复" | **循环排序** | O(n) |
| "层序遍历"、"最短路径"、"最近的X"、"矩阵搜索" | **BFS** | O(V+E) |
| "所有路径"、"岛屿数量"、"连通分量"、"迷宫" | **DFS** | O(V+E) |
| "所有排列"、"所有组合"、"所有子集"、"N皇后" | **回溯法** | O(2^n)或O(n!) |
| "有序数组查找"、"旋转数组"、"第一个/最后一个位置" | **二分查找** | O(logn) |
| "前K个"、"第K大"、"频率排序" | **堆(TopK)** | O(nlogk) |
| "数据流中位数" | **双堆** | O(logn) |
| "背包"、"凑硬币"、"选/不选"、"容量限制" | **DP-背包** | O(n*W) |
| "最长递增子序列"、"编辑距离"、"最长公共子序列" | **DP-序列** | O(n²)或O(nlogn) |
| "路径数"、"最小路径和"、"网格DP" | **DP-路径** | O(m*n) |
| "任务调度"、"跳跃游戏"、"分发糖果" | **贪心** | O(nlogn) |
| "课程表"、"编译顺序"、"依赖关系" | **拓扑排序** | O(V+E) |
| "朋友圈"、"连通网络"、"最小生成树" | **并查集** | O(α(n)) |
| "子数组和等于K"、"区间和查询" | **前缀和** | O(n) |
| "括号匹配"、"下一个更大元素"、"柱状图最大矩形" | **栈/单调栈** | O(n) |

---

## 二、每个模式的经典题精讲（1~2道吃透）

> 策略：每个模式选最经典的1~2道题，理解**为什么用这个模式**、**模板是什么**、**如何变形**。

---

### 模式1: 滑动窗口

**核心思想**: 维护一个窗口在数组/字符串上滑动，避免重复计算。

**识别信号**: 连续子数组/子串 + 最值/满足某条件

#### 经典题: 无重复字符的最长子串 (LeetCode 3)

```
题目: 给定字符串s，找出不含重复字符的最长子串的长度。
输入: "abcabcbb"  输出: 3 (abc)
```

**解题模板 (Python)**:
```python
def lengthOfLongestSubstring(s: str) -> int:
    char_set = set()
    left = 0
    max_len = 0

    for right in range(len(s)):
        # 收缩窗口：当窗口内有重复时，移动左边界
        while s[right] in char_set:
            char_set.remove(s[left])
            left += 1
        # 扩展窗口
        char_set.add(s[right])
        max_len = max(max_len, right - left + 1)

    return max_len
```

**解题模板 (Java)**:
```java
public int lengthOfLongestSubstring(String s) {
    Set<Character> charSet = new HashSet<>();
    int left = 0, maxLen = 0;

    for (int right = 0; right < s.length(); right++) {
        // 收缩窗口：当窗口内有重复时，移动左边界
        while (charSet.contains(s.charAt(right))) {
            charSet.remove(s.charAt(left));
            left++;
        }
        // 扩展窗口
        charSet.add(s.charAt(right));
        maxLen = Math.max(maxLen, right - left + 1);
    }
    return maxLen;
}
```

**滑动窗口万能模板 (Python)**:
```python
def sliding_window(s):
    window = {}          # 窗口内的状态
    left = 0
    result = 0

    for right in range(len(s)):
        # 1. 扩大窗口，更新状态
        c = s[right]
        window[c] = window.get(c, 0) + 1

        # 2. 判断是否需要收缩（条件根据题意定）
        while 窗口需要收缩:
            d = s[left]
            window[d] -= 1
            left += 1

        # 3. 更新结果
        result = max(result, right - left + 1)

    return result
```

**滑动窗口万能模板 (Java)**:
```java
public int slidingWindow(String s) {
    Map<Character, Integer> window = new HashMap<>();  // 窗口内的状态
    int left = 0, result = 0;

    for (int right = 0; right < s.length(); right++) {
        // 1. 扩大窗口，更新状态
        char c = s.charAt(right);
        window.merge(c, 1, Integer::sum);

        // 2. 判断是否需要收缩（条件根据题意定）
        while (窗口需要收缩) {
            char d = s.charAt(left);
            window.merge(d, -1, Integer::sum);
            left++;
        }

        // 3. 更新结果
        result = Math.max(result, right - left + 1);
    }
    return result;
}
```

**华为OD真题映射**: 「补种未成活胡杨」(100分)、「观看文艺汇演问题」(200分)

**变形方向**: 固定窗口大小 → 直接维护窗口; 可变窗口 → while收缩

---

### 模式2: 双指针

**核心思想**: 两个指针协同移动，减少嵌套循环。

**识别信号**: 有序数组 + 配对/目标值 + 原地操作

#### 经典题: 三数之和 (LeetCode 15)

```
题目: 找出数组中所有和为0的三元组，不重复。
输入: [-1,0,1,2,-1,-4]  输出: [[-1,-1,2],[-1,0,1]]
```

**解法 (Python)**:
```python
def threeSum(nums: list) -> list:
    nums.sort()
    result = []

    for i in range(len(nums) - 2):
        if i > 0 and nums[i] == nums[i-1]:  # 跳过重复
            continue
        left, right = i + 1, len(nums) - 1

        while left < right:
            total = nums[i] + nums[left] + nums[right]
            if total < 0:
                left += 1
            elif total > 0:
                right -= 1
            else:
                result.append([nums[i], nums[left], nums[right]])
                while left < right and nums[left] == nums[left+1]: left += 1
                while left < right and nums[right] == nums[right-1]: right -= 1
                left += 1
                right -= 1

    return result
```

**解法 (Java)**:
```java
public List<List<Integer>> threeSum(int[] nums) {
    Arrays.sort(nums);
    List<List<Integer>> result = new ArrayList<>();

    for (int i = 0; i < nums.length - 2; i++) {
        if (i > 0 && nums[i] == nums[i - 1]) continue;  // 跳过重复
        int left = i + 1, right = nums.length - 1;

        while (left < right) {
            int total = nums[i] + nums[left] + nums[right];
            if (total < 0) {
                left++;
            } else if (total > 0) {
                right--;
            } else {
                result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                while (left < right && nums[left] == nums[left + 1]) left++;
                while (left < right && nums[right] == nums[right - 1]) right--;
                left++;
                right--;
            }
        }
    }
    return result;
}
```

**华为OD真题映射**: 「太阳能板最大面积」(100分)、「水库蓄水问题」(100分)

---

### 模式3: BFS (广度优先搜索)

**核心思想**: 逐层扩展，天然适合求最短路径/最少步数。

**识别信号**: 最短路径、最少步数、层序遍历、矩阵搜索

#### 经典题: 二叉树层序遍历 (LeetCode 102) + 岛屿数量 (LeetCode 200)

**BFS模板 (Python)**:
```python
# BFS模板 — 层序遍历
from collections import deque

def bfs_level_order(root):
    if not root: return []
    result = []
    queue = deque([root])

    while queue:
        level_size = len(queue)   # 关键：记录当前层节点数
        level = []
        for _ in range(level_size):
            node = queue.popleft()
            level.append(node.val)
            if node.left:  queue.append(node.left)
            if node.right: queue.append(node.right)
        result.append(level)

    return result

# BFS模板 — 矩阵最短路径
def bfs_matrix(grid, start, end):
    rows, cols = len(grid), len(grid[0])
    queue = deque([(start[0], start[1], 0)])  # (row, col, distance)
    visited = set()
    visited.add((start[0], start[1]))

    while queue:
        r, c, dist = queue.popleft()
        if (r, c) == end:
            return dist
        for dr, dc in [(0,1),(0,-1),(1,0),(-1,0)]:
            nr, nc = r + dr, c + dc
            if 0 <= nr < rows and 0 <= nc < cols and (nr,nc) not in visited and grid[nr][nc] != 障碍:
                visited.add((nr, nc))
                queue.append((nr, nc, dist + 1))

    return -1
```

**BFS模板 (Java)**:
```java
// BFS模板 — 层序遍历
public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) return result;
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);

    while (!queue.isEmpty()) {
        int levelSize = queue.size();   // 关键：记录当前层节点数
        List<Integer> level = new ArrayList<>();
        for (int i = 0; i < levelSize; i++) {
            TreeNode node = queue.poll();
            level.add(node.val);
            if (node.left != null)  queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
        result.add(level);
    }
    return result;
}

// BFS模板 — 矩阵最短路径
public int bfsMatrix(int[][] grid, int[] start, int[] end) {
    int rows = grid.length, cols = grid[0].length;
    int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
    Queue<int[]> queue = new LinkedList<>();
    boolean[][] visited = new boolean[rows][cols];

    queue.offer(new int[]{start[0], start[1], 0});  // {row, col, distance}
    visited[start[0]][start[1]] = true;

    while (!queue.isEmpty()) {
        int[] curr = queue.poll();
        int r = curr[0], c = curr[1], dist = curr[2];
        if (r == end[0] && c == end[1]) return dist;

        for (int[] d : dirs) {
            int nr = r + d[0], nc = c + d[1];
            if (nr >= 0 && nr < rows && nc >= 0 && nc < cols
                    && !visited[nr][nc] && grid[nr][nc] != 1/*障碍*/) {
                visited[nr][nc] = true;
                queue.offer(new int[]{nr, nc, dist + 1});
            }
        }
    }
    return -1;
}
```

**华为OD真题映射**: 「上班之路」(200分)、二叉树遍历类题目

---

### 模式4: DFS / 回溯

**核心思想**: 深入探索所有路径，不行就回退。

**识别信号**: 所有方案、排列组合、路径枚举、连通性

#### 经典题: 全排列 (LeetCode 46)

**解法 (Python)**:
```python
def permute(nums: list) -> list:
    result = []

    def backtrack(path, remaining):
        if not remaining:
            result.append(path[:])  # 收集结果
            return
        for i in range(len(remaining)):
            path.append(remaining[i])                    # 做选择
            backtrack(path, remaining[:i] + remaining[i+1:])  # 递归
            path.pop()                                    # 撤销选择

    backtrack([], nums)
    return result
```

**解法 (Java)**:
```java
public List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    backtrack(result, new ArrayList<>(), nums, new boolean[nums.length]);
    return result;
}

private void backtrack(List<List<Integer>> result, List<Integer> path,
                       int[] nums, boolean[] used) {
    if (path.size() == nums.length) {
        result.add(new ArrayList<>(path));  // 收集结果
        return;
    }
    for (int i = 0; i < nums.length; i++) {
        if (used[i]) continue;
        path.add(nums[i]);                  // 做选择
        used[i] = true;
        backtrack(result, path, nums, used); // 递归
        path.remove(path.size() - 1);       // 撤销选择
        used[i] = false;
    }
}
```

**回溯法万能模板 (Python)**:
```python
def backtrack(路径, 选择列表):
    if 满足结束条件:
        result.append(路径的拷贝)
        return

    for 选择 in 选择列表:
        if 选择不合法: continue     # 剪枝
        做选择（将选择加入路径）
        backtrack(路径, 新的选择列表)
        撤销选择（将选择从路径移除）
```

**回溯法万能模板 (Java)**:
```java
void backtrack(List<Integer> path, int[] choices) {
    if (满足结束条件) {
        result.add(new ArrayList<>(path));
        return;
    }

    for (int i = 0; i < choices.length; i++) {
        if (选择不合法) continue;       // 剪枝
        path.add(choices[i]);           // 做选择
        backtrack(path, 新的选择列表);   // 递归
        path.remove(path.size() - 1);   // 撤销选择
    }
}
```

**华为OD真题映射**: 「篮球比赛」(200分)、「分披萨」(100分)、「检查是否存在满足条件的数字组合」(100分)

---

### 模式5: 动态规划

**核心思想**: 将大问题拆成子问题，记住子问题的解，避免重复计算。

**识别信号**: 最优解(最大/最小/最长/最短) + 可分解为子问题 + 有重叠子问题

#### 经典题①: 0-1背包 (华为OD高频)

```
题目: N个物品，每个有重量w[i]和价值v[i]，背包容量W，求最大价值。
```

**解法 (Python)**:
```python
def knapsack(W, weights, values):
    n = len(weights)
    # dp[j] = 容量为j时的最大价值
    dp = [0] * (W + 1)

    for i in range(n):
        # 逆序遍历！保证每个物品只用一次
        for j in range(W, weights[i] - 1, -1):
            dp[j] = max(dp[j], dp[j - weights[i]] + values[i])

    return dp[W]
```

**解法 (Java)**:
```java
public int knapsack(int W, int[] weights, int[] values) {
    int n = weights.length;
    // dp[j] = 容量为j时的最大价值
    int[] dp = new int[W + 1];

    for (int i = 0; i < n; i++) {
        // 逆序遍历！保证每个物品只用一次
        for (int j = W; j >= weights[i]; j--) {
            dp[j] = Math.max(dp[j], dp[j - weights[i]] + values[i]);
        }
    }
    return dp[W];
}
```

#### 经典题②: 最长递增子序列 LIS (LeetCode 300)

**解法 (Python)**:
```python
def lengthOfLIS(nums: list) -> int:
    if not nums: return 0
    dp = [1] * len(nums)  # dp[i] = 以nums[i]结尾的LIS长度

    for i in range(1, len(nums)):
        for j in range(i):
            if nums[j] < nums[i]:
                dp[i] = max(dp[i], dp[j] + 1)

    return max(dp)
```

**解法 (Java)**:
```java
public int lengthOfLIS(int[] nums) {
    if (nums.length == 0) return 0;
    int[] dp = new int[nums.length];  // dp[i] = 以nums[i]结尾的LIS长度
    Arrays.fill(dp, 1);

    for (int i = 1; i < nums.length; i++) {
        for (int j = 0; j < i; j++) {
            if (nums[j] < nums[i]) {
                dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }
    }
    return Arrays.stream(dp).max().getAsInt();
}

// 进阶: O(nlogn) 二分查找优化版
public int lengthOfLIS_BS(int[] nums) {
    List<Integer> tails = new ArrayList<>();  // tails[i] = 长度为i+1的LIS的最小末尾

    for (int num : nums) {
        int pos = Collections.binarySearch(tails, num);
        if (pos < 0) pos = -(pos + 1);  // 插入位置
        if (pos == tails.size()) {
            tails.add(num);
        } else {
            tails.set(pos, num);
        }
    }
    return tails.size();
}
```

**DP解题四步法**:
```
1. 定义状态: dp[i] 代表什么？
2. 状态转移方程: dp[i] 由哪些 dp[j] 推导而来？
3. 初始条件: dp[0] 或 dp[0][0] 是什么？
4. 遍历顺序: 从小到大？从大到小？
```

**华为OD真题映射**: 「跳格子3」(200分)、「执行任务赚积分」(100分)、「代表团坐车」(200分)

---

### 模式6: 贪心

**核心思想**: 每一步选当前最优，希望全局最优。

**识别信号**: 每步有明显的"最优选择" + 局部最优能推出全局最优

#### 经典题: 区间调度/会议室 (LeetCode 435 无重叠区间)

**解法 (Python)**:
```python
def eraseOverlapIntervals(intervals: list) -> int:
    intervals.sort(key=lambda x: x[1])  # 按结束时间排序！
    count = 0
    end = float('-inf')

    for interval in intervals:
        if interval[0] >= end:   # 不重叠，保留
            end = interval[1]
        else:                     # 重叠，移除
            count += 1

    return count
```

**解法 (Java)**:
```java
public int eraseOverlapIntervals(int[][] intervals) {
    Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));  // 按结束时间排序！
    int count = 0;
    int end = Integer.MIN_VALUE;

    for (int[] interval : intervals) {
        if (interval[0] >= end) {   // 不重叠，保留
            end = interval[1];
        } else {                     // 重叠，移除
            count++;
        }
    }
    return count;
}
```

**贪心 vs DP 判断**: 如果每一步的选择不影响后续子问题的最优解 → 贪心; 否则 → DP

**华为OD真题映射**: 「田忌赛马」(100分)、「任务处理/可以处理的最大任务数」(100分)

---

### 模式7: 二分查找

**核心思想**: 每次排除一半搜索空间。

**识别信号**: 有序 + 查找 + O(logn)要求

#### 经典题: 在排序数组中查找元素的第一个和最后一个位置 (LeetCode 34)

**解法 (Python)**:
```python
def searchRange(nums, target):
    def find_left():
        lo, hi = 0, len(nums) - 1
        while lo <= hi:
            mid = (lo + hi) // 2
            if nums[mid] < target:
                lo = mid + 1
            else:
                hi = mid - 1
        return lo

    def find_right():
        lo, hi = 0, len(nums) - 1
        while lo <= hi:
            mid = (lo + hi) // 2
            if nums[mid] <= target:
                lo = mid + 1
            else:
                hi = mid - 1
        return hi

    left, right = find_left(), find_right()
    if left <= right:
        return [left, right]
    return [-1, -1]
```

**解法 (Java)**:
```java
public int[] searchRange(int[] nums, int target) {
    int left = findLeft(nums, target);
    int right = findRight(nums, target);
    if (left <= right) return new int[]{left, right};
    return new int[]{-1, -1};
}

private int findLeft(int[] nums, int target) {
    int lo = 0, hi = nums.length - 1;
    while (lo <= hi) {
        int mid = lo + (hi - lo) / 2;  // 防溢出写法
        if (nums[mid] < target) lo = mid + 1;
        else hi = mid - 1;
    }
    return lo;
}

private int findRight(int[] nums, int target) {
    int lo = 0, hi = nums.length - 1;
    while (lo <= hi) {
        int mid = lo + (hi - lo) / 2;  // 防溢出写法
        if (nums[mid] <= target) lo = mid + 1;
        else hi = mid - 1;
    }
    return hi;
}
```

**二分查找三种边界模板**:
- 找确切值: `nums[mid] == target → return`
- 找左边界: `nums[mid] >= target → hi = mid - 1`, 返回 `lo`
- 找右边界: `nums[mid] <= target → lo = mid + 1`, 返回 `hi`

---

### 模式8: 栈 / 单调栈

**核心思想**: 后进先出，维护单调性。

**识别信号**: 括号匹配、表达式求值、下一个更大/更小元素

#### 经典题: 每日温度 (LeetCode 739) — 单调栈

**解法 (Python)**:
```python
def dailyTemperatures(temperatures: list) -> list:
    n = len(temperatures)
    result = [0] * n
    stack = []  # 存索引，栈内温度单调递减

    for i in range(n):
        while stack and temperatures[i] > temperatures[stack[-1]]:
            prev = stack.pop()
            result[prev] = i - prev
        stack.append(i)

    return result
```

**解法 (Java)**:
```java
public int[] dailyTemperatures(int[] temperatures) {
    int n = temperatures.length;
    int[] result = new int[n];
    Deque<Integer> stack = new ArrayDeque<>();  // 存索引，栈内温度单调递减

    for (int i = 0; i < n; i++) {
        while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
            int prev = stack.pop();
            result[prev] = i - prev;
        }
        stack.push(i);
    }
    return result;
}
```

**华为OD真题映射**: 「模拟目录管理」、「括号子串反转」

---

### 模式9: 前缀和 / 差分

**核心思想**: 预处理累加，O(1)求区间和。

#### 经典题: 和为K的子数组 (LeetCode 560)

**解法 (Python)**:
```python
def subarraySum(nums: list, k: int) -> int:
    prefix_count = {0: 1}  # 前缀和 → 出现次数
    prefix_sum = 0
    count = 0

    for num in nums:
        prefix_sum += num
        # 如果 prefix_sum - k 出现过，说明存在和为k的子数组
        count += prefix_count.get(prefix_sum - k, 0)
        prefix_count[prefix_sum] = prefix_count.get(prefix_sum, 0) + 1

    return count
```

**解法 (Java)**:
```java
public int subarraySum(int[] nums, int k) {
    Map<Integer, Integer> prefixCount = new HashMap<>();  // 前缀和 → 出现次数
    prefixCount.put(0, 1);
    int prefixSum = 0, count = 0;

    for (int num : nums) {
        prefixSum += num;
        // 如果 prefixSum - k 出现过，说明存在和为k的子数组
        count += prefixCount.getOrDefault(prefixSum - k, 0);
        prefixCount.merge(prefixSum, 1, Integer::sum);
    }
    return count;
}
```

---

### 模式10: 并查集

**核心思想**: 高效管理集合的合并与查询。

**识别信号**: 连通性、分组、朋友圈、网络连通

#### 经典题: 朋友圈/省份数量 (LeetCode 547)

**解法 (Python)**:
```python
class UnionFind:
    def __init__(self, n):
        self.parent = list(range(n))
        self.rank = [0] * n
        self.count = n  # 连通分量数

    def find(self, x):
        if self.parent[x] != x:
            self.parent[x] = self.find(self.parent[x])  # 路径压缩
        return self.parent[x]

    def union(self, x, y):
        px, py = self.find(x), self.find(y)
        if px == py: return
        # 按秩合并
        if self.rank[px] < self.rank[py]: px, py = py, px
        self.parent[py] = px
        if self.rank[px] == self.rank[py]: self.rank[px] += 1
        self.count -= 1
```

**解法 (Java)**:
```java
class UnionFind {
    private int[] parent;
    private int[] rank;
    private int count;  // 连通分量数

    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        count = n;
        for (int i = 0; i < n; i++) parent[i] = i;
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);  // 路径压缩
        }
        return parent[x];
    }

    public void union(int x, int y) {
        int px = find(x), py = find(y);
        if (px == py) return;
        // 按秩合并
        if (rank[px] < rank[py]) { int tmp = px; px = py; py = tmp; }
        parent[py] = px;
        if (rank[px] == rank[py]) rank[px]++;
        count--;
    }

    public int getCount() { return count; }
}
```

**华为OD真题映射**: 「快递业务站」(200分)、「需要广播的服务器数量」(200分)

---

### 模式11: 拓扑排序

**核心思想**: 处理有向无环图中的依赖顺序。

#### 经典题: 课程表 (LeetCode 207)

**解法 (Python)**:
```python
from collections import deque

def canFinish(numCourses, prerequisites):
    graph = [[] for _ in range(numCourses)]
    in_degree = [0] * numCourses

    for dest, src in prerequisites:
        graph[src].append(dest)
        in_degree[dest] += 1

    queue = deque([i for i in range(numCourses) if in_degree[i] == 0])
    count = 0

    while queue:
        node = queue.popleft()
        count += 1
        for neighbor in graph[node]:
            in_degree[neighbor] -= 1
            if in_degree[neighbor] == 0:
                queue.append(neighbor)

    return count == numCourses
```

**解法 (Java)**:
```java
public boolean canFinish(int numCourses, int[][] prerequisites) {
    List<List<Integer>> graph = new ArrayList<>();
    int[] inDegree = new int[numCourses];

    for (int i = 0; i < numCourses; i++) graph.add(new ArrayList<>());

    for (int[] pre : prerequisites) {
        graph.get(pre[1]).add(pre[0]);
        inDegree[pre[0]]++;
    }

    Queue<Integer> queue = new LinkedList<>();
    for (int i = 0; i < numCourses; i++) {
        if (inDegree[i] == 0) queue.offer(i);
    }

    int count = 0;
    while (!queue.isEmpty()) {
        int node = queue.poll();
        count++;
        for (int neighbor : graph.get(node)) {
            inDegree[neighbor]--;
            if (inDegree[neighbor] == 0) queue.offer(neighbor);
        }
    }
    return count == numCourses;
}
```

---

## 三、应对陌生题的万能思路

```
┌─────────────────────────────────────────────────────────────────┐
│              面对陌生题的 5 步拆解法                               │
├─────────────────────────────────────────────────────────────────┤
│                                                                   │
│  Step 1: 读题 & 画图 (2分钟)                                      │
│  ┌─────────────────────────────────┐                              │
│  │ • 用小例子手动模拟                │                              │
│  │ • 画出输入→输出的变换过程          │                              │
│  │ • 确认边界: 空输入? 单元素? 极大值? │                              │
│  └─────────────────────────────────┘                              │
│           ↓                                                       │
│  Step 2: 暴力解 (3分钟)                                           │
│  ┌─────────────────────────────────┐                              │
│  │ • 先想最笨的方法，确保正确         │                              │
│  │ • 写出来！暴力解也有分             │                              │
│  │ • 分析时间复杂度: O(n²)? O(2^n)?  │                              │
│  └─────────────────────────────────┘                              │
│           ↓                                                       │
│  Step 3: 模式匹配 (3分钟)                                         │
│  ┌─────────────────────────────────┐                              │
│  │ 问自己这些问题:                    │                              │
│  │ • 数据有序吗? → 二分/双指针        │                              │
│  │ • 求连续子数组? → 滑动窗口/前缀和   │                              │
│  │ • 求所有方案? → 回溯               │                              │
│  │ • 求最优解且可分解? → DP            │                              │
│  │ • 有依赖关系? → 拓扑排序           │                              │
│  │ • 求最短路径? → BFS               │                              │
│  │ • 求连通性? → 并查集/DFS           │                              │
│  │ • 每步取最优? → 贪心               │                              │
│  └─────────────────────────────────┘                              │
│           ↓                                                       │
│  Step 4: 复杂度倒推法                                              │
│  ┌─────────────────────────────────┐                              │
│  │ 根据数据规模推算需要的复杂度:       │                              │
│  │ • n ≤ 20     → O(2^n) 回溯/状压DP │                              │
│  │ • n ≤ 100    → O(n³) 区间DP       │                              │
│  │ • n ≤ 1000   → O(n²) 普通DP       │                              │
│  │ • n ≤ 10^5   → O(nlogn) 排序/二分  │                              │
│  │ • n ≤ 10^6   → O(n) 线性扫描      │                              │
│  │ • n ≤ 10^9   → O(logn) 二分/数学   │                              │
│  └─────────────────────────────────┘                              │
│           ↓                                                       │
│  Step 5: 编码 & 验证                                               │
│  ┌─────────────────────────────────┐                              │
│  │ • 先写伪代码/注释框架              │                              │
│  │ • 填充代码，保持整洁               │                              │
│  │ • 用Step1的小例子手动验证           │                              │
│  │ • 检查边界情况                     │                              │
│  └─────────────────────────────────┘                              │
│                                                                   │
└─────────────────────────────────────────────────────────────────┘
```

### 完全没思路时的救命锦囊

| 场景 | 策略 |
|:---|:---|
| **完全没见过** | 先写暴力解拿基础分，再想优化。华为OD部分通过也有分！ |
| **题目很长很复杂** | 拆分问题：先解决简化版，再处理附加条件 |
| **数据结构不确定** | 想想用什么容器最方便: 需要去重→set, 需要映射→dict, 需要有序→排序/堆 |
| **DP状态定义不出来** | 试试「以i结尾」或「前i个」或「区间[i,j]」三种经典定义方式 |
| **贪心不确定对不对** | 先贪心写一版，用暴力解对拍验证几个case |

### 华为OD机试的特殊策略

```
┌─────────────────────────────────────────────────────────────┐
│                 华为OD机试时间分配策略                        │
│                                                               │
│  总时间: 150分钟  总分: 400分  及格: 150分                     │
│                                                               │
│  第1题 (100分/简单) ──→ 30分钟内搞定                          │
│  第2题 (100分/简单) ──→ 30分钟内搞定                          │
│  第3题 (200分/困难) ──→ 60分钟                                │
│  检查 + Buffer ──────→ 30分钟                                 │
│                                                               │
│  注意:                                                        │
│  • ACM模式，每道题只能提交一次！本地多测几组再提交              │
│  • 100分题大多是模拟/字符串/简单贪心/滑动窗口                  │
│  • 200分题大多是DP/BFS+DFS/回溯/并查集                        │
│  • 抽中原题概率约80%，刷真题性价比极高                         │
│  • 代码相似度>80%判作弊，注意修改变量名                        │
└─────────────────────────────────────────────────────────────┘
```

---

## 四、华为OD高频考点 × 模式矩阵

| 分值 | 高频模式 | 经典真题 | 难度 |
|:---:|:---|:---|:---:|
| 100 | 滑动窗口 | 补种未成活胡杨、新词挖掘 | ★☆☆ |
| 100 | 双指针 | 太阳能板最大面积、水库蓄水问题 | ★☆☆ |
| 100 | 贪心 | 田忌赛马、万能字符单词拼写 | ★☆☆ |
| 100 | 模拟/字符串 | IPv4地址转换、IMSI匹配 | ★☆☆ |
| 100 | 二分查找 | 有序数组中的查找类题目 | ★☆☆ |
| 100 | 前缀和/差分 | 区间统计类题目 | ★★☆ |
| 200 | 动态规划 | 跳格子3、代表团坐车、执行任务赚积分 | ★★★ |
| 200 | BFS/DFS | 上班之路、篮球比赛 | ★★★ |
| 200 | 回溯 | 分披萨、检查数字组合 | ★★☆ |
| 200 | 并查集 | 快递业务站、需要广播的服务器数量 | ★★★ |
| 200 | 拓扑排序 | 依赖关系类题目 | ★★☆ |

---

## 五、一句话总结

```
╔══════════════════════════════════════════════════════════════════╗
║                                                                  ║
║   面试算法的本质 = 模式识别 + 模板套用 + 边界处理                  ║
║                                                                  ║
║   11个核心模式 × 每个1~2道经典题 = 不到25道题                     ║
║   掌握这25道，可以覆盖80%以上的面试算法题                          ║
║                                                                  ║
║   遇到陌生题: 读题画图 → 暴力解 → 模式匹配 → 复杂度倒推 → 编码    ║
║                                                                  ║
╚══════════════════════════════════════════════════════════════════╝
```

---

## 六、Java ACM模式输入输出模板（华为OD必备）

> 华为OD机试采用ACM模式（非LeetCode核心代码模式），需要自己处理输入输出。

```java
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // 方式1: Scanner（简单但较慢）
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();           // 读一个整数
        String s = sc.next();           // 读一个字符串（空格分隔）
        String line = sc.nextLine();    // 读一整行

        // 方式2: BufferedReader（推荐，速度快）
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line2 = br.readLine();                     // 读一行
        String[] parts = line2.split(" ");                // 按空格分割
        int a = Integer.parseInt(parts[0]);               // 转整数
        int[] arr = Arrays.stream(parts).mapToInt(Integer::parseInt).toArray();  // 一行转数组

        // 输出
        System.out.println(result);                       // 带换行
        StringBuilder sb = new StringBuilder();           // 拼接大量输出时用StringBuilder
        for (int i = 0; i < n; i++) {
            if (i > 0) sb.append(" ");
            sb.append(arr[i]);
        }
        System.out.println(sb);
    }
}
```

### 常用Java数据结构速查

```java
// ===== 哈希表 =====
Map<String, Integer> map = new HashMap<>();
map.put("key", 1);
map.getOrDefault("key", 0);
map.merge("key", 1, Integer::sum);      // 计数器惯用法
map.containsKey("key");

// ===== 优先队列(堆) =====
PriorityQueue<Integer> minHeap = new PriorityQueue<>();                // 小顶堆
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); // 大顶堆
PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]); // 自定义比较

// ===== 双端队列(当栈/队列用) =====
Deque<Integer> stack = new ArrayDeque<>();   // 当栈: push/pop/peek
Queue<Integer> queue = new LinkedList<>();   // 当队列: offer/poll/peek

// ===== 排序 =====
Arrays.sort(arr);                                         // 基本类型排序
Arrays.sort(arr2D, (a, b) -> a[0] - b[0]);               // 二维数组按第一列排序
Collections.sort(list, Comparator.comparingInt(a -> a[1])); // List排序

// ===== 常用工具 =====
Arrays.fill(dp, Integer.MAX_VALUE);    // 数组填充
Arrays.copyOfRange(arr, from, to);     // 数组切片
Collections.binarySearch(list, key);   // 二分查找
```

---

## 参考资源

- [Grokking the Coding Interview - 28种编码面试模式](https://www.designgurus.io/course/grokking-the-coding-interview)
- [algorithm-pattern (GitHub) - 算法模板，最科学的刷题方式](https://github.com/greyireland/algorithm-pattern)
- [华为OD机试2025题库 - CSDN按算法分类](https://blog.csdn.net/guorui_java/article/details/149004330)
- [欧弟算法 - 华为OD全攻略](https://www.odalgo.com/od-questions/2025/)
- [华为OD机试知识点汇总 - 知乎](https://zhuanlan.zhihu.com/p/648283751)
- [华为OD 2024最新题库分类 - 知乎](https://zhuanlan.zhihu.com/p/683081065)
- [面试最常考的100道LeetCode算法题分类整理](https://zhuanlan.zhihu.com/p/449686402)
- [CodeTop - 面试题频率排序](https://codetop.cc/home)
- [牛客网 - 华为OD讨论区](https://www.nowcoder.com/discuss/475623540998393856)
