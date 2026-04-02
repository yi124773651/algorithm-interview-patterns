package no8_stack.practice.stage2;

import java.util.Arrays;

/**
 * @ClassName LeetCode_496
 * @Description 下一个更大元素 I
 *
 * 题目: nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置右侧的第一个比 x 大的元素。
 * 给你两个没有重复元素的数组 nums1 和 nums2，下标从 0 开始计数，其中 nums1 是 nums2 的子集。
 * 请你找出 nums1 中每个元素在 nums2 中的下一个更大元素。
 *
 * 示例1: 输入：nums1 = [4,1,2], nums2 = [1,3,4,2] 输出：[-1,3,-1]
 * 示例2: 输入：nums1 = [2,4], nums2 = [1,2,3,4] 输出：[3,-1]
 *
 * 提示: 1 <= nums1.length <= nums2.length <= 1000; 0 <= nums1[i], nums2[i] <= 10^4;
 *       nums1和nums2中所有整数互不相同; nums1 中的所有整数同样出现在 nums2 中
 *
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_496 {

    public static void main(String[] args) {
        LeetCode_496 solution = new LeetCode_496();
        System.out.println(Arrays.toString(solution.stackSolve(new int[]{4, 1, 2}, new int[]{1, 3, 4, 2}))); // [-1,3,-1]
        System.out.println(Arrays.toString(solution.stackSolve(new int[]{2, 4}, new int[]{1, 2, 3, 4})));    // [3,-1]
    }

    public int[] stackSolve(int[] nums1, int[] nums2) {
        // TODO: 使用单调栈解决下一个更大元素问题
        return new int[nums1.length];
    }
}
