package no7_binarysearch.practice.stage4;

/**
 * @ClassName LeetCode_4
 * @Description
 * 题目: 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的中位数。算法的时间复杂度应该为 O(log (m+n))。
 * 示例1: 输入：nums1 = [1,3], nums2 = [2] 输出：2.00000
 * 示例2: 输入：nums1 = [1,2], nums2 = [3,4] 输出：2.50000
 * 提示: nums1.length == m; nums2.length == n; 0 <= m <= 1000; 0 <= n <= 1000; 1 <= m + n <= 2000; -10^6 <= nums1[i], nums2[i] <= 10^6
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_4 {
    public static void main(String[] args) {
        System.out.println(binarySearch(new int[]{1, 3}, new int[]{2}));    // 2.0
        System.out.println(binarySearch(new int[]{1, 2}, new int[]{3, 4})); // 2.5
    }

    public static double binarySearch(int[] nums1, int[] nums2) {
        // TODO
        return 0.0;
    }
}
