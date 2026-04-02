package no9_prefixsum.practice.stage2;

/**
 * @ClassName LeetCode_560
 * @Description 和为 K 的子数组
 * 题目：给你一个整数数组 nums 和一个整数 k，请你统计并返回该数组中和为 k 的子数组的个数。子数组是数组中元素的连续非空序列。
 *
 * 示例1：输入：nums = [1,1,1], k = 2 输出：2
 * 示例2：输入：nums = [1,2,3], k = 3 输出：2
 *
 * 提示：
 * 1 <= nums.length <= 2 * 10^4
 * -1000 <= nums[i] <= 1000
 * -10^7 <= k <= 10^7
 *
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_560 {

    public static int prefixSum(int[] nums, int k) {
        // TODO
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(prefixSum(new int[]{1, 1, 1}, 2)); // 2
        System.out.println(prefixSum(new int[]{1, 2, 3}, 3)); // 2
    }
}
