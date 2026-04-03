package no5_dynamicprogramming.practice.stage3;

/**
 * @ClassName LeetCode_416
 * @Description 分割等和子集
 * 题目：给你一个只包含正整数的非空数组 nums。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 *
 * 示例1：
 * 输入：nums = [1,5,11,5]
 * 输出：true
 * 解释：数组可以分割成 [1,5,5] 和 [11]
 *
 * 示例2：
 * 输入：nums = [1,2,3,5]
 * 输出：false
 *
 * 提示：1 <= nums.length <= 200; 1 <= nums[i] <= 100
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_416 {

    public static void main(String[] args) {
        System.out.println(dp(new int[]{1, 5, 11, 5})); // true
        System.out.println(dp(new int[]{1, 2, 3, 5})); // false
    }

    public static boolean dp(int[] nums) {
        // TODO
        return false;
    }
}
