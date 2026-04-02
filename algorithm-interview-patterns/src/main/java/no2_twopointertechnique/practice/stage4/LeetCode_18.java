package no2_twopointertechnique.practice.stage4;

import java.util.List;

/**
 * @ClassName LeetCode_18
 * @Description
 * 题目: 给你一个由 n 个整数组成的数组 nums，和一个目标值 target。
 * 请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b], nums[c], nums[d]]：
 * 0 <= a, b, c, d < n 且 a、b、c、d 互不相同
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 *
 * 示例 1：
 *
 * 输入：nums = [1,0,-1,0,-2,2], target = 0
 * 输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 *
 * 示例 2：
 *
 * 输入：nums = [2,2,2,2,2], target = 8
 * 输出：[[2,2,2,2]]
 *
 * 提示：
 * 1 <= nums.length <= 200
 * -10^9 <= nums[i] <= 10^9
 * -10^9 <= target <= 10^9
 * @Author gm
 * @Date 2026/4/2 15:00
 */
public class LeetCode_18 {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 0, -1, 0, -2, 2};
        int target = 0;
        List<List<Integer>> result = LeetCode_18.twoPointer(nums, target);
        System.out.println(result);
    }

    private static List<List<Integer>> twoPointer(int[] nums, int target) {
        // TODO: 在三数之和基础上再套一层循环，固定两个数 + 双指针，注意去重和溢出
        return null;
    }
}
