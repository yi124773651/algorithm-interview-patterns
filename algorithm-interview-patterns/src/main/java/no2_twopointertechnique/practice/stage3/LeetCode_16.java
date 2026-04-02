package no2_twopointertechnique.practice.stage3;

/**
 * @ClassName LeetCode_16
 * @Description
 * 题目: 给你一个长度为 n 的整数数组 nums 和一个目标值 target。
 * 请你从 nums 中选出三个整数，使它们的和与 target 最接近。返回这三个数的和。
 * 假定每组输入只存在恰好一个解。
 *
 * 示例 1：
 *
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 解释：与 target 最接近的和是 2（-1 + 2 + 1 = 2）。
 *
 * 示例 2：
 *
 * 输入：nums = [0,0,0], target = 1
 * 输出：0
 * 解释：与 target 最接近的和是 0（0 + 0 + 0 = 0）。
 *
 * 提示：
 * 3 <= nums.length <= 500
 * -1000 <= nums[i] <= 1000
 * -10^4 <= target <= 10^4
 * @Author gm
 * @Date 2026/4/2 15:00
 */
public class LeetCode_16 {
    public static void main(String[] args) {
        int[] nums = new int[]{-1, 2, 1, -4};
        int target = 1;
        int result = LeetCode_16.twoPointer(nums, target);
        System.out.println(result);
    }

    private static int twoPointer(int[] nums, int target) {
        // TODO: 先排序，再用固定一个数 + 双指针逼近
        return 0;
    }
}
