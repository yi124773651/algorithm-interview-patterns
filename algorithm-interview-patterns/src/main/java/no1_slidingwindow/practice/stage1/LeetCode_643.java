package no1_slidingwindow.practice.stage1;

/**
 * @ClassName LeetCode_643
 * @Description
 * 给你一个由 n 个元素组成的整数数组 nums 和一个整数 k 。
 * 请你找出平均数最大且 长度为 k 的连续子数组，并输出该最大平均数。
 * 任何误差小于 10-5 的答案都将被视为正确答案。
 *
 * 示例 1：
 *
 * 输入：nums = [1,12,-5,-6,50,3], k = 4
 * 输出：12.75
 * 解释：最大平均数 (12-5-6+50)/4 = 51/4 = 12.75
 * 示例 2：
 *
 * 输入：nums = [5], k = 1
 * 输出：5.00000
 *
 * 提示：
 * n == nums.length
 * 1 <= k <= n <= 105
 * -104 <= nums[i] <= 104
 * @Author gm
 * @Date 2026/4/1 18:31
 */
public class LeetCode_643 {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 12, -5, -6, 50, 3};
        int k = 4;
        double result = LeetCode_643.slidingWindow(nums, k);
        System.out.println(result);
    }

    private static double slidingWindow(int[] nums, int k) {
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            //1.扩展窗口，更新状态
            //2.判断是否需要缩容（固定窗口用if）
            //3.窗口满时更新结果
        }
        // TODO
        return 0;
    }
}
