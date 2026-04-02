# Algorithm Interview Patterns

> 拒绝题海战术，建立「题目特征 -> 解题模式」的条件反射。11 种核心模式，每种精选经典题，以不变应万变。

## 分支说明

| 分支 | 用途 |
|:---|:---|
| `main` | 包含完整解答、踩坑笔记和优化思路，做完题后在此分支提交 |
| `practice` | 纯净练习分支，所有题目仅保留题目描述和方法框架（TODO），用于重复刷题训练 |

> 切换到练习分支: `git checkout practice`

## 难度分级

| 阶段 | 难度 | 说明 |
|:---:|:---:|:---|
| Stage 1 | Easy | 模式入门，掌握基本模板 |
| Stage 2 | Easy-Medium | 模板变形，引入常见技巧 |
| Stage 3 | Medium-Hard | 多技巧综合，考察灵活运用 |
| Stage 4 | Hard | 高难度经典题，面试压轴 |

## 特征识别速查

| 看到这些关键词 | 立刻想到 | 复杂度 |
|:---|:---|:---|
| 连续子数组/子串、窗口大小K | 滑动窗口 | O(n) |
| 有序数组、两数之和、接雨水 | 双指针 | O(n) |
| 层序遍历、最短路径、矩阵搜索 | BFS | O(V+E) |
| 所有路径、排列组合、N皇后 | DFS/回溯 | O(2^n) |
| 最优解、背包、子序列 | 动态规划 | O(n^2) |
| 局部最优即全局最优、区间调度 | 贪心 | O(nlogn) |
| 有序查找、旋转数组 | 二分查找 | O(logn) |
| 括号匹配、下一个更大元素 | 栈/单调栈 | O(n) |
| 子数组和、区间批量操作 | 前缀和/差分 | O(n) |
| 连通性、集合合并、朋友圈 | 并查集 | O(α(n)) |
| 依赖关系、课程表、执行顺序 | 拓扑排序 | O(V+E) |

---

## 题目索引

### 模式 1: 滑动窗口 (`no1_slidingwindow`)

> 核心思想: 维护一个窗口在数组/字符串上滑动，避免重复计算。

| 阶段 | 题号 | 题目 | 难度 |
|:---:|:---:|:---|:---:|
| Stage 1 | [643](https://leetcode.cn/problems/maximum-average-subarray-i/) | 子数组最大平均数 I | Easy |
| Stage 1 | [438](https://leetcode.cn/problems/find-all-anagrams-in-a-string/) | 找到字符串中所有字母异位词 | Medium |
| Stage 2 | [3](https://leetcode.cn/problems/longest-substring-without-repeating-characters/) | 无重复字符的最长子串 | Medium |
| Stage 2 | [424](https://leetcode.cn/problems/longest-repeating-character-replacement/) | 替换后的最长重复字符 | Medium |
| Stage 2 | [1004](https://leetcode.cn/problems/max-consecutive-ones-iii/) | 最大连续1的个数 III | Medium |
| Stage 3 | [209](https://leetcode.cn/problems/minimum-size-subarray-sum/) | 长度最小的子数组 | Medium |
| Stage 3 | [76](https://leetcode.cn/problems/minimum-window-substring/) | 最小覆盖子串 | Hard |
| Stage 4 | [239](https://leetcode.cn/problems/sliding-window-maximum/) | 滑动窗口最大值 | Hard |

### 模式 2: 双指针 (`no2_twopointertechnique`)

> 核心思想: 两个指针协同移动，减少嵌套循环。

| 阶段 | 题号 | 题目 | 难度 |
|:---:|:---:|:---|:---:|
| Stage 1 | [125](https://leetcode.cn/problems/valid-palindrome/) | 验证回文串 | Easy |
| Stage 1 | [167](https://leetcode.cn/problems/two-sum-ii-input-array-is-sorted/) | 两数之和 II | Medium |
| Stage 1 | [15](https://leetcode.cn/problems/3sum/) | 三数之和 | Medium |
| Stage 2 | [11](https://leetcode.cn/problems/container-with-most-water/) | 盛最多水的容器 | Medium |
| Stage 2 | [75](https://leetcode.cn/problems/sort-colors/) | 颜色分类（荷兰国旗） | Medium |
| Stage 3 | [16](https://leetcode.cn/problems/3sum-closest/) | 最接近的三数之和 | Medium |
| Stage 3 | [42](https://leetcode.cn/problems/trapping-rain-water/) | 接雨水 | Hard |
| Stage 4 | [18](https://leetcode.cn/problems/4sum/) | 四数之和 | Medium |

### 模式 3: BFS 广度优先搜索 (`no3_bfs`)

> 核心思想: 逐层扩展，天然适合求最短路径/最少步数。

| 阶段 | 题号 | 题目 | 难度 |
|:---:|:---:|:---|:---:|
| Stage 1 | [102](https://leetcode.cn/problems/binary-tree-level-order-traversal/) | 二叉树的层序遍历 | Medium |
| Stage 1 | [111](https://leetcode.cn/problems/minimum-depth-of-binary-tree/) | 二叉树的最小深度 | Easy |
| Stage 2 | [200](https://leetcode.cn/problems/number-of-islands/) | 岛屿数量 | Medium |
| Stage 2 | [994](https://leetcode.cn/problems/rotting-oranges/) | 腐烂的橘子 | Medium |
| Stage 3 | [127](https://leetcode.cn/problems/word-ladder/) | 单词接龙 | Hard |
| Stage 4 | [773](https://leetcode.cn/problems/sliding-puzzle/) | 滑动谜题 | Hard |

### 模式 4: DFS / 回溯 (`no4_dfsbacktracking`)

> 核心思想: 深入探索所有路径，不行就回退。做选择 -> 递归 -> 撤销选择。

| 阶段 | 题号 | 题目 | 难度 |
|:---:|:---:|:---|:---:|
| Stage 1 | [46](https://leetcode.cn/problems/permutations/) | 全排列 | Medium |
| Stage 1 | [78](https://leetcode.cn/problems/subsets/) | 子集 | Medium |
| Stage 2 | [39](https://leetcode.cn/problems/combination-sum/) | 组合总和 | Medium |
| Stage 2 | [77](https://leetcode.cn/problems/combinations/) | 组合 | Medium |
| Stage 3 | [79](https://leetcode.cn/problems/word-search/) | 单词搜索 | Medium |
| Stage 3 | [131](https://leetcode.cn/problems/palindrome-partitioning/) | 分割回文串 | Medium |
| Stage 4 | [51](https://leetcode.cn/problems/n-queens/) | N 皇后 | Hard |

### 模式 5: 动态规划 (`no5_dynamicprogramming`)

> 核心思想: 大问题拆子问题，记住子问题的解。四步法: 定义状态 -> 转移方程 -> 初始条件 -> 遍历顺序。

| 阶段 | 题号 | 题目 | 难度 |
|:---:|:---:|:---|:---:|
| Stage 1 | [509](https://leetcode.cn/problems/fibonacci-number/) | 斐波那契数 | Easy |
| Stage 1 | [70](https://leetcode.cn/problems/climbing-stairs/) | 爬楼梯 | Easy |
| Stage 2 | [300](https://leetcode.cn/problems/longest-increasing-subsequence/) | 最长递增子序列 | Medium |
| Stage 2 | [322](https://leetcode.cn/problems/coin-change/) | 零钱兑换 | Medium |
| Stage 3 | [1143](https://leetcode.cn/problems/longest-common-subsequence/) | 最长公共子序列 | Medium |
| Stage 3 | [416](https://leetcode.cn/problems/partition-equal-subset-sum/) | 分割等和子集 | Medium |
| Stage 4 | [72](https://leetcode.cn/problems/edit-distance/) | 编辑距离 | Medium |

### 模式 6: 贪心 (`no6_greedy`)

> 核心思想: 每一步选当前最优，局部最优推出全局最优。

| 阶段 | 题号 | 题目 | 难度 |
|:---:|:---:|:---|:---:|
| Stage 1 | [455](https://leetcode.cn/problems/assign-cookies/) | 分发饼干 | Easy |
| Stage 1 | [605](https://leetcode.cn/problems/can-place-flowers/) | 种花问题 | Easy |
| Stage 2 | [55](https://leetcode.cn/problems/jump-game/) | 跳跃游戏 | Medium |
| Stage 2 | [435](https://leetcode.cn/problems/non-overlapping-intervals/) | 无重叠区间 | Medium |
| Stage 3 | [45](https://leetcode.cn/problems/jump-game-ii/) | 跳跃游戏 II | Medium |
| Stage 3 | [134](https://leetcode.cn/problems/gas-station/) | 加油站 | Medium |
| Stage 4 | [135](https://leetcode.cn/problems/candy/) | 分发糖果 | Hard |

### 模式 7: 二分查找 (`no7_binarysearch`)

> 核心思想: 每次排除一半搜索空间。注意三种边界: 找确切值、找左边界、找右边界。

| 阶段 | 题号 | 题目 | 难度 |
|:---:|:---:|:---|:---:|
| Stage 1 | [704](https://leetcode.cn/problems/binary-search/) | 二分查找 | Easy |
| Stage 1 | [35](https://leetcode.cn/problems/search-insert-position/) | 搜索插入位置 | Easy |
| Stage 2 | [34](https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/) | 排序数组中查找首尾位置 | Medium |
| Stage 2 | [33](https://leetcode.cn/problems/search-in-rotated-sorted-array/) | 搜索旋转排序数组 | Medium |
| Stage 3 | [153](https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array/) | 旋转排序数组的最小值 | Medium |
| Stage 3 | [162](https://leetcode.cn/problems/find-peak-element/) | 寻找峰值 | Medium |
| Stage 4 | [4](https://leetcode.cn/problems/median-of-two-sorted-arrays/) | 两个正序数组的中位数 | Hard |

### 模式 8: 栈 / 单调栈 (`no8_stack`)

> 核心思想: 后进先出，维护单调性。

| 阶段 | 题号 | 题目 | 难度 |
|:---:|:---:|:---|:---:|
| Stage 1 | [20](https://leetcode.cn/problems/valid-parentheses/) | 有效的括号 | Easy |
| Stage 1 | [155](https://leetcode.cn/problems/min-stack/) | 最小栈 | Medium |
| Stage 2 | [739](https://leetcode.cn/problems/daily-temperatures/) | 每日温度 | Medium |
| Stage 2 | [496](https://leetcode.cn/problems/next-greater-element-i/) | 下一个更大元素 I | Easy |
| Stage 3 | [84](https://leetcode.cn/problems/largest-rectangle-in-histogram/) | 柱状图中最大的矩形 | Hard |
| Stage 3 | [394](https://leetcode.cn/problems/decode-string/) | 字符串解码 | Medium |
| Stage 4 | [85](https://leetcode.cn/problems/maximal-rectangle/) | 最大矩形 | Hard |

### 模式 9: 前缀和 / 差分 (`no9_prefixsum`)

> 核心思想: 预处理累加和，O(1) 求区间和；差分数组处理区间批量操作。

| 阶段 | 题号 | 题目 | 难度 |
|:---:|:---:|:---|:---:|
| Stage 1 | [303](https://leetcode.cn/problems/range-sum-query-immutable/) | 区域和检索 - 数组不可变 | Easy |
| Stage 1 | [724](https://leetcode.cn/problems/find-pivot-index/) | 寻找数组的中心下标 | Easy |
| Stage 2 | [560](https://leetcode.cn/problems/subarray-sum-equals-k/) | 和为 K 的子数组 | Medium |
| Stage 2 | [525](https://leetcode.cn/problems/contiguous-array/) | 连续数组 | Medium |
| Stage 3 | [1109](https://leetcode.cn/problems/corporate-flight-bookings/) | 航班预订统计（差分） | Medium |
| Stage 4 | [437](https://leetcode.cn/problems/path-sum-iii/) | 路径总和 III | Medium |

### 模式 10: 并查集 (`no10_unionfind`)

> 核心思想: 高效管理集合的合并与查询。路径压缩 + 按秩合并。

| 阶段 | 题号 | 题目 | 难度 |
|:---:|:---:|:---|:---:|
| Stage 1 | [547](https://leetcode.cn/problems/number-of-provinces/) | 省份数量 | Medium |
| Stage 1 | [684](https://leetcode.cn/problems/redundant-connection/) | 冗余连接 | Medium |
| Stage 2 | [990](https://leetcode.cn/problems/satisfiability-of-equality-equations/) | 等式方程的可满足性 | Medium |
| Stage 3 | [399](https://leetcode.cn/problems/evaluate-division/) | 除法求值 | Medium |
| Stage 4 | [765](https://leetcode.cn/problems/couples-holding-hands/) | 情侣牵手 | Hard |

### 模式 11: 拓扑排序 (`no11_topologicalsort`)

> 核心思想: 处理有向无环图中的依赖顺序。入度为 0 的节点先处理。

| 阶段 | 题号 | 题目 | 难度 |
|:---:|:---:|:---|:---:|
| Stage 1 | [207](https://leetcode.cn/problems/course-schedule/) | 课程表 | Medium |
| Stage 1 | [210](https://leetcode.cn/problems/course-schedule-ii/) | 课程表 II | Medium |
| Stage 2 | [802](https://leetcode.cn/problems/find-eventual-safe-states/) | 找到最终的安全状态 | Medium |
| Stage 3 | [310](https://leetcode.cn/problems/minimum-height-trees/) | 最小高度树 | Medium |
| Stage 4 | [329](https://leetcode.cn/problems/longest-increasing-path-in-a-matrix/) | 矩阵中的最长递增路径 | Hard |

---

## 面对陌生题的 5 步拆解法

1. **读题画图** (2min) - 小例子手动模拟，确认边界
2. **暴力解** (3min) - 先写最笨的方法，暴力解也有分
3. **模式匹配** (3min) - 对照上方特征表，识别属于哪种模式
4. **复杂度倒推** - n<=20 回溯, n<=1000 O(n^2), n<=10^5 O(nlogn), n<=10^6 O(n)
5. **编码验证** - 先写注释框架，再填充代码，用小例子验证

## 参考资源

- [Grokking the Coding Interview](https://www.designgurus.io/course/grokking-the-coding-interview)
- [algorithm-pattern (GitHub)](https://github.com/greyireland/algorithm-pattern)
- [CodeTop - 面试题频率排序](https://codetop.cc/home)
