package no6_greedy.practice.stage3;

/**
 * @ClassName LeetCode_45
 * @Description 跳跃游戏 II
 * 题目: 给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。
 * 每个元素 nums[i] 表示从索引 i 向前跳转的最大长度。返回到达 nums[n - 1] 的最小跳跃次数。
 *
 * 示例1: 输入：nums = [2,3,1,1,4] 输出：2
 * 示例2: 输入：nums = [2,3,0,1,4] 输出：2
 *
 * 提示: 1 <= nums.length <= 10^4; 0 <= nums[i] <= 1000; 保证可以到达 nums[n - 1]
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_45 {

    public static void main(String[] args) {
        LeetCode_45 solution = new LeetCode_45();
        System.out.println(solution.greedy(new int[]{2, 3, 1, 1, 4})); // 2
        System.out.println(solution.greedy(new int[]{2, 3, 0, 1, 4})); // 2
    }

    public int greedy(int[] nums) {
        // TODO
        return 0;
    }
}
