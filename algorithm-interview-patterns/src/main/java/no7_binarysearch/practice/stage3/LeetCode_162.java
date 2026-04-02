package no7_binarysearch.practice.stage3;

/**
 * @ClassName LeetCode_162
 * @Description
 * 题目: 峰值元素是指其值严格大于左右相邻值的元素。给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，返回任何一个峰值所在位置即可。你必须实现时间复杂度为 O(log n) 的算法。
 * 示例1: 输入：nums = [1,2,3,1] 输出：2
 * 示例2: 输入：nums = [1,2,1,3,5,6,4] 输出：5
 * 提示: 1 <= nums.length <= 1000; -2^31 <= nums[i] <= 2^31 - 1; 对于所有有效的 i 都有 nums[i] != nums[i + 1]
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_162 {
    public static void main(String[] args) {
        System.out.println(binarySearch(new int[]{1, 2, 3, 1}));          // 2
        System.out.println(binarySearch(new int[]{1, 2, 1, 3, 5, 6, 4})); // 5
    }

    public static int binarySearch(int[] nums) {
        // TODO
        return -1;
    }
}
