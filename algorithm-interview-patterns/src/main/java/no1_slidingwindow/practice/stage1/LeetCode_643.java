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
//        int[] nums = new int[]{1,12,-5,-6,50,3};
//        int k  = 4;
        int[] nums = new int[]{5};
        int k  = 1;
        double result = LeetCode_643.slidingWindow(nums, k);
        System.out.println(result);
    }

    /**
     * 1. Bug：max 初始值不对
     * max = 0f，当数组元素全为负数时（如 [-1, -2, -3], k=2），最终结果会错误返回 0/k。应该初始化为 Float.MIN_VALUE
     * 或 -Float.MAX_VALUE。
     *
     * 2. 类型问题：不要用包装类型
     * Integer[] → int[]
     * Float → double
     * Float 精度只有约 7 位有效数字，题目要求误差小于 10⁻⁵，用 double 更安全。而且包装类型有自动拆装箱的开销，
     * int[] 和 double 就够了。
     *
     * 3. 更新结果的时机
     * 第 50 行在窗口还没填满 k 个元素时就在更新 max，虽然最终不一定影响结果（因为更大的 sum 通常出现在窗口满时），
     * 但逻辑上应该在窗口大小 == k 时才更新：
     *
     * if (right - left + 1 == k) {
     *     max = Math.max(max, sum);
     * }
     *
     * 4. 固定窗口用 if 而非 while
     * 窗口大小固定为 k，每次最多只需要缩一次，用 if 语义更清晰：
     *
     * if (right - left + 1 > k) {
     *     sum -= nums[left];
     *     left++;
     * }
     * @param nums
     * @param k
     * @return
     */
    private static double slidingWindow(int[] nums, int k) {
        double sum = 0;
        double max = -Double.MAX_VALUE;
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            //1.先扩容，更新状态
            sum += nums[right];
            //2.判断是否需要缩容（固定窗口用if）
            if (right - left + 1 > k) {
                sum -= nums[left];
                left++;
            }
            //3.窗口满时更新结果
            if (right - left + 1 == k) {
                max = Math.max(max, sum);
            }
        }
        return max / k;
    }
}
