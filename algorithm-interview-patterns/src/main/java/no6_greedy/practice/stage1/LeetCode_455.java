package no6_greedy.practice.stage1;

import java.util.Arrays;

/**
 * @ClassName LeetCode_455
 * @Description 分发饼干
 * 题目: 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。每个孩子最多只能给一块饼干。
 * 每个孩子 i 都有一个胃口值 g[i]；每块饼干 j 都有一个尺寸 s[j]。
 * 如果 s[j] >= g[i]，可以满足孩子。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
 * <p>
 * 示例1:
 * 输入：g = [1,2,3], s = [1,1]
 * 输出：1
 * 示例2:
 * 输入：g = [1,2], s = [1,2,3]
 * 输出：2
 * <p>
 * 提示: 1 <= g.length <= 3 * 10^4; 0 <= s.length <= 3 * 10^4; 1 <= g[i], s[j] <= 2^31 - 1
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_455 {

    public static void main(String[] args) {
        LeetCode_455 solution = new LeetCode_455();

        System.out.println("=== 原始解法(有Bug) ===");
        System.out.println(solution.greedy_original(new int[]{1, 2, 3}, new int[]{1, 1})); // 期望1
        System.out.println(solution.greedy_original(new int[]{1, 2}, new int[]{1, 2, 3})); // 期望2
        System.out.println(solution.greedy_original(new int[]{1, 2, 3}, new int[]{3}));    // 期望1，实际输出3 ❌
        System.out.println(solution.greedy_original(new int[]{1, 2}, new int[]{3, 1}));    // 期望2，实际输出1 ❌

        System.out.println("=== 正确解法(贪心+双指针) ===");
        System.out.println(solution.greedy(new int[]{1, 2, 3}, new int[]{1, 1})); // 1 ✅
        System.out.println(solution.greedy(new int[]{1, 2}, new int[]{1, 2, 3})); // 2 ✅
        System.out.println(solution.greedy(new int[]{1, 2, 3}, new int[]{3}));    // 1 ✅
        System.out.println(solution.greedy(new int[]{1, 2}, new int[]{3, 1}));    // 2 ✅
    }

    /**
     * 原始解法（有Bug）
     * Bug1: 内层循环匹配成功后没有break，一块饼干会满足多个孩子
     * Bug2: 饼干未排序，大饼干可能先喂了胃口小的孩子，浪费资源
     */
    public int greedy_original(int[] g, int[] s) {
        int matchedCount = 0;
        //简单的想法：对s做遍历  每次遍历找 s[i] 满足 使得g[j] < s[i] 但是g[j]最大的情况
        //有可能需要对g做排序 标记
        Arrays.sort(g);
        int[] matched = new int[g.length];  //标记 默认为0 为1则已满足
        for (int size : s) {
            for (int i = 0; i < g.length; i++) {
                if (g[i] <= size && matched[i] == 0) {
                    matched[i] = 1;
                    matchedCount++;
                }
            }
        }
        return matchedCount;
    }

    /**
     * 正确解法：贪心 + 双指针
     * 思路：将孩子胃口和饼干尺寸都排序，用尽可能小的饼干去满足胃口小的孩子
     * 时间复杂度：O(n log n + m log m)
     * 空间复杂度：O(log n + log m) 排序所需   排序是贪心的前提，无法再优化
     */
    public int greedy(int[] g, int[] s) {
        Arrays.sort(g); // 孩子胃口排序
        Arrays.sort(s); // 饼干尺寸排序
        int child = 0;  // 指向当前待满足的孩子
        int cookie = 0; // 指向当前饼干
        while (child < g.length && cookie < s.length) {
            if (s[cookie] >= g[child]) {
                // 当前饼干能满足当前孩子，孩子指针前进
                child++;
            }
            // 无论是否满足，饼干指针都前进（不满足说明这块饼干太小，跳过）
            cookie++;
        }
        return child; // child 即为被满足的孩子数量
    }

    /**
     * 贪心算法模板总结：
     *
     * 第一步：排序/预处理 —— 让"最优选择"变得显而易见
     * 第二步：贪心选择   —— 每一步都做当前最优的局部决策
     * 第三步：推进状态   —— 缩小问题规模，重复第二步
     *
     * 关键判断：贪心能用吗？
     * 如果"每一步的局部最优" 能推导出 "全局最优"，就能用贪心。
     * 反例：0-1背包问题，局部最优（选性价比最高的）不能得到全局最优。
     *
     * 模式一：排序 + 双指针匹配
     * 适用场景：两组东西做匹配/分配    小饼干优先喂小胃口
     * // 模板
     * Arrays.sort(demand);  // 需求排序
     * Arrays.sort(supply);  // 供给排序
     * int i = 0, j = 0;
     * while (i < demand.length && j < supply.length) {
     *     if (supply[j] >= demand[i]) {
     *         // 匹配成功，两个指针都前进
     *         i++;
     *     }
     *     // 匹配失败，只移动供给指针（当前供给太小，跳过）
     *     j++;
     * }
     * return i; // 匹配成功的数量
     *
     *
     * 模式二：逐步扫描，局部最优决策
     * 适用场景：遍历数组，每个位置做一个"种/不种、放/不放"的决策
     * // 模板
     * for (int i = 0; i < n; i++) {
     *     if (满足贪心条件) {
     *         执行操作;    // 种花、放置等
     *         更新状态;    // 跳过受影响的位置
     *     }
     * }
     *
     *
     * 模式三：区间贪心（排序 + 选择/淘汰）
     * 适用场景：区间调度、重叠区间、会议室问题
     * // 模板：按结束时间排序，尽量保留更多不重叠区间
     * Arrays.sort(intervals, (a, b) -> a[1] - b[1]);  // 按右端点排序
     * int end = intervals[0][1];
     * int remove = 0;
     * for (int i = 1; i < intervals.length; i++) {
     *     if (intervals[i][0] < end) {
     *         remove++;          // 重叠了，移除当前区间
     *     } else {
     *         end = intervals[i][1]; // 不重叠，更新边界
     *     }
     * }
     *
     * 模式四：维护可达边界 / 累计量
     * 适用场景：跳跃问题、能否到达终点、环形路线
     * // 模板A：可达性（跳跃游戏）
     * int maxReach = 0;
     * for (int i = 0; i < n; i++) {
     *     if (i > maxReach) return false;   // 到不了当前位置
     *     maxReach = Math.max(maxReach, i + nums[i]); // 更新最远可达
     * }
     *
     * // 模板B：累计量+重置起点（加油站）
     * int totalSum = 0, curSum = 0, start = 0;
     * for (int i = 0; i < n; i++) {
     *     int diff = gas[i] - cost[i];
     *     totalSum += diff;
     *     curSum += diff;
     *     if (curSum < 0) {   // 从start出发走不下去了
     *         start = i + 1;  // 重置起点
     *         curSum = 0;
     *     }
     * }
     * return totalSum >= 0 ? start : -1;
     *
     * 进阶模式：多方向贪心（两次遍历）
     * 适用场景：需要同时满足左右两个方向的约束
     *
     * // 模板：正向扫一遍 + 反向扫一遍，取交集/最大值
     * int[] result = new int[n];
     * Arrays.fill(result, 1);
     *
     * // 从左到右：保证右边比左边大时，右边值更大
     * for (int i = 1; i < n; i++) {
     *     if (ratings[i] > ratings[i - 1])
     *         result[i] = result[i - 1] + 1;
     * }
     * // 从右到左：保证左边比右边大时，左边值更大
     * for (int i = n - 2; i >= 0; i--) {
     *     if (ratings[i] > ratings[i + 1])
     *         result[i] = Math.max(result[i], result[i + 1] + 1);
     * }
     * --------------------------------------------------------------------------------------------
     *               排序+双指针        局部扫描         区间贪心        可达边界/累计      多方向贪心
     * Stage1(Easy)    455 ✅           605 📝
     * Stage2(Mid)                                      435 📝          55 📝
     * Stage3(Hard)                                                    45 📝  134 📝
     * Stage4(Hard)                                                                      135 📝
     * --------------------------------------------------------------------------------------------
     */
}
