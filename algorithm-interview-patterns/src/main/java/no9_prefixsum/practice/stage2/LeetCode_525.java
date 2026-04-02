package no9_prefixsum.practice.stage2;

/**
 * @ClassName LeetCode_525
 * @Description 连续数组
 * 题目：给定一个二进制数组 nums，找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。
 *
 * 示例1：输入：nums = [0,1] 输出：2
 * 示例2：输入：nums = [0,1,0] 输出：2
 *
 * 提示：
 * 1 <= nums.length <= 10^5
 * nums[i] 不是 0 就是 1
 *
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_525 {

    public static int prefixSum(int[] nums) {
        // TODO
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(prefixSum(new int[]{0, 1})); // 2
        System.out.println(prefixSum(new int[]{0, 1, 0})); // 2
    }
}
