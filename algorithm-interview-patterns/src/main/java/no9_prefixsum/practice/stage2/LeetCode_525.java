package no9_prefixsum.practice.stage2;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LeetCode_525
 * @Description 连续数组
 * 题目：给定一个二进制数组 nums，找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。
 * <p>
 * 示例1：
 * 输入：nums = [0,1]
 * 输出：2
 * <p>
 * 示例2：
 * 输入：nums = [0,1,0]
 * 输出：2
 * <p>
 * 提示：
 * 1 <= nums.length <= 10^5
 * nums[i] 不是 0 就是 1
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_525 {

    /**
     * 原解：保留你的原始思路。
     *
     * 这份代码的优点：
     * 1. 你已经抓住了本题最关键的转换：把 0 看成 -1，把 1 看成 1。
     * 2. 一旦这样转换后，题目就变成了“求和为 0 的最长连续子数组”。
     * 3. 说明你已经找到了这题的核心突破口。
     *
     * 但这份代码当前还不能正确解决整道题，原因在于：
     * 1. 你只寻找了 preSum[i] == 0 的最后一个位置。
     * 2. 这只代表“从下标 0 开始，到 i - 1 结束”的区间和为 0。
     * 3. 但题目要求的是“任意起点、任意终点”的最长连续子数组。
     * 4. 所以当前代码只覆盖了“最长区间恰好从 0 开始”的特殊情况。
     *
     * 一句话评价：
     * “方向完全对，但还差最后一步——要从‘前缀和为 0’升级到‘相同前缀和之间的区间和为 0’。”
     */
    public static int prefixSum(int[] nums) {
        int[] preSum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            preSum[i + 1] = preSum[i] + (nums[i] == 0 ? -1 : 1);
        }

        int lastZeroIndex = -1;
        for (int i = 1; i < preSum.length; i++) {
            if (preSum[i] == 0) {
                lastZeroIndex = i;
            }
        }
        return lastZeroIndex;
    }

    /**
     * 错误原因拆解。
     *
     * 正确的数学关系应该是：
     * 如果 preSum[i] == preSum[j]，那么区间 [j, i - 1] 的和就是 0。
     *
     * 这意味着：
     * 1. 不只是 preSum[i] == 0 才有意义。
     * 2. 只要两个位置的前缀和相等，它们之间就形成一个“0 和 1 数量相同”的区间。
     * 3. 为了让区间尽可能长，应该记录某个前缀和最早出现的位置。
     *
     * 所以本题的真正解法不是：
     * “找最后一个 preSum == 0 的位置”，
     * 而是：
     * “找相同 preSum 之间最远的距离”。
     */

    /**
     * 标准解：前缀和 + 哈希表。
     *
     * 核心思路：
     * 1. 把 0 记为 -1，把 1 记为 1。
     * 2. 这样“0 和 1 数量相同”就等价于“某段区间和为 0”。
     * 3. 如果两个位置的前缀和相同，那么它们之间的区间和为 0。
     * 4. 用哈希表记录每个前缀和第一次出现的位置。
     * 5. 当同一个前缀和再次出现时，就可以更新最长长度。
     *
     * 为什么哈希表里先放 (0, 0)？
     * 1. 表示前 0 个元素的和为 0。
     * 2. 这样如果某一段前缀本身就满足和为 0，也能统一处理。
     */
    public static int findMaxLength(int[] nums) {
        Map<Integer, Integer> firstIndexMap = new HashMap<>();
        firstIndexMap.put(0, 0);

        int preSum = 0;
        int maxLength = 0;

        for (int i = 0; i < nums.length; i++) {
            preSum += nums[i] == 0 ? -1 : 1;
            int currentIndex = i + 1;

            if (firstIndexMap.containsKey(preSum)) {
                maxLength = Math.max(maxLength, currentIndex - firstIndexMap.get(preSum));
            } else {
                firstIndexMap.put(preSum, currentIndex);
            }
        }
        return maxLength;
    }

    /**
     * 如何从原解流畅过渡到标准解？
     *
     * 可以这样讲：
     * 1. 先把 0 转成 -1，把 1 保持为 1。
     * 2. 这样问题就从“0 和 1 数量相同”变成“某段区间和为 0”。
     * 3. 我原本先想到的是找 preSum == 0 的位置，这能处理“从开头开始”的情况。
     * 4. 但继续推广会发现，只要两个位置前缀和相同，它们之间的区间和也为 0。
     * 5. 所以真正要维护的不是“最后一个 0 前缀和”，而是“每个前缀和第一次出现的位置”。
     * 6. 这样每次遇到重复前缀和，就能立刻计算一个候选最长区间。
     *
     * 一句话过渡模板：
     * “我先把问题转成前缀和为 0 的最长子数组；
     *  再进一步，不只是前缀和本身等于 0，有两个前缀和相等时，中间那段也等于 0，
     *  所以用哈希表记录前缀和最早出现的位置，就能得到标准解。”
     */

    /**
     * 通用模板：前缀和 + 哈希表找最长子数组。
     *
     * 适用场景：
     * 1. 求“最长子数组”满足某种和关系。
     * 2. 可以把原问题转化为“子数组和等于某个目标值”。
     * 3. 本题就是目标值为 0 的典型案例。
     *
     * 通用套路：
     * 1. 维护前缀和 preSum。
     * 2. 哈希表记录某个前缀和第一次出现的位置。
     * 3. 如果当前 preSum 之前出现过，说明中间这段子数组满足条件。
     * 4. 用当前位置减去最早位置，更新最长长度。
     * 5. 只记录第一次出现的位置，因为第一次出现才能让区间最长。
     */
    public static int longestSubArrayTemplate(int[] nums) {
        Map<Integer, Integer> firstIndexMap = new HashMap<>();
        firstIndexMap.put(0, 0);

        int preSum = 0;
        int maxLength = 0;

        for (int i = 0; i < nums.length; i++) {
            preSum += nums[i] == 0 ? -1 : 1;
            int currentIndex = i + 1;

            if (firstIndexMap.containsKey(preSum)) {
                maxLength = Math.max(maxLength, currentIndex - firstIndexMap.get(preSum));
            } else {
                firstIndexMap.put(preSum, currentIndex);
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        int[] nums1 = {0, 1};
        int[] nums2 = {0, 1, 0};
        int[] nums3 = {0, 0, 1, 0, 0, 0, 1, 1};

        System.out.println("===== 保留你的原解 =====");
        System.out.println(prefixSum(nums1)); // 2
        System.out.println(prefixSum(nums2)); // 2
        System.out.println(prefixSum(nums3)); // 原解只能覆盖部分情况

        System.out.println("===== 标准解 =====");
        System.out.println(findMaxLength(nums1)); // 2
        System.out.println(findMaxLength(nums2)); // 2
        System.out.println(findMaxLength(nums3)); // 6

        System.out.println("===== 通用模板演示 =====");
        System.out.println(longestSubArrayTemplate(nums1)); // 2
        System.out.println(longestSubArrayTemplate(nums2)); // 2
        System.out.println(longestSubArrayTemplate(nums3)); // 6
    }
}
