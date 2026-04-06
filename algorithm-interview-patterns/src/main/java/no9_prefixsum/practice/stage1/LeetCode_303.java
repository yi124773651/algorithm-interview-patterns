package no9_prefixsum.practice.stage1;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LeetCode_303
 * @Description 区域和检索 - 数组不可变
 * 题目：给定一个整数数组 nums，处理以下类型的多个查询：计算索引 left 和 right（包含 left 和 right）之间的 nums 元素的和。
 * 实现 NumArray 类：
 * NumArray(int[] nums) 使用数组 nums 初始化对象；
 * int sumRange(int left, int right) 返回数组 nums 中索引 left 和 right 之间的元素的总和。
 * <p>
 * 示例：
 * 输入：["NumArray","sumRange","sumRange","sumRange"] [[[-2,0,3,-5,2,-1]],[0,2],[2,5],[0,5]]
 * 输出：[null,1,-1,-3]
 * <p>
 * 提示：
 * 1 <= nums.length <= 10^4
 * -10^5 <= nums[i] <= 10^5
 * 0 <= left <= right < nums.length
 * 最多调用 10^4 次 sumRange
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_303 {

    /**
     * 原解：思路正确，可以通过本题。
     *
     * 这里保留你的原始写法，用于体现：
     * 1. 你已经理解了“前缀和”的本质。
     * 2. 你知道如何把多次区间求和优化到 O(1) 查询。
     *
     * 但从“面试标准写法”角度，它还有几个可以继续优化的点：
     * 1. 使用 Map 存前缀和，空间和常数开销都比数组更大。
     * 2. 这里的下标是连续的，天然更适合用 int[]。
     * 3. 查询时需要额外处理 left == 0 的边界，不够统一。
     * 4. new HashMap() 没写泛型，属于原始类型写法，不够规范。
     *
     * 所以这份代码的评价可以这样说：
     * “思路完全正确，已经抓住了前缀和的核心；
     *  只是数据结构选择上还可以进一步标准化。”
     */
    static class NumArray {
        Map<Integer, Integer> preSum = new HashMap<>();

        public NumArray(int[] nums) {
            int sum = 0;
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
                preSum.put(i, sum);
            }
        }

        public int sumRange(int left, int right) {
            return preSum.get(right) - (left - 1 < 0 ? 0 : preSum.get(left - 1));
        }
    }

    /**
     * 标准解：使用“前缀和数组”完成区间和查询。
     *
     * 为什么它比原解更标准？
     * 1. 数组下标连续，用数组存前缀和更自然。
     * 2. preSum[i] 表示“前 i 个元素的和”，统一采用左闭右开思想。
     * 3. 这样任何区间 [left, right] 的和都能统一写成：
     *    preSum[right + 1] - preSum[left]
     * 4. 不需要再单独讨论 left == 0 的边界。
     *
     * 这是面试中最推荐的写法：
     * 构造 O(n)，查询 O(1)，代码简洁、边界统一、可迁移性强。
     */
    static class NumArrayStandard {
        private final int[] preSum;

        public NumArrayStandard(int[] nums) {
            preSum = new int[nums.length + 1];
            for (int i = 0; i < nums.length; i++) {
                preSum[i + 1] = preSum[i] + nums[i];
            }
        }

        public int sumRange(int left, int right) {
            return preSum[right + 1] - preSum[left];
        }
    }

    /**
     * 如何从“你的原解”自然过渡到“标准解”？
     *
     * 可以按照下面这条思路讲，会非常流畅：
     * 1. 先说暴力解：每次查询都遍历区间，单次 O(n)，多次查询会慢。
     * 2. 再引出优化：既然数组不变，就可以提前预处理累计和。
     * 3. 于是得到前缀和：区间和 = 两段前缀和相减。
     * 4. 你当前的做法已经实现了这个核心思想，只是把前缀和放进了 Map。
     * 5. 因为题目下标连续，所以继续优化成 int[] 会更标准。
     * 6. 再把 preSum 定义成“前 i 个元素之和”，边界就彻底统一了。
     *
     * 一句话过渡模板：
     * “我一开始已经想到用前缀和来做区间查询，只是先用 Map 保存每个位置的累计和。
     *  如果进一步标准化，因为下标连续，我们可以直接改成前缀和数组，
     *  并把 preSum[i] 定义为前 i 个元素之和，这样区间公式会更统一。”
     */

    /**
     * 前缀和通用模板。
     *
     * 一维前缀和最常见的定义：
     * preSum[i] 表示原数组前 i 个元素的和。
     * 注意：这里是“前 i 个”，不是“下标 i 的前缀和”，所以数组长度通常是 n + 1。
     *
     * 通用构建公式：
     * preSum[0] = 0
     * preSum[i + 1] = preSum[i] + nums[i]
     *
     * 通用查询公式：
     * 区间 [left, right] 的元素和 = preSum[right + 1] - preSum[left]
     *
     * 适用场景：
     * 1. 原数组不频繁修改。
     * 2. 需要频繁查询区间和。
     * 3. 需要把某段区间问题转成“两个前缀量之差”。
     */
    static class PrefixSumTemplate {
        private final int[] preSum;

        public PrefixSumTemplate(int[] nums) {
            preSum = buildPreSum(nums);
        }

        /**
         * 构建前缀和数组。
         */
        private int[] buildPreSum(int[] nums) {
            int[] prefix = new int[nums.length + 1];
            for (int i = 0; i < nums.length; i++) {
                prefix[i + 1] = prefix[i] + nums[i];
            }
            return prefix;
        }

        /**
         * 查询闭区间 [left, right] 的区间和。
         */
        public int queryRangeSum(int left, int right) {
            return preSum[right + 1] - preSum[left];
        }
    }

    public static void main(String[] args) {
        int[] nums = {-2, 0, 3, -5, 2, -1};

        System.out.println("===== 保留你的原解 =====");
        NumArray numArray = new NumArray(nums);
        System.out.println(numArray.sumRange(0, 2)); // 1
        System.out.println(numArray.sumRange(2, 5)); // -1
        System.out.println(numArray.sumRange(0, 5)); // -3

        System.out.println("===== 标准解演示 =====");
        NumArrayStandard numArrayStandard = new NumArrayStandard(nums);
        System.out.println(numArrayStandard.sumRange(0, 2)); // 1
        System.out.println(numArrayStandard.sumRange(2, 5)); // -1
        System.out.println(numArrayStandard.sumRange(0, 5)); // -3

        System.out.println("===== 前缀和模板演示 =====");
        PrefixSumTemplate prefixSumTemplate = new PrefixSumTemplate(nums);
        System.out.println(prefixSumTemplate.queryRangeSum(0, 2)); // 1
        System.out.println(prefixSumTemplate.queryRangeSum(2, 5)); // -1
        System.out.println(prefixSumTemplate.queryRangeSum(0, 5)); // -3
    }
}
