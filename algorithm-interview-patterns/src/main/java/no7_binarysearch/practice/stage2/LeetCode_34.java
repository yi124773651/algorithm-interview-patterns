package no7_binarysearch.practice.stage2;

/**
 * @ClassName LeetCode_34
 * @Description
 * 题目: 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。如果数组中不存在目标值 target，返回 [-1, -1]。你必须设计并实现时间复杂度为 O(log n) 的算法。
 * 示例1: 输入：nums = [5,7,7,8,8,10], target = 8 输出：[3,4]
 * 示例2: 输入：nums = [5,7,7,8,8,10], target = 6 输出：[-1,-1]
 * 提示: 0 <= nums.length <= 10^5; -10^9 <= nums[i] <= 10^9; nums 是一个非递减数组; -10^9 <= target <= 10^9
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_34 {
    public static void main(String[] args) {
        int[] nums = {5, 7, 7, 8, 8, 10};
        int[] result1 = binarySearch(nums, 8);
        System.out.println("[" + result1[0] + "," + result1[1] + "]");  // [3,4]
        int[] result2 = binarySearch(nums, 6);
        System.out.println("[" + result2[0] + "," + result2[1] + "]");  // [-1,-1]
    }

    public static int[] binarySearch(int[] nums, int target) {
        // TODO
        return new int[]{-1, -1};
    }
}
