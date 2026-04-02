package no5_dynamicprogramming.practice.stage2;

/**
 * @ClassName LeetCode_300
 * @Description 最长递增子序列
 * 题目：给你一个整数数组 nums，找到其中最长严格递增子序列的长度。
 * 示例1：输入：nums = [10,9,2,5,3,7,101,18] 输出：4 解释：最长递增子序列是 [2,3,7,101]
 * 示例2：输入：nums = [0,1,0,3,2,3] 输出：4
 * 提示：1 <= nums.length <= 2500; -10^4 <= nums[i] <= 10^4
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_300 {

    public static void main(String[] args) {
        System.out.println(dp(new int[]{10, 9, 2, 5, 3, 7, 101, 18})); // 4
        System.out.println(dp(new int[]{0, 1, 0, 3, 2, 3})); // 4
    }

    public static int dp(int[] nums) {
        // TODO
        return -1;
    }
}
