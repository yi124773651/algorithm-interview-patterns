package no9_prefixsum.practice.stage2;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LeetCode_560
 * @Description 和为 K 的子数组
 * 题目：给你一个整数数组 nums 和一个整数 k，请你统计并返回该数组中和为 k 的子数组的个数。
 * 子数组是数组中元素的连续非空序列。
 * <p>
 * 示例1：
 * 输入：nums = [1,1,1], k = 2
 * 输出：2
 * <p>
 * 示例2：
 * 输入：nums = [1,2,3], k = 3
 * 输出：2
 * <p>
 * 提示：
 * 1 <= nums.length <= 2 * 10^4
 * -1000 <= nums[i] <= 1000
 * -10^7 <= k <= 10^7
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_560 {

    /**
     * 原解：思路正确，结果也正确。
     *
     * 这份代码的优点：
     * 1. 你已经正确使用了前缀和。
     * 2. 能把“子数组和”转成“两个前缀和之差”。
     * 3. 判断条件 preSum[i] - preSum[j] == k 是完全正确的。
     *
     * 但这份代码还不是本题最标准的写法，主要问题在于：
     * 1. 你虽然用了前缀和，但后面仍然用了双重循环去枚举 j。
     * 2. 时间复杂度是 O(n^2)，当数据规模变大时性能会比较吃力。
     * 3. 本题标准解可以进一步优化到 O(n)。
     *
     * 一句话评价：
     * “前缀和已经用对了，差的不是思路方向，而是把枚举历史前缀和这一步继续优化掉。”
     */
    public static int prefixSum(int[] nums, int k) {
        int[] preSum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }

        int count = 0;
        for (int i = 1; i < preSum.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (preSum[i] - preSum[j] == k) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 复杂度问题拆解。
     *
     * 你当前代码的本质是：
     * 1. 固定一个右端点 i。
     * 2. 再回头枚举所有可能的左端点 j。
     * 3. 判断 preSum[i] - preSum[j] 是否等于 k。
     *
     * 这说明你已经把问题转成了：
     * “是否存在某个历史前缀和 preSum[j]，使得它等于 preSum[i] - k”。
     *
     * 那么继续优化时就会想到：
     * 1. 没必要每次都回头找所有 j。
     * 2. 只需要知道 preSum[i] - k 之前出现过多少次即可。
     * 3. 所以可以用哈希表记录“某个前缀和出现的次数”。
     *
     * 这就是本题从 O(n^2) 过渡到 O(n) 的关键。
     */

    /**
     * 标准解：前缀和 + 哈希表计数。
     *
     * 核心思路：
     * 1. 遍历数组时实时维护当前前缀和 preSum。
     * 2. 如果之前出现过 preSum - k，
     *    说明存在若干个位置 j，使得从 j 到当前位置的子数组和为 k。
     * 3. 这些合法区间的数量，正好等于前缀和 preSum - k 出现的次数。
     * 4. 因此用哈希表记录每个前缀和出现了多少次即可。
     *
     * 为什么要先放入 (0, 1)？
     * 1. 表示“前 0 个元素的前缀和为 0”出现过 1 次。
     * 2. 这样如果某段前缀本身和就等于 k，也能统一统计进去。
     */
    public static int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> preSumCountMap = new HashMap<>();
        preSumCountMap.put(0, 1);

        int preSum = 0;
        int count = 0;

        for (int num : nums) {
            preSum += num;
            count += preSumCountMap.getOrDefault(preSum - k, 0);
            preSumCountMap.put(preSum, preSumCountMap.getOrDefault(preSum, 0) + 1);
        }
        return count;
    }

    /**
     * 如何从原解流畅过渡到标准解？
     *
     * 可以这样讲：
     * 1. 我先用前缀和把子数组和转成两个前缀和之差。
     * 2. 原解里，我对每个右端点都回头找所有左端点，所以是 O(n^2)。
     * 3. 继续观察会发现，我真正要找的其实是：历史上有多少个前缀和等于 preSum - k。
     * 4. 既然只是查“出现次数”，就没必要每次重新遍历。
     * 5. 直接用哈希表维护每个前缀和出现的次数，就能把查询降到 O(1)。
     * 6. 整体复杂度也就从 O(n^2) 优化到了 O(n)。
     *
     * 一句话过渡模板：
     * “我先通过前缀和把问题转成 preSum[i] - preSum[j] = k；
     *  再进一步，本质上就是在问历史上有多少个 preSum[j] = preSum[i] - k，
     *  所以用哈希表统计前缀和出现次数，就能把双重循环优化掉。”
     */

    /**
     * 通用模板：前缀和 + 哈希表统计子数组个数。
     *
     * 适用场景：
     * 1. 题目要求“子数组和等于某个目标值”的个数。
     * 2. 数组中可能有负数，不能直接用滑动窗口。
     * 3. 需要在线性时间内统计所有合法区间数量。
     *
     * 通用套路：
     * 1. 维护当前前缀和 preSum。
     * 2. 如果历史上存在 preSum - target，说明可以组成合法区间。
     * 3. 合法区间数量等于 preSum - target 出现次数。
     * 4. 再把当前 preSum 的出现次数加一。
     *
     * 注意：
     * 1. 求“最长长度”通常记录最早出现位置。
     * 2. 求“区间个数”通常记录出现次数。
     */
    public static int subArrayCountTemplate(int[] nums, int target) {
        Map<Integer, Integer> preSumCountMap = new HashMap<>();
        preSumCountMap.put(0, 1);

        int preSum = 0;
        int count = 0;

        for (int num : nums) {
            preSum += num;
            count += preSumCountMap.getOrDefault(preSum - target, 0);
            preSumCountMap.put(preSum, preSumCountMap.getOrDefault(preSum, 0) + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 1, 1};
        int[] nums2 = {1, 2, 3};
        int[] nums3 = {1, -1, 0};

        System.out.println("===== 保留你的原解 =====");
        System.out.println(prefixSum(nums1, 2)); // 2
        System.out.println(prefixSum(nums2, 3)); // 2
        System.out.println(prefixSum(nums3, 0)); // 3

        System.out.println("===== 标准解 =====");
        System.out.println(subarraySum(nums1, 2)); // 2
        System.out.println(subarraySum(nums2, 3)); // 2
        System.out.println(subarraySum(nums3, 0)); // 3

        System.out.println("===== 通用模板演示 =====");
        System.out.println(subArrayCountTemplate(nums1, 2)); // 2
        System.out.println(subArrayCountTemplate(nums2, 3)); // 2
        System.out.println(subArrayCountTemplate(nums3, 0)); // 3
    }
}
