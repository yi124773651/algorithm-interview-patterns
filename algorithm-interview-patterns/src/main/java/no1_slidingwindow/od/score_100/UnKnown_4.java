package no1_slidingwindow.od.score_100;

/**
 * @ClassName UnKnown_4
 * @Description 滑动窗口最大值（实际求滑动窗口最大和）
 * @Author gm
 * @Date 2026/4/3 02:13
 * <p>
 * 华为OD机试真题 100分
 * <p>
 * 题目描述
 * 有一个N个整数的数组，和一个长度为M的窗口，窗口从数组内的第一个数开始滑动直到窗口不能滑动为止，
 * 每次窗口滑动产生一个窗口和（窗口内所有数的和），求窗口滑动产生的所有窗口和的最大值。
 * <p>
 * 输入描述
 * 第一行输入一个正整数N，表示整数个数。（0 < N < 100000）
 * 第二行输入N个整数，整数的取值范围为 [-100, 100]。
 * 第三行输入一个正整数M，M代表窗口的大小，M <= 100000，且 M <= N。
 * <p>
 * 输出描述
 * 窗口滑动产生所有窗口和的最大值。
 * <p>
 * 示例1
 * 输入
 * 6
 * 10 26 89 36 78 12
 * 3
 * <p>
 * 输出
 * 203
 * <p>
 * 说明
 * 窗口长度为3，窗口滑动产生的窗口和分别为：
 * 10+26+89=125, 26+89+36=151, 89+36+78=203, 36+78+12=126
 * 所以窗口滑动产生的所有窗口和的最大值为203。
 */
public class UnKnown_4 {
    public static void main(String[] args) {
        int[] nums = new int[]{10, 26, 89, 36, 78, 12};
        int m = 3;
        int result = solution(nums, m);
        System.out.println(result);
    }

    private static int solution(int[] nums, int m) {
        int maxSum = Integer.MIN_VALUE;
        int left = 0;
        int sum = 0;
        for (int right = 0; right < nums.length; right++) {
            //扩容，更新状态
            sum += nums[right];
            //判断缩容条件
            if (right - left + 1 > m) {
                sum -= nums[left];
                left++;
            }
            //窗口大小等于m时，更新结果
            //修正点：将第 58 行的 maxSum 更新加上 if (right - left + 1 == m) 条件，确保只在窗口恰好填满 m 个元素时才比较最大值。
            if (right - left + 1 == m) {
                maxSum = Math.max(maxSum, sum);
            }
        }
        return maxSum;
    }
}
