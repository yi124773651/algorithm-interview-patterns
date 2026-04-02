package no7_binarysearch.practice.stage3;

/**
 * @ClassName LeetCode_153
 * @Description
 * 题目: 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次旋转后，得到输入数组。给你一个元素值互不相同的数组 nums，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的最小元素。你必须设计一个时间复杂度为 O(log n) 的算法。
 * 示例1: 输入：nums = [3,4,5,1,2] 输出：1
 * 示例2: 输入：nums = [4,5,6,7,0,1,2] 输出：0
 * 示例3: 输入：nums = [11,13,15,17] 输出：11
 * 提示: n == nums.length; 1 <= n <= 5000; -5000 <= nums[i] <= 5000; nums 中的所有整数互不相同
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_153 {
    public static void main(String[] args) {
        System.out.println(binarySearch(new int[]{3, 4, 5, 1, 2}));      // 1
        System.out.println(binarySearch(new int[]{4, 5, 6, 7, 0, 1, 2})); // 0
        System.out.println(binarySearch(new int[]{11, 13, 15, 17}));      // 11
    }

    public static int binarySearch(int[] nums) {
        // TODO
        return -1;
    }
}
